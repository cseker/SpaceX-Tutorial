package com.cihadseker.core.extension.datalistener

import androidx.lifecycle.lifecycleScope
import com.cihadseker.core.BR
import com.cihadseker.core.base.BaseActivity
import com.cihadseker.core.base.BaseFragment
import com.cihadseker.core.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest

inline fun <reified VM : BaseViewModel> Lazy<VM>.bindingListener(owner: BaseFragment<*>) {
    with(owner) {
        lifecycleScope.launchWhenResumed {
            value.let { vm ->
                vi.lifecycleOwner = owner
                vi.setVariable(BR.viewModel, vm)
                vi.executePendingBindings()
            }
        }
    }
}

inline fun <reified VM : BaseViewModel> Lazy<VM>.viewListener(owner: BaseFragment<*>) {
    with(owner) {
        lifecycleScope.launchWhenCreated {
            this@viewListener.value.let { vm ->
                lifecycleScope.launchWhenCreated {
                    vm.goBack.collectLatest {
                        activity?.onBackPressed()
                    }
                }

                lifecycleScope.launchWhenCreated {
                    vm.finish.collectLatest {
                        activity?.finish()
                    }
                }
                lifecycleScope.launchWhenCreated {
                    vm.showProgress.collectLatest {
                        (requireActivity() as BaseActivity<*>).showLoading()
                    }
                }

                lifecycleScope.launchWhenCreated {
                    vm.hideProgress.collectLatest {
                        (requireActivity() as BaseActivity<*>).hideLoading()
                    }
                }
            }
        }
    }
}
