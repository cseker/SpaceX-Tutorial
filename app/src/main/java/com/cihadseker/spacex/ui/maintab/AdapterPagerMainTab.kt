package com.cihadseker.spacex.ui.maintab

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cihadseker.spacex.ui.home.FRHome
import com.cihadseker.spacex.ui.favorite.FRFavorite

class AdapterPagerMainTab (fa: Fragment): FragmentStateAdapter(fa){

    var items = arrayListOf(FRHome(), FRFavorite())

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int) = items[position]
}
