package com.android.skeleton.view.activities

import androidx.activity.viewModels
import com.android.skeleton.R
import com.android.skeleton.databinding.ActivityListBinding
import com.android.skeleton.viewmodel.ListVM
import com.android.skeleton.model.api.ApiCallback
import com.android.skeleton.view.adapters.ProductRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListAct : BaseAct<ActivityListBinding, ListVM>(R.layout.activity_list) {

    override val vm: ListVM by viewModels()

    override fun init() {

        showProgress(true)
        vm.getPosts().observe(this) {
            when (it) {
                is ApiCallback.OnSuccess -> {
                    showProgress(false)
                    binding.rvProducts.adapter = ProductRVAdapter(it.data!!)
                }

                is ApiCallback.OnError -> {
                    showProgress(false)
                }

                else -> {}
            }
        }
    }
}