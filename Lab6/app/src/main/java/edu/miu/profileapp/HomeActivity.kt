package edu.miu.profileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), WorkDialogDelegate {

    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        adapter = MyAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "HOME"
                    tab.setIcon(R.drawable.ic_baseline_home_24)
                }
                1 -> {
                    tab.text = "ABOUT"
                    tab.setIcon(R.drawable.ic_baseline_person_24)
                }
                2 -> {
                    tab.text = "WORK"
                    tab.setIcon(R.drawable.ic_baseline_work_24)
                }
                3 -> {
                    tab.text = "CONTACT"
                    tab.setIcon(R.drawable.ic_baseline_contact_24)
                }
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(
            this,
            item.title.toString(),
            Toast.LENGTH_LONG
        ).show()
        return super.onOptionsItemSelected(item)
    }

    override fun addWork(work: WorkEntity) {
        adapter.addWork(work)
    }
}