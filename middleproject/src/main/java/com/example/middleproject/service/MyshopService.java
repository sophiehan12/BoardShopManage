package com.example.middleproject.service;

import com.example.middleproject.model.MyshopEntity;
import com.example.middleproject.persistence.MyshopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MyshopService {

    @Autowired
    private MyshopRepository repository;


    public List<MyshopEntity> create(final MyshopEntity entity){
        validate(entity);

        repository.save(entity);

        log.info("Entity Id : {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());

    }

    public List<MyshopEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }

    public List<MyshopEntity> retrieve2(){
        return repository.findAll();
    }

    public List<MyshopEntity> update(final MyshopEntity entity){
        validate(entity);

        MyshopEntity a = repository.getById(entity.getId());

        final Optional<MyshopEntity> original = repository.findById(entity.getId());
        original.ifPresent(myshop ->{
            myshop.setTitle(entity.getTitle());
            myshop.setType(entity.getType());
            myshop.setLength(entity.getLength());

            repository.save(myshop);
        });

        //return retrieve(entity.getUserId());
        //return retrieve(entity.getId());
        return Collections.singletonList(repository.getById(entity.getId()));


    }

    public List<MyshopEntity> delete(final MyshopEntity entity){
        validate(entity);

        try{
            repository.delete(entity);
        }catch (Exception e){
            log.error("error deleting entity", entity.getId(), e);

            throw new RuntimeException("error deleting entity" + entity.getId());
        }
        return retrieve(entity.getUserId());
        //return repository.findByUserId(entity.getUserId());
    }

    //search
    public List<MyshopEntity> search(final String title){
        //validate(entity);
        return repository.findByTitle(title);

        //return retrieve(entity.getUserId());
    }



    private void validate(final MyshopEntity entity){
        if(entity == null){
            log.warn("Entity cannot be null.");
            throw new RuntimeException(("Entity cannot be null."));
        }if(entity.getUserId() == null){
            log.warn("Entity cannot be null.");
            throw new RuntimeException(("Entity cannot be null."));
        }
    }
}
