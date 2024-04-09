package com.abudnitski.express.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abudnitski.express.R

@Composable
fun Background() {
    Box(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                shape = RoundedCornerShape(bottomStart = 1f, bottomEnd = 1f, topStart = 50f, topEnd = 50f)
            ) {
                Spacer(modifier = Modifier.weight(0.5f))
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_shape),
            contentDescription = "",
            modifier = Modifier.align(Alignment.TopEnd),
            tint = (MaterialTheme.colorScheme.primaryContainer).copy(alpha = 0.1f),
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_shape),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(400.dp)
                .padding(top = 120.dp, end = 160.dp),
            tint = (MaterialTheme.colorScheme.primaryContainer).copy(alpha = 0.1f),
        )

    }
}
