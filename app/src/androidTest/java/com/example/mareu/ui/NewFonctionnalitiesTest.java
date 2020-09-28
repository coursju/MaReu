package com.example.mareu.ui;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import com.example.mareu.R;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewFonctionnalitiesTest {

    @Rule
    public ActivityScenarioRule <MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void a_addOneReunionTest() {
        onView(allOf(withId(R.id.add_reunion), isDisplayed())).perform(click());
        onView(withId(R.id.hour_selection)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("10"))).perform(click());
        onView(withId(R.id.hour_selection)).check(matches(withSpinnerText(containsString("10"))));
        onView(withId(R.id.enter_name)).perform(replaceText("abc"));
        onView(withId(R.id.enter_name)).check(matches(withText("abc")));
        onView(withId(R.id.save_reunion)).perform(scrollTo(), click());
        pressBack();
        onView(allOf(withId(R.id.item_list_title), withText("abc"), isDisplayed()));
    }

    @Test
    public void b_filterByRoomTest() {
        openContextualActionModeOverflowMenu();
        onView(allOf(withId(R.id.title), withText("Filtrer par lieu"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("salle 1"))).perform(click());
        onView(allOf(withId(R.id.item_list_title), withText("abc"), isDisplayed()));
    }

    @Test
    public void c_filterByHourTest() {
        openContextualActionModeOverflowMenu();
        onView(allOf(withId(R.id.title), withText("Filtrer par heure"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("10:00"))).perform(click());
        onView(allOf(withId(R.id.item_list_title), withText("abc"), isDisplayed()));
    }

    @Test
    public void d_deleteReunionTest() {
        onView(withId(R.id.reunion_recycler))
                .perform(actionOnItemAtPosition(4, click()));
        onView(allOf(withId(R.id.item_list_title), withText("abc"))).check(doesNotExist());
    }
}
