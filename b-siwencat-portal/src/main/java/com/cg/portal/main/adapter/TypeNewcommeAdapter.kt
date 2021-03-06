package com.cg.portal.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cg.base.utils.ToastUtils
import com.cg.portal.R
import com.cg.portal.main.bean.StoreProductsBean
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.portal_item_product.view.*

class TypeNewcommeAdapter(var context: Context,
                          var data:ArrayList<StoreProductsBean.Product>?,
                          var itemWidth:Int,
                          private var firstLeftMargin:Int,
                          private var leftMargin:Int) :
    RecyclerView.Adapter<TypeNewcommeAdapter.MyHolder>() {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView),LayoutContainer{
        override val containerView: View = itemView
        init {
            itemView.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.portal_item_product,parent,false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        reSetRecyclerViewItemParams(holder.itemView,position)
        Glide.with(context).load(data?.get(position)?.url?.get(0)).into(holder.itemView.iv_content)
        holder.itemView.tv_name.text = data?.get(position)?.name ?: ""
        holder.itemView.tv_price.text = data?.get(position)?.price ?: ""

        holder.itemView.setOnClickListener{
            ToastUtils.show("点击了：" + data?.get(position)?.name)
        }
    }

    private fun reSetRecyclerViewItemParams(itemView: View,adapterPosition:Int) {
        val params = itemView.layoutParams as ConstraintLayout.LayoutParams
        params.width = itemWidth
        params.leftMargin = if (adapterPosition == 0) firstLeftMargin else leftMargin
        itemView.layoutParams = params
        val ivParams = itemView.iv_content.layoutParams as ConstraintLayout.LayoutParams
        ivParams.width = itemWidth
        ivParams.height = itemWidth
        itemView.iv_content.layoutParams = ivParams
    }
}