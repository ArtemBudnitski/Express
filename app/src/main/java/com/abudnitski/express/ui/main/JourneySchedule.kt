package com.abudnitski.express.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abudnitski.express.R
import com.abudnitski.express.presentation.main.MainScreenUiState

@Composable
fun JourneySchedule(
    mainScreenUiState: MainScreenUiState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(24.dp)) {
        Text(
            text = stringResource(R.string.main_journey_schedule_title), modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Card(
            modifier = Modifier
                .wrapContentSize(), shape = RoundedCornerShape(50f),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            if (mainScreenUiState.showDistanceCard) {
                DistanceCardInfo(mainScreenUiState)
            } else {
                DefaultInfo()
            }
        }
    }
}

