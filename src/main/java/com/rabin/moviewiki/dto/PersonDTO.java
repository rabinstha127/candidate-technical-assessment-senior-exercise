package com.rabin.moviewiki.dto;

import com.rabin.moviewiki.dto.base.Identity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class PersonDTO extends Identity<Long> {

    private String firstName;

    private String middleName;

    private String lastName;

    private List<PersonMovieDTO> personMovies;
}
