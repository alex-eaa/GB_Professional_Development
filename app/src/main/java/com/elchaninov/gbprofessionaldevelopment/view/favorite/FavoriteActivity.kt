package com.elchaninov.gbprofessionaldevelopment.view.favorite

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityFavoriteBinding
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.view.BaseActivity
import com.elchaninov.gbprofessionaldevelopment.view.SearchDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : BaseActivity<AppState>() {

    override val model: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding
    private val adapter: FavoriteAdapter by lazy {
        FavoriteAdapter(onListItemClickListener, onFavoriteClickListener)
    }

    private val onFavoriteClickListener: (DataModel) -> Unit = { dataModel ->
        model.toggleEntityTranslation(dataModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model.liveDataForViewToObserve.observe(this@FavoriteActivity) { renderData(it) }
        initViews()
        setActionbarHomeButtonAsUp()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.title = getString(R.string.title_favorite_activity)
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

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}
