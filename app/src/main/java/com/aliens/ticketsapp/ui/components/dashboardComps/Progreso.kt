package com.aliens.ticketsapp.ui.components.dashboardComps

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.ui.screens.ticket.TicketViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay

@Composable
fun DeterminedCircularProgress(progress: Float) {
    val percentage: Int = (progress * 100).toInt()
    Box(contentAlignment = Alignment.Center) {
        Text(text = "$percentage%", style = MaterialTheme.typography.body2)
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.size(80.dp)
        )
    }
}

@Composable
fun Progreso(
    size: Int,
    finalizados: Int
) {
    var progress by rememberSaveable { mutableStateOf(0.0f) }
    var porcentaje = 0f
    if (size != 0) {
        porcentaje = (finalizados * 100 / size).toFloat()
        progress = porcentaje / 100f
    }


    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        DeterminedCircularProgress(progress = animatedProgress)
        Text(
            text = "Progreso",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
    }
}