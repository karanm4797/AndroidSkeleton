package com.yudiz.skeleton.model.api

import android.content.Context
import android.util.Log
import com.yudiz.skeleton.model.data.ProductsRes
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceProvider @Inject constructor(
    @ApplicationContext val context: Context,
    private val apiServices: ApiServices,
) {

    private suspend fun <T> parseResponse(apiCall: suspend () -> Response<T>): ApiCallback<T> {

        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                ApiCallback.OnSuccess(response.body())
            } else {
                Log.e("API_ERROR", response.message())
                ApiCallback.OnError(response.message())
            }
        } catch (exception: Exception) {
            Log.e("API_ERROR", exception.message.toString())
            ApiCallback.OnError(exception.message)
        }
    }

    suspend fun getProducts(): ApiCallback<ProductsRes> {
        val call = apiServices.getProducts()
        return parseResponse { call }
    }
}