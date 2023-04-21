package com.ytbot.website.mapper;

import com.ytbot.website.dto.GenericDTO;
import com.ytbot.website.model.GenericModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public abstract class GenericMapper<E extends GenericModel, D extends GenericDTO> implements Mapper<E, D> {
    protected final ModelMapper modelMapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    protected GenericMapper(ModelMapper modelMapper, Class<E> entityClass, Class<D> dtoClass) {
        this.modelMapper = modelMapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }
//    protected GenericMapper(ModelMapper modelMapperFirst, ModelMapper modelMapperSecond, Class<E> entityClass, Class<D> dtoClass) {
//        this.modelMapperFirst = modelMapperFirst;
//        this.modelMapperSecond = modelMapperSecond;
//        this.entityClass = entityClass;
//        this.dtoClass = dtoClass;
//    }

    @Override
    public E toEntity(D dto) {
            return Objects.isNull(dto)
                    ? null
                    : modelMapper.map(dto, entityClass);
    }

    @Override
    public D toDTO(E entity) {
        return Objects.isNull(entity)
                ? null
                : modelMapper.map(entity, dtoClass);
    }

    @Override
    public List<E> toEntities(List<D> dtoList) {
        return dtoList.stream().map(this::toEntity).toList();
    }

    @Override
    public List<D> toDTOs(List<E> entitiesList) {
        return entitiesList.stream().map(this::toDTO).toList();
    }

    public Converter<D, E> toEntityConverter(){
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<E, D> toDTOConverter(){
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public abstract void mapSpecificFields(D source, E destination);
    public abstract void mapSpecificFields(E source, D destination);

}
