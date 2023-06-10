package com.skystatus.presentation.home.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.skystatus.databinding.BottomSheetMenuBinding
import com.skystatus.presentation.core.BottomSheetDialog

class BottomSheetMenu(private val cityOption: () -> Unit, private val sunsetOption: () -> Unit) :
    BottomSheetDialog<BottomSheetMenuBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BottomSheetMenuBinding {
        return BottomSheetMenuBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        dialog?.setOnShowListener {
            val frameLayout = dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            frameLayout?.let {
                BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
            }
            binding.cityOption.setOnClickListener {
                cityOption()
                dismiss()
            }
            binding.sunsetOption.setOnClickListener {
                sunsetOption()
                dismiss()
            }
        }
    }
}