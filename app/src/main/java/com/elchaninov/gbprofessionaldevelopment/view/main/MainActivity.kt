package com.elchaninov.gbprofessionaldevelopment.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityMainBinding
import com.example.core.view.SearchDialogFragment
import com.elchaninov.favorite.favorite.FavoriteActivity
import com.elchaninov.gbprofessionaldevelopment.view.history.HistoryActivity
import com.example.core.AppState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : com.example.core.BaseActivity<AppState>(),
    com.elchaninov.utils.AlertDialogFragment.OnActionButtonClickListener {

    override val model: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter: MainAdapter by lazy {
        MainAdapter(onListItemClickListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model.liveDataForViewToObserve.observe(this@MainActivity) { renderData(it) }
        initViews()
    }

    private fun initViews() {
        binding.mainActivityRecyclerview.adapter = adapter
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        supportActionBar?.title = getString(R.string.title_main_activity)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            R.id.menu_favorite -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewSuccess()
                showViewMessageNoConnection(true)
                adapter.setData(appState.data)
            }
            is AppState.Empty -> {
                showViewSuccess()
                showViewMessageNoConnection(true)
                if (isOnline) {
                    showAlertDialog(
                        getString(R.string.dialog_title_empty),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    showAlertDialog(
                        getString(R.string.dialog_title_empty),
                        getString(R.string.empty_cash_response_on_success),
                        getString(R.string.dialog_button_try_again),
                    )
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                showViewMessageNoConnection()
                if (appState.progress != null) {
                    binding.loading.progressBarHorizontal.visibility = VISIBLE
                    binding.loading.loadingFrameLayout.visibility = GONE
                    binding.loading.progressBarHorizontal.progress = appState.progress!!
                } else {
                    binding.loading.progressBarHorizontal.visibility = GONE
                    binding.loading.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showViewMessageNoConnection()
                showViewError(appState.error.message)
            }
        }
    }

    private fun showViewMessageNoConnection(isExtendedMessage: Boolean = false) {
        if (isExtendedMessage) {
            binding.sideMessageNoConnection.text =
                getString(R.string.message_is_offline_and_show_cache)
        }
        binding.sideMessageNoConnection.visibility = if (isOnline) GONE else VISIBLE
    }

    override fun onClickActionButton() {
        model.getData(null, isOnline)
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}