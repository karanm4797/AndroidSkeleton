package com.yudiz.skeleton.model.api

import com.yudiz.skeleton.model.data.ProductsRes
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    @GET(ApiConstants.GET_PRODUCTS)
    suspend fun getProducts(): Response<ProductsRes>
}