package com.example.entity;

import lombok.Data;

@Data
public class DisposalObject {
    Integer disposalId;
    Integer id;
    String objectClass;
    String sampleType;
    String sampleContent;
    String testMethod;
    String result;
    String sampleRequirement;
}
