package com.nutrition.ui.horizontalRecycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import coil.load
import com.nutrition.R
import kotlinx.android.synthetic.main.my_view_holder.view.*
import kotlin.random.Random

class ViewPagerFragment : Fragment(R.layout.view_pager_fragment) {

    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler)

        val myAdapter = ViewsAdapter().apply {
            submitList(
                List(5) { HolderModel(
                    Random.nextLong(),
                    "https://c8.alamy.com/comp/GKC9R4/blue-sight-view-GKC9R4.jpg",
                    "My super title $it",
                    "Description should be a far longer than title. That is why I try to make it a little bit longer. This is my description number #$it"
                ) }
            )
        }
        recyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(DotsPagerIndicatorDecoration())
        }
        /*val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)*/
    }
}

class ViewsAdapter : ListAdapter<HolderModel, MyViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<HolderModel>() {
        override fun areItemsTheSame(oldItem: HolderModel, newItem: HolderModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HolderModel, newItem: HolderModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_view_holder, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: HolderModel) {
        with(view) {
            holder_image.load(item.image) {
                crossfade(true)
            }
            holder_title.text = item.title
            holder_desc.text = item.desc
        }
    }
}

data class HolderModel(val id: Long, val image: String, val title: String, val desc: String)