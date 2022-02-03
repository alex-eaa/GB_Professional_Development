package com.elchaninov.gbprofessionaldevelopment.view.history

import android.os.Bundle
import android.view.View
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityHistoryBinding
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.utils.ui.AlertDialogFragment
import com.elchaninov.gbprofessionaldevelopment.view.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<AppState>() {

    override val model: HistoryViewModel by viewModel()

    private lateinit var binding: ActivityHistoryBinding
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.liveDataForViewToObserve.observe(this@HistoryActivity, {
            renderData(it)
        })

        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewSuccess()
                setDataToAdapter(appState.data)
            }
            is AppState.Empty -> {
                showViewSuccess()
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.loading.progressBarHorizontal.visibility = View.VISIBLE
                    binding.loading.loadingFrameLayout.visibility = View.GONE
                    binding.loading.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.loading.progressBarHorizontal.visibility = View.GONE
                    binding.loading.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewError(appState.error.message)
            }
        }
    }

    private fun showViewSuccess() {
        binding.loading.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loading.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun showViewError(message: String?) {
        binding.loading.loadingFrameLayout.visibility = View.GONE
        showAlertDialog("Ошибка", message)
    }

    private fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "0c7dff51-9769-4f6d-bbee-a3896085e76e"
    }
}
