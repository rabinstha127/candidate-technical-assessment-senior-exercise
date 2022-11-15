package com.rabin.moviewiki.mapper.base;

import java.util.List;

public interface BaseMapper<D, E> {
    D toDTO(E entity);

    E toEntity(D dto);

    List<D> toDTOs(List<E> entities);

    List<E> toEntities(List<D> dtos);
}
