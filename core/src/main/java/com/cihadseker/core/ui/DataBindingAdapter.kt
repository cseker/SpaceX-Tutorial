package com.cihadseker.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class DataBindingAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T> = EmptyDiffCallBack(),
    var currentSelected: T? = null
) : ListAdapter<T, DataBindingViewHolder<T>>(diffCallback) {

    open var itemClickListener: ((T) -> Unit)? = null

    abstract fun getItemLayoutId(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val holder = DataBindingViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), getItemLayoutId(viewType), parent, false),
            currentSelected
        )

        holder.viewType = viewType

        return holder
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { itemClickListener?.invoke(item) }
    }
}

class EmptyDiffCallBack<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
