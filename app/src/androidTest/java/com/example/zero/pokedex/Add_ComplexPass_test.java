package com.example.zero.pokedex;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Add_ComplexPass_test {
//This test will if the user can press the complex password button.
    @Rule
    public ActivityTestRule<Welcome> mActivityTestRule = new ActivityTestRule<>(Welcome.class);

    @Test
    public void add_ComplexPass_test() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnStart), withText("Start"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnCreate), withText("Create a new account"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btnSetPassword), withText("Set Password"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btnComplex), withText("Create Complex Password"), isDisplayed()));
        appCompatButton4.perform(click());

    }

}
