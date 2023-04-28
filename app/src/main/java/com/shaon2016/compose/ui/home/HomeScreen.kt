package com.shaon2016.compose.ui.home

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shaon2016.compose.domain.entity.Product
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
internal fun HomeScreen(
    onNavigationRequested: (navigationEffect: HomeContract.Effect.Navigation) -> Unit
) {
    val vm: HomeViewModel = hiltViewModel()
    val state = vm.state.value

    LaunchedEffect("Home") {
        vm.effect.onEach { effect ->
            when (effect) {
                is HomeContract.Effect.Navigation -> onNavigationRequested(effect)
            }
        }.collect()
    }

    HomeContent(
        state = state,
        onEventSent = { vm.setEvent(it) }
    )
}

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
internal fun HomeContent(state: HomeContract.State, onEventSent: (HomeContract.Event) -> Unit) {
    Scaffold(
        topBar = {
            ToolbarContent()
        }
    ) {
        when (state.dataState) {
            DataState.INITIAL -> {}
            DataState.SUCCESS -> {
                ContentBody(
                    products = state.products,
                    onEventSent = onEventSent
                )
            }

            DataState.FAIL -> {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Fail")
                }
            }
        }

        if (state.isLoading) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Loading")
            }
        }
    }
}

@Composable
private fun ToolbarContent() {
    TopAppBar {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Home"
            )
        }
    }
}

@VisibleForTesting
@Composable
internal fun ContentBody(
    products: List<Product>,
    onEventSent: (HomeContract.Event) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(products.size) {
            Column(modifier = Modifier
                .clickable {
                    onEventSent(HomeContract.Event.SelectProduct(products[it]))
                }) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = products[it].title)
                Text(text = "Price: ${products[it].price}")
            }
        }
    }
}

@Composable
@Preview
fun ToolbarPreview() {
    ToolbarContent()
}

@Composable
@Preview(showBackground = true)
private fun HomePreview() {
    ContentBody(
        products = listOf(
            Product(
                id = "asd",
                title = "Bike",
                price = 20000.3
            ),

            Product(
                id = "asde",
                title = "Cycle",
                price = 1400.3
            )
        ),
        onEventSent = {}
    )
}
