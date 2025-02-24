package com.example.myapplication.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.myapplication.domain.model.Movie
import com.example.myapplication.presentation.Dimens.MediumPadding1
import com.example.myapplication.R
import com.example.myapplication.presentation.common.ArticlesList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(movie: LazyPagingItems<Movie>, navigate: (Movie) -> Unit) {
    val titles by remember {
        derivedStateOf {
            if (movie.itemCount > 10) {
                movie.itemSnapshotList.items
                    .slice(IntRange(0, 9))
                    .joinToString(separator = "\uD83d\uDFE5") { it.title }
            } else {
                ""
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(top = MediumPadding1).statusBarsPadding()) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = titles,
            modifier = Modifier.fillMaxWidth().padding(start = MediumPadding1).basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            movie = movie,
            onClick = { resultItem ->
                navigate(resultItem) // Передаем весь объект resultItem в navigate
            }
        )
    }
}
