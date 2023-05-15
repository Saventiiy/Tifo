package com.github.tifo.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun ErrorSnackbar(
    hostState: SnackbarHostState,
) {
    SnackbarHost(
        hostState = hostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(16.dp),
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.Black,
                                shape = RoundedCornerShape(corner = CornerSize(8.dp))
                            )
                            .padding(all = 12.dp),
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = data.message,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                },
            )
        },
    )
}

