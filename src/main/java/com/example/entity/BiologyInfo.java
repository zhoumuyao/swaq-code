package com.example.entity;

import lombok.Data;

@Data
public class BiologyInfo {
    int id;
    String dangerName;
    String diseasesClass;
    String infectious;
    String transmissionRoute;
    String transmissionRange;
    String pathogenicity;
    String toxicity;
    String invasiveness;
    String fatalityRate;
    String incidenceRate;
    String activity;
    String disposal;
}
