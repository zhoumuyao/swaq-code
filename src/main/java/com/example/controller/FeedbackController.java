package com.example.controller;

import com.example.service.FeedbackService;
import com.example.entity.Feedback;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Resource
    FeedbackService service;

    @GetMapping("/showAll")
    public List<Feedback> showAllFeedback(){
        System.out.println("查询所有反馈结果");

        return service.showAllFeedback();
    }

    @PostMapping("/addFeedback")
    public String addFeedback(@RequestBody Feedback request){
        System.out.println("新增了一条反馈");
//        System.out.println(feedback);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // 创建 SimpleDateFormat 对象，并设置所需的日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化 Timestamp 对象
        String formattedDate = sdf.format(timestamp);
        System.out.println(formattedDate);

        return service.add(request.getId(), request.getFeedback(), request.getRate(), formattedDate);
    }

    @PostMapping("/feedbackById")
    public Feedback feedbackById(@RequestBody Feedback request){
        return service.selectById(request.getId());
    }

}
