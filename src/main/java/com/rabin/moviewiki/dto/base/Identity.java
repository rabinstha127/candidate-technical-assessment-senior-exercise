package com.rabin.moviewiki.dto.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
@Data
public class Identity<T extends Serializable> {
    private T id;
}
