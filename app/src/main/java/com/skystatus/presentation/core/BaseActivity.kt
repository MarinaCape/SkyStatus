package com.skystatus.presentation.core

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.skystatus.domain.core.main
import kotlinx.coroutines.launch

abstract class BaseActivity<B : ViewBinding, VM : BaseViewModel<VS, *>, VS : ViewState> : AppCompatActivity(),
    Renderable<VS> {

    companion object {
        protected val REQUEST_CHECK_SETTINGS = 0x1
    }
    protected abstract val viewModel: VM
    protected val binding: B by lazy {
        viewBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = Color.TRANSPARENT
        }
        setContentView(binding.root)
        lifecycleScope.launch(main) {
            viewModel.viewState.collect {
                if (it != null) render(it)
            }
        }
        //setupNavigator()
    }

    abstract fun viewBinding(): B

}