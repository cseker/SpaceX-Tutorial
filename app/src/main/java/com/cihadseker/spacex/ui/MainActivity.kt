package com.cihadseker.spacex.ui

import android.os.Bundle
import com.cihadseker.core.base.BaseActivity
import com.cihadseker.spacex.R
import com.cihadseker.spacex.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId() = R.layout.activity_main

    override fun initViews(savedInstanceState: Bundle?) {}

    override fun setListener() {}

    override fun setReceiver() {}
}