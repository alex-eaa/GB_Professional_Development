package com.elchaninov.gbprofessionaldevelopment.view.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityHistoryRecyclerviewItemBinding
import com.elchaninov.gbprofessionaldevelopment.model.usermodel.DataModel

class HistoryAdapter(
    private var onListItemClickListener: (DataModel) -> Unit,
    private var onFavoriteClickListener: (DataModel) -> Unit,
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
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: ActivityHistoryRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { onListItemClickListener(data[layoutPosition]) }
            binding.favoriteImage.setOnClickListener { onFavoriteClickListener(data[layoutPosition]) }
        }

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerHistoryTextviewRecyclerItem.text = data.text
                if (data.favorite) binding.favoriteImage.setImageResource(R.drawable.ic_baseline_grade_24)
                else binding.favoriteImage.setImageResource(R.drawable.ic_outline_grade_24)
            }
        }
    }
}
