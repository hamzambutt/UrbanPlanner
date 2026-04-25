package com.SemiColon.urbanplanner.ui.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
// ============================================
// URBAN PLANNER TYPOGRAPHY SYSTEM
// ============================================
val UrbanFont = FontFamily.Default
val Typography =
        Typography(
                displayLarge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Bold, fontSize = 57.sp, lineHeight = 64.sp, letterSpacing = (-0.25).sp),
                displayMedium = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Bold, fontSize = 45.sp, lineHeight = 52.sp, letterSpacing = 0.sp),
                displaySmall = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Bold, fontSize = 36.sp, lineHeight = 44.sp, letterSpacing = 0.sp),
                headlineLarge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.SemiBold, fontSize = 32.sp, lineHeight = 40.sp, letterSpacing = 0.sp),
                headlineMedium = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.SemiBold, fontSize = 28.sp, lineHeight = 36.sp, letterSpacing = 0.sp),
                headlineSmall = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.SemiBold, fontSize = 24.sp, lineHeight = 32.sp, letterSpacing = 0.sp),
                titleLarge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.SemiBold, fontSize = 22.sp, lineHeight = 28.sp, letterSpacing = 0.sp),
                titleMedium = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.15.sp),
                titleSmall = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp),
                bodyLarge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.5.sp),
                bodyMedium = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.25.sp),
                bodySmall = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.4.sp),
                labelLarge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp),
                labelMedium = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp),
                labelSmall = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp)
        )
object UrbanTextStyles {
    val criticalAlert = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, lineHeight = 28.sp, letterSpacing = 1.sp)
    val statusBadge = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Bold, fontSize = 10.sp, lineHeight = 12.sp, letterSpacing = 0.5.sp)
    val metricValue = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Bold, fontSize = 40.sp, lineHeight = 48.sp, letterSpacing = (-1).sp)
    val metricLabel = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp)
    val timestamp = TextStyle(fontFamily = UrbanFont, fontWeight = FontWeight.Normal, fontSize = 11.sp, lineHeight = 14.sp, letterSpacing = 0.25.sp)
    val coordinates = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Normal, fontSize = 13.sp, lineHeight = 18.sp, letterSpacing = 0.sp)
}
