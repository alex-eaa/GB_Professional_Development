package com.elchaninov.descriptionScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import coil.load
import coil.request.Disposable
import coil.transform.RoundedCornersTransformation
import com.elchaninov.descriptionScreen.databinding.ActivityDescriptionBinding
import com.elchaninov.utils.AlertDialogFragment
import com.elchaninov.utils.OnlineLiveData
import com.elchaninov.utils.disableBlurEffect
import com.elchaninov.utils.enableBlurEffect
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DescriptionActivity : ScopeActivity(), AlertDialogFragment.OnBlurEffectDisable {

    private lateinit var binding: ActivityDescriptionBinding
    private val model: DescriptionViewModel by viewModel()
    private var menu: Menu? = null
    private var word: String? = null
    private var imageLoader: Disposable? = null
    private var isOnline: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionbarHomeButtonAsUp()
        setData()
        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener { startLoadingOrShowError() }
        model.liveDataForViewToObserve.observe(this@DescriptionActivity) { updateIconOnMenu(it) }
        subscribeToNetworkChange()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.description_menu, menu)
        this.menu = menu
        word?.let { model.getData(it) }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menu_favorite -> {
                model.toggleEntityTranslation(word)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.let {
            it.title = getString(R.string.title_description_activity)
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun updateIconOnMenu(isFavorite: Boolean) {
        menu?.findItem(R.id.menu_favorite)?.let {
            if (isFavorite) it.setIcon(R.drawable.ic_baseline_grade_24)
            else it.setIcon(R.drawable.ic_outline_grade_24)
        }
    }

    private fun setData() {
        val bundle = intent.extras
        word = bundle?.getString(WORD_EXTRA)
        binding.descriptionHeader.text = bundle?.getString(WORD_EXTRA)
        binding.descriptionTextview.text = bundle?.getString(DESCRIPTION_EXTRA)

        val imageLink = bundle?.getString(URL_EXTRA)
        if (imageLink.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
            useCoilToLoadPhoto(binding.descriptionImageview, imageLink)
        }
    }

    private fun startLoadingOrShowError() {
        if (isOnline) {
            setData()
        } else {
            AlertDialogFragment.newInstance(
                getString(R.string.dialog_title_device_is_offline),
                getString(R.string.dialog_message_device_is_offline)
            ).show(
                supportFragmentManager,
                DIALOG_FRAGMENT_TAG
            )
            stopRefreshAnimationIfNeeded()
            binding.root.enableBlurEffect()
        }
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
            binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageLink: String) {
        imageLoader = imageView.load("https:$imageLink") {
            listener(
                onSuccess = { _, _ ->
                    stopRefreshAnimationIfNeeded()
                },
                onError = { _, _ ->
                    stopRefreshAnimationIfNeeded()
                }
            )
            placeholder(R.drawable.progress_animation)
            transformations(RoundedCornersTransformation(8F))
            error(R.drawable.ic_load_error_vector)
            crossfade(750)
                .build()
        }
    }

    private fun subscribeToNetworkChange() {
        OnlineLiveData(this).observe(this) {
            isOnline = it
            startLoadingOrShowError()
        }
    }

    override fun onDestroy() {
        imageLoader?.dispose()
        super.onDestroy()
    }

    override fun disableBlurEffect() {
        binding.root.disableBlurEffect()
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "8c7dff51-9769-4f6d-bbee-a3896085e76e"
        private const val WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b0"
        private const val DESCRIPTION_EXTRA = "0eeb92aa-520b-4fd1-bb4b-027fbf963d9a"
        private const val URL_EXTRA = "6e4b154d-e01f-4953-a404-639fb3bf7281"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            url: String?,
        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
            putExtra(DESCRIPTION_EXTRA, description)
            putExtra(URL_EXTRA, url)
        }
    }
}
