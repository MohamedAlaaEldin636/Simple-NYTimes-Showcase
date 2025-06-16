package my.ym.ui_shared.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

private val LocalExtendedColorScheme = staticCompositionLocalOf<ThemeColorSchemeUtils.ExtendedColorScheme> {
	error("You must use ThemeApp Composable for this to be auto initialized, Or provide it yourself")
}

data object ThemeApp {

	val colorScheme: ColorScheme
		@Composable
		@ReadOnlyComposable
		get() = MaterialTheme.colorScheme

	val typography: Typography
		@Composable
		@ReadOnlyComposable
		get() = MaterialTheme.typography

	val extendedColorScheme: ThemeColorSchemeUtils.ExtendedColorScheme
		@Composable
		@ReadOnlyComposable
		get() = LocalExtendedColorScheme.current

}

/**
 * @param dynamicColor available for Android 12 and above only.
 */
@Composable
fun ThemeApp(
	darkTheme: Boolean = isSystemInDarkTheme(),
	dynamicColor: Boolean = false,
	content: @Composable () -> Unit
) {
	val colorScheme = when {
		dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
			val context = LocalContext.current
			if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
		}
		darkTheme -> ThemeColorSchemeUtils.darkScheme
		else -> ThemeColorSchemeUtils.lightScheme
	}

	val extendedColorScheme = when {
		darkTheme -> ThemeColorSchemeUtils.extendedDarkScheme
		else -> ThemeColorSchemeUtils.extendedLightScheme
	}

	MaterialTheme(
		colorScheme = colorScheme,
		typography = Typography(),
		content = {
			CompositionLocalProvider(
				LocalExtendedColorScheme provides extendedColorScheme
			) {
				content()
			}
		}
	)
}
