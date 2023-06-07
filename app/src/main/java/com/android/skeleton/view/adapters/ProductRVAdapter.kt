package com.android.skeleton.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.skeleton.R
import com.android.skeleton.databinding.ProductItemBinding
import com.bumptech.glide.Glide
import com.android.skeleton.model.data.ProductsRes

class ProductRVAdapter(private val products: ProductsRes) :
    RecyclerView.Adapter<ProductRVAdapter.ProductViewHolder>() {

    class ProductViewHolder(internal val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(ProductItemBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        with(holder.binding) {

            product = products[position]
            Glide.with(holder.binding.root).load(products[position].image).into(imgProduct)
        }
    }
}