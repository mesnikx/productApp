package com.example.productapp.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.productapp.data.model.Product
import retrofit2.HttpException
import java.io.IOException

class ProductsPagingSource(val productApi: ProductApiService): PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {

        return try {

            val pageNumber = params.key ?: 0

            val response = productApi.getProducts(skip = pageNumber*20, limit = 20)

            val prevKey = if (pageNumber > 0) pageNumber - 1 else null

            val nextKey = if (response.products.isNotEmpty()) pageNumber + 1 else null
            LoadResult.Page(
                data = response.products,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}
