package com.example.productapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.productapp.data.model.Product
import com.example.productapp.ui.theme.ProductAppTheme

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),

        ) {
        Box(modifier = Modifier.height(200.dp)) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = null
            )

//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        brush = Brush.verticalGradient(
//                            colors = listOf(
//                                Color.Transparent,
//                                Color.Black
//                            ),
//                            startY = 300f
//                        )
//                    )
//            ) {
//
//            }
        }
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = product.title,
            style = TextStyle(color = Color.White, fontSize = 16.sp)
        )

        Text(
            modifier = Modifier.padding(12.dp),
            text = product.description,
            style = TextStyle(color = Color.White, fontSize = 16.sp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductAppTheme {
        ProductCard(testProduct)
    }
}

val testProduct = Product(
    id = 1,
    title = "iPhone 9",
    description = "An apple mobile which is nothing like apple",
    thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
)