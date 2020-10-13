package com.nutrition.ui.wheelPicker

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.nutrition.R
import kotlinx.android.synthetic.main.fragment_wheel_picker.*
import kotlinx.android.synthetic.main.fragment_wheel_picker.view.*
import kotlinx.android.synthetic.main.wheel_picker_holder.view.*
import kotlin.random.Random

class WheelPickerFragment : Fragment(R.layout.fragment_wheel_picker) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.checkValueBtn)
        val wheelAdapter = WheelPickerAdapter().apply {
            val list = List(10) {
                WheelPickerModel(Random.nextLong(), it)
            }
            submitList(list.asReversed())
        }

        wheelPickerRecycler.apply {
            val sliderLayoutManager = SliderLayoutManager(requireContext())
            layoutManager = sliderLayoutManager
            adapter = wheelAdapter

            val padding =
                if (context.resources.configuration.orientation == OrientationHelper.VERTICAL) Resources.getSystem().displayMetrics.widthPixels / 2.5 else Resources.getSystem().displayMetrics.widthPixels / 8
            setPadding(0, padding.toInt(), 0, padding.toInt())

            sliderLayoutManager.setOnItemSelectedListener(object : OnItemSelectedListener {
                @SuppressLint("OnClickListenerDetector")
                override fun onItemSelectedPosition(position: Int) {
                    button.setOnClickListener {
                        Toast.makeText(
                            requireContext(),
                            "Pos: $position, value: ${wheelAdapter.currentList[position]}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
    }
}

class SliderLayoutManager(context: Context, val linearSnapHelper: SnapHelper = LinearSnapHelper(), orientation: Int = RecyclerView.VERTICAL) :
    LinearLayoutManager(
        context,
        orientation,
        false
    ) {
    private var listener: OnItemSelectedListener? = null
    private lateinit var recyclerView: RecyclerView


    override fun onAttachedToWindow(view: RecyclerView) {
        super.onAttachedToWindow(view)

        recyclerView = view
        linearSnapHelper.attachToRecyclerView(recyclerView)
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            val snapView = linearSnapHelper.findSnapView(this)
            snapView?.let {
                val position = recyclerView.getChildAdapterPosition(snapView)
                listener?.onItemSelectedPosition(position)
            }
        }
    }

    fun setOnItemSelectedListener(listener: OnItemSelectedListener?) {
        this.listener = listener
    }
}

interface OnItemSelectedListener {
    fun onItemSelectedPosition(position: Int)
}

class WheelPickerAdapter : ListAdapter<WheelPickerModel, WheelPickerViewHolder>(DiffCallback) {
    object DiffCallback : DiffUtil.ItemCallback<WheelPickerModel>() {
        override fun areItemsTheSame(
            oldItem: WheelPickerModel,
            newItem: WheelPickerModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WheelPickerModel,
            newItem: WheelPickerModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WheelPickerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.wheel_picker_holder,
            parent,
            false
        )
        return WheelPickerViewHolder(view)
    }

    override fun onBindViewHolder(holder: WheelPickerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class WheelPickerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: WheelPickerModel) = with(view) {
        wheel_number.text = item.value.toString()
    }
}

data class WheelPickerModel(val id: Long, val value: Int)

