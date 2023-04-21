package com.ytbot.website.mapper;


import com.ytbot.website.dto.GenericDTO;
import com.ytbot.website.model.GenericModel;
import org.springframework.stereotype.Component;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {

    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(List<D> dtoList);

    List<D> toDTOs(List<E> entitiesList);

}
