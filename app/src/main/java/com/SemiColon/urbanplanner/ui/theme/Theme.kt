package com.SemiColon.urbanplanner.ui.theme
import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
enum class AppTheme {
    DEFAULT,
    OCEAN,
    FOREST,
    SUNSET,
    MIDNIGHT,
    INDUSTRIAL
}
private val DefaultDarkColorScheme = darkColorScheme(
    primary = UrbanPrimary,
    onPrimary = TextOnPrimary,
    primaryContainer = UrbanPrimaryVariant,
    secondary = UrbanSecondary,
    onSecondary = TextOnPrimary,
    secondaryContainer = UrbanSecondaryVariant,
    tertiary = InfoBlue,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    error = CriticalRed,
    onError = TextOnPrimary,
    outline = DarkBorder
)
private val OceanDarkColorScheme = darkColorScheme(primary = OceanPrimary, onPrimary = TextOnPrimary, primaryContainer = Color(0xFF065266), secondary = OceanSecondary, background = OceanBackground, onBackground = TextPrimary, surface = OceanSurface, onSurface = TextPrimary, error = CriticalRed, onError = TextOnPrimary)
private val ForestDarkColorScheme = darkColorScheme(primary = ForestPrimary, onPrimary = TextOnPrimary, primaryContainer = Color(0xFF047857), secondary = ForestSecondary, background = ForestBackground, onBackground = TextPrimary, surface = ForestSurface, onSurface = TextPrimary, error = CriticalRed, onError = TextOnPrimary)
private val SunsetDarkColorScheme = darkColorScheme(primary = SunsetPrimary, onPrimary = TextOnPrimary, primaryContainer = Color(0xFFEA580C), secondary = SunsetSecondary, background = SunsetBackground, onBackground = TextPrimary, surface = SunsetSurface, onSurface = TextPrimary, error = CriticalRed, onError = TextOnPrimary)
private val MidnightDarkColorScheme = darkColorScheme(primary = MidnightPrimary, onPrimary = TextOnPrimary, primaryContainer = Color(0xFF7C3AED), secondary = MidnightSecondary, background = MidnightBackground, onBackground = TextPrimary, surface = MidnightSurface, onSurface = TextPrimary, error = CriticalRed, onError = TextOnPrimary)
private val IndustrialDarkColorScheme = darkColorScheme(primary = IndustrialPrimary, onPrimary = TextOnPrimary, primaryContainer = ConstructionOrangeDark, secondary = IndustrialSecondary, background = IndustrialBackground, onBackground = TextPrimary, surface = IndustrialSurface, onSurface = TextPrimary, error = CriticalRed, onError = TextOnPrimary)
private val DefaultLightColorScheme = lightColorScheme(
    primary = UrbanPrimary,
    onPrimary = TextOnPrimary,
    primaryContainer = Color(0xFFDBEAFE),
    secondary = UrbanSecondary,
    onSecondary = TextOnPrimary,
    secondaryContainer = Color(0xFFD1FAE5),
    background = LightBackground,
    onBackground = LightTextPrimary,
    surface = LightSurface,
    onSurface = LightTextPrimary,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightTextSecondary,
    error = CriticalRed,
    onError = TextOnPrimary,
    outline = LightBorder
)
@Composable
fun UrbanPlannerTheme(
    appTheme: AppTheme = AppTheme.DEFAULT,
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> when (appTheme) {
            AppTheme.DEFAULT -> DefaultDarkColorScheme
            AppTheme.OCEAN -> OceanDarkColorScheme
            AppTheme.FOREST -> ForestDarkColorScheme
            AppTheme.SUNSET -> SunsetDarkColorScheme
            AppTheme.MIDNIGHT -> MidnightDarkColorScheme
            AppTheme.INDUSTRIAL -> IndustrialDarkColorScheme
        }
        else -> DefaultLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
object UrbanColors {
    val critical @Composable get() = CriticalRed
    val criticalDark @Composable get() = CriticalRedDark
    val construction @Composable get() = ConstructionOrange
    val constructionDark @Composable get() = ConstructionOrangeDark
    val park @Composable get() = ParkGreen
    val parkDark @Composable get() = ParkGreenDark
    val info @Composable get() = InfoBlue
    val infoDark @Composable get() = InfoBlueDark
    val densityLow @Composable get() = DensityLow
    val densityMedium @Composable get() = DensityMedium
    val densityHigh @Composable get() = DensityHigh
    val densitySevere @Composable get() = DensitySevere
    val densityCritical @Composable get() = DensityCritical
    val residential @Composable get() = ResidentialZone
    val commercial @Composable get() = CommercialZone
    val industrial @Composable get() = IndustrialZone
    val mixedUse @Composable get() = MixedUseZone
    val statusActive @Composable get() = StatusActive
    val statusWarning @Composable get() = StatusWarning
    val statusPlanning @Composable get() = StatusPlanning
    val statusCompleted @Composable get() = StatusCompleted
}
