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
import kotlinx.android.synthetic.main.portal_item_my_fun_detail.view.*

class MyFunDetailAdapter(
    var context: Context,
    var data: ArrayList<MenuBean>,
    var parentPosition:Int,
    var listener: OnFunDetailItemClickListener
): RecyclerView.Adapter<MyFunDetailAdapter.MyHolder>() {


    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) ,LayoutContainer{
        override var containerView:View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.portal_item_my_fun_detail,null)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.containerView.img.setImageResource(data[position].resource)
        holder.containerView.tv.text = data[position].name
        holder.containerView.content_cl.setOnClickListener{
            Handler(Looper.myLooper()!!).postDelayed(Runnable {
                listener.onFunDetailItemClick(parentPosition,holder.adapterPosition)
            },300L)
        }
    }
    interface OnFunDetailItemClickListener{
        fun onFunDetailItemClick(parentPosition: Int, position: Int)
    }
}