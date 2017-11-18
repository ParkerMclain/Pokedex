package com.example.zero.pokedex;

import org.junit.Test;

import static org.junit.Assert.*;



public class loginFirebaseTest {
    @Test //Unit Test to see if user does not enter both fields
    public void testCheckTextboxForEmptyNoText() throws Exception {
        assertEquals(loginFirebase.checkTextboxForEmpty("",""), false);
    }

    @Test//Unit Test to see if the user enters both of the field
    public void testCheckTextboxForEmptyWithText() throws Exception {
        assertEquals(loginFirebase.checkTextboxForEmpty("Hello","World"), true);
    }

    @Test//Unit Test to see if the user enters one of the fields is empty
    public void testCheckTextboxForEmptyOneEmptyOneNot() throws Exception {
        assertEquals(loginFirebase.checkTextboxForEmpty("Hello",""), false);
    }
    //Unit Test to see if the user enters one of the fields is empter
    @Test
    public void testCheckTextboxForEmptyOneEmptyOneNot2() throws Exception {
        assertEquals(loginFirebase.checkTextboxForEmpty("","World"), false);
    }
}