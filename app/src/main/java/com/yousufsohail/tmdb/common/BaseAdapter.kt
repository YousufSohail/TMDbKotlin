package com.yousufsohail.tmdb.common

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Yousuf Sohail on 1/18/18.
 */

open class BaseAdapter<T> internal constructor(
        private val layoutId: Int,
        private val listener: OnItemClickListener<T>
) : RecyclerView.Adapter<MyViewHolder<T>>() {

    var items: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder<T>, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener({ listener.onItemClick(item) })
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return layoutId
    }
}
