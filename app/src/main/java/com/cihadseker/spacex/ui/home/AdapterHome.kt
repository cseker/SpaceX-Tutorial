package com.cihadseker.spacex.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.cihadseker.core.ui.DataBindingAdapter
import com.cihadseker.core.ui.DataBindingViewHolder
import com.cihadseker.spacex.R
import com.cihadseker.spacex.data.local.RocketInfo
import com.cihadseker.spacex.databinding.ItemMainListBinding
import javax.inject.Inject

class AdapterHome @Inject constructor() : DataBindingAdapter<RocketInfo>(RocketDiffCallBack()) {

    override fun getItemLayoutId(viewType: Int) = R.layout.item_main_list

    var onClickedFavorite: ((RocketInfo) -> Unit)? = null

    override fun onBindViewHolder(holder: DataBindingViewHolder<RocketInfo>, position: Int) {
        super.onBindViewHolder(holder, position)

        val item = getItem(position)

        (holder.binding as ItemMainListBinding).btnFavorite.setOnClickListener {
            onClickedFavorite?.invoke(item)
        }
    }

    class RocketDiffCallBack : DiffUtil.ItemCallback<RocketInfo>() {
        override fun areItemsTheSame(oldItem: RocketInfo, newItem: RocketInfo) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RocketInfo, newItem: RocketInfo) = oldItem.isFavorite == newItem.isFavorite
    }
}
