package com.example.android_github_api

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android_github_api.model.RepositoryItem
import com.example.android_github_api.model.RepositoryOwner
import org.junit.Test

class CardCustomViewTest {

    @Test
    fun cardCustomViewTest() {
        //given
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val createRepository = createRepositoryGithub()
        val cardCustomView = CardCustomView(context)

        //when
        cardCustomView.launch()
        runOnUiThread {
            cardCustomView.setup(createRepository)
        }

        //then
        onView(withId(R.id.repository_name)).check(matches(withText(createRepository.name)))
        onView(withId(R.id.repository_description)).check(matches(withText(createRepository.description)))
        onView(withId(R.id.repository_author)).check(matches(withText(createRepository.owner.login)))
    }
}

fun View.launch() {
    ActivityScenario.launch(MainActivity::class.java).onActivity {
        it.setContentView(this)
    }
}

private fun createRepositoryGithub() = RepositoryItem (
    name = "Repository",
    description = "Describes",
    forks_count = 10,
    stargazers_count = 5,
    owner = RepositoryOwner(
        "Login",
        "")
)