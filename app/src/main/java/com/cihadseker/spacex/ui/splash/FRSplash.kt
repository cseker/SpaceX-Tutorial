package com.cihadseker.spacex.ui.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.cihadseker.core.base.BaseFragment
import com.cihadseker.spacex.R
import com.cihadseker.spacex.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FRSplash : BaseFragment<FragmentSplashBinding>() {

    private val viewModel: FRSplashVM by viewModels()

    override fun getLayoutId() = R.layout.fragment_splash

    override fun initViews() {
        viewModel.initVM()
    }

    override fun setListener() {}

    override fun setReceiver() {
        lifecycleScope.launchWhenCreated {
            viewModel.navigateToHome.collect {
                navigate(FRSplashDirections.toFRMainTab())
            }
        }
    }
}

