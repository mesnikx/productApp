@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.productapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productApp.R
import com.example.productapp.ui.screens.HomeScreen
import com.example.productapp.ui.screens.ProductViewModel

@Composable
fun ProductApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { ProductTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val productViewModel: ProductViewModel = viewModel()
            HomeScreen(
                productUiState = productViewModel.productUiState,
                contentPadding = it
            )
        }
    }
}

@Composable
fun ProductTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}