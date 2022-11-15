package com.rabin.moviewiki.service;

import com.rabin.moviewiki.dto.PersonDTO;

import java.util.List;

public interface PersonService {
    PersonDTO findById(long id);
    List<PersonDTO> searchBy(String criteria);
}
