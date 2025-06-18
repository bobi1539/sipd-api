package com.suzume.sipd.service.impl;

import com.suzume.sipd.constant.GlobalMessage;
import com.suzume.sipd.entity.LogAuth;
import com.suzume.sipd.entity.MUser;
import com.suzume.sipd.exception.BusinessException;
import com.suzume.sipd.helper.StringHelper;
import com.suzume.sipd.model.dto.JwtComponent;
import com.suzume.sipd.model.request.LoginRequest;
import com.suzume.sipd.model.request.RefreshTokenRequest;
import com.suzume.sipd.model.response.LoginResponse;
import com.suzume.sipd.model.response.LogoutResponse;
import com.suzume.sipd.repository.LogAuthRepository;
import com.suzume.sipd.repository.UserRepository;
import com.suzume.sipd.service.AuthService;
import com.suzume.sipd.service.JwtService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final LogAuthRepository logAuthRepository;
    private static final long REFRESH_TOKEN_VALIDITY_IN_MONTH = 1L;

    @Transactional
    @Override
    public LoginResponse login(LoginRequest request) {
        MUser user = getUserByEmail(request.getEmail());
        verifyPassword(request.getPassword(), user.getPassword());

        String jwt = generateToken(user);
        String refreshToken = saveLogAuth(user);
        return buildLoginResponse(jwt, refreshToken);
    }

    @Override
    public LoginResponse loginWithRefreshToken(RefreshTokenRequest request) {
        LogAuth logAuth = findLogAuthByRefreshToken(request.getRefreshToken());
        String jwt = generateToken(logAuth.getUser());
        return buildLoginResponse(jwt, logAuth.getRefreshToken());

    }

    @Override
    public LogoutResponse logout(RefreshTokenRequest request) {
        LogAuth logAuth = findLogAuthByRefreshToken(request.getRefreshToken());
        logAuthRepository.delete(logAuth);
        return LogoutResponse.builder().refreshToken(logAuth.getRefreshToken()).build();
    }

    private MUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(GlobalMessage.WRONG_EMAIL_OR_PASSWORD));
    }

    private void verifyPassword(String rawPassword, String hashPassword) {
        boolean matches = passwordEncoder.matches(rawPassword, hashPassword);
        if (!matches) {
            throw new BusinessException(GlobalMessage.WRONG_EMAIL_OR_PASSWORD);
        }
    }

    private String generateToken(MUser user) {
        JwtComponent jwtComponent = JwtComponent.builder()
                .userId(user.getId().toString())
                .userEmail(user.getEmail())
                .userFullName(user.getFullName())
                .build();
        return jwtService.generateToken(jwtComponent);
    }

    private String saveLogAuth(MUser user) {
        LogAuth logAuth = LogAuth.builder()
                .refreshToken(generateRefreshToken())
                .refreshTokenExpiry(LocalDate.now().plusMonths(REFRESH_TOKEN_VALIDITY_IN_MONTH))
                .user(user)
                .build();
        logAuthRepository.save(logAuth);
        return logAuth.getRefreshToken();
    }

    private LoginResponse buildLoginResponse(String jwt, String refreshToken) {
        return LoginResponse.builder()
                .jwt(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    private LogAuth findLogAuthByRefreshToken(String refreshToken) {
        return logAuthRepository.findByRefreshTokenAndRefreshTokenExpiryAfter(refreshToken, LocalDate.now())
                .orElseThrow(() -> new BusinessException(GlobalMessage.REFRESH_TOKEN_NOT_VALID));
    }

    private String generateRefreshToken() {
        return String.format("%s%d", StringHelper.random(20), System.currentTimeMillis());
    }
}
