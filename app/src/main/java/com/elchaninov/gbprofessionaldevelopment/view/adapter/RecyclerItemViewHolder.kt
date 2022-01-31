package com.elchaninov.gbprofessionaldevelopment.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityMainRecyclerviewItemBinding
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel

class RecyclerItemViewHolder(private val viewBinding: ActivityMainRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(data: DataModel) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                viewBinding.headerTextviewRecyclerItem.text = data.text

                viewBinding.descriptionTextviewRecyclerItem.text =
                    data.meanings?.get(0)?.translation?.translation
            }
        }
    }
}