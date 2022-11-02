package edu.miu.walmart.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import edu.miu.walmart.R
import edu.miu.walmart.adapter.ProductAdapter
import edu.miu.walmart.entity.Product
import kotlinx.android.synthetic.main.activity_list_electronics.*

class ListElectronicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_electronics)

        val products = ArrayList<Product>()
        products.add(Product(title = "RGA Voyager 7 16GB Android Tablet", price = 35.0, color = "Black", image = R.drawable.voyager, itemId = "1", desc = "Introducing the RCA Voyager 7-inch Google Certified Table. Operating on Android's sweetest operating system yet, Android 6.0 (Marshmallow), you can enjoy more of what the Google Play store has to offer"))
        products.add(Product(title = "HP Flyer Red 15.6 Laptop", price = 299.0, color = "Black & Red", image = R.drawable.hpflyer, itemId = "2", desc = "Description"))
        products.add(Product(title = "VIZIO 70 Class 4K", price = 1298.0, color = "Black", image = R.drawable.vizio, itemId = "3", desc = "Description"))
        products.add(Product(title = "Epson WorkForce WF-2750", price = 69.0, color = "Black", image = R.drawable.epson, itemId = "4", desc = "Description"))
        recycleProducts.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter(this, products)
        recycleProducts.adapter = adapter
    }
}