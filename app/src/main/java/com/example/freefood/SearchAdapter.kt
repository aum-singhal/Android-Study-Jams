package com.example.freefood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchAdapter(private val searchlist : ArrayList<Service>): RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = searchlist[position]

        holder.name.text = current.name
        holder.phone.text = current.phone
        holder.address.text = current.address
        holder.date.text = current.date
        holder.pin.text = current.pin
        holder.menu.text = current.menu
        holder.count.text = current.count

    }

    override fun getItemCount(): Int {
        return searchlist.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.pname)
        val phone : TextView = itemView.findViewById(R.id.pphone)
        val address : TextView = itemView.findViewById(R.id.addr)
        val date : TextView = itemView.findViewById(R.id.fdate)
        val pin : TextView = itemView.findViewById(R.id.ppin)
        val menu : TextView = itemView.findViewById(R.id.fmenu)
        val count : TextView = itemView.findViewById(R.id.pquantity)
    }

}