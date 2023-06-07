package com.android.skeleton.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.skeleton.model.api.ApiCallback
import com.android.skeleton.model.api.ApiRepository
import com.android.skeleton.model.data.ProductsRes
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