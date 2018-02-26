package com.yousufsohail.tmdb.common

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.yousufsohail.tmdb.BR

/**
 * Created by Yousuf Sohail on 1/18/18.
 */
class MyViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}