package com.rabin.moviewiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class PersonMovieKey implements Serializable {
    @Column(nullable = false)
    private long personId;

    @Column(nullable = false)
    private long movieId;
}
