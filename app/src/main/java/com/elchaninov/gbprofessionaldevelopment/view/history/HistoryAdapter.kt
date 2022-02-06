package com.elchaninov.gbprofessionaldevelopment.view.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityHistoryRecyclerviewItemBinding
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel

class HistoryAdapter(
    private var onListItemClickListener: (DataModel) -> Unit,
) : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val binding = ActivityHistoryRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onListItemClickListener(data[position]) }
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: ActivityHistoryRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerHistoryTextviewRecyclerItem.text = data.text
                if(data.favorite) binding.favoriteImage.setImageResource(R.drawable.ic_baseline_grade_24)

            }
        }
    }
}
