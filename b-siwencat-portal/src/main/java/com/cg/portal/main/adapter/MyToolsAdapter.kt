package com.cg.portal.main.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cg.portal.R
import com.cg.portal.main.bean.MenuBean
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.portal_item_my_tools.view.*

class MyToolsAdapter(
    var context: Context,
    var data: ArrayList<MenuBean>,
    var listener: OnToolsItemClickListener
): RecyclerView.Adapter<MyToolsAdapter.MyHolder>() {


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , LayoutContainer{
        override val containerView: View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.portal_item_my_tools,null))
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.containerView.img.setImageResource(data[position].resource)
        holder.containerView.tv.text = data[position].name
        holder.containerView.content_cl.setOnClickListener {
            Handler(Looper.myLooper()!!).postDelayed(Runnable {
                listener.onToolItemClick(holder.adapterPosition)
            },300L)
        }
    }

    fun getItemData(position: Int): MenuBean? {
        return data[position]
    }

    interface OnToolsItemClickListener{
        fun onToolItemClick(position:Int)
    }
}