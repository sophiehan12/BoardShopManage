package com.example.middleproject.persistence;

import com.example.middleproject.model.MyshopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyshopRepository extends JpaRepository<MyshopEntity, String> {
    //@Query("select * from Todo t where t.userId=?1")
    List<MyshopEntity> findByUserId(String userId);

    List<MyshopEntity> findByTitle(String title);

    List<MyshopEntity> findAll();

}
