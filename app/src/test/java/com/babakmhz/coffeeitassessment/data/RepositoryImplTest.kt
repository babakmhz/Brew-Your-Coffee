package com.babakmhz.coffeeitassessment.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.babakmhz.coffeeitassessment.data.db.DbHelper
import com.babakmhz.coffeeitassessment.data.model.device.Device
import com.babakmhz.coffeeitassessment.data.model.typeImages.Result
import com.babakmhz.coffeeitassessment.data.model.typeImages.TypeImage
import com.babakmhz.coffeeitassessment.data.model.typeImages.Urls
import com.babakmhz.coffeeitassessment.data.network.ApiService
import com.babakmhz.coffeeitassessment.data.network.sampleResponse
import com.babakmhz.coffeeitassessment.utils.validString
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.any
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RepositoryImplTest {

    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule(coroutineDispatcher)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiService: ApiService
    private lateinit var dbHelper: DbHelper
    private lateinit var repo: RepositoryImpl
    private lateinit var sampleTypesResponse: Device

    @Before
    fun setUp() {
        apiService = mockk()
        dbHelper = mockk()
        repo = RepositoryImpl(apiService, dbHelper)
        val gson = Gson()
        sampleTypesResponse = gson.fromJson(sampleResponse, Device::class.java)

    }


    @Test
    fun `test getting types from server handles associated images`() =
        coroutineTestRule.coroutineScope.runBlockingTest {
            //before
            coEvery { apiService.getDeviceCoffees() } returns sampleTypesResponse
            coEvery { apiService.getTypeImage(ofType()) } returns TypeImage(
                arrayListOf(
                    Result(
                        Urls(
                            "someUrl",
                            "someUrl",
                            "someUrl",
                            "someUrl",
                            "someUrl",
                        )
                    )
                )
            )

            every { dbHelper.putAllData(ofType()) } returns 1L
            every { dbHelper.getImageUrlForType(ofType()) } returns ""

            //when
            val devices = repo.getDeviceCoffees()

            //then
            for(type in devices.types){
                assertTrue(type.imageUrl.validString())
            }
        }



    @Test
    fun `test getting types from server handles associated images if `() =
        coroutineTestRule.coroutineScope.runBlockingTest {
            //before
            for (type in sampleTypesResponse.types)
                type.imageUrl = "ValidString"
            coEvery { apiService.getDeviceCoffees() } returns sampleTypesResponse
            coEvery { apiService.getTypeImage(ofType()) } returns TypeImage(
                arrayListOf(
                    Result(
                        Urls(
                            "someUrl",
                            "someUrl",
                            "someUrl",
                            "someUrl",
                            "someUrl",
                        )
                    )
                )
            )

            every { dbHelper.putAllData(ofType()) } returns 1L
            every { dbHelper.getImageUrlForType(ofType()) } returns ""

            //when
            val devices = repo.getDeviceCoffees()

            //then
            for(type in devices.types){
                assertTrue(type.imageUrl.validString())
            }
        }
}