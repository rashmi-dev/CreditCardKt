package com.example.creditcardinputexercise

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private var cardNameToBeTyped: String? = null
    private var mmyyNameToBeTyped: String? = null
    private var securityCodeToBeTyped: String? = null
    private var firstNameToBeTyped: String? = null
    private var lastNameToBeTyped: String? = null
    private var cardNameToBeTypedIn: String? = null
    private var mmyyNameToBeTypedIn: String? = null
    private var securityCodeToBeTypedIn: String? = null
    private var firstNameToBeTypedIn: String? = null
    private var lastNameToBeTypedIn: String? = null

    @Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.creditcardinputexercise", appContext.packageName)
    }

    @Before
    fun initValidString() {
        // Specify a valid string.
        cardNameToBeTyped = "371449635398431"
        mmyyNameToBeTyped = "08/21"
        securityCodeToBeTyped = "5678"
        firstNameToBeTyped = "Jo"
        lastNameToBeTyped = "mo"
    }

    @Before
    fun initInValidString() {
        // Specify a valid string.
        cardNameToBeTypedIn = "371449635398436"
        mmyyNameToBeTypedIn = "08/58"
        securityCodeToBeTypedIn = "567"
        firstNameToBeTypedIn = "5646456456456"
        lastNameToBeTypedIn = ""
    }

    @Test
    fun changeText_sameActivity() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(ViewActions.typeText(cardNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(ViewActions.typeText(mmyyNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(ViewActions.typeText(securityCodeToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(ViewActions.typeText(firstNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(ViewActions.typeText(lastNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.submit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("OK")).inRoot(RootMatchers.isDialog()).check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.pressBack())
    }

    @Test
    fun check_when_good_error() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(ViewActions.typeText("4111111111111111"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(ViewActions.typeText("10/20"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(ViewActions.typeText("626"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(ViewActions.typeText("Jane"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(ViewActions.typeText("Doe"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.submit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("OK")).inRoot(RootMatchers.isDialog()).check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.pressBack())
    }

    @Test
    fun check_when_card_error() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(ViewActions.typeText(cardNameToBeTypedIn), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(ViewActions.typeText(mmyyNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(ViewActions.typeText(securityCodeToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(ViewActions.typeText(firstNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(ViewActions.typeText(lastNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.submit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.card_number)).check(ViewAssertions.matches(hasTextInputLayoutErrorText("Invalid number")))
    }

    @Test
    fun check_when_mmyy_error() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(ViewActions.typeText(cardNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(ViewActions.typeText(mmyyNameToBeTypedIn), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(ViewActions.typeText(securityCodeToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(ViewActions.typeText(firstNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(ViewActions.typeText(lastNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.submit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.mm_yy)).check(ViewAssertions.matches(hasTextInputLayoutErrorText("Card Expired")))
    }

    @Test
    fun check_when_security_error() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(ViewActions.typeText(cardNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(ViewActions.typeText(mmyyNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(ViewActions.typeText(securityCodeToBeTypedIn), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(ViewActions.typeText(firstNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(ViewActions.typeText(lastNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.submit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.security_code)).check(ViewAssertions.matches(hasTextInputLayoutErrorText("Invalid length")))
    }

    @Test
    fun check_when_firstn_error() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(ViewActions.typeText(cardNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(ViewActions.typeText(mmyyNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(ViewActions.typeText(securityCodeToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(ViewActions.typeText(firstNameToBeTypedIn), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(ViewActions.typeText(lastNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.submit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.first_name)).check(ViewAssertions.matches(hasTextInputLayoutErrorText("Invalid name")))
    }

    @Test
    fun check_when_ln_error() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.card_number_edit))
                .perform(ViewActions.typeText(cardNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.mm_yy_edit))
                .perform(ViewActions.typeText(mmyyNameToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.security_code_edit))
                .perform(ViewActions.typeText(securityCodeToBeTyped), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.first_name_edit))
                .perform(ViewActions.typeText(firstNameToBeTypedIn), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.last_name_edit))
                .perform(ViewActions.typeText(lastNameToBeTypedIn), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.submit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.last_name)).check(ViewAssertions.matches(hasTextInputLayoutErrorText("Field Empty")))
    }

    companion object {
        fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                public override fun matchesSafely(view: View?): Boolean {
                    if (view !is TextInputLayout) {
                        return false
                    }
                    val error = view.error ?: return false
                    val hint = error.toString()
                    return expectedErrorText == hint
                }

                override fun describeTo(description: Description) {}
            }
        }
    }
}