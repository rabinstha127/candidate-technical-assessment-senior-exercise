package com.rabin.moviewiki.service.impl;

import com.rabin.moviewiki.dto.MovieDTO;
import com.rabin.moviewiki.dto.PersonDTO;
import com.rabin.moviewiki.dto.PersonMovieDTO;
import com.rabin.moviewiki.entity.Movie;
import com.rabin.moviewiki.entity.Person;
import com.rabin.moviewiki.entity.PersonMovie;
import com.rabin.moviewiki.exceptions.NoDataException;
import com.rabin.moviewiki.mapper.PersonMapper;
import com.rabin.moviewiki.repository.PersonRepository;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    private PersonService personService;

    private Person mockPerson;
    private PersonDTO mockPersonDTO;
    private Movie mockMovie;
    private MovieDTO mockMovieDTO;
    private PersonMovie mockPersonMovie;
    private PersonMovieDTO mockPersonMovieDTO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        personService = new PersonServiceImpl(personRepository, personMapper);
        initMockEntities();
        initMockDTOs();
    }

    private void initMockEntities() {
        mockMovie = Movie.builder().title("test").build();
        mockPerson = Person.builder()
                .firstName("John")
                .middleName("Michael")
                .lastName("Doe")
                .build();
        mockPersonMovie = PersonMovie.builder().person(mockPerson).movie(mockMovie).build();
        mockMovie.setPersonMovies(List.of(mockPersonMovie));
        mockPerson.setPersonMovies(List.of(mockPersonMovie));
    }

    private void initMockDTOs() {
        mockMovieDTO = MovieDTO.builder().title(mockMovie.getTitle()).build();
        mockPersonDTO = PersonDTO.builder()
                .firstName(mockPerson.getFirstName())
                .middleName(mockPerson.getMiddleName())
                .lastName(mockPerson.getLastName())
                .build();
        mockPersonMovieDTO = PersonMovieDTO.builder().movie(mockMovieDTO).build();
        mockPersonDTO.setPersonMovies(List.of(mockPersonMovieDTO));
    }

    @Test
    public void searchBy_validCriteria_success() {
        Mockito.doReturn(List.of(mockPerson)).when(personRepository).searchBy(ArgumentMatchers.anyString());
        Mockito.doReturn(List.of(mockPersonDTO)).when(personMapper).toDTOs(List.of(mockPerson));
        var persons = personService.searchBy(ArgumentMatchers.anyString());
        Mockito.verify(personRepository, Mockito.times(1)).searchBy(ArgumentMatchers.anyString());
        Assertions.assertEquals(persons.size(), 1);
    }

    @Test
    public void searchBy_emptyData_success() {
        Mockito.doReturn(Collections.emptyList()).when(personRepository).searchBy(ArgumentMatchers.anyString());
        Mockito.doReturn(Collections.emptyList()).when(personMapper).toDTOs(Collections.emptyList());
        var persons = personService.searchBy(ArgumentMatchers.anyString());
        Mockito.verify(personRepository, Mockito.times(1)).searchBy(ArgumentMatchers.anyString());
        Assertions.assertEquals(persons.size(), 0);
    }

    @Test
    public void findById_validData_success() {
        Mockito.doReturn(Optional.of(mockPerson)).when(personRepository).findById(ArgumentMatchers.anyLong());
        Mockito.doReturn(mockPersonDTO).when(personMapper).toDTO(mockPerson);
        var person = personService.findById(ArgumentMatchers.anyLong());
        Mockito.verify(personRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Assertions.assertNotNull(person);
    }

    @Test
    public void findById_invalidData_throwException() {
        Mockito.doThrow(new NoDataException("invalid")).when(personRepository).findById(ArgumentMatchers.anyLong());
        NoDataException exception = Assertions.assertThrows(NoDataException.class, () -> personService.findById(ArgumentMatchers.anyLong()));
        Assertions.assertEquals("invalid", exception.getMessage());
    }
}