package com.SemiColon.urbanplanner.analysis

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RadarChart(
    data: Map<String, Int>,
    modifier: Modifier = Modifier,
    maxScore: Float = 100f
) {
    val textMeasurer = rememberTextMeasurer()
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface

    Canvas(modifier = modifier.fillMaxSize()) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = (size.minDimension / 2f) * 0.7f // leave room for text
        val keys = data.keys.toList()
        val numPoints = keys.size

        if (numPoints < 3) return@Canvas

        val angleStep = (2 * Math.PI / numPoints).toFloat()

        // Draw background web
        for (i in 1..5) {
            val r = radius * (i / 5f)
            val webPath = Path().apply {
                for (j in 0 until numPoints) {
                    val angle = j * angleStep - (Math.PI / 2).toFloat()
                    val x = center.x + r * cos(angle)
                    val y = center.y + r * sin(angle)
                    if (j == 0) moveTo(x, y) else lineTo(x, y)
                }
                close()
            }
            drawPath(
                path = webPath,
                color = onSurfaceColor.copy(alpha = 0.2f),
                style = Stroke(width = 1.dp.toPx())
            )
        }

        // Draw axes and labels
        for (i in 0 until numPoints) {
            val angle = i * angleStep - (Math.PI / 2).toFloat()
            val endX = center.x + radius * cos(angle)
            val endY = center.y + radius * sin(angle)
            
            drawLine(
                color = onSurfaceColor.copy(alpha = 0.2f),
                start = center,
                end = Offset(endX, endY),
                strokeWidth = 1.dp.toPx()
            )

            val labelRadius = radius * 1.2f
            val labelX = center.x + labelRadius * cos(angle)
            val labelY = center.y + labelRadius * sin(angle)

            val label = keys[i]
            val textLayoutResult = textMeasurer.measure(
                text = label,
                style = TextStyle(fontSize = 12.sp, color = onSurfaceColor)
            )

            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x = labelX - textLayoutResult.size.width / 2f,
                    y = labelY - textLayoutResult.size.height / 2f
                )
            )
        }

        // Draw data polygon
        val dataPath = Path().apply {
            for (i in 0 until numPoints) {
                val score = (data[keys[i]]?.toFloat() ?: 0f) / maxScore
                val r = radius * score
                val angle = i * angleStep - (Math.PI / 2).toFloat()
                val x = center.x + r * cos(angle)
                val y = center.y + r * sin(angle)
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }

        drawPath(
            path = dataPath,
            color = primaryColor.copy(alpha = 0.4f),
            style = Fill
        )
        drawPath(
            path = dataPath,
            color = primaryColor,
            style = Stroke(width = 2.dp.toPx())
        )
    }
}
