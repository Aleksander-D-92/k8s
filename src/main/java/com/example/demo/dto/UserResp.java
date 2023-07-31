package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResp {
    private String email;
    private String password;
}
