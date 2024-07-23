package com.example.service;

import com.example.entity.DisposalObject;

import java.util.List;

public interface DisposalService {
    String addDisposal(DisposalObject disposalObject);

    List<DisposalObject> searchDisposal(Integer id);
}
