package com.example.service.Impl;

import com.example.entity.Feedback;
import com.example.service.FeedbackService;
import org.springframework.stereotype.Service;
import com.example.mapper.FeedBackMapper;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private FeedBackMapper mapper;

    @Override
    public List<Feedback> showAllFeedback() {
        return mapper.list();
    }

    /**
     * 新增部门
     * @param feedback
     */
    @Override
    public String add(String feedback, int rate, String time) {
        if(feedback == null){
            return null;
        }
        if(mapper.insert(feedback, rate, time)<=0){
            return null;
        }
        return "新增反馈成功！";
    }

}
