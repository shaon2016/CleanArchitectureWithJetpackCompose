package com.shaon2016.compose.ui.home

import android.annotation.SuppressLint
import android.util.Log
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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

    Scaffold(
        topBar = {
            ToolbarContent()
        }
    ) {
        when (state.dataState) {
            DataState.INITIAL -> {}
            DataState.SUCCESS -> {
                ContentBody(
                    products = state.products
                )
            }
            DataState.FAIL -> {
                // TODO error view
            }
        }

        if (state.isLoading) {
            //   TODO show loading dialog
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

@Composable
private fun ContentBody(
    products: List<Product>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(products.size) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = products[it].name)
                Text(text = products[it].price.toString())
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
                name = "Bike",
                price = 20000.3
            ),

            Product(
                id = "asde",
                name = "Cycle",
                price = 1400.3
            )
        )
    )
}
