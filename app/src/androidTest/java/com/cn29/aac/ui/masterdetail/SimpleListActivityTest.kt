package com.cn29.aac.ui.masterdetail

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
internal class SimpleListActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<SimpleListActivity> = ActivityTestRule(
            SimpleListActivity::class.java)

    @Test
    fun should_open_page() {

    }
}