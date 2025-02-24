package com.example.myapplication.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.myapplication.domain.model.Movie
import com.example.myapplication.presentation.Dimens.ExtraSmallPadding
import com.example.myapplication.presentation.Dimens.MediumPadding1

@Composable
fun ArticlesList (
    modifier: Modifier = Modifier,
    movie: LazyPagingItems<Movie>,
    onClick: (Movie) -> Unit
){
val handlePagingResult = handlePagingResult(movie = movie)
    if (handlePagingResult){
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding)
        ){
            items(count = movie.itemCount){
                movie[it]?.let{
                    MovieCard(movie = it, onClick = {onClick(it)})
                }
            }
        }
    }

}
@Composable
fun handlePagingResult(
    movie: LazyPagingItems<Movie>,
):Boolean{
    val loadState  = movie.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when{
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error !=null -> {
            EmptyScreen()
            false
        }else ->{
            true
        }
    }
}


@Composable
private fun ShimmerEffect(){
    Column (verticalArrangement = Arrangement.spacedBy(MediumPadding1)){
        repeat(10){

        }
    }
}