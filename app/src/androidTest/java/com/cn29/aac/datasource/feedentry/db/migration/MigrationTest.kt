package com.cn29.aac.datasource.feedentry.db.migration

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.cn29.aac.datasource.feedentry.db.AppDatabase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
internal class MigrationTest {
    private val TEST_DB = "migration-test"
    private val ALL_MIGRATIONS = arrayOf(
            Migration1_2.newInstance(), Migration2_3.newInstance())

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
            InstrumentationRegistry.getInstrumentation(),
            AppDatabase::class.java.canonicalName,
            FrameworkSQLiteOpenHelperFactory()
    )


    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        helper.createDatabase(TEST_DB, 1).apply {
            execSQL("INSERT INTO feedEntrys (title,subtitle) VALUES ('Title1','SubTitle1')")
            close()
        }
        helper.runMigrationsAndValidate(TEST_DB,
                                        2,
                                        true,
                                        Migration1_2.newInstance())
    }

    @Test
    @Throws(IOException::class)
    fun migrate2To3() {
        helper.createDatabase(TEST_DB, 2).apply {
            execSQL("INSERT INTO feedEntrys (title,subtitle,imageUrl) VALUES ('Title1','SubTitle1','www.google.com')")
            close()
        }
        helper.runMigrationsAndValidate(TEST_DB,
                                        3,
                                        true,
                                        Migration2_3.newInstance())
    }

    @Test
    @Throws(IOException::class)
    fun migrateAll() {
        // Create earliest version of the database.
        helper.createDatabase(TEST_DB, 1).apply {
            close()
        }
        // Open latest version of the database. Room will validate the schema
        // once all migrations execute.
        Room.databaseBuilder(
                InstrumentationRegistry.getInstrumentation().targetContext,
                AppDatabase::class.java,
                TEST_DB
        ).addMigrations(*ALL_MIGRATIONS).build().apply {
            openHelper.writableDatabase
            close()
        }
    }

}