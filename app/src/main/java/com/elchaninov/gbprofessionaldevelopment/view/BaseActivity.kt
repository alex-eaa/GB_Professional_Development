package com.elchaninov.gbprofessionaldevelopment.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.LoadingFrameLayoutBinding
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.utils.convertMeaningsToString
import com.elchaninov.gbprofessionaldevelopment.utils.isOnline
import com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen.DescriptionActivity
import com.elchaninov.gbprofessionaldevelopment.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    private lateinit var binding: LoadingFrameLayoutBinding
    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)

    protected val isOnline: Boolean
        get() = isOnline(applicationContext)

    protected val onListItemClickListener: (DataModel) -> Unit = { data ->
        startActivity(
            DescriptionActivity.getIntent(
                this,
                data.text.toString(),
                convertMeaningsToString(data.meanings),
                data.meanings?.get(0)?.imageUrl
            )
        )
    }

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
        if (isDialogNull()) {
            AlertDialogFragment.newInstance(title, message, buttonTitle)
                .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
        }
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    companion object {
        const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}