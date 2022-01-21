package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Tells recycler view how to display data we give it
 */
class TaskItemAdapter(val listOfItems: List<String>,
                      val longclickListener: OnLongClickListener,
                      val clickListener: OnClickListener) :
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    interface OnLongClickListener {
        fun onItemLongClicked(position: Int)
    }

    interface OnClickListener {
        fun onItemClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val taskView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(taskView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // get item from list
        val item = listOfItems.get(position)

        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        // length of a list
        return listOfItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Store references to elements in our layout
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener {
                longclickListener.onItemLongClicked(adapterPosition)
                true
            }

            itemView.setOnClickListener {
                clickListener.onItemClicked(adapterPosition)
                true
            }
        }
    }
}