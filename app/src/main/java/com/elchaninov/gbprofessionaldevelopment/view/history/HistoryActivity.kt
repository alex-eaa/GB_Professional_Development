package com.elchaninov.gbprofessionaldevelopment.view.history

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityHistoryBinding
import com.elchaninov.gbprofessionaldevelopment.model.usermodel.DataModel
import com.elchaninov.gbprofessionaldevelopment.view.AppState
import com.elchaninov.gbprofessionaldevelopment.view.BaseActivity
import com.elchaninov.gbprofessionaldevelopment.view.SearchDialogFragment
import com.elchaninov.gbprofessionaldevelopment.view.favorite.FavoriteActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<AppState>(), SearchDialogFragment.OnSearchClickListener {

    override val model: HistoryViewModel by viewModel()
    private lateinit var binding: ActivityHistoryBinding
    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter(onListItemClickListener, onFavoriteClickListener)
    }

    private val onFavoriteClickListener: (DataModel) -> Unit = { dataModel ->
        model.toggleEntityTranslation(dataModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model.liveDataForViewToObserve.observe(this@HistoryActivity) { renderData(it) }
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData(null, false)
    }

    private fun initViews() {
        setActionbarHomeButtonAsUp()
        binding.historyActivityRecyclerview.adapter = adapter
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        supportActionBar?.title = getString(R.string.title_history_activity)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menu_favorite -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewSuccess()
                adapter.setData(appState.data)
            }
            is AppState.Empty -> {
                showViewSuccess()
                showAlertDialog(
                    getString(R.string.dialog_title_empty),
                    getString(R.string.empty_server_response_on_success)
                )
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

    override fun onClick(searchWord: String) {
        model.getData(searchWord, isOnline)
    }

    override fun onFlowSearch(searchWord: String) {
        model.getData(searchWord, isOnline)
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}
