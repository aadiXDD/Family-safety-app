package com.example.famsaf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberAdapter(private val listMembers: List<MemberModel>) :
    RecyclerView.Adapter<MemberAdapter.ViewHolder>() {

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val userImage = item.findViewById<ImageView>(R.id.img_user)
        var userName = item.findViewById<TextView>(R.id.member_title)
        var userTime = item.findViewById<TextView>(R.id.member_time)
        var userAddress = item.findViewById<TextView>(R.id.member_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.item_member, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMembers[position]
        holder.userName.text = item.name
        holder.userTime.text = item.time
        holder.userAddress.text = item.address
    }

    override fun getItemCount(): Int {
        return listMembers.size
    }
}