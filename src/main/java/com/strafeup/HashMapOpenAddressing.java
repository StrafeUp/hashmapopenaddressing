package com.strafeup;

import java.util.Objects;

public class HashMapOpenAddressing {
    private static final double LOAD_FACTOR = 0.75;
    int size = 1;
    Element[] innerArray;
    private int internalCapacity = 4;

    public HashMapOpenAddressing() {
        innerArray = new Element[internalCapacity];
    }

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

    public long get(int key) {
        int pos = getOffset(key);

        if (innerArray[pos] == null) {
            throw new ElementIsNotPresentException(String.format("Element with key %d is not present in the map", key));
        }

        while (innerArray[pos] != null && innerArray[pos].key != key) {
            pos = (pos + 1) & (internalCapacity - 1);
        }

        return innerArray[pos].value;
    }

    public int size() {
        return size;
    }

    private boolean checkCapacity() {
        return (double) size / innerArray.length >= LOAD_FACTOR;
    }

    private void linearProbe(int pos, int key, long value) {
        if (innerArray[pos + 1] == null) {
            innerArray[pos + 1] = new Element(key, value);
        } else {
            linearProbe(pos + 1, key, value);
        }
    }

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

    private int getOffset(int k) {
        return Math.abs(hash(k)) & (internalCapacity - 1);
    } // Trick with getting remainder of division by power of 2

    private int hash(int k) {
        return k; // We can use plain key as result of hash function, because collision will happen only on same keys
    }

    private static class Element {
        private final int key;
        private final long value;

        public Element(int key, long value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Element)) return false;
            Element element = (Element) o;
            return key == element.key &&
                    value == element.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
