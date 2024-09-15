package com.example.mapper;

import com.example.entity.auth.Account;
import com.example.entity.user.AccountUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from db_account where username = #{text} or email = #{text} or police_id = #{text}")
    Account findAccountByNameOrEmailOrPoliceId(String text);
    @Insert("insert into db_account (username,police_id,police_station,password,email)values (#{username},#{policeId},#{policeStation},#{password},#{email})")
    int createAccount(String username,String policeId, String policeStation,String password,String email);
    @Update("update db_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password,String email);
    @Select("select * from db_account where username = #{text} or email = #{text}")
    AccountUser findAccountUserByNameOrEmail(String text);
}
