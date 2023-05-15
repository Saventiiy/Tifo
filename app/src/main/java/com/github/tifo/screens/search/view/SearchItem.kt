package com.github.tifo.screens.search.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.tifo.data.model.dto.repositories.RepositoriesItem

@Composable
internal fun SearchItem(
    repositoriesItem: RepositoriesItem,
    onItemClicked: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .height(60.dp)
            .fillMaxWidth()
            .clickable { onItemClicked.invoke(repositoriesItem.fullName) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(end = 24.dp)
                    .weight(1f)
            ) {
                Text(
                    text = repositoriesItem.fullName,
                    maxLines = 1, overflow =
                    TextOverflow.Ellipsis
                )
                if (repositoriesItem.description != null) {
                    Text(
                        text = repositoriesItem.description!!,
                        modifier = Modifier.padding(top = 10.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                if (repositoriesItem.language != null) {
                    Text(text = repositoriesItem.language!!)
                }
                Row(modifier = Modifier.padding(top = 10.dp)) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null
                    )
                    Text(
                        text = repositoriesItem.stars.toString(),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}