package com.elchaninov.gbprofessionaldevelopment.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityMainBinding
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.view.adapter.MainAdapter
import com.elchaninov.gbprofessionaldevelopment.view.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState>(), SearchDialogFragment.OnSearchClickListener {

    override val model: MainViewModel by viewModel()

    private val observer = Observer<AppState> { renderData(it) }

    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(
                    this@MainActivity, data.text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.liveDataForViewToObserve.observe(this@MainActivity, observer)

        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewSuccess()
                if (adapter == null) {
                    binding.mainActivityRecyclerview.layoutManager =
                        LinearLayoutManager(applicationContext)
                    binding.mainActivityRecyclerview.adapter =
                        MainAdapter(onListItemClickListener, appState.data)
                } else {
                    adapter!!.setData(appState.data)
                }
            }
            is AppState.Empty -> {
                showViewSuccess()
                if (isOnline)
                    showErrorScreen(getString(R.string.empty_server_response_on_success), false)
                else
                    showErrorScreen(getString(R.string.empty_cash_response_on_success), true)
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message, true)
            }
        }
    }

    private fun showErrorScreen(error: String?, withButton: Boolean) {
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)

        binding.reloadButton.apply {
            setOnClickListener { model.getData(isOnline = isOnline) }
            visibility = if (withButton) VISIBLE else GONE
        }

        showViewError()
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
        showViewMessageNoConnection(true)
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
        showViewMessageNoConnection()
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
        showViewMessageNoConnection()
    }

    private fun showViewMessageNoConnection(isExtendedMessage: Boolean = false) {
        if (isExtendedMessage) {
            binding.sideMessageNoConnection.text =
                getString(R.string.message_is_offline_and_show_cache)
        }

        binding.sideMessageNoConnection.visibility = if (isOnline) GONE else VISIBLE
    }

    override fun onClick(searchWord: String) {
        model.getData(searchWord, isOnline)
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}