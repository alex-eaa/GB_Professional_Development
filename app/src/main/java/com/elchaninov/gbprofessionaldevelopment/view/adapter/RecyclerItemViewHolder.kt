package com.elchaninov.gbprofessionaldevelopment.view.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityMainRecyclerviewItemBinding
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel

class RecyclerItemViewHolder(viewBinding: ActivityMainRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(data: DataModel) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text = data.text

            itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                data.meanings?.get(0)?.translation?.translation
        }
    }
}