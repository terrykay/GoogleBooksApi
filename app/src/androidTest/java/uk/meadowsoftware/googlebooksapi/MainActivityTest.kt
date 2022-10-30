package uk.meadowsoftware.googlebooksapi

import android.support.test.rule.ActivityTestRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.facebook.testing.screenshot.Screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    val testRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Rule
    fun rule()  = testRule

    @Test
    fun snapshot() {

        val view = testRule.activity.findViewById<RecyclerView>(R.id.recyclerView)
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        Screenshot.snap(view).record()
    }
}