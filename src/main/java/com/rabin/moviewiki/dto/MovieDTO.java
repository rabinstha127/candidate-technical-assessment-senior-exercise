package com.rabin.moviewiki.dto;

import com.rabin.moviewiki.dto.base.Identity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
public class MovieDTO extends Identity<Long> {
    private String title;
}
