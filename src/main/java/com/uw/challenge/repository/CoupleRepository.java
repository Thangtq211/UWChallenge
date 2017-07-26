package com.uw.challenge.repository;

import com.uw.challenge.entity.Couple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

/**
 * The interface for CRUD:Create, Read, Update, Delete couple objects
 */
public interface CoupleRepository extends CrudRepository<Couple, Long> {
    @Query("select c from Couple c where c.firstElement = :firstElement and c.secondElement= :secondElement")
    Stream<Couple> findByElements(@Param("firstElement") int firstElement, @Param("secondElement") int secondElement);
}
