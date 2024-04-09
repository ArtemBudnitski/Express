package com.abudnitski.express.ui.main

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abudnitski.express.presentation.main.MainScreenUiState
import com.abudnitski.express.presentation.main.MainScreenViewModel
import com.abudnitski.express.ui.common.Alert


@Composable
fun MainScreen(
    onClickScreenFrom: () -> Unit,
    onClickScreenTo: () -> Unit,
) {
    val viewModel = hiltViewModel<MainScreenViewModel>()
    val mainScreenUiState = viewModel.uiState.collectAsState().value
    if (mainScreenUiState.isDataLoading) {
        LoadingIndicator()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Background()
            Content(mainScreenUiState, onClickScreenFrom, onClickScreenTo)
            if (mainScreenUiState.isDataError) {
                Error(mainScreenUiState)
            }
        }
    }
}

@Composable
private fun LoadingIndicator() {
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
private fun Content(
    mainScreenUiState: MainScreenUiState,
    onClickScreenFrom: () -> Unit,
    onClickScreenTo: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.weight(0.6f)) {
            JourneyForm(
                mainScreenUiState = mainScreenUiState,
                onClickScreenFrom = onClickScreenFrom,
                onClickScreenTo = onClickScreenTo,
                onButtonClick = { mainScreenUiState.onSearchClick() },
            )
        }
        Box(modifier = Modifier.weight(0.4f)) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .align(Alignment.BottomCenter)
            ) {
                JourneySchedule(
                    mainScreenUiState = mainScreenUiState,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
private fun Error(
    mainScreenUiState: MainScreenUiState,
) {
    val activity = (LocalContext.current as? Activity)
    Alert(
        onDismissRequest = { /* Do nothing when dismissed */ },
        onDismissRequestForExit = { activity?.finish() },
        onConfirmation = { mainScreenUiState.tryAgainButton() })
}
