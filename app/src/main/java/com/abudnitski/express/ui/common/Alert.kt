package com.abudnitski.express.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.abudnitski.express.R

@Composable
fun Alert(
    onDismissRequest: () -> Unit,
    onDismissRequestForExit: () -> Unit,
    onConfirmation: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                painterResource(id = R.drawable.ic_user),
                contentDescription = stringResource(R.string.alert_icon_content_description),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        title = {
            Text(text = stringResource(R.string.alter_title), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = stringResource(R.string.alert_message), textAlign = TextAlign.Center)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = stringResource(R.string.alert_button_positive))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequestForExit()
                }
            ) {
                Text(text = stringResource(R.string.alert_button_negative))
            }
        }
    )
}
