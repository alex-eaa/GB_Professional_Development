package com.elchaninov.gbprofessionaldevelopment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityMainRecyclerviewItemBinding
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel


class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>
) :
    RecyclerView.Adapter<RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val binding = ActivityMainRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onListItemClickListener.onItemClick(data[position]) }
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}