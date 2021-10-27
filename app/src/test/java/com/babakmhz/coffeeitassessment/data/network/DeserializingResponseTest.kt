package com.babakmhz.coffeeitassessment.data.network

import com.babakmhz.coffeeitassessment.data.model.device.Device
import com.google.gson.Gson
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DeserializingResponseTest {

    @Test
    fun smokeTest() = assertTrue(true)


    @Test
    fun `test deserializing sample network response should pass`() {
        val gson = Gson()
        val device = gson.fromJson(sampleResponse, Device::class.java)
        println(device)
        assertFalse(device.extras.isEmpty())
        assertFalse(device.sizes.isEmpty())
        assertFalse(device.types.isEmpty())
    }
}