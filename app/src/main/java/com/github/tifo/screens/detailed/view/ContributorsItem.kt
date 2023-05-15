package com.github.tifo.screens.detailed.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.tifo.data.model.dto.contributors.Contributors

@Composable
internal fun ContributorsItem(contributors: Contributors) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = contributors.login, modifier = Modifier.padding(end = 24.dp))
        Spacer(modifier = Modifier.weight(1f))
        AsyncImage(
            model = contributors.avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp),
            contentScale = ContentScale.Crop,
        )
    }
}