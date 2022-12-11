package com.fh.vs.gruppe1.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

}
