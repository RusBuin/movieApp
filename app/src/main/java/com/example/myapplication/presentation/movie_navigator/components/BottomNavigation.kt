package com.example.myapplication.presentation.movie_navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.presentation.Dimens.ExtraSmallPadding
import com.example.myapplication.presentation.Dimens.IconSize

fun BottomNavigation(items:List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ){
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column ( horizontalAlignment = CenterHorizontally ){
                        Icon(painter = painterResource(id = item.icon), contentDescription = null, modifier = Modifier.size(IconSize))
                    Spacer(modifier = Modifier.height(ExtraSmallPadding))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                )

            )
        }
    }
}



data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
    )


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigation



