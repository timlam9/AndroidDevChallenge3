package com.example.androiddevchallenge.ui.screens

import android.widget.GridView
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.SootheItem
import com.example.androiddevchallenge.bodyList
import com.example.androiddevchallenge.favorites
import com.example.androiddevchallenge.mindList
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import java.util.*

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    var search by remember { mutableStateOf("") }

    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomBar(color = MaterialTheme.colors.background) },
            floatingActionButton = { PlayFab() },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )
                TextField(
                    value = search,
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = stringResource(R.string.search),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search),
                            style = MaterialTheme.typography.body1
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface),
                    textStyle = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onSurface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    singleLine = true,
                    onValueChange = { search = it }
                )
                Text(
                    text = stringResource(R.string.favorite_collections).toUpperCase(Locale.getDefault()),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.paddingFromBaseline(top = 40.dp)
                )
                FavoriteList(soothes = favorites)
                Text(
                    text = stringResource(R.string.align_your_body).toUpperCase(Locale.getDefault()),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.paddingFromBaseline(top = 40.dp)
                )
                BodyList(soothes = bodyList)
                Text(
                    text = stringResource(R.string.align_your_mind).toUpperCase(Locale.getDefault()),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                )
                MindList(soothes = mindList)
            }
        }
    }
}

@Composable
fun FavoriteList(soothes: List<SootheItem>) {
    val collection = soothes
        .take(6)
        .withIndex()
        .groupBy { it.index / 2 }
    LazyRow {
        collection
            .map { pair ->
                item {
                    Column {
                        CardImageText(pair.value[0].value.image, pair.value[0].value.title)
                        CardImageText(pair.value[1].value.image, pair.value[1].value.title)
                    }
                }
            }
    }
}

@Composable
fun BodyList(soothes: List<SootheItem>) {
    LazyRow {
        items(soothes) { soothe ->
            Column(modifier = Modifier.padding(8.dp)) {
                CircleImageText(soothe.image, soothe.title)
            }
        }
    }
}

@Composable
fun MindList(soothes: List<SootheItem>) {
    LazyRow {
        items(soothes) { soothe ->
            Column(modifier = Modifier.padding(8.dp)) {
                CircleImageText(soothe.image, soothe.title)
            }
        }
    }
}

@Composable
fun PlayFab() {
    FloatingActionButton(
        backgroundColor = MaterialTheme.colors.primary,
        onClick = { /*TODO*/ }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_play),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onPrimary),
            contentDescription = stringResource(R.string.play),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun BottomBar(color: Color) {
    val insets = LocalWindowInsets.current
    Surface(elevation = 8.dp, color = color) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    bottom = with(LocalDensity.current) { insets.navigationBars.bottom.toDp() + 8.dp }
                )
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_home),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground),
                    contentDescription = stringResource(R.string.home),
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = stringResource(R.string.home).toUpperCase(Locale.getDefault()),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onBackground
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground),
                    contentDescription = stringResource(R.string.profile),
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = stringResource(R.string.profile).toUpperCase(Locale.getDefault()),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}

@Composable
fun CircleImageText(@DrawableRes image: Int, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            data = image,
            contentDescription = text,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.paddingFromBaseline(top = 24.dp)
        )
    }
}

@Composable
fun CardImageText(@DrawableRes image: Int, text: String) {
    Box(modifier = Modifier.padding(8.dp)) {
        Card(
            shape = MaterialTheme.shapes.small,
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp,
            modifier = Modifier.size(192.dp, 56.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoilImage(
                    data = image,
                    contentDescription = text,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(56.dp)
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun AvatarPreview() {
    MyTheme {
        Column {
            CircleImageText(image = R.drawable.inversions, text = "Inversions")
            CardImageText(image = R.drawable.short_mantras, text = "Short mantras")
        }
    }
}