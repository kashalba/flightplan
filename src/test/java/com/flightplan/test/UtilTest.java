package com.flightplan.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

import com.flightplan.Util;

public class UtilTest {

    @Test
    public void emptyStringShouldBeEmpty() {
        assertTrue(Util.isEmpty(""));
    }

    @Test
    public void missingStringShouldBeEmpty() {
        String str = null;
        assertTrue(Util.isEmpty(str));
    }

    @Test
    public void missingObjectShouldBeEmpty() {
        Object obj = null;
        assertTrue(Util.isEmpty(obj));
    }

    @Test
    public void missingCollectionShouldBeEmpty() {
        Collection<?> collection = null;
        assertTrue(Util.isEmpty(collection));
    }

    @Test
    public void emptyCollectionShouldBeEmpty() {
        Collection<?> collection = new LinkedList<Object>();
        assertTrue(Util.isEmpty(collection));
    }

    @Test
    public void filledCollectionShouldNotBeEmpty() {
        String[] arr = { "a" };
        assertTrue(!Util.isEmpty(arr));
    }

    @Test
    public void validStringShouldNotBeEmpty() {
        assertTrue(!Util.isEmpty("a"));
    }

    @Test
    public void validObjectShouldNotBeEmpty() {
        assertTrue(!Util.isEmpty(new Object()));
    }

}
