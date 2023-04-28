package com.shaon2016.compose.ui.productdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.shaon2016.compose.domain.entity.Product
import com.shaon2016.compose.ui.home.HomeContract
import com.shaon2016.compose.ui.home.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
internal fun ProductDetailsScreen(
    onNavigationRequested: (navigationEffect: ProductDetailsContract.Effect.Navigation) -> Unit
) {
    val vm: ProductDetailsViewModel = hiltViewModel()
    val state = vm.state.value

    LaunchedEffect("Details") {
        vm.effect.onEach { effect ->
            when (effect) {
                is ProductDetailsContract.Effect.Navigation -> onNavigationRequested(effect)
            }
        }.collect()
    }

    Scaffold(
        topBar = {
            ToolbarContent()
        },
        scaffoldState = rememberScaffoldState()
    ) { paddingValues ->
        ProductDetailsContent(
            modifier = Modifier.padding(paddingValues),
            product = state.product
        )
    }
}

@Composable
private fun ToolbarContent() {
    TopAppBar {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Details"
            )
        }
    }
}

@Composable
private fun ProductDetailsContent(
    modifier: Modifier,
    product: Product
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.imageUrl)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Fit,
            contentDescription = "Image"
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = product.title)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Price: ${product.price}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Description",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = product.description)
    }
}

@Composable
@Preview(showBackground = true)
private fun DetailPreview() {
    ProductDetailsContent(
        modifier = Modifier,
        product = Product(
            id = "asd",
            title = "Bike",
            price = 20000.3
        )
    )
}