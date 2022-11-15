package com.rabin.moviewiki.repository;

import com.rabin.moviewiki.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(Queries.PERSON_LIST)
    List<Person> searchBy(String criteria);

    final class Queries {

        public static final String PERSON_LIST = "select t from Person t where " +
                "concat(t.firstName, coalesce(t.middleName, '') ,t.lastName) like %:criteria%";

        private Queries() {
        }
    }
}
