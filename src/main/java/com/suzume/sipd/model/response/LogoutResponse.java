package com.suzume.sipd.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LogoutResponse {
    private String refreshToken;
}
