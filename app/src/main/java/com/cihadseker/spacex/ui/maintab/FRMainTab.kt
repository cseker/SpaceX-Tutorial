package com.cihadseker.spacex.ui.maintab

import com.cihadseker.core.base.BaseFragment
import com.cihadseker.spacex.R
import com.cihadseker.spacex.databinding.FragmentMainTabBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FRMainTab : BaseFragment<FragmentMainTabBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_main_tab

    override fun initViews() {
        vi.vpPager.adapter = AdapterPagerMainTab(this@FRMainTab)

        val tabNames =
            arrayListOf(
                "Roket Listesi",
                "Favori Roketlerim"
            )

        TabLayoutMediator(vi.tabLayout, vi.vpPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

    override fun setListener() {}

    override fun setReceiver() {}
}

