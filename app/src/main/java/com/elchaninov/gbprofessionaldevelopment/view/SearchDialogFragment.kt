package com.elchaninov.gbprofessionaldevelopment.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import com.elchaninov.gbprofessionaldevelopment.databinding.BottomSheetDialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchDialogFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDialogLayoutBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: OnSearchClickListener? = null

    private val onSearchButtonClickListener = View.OnClickListener {
        onSearchClickListener?.onClick(binding.searchEditText.text.toString())
        dismiss()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onSearchClickListener = context as OnSearchClickListener
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
        binding.searchButtonTextview.isEnabled = false
        binding.searchButtonTextview.setOnClickListener(onSearchButtonClickListener)

        binding.searchEditText.doOnTextChanged { _, _, _, _ ->
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
                onSearchClickListener?.onClick(binding.searchEditText.text.toString())
                dismiss()
                handled = true
            }
            handled
        }
    }

    override fun onDestroyView() {
        onSearchClickListener = null
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
    }

    companion object {
        fun newInstance(): SearchDialogFragment = SearchDialogFragment()
    }
}
