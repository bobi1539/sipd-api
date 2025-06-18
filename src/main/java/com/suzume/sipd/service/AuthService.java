package com.suzume.sipd.service;

import com.suzume.sipd.model.request.LoginRequest;
import com.suzume.sipd.model.request.RefreshTokenRequest;
import com.suzume.sipd.model.response.LoginResponse;
import com.suzume.sipd.model.response.LogoutResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse loginWithRefreshToken(RefreshTokenRequest request);

    LogoutResponse logout(RefreshTokenRequest request);

}
