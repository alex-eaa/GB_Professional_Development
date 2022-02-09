package com.example.core

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.elchaninov.utils.AlertDialogFragment
import com.elchaninov.utils.isOnline
import com.example.core.databinding.LoadingFrameLayoutBinding
import com.example.core.view.SearchDialogFragment
import com.example.core.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity(),
    SearchDialogFragment.OnSearchClickListener {

    private lateinit var binding: LoadingFrameLayoutBinding
    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)

    private var isEnableShowErrorIfEmpty = true

    protected val isOnline: Boolean
        get() = isOnline(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoadingFrameLayoutBinding.inflate(layoutInflater)
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
        }
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    override fun onClick(searchWord: String) {
        isEnableShowErrorIfEmpty = true
        model.getData(searchWord, isOnline)
    }

    override fun onFlowSearch(searchWord: String) {
        isEnableShowErrorIfEmpty = false
        model.getData(searchWord, isOnline)
    }

    companion object {
        const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}