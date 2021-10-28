package com.babakmhz.coffeeitassessment.view.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.babakmhz.coffeeitassessment.data.CoroutineTestRule
import com.babakmhz.coffeeitassessment.data.RepositoryHelper
import com.babakmhz.coffeeitassessment.data.model.device.Device
import com.babakmhz.coffeeitassessment.utils.State
import com.google.android.gms.maps.model.LatLng
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest{
    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule(coroutineDispatcher)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var viewModel: MainViewModel
    private lateinit var repoHelper: RepositoryHelper
    @Test
    fun smokeTest() = assertTrue(true)


    @Before
    fun setup() {
        repoHelper = mockk()
        viewModel = MainViewModel(repoHelper)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test products with error response should change live data state to error`() =
        coroutineTestRule.coroutineScope.runBlockingTest {
            val returnError = Throwable()
            coEvery { repoHelper.getDeviceCoffees() } throws (returnError)
            viewModel.typesLivedata.observeForever {
            }
            viewModel.getTypes()

            assertNotNull(viewModel.typesLivedata.value)
            assertEquals(viewModel.typesLivedata.value, State.Error(returnError))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getting products with success response should change live data state to success`() =
        coroutineTestRule.coroutineScope.runBlockingTest {


            val response = Device()
            coEvery { repoHelper.getDeviceCoffees() } coAnswers {
                delay(100)
                response
            }

            viewModel.typesLivedata.observeForever {
            }

            viewModel.getTypes()

            assertNotNull(viewModel.typesLivedata.value)
            advanceTimeBy(0)
            assertEquals(viewModel.typesLivedata.value, State.Loading)
            advanceTimeBy(100)
            assertNotNull(viewModel.typesLivedata.value)
        }

}