package com.cn29.aac.ui.location

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
internal class LocationActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<LocationActivity> = ActivityTestRule(
            LocationActivity::class.java)

    @Test
    fun should_open_page() {

    }
}