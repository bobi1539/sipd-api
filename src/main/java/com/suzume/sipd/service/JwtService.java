package com.suzume.sipd.service;

import com.suzume.sipd.model.dto.JwtComponent;

public interface JwtService {

    String generateToken(JwtComponent jwtComponent);

    JwtComponent extractToken(String token);

}
