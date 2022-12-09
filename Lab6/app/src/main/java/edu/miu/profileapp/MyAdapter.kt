package edu.miu.profileapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyAdapter(fm: FragmentManager, lc: Lifecycle): FragmentStateAdapter(fm, lc) {
    override fun getItemCount() = 4
    val workFragment = WorkFragment()
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> PersonFragment()
            2 -> workFragment
            3 -> AboutFragment()
            else -> Fragment()
        }
    }

    fun addWork(work: WorkEntity) {
        workFragment.addWOrk(work)
    }
}