package com.rabin.moviewiki.constants;

public final class APIUrlConstant {
    private APIUrlConstant() {
    }

    public static final String BASE_API_V1 = "api/v1";
    public static final String PERSONS = "/persons";
    public static final String ID = "/{id}";
    public static final String PERSONS_FULL = BASE_API_V1 + PERSONS;

}
