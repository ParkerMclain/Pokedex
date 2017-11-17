package com.example.zero.pokedex;

import org.junit.Test;

import static org.junit.Assert.*;



public class loginFirebaseTest {
    @Test
    public void testCheckTextboxForEmptyNoText() throws Exception {
        assertEquals(loginFirebase.checkTextboxForEmpty("",""), false);
    }

    @Test
    public void testCheckTextboxForEmptyWithText() throws Exception {
        assertEquals(loginFirebase.checkTextboxForEmpty("Hello","World"), true);
    }

    @Test
    public void testCheckTextboxForEmptyOneEmptyOneNot() throws Exception {
        assertEquals(loginFirebase.checkTextboxForEmpty("Hello",""), false);
    }

    @Test
    public void testCheckTextboxForEmptyOneEmptyOneNot2() throws Exception {
        assertEquals(loginFirebase.checkTextboxForEmpty("","World"), false);
    }
}