package com.daffodyl.medialibrary.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LabeledMediaValue(value: String, label: String) {
    Text(
        text = label,
        fontSize = MaterialTheme.typography.titleLarge.fontSize
    )
    Text(
        text = value,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize
    )
    Spacer(modifier = Modifier.height(8.dp))

}