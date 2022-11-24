package edu.miu.profileapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_work.view.*

class WorkAdapter(val context: Context, var listWorks: ArrayList<WorkEntity>) : RecyclerView.Adapter<WorkAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_work, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var work = listWorks[position]
        holder.itemView.tvTitle.text = work.title
        holder.itemView.tvName.text = work.name
        holder.itemView.tvLocation.text = work.location
        holder.itemView.tvTime.text = work.time
    }

    override fun getItemCount(): Int {
        return listWorks.size
    }
}