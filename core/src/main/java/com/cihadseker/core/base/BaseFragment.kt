package com.cihadseker.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.cihadseker.core.extension.clearCurrentFocus
import com.cihadseker.core.util.NavOption
import com.cihadseker.core.util.OnBackPressListener

abstract class BaseFragment<DB : ViewDataBinding> : Fragment(), OnBackPressListener {

    lateinit var vi: DB
    private var isViewCreated = false

    @LayoutRes
    abstract fun getLayoutId(): Int

    @MenuRes
    open fun getMenuId(): Int = -1

    abstract fun initViews()
    abstract fun setListener()
    abstract fun setReceiver()

    protected fun navigate(
        navDirections: NavDirections,
        navOption: NavOption? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        clearCurrentFocus()
        val navOptions = navOption?.let {
            NavOptions.Builder().setEnterAnim(navOption.enter)
                .setExitAnim(navOption.exit)
                .setPopEnterAnim(navOption.popEnter)
                .setPopExitAnim(navOption.popExit)
                .build()
        } ?: run { null }

        findNavController().navigate(
            navDirections.actionId,
            navDirections.arguments,
            navOptions,
            navigatorExtras
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!::vi.isInitialized) {
            vi = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            vi.lifecycleOwner = this
        }

        container?.removeView(vi.root)

        return vi.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isViewCreated) {
            isViewCreated = true

            initViews()
            setListener()
        }
        setReceiver()
        vi.root.setOnClickListener { clearCurrentFocus() }
    }

    override fun onResume() {
        if (requireActivity() is BaseActivity<*> && isBackEnable())
            (requireActivity() as BaseActivity<*>).onBackPressListener = this

        super.onResume()
    }

    override fun onPause() {
        if (requireActivity() is BaseActivity<*> && isBackEnable())
            (requireActivity() as BaseActivity<*>).onBackPressListener = null

        super.onPause()
    }

    override fun isBackEnable() = false
    override fun onBackPressed() = false
}