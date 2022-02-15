package com.example.core

import android.os.Bundle
import android.view.View
import com.elchaninov.utils.AlertDialogFragment
import com.elchaninov.utils.OnlineLiveData
import com.elchaninov.utils.disableBlurEffect
import com.elchaninov.utils.enableBlurEffect
import com.example.core.databinding.LoadingFrameLayoutBinding
import com.example.core.view.SearchDialogFragment
import com.example.core.viewmodel.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity

abstract class BaseActivity<T : AppState> : ScopeActivity(),
    SearchDialogFragment.OnSearchClickListener, AlertDialogFragment.OnBlurEffectDisable {

    private lateinit var binding: LoadingFrameLayoutBinding
    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)

    var isEnableShowErrorIfEmpty = true

    private val onlineLiveData: OnlineLiveData by inject()
    protected var isOnline: Boolean = false
    abstract var snackbar: Snackbar?
    protected var viewForBlurEffect: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoadingFrameLayoutBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        subscribeToNetworkChange()
    }

    protected fun showViewSuccess() {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    protected fun showViewLoading() {
        binding.loadingFrameLayout.visibility = View.VISIBLE
    }

    protected fun showViewError(message: String?) {
        binding.loadingFrameLayout.visibility = View.GONE
        showAlertDialog(getString(R.string.dialog_title_stub), message)
    }

    protected fun showAlertDialog(title: String?, message: String?, buttonTitle: String? = null) {
        if (isDialogNull() && isEnableShowErrorIfEmpty) {
            AlertDialogFragment.newInstance(title, message, buttonTitle)
                .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)

            viewForBlurEffect?.enableBlurEffect()
        }
    }

    protected fun getSnackbar(view: View): Snackbar = Snackbar.make(
        view, getString(R.string.message_device_is_offline),
        Snackbar.LENGTH_INDEFINITE
    )

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    override fun onClick(searchWord: String) {
        isEnableShowErrorIfEmpty = true
        model.getData(searchWord, isOnline)
    }

    override fun updateFlowSearch(searchWord: String) {
        isEnableShowErrorIfEmpty = false
        model.updateQueryStateFlow(searchWord, isOnline)
    }

    private fun subscribeToNetworkChange() {
        onlineLiveData.observe(
            this@BaseActivity
        ) {
            isOnline = it
            if (isOnline) snackbar?.dismiss()
            else snackbar?.show()
        }
    }

    override fun disableBlurEffect() {
        viewForBlurEffect?.disableBlurEffect()
    }

    companion object {
        const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}