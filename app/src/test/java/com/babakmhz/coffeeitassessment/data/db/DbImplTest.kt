package com.babakmhz.coffeeitassessment.data.db


import com.babakmhz.coffeeitassessment.data.model.device.*
import com.babakmhz.coffeeitassessment.data.network.sampleResponse
import com.google.gson.Gson
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.DebugFlags
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.File


@RunWith(JUnit4::class)
class DbImplTest {

    private val TEST_DIRECTORY: File = File("objectbox-coffeeit/test-db")
    private lateinit var store: BoxStore
    private lateinit var deviceBox: Box<Device>
    private lateinit var typesBox: Box<Type>
    private lateinit var sizesBox: Box<Size>
    private lateinit var extrasBox :Box<Extra>
    private lateinit var subSelectionsBox :Box<Subselection>

    private lateinit var device: Device
    private var deviceId = -1L

    private lateinit var dbImpl: DbImpl


    @Test
    fun smokeTest() = assertTrue(true)



    @Before
    fun setUp() {
        // deleting database files before each test to start with a clean database
        BoxStore.deleteAllFiles(TEST_DIRECTORY)
        store =
            MyObjectBox.builder()
                .directory(TEST_DIRECTORY)
                .debugFlags(DebugFlags.LOG_QUERIES or DebugFlags.LOG_QUERY_PARAMETERS)
                .build()

        dbImpl = DbImpl(store)
        deviceBox = store.boxFor(Device::class.java)
        typesBox = store.boxFor(Type::class.java)
        sizesBox = store.boxFor(Size::class.java)
        extrasBox = store.boxFor(Extra::class.java)
        subSelectionsBox = store.boxFor(Subselection::class.java)

        val gson = Gson()
        device = gson.fromJson(sampleResponse, Device::class.java)
        deviceId = dbImpl.putAllData(device)


    }


    @Test
    fun `test inserting network response to device boxStore should pass`() {
        assertFalse(deviceId < 0)
        assertFalse(typesBox.all.isEmpty())
        assertFalse(sizesBox.all.isEmpty())
        assertFalse(extrasBox.all.isEmpty())
        assertFalse(subSelectionsBox.all.isEmpty())
        for(extra in extrasBox.all ){
            assertFalse(extra.subselections.isEmpty())
        }
    }


    @Test
    fun `test putting data in db should not produce duplicate records for device`(){
        // we did that once in setup function so, this would be second time
        dbImpl.putAllData(device)
        assertTrue(deviceBox.all.size==1)
    }

    @Test
    fun `testing relation for associated sizes and extras should pass`() {
        // retrieving one sample type from db
        // we know in device object there's single type with sizes and extras
        val type = typesBox.all[0]
        val sizes = dbImpl.getSizesForType(type)
        val extras = dbImpl.getExtrasForType(type)
        val subSelections = dbImpl.getSubSelectionsForExtra(extras[0])

        println(type)
        assertEquals(sizes.size,type.sizes.size)
        assertEquals(extras.size,type.extras.size)
        assertEquals(subSelections.size,device.extras[0].subselections.size)
    }


    @After
    fun tearDown() {
        store.close()
        BoxStore.deleteAllFiles(TEST_DIRECTORY)
    }
}