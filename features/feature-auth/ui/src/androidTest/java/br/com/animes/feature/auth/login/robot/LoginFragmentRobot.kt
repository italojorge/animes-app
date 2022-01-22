package br.com.animes.feature.auth.login.robot

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import br.com.animes.core.custom.views.LoadingButton
import br.com.animes.feature.auth.R
import br.com.animes.feature.auth.login.LoginFragment
import br.com.animes.feature.auth.login.LoginFragmentTest
import org.hamcrest.Description
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsNot

class LoginFragmentRobot {

    fun typeAnEmail(text: String) {
        insertText(R.id.login_email_edit_text, text)
        Espresso.closeSoftKeyboard()
    }

    fun typeAPassword(text: String) {
        insertText(R.id.login_password_edit_text, text)
        Espresso.closeSoftKeyboard()
    }

    fun clickOnEnterButton() {
        onView(withId(R.id.login_loading_button)).perform(ViewActions.click())
    }

    private fun insertText(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).perform(ViewActions.typeText(text))
    }

    infix fun checkIf(event: LoginFragmentResult.() -> Unit) =
        LoginFragmentResult().apply(event)
}

class LoginFragmentResult {
//    fun recoveryCardPasswordTokenFragmentIsOpen(navController: TestNavHostController) {
//        assertEquals(R.id.recovery_card_password_token_fragment, navController.currentDestination?.id)
//    }

    fun isShowingCorrectTexts() {
        checkTextOnView(R.id.login_title_text_view, "Animes App")
        checkTextOnView(R.id.login_sub_title_text_view, "Welcome to Animes App")
        checkTextOnView(R.id.login_description_text_view, "Here you can consult the animes of your choice and view their details!")

        checkHintOnView(R.id.login_email_edit_text, "E-mail")
        checkHintOnView(R.id.login_password_edit_text, "Password")
        checkTextOnLoadingButton()
    }

    fun checkTextOnEmailEditText(userEmail: String) {
        checkTextOnView(R.id.login_email_edit_text, userEmail)
    }

    fun isBiometricSwitchDisplayed() {
        checkIfViewIsDisplayed(R.id.login_biometric_switch)
    }

    fun isBiometricSwitchNotDisplayed() {
        checkIfViewIsNotDisplayed(R.id.login_biometric_switch)
    }

    fun checkTextOnDialog(text: String) {
        onView(ViewMatchers.withText(text))
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(isDisplayed()))
    }

    private fun checkTextOnLoadingButton() {
        onView(withId(R.id.login_loading_button))
            .check(ViewAssertions.matches(LoadingButtonMatcher.matchText("Enter")))
    }

    private fun checkTextOnView(@IdRes viewId: Int, text: String) {
        onView(Matchers.allOf(withId(viewId), ViewMatchers.withText(text)))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    private fun checkHintOnView(@IdRes viewId: Int, hint: String) {
        onView(withId(viewId)).check(ViewAssertions.matches(ViewMatchers.withHint(hint)))
    }

    private fun checkIfViewIsDisplayed(@IdRes viewId: Int) {
        onView(Matchers.allOf(withId(viewId))).check(ViewAssertions.matches(isDisplayed()))
    }

    private fun checkIfViewIsNotDisplayed(@IdRes viewId: Int) {
        onView(Matchers.allOf(withId(viewId))).check(ViewAssertions.matches(IsNot.not(isDisplayed())))
    }

    private object LoadingButtonMatcher {
        fun matchText(text: String) = object : TypeSafeMatcher<View>() {
            override fun matchesSafely(item: View?) = item is LoadingButton && item.text == text
            override fun describeTo(description: Description?) {}
        }
    }
}


internal fun LoginFragmentTest.onLaunch(
    event: LoginFragmentRobot.() -> Unit = {}
): LoginFragmentRobot {
    launchFragmentInContainer<LoginFragment>(
        null,
        R.style.AppTheme
    ).onFragment { fragment ->
        navController.setGraph(R.navigation.auth_navigation)
        Navigation.setViewNavController(fragment.requireView(), navController)
    }
    return LoginFragmentRobot().apply(event)
}

