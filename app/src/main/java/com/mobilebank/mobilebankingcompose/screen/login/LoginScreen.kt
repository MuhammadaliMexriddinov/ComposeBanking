package com.mobilebank.mobilebankingcompose.screen.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getScreenModel
import com.mobilebank.mobilebankingcompose.screen.login.viewmodel.LoginContract
import com.mobilebank.mobilebankingcompose.screen.login.viewmodel.LoginViewModelImpl
import com.mobilebank.mobilebankingcompose.ui.theme.*
import com.mobilebank.mobilebankingcompose.utils.PhoneMaskTransformation

class LoginScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        var viewModel = getScreenModel<LoginViewModelImpl>()
        val uiState = viewModel.uiState.collectAsState().value
        LoginUi(uiState, viewModel::onEventDispatcher)

    }

    @Composable
    fun LoginUi(
        uiState: LoginContract.UiState,
        events: (LoginContract.LoginIntent) -> Unit,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))



                Text(text = "Sign in", fontSize = MaterialTheme.typography.h6.fontSize)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 80.dp, start = 27.dp, end = 35.dp)
                ) {
                    Text(
                        text = "Phone number",
//                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Start, modifier = Modifier.padding(start = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = uiState.phone,
                        visualTransformation = PhoneMaskTransformation("##-###-##-##"),
                        leadingIcon = {
                            Text(
                                text = "+998",
                                color = MaterialTheme.colors.onBackground,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )

                        },
                        onValueChange = {
                            if (it.length <= 9) {
                                events(LoginContract.LoginIntent.PhoneEnter(it))
                            }
                        },

                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = editTextColor,
                            cursorColor = MaterialTheme.colors.primaryVariant,
                            disabledLabelColor = editTextColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                        modifier = Modifier

                            .height(56.dp)
                            .width(328.dp),
                        shape = RoundedCornerShape(12.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Password", textAlign = TextAlign.Start,
//                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

                    TextField(
                        value = uiState.password,
                        onValueChange = {
                            events(LoginContract.LoginIntent.PasswordEnter(it))
                        },

                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = editTextColor,
                            cursorColor = MaterialTheme.colors.onBackground,
                            disabledLabelColor = editTextColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier
                            .height(56.dp)
                            .width(328.dp),
                        shape = RoundedCornerShape(12.dp),
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Button(
                            onClick = { Theme.myMode.value = MyTheme.LIGTH },
                            modifier = Modifier.padding(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = buttonColor,
                                disabledBackgroundColor = editTextColor
                            )
                        ) {
                            Text(text = "Ligth")
                        }

                        Button(
                            onClick = { Theme.myMode.value = MyTheme.DARK },
                            modifier = Modifier.padding(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = buttonColor,
                                disabledBackgroundColor = editTextColor
                            )
                        ) {
                            Text(text = "Dark")
                        }

                        Button(
                            onClick = { Theme.myMode.value = MyTheme.COSTUM },
                            modifier = Modifier.padding(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = buttonColor,
                                disabledBackgroundColor = editTextColor
                            )
                        ) {
                            Text(text = "Costum")
                        }
                    }


                    Row(modifier = Modifier.fillMaxWidth()) {

                        Button(
                            onClick = { Typography1.typography.value = MyTypography.SMALL },
                            modifier = Modifier.padding(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = buttonColor,
                                disabledBackgroundColor = editTextColor
                            )
                        ) {
                            Text(text = "Small")
                        }

                        Button(
                            onClick = { Typography1.typography.value = MyTypography.MEDIUM },
                            modifier = Modifier.padding(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = buttonColor,
                                disabledBackgroundColor = editTextColor
                            ),
                        ) {
                            Text(text = "Medium")
                        }

                        Button(
                            onClick = { Typography1.typography.value = MyTypography.LARGE },
                            modifier = Modifier.padding(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = buttonColor,
                                disabledBackgroundColor = editTextColor
                            )
                        ) {
                            Text(text = "Large")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(bottom = 30.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        Button(
                            onClick = {
                                events(LoginContract.LoginIntent.ClickLogin)
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = buttonColor,
                                disabledBackgroundColor = editTextColor
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(text = "Login")
                        }

                        Button(
                            onClick = {
                                events(
                                    LoginContract.LoginIntent.CreateAccount
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = buttonColor,
                                disabledBackgroundColor = editTextColor
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text(text = "SignUp")
                        }

                    }

                }
            }
            if (uiState.progress) {
                Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    @Composable
    @Preview
    fun LoginUiPreview() {
        LoginUi(LoginContract.UiState("+998", "", "", false)) {}
    }
}


