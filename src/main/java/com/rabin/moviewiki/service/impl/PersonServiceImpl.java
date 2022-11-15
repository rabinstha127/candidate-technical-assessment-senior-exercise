package com.rabin.moviewiki.service.impl;

import com.rabin.moviewiki.dto.PersonDTO;
import com.rabin.moviewiki.exceptions.NoDataException;
import com.rabin.moviewiki.mapper.PersonMapper;
import com.rabin.moviewiki.repository.PersonRepository;
import com.rabin.moviewiki.service.PersonService;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.rabin.moviewiki.constants.ErrorMessageConstants.NO_SUCH_PERSON;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Autowired
    public PersonServiceImpl(final PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.personMapper = Mappers.getMapper(PersonMapper.class);
    }

    public PersonServiceImpl(final PersonRepository personRepository, final PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public List<PersonDTO> searchBy(String criteria) {
        return personMapper.toDTOs(personRepository.searchBy(criteria.replaceAll("\\s", "")));
    }

    @Override
    public PersonDTO findById(long id) {
        return personMapper.toDTO(personRepository.findById(id).orElseThrow(this::noSuchPerson));
    }

    private NoDataException noSuchPerson() {
        return new NoDataException(NO_SUCH_PERSON);
    }

}
