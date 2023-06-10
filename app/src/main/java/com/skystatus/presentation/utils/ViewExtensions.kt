package com.skystatus.presentation.utils

import androidx.fragment.app.Fragment
import com.skystatus.presentation.core.BottomSheetDialog


fun BottomSheetDialog<*>.show(fragment: Fragment) {
    show(fragment.requireActivity().supportFragmentManager, null)
    fragment.requireActivity().supportFragmentManager.executePendingTransactions()
}