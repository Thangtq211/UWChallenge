package com.uw.challenge.entity;

import javax.persistence.*;

/**
 * Created by ThangTQ on 7/26/2017.
 */
@Entity
@Table(name = "Element")
public class Element {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private int value;

    public Element() {

    }

    public Element(int value) {
        this.value = value;
    }

}
