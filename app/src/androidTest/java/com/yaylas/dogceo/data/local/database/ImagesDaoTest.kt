package com.yaylas.dogceo.data.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.yaylas.dogceo.data.local.models.BreedCacheEntity
import com.yaylas.dogceo.data.local.models.ImageCacheEntity
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
class ImagesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: BreedDatabase
    private lateinit var dao: ImagesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BreedDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.imagesDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertMasterBreedImage() = runBlockingTest {
        val image = ImageCacheEntity("name", null, "https://www.image.url")
        dao.insertImage(image)
        val resultList = dao.get("name", null)
        Truth.assertThat(resultList.contains(image))
    }

    @Test
    fun insertSubBreedImage() = runBlockingTest {
        val image = ImageCacheEntity("name", "master", "https://www.image.url")
        dao.insertImage(image)
        val resultList = dao.get("name", "master")
        Truth.assertThat(resultList.contains(image))
    }

    @Test
    fun getSubBreedImages() = runBlockingTest {

        val image1 = ImageCacheEntity("sub1", "master", "https://www.image.url")
        val image2 = ImageCacheEntity("sub2", "master", "https://www.image.url")
        val image3 = ImageCacheEntity("sub2", "master", "https://www.image.url")

        dao.insertImage(image1)
        dao.insertImage(image2)
        dao.insertImage(image3)


        val resultList = dao.get("sub2",   "master")
        Truth.assertThat(resultList.size).isEqualTo(2)
    }
}