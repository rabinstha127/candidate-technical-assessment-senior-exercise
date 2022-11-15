package com.rabin.moviewiki.mapper;

import com.rabin.moviewiki.dto.PersonDTO;
import com.rabin.moviewiki.entity.Person;
import com.rabin.moviewiki.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper extends BaseMapper<PersonDTO, Person> {
}
