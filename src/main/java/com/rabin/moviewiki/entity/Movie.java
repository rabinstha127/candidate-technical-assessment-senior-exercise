package com.rabin.moviewiki.entity;

import com.rabin.moviewiki.entity.base.Identity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
public class Movie extends Identity<Long> {
    private String title;

    @OneToMany(mappedBy = "movie")
    private List<PersonMovie> personMovies;
}
