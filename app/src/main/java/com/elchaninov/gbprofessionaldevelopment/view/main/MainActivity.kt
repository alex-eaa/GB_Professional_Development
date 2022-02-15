package com.elchaninov.gbprofessionaldevelopment.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.recyclerview.widget.RecyclerView
import com.elchaninov.descriptionScreen.DescriptionActivity
import com.elchaninov.favorite.favorite.FavoriteActivity
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityMainBinding
import com.elchaninov.historyscreen.HistoryActivity
import com.elchaninov.model.usermodel.DataModel
import com.elchaninov.utils.AlertDialogFragment
import com.elchaninov.utils.convertMeaningsToString
import com.elchaninov.utils.viewById
import com.example.core.AppState
import com.example.core.BaseActivity
import com.example.core.Settings
import com.example.core.Theme
import com.example.core.view.SearchDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState>(), AlertDialogFragment.OnActionButtonClickListener {

    override val model: MainViewModel by viewModel()
    private val settings: Settings by inject()
    private lateinit var binding: ActivityMainBinding
    private val adapter: MainAdapter by lazy {
        MainAdapter(onListItemClickListener)
    }

    private val searchFAB by viewById<FloatingActionButton>(R.id.search_fab)
    private val mainActivityRecyclerview by viewById<RecyclerView>(R.id.main_activity_recyclerview)
    override var snackbar: Snackbar? = null

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
        settings.nightTheme?.let { setDefaultNightMode(it.value) }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        snackbar = getSnackbar(binding.root)
        viewForBlurEffect = binding.root
        model.liveDataForViewToObserve.observe(this@MainActivity) { renderData(it) }
        initViews()
    }

    private fun initViews() {
        mainActivityRecyclerview.adapter = adapter
        searchFAB.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        supportActionBar?.title = getString(R.string.title_main_activity)

        menu?.let {
            when (settings.nightTheme) {
                Theme.DARK -> it.findItem(R.id.theme_dark).isChecked = true
                Theme.LIGHT -> it.findItem(R.id.theme_light).isChecked = true
                else -> it.findItem(R.id.theme_auto).isChecked = true
            }
        }
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
            R.id.theme_dark -> {
                settings.nightTheme = Theme.DARK
                this.recreate()
                true
            }
            R.id.theme_light -> {
                settings.nightTheme = Theme.LIGHT
                this.recreate()
                true
            }
            R.id.theme_auto -> {
                settings.nightTheme = Theme.AUTO
                this.recreate()
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