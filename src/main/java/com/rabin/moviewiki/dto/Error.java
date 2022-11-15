package com.rabin.moviewiki.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    private String field;

    private Object rejectedValue;

    private Set<HttpMessageResponse> errorMessages;
}
