package com.rabin.moviewiki.controller;

import com.rabin.moviewiki.dto.MovieDTO;
import com.rabin.moviewiki.dto.PersonDTO;
import com.rabin.moviewiki.dto.PersonMovieDTO;
import com.rabin.moviewiki.entity.Movie;
import com.rabin.moviewiki.entity.Person;
import com.rabin.moviewiki.entity.PersonMovie;
import com.rabin.moviewiki.exceptions.NoDataException;
import com.rabin.moviewiki.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    private PersonService personService;

    private PersonController personController;
    private PersonDTO mockPersonDTO;
    private MovieDTO mockMovieDTO;
    private PersonMovieDTO mockPersonMovieDTO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        personController = new PersonController(personService);
        initMockDTOs();
    }

    private void initMockDTOs() {
        mockMovieDTO = MovieDTO.builder().title("test").build();
        mockPersonDTO = PersonDTO.builder()
                .firstName("John")
                .middleName("Michael")
                .lastName("Doe")
                .build();
        mockPersonMovieDTO = PersonMovieDTO.builder().movie(mockMovieDTO).build();
        mockPersonDTO.setPersonMovies(List.of(mockPersonMovieDTO));
    }

    @Test
    public void searchBy_emptyList_success() {
        Mockito.doReturn(Collections.emptyList()).when(personService).searchBy(ArgumentMatchers.anyString());
        var data = personController.searchBy(ArgumentMatchers.anyString());
        Assertions.assertEquals(Objects.requireNonNull(data.getBody()).size(), 0);
    }

    @Test
    public void searchBy_withData_success() {
        Mockito.doReturn(List.of(mockPersonDTO)).when(personService).searchBy(ArgumentMatchers.anyString());
        var data = personController.searchBy(ArgumentMatchers.anyString());
        Assertions.assertEquals(Objects.requireNonNull(data.getBody()).size(), 1);
    }

    @Test
    public void findById_nullId_throwsException() {
        Assertions.assertThrows(NumberFormatException.class, () -> personController.findById(null));
    }

    @Test
    public void searchBy_stringId_throwsException() {
        Assertions.assertThrows(NumberFormatException.class, () -> personController.findById("1A"));
    }

    @Test
    public void searchBy_validId_success() {
        Mockito.doReturn(mockPersonDTO).when(personService).findById(ArgumentMatchers.anyLong());
        var data = personController.findById("12");
        Assertions.assertNotNull(data);
    }
}