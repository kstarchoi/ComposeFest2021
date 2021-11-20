package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.RallyScreen.Accounts
import com.example.compose.rally.RallyScreen.Overview
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        val allScreen = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreen,
                onTabSelected = {},
                currentScreen = Accounts
            )
        }

        composeTestRule
            .onNodeWithContentDescription(Accounts.name)
            .assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreen = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreen,
                onTabSelected = {},
                currentScreen = Accounts
            )
        }

//        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        composeTestRule
            .onNode(
                matcher = hasText(Accounts.name.uppercase()) and
                        hasParent(hasContentDescription(Accounts.name)),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_currentTabChanged() {
        composeTestRule.setContent {
            RallyApp()
        }

        composeTestRule
            .onNodeWithContentDescription(Overview.name)
            .assertIsSelected()

        composeTestRule
            .onNodeWithContentDescription(Accounts.name)
            .performClick()
            .assertIsSelected()

        composeTestRule
            .onNodeWithContentDescription(Overview.name)
            .assertIsNotSelected()
    }
}