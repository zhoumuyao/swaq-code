package com.example.service.Impl;

import com.example.entity.auth.Account;
import com.example.mapper.UserMapper;
import com.example.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Resource
    UserMapper userMapper;

    @Value("${spring.mail.username}")
    String from;

    @Resource
    MailSender mailSender;

    @Resource
    StringRedisTemplate template;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null)
            throw new UsernameNotFoundException("用户名不能为空");
        Account account = userMapper.findAccountByNameOrEmailOrPoliceId(username);
        if (account == null)
            throw new UsernameNotFoundException("密码错误");
        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("user")
                .build();
    }

    @Override
    public String sendValidateEmail(String email,String sessionId,boolean hasAccount){
//        String key = "email" + ": " +email+" "+sessionId+":"+hasAccount;
        String key = "email" + ": " +email+" "+ ":"+hasAccount;
        System.out.println(key);
        if(Boolean.TRUE.equals(template.hasKey(key))){
            Long expire = Optional.ofNullable(template.getExpire(key,TimeUnit.SECONDS)).orElse(0L);
            if(expire > 120){
                return "请求频繁，请稍后再试！";
            }
        }
        Account account = userMapper.findAccountByNameOrEmailOrPoliceId(email);
        if(account == null && hasAccount){
            return "此邮箱未被注册";
        }
        if(account != null && !hasAccount){
            return "此邮箱已被注册";
        }
        Random random = new Random();
        int code = random.nextInt(89999)+100000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("您的验证邮件");
        message.setText("验证码是"+code);
        try {
            mailSender.send(message);
            template.opsForValue().set(key,String.valueOf(code),3, TimeUnit.MINUTES);
            return null;
        }catch (MailException e){
            e.printStackTrace();
            return "邮件发送失败，请联系管理员!";
        }
    }

    @Override
    public String validateAndRegister(String username,String policeId, String policeStation,String password,String email,String code,String sessionId){
//        String key = "email"  +": " +email+" "+sessionId+":false";
        String key = "email" + ": " + email + " " + ":false";
        if(Boolean.TRUE.equals(template.hasKey(key))){
            String s = template.opsForValue().get(key);
            if(s == null){
                return "验证码失效，请重新获取";
            }
            if (s.equals(code)){
                Account account1 = userMapper.findAccountByNameOrEmailOrPoliceId(username);
                Account account2 = userMapper.findAccountByNameOrEmailOrPoliceId(policeId);
                if(account1 != null ){
                    return "该用户名已被注册";
                }
                if (account2 != null){
                    return "该警号已被注册";
                }
                template.delete(key);
                password = encoder.encode(password);
                if(userMapper.createAccount(username,policeId,policeStation,password,email) > 0){
                    return null;
                }else {
                    return "内部错误，请联系管理员！";
                }
            }else {
                return "验证码错误，请检查后再提交!";
            }
        }else {
            return "请先获取验证码";
        }
    }

    @Override
    public String validateOnly (String email,String code,String sessionId){
//        String key = "email"  +": " +email+" "+sessionId+":true";
        String key = "email"  +": " +email+" " +":true";
        if(Boolean.TRUE.equals(template.hasKey(key))){
            String s = template.opsForValue().get(key);
            if(s == null){
                return "验证码失效，请重新获取";
            }
            if (s.equals(code)){
                template.delete(key);
                return null;
            }else {
                return "验证码错误，请检查后再提交!";
            }
        }else {
            return "请先获取验证码";
        }
    }

    @Override
    public boolean resetPassword(String password,String email){
        password = encoder.encode(password);
        return userMapper.resetPasswordByEmail(password,email) > 0;
    }
}
