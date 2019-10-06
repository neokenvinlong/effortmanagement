package com.restful.api.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {


    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private String role,name;

    public JwtResponse(String jwttoken, String role, String name) {
        this.jwttoken = jwttoken;
        this.role = role;
        this.name = name;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
