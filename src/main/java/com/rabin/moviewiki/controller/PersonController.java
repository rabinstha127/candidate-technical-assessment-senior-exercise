package com.rabin.moviewiki.controller;

import com.rabin.moviewiki.dto.PersonDTO;
import com.rabin.moviewiki.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rabin.moviewiki.constants.APIUrlConstant.PERSONS_FULL;
import static com.rabin.moviewiki.constants.APIUrlConstant.ID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = PERSONS_FULL)
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> searchBy(@RequestParam String criteria) {
       return ResponseEntity.ok(personService.searchBy(criteria));
    }

    @GetMapping(ID)
    public ResponseEntity<PersonDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(personService.findById(Long.parseLong(id)));
    }
}
