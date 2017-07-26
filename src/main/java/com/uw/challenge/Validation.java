package com.uw.challenge;

/**
 * This class is used to validate elements, which must be positive integers and below Integer.MAX_VALUE
 */
public class Validation {
    public static void checkPositive(int element) {
        if (element <= 0 || element >= Integer.MAX_VALUE)
            throw new IllegalArgumentException(" an element must be positive and below " + Integer.MAX_VALUE);
    }

    public static void checkElements(int... elements) {
        for (int element : elements)
            checkPositive(element);
    }

}
