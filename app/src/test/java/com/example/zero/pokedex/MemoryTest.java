package com.example.zero.pokedex;

import android.view.View;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by parker on 11/17/17.
 */
public class MemoryTest {


    /*View view;
    @Test
    public void testBackRed() throws Exception {
    }

    @Test
    public void testBackWhite() throws Exception {
    }

    @Test
    public void testBackBlue() throws Exception {
    }

    @Test
    public void testlogoutUser() throws Exception {

        Memory memory = new Memory();

        assertEquals(memory.logoutUser(), memory.);

    }*/


    @Test //Test to see if the user get the right font when the button is hit
    public void testFontChange1() throws Exception {
        assertEquals(Memory.getFont("calibri.ttf"), "calibri.ttf");

    }

    @Test//Test to see if the user get the right font when the button is hit
    public void testFontChange2() throws Exception {

        assertEquals(Memory.getFont("HARLOWSI.ttf"), "HARLOWSI.ttf");
    }

    @Test//Test to see if the user get the right font when the button is hit
    public void testFontChange3() throws Exception {
        assertEquals(Memory.getFont("OldEnglish.ttf"), "OldEnglish.ttf");
    }

}