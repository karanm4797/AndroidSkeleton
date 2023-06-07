package com.android.skeleton.model.api

import android.content.Context
import android.util.Log
import com.android.skeleton.model.data.ProductsRes
import com.android.skeleton.utils.Utils
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class ApiRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val apiServices: ApiServices,
) {

    private suspend fun <T> parseResponse(apiCall: suspend () -> Response<T>): ApiCallback<T> {

        return try {
            if (Utils.isOnline(context)) {
                val response = apiCall.invoke()

                if (response.isSuccessful) {
                    ApiCallback.OnSuccess(response.body())
                } else {
                    Log.e("API_ERROR", response.message())
                    ApiCallback.OnError(response.message())
                }
            }
            else{
                ApiCallback.OnError("No internet connection")
            }
        } catch (exception: Exception) {
            Log.e("API_ERROR", exception.message.toString())
            ApiCallback.OnError(exception.message)
        }
    }

    suspend fun getProducts(): ApiCallback<ProductsRes> {
        return parseResponse { apiServices.getProducts() }
    }
}