package com.elchaninov.favorite.favorite

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.elchaninov.descriptionScreen.DescriptionActivity
import com.elchaninov.favorite.R
import com.elchaninov.favorite.databinding.ActivityFavoriteBinding
import com.elchaninov.model.usermodel.DataModel
import com.elchaninov.utils.convertMeaningsToString
import com.example.core.AppState
import com.example.core.BaseActivity
import com.example.core.view.SearchDialogFragment
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

    private val onListItemClickListener: (DataModel) -> Unit = { data ->
        startActivity(
            DescriptionActivity.getIntent(
                this,
                data.text,
                convertMeaningsToString(data.meanings),
                data.meanings[0].imageUrl
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        rootView = binding.root
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        model.liveDataForViewToObserve.observe(this@FavoriteActivity) { renderData(it) }
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
        supportActionBar?.let {
            it.title = getString(R.string.title_favorite_activity)
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
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
                    binding.loading.progressBarHorizontal.progress = appState.progress!!
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
