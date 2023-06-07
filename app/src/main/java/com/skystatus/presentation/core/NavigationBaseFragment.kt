package com.skystatus.presentation.core

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding

abstract class NavigationBaseFragment<B : ViewBinding, VM : BaseViewModel<VS, *>, VS : ViewState> :
    BaseFragment<B, VM, VS>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        //toggleNavigationVisibility(true)
        //toggleToolbarVisibility(true)
    }

    fun refreshMenu(){
        (requireActivity() as? BaseActivity<*, *, *>)?.invalidateOptionsMenu()
    }

}