package com.yudiz.skeleton.view.activities

import androidx.activity.viewModels
import com.yudiz.skeleton.R
import com.yudiz.skeleton.databinding.ActivityListBinding
import com.yudiz.skeleton.model.api.ApiCallback
import com.yudiz.skeleton.view.adapters.ProductRVAdapter
import com.yudiz.skeleton.viewmodel.ListVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListAct : BaseAct<ActivityListBinding, ListVM>(R.layout.activity_list) {

    override val vm: ListVM by viewModels()

    override fun init() {

        progress.show()
        vm.getPosts()

        vm.posts.observe(this) {

            when (it) {
                is ApiCallback.OnSuccess -> {
                    progress.hide()
                    binding.rvProducts.adapter = ProductRVAdapter(it.data!!)
                }
                is ApiCallback.OnError -> {
                    progress.hide()
                }
            }
        }
    }
}