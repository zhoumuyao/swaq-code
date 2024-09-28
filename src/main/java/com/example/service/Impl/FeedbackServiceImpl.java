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
     * 新增反馈
     * @param feedback
     */
    @Override
    public String add(int id, String feedback, int rate, String time) {
        if(feedback == null){
            return null;
        }
        if(mapper.insert(id, feedback, rate, time)<=0){
            return null;
        }
        return "新增反馈成功！";
    }

    @Override
    public Feedback selectById(int id) {
        if(id <= 0){
            return null;
        }
        return mapper.select(id);
    }



}
