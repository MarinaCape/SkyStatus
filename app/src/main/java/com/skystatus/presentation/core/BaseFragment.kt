package com.skystatus.presentation.core

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.skystatus.R
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch


abstract class BaseFragment<B : ViewBinding, VM : BaseViewModel<VS, *>, VS : ViewState> :
    Fragment(), Renderable<VS> {

    protected abstract val viewModel: VM
    protected lateinit var binding: B

    internal var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = viewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.launchWhenStarted(viewLifecycleOwner)
    }


    private fun SharedFlow<VS?>.launchWhenStarted(lifecycleOwner: LifecycleOwner) =
        with(lifecycleOwner) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    this@launchWhenStarted.collect {
                        it?.let { render(it) }
                    }
                }
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog?.hide()
        progressDialog = null
    }

    abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): B

    fun showError(root: ViewGroup, message: String? = null, description: String? = null) {
        showMessage(
            root,
            message ?: getString(R.string.error_generic_title),
            description ?: getString(R.string.error_generic_description),
            R.drawable.ic_warning_white
        )
    }

    fun showSuccessDialog(msg: Int, action: () -> Unit = {}) {
       /* val dialog =
            InfoDialog(R.drawable.ic_check_simple, msg)
        dialog.setOnClickButtonListener {
            dialog.dismiss()
            action()
        }
        dialog.isCancelable = false
        dialog.show(parentFragmentManager)*/
    }

    fun showMessage(root: ViewGroup, message: String, description: String, @DrawableRes icon: Int) {
        /*TopSnackBar.make(
            root,
            message,
            description,
            icon
        ).show()*/
    }

    fun openWithBrowser(url: String) {
        val link = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, link)
        startActivity(intent)
    }

    protected fun hideKeyboard() {
        val focus = activity?.currentFocus ?: return
        val inputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(focus.windowToken, 0)
    }

}