package my.ym.ui_shared.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

// Used https://material-foundation.github.io/material-theme-builder/ to help in generating colors.
data object ThemeColorSchemeUtils {

	internal val lightScheme = lightColorScheme(
		primary = Colors.primaryLight,
		onPrimary = Colors.onPrimaryLight,
		primaryContainer = Colors.primaryContainerLight,
		onPrimaryContainer = Colors.onPrimaryContainerLight,
		inversePrimary = Colors.inversePrimaryLight,
		secondary = Colors.secondaryLight,
		onSecondary = Colors.onSecondaryLight,
		secondaryContainer = Colors.secondaryContainerLight,
		onSecondaryContainer = Colors.onSecondaryContainerLight,
		tertiary = Colors.tertiaryLight,
		onTertiary = Colors.onTertiaryLight,
		tertiaryContainer = Colors.tertiaryContainerLight,
		onTertiaryContainer = Colors.onTertiaryContainerLight,
		background = Colors.backgroundLight,
		onBackground = Colors.onBackgroundLight,
		surface = Colors.surfaceLight,
		onSurface = Colors.onSurfaceLight,
		surfaceVariant = Colors.surfaceVariantLight,
		onSurfaceVariant = Colors.onSurfaceVariantLight,
		surfaceTint = Colors.primaryLight,
		inverseSurface = Colors.inverseSurfaceLight,
		inverseOnSurface = Colors.inverseOnSurfaceLight,
		error = Colors.errorLight,
		onError = Colors.onErrorLight,
		errorContainer = Colors.errorContainerLight,
		onErrorContainer = Colors.onErrorContainerLight,
		outline = Colors.outlineLight,
		outlineVariant = Colors.outlineVariantLight,
		scrim = Colors.scrimLight,
		surfaceBright = Colors.surfaceBrightLight,
		surfaceContainer = Colors.surfaceContainerLight,
		surfaceContainerHigh = Colors.surfaceContainerHighLight,
		surfaceContainerHighest = Colors.surfaceContainerHighestLight,
		surfaceContainerLow = Colors.surfaceContainerLowLight,
		surfaceContainerLowest = Colors.surfaceContainerLowestLight,
		surfaceDim = Colors.surfaceDimLight
	)

	internal val darkScheme = darkColorScheme(
		primary = Colors.primaryDark,
		onPrimary = Colors.onPrimaryDark,
		primaryContainer = Colors.primaryContainerDark,
		onPrimaryContainer = Colors.onPrimaryContainerDark,
		inversePrimary = Colors.inversePrimaryDark,
		secondary = Colors.secondaryDark,
		onSecondary = Colors.onSecondaryDark,
		secondaryContainer = Colors.secondaryContainerDark,
		onSecondaryContainer = Colors.onSecondaryContainerDark,
		tertiary = Colors.tertiaryDark,
		onTertiary = Colors.onTertiaryDark,
		tertiaryContainer = Colors.tertiaryContainerDark,
		onTertiaryContainer = Colors.onTertiaryContainerDark,
		background = Colors.backgroundDark,
		onBackground = Colors.onBackgroundDark,
		surface = Colors.surfaceDark,
		onSurface = Colors.onSurfaceDark,
		surfaceVariant = Colors.surfaceVariantDark,
		onSurfaceVariant = Colors.onSurfaceVariantDark,
		surfaceTint = Colors.primaryDark,
		inverseSurface = Colors.inverseSurfaceDark,
		inverseOnSurface = Colors.inverseOnSurfaceDark,
		error = Colors.errorDark,
		onError = Colors.onErrorDark,
		errorContainer = Colors.errorContainerDark,
		onErrorContainer = Colors.onErrorContainerDark,
		outline = Colors.outlineDark,
		outlineVariant = Colors.outlineVariantDark,
		scrim = Colors.scrimDark,
		surfaceBright = Colors.surfaceBrightDark,
		surfaceContainer = Colors.surfaceContainerDark,
		surfaceContainerHigh = Colors.surfaceContainerHighDark,
		surfaceContainerHighest = Colors.surfaceContainerHighestDark,
		surfaceContainerLow = Colors.surfaceContainerLowDark,
		surfaceContainerLowest = Colors.surfaceContainerLowestDark,
		surfaceDim = Colors.surfaceDimDark
	)

	internal val extendedLightScheme = ExtendedColorScheme(
		success = ColorFamily(
			color = Color(0xFF2C7B38),
			onColor = Color(0xFFFFFFFF),
			colorContainer = Color(0xFFB2F1B2),
			onColorContainer = Color(0xFFB2F1B2),
		),
		warning = ColorFamily(
			color = Color(0xFF6D5C00),
			onColor = Color(0xFFFFFFFF),
			colorContainer = Color(0xFFFFF0A3),
			onColorContainer = Color(0xFF211B00),
		),
	)

	internal val extendedDarkScheme = ExtendedColorScheme(
		success = ColorFamily(
			color = Color(0xFF8FD894),
			onColor = Color(0xFF003914),
			colorContainer = Color(0xFF135128),
			onColorContainer = Color(0xFFA9F5AE),
		),
		warning = ColorFamily(
			color = Color(0xFFDDC600), 
			onColor = Color(0xFF393000),
			colorContainer = Color(0xFF534600),
			onColorContainer = Color(0xFFFFF0A3),
		),
	)

	@Immutable
	data class ExtendedColorScheme(
		val success: ColorFamily,
		val warning: ColorFamily,
	)

	@Immutable
	data class ColorFamily(
		val color: Color,
		val onColor: Color,
		val colorContainer: Color,
		val onColorContainer: Color
	)
	
	private data object Colors {
		
		val primaryLight = Color(0xFF64558F)
		val onPrimaryLight = Color(0xFFFFFFFF)
		val primaryContainerLight = Color(0xFFE8DDFF)
		val onPrimaryContainerLight = Color(0xFF4C3E75)
		val secondaryLight = Color(0xFF65558F)
		val onSecondaryLight = Color(0xFFFFFFFF)
		val secondaryContainerLight = Color(0xFFE9DDFF)
		val onSecondaryContainerLight = Color(0xFF4D3D75)
		val tertiaryLight = Color(0xFF8B4A61)
		val onTertiaryLight = Color(0xFFFFFFFF)
		val tertiaryContainerLight = Color(0xFFFFD9E3)
		val onTertiaryContainerLight = Color(0xFF6F334A)
		val errorLight = Color(0xFFBA1A1A)
		val onErrorLight = Color(0xFFFFFFFF)
		val errorContainerLight = Color(0xFFFFDAD6)
		val onErrorContainerLight = Color(0xFF93000A)
		val backgroundLight = Color(0xFFFDF7FF)
		val onBackgroundLight = Color(0xFF1D1B20)
		val surfaceLight = Color(0xFFFDF7FF)
		val onSurfaceLight = Color(0xFF1D1B20)
		val surfaceVariantLight = Color(0xFFE6E0EC)
		val onSurfaceVariantLight = Color(0xFF49454E)
		val outlineLight = Color(0xFF7A757F)
		val outlineVariantLight = Color(0xFFCAC4CF)
		val scrimLight = Color(0xFF000000)
		val inverseSurfaceLight = Color(0xFF322F35)
		val inverseOnSurfaceLight = Color(0xFFF5EFF7)
		val inversePrimaryLight = Color(0xFFCEBDFE)
		val surfaceDimLight = Color(0xFFDED8E0)
		val surfaceBrightLight = Color(0xFFFDF7FF)
		val surfaceContainerLowestLight = Color(0xFFFFFFFF)
		val surfaceContainerLowLight = Color(0xFFF8F2FA)
		val surfaceContainerLight = Color(0xFFF2ECF4)
		val surfaceContainerHighLight = Color(0xFFECE6EE)
		val surfaceContainerHighestLight = Color(0xFFE6E1E9)

		val primaryDark = Color(0xFFCEBDFE)
		val onPrimaryDark = Color(0xFF35275D)
		val primaryContainerDark = Color(0xFF4C3E75)
		val onPrimaryContainerDark = Color(0xFFE8DDFF)
		val secondaryDark = Color(0xFFD0BCFE)
		val onSecondaryDark = Color(0xFF36265D)
		val secondaryContainerDark = Color(0xFF4D3D75)
		val onSecondaryContainerDark = Color(0xFFE9DDFF)
		val tertiaryDark = Color(0xFFFFB0CA)
		val onTertiaryDark = Color(0xFF541D33)
		val tertiaryContainerDark = Color(0xFF6F334A)
		val onTertiaryContainerDark = Color(0xFFFFD9E3)
		val errorDark = Color(0xFFFFB4AB)
		val onErrorDark = Color(0xFF690005)
		val errorContainerDark = Color(0xFF93000A)
		val onErrorContainerDark = Color(0xFFFFDAD6)
		val backgroundDark = Color(0xFF141218)
		val onBackgroundDark = Color(0xFFE6E1E9)
		val surfaceDark = Color(0xFF141218)
		val onSurfaceDark = Color(0xFFE6E1E9)
		val surfaceVariantDark = Color(0xFF49454E)
		val onSurfaceVariantDark = Color(0xFFCAC4CF)
		val outlineDark = Color(0xFF948F99)
		val outlineVariantDark = Color(0xFF49454E)
		val scrimDark = Color(0xFF000000)
		val inverseSurfaceDark = Color(0xFFE6E1E9)
		val inverseOnSurfaceDark = Color(0xFF322F35)
		val inversePrimaryDark = Color(0xFF64558F)
		val surfaceDimDark = Color(0xFF141218)
		val surfaceBrightDark = Color(0xFF3B383E)
		val surfaceContainerLowestDark = Color(0xFF0F0D13)
		val surfaceContainerLowDark = Color(0xFF1D1B20)
		val surfaceContainerDark = Color(0xFF211F24)
		val surfaceContainerHighDark = Color(0xFF2B292F)
		val surfaceContainerHighestDark = Color(0xFF36343A)

	}

	/*private val primaryLightMediumContrast = Color(0xFF3B2D64)
	private val onPrimaryLightMediumContrast = Color(0xFFFFFFFF)
	private val primaryContainerLightMediumContrast = Color(0xFF73649F)
	private val onPrimaryContainerLightMediumContrast = Color(0xFFFFFFFF)
	private val secondaryLightMediumContrast = Color(0xFF3C2C63)
	private val onSecondaryLightMediumContrast = Color(0xFFFFFFFF)
	private val secondaryContainerLightMediumContrast = Color(0xFF74649E)
	private val onSecondaryContainerLightMediumContrast = Color(0xFFFFFFFF)
	private val tertiaryLightMediumContrast = Color(0xFF5B2239)
	private val onTertiaryLightMediumContrast = Color(0xFFFFFFFF)
	private val tertiaryContainerLightMediumContrast = Color(0xFF9C5870)
	private val onTertiaryContainerLightMediumContrast = Color(0xFFFFFFFF)
	private val errorLightMediumContrast = Color(0xFF740006)
	private val onErrorLightMediumContrast = Color(0xFFFFFFFF)
	private val errorContainerLightMediumContrast = Color(0xFFCF2C27)
	private val onErrorContainerLightMediumContrast = Color(0xFFFFFFFF)
	private val backgroundLightMediumContrast = Color(0xFFFDF7FF)
	private val onBackgroundLightMediumContrast = Color(0xFF1D1B20)
	private val surfaceLightMediumContrast = Color(0xFFFDF7FF)
	private val onSurfaceLightMediumContrast = Color(0xFF121016)
	private val surfaceVariantLightMediumContrast = Color(0xFFE6E0EC)
	private val onSurfaceVariantLightMediumContrast = Color(0xFF38353D)
	private val outlineLightMediumContrast = Color(0xFF54515A)
	private val outlineVariantLightMediumContrast = Color(0xFF6F6B75)
	private val scrimLightMediumContrast = Color(0xFF000000)
	private val inverseSurfaceLightMediumContrast = Color(0xFF322F35)
	private val inverseOnSurfaceLightMediumContrast = Color(0xFFF5EFF7)
	private val inversePrimaryLightMediumContrast = Color(0xFFCEBDFE)
	private val surfaceDimLightMediumContrast = Color(0xFFCAC5CC)
	private val surfaceBrightLightMediumContrast = Color(0xFFFDF7FF)
	private val surfaceContainerLowestLightMediumContrast = Color(0xFFFFFFFF)
	private val surfaceContainerLowLightMediumContrast = Color(0xFFF8F2FA)
	private val surfaceContainerLightMediumContrast = Color(0xFFECE6EE)
	private val surfaceContainerHighLightMediumContrast = Color(0xFFE0DBE3)
	private val surfaceContainerHighestLightMediumContrast = Color(0xFFD5D0D8)

	private val primaryLightHighContrast = Color(0xFF312259)
	private val onPrimaryLightHighContrast = Color(0xFFFFFFFF)
	private val primaryContainerLightHighContrast = Color(0xFF4F4078)
	private val onPrimaryContainerLightHighContrast = Color(0xFFFFFFFF)
	private val secondaryLightHighContrast = Color(0xFF322258)
	private val onSecondaryLightHighContrast = Color(0xFFFFFFFF)
	private val secondaryContainerLightHighContrast = Color(0xFF504078)
	private val onSecondaryContainerLightHighContrast = Color(0xFFFFFFFF)
	private val tertiaryLightHighContrast = Color(0xFF4F182F)
	private val onTertiaryLightHighContrast = Color(0xFFFFFFFF)
	private val tertiaryContainerLightHighContrast = Color(0xFF72354C)
	private val onTertiaryContainerLightHighContrast = Color(0xFFFFFFFF)
	private val errorLightHighContrast = Color(0xFF600004)
	private val onErrorLightHighContrast = Color(0xFFFFFFFF)
	private val errorContainerLightHighContrast = Color(0xFF98000A)
	private val onErrorContainerLightHighContrast = Color(0xFFFFFFFF)
	private val backgroundLightHighContrast = Color(0xFFFDF7FF)
	private val onBackgroundLightHighContrast = Color(0xFF1D1B20)
	private val surfaceLightHighContrast = Color(0xFFFDF7FF)
	private val onSurfaceLightHighContrast = Color(0xFF000000)
	private val surfaceVariantLightHighContrast = Color(0xFFE6E0EC)
	private val onSurfaceVariantLightHighContrast = Color(0xFF000000)
	private val outlineLightHighContrast = Color(0xFF2D2B33)
	private val outlineVariantLightHighContrast = Color(0xFF4B4851)
	private val scrimLightHighContrast = Color(0xFF000000)
	private val inverseSurfaceLightHighContrast = Color(0xFF322F35)
	private val inverseOnSurfaceLightHighContrast = Color(0xFFFFFFFF)
	private val inversePrimaryLightHighContrast = Color(0xFFCEBDFE)
	private val surfaceDimLightHighContrast = Color(0xFFBCB7BF)
	private val surfaceBrightLightHighContrast = Color(0xFFFDF7FF)
	private val surfaceContainerLowestLightHighContrast = Color(0xFFFFFFFF)
	private val surfaceContainerLowLightHighContrast = Color(0xFFF5EFF7)
	private val surfaceContainerLightHighContrast = Color(0xFFE6E1E9)
	private val surfaceContainerHighLightHighContrast = Color(0xFFD8D2DA)
	private val surfaceContainerHighestLightHighContrast = Color(0xFFCAC5CC)



	private val primaryDarkMediumContrast = Color(0xFFE3D6FF)
	private val onPrimaryDarkMediumContrast = Color(0xFF2A1B52)
	private val primaryContainerDarkMediumContrast = Color(0xFF9887C5)
	private val onPrimaryContainerDarkMediumContrast = Color(0xFF000000)
	private val secondaryDarkMediumContrast = Color(0xFFE3D6FF)
	private val onSecondaryDarkMediumContrast = Color(0xFF2B1B51)
	private val secondaryContainerDarkMediumContrast = Color(0xFF9987C5)
	private val onSecondaryContainerDarkMediumContrast = Color(0xFF000000)
	private val tertiaryDarkMediumContrast = Color(0xFFFFD0DD)
	private val onTertiaryDarkMediumContrast = Color(0xFF471228)
	private val tertiaryContainerDarkMediumContrast = Color(0xFFC57B94)
	private val onTertiaryContainerDarkMediumContrast = Color(0xFF000000)
	private val errorDarkMediumContrast = Color(0xFFFFD2CC)
	private val onErrorDarkMediumContrast = Color(0xFF540003)
	private val errorContainerDarkMediumContrast = Color(0xFFFF5449)
	private val onErrorContainerDarkMediumContrast = Color(0xFF000000)
	private val backgroundDarkMediumContrast = Color(0xFF141218)
	private val onBackgroundDarkMediumContrast = Color(0xFFE6E1E9)
	private val surfaceDarkMediumContrast = Color(0xFF141218)
	private val onSurfaceDarkMediumContrast = Color(0xFFFFFFFF)
	private val surfaceVariantDarkMediumContrast = Color(0xFF49454E)
	private val onSurfaceVariantDarkMediumContrast = Color(0xFFE0DAE5)
	private val outlineDarkMediumContrast = Color(0xFFB5B0BB)
	private val outlineVariantDarkMediumContrast = Color(0xFF938E99)
	private val scrimDarkMediumContrast = Color(0xFF000000)
	private val inverseSurfaceDarkMediumContrast = Color(0xFFE6E1E9)
	private val inverseOnSurfaceDarkMediumContrast = Color(0xFF2B292F)
	private val inversePrimaryDarkMediumContrast = Color(0xFF4D3F77)
	private val surfaceDimDarkMediumContrast = Color(0xFF141218)
	private val surfaceBrightDarkMediumContrast = Color(0xFF46434A)
	private val surfaceContainerLowestDarkMediumContrast = Color(0xFF08070B)
	private val surfaceContainerLowDarkMediumContrast = Color(0xFF1F1D22)
	private val surfaceContainerDarkMediumContrast = Color(0xFF29272D)
	private val surfaceContainerHighDarkMediumContrast = Color(0xFF343138)
	private val surfaceContainerHighestDarkMediumContrast = Color(0xFF3F3D43)

	private val primaryDarkHighContrast = Color(0xFFF5EDFF)
	private val onPrimaryDarkHighContrast = Color(0xFF000000)
	private val primaryContainerDarkHighContrast = Color(0xFFCBB9FA)
	private val onPrimaryContainerDarkHighContrast = Color(0xFF0F0033)
	private val secondaryDarkHighContrast = Color(0xFFF5EDFF)
	private val onSecondaryDarkHighContrast = Color(0xFF000000)
	private val secondaryContainerDarkHighContrast = Color(0xFFCCB9FA)
	private val onSecondaryContainerDarkHighContrast = Color(0xFF100032)
	private val tertiaryDarkHighContrast = Color(0xFFFFEBEF)
	private val onTertiaryDarkHighContrast = Color(0xFF000000)
	private val tertiaryContainerDarkHighContrast = Color(0xFFFDABC6)
	private val onTertiaryContainerDarkHighContrast = Color(0xFF20000D)
	private val errorDarkHighContrast = Color(0xFFFFECE9)
	private val onErrorDarkHighContrast = Color(0xFF000000)
	private val errorContainerDarkHighContrast = Color(0xFFFFAEA4)
	private val onErrorContainerDarkHighContrast = Color(0xFF220001)
	private val backgroundDarkHighContrast = Color(0xFF141218)
	private val onBackgroundDarkHighContrast = Color(0xFFE6E1E9)
	private val surfaceDarkHighContrast = Color(0xFF141218)
	private val onSurfaceDarkHighContrast = Color(0xFFFFFFFF)
	private val surfaceVariantDarkHighContrast = Color(0xFF49454E)
	private val onSurfaceVariantDarkHighContrast = Color(0xFFFFFFFF)
	private val outlineDarkHighContrast = Color(0xFFF4EEF9)
	private val outlineVariantDarkHighContrast = Color(0xFFC6C0CB)
	private val scrimDarkHighContrast = Color(0xFF000000)
	private val inverseSurfaceDarkHighContrast = Color(0xFFE6E1E9)
	private val inverseOnSurfaceDarkHighContrast = Color(0xFF000000)
	private val inversePrimaryDarkHighContrast = Color(0xFF4D3F77)
	private val surfaceDimDarkHighContrast = Color(0xFF141218)
	private val surfaceBrightDarkHighContrast = Color(0xFF524F55)
	private val surfaceContainerLowestDarkHighContrast = Color(0xFF000000)
	private val surfaceContainerLowDarkHighContrast = Color(0xFF211F24)
	private val surfaceContainerDarkHighContrast = Color(0xFF322F35)
	private val surfaceContainerHighDarkHighContrast = Color(0xFF3D3A41)
	private val surfaceContainerHighestDarkHighContrast = Color(0xFF48464C)*/

}
