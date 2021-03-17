package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.MainActivity
import com.example.androiddevchallenge.R
import java.util.Locale

@Composable
fun LoginScreen(action: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Surface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_login),
                contentDescription = MainActivity.LOGIN,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.log_in).toUpperCase(Locale.getDefault()),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.paddingFromBaseline(
                        top = 200.dp,
                        bottom = 32.dp
                    )
                )
                TextField(
                    value = email,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.email_address),
                            style = MaterialTheme.typography.body1
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
                    textStyle = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onSurface
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    onValueChange = { email = it },
                    keyboardActions = KeyboardActions(onNext = { focusRequester.requestFocus() })
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth()
                )
                TextField(
                    value = password,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.password),
                            style = MaterialTheme.typography.body1
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
                    textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester = focusRequester),
                    singleLine = true,
                    onValueChange = { password = it },
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth()
                )
                Button(
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    onClick = action
                ) {
                    Text(text = stringResource(R.string.log_in).toUpperCase(Locale.getDefault()))
                }
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.onBackground,
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontSize = MaterialTheme.typography.body1.fontSize,
                                fontFamily = MaterialTheme.typography.body1.fontFamily,
                                fontWeight = MaterialTheme.typography.body1.fontWeight,
                                letterSpacing = MaterialTheme.typography.body1.letterSpacing
                            )
                        ) {
                            append("Don't have an account?")
                        }
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.onBackground,
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontSize = MaterialTheme.typography.body1.fontSize,
                                fontFamily = MaterialTheme.typography.body1.fontFamily,
                                fontWeight = MaterialTheme.typography.body1.fontWeight,
                                letterSpacing = MaterialTheme.typography.body1.letterSpacing,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("Sign up")
                        }
                    },
                    modifier = Modifier.paddingFromBaseline(top = 32.dp)
                )
            }
        }
    }
}