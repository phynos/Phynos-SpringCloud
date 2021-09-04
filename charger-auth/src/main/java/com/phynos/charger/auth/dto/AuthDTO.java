package com.phynos.charger.auth.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author by lupc
 * @date 2020-08-27 15:12
 */
@Data
public class AuthDTO implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    //default constructor for JSON Parsing
    public AuthDTO()
    {
    }

    public AuthDTO(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

}