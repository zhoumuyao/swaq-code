package com.example.service;

import com.example.entity.Feedback;

import java.sql.Timestamp;
import java.util.List;

public interface FeedbackService {

    List<Feedback> showAllFeedback();

    String add(int id, String feedback, int rate, String time);
}
