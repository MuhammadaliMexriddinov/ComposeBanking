package com.mobilebank.mobilebankingcompose.screen.verify

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getScreenModel
import com.mobilebank.mobilebankingcompose.screen.verify.viewmodel.VerifyContract
import com.mobilebank.mobilebankingcompose.screen.verify.viewmodel.impl.VerifyViewModelImpl
import com.mobilebank.mobilebankingcompose.R
import com.magneto.magotplib.Screen.ComposableType
import com.magneto.magotplib.Screen.OtpComposableOutlined
import com.mobilebank.mobilebankingcompose.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.seconds

class VerifyScreen() : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<VerifyViewModelImpl>()
        VerifyUi(
            uiState = viewModel.uiState.collectAsState().value,
            events = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun VerifyUi(
    uiState: VerifyContract.UiState,
    events: (VerifyContract.VerifyIntent) -> Unit,
) {

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(18.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "Back",
                    modifier = Modifier
                        .height(25.dp)
                        .padding(top = 3.dp)
                        .width(25.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false), // You can also change the color and radius of the ripple
                            onClick = {}
                        )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Verify", fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Spacer (modifier = Modifier.weight(1.5f))


            }

            ///


            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = "Sms Code ",
                modifier = Modifier
                    .width(176.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(40.dp))

            OtpComposableOutlined(
                widthInDp = 44.dp,
                otpComposableType = ComposableType.TYPE_SIX,
                heightInDp = 50.dp,
                backgroundColor = Color.Transparent,
                passwordToggle = false,
                focusColor = Color.Black,
                unfocusColor = Color.DarkGray,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                ,
                cornerRadius = 8.dp,

            )
            {
                events(VerifyContract.VerifyIntent.CodeEnter(it))
                events(VerifyContract.VerifyIntent.ClickVerify)
            }

            Spacer(modifier = Modifier.height(30.dp))
            var ticks by remember { mutableStateOf(60) }
            LaunchedEffect(Unit) {
                while (true) {
                    delay(1.seconds)
                    if (ticks > 0) {
                        ticks--
                    } else {
                        ticks = 0
                    }
                }
            }

            if (ticks != 0) {
                Text(text = "Time: $ticks")
            } else {
                ticks = 0

                Text(text = "Resend Code", color = Color.Red, modifier = Modifier.clickable {
                    events(VerifyContract.VerifyIntent.ClickResend)
                    ticks = 60
                })

            }
            Spacer(modifier = Modifier.height(24.dp))


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
                    androidx.compose.material3.Text(text = "Ligth")
                }

                Button(
                    onClick = { Theme.myMode.value = MyTheme.DARK },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonColor,
                        disabledBackgroundColor = editTextColor
                    )
                ) {
                    androidx.compose.material3.Text(text = "Dark")
                }

                Button(
                    onClick = { Theme.myMode.value = MyTheme.COSTUM },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonColor,
                        disabledBackgroundColor = editTextColor
                    )
                ) {
                    androidx.compose.material3.Text(text = "Costum")
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
                    androidx.compose.material3.Text(text = "Small")
                }

                Button(
                    onClick = { Typography1.typography.value = MyTypography.MEDIUM },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonColor,
                        disabledBackgroundColor = editTextColor
                    ),
                ) {
                    androidx.compose.material3.Text(text = "Medium")
                }

                Button(
                    onClick = { Typography1.typography.value = MyTypography.LARGE },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonColor,
                        disabledBackgroundColor = editTextColor
                    )
                ) {
                    androidx.compose.material3.Text(text = "Large")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Spacer(modifier = Modifier.height(50.dp))
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 30.dp),
                contentAlignment = Alignment.BottomCenter
            ) {


                Button(
                    onClick = {
                        events(VerifyContract.VerifyIntent.ClickVerify)
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        backgroundColor = buttonColor, disabledBackgroundColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(6.dp), modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(48.dp)
                ) {
                    Text(text = "Verify")
                }

            }
            if (uiState.progress) {
                Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_LONG).show()
            }

            //


        }
    }
}

@Composable
@OptIn(ExperimentalComposeApi::class)
fun rememberCountdownTimerState(
    initialMillis: Long,
    step: Long = 1000,
): MutableState<Long> {
    val timeLeft = remember { mutableStateOf(initialMillis) }
    LaunchedEffect(initialMillis, step) {
        val startTime = withFrameMillis { it }
        while (isActive && timeLeft.value > 0) {
            val duration = withFrameMillis { time ->
                (time - startTime).coerceAtLeast(0)
            }
            timeLeft.value = (initialMillis - duration).coerceAtLeast(0)
            delay(step.coerceAtMost(timeLeft.value))
        }
    }
    return timeLeft
}

@Preview(showBackground = true)
@Composable
fun VerifyUiPreview() {
    VerifyUi(VerifyContract.UiState("", "", resendTime = 0, false)) {

    }
}

