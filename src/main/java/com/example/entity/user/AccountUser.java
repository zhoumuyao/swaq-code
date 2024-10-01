package com.example.entity.user;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;


@Data
public class AccountUser {
    int id;
    int policeId;
    String policeStation;
    String username;
    String email;
    int isLogin;
    int isDelete;

}
