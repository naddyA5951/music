package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.MusicCatalog
import com.example.model.formatDuration
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Music Libreriya", appName)
    }

    @Test
    fun `verify catalog tracks and formatting`() {
        val tracks = MusicCatalog.sampleTracks
        assertTrue(tracks.isNotEmpty())
        assertEquals(100, tracks.size)
        assertEquals("3:12", formatDuration(192000L))
        assertEquals("0:00", formatDuration(0L))
    }
}
