package com.cihadseker.core.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.cihadseker.core.extension.hideKeyboard
import com.cihadseker.core.extension.isTrue
import com.cihadseker.core.model.ServiceError
import com.cihadseker.core.util.OnBackPressListener

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    lateinit var vi: DB

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initViews(savedInstanceState: Bundle?)
    abstract fun setListener()
    abstract fun setReceiver()

    var onBackPressListener: OnBackPressListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vi = DataBindingUtil.setContentView(this, getLayoutId())
        vi.lifecycleOwner = this

        initViews(savedInstanceState)
        setListener()
        setReceiver()
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    override fun onBackPressed() {
        if (onBackPressListener?.isBackEnable().isTrue()) {
            if (onBackPressListener?.onBackPressed().isTrue()) {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    fun showLoading() {
        //TODO dolduruılacak
    }

    fun hideLoading() {
        //TODO dolduruılacak
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showError(error: ServiceError) {
        //TODO servis error kısımları yazılacak
    }
}