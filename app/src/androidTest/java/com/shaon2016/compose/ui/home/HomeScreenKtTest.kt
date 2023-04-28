package com.shaon2016.compose.ui.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.shaon2016.compose.domain.entity.Product
import org.junit.Rule
import org.junit.Test

internal class HomeScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun verifyLoading_WhenStateIsInLoading() {
        val state = HomeContract.State(isLoading = true)

        composeTestRule.setContent {
            HomeContent(state = state)
        }

        composeTestRule
            .onNodeWithText("Loading")
            .assertExists()
    }

    @Test
    fun verifyFail_WhenStateIsInFail() {
        val state = HomeContract.State(dataState = DataState.FAIL)

        composeTestRule.setContent {
            HomeContent(state = state)
        }

        composeTestRule
            .onNodeWithText("Fail")
            .assertExists()
    }

    @Test
    fun verifyProductList_WhenStateIsSuccess_LoadingIsNotShown_FailIsNowShown() {
        val state = HomeContract.State(
            dataState = DataState.SUCCESS,
            products = listOf(
                Product(
                    id = "asd",
                    title = "Bike",
                    price = 230.2
                )
            )
        )

        composeTestRule.setContent {
            HomeContent(state = state)
        }

        composeTestRule
            .onNodeWithText("Loading")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("Fail")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(state.products[0].title)
            .assertExists()
    }

}