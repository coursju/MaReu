package com.example.mareu.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.mareu.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
public class FonctionnalitiesTest {

    @Rule
    public ActivityScenarioRule <MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void FonctionnalitiesTest() {
//        ViewInteraction floatingActionButton = onView(
//                allOf(withId(R.id.add_reunion), withContentDescription("Ajouter une réunion"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                1),
//                        isDisplayed()));
//        floatingActionButton.perform(click());

        onView( allOf(withId(R.id.add_reunion), isDisplayed())).perform(click());

//        ViewInteraction appCompatSpinner = onView(
//                allOf(withId(R.id.hour_selection),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.LinearLayout")),
//                                        0),
//                                0)));
//        appCompatSpinner.perform(scrollTo(), click());

        onView(withId(R.id.hour_selection)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("10"))).perform(click());
        onView(withId(R.id.hour_selection)).check(matches(withSpinnerText(containsString("10"))));

//        DataInteraction appCompatCheckedTextView = onData(anything())
//                .inAdapterView(childAtPosition(
//                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
//                        0))
//                .atPosition(1);
//        appCompatCheckedTextView.perform(click());

//        ViewInteraction appCompatEditText = onView(
//                allOf(withId(R.id.enter_name),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.ScrollView")),
//                                        0),
//                                9)));
//        appCompatEditText.perform(scrollTo(), replaceText("abc"), closeSoftKeyboard());

        onView(withId(R.id.enter_name)).perform(replaceText("abc"));
        onView(withId(R.id.enter_name)).check(matches(withText("abc")));

//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.save_reunion), withText("Ajouter"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.ScrollView")),
//                                        0),
//                                12)));
//        appCompatButton.perform(scrollTo(), click());

        onView( allOf(withId(R.id.save_reunion), isDisplayed())).perform(click());

        //onView(withId(R.id.save_reunion)).perform(click());
        pressBack();

//        ViewInteraction appCompatImageButton = onView(
//                allOf(withContentDescription("Navigate up"),
//                        childAtPosition(
//                                allOf(withId(R.id.action_bar),
//                                        childAtPosition(
//                                                withId(R.id.action_bar_container),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatImageButton.perform(click());
//
//        ViewInteraction textView = onView(
//                allOf(withId(R.id.item_list_title), withText("abc-10:00-salle 1"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.reunion_recycler),
//                                        4),
//                                1),
//                        isDisplayed()));
//        textView.check(matches(withText("abc-10:00-salle 1")));

        onView(allOf(withId(R.id.item_list_title), withText("abc"),isDisplayed()));
//
//        ViewInteraction overflowMenuButton = onView(
//                allOf(withContentDescription("More options"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.action_bar),
//                                        1),
//                                0),
//                        isDisplayed()));
//        overflowMenuButton.perform(click());
//
//        ViewInteraction appCompatTextView = onView(
//                allOf(withId(R.id.title), withText("Filtrer par lieu"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.content),
//                                        0),
//                                0),
//                        isDisplayed()));
//        appCompatTextView.perform(click());
//
//        DataInteraction appCompatTextView2 = onData(anything())
//                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
//                        childAtPosition(
//                                withClassName(is("android.widget.FrameLayout")),
//                                0)))
//                .atPosition(0);
//        appCompatTextView2.perform(click());
//
//        ViewInteraction textView2 = onView(
//                allOf(withId(R.id.item_list_title), withText("abc-10:00-salle 1"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.reunion_recycler),
//                                        1),
//                                1),
//                        isDisplayed()));
//        textView2.check(matches(withText("abc-10:00-salle 1")));
//
        onView(allOf(withContentDescription("More options"))).perform(click());
        onView(allOf(withId(R.id.title), withText("Filtrer par lieu"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("salle 1"))).perform(click());
        onView(allOf(withId(R.id.item_list_title), withText("abc"),isDisplayed()));

//        ViewInteraction overflowMenuButton2 = onView(
//                allOf(withContentDescription("More options"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.action_bar),
//                                        1),
//                                0),
//                        isDisplayed()));
//        overflowMenuButton2.perform(click());
//
//        ViewInteraction appCompatTextView3 = onView(
//                allOf(withId(R.id.title), withText("Filtrer par heure"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.content),
//                                        0),
//                                0),
//                        isDisplayed()));
//        appCompatTextView3.perform(click());
//
//        DataInteraction appCompatTextView4 = onData(anything())
//                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
//                        childAtPosition(
//                                withClassName(is("android.widget.FrameLayout")),
//                                0)))
//                .atPosition(4);
//        appCompatTextView4.perform(click());
//
//        ViewInteraction textView3 = onView(
//                allOf(withId(R.id.item_list_title), withText("abc-10:00-salle 1"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.reunion_recycler),
//                                        0),
//                                1),
//                        isDisplayed()));
//        textView3.check(matches(withText("abc-10:00-salle 1")));
//
        onView(allOf(withContentDescription("More options"))).perform(click());
        onView(allOf(withId(R.id.title), withText("Filtrer par heure"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("10:00"))).perform(click());
        onView(allOf(withId(R.id.item_list_title), withText("abc"),isDisplayed()));

//        ViewInteraction appCompatImageButton2 = onView(
//                allOf(withId(R.id.item_reunion_delete_button),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.reunion_recycler),
//                                        0),
//                                3),
//                        isDisplayed()));
//        appCompatImageButton2.perform(click());
//
//        ViewInteraction overflowMenuButton3 = onView(
//                allOf(withContentDescription("More options"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.action_bar),
//                                        1),
//                                0),
//                        isDisplayed()));
//        overflowMenuButton3.perform(click());
//
        //onView(allOf(withId(R.id.item_list_title), withText("abc"),withId(R.id.item_reunion_delete_button))).perform(click());

        //onView(allOf(withId(R.id.item_list_title), withText("abc"))).check(doesNotExist());


//        ViewInteraction appCompatTextView5 = onView(
//                allOf(withId(R.id.title), withText("Pas de filtre"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.content),
//                                        0),
//                                0),
//                        isDisplayed()));
//        appCompatTextView5.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
