package com.strafeup;

/**
 * HashMap implementation based on open addressing collision resolution mechanism
 * which is based on probing, or searching through alternate locations in the array
 * (the probe sequence) until either the target record is found.
 * In this example only linear probing with step equal to 1 is implemented
 */
public class HashMapOpenAddressing {
    /**
     * Step is used in linear probing and determines the number of elements to skip when searching for key
     */
    public static final int STEP = 1;

    /**
     * Load factor determines the percentage of map fullness, when it needs to be resized
     */
    private static final double LOAD_FACTOR = 0.75;

    /**
     * Initial size of map is 1
     */
    private int size = 1;

    /**
     * Inner array to hold the elements which are contained inside custom Element class which contains pair of Key Value
     */
    private Element[] innerArray;

    /**
     * Internal capacity is always is a power of two number, starting from 16
     */
    private int internalCapacity = 1 << 4;

    /**
     * Basic constructor which initialises the internal array for a fixed number of elements
     */
    public HashMapOpenAddressing() {
        innerArray = new Element[internalCapacity];
    }

    /**
     * Implementing the put method, which handles check for capacity, and determining position of new element in inner
     * array
     *
     * @param key   key of pair
     * @param value value of pair to put in array
     */
    public void put(int key, long value) {
        if (checkCapacity()) {
            resize();
        }
        size++;
        int pos = getOffset(key);
        if (innerArray[pos] == null) {
            innerArray[pos] = new Element(key, value);
        } else {
            linearProbe(pos, key, value);
        }
    }

    /**
     * Implementing the get method, which looks through the array and returns the element by key if it is present
     *
     * @param key key of an pair we need to retrieve
     * @return Returns long value of an element, if it is present in the inner array, or throws a custom runtime exception
     * which says what element is not present
     */
    public long get(int key) {
        int pos = getOffset(key);

        if (innerArray[pos] == null) {
            throw new ElementIsNotPresentException(String.format("Element with key %d is not present in the map", key));
        }

        while (innerArray[pos] != null && innerArray[pos].key != key) {
            pos = (pos + STEP) & (internalCapacity - 1);
        }

        return innerArray[pos].value;
    }

    /**
     * Returns the size of an inner array.
     *
     * @return Returns the integer number of an elements in the inner array
     */
    public int size() {
        return size - 1; // The method returns value -1, because the initial count starts from 1
    }

    /**
     * @return Returns true, if the array is over LOAD_FACTOR percent full
     */
    private boolean checkCapacity() {
        return (double) size / innerArray.length >= LOAD_FACTOR;
    }

    /**
     * Linear probe method uses the STEP constant to search for the empty cell in array. Basic STEP is 1, so the
     * recursive search hops onto the next cell, if current is occupied.
     *
     * @param pos   current position in the inner array
     * @param key   key of an element we need to save
     * @param value value of an element we need to save
     */
    private void linearProbe(int pos, int key, long value) {
        if (innerArray[pos + STEP] == null) {
            innerArray[pos + STEP] = new Element(key, value);
        } else {
            linearProbe(pos + STEP, key, value);
        }
    }

    /**
     * Resize method is launched after the checkCapacity method returned true and the inner array needs to be enlarged.
     */
    private void resize() {
        int elemCount = 0;
        int newCapacity = innerArray.length << 1; // Incrementing capacity by power of 2
        internalCapacity = newCapacity;

        Element[] tempArr = innerArray;
        innerArray = new Element[newCapacity];

        for (Element elem : tempArr) {
            if (elem != null) {
                put(elem.key, elem.value);
                elemCount++;
            }
        }
        this.size = elemCount;

    }

    /**
     * Method returns basic offset of element in the inner array. Implementation based on getting the remainder of
     * division by a power of 2, so it is made through bitwise operator
     *
     * @param k key of an element
     * @return returns offset
     */
    private int getOffset(int k) {
        return Math.abs(hash(k)) & (internalCapacity - 1);
    } // Trick with getting remainder of division by power of 2, which is faster than modulo operator

    /**
     * Method to find result of hash function to calculate uniqueness of an element.
     * In the implementation based on integer keys we can simply return value of our key, collisions will happen only
     * if the key is the same integer number.
     *
     * @param k key of an element
     * @return returns an integer value of hash function
     */
    private int hash(int k) {
        return k;
    }

    /**
     * The custom data node object, which represents an element in the array consisting of pair of integer
     * and long numbers
     */
    private static class Element {
        private final int key;
        private final long value;

        /**
         * Default constructor for element node
         *
         * @param key
         * @param value
         */
        public Element(int key, long value) {
            this.key = key;
            this.value = value;
        }
    }
}
