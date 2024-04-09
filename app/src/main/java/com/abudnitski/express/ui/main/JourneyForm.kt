package com.abudnitski.express.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abudnitski.express.R
import com.abudnitski.express.presentation.main.MainScreenUiState

@Composable
fun JourneyForm(
    modifier: Modifier = Modifier,
    mainScreenUiState: MainScreenUiState,
    onClickScreenFrom: () -> Unit,
    onClickScreenTo: () -> Unit,
    onButtonClick: () -> Unit,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(start = 28.dp, top = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = mainScreenUiState.dateTitle,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.main_journey_title),
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(start = 28.dp)
        )
        Text(
            text = stringResource(R.string.main_journey_subtitle),
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(start = 28.dp)
        )
        Card(
            modifier = modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .wrapContentSize(),
            shape = RoundedCornerShape(50f),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .wrapContentHeight()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(50f),
                    onClick = { onClickScreenFrom() }) {
                    Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
                        Text(
                            text = stringResource(R.string.main_journey_label_start),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                        Text(
                            text = mainScreenUiState.startPoint?.name ?: stringResource(R.string.main_journey_hint_start),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp

                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(50f),
                    onClick = { onClickScreenTo() }) {
                    Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
                        Text(
                            text = stringResource(R.string.main_journey_label_end),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                        Text(
                            text = mainScreenUiState.endPoint?.name ?: stringResource(R.string.main_journey_hint_end),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }
                }
                Button(
                    enabled = mainScreenUiState.isSearchEnabled,
                    onClick = { onButtonClick() },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(40f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .height(55.dp),
                ) {
                    Text(
                        text = stringResource(R.string.main_journey_button),
                        fontSize = 16.sp,
                        color = if (mainScreenUiState.isSearchEnabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}
