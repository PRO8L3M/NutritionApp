package com.nutrition.ui.swipeRecyclerView

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nutrition.R
import kotlinx.android.synthetic.main.swipe_view_holder.view.*

inline fun <reified T> getDiffUtilCallback(crossinline areItemsTheSame: (oldItem: T, newItem: T) -> Boolean) : DiffUtil.ItemCallback<T> {
    return object: DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = areItemsTheSame(oldItem, newItem)
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    }
}

private val swipeDiffUtil = getDiffUtilCallback<SwipeModel> { oldItem, newItem -> oldItem.id == newItem.id }

class SwipeRemovingAdapter : ListAdapter<SwipeModel, SwipeViewHolder>(swipeDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        val view = View.inflate(parent.context, R.layout.swipe_view_holder, parent)
        return SwipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SwipeViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    fun bind(model: SwipeModel) = with(model) {
        view.title.text = title
        view.subtitle.text = subtitle
    }
}

data class SwipeModel(val id: Long, val title: String, val subtitle: String)