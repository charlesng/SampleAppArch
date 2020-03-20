package com.cn29.aac.datasource.feedentry.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.cn29.aac.repo.feedentry.FeedEntry
import com.cn29.aac.utils.LiveDataTestUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
internal class FeedEntryDAOTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var feedEntryDAO: FeedEntryDAO
    private lateinit var getAllLiveData: LiveData<List<FeedEntry>>
    private lateinit var findByUidLiveData: LiveData<FeedEntry>
    private var isDeleted: Boolean = false
    private lateinit var insertedId: List<Long>

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                                                   AppDatabase::class.java)
                .build()
        feedEntryDAO = appDatabase.feedEntryDao()
    }


    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun should_insert_all_entries() {
        val entries = generateDummyEntries(size = 3)
        givenEntriesInserted(entries)
        whenAllEntriesFetch()
        thenEntriesSizeShouleBe(size = 3)
    }

    @Test
    fun should_find_Entry_By_Uid() {
        val entries = generateDummyEntries(size = 1)
        givenEntriesInserted(entries)
        whenEntriesFetchByUid(uid = insertedId[0])
        thenEntryShouldNotBeNull()
    }

    @Test
    fun should_delete_one_record() {
        val entries = generateDummyEntries(size = 1)
        givenEntriesInserted(entries)
        whenEntryIsDeleted(entries[0])
        thenReturnValueShouldBe(true)
    }

    @Test
    fun should_update_1_entry() {
        val entries = generateDummyEntries(size = 1)
        givenEntriesInserted(entries)
        entries[0].apply {
            title = "New Title"
            subTitle = "New Subtitle"
            uid = insertedId[0].toInt()
        }
        whenEntryIsUpdated(entries[0])
        whenEntriesFetchByUid(uid = insertedId[0])
        thenEntryShouldBeSame(expect = entries[0],
                              actual = LiveDataTestUtil.getValue(
                                      findByUidLiveData))
    }

    private fun thenEntryShouldBeSame(expect: FeedEntry,
                                      actual: FeedEntry) {
        assertEquals(expect, actual)
    }

    private fun whenEntryIsUpdated(entry: FeedEntry) {
        feedEntryDAO.update(entry)
    }

    private fun thenReturnValueShouldBe(expect: Boolean) {
        assertEquals(expect, isDeleted)
    }

    private fun whenEntryIsDeleted(feedEntry: FeedEntry) {
        isDeleted = feedEntryDAO.delete(feedEntry) != -1
    }

    private fun thenEntryShouldNotBeNull() {
        assertNotNull(LiveDataTestUtil.getValue(findByUidLiveData))
    }

    private fun whenEntriesFetchByUid(uid: Long) {
        findByUidLiveData = feedEntryDAO.findByUid(uid.toInt())
    }

    private fun thenEntriesSizeShouleBe(size: Int) {
        assertEquals(size, LiveDataTestUtil.getValue(getAllLiveData).size)
    }

    private fun whenAllEntriesFetch() {
        getAllLiveData = feedEntryDAO.getAll()
    }

    private fun givenEntriesInserted(entries: List<FeedEntry>) {
        insertedId = feedEntryDAO.insertAll(*entries.toTypedArray())
    }

    private fun generateDummyEntries(size: Int): List<FeedEntry> =
            (1..size).map {
                FeedEntry(title = "Testing$it",
                          subTitle = "SubTitle$it")
            }
}