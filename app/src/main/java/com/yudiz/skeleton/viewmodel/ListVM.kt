package com.yudiz.skeleton.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yudiz.skeleton.model.api.ApiCallback
import com.yudiz.skeleton.model.api.ApiRepository
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
    private var apiServiceProvider: ApiRepository,
) :
    BaseVM() {

    fun getPosts() : LiveData<ApiCallback<ProductsRes>>{
        val lst  = MutableLiveData<ApiCallback<ProductsRes>>()
        viewModelScope.launch {
            lst.postValue(apiServiceProvider.getProducts())
        }
        return lst
    }
}