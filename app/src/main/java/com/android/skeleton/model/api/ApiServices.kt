package com.android.skeleton.model.api

import com.android.skeleton.model.data.ProductsRes
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    @GET(ApiConstants.GET_PRODUCTS)
    suspend fun getProducts(): Response<ProductsRes>
}