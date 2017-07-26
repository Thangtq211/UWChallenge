package com.uw.challenge.entity;

import javax.persistence.*;

/**
 * This class is used to represent group connected couples. The class persists to the H2 database using JPA.
 */
@Entity
@Table(name = "GroupConnectedCouples")
public class GroupConnectedCouples {
    @Id
    @GeneratedValue
    @Column(name = "idConnectedCouple")
    private Long idConnectedCouple;


    public long getId() {
        return this.idConnectedCouple;
    }

    @Override
    public String toString() {
        return "[idGroupConnectedCouple:" + idConnectedCouple + "]";

    }

}
