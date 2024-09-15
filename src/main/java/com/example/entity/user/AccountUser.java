package com.example.entity.user;

import lombok.Data;

@Data
public class AccountUser {
    int id;
    int policeId;
    String policeStation;
    String username;
    String email;
}
