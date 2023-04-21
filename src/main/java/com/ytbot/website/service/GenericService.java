package com.ytbot.website.service;

import com.ytbot.website.dto.GenericDTO;
import com.ytbot.website.mapper.GenericMapper;
import com.ytbot.website.model.GenericModel;
import com.ytbot.website.repository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public abstract class GenericService<T extends GenericModel, N extends GenericDTO> {
    protected final GenericRepository<T> repository;
    protected final GenericMapper<T, N> mapper;

    public GenericService(GenericRepository<T> repository, GenericMapper<T, N> mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<N> listAll(){
        return mapper.toDTOs(repository.findAll());
    }

    public Page<N> listAll(Pageable pageable){
        Page<T> objects = repository.findAll(pageable);
        List<N> result = mapper.toDTOs(objects.getContent());
        return new PageImpl<>(result, pageable, objects.getTotalElements());
    }

    public N getOne(final Long id){
        return mapper.toDTO(repository.findById(id).orElseThrow(() -> new NotFoundException("Данных не найдено для id = " + id)));
    }

    public N create(N newObject){
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }

    public N update(N updatedObject){
        return mapper.toDTO(repository.save(mapper.toEntity(updatedObject)));
    }

    public void delete(final long id){
        repository.deleteById(id);
    }
}
