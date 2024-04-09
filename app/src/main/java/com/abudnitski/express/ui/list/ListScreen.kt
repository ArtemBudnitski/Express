package com.abudnitski.express.ui.list

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abudnitski.express.R
import com.abudnitski.express.presentation.list.ListScreenUiState
import com.abudnitski.express.presentation.list.ListScreenViewModel
import com.abudnitski.express.ui.common.Alert

@Composable
fun ListScreen(onClick: (Int) -> Unit, onBackClick: () -> Unit) {
    val viewModel = hiltViewModel<ListScreenViewModel>()
    val listUiState = viewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onBackClick = { onBackClick() },
            searchText = listUiState.searchText,
            onSearchTextChange = { viewModel.setSearchText(it) }
        )

        if (listUiState.isDataLoading) {
            LoadingIndicator()
        } else {
            Content(listUiState, onClick)
        }

        if (listUiState.isDataError) {
            Error(listUiState)
        }
    }
}

@Composable
private fun TopAppSearchBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    Row(
        modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick() }, Modifier.weight(0.3f)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_left_arrow),
                contentDescription = stringResource(R.string.generic_back),
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
        TextField(
            value = searchText,
            onValueChange = { onSearchTextChange(it) },
            placeholder = { Text(text = stringResource(R.string.list_search), fontWeight = FontWeight.Normal) },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = modifier.weight(0.1f))
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
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}


@Composable
private fun Content(
    listUiState: ListScreenUiState,
    onClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(listUiState.filteredStations) {
            StationItem(station = it, onClick = { onClick(it.id) })
        }
    }
}

@Composable
private fun Error(
    listUiState: ListScreenUiState,
) {
    val activity = (LocalContext.current as? Activity)
    Alert(
        onDismissRequest = { /* Do nothing when dismissed */ },
        onDismissRequestForExit = { activity?.finish() },
        onConfirmation = { listUiState.tryAgainButton() })
}
