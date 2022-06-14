package com.cihadseker.spacex.ui.home

import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import com.cihadseker.core.base.BaseFragment
import com.cihadseker.core.extension.injectVM
import com.cihadseker.spacex.R
import com.cihadseker.spacex.databinding.FragmentHomeBinding
import com.cihadseker.spacex.ui.SharedRocketVM
import com.cihadseker.spacex.ui.maintab.FRMainTabDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FRHome : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: FRHomeVM by injectVM()

    @Inject
    lateinit var adapterHome: AdapterHome

    private val sharedRocketVM: SharedRocketVM by navGraphViewModels(R.id.FRMainTab)

    override fun getLayoutId() = R.layout.fragment_home

    override fun initViews() {
        vi.rvMainList.apply {
            adapter = adapterHome
        }

        viewModel.getRocketList()
    }

    override fun setListener() {
        adapterHome.apply {
            onClickedFavorite = {
                viewModel.updateRocketItem(it)
            }

            itemClickListener = {
                sharedRocketVM.selectedRocketUI = it
                navigate(FRMainTabDirections.toFRDetail())
            }
        }

        vi.swipeRefreshLayout.setOnRefreshListener {
            vi.swipeRefreshLayout.isRefreshing = false
            viewModel.getRocketList()
        }
    }

    override fun setReceiver() {
        lifecycleScope.launchWhenCreated {
            viewModel.rocketList.collect { itCollect ->
                itCollect?.let {
                    adapterHome.submitList(it)
                }
            }
        }
    }
}