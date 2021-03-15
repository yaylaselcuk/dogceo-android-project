package com.yaylas.dogceo.data.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class BreedDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: BreedDatabase
    private lateinit var dao: BreedDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BreedDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.breedDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertMasterBreed() = runBlockingTest {
        val breed = BreedCacheEntity("name", null)
        dao.insertBreed(breed)
        val resultList = dao.get(null)
        assertThat(resultList.contains(breed))
    }

    @Test
    fun insertSubBreed() = runBlockingTest {
        val breed = BreedCacheEntity("sub", "master")
        dao.insertBreed(breed)
        val resultList = dao.get(null)
        assertThat(resultList.contains(breed))
    }

    @Test
    fun getSubBreeds() = runBlockingTest {
        val breed1 = BreedCacheEntity("master", null)
        val breed2 = BreedCacheEntity("sub1", "master")
        val breed3 = BreedCacheEntity("sub2", "master")
        val breed4 = BreedCacheEntity("sub3", "master")

        dao.insertBreed(breed1)
        dao.insertBreed(breed2)
        dao.insertBreed(breed3)
        dao.insertBreed(breed4)

        val resultList = dao.get("master")
        assertThat(resultList.size).isEqualTo(3)
    }
}