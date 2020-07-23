package com.strafeup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HashMapOpenAddressingTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private HashMapOpenAddressing intToLongMap;

    @Before
    public void init() {
        intToLongMap = new HashMapOpenAddressing();
    }

    @Test
    public void shouldAddElementToMap() {
        intToLongMap.put(5, 100);
        int size = intToLongMap.size();

        Assert.assertEquals(1, size);
    }

    @Test
    public void shouldGetElementFromMap() {
        intToLongMap.put(5, 100);
        long element = intToLongMap.get(5);

        Assert.assertEquals(100, element);
    }

    @Test
    public void shouldReturnValidSize() {
        intToLongMap.put(5, 100);
        intToLongMap.put(-5, 101);
        int size = intToLongMap.size();

        Assert.assertEquals(2, size);
    }

    @Test
    public void shouldAddDuplicateKeyElement() {
        intToLongMap.put(5, 100);
        intToLongMap.put(5, 101);
        int size = intToLongMap.size();

        Assert.assertEquals(2, size);
    }

    @Test
    public void shouldGetFirstElementWithDuplication() {
        intToLongMap.put(5, 100);
        intToLongMap.put(5, 101);
        long element = intToLongMap.get(5);

        Assert.assertEquals(100, element);
    }

    @Test
    public void shouldAddAndGetByIntegerMaxKey() {
        intToLongMap.put(Integer.MAX_VALUE, 100);
        long element = intToLongMap.get(Integer.MAX_VALUE);

        Assert.assertEquals(100, element);
    }

    @Test
    public void shouldAddAndGetByIntegerMinKey() {
        intToLongMap.put(Integer.MIN_VALUE, 1);
        long element = intToLongMap.get(Integer.MIN_VALUE);

        Assert.assertEquals(1, element);
    }

    @Test
    public void shouldThrowElementIsNotPresentException() {
        int key = 6;
        expectedException.expect(ElementIsNotPresentException.class);
        expectedException.expectMessage(String.format("Element with key %d is not present in the map", key));

        intToLongMap.get(6);
    }

}