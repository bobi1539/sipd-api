package com.suzume.sipd.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Header {
    private Long userId;
    private String userFullName;
}
