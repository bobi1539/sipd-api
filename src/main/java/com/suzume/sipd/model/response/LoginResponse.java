package com.suzume.sipd.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    private String jwt;
    private String refreshToken;
}
