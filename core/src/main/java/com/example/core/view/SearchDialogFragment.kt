package com.example.core.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import com.example.core.databinding.BottomSheetDialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchDialogFragment : BottomSheetDialogFragment() {

    private val job: Job = Job()
    private val queryStateFlow = MutableStateFlow("")

    private var _binding: BottomSheetDialogLayoutBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: OnSearchClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSearchClickListener) onSearchClickListener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addOnClearClickListener()
    }

    private fun initView() {
        CoroutineScope(Dispatchers.Main + job).launch {
            queryStateFlow.debounce(500)
                .filter { query ->
                    return@filter query.isNotEmpty()
                }
                .distinctUntilChanged()
                .map { query ->
                    onSearchClickListener?.onFlowSearch(query)
                }
                .collect()
        }

        binding.searchButtonTextview.isEnabled = false
        binding.searchButtonTextview.setOnClickListener { executeSearch() }

        binding.searchEditText.doOnTextChanged { _, _, _, _ ->
            queryStateFlow.value = binding.searchEditText.text.toString()

            if (binding.searchEditText.text != null &&
                binding.searchEditText.text.toString().isNotEmpty()
            ) {
                binding.searchButtonTextview.isEnabled = true
                binding.clearTextImageview.visibility = View.VISIBLE
            } else {
                binding.searchButtonTextview.isEnabled = false
                binding.clearTextImageview.visibility = View.GONE
            }
        }

        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_SEARCH) {
                executeSearch()
                handled = true
            }
            handled
        }
    }

    private fun executeSearch() {
        onSearchClickListener?.onClick(binding.searchEditText.text.toString())
        dismiss()
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        job.cancel()
        super.onDestroyView()
    }

    private fun addOnClearClickListener() {
        binding.clearTextImageview.setOnClickListener {
            binding.searchEditText.setText("")
            binding.searchButtonTextview.isEnabled = false
        }
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
        fun onFlowSearch(searchWord: String)
    }

    companion object {
        fun newInstance(): SearchDialogFragment = SearchDialogFragment()
    }
}