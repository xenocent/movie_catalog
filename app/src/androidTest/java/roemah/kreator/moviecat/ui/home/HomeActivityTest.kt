package roemah.kreator.moviecat.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import roemah.kreator.moviecat.R
import roemah.kreator.moviecat.utils.DataDummy
import roemah.kreator.moviecat.utils.EspressoIdlingResource

class HomeActivityTest {

//    private val dummyTv = DataDummy.generateDummyTv()
//    private val dummyMovie = DataDummy.generateDummyMovie()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTv() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun loadDetailTv() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
    }

}