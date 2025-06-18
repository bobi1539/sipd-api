package com.suzume.sipd.model.request;

import com.suzume.sipd.constant.Constant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshTokenRequest {

    @NotNull(message = Constant.REFRESH_TOKEN_REQUIRED)
    @NotBlank(message = Constant.REFRESH_TOKEN_REQUIRED)
    private String refreshToken;
}
