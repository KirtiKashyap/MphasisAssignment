package com.example.mphasisassignment


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun checkPath() {
        ActivityScenario.launch(MainActivity::class.java)

        // Check MainActivity
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
        // Check Album fragment screen is displayed
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

    }
}
