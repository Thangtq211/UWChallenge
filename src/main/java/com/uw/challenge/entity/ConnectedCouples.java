package com.uw.challenge.entity;

import javax.persistence.*;

/**
 * This class is used to represent connected couples of the elements. The class
 * persists to the H2 database using JPA.
 */
@Entity
@Table(name = "ConnectedCouples")
public class ConnectedCouples {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "idConnectedCouple")
    private Long idConnectedCouple;

    @Column(name = "idCouple")
    private Long idCouple;

    public ConnectedCouples() {
    }

    public long getId() {
        return idConnectedCouple;
    }

    public void setId(long idConnectedCouple) {
        this.idConnectedCouple = idConnectedCouple;
    }

    public long getCoupleId() {
        return idCouple;
    }

    public void setCoupleId(long idCouple) {
        this.idCouple = idCouple;
    }

    @Override
    public String toString() {
        return "[id:" + id + ",idConnectedCouple:" + idConnectedCouple + ",idCouple:" + idCouple + "]";

    }


}
