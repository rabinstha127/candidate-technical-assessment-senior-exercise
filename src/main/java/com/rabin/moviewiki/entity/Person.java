package com.rabin.moviewiki.entity;

import com.rabin.moviewiki.entity.base.Identity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
public class Person extends Identity<Long> {

    @Column(nullable = false)
    private String firstName;

    @Column
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "person")
    private List<PersonMovie> personMovies;

}
