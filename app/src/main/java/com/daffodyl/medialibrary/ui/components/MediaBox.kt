package com.daffodyl.medialibrary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MediaBox(modifier: Modifier, title: String, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = RoundedCornerShape(8.dp)
            )
            .wrapContentSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
        )
    }
}