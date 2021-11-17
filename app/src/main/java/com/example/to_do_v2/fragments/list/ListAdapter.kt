package com.example.to_do_v2.fragments.list

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_v2.R
import com.example.to_do_v2.data.model.Priority
import com.example.to_do_v2.data.model.ToDoData


class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var dataList = emptyList<ToDoData>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titel_ad: TextView = view.findViewById(R.id.title_txt)
        val priority_ad: CardView = view.findViewById(R.id.priority_indicator)
        val discription_ad: TextView = view.findViewById(R.id.discription_txt)
        val background: ConstraintLayout = view.findViewById(R.id.place_holder_background)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.place_holder, viewGroup, false)

        return ViewHolder(view)
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataList at this position and replace the
        // contents of the view with that element
        viewHolder.titel_ad.text = dataList[position].title
        viewHolder.discription_ad.text = dataList[position].description
        when(dataList[position].priority){
            Priority.HIGH->viewHolder.priority_ad.setCardBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.red))
            Priority.MEDIUM->viewHolder.priority_ad.setCardBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.yellow))
            Priority.LOW->viewHolder.priority_ad.setCardBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.green))
        }
        viewHolder.background.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            viewHolder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData (toDoData: List<ToDoData>){
        this.dataList = toDoData
        notifyDataSetChanged()
    }
}