package com.elchaninov.gbprofessionaldevelopment.view.base

import androidx.appcompat.app.AppCompatActivity
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.utils.network.isOnline
import com.elchaninov.gbprofessionaldevelopment.utils.ui.AlertDialogFragment
import com.elchaninov.gbprofessionaldevelopment.viewmodel.BaseViewModel


abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    // В каждой Активити будет своя ViewModel, которая наследуется от BaseViewModel
    abstract val model: BaseViewModel<T>

    // Каждая Активити будет отображать какие-то данные в соответствующем состоянии
    abstract fun renderData(appState: T)

    protected val isOnline: Boolean
        get() = isOnline(applicationContext)

    protected fun showAlertDialog(title: String?, message: String?, buttonTitle: String? = null) {
        if (isDialogNull()) {
            AlertDialogFragment.newInstance(title, message, buttonTitle)
                .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
        }
    }

    protected fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    companion object {
        const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}