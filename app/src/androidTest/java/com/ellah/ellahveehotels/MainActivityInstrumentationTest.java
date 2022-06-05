package com.ellah.ellahveehotels;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.ellah.ellahveehotels.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {

        @Rule
        public ActivityScenarioRule<MainActivity> activityRule =
                new ActivityScenarioRule<>(MainActivity.class);
        @Test
        public void validateEditText(){
            onView(withId(R.id.locationEditText)).perform(typeText("London"));
            onView(withId(R.id.findHotel)).perform(click());
            onView(withId(R.id.locationTextView)).check(matches(withText("Here are the hotels near London")));
        }



        }
