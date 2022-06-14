package com.cihadseker.spacex.ui.homedetail

import androidx.navigation.navGraphViewModels
import com.cihadseker.core.base.BaseFragment
import com.cihadseker.core.extension.injectVM
import com.cihadseker.spacex.R
import com.cihadseker.spacex.databinding.FragmentHomeDetailBinding
import com.cihadseker.spacex.ui.SharedRocketVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FRHomeDetail : BaseFragment<FragmentHomeDetailBinding>() {

    val viewModel: FRHomeDetailVM by injectVM()

    private val sharedRocketVM: SharedRocketVM by navGraphViewModels(R.id.FRMainTab)

    override fun getLayoutId() = R.layout.fragment_home_detail

    override fun initViews() {
        viewModel.setValue(sharedRocketVM.selectedRocketUI)
    }

    override fun setListener() {
        vi.btnIsFavorite.setOnClickListener {
            viewModel.updateItem(sharedRocketVM.selectedRocketUI)
        }
    }

    override fun setReceiver() {}
}