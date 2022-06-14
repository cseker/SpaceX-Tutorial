package com.cihadseker.spacex.ui.favorite

import com.cihadseker.core.ui.DataBindingAdapter
import com.cihadseker.core.ui.DataBindingViewHolder
import com.cihadseker.spacex.R
import com.cihadseker.spacex.data.local.RocketInfo
import com.cihadseker.spacex.databinding.ItemFavoriteBinding
import com.cihadseker.spacex.databinding.ItemMainListBinding

class AdapterFavorite : DataBindingAdapter<RocketInfo>() {

    override fun getItemLayoutId(viewType: Int) = R.layout.item_favorite

    var onClickedFavorite: ((RocketInfo) -> Unit)? = null

    override fun onBindViewHolder(holder: DataBindingViewHolder<RocketInfo>, position: Int) {
        super.onBindViewHolder(holder, position)

        val item = getItem(position)

        (holder.binding as ItemFavoriteBinding).btnFavorite.setOnClickListener {
            onClickedFavorite?.invoke(item)
        }
    }
}
