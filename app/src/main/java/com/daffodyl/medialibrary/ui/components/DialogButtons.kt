package com.daffodyl.medialibrary.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DialogButtons(
    goBack: () -> Unit,
    save: () -> Unit
) {
    Row {
        Button(
            onClick = { goBack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Cancel")
        }
        Button(onClick = {
            save()
            goBack()
        }) {
            Text("Save")
        }
    }
}