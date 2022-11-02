package edu.miu.walmart.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.miu.walmart.R
import edu.miu.walmart.entity.Product
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (intent.hasExtra("product")) {
            val product = intent.getSerializableExtra("product") as Product?
            tvDesProduct.text = "Item description:\n${product?.desc}"
            tvIdProduct.text = "Walmart #: " + product?.itemId
            tvColorProduct.text = "Color: " + product?.color
            product?.image?.let { imgProductDetail.setBackgroundResource(it) }
        }

    }
}