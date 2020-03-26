package com.cn29.aac.ui.feedentry


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FeedActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<FeedActivity> = ActivityTestRule(
            FeedActivity::class.java)

    @Test
    fun should_open_feed_page() {

    }
}