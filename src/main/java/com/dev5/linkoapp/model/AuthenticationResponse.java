package com.dev5.linkoapp.model;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

}
