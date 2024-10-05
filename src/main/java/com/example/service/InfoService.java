package com.example.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface InfoService {

    String createInfo(int id,
                      MultipartFile file1,
                      MultipartFile file2,
                      MultipartFile file3,
                      MultipartFile file4,
                      MultipartFile file5,
                      MultipartFile file6,
                      MultipartFile file7,
                      MultipartFile file8,
                      MultipartFile file9,
                      MultipartFile file10,
                      MultipartFile file11,
                      MultipartFile file12,
                      MultipartFile file13,
                      MultipartFile file14,
                      MultipartFile file15,
                      MultipartFile file16,
                      MultipartFile file17,
                      MultipartFile file18,
                      MultipartFile file19
                      );

    Map<Integer,String> queryInfo(int id);
}
