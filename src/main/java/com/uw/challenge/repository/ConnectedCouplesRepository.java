package com.uw.challenge.repository;

import com.uw.challenge.entity.ConnectedCouples;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The interface for CRUD:Create, Read, Update, Delete ConnectedCouples objects
 */
public interface ConnectedCouplesRepository extends CrudRepository<ConnectedCouples, Long> {
    List<ConnectedCouples> findByIdConnectedCouple(long idConnectedCouple);

}
