/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.MainActivity.Companion.HOME
import com.example.androiddevchallenge.MainActivity.Companion.LOGIN
import com.example.androiddevchallenge.MainActivity.Companion.WELCOME
import com.example.androiddevchallenge.ui.screens.HomeScreen
import com.example.androiddevchallenge.ui.screens.LoginScreen
import com.example.androiddevchallenge.ui.screens.WelcomeScreen
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : AppCompatActivity() {

    companion object {

        const val WELCOME = "welcome"
        const val LOGIN = "login"
        const val HOME = "home"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                ProvideWindowInsets(consumeWindowInsets = true) {
                    MyApp()
                }
            }
        }
    }

}


@Composable
fun MyApp() {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController = navController, startDestination = WELCOME) {
            composable(WELCOME) {
                WelcomeScreen { navController.navigate(LOGIN) }
            }

            composable(LOGIN) {
                LoginScreen { navController.navigate(HOME) }
            }

            composable(HOME) {
                HomeScreen()
            }
        }
    }
}

