package com.suzume.sipd.model.response;

import com.suzume.sipd.entity.MCity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CityResponse {
    private Long id;
    private String name;

    public static CityResponse build(MCity city) {
        if (city == null) return null;
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }
}
