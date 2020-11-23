package com.phynos.charger.auth.pojo;

import java.io.Serializable;

/**
 * @author by lupc
 * @date 2020-08-27 15:13
 */
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

}