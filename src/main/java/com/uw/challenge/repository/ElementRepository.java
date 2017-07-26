package com.uw.challenge.repository;

import com.uw.challenge.entity.Element;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * The interface for CRUD:Create, Read, Update, Delete elements
 */
public interface ElementRepository extends CrudRepository<Element, Long> {
    List<Element> findByValue(int value);
}
