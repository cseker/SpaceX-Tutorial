package com.cihadseker.spacex.ui.favorite

import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import com.cihadseker.core.base.BaseFragment
import com.cihadseker.core.extension.injectVM
import com.cihadseker.spacex.R
import com.cihadseker.spacex.databinding.FragmentFavoriteBinding
import com.cihadseker.spacex.ui.SharedRocketVM
import com.cihadseker.spacex.ui.maintab.FRMainTabDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FRFavorite : BaseFragment<FragmentFavoriteBinding>() {

    private val viewModel: FRFavoriteVM by injectVM()

    private lateinit var adapterFavorite: AdapterFavorite

    private val sharedRocketVM: SharedRocketVM by navGraphViewModels(R.id.FRMainTab)

    override fun getLayoutId() = R.layout.fragment_favorite

    override fun initViews() {
        vi.rvFavorite.apply {
            adapter = AdapterFavorite().apply {
                adapterFavorite = this
            }
        }
    }

    override fun setListener() {
        adapterFavorite.apply {
            onClickedFavorite = {
                viewModel.updateRocketItem(it)
            }

            itemClickListener = {
                sharedRocketVM.selectedRocketUI = it
                navigate(FRMainTabDirections.toFRDetail())
            }
        }
    }

    override fun setReceiver() {
        lifecycleScope.launchWhenCreated {
            viewModel.allList.collect { itCollect ->
                itCollect?.let {
                    adapterFavorite.submitList(it)
                }
            }
        }
    }
}