package com.skystatus.presentation.main

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.skystatus.R
import com.skystatus.databinding.ActivityMainBinding
import com.skystatus.presentation.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel, MainViewState>() {

    override val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var menu: Menu
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun render(viewState: MainViewState) = when (viewState) {
        is MainViewState.InitializeView -> initializeView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onEvent(MainEvent.InitializeView)
    }

    private fun initializeView(){
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}