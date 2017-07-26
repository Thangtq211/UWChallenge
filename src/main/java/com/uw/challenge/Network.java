package com.uw.challenge;

import com.uw.challenge.entity.Couple;

/**
 * This class is used to represent a network interface of the elements. The class
 * provides methods to add, connect, query connections about the elements.
 */
public interface Network {
    void add(int element);

    void connect(int firstElement, int secondElement);

    boolean query(int firstElement, int secondElement);

    void validateElements(int firstElement, int secondElement);

    boolean hasCouple(Couple couple);

    @Override
    String toString();
}

