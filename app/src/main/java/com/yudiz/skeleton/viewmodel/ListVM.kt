package com.yudiz.skeleton.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yudiz.skeleton.model.api.ApiCallback
import com.yudiz.skeleton.model.api.ApiServiceProvider
import com.yudiz.skeleton.model.data.ProductsRes
import com.yudiz.skeleton.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class ListVM @Inject constructor(
    @ApplicationContext val context: Context,
    private var apiServiceProvider: ApiServiceProvider,
) :
    BaseVM() {

    private var postsRes = MutableLiveData<ApiCallback<ProductsRes>>()
    val posts: LiveData<ApiCallback<ProductsRes>> = postsRes

    fun getPosts() {

        viewModelScope.launch {

            if (Utils.isOnline(context)) {
                postsRes.value = apiServiceProvider.getProducts()
            }
        }
    }
}