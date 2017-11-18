package com.example.zero.pokedex;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import android.content.Context;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import android.content.SharedPreferences;

public class createNewAccountTest {

    @Test//Test when the user sets a password less then 6
    public void testPasswordLengthLessThen6() throws Exception {
        assertEquals(createNewAccount.checkPasswordLength("hello"), false);
    }

    @Test//Test when the user sets a password more then 6
    public void testPasswordLengthGreaterThan6() throws Exception {
        assertEquals(createNewAccount.checkPasswordLength("hello world"), true);
    }

    @Test//Test when the user sets a password equal to 6
    public void testPasswordLengthExactly6() throws Exception {
        assertEquals(createNewAccount.checkPasswordLength("hello1"), true);
    }
    @Test//Test when the user sets a password and its empty
    public void testPasswordEmpty() throws Exception {
        assertEquals(createNewAccount.checkPasswordLength(""), false);
    }
}