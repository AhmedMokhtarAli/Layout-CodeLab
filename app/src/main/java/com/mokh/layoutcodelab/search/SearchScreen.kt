package com.mokh.layoutcodelab.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mokh.layoutcodelab.R


@Composable
fun SearchScreen(modifier: Modifier=Modifier) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())) {
        SearchBar(modifier = Modifier.padding(horizontal = 8.dp))
        HomeSection(title = "Align your body") {
            HorizontalList()
        }
        HomeSection(title = "Favorite exercises") {
            FavoriteList()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 12.dp)
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium
        )
        content()
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }
    TextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(12.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
        ), placeholder = {
            Text(text = "Search")
        }
    )
}


@Composable
fun HorizontalList(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) {
            HorizontalItem(
                modifier = Modifier,
                name = "Lotfi Labib",
                imageResId = R.drawable.lotfi
            )
        }
    }
}

@Composable
fun HorizontalItem(modifier: Modifier = Modifier, name: String, imageResId: Int) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape), contentScale = ContentScale.Crop
        )
        Text(
            text = name,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun FavoriteList(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(168.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(10) {
            FavoriteItem(
                modifier = Modifier.heightIn(80.dp),
                name = "Lotfi Labib",
                imageResId = R.drawable.lotfi
            )
        }
    }
}

@Composable
fun FavoriteItem(modifier: Modifier = Modifier, imageResId: Int, name: String) {
    Surface(
        modifier = modifier.width(225.dp),
        shape = MaterialTheme.shapes.medium,
        color = Color.Gray
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(6.dp))
            )
            Text(
                text = name,
                modifier = Modifier.padding(start = 12.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}