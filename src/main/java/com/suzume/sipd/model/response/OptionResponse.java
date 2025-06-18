package com.suzume.sipd.model.response;

import com.suzume.sipd.constant.enums.LabeledEnum;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OptionResponse {
    private String value;
    private String label;

    public static <E extends Enum<E> & LabeledEnum> List<OptionResponse> toOptions(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(e -> OptionResponse.builder()
                        .value(e.name())
                        .label(e.getLabel())
                        .build())
                .toList();
    }
}
