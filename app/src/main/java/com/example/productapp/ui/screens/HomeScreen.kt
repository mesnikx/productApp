package com.example.productapp.ui.screens

import android.content.ClipData.Item
import android.os.Message
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.Pager
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.productApp.R
import com.example.productapp.data.model.Product
import com.example.productapp.data.model.ProductsList
import com.example.productapp.network.ProductsPagingSource
import com.example.productapp.ui.ProductCard
import com.example.productapp.ui.theme.ProductAppTheme

@Composable
fun HomeScreen(
    productUiState: ProductUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (productUiState) {
        is ProductUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is ProductUiState.Success -> ResultScreen(productUiState.productsList.products)
        is ProductUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}


@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun ResultScreen(
    products: List<Product>,
    modifier: Modifier = Modifier,
    pagingSource: ProductsPagingSource
) {
    val lazyPagingItems: LazyPagingItems<Product> = pagingSource.collectAsLazyPagingItems()
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
        items(products) {product ->
            ProductCard(product)
        }

    }
    fun LazyVerticalGridWithPaging(
        pagingSource: ItemPagingSource
    ) {
        val lazyPagingItems: LazyPagingItems<Item> = pagingSource.collectAsLazyPagingItems()

        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(lazyPagingItems.itemCount) { index ->
                val item = lazyPagingItems[index]
                if (item != null) {
                    // Display your item in a grid cell
                } else {
                    // Handle loading state
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    ProductAppTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ProductAppTheme {
        ErrorScreen()
    }
}
