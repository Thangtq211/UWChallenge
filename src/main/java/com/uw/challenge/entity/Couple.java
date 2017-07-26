package com.uw.challenge.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is used to represent a couple. A couple is represented for two connected elements
 * , either directly or through a series of connections.
 */
@Entity
@Table(name = "Couple")
public class Couple implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "idCouple")
    private Long idCouple;

    @Column(name = "firstElement")
    private int firstElement;

    @Column(name = "secondElement")
    private int secondElement;

    public Couple() {

    }

    public Couple(int firstElement, int secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public long getId() {
        return this.idCouple;
    }

    public boolean hasElement(int element) {
        return this.firstElement == element || this.secondElement == element;
    }

    public boolean isMatch(Couple couple) {
        return (this.firstElement == couple.getFirstElement() || this.firstElement == couple.getSecondElement())
                && (this.secondElement == couple.getSecondElement() || this.secondElement == couple.getFirstElement());
    }

    public int getCouple(int element) {
        if (this.firstElement == element) return this.secondElement;
        else if (this.secondElement == element) return this.firstElement;
        else throw new IllegalArgumentException("Can't get a couple with wrong input element:" + element);
    }

    public int getFirstElement() {
        return firstElement;
    }

    public void setFirstElement(int firstElement) {
        this.firstElement = firstElement;
    }

    public int getSecondElement() {
        return secondElement;
    }

    public void setSecondElement(int secondElement) {
        this.secondElement = secondElement;
    }

    public ArrayList getElements() {
        ArrayList arrayList = new ArrayList<Integer>(2);
        arrayList.add(firstElement);
        arrayList.add(secondElement);
        return arrayList;
    }

    @Override
    public String toString() {
        return "[" + firstElement + "," + secondElement + "]";
    }
}
