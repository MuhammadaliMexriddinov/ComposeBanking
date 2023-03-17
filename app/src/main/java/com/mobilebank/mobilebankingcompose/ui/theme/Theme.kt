package com.mobilebank.mobilebankingcompose.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)
private val CostumColorScheme = lightColorScheme(
    primary = costum1,
    secondary = costum2,
    tertiary = costum2
)


object Theme {
    val myMode = mutableStateOf(MyTheme.LIGTH)
}

object Typography1 {
    val typography = mutableStateOf(MyTypography.MEDIUM)
}


@Composable
fun MobileBankingComposeTheme(
    myTheme: MyTheme = Theme.myMode.value,
    typography: MyTypography = Typography1.typography.value,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when (myTheme) {
        MyTheme.LIGTH -> {
            LightColorScheme
        }
        MyTheme.DARK -> {
            DarkColorScheme
        }
        MyTheme.COSTUM -> {
            CostumColorScheme
        }
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
//                  ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = typography
        }
    }

    val typographyChange = when (typography) {
        MyTypography.SMALL -> {
            Small
        }
        MyTypography.MEDIUM -> {
            Medium
        }
        MyTypography.LARGE -> {
            Large
        }
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = typographyChange,
        content = content
    )
}

enum class MyTypography {
    SMALL,
    MEDIUM,
    LARGE
}

enum class MyTheme {
    LIGTH,
    DARK,
    COSTUM
}

