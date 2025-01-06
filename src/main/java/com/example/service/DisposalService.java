package com.example.service;

import com.example.entity.DisposalObject;

import java.util.List;

public interface DisposalService {
    Integer addDisposal(DisposalObject disposalObject);

    List<DisposalObject> searchDisposal(Integer id);

    String deleteDisposal(Integer disposalId);
}
