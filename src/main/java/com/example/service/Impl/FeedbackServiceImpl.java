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
     * 根据id新增或修改反馈
     * @param feedback
     */
    @Override
    public String add(int id, String feedback, int rate, String time) {
        if (feedback == null) {
            return null;
        }
        // 使用新的插入或更新方法
        int result = mapper.insertOrUpdate(id, feedback, rate, time);
        if (result <= 0) {
            return null;
        }
        return "操作成功！"; // 这里可以根据需要修改返回的消息
    }

    @Override
    public Feedback selectById(int id) {
        if(id <= 0){
            return null;
        }
        return mapper.select(id);
    }



}
