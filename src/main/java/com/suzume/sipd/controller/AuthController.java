package com.suzume.sipd.controller;

import com.suzume.sipd.constant.Endpoint;
import com.suzume.sipd.model.request.LoginRequest;
import com.suzume.sipd.model.request.RefreshTokenRequest;
import com.suzume.sipd.model.response.LoginResponse;
import com.suzume.sipd.model.response.LogoutResponse;
import com.suzume.sipd.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.AUTH)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh-token")
    public LoginResponse loginWithRefreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return authService.loginWithRefreshToken(request);
    }

    @PostMapping("/logout")
    public LogoutResponse logout(@RequestBody @Valid RefreshTokenRequest request) {
        return authService.logout(request);
    }
}
