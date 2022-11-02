package edu.miu.walmart.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.miu.walmart.R
import edu.miu.walmart.activities.DetailActivity
import edu.miu.walmart.entity.Product
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter(val context: Context, var listProducts: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.MyViewHolder, position: Int) {
        var product = listProducts[position]
        holder.itemView.tvName.text =  product.title
        holder.itemView.tvPrice.text = "Price: " + product.price.toString()
        holder.itemView.tvColor.text = "Color: " + product.color
        holder.itemView.imgProduct.setBackgroundResource(product.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("product", product)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listProducts.size
    }
}