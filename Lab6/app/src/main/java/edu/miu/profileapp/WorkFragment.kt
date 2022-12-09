package edu.miu.profileapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_work.*

class WorkFragment : Fragment(R.layout.fragment_work) {

    private var works = ArrayList<WorkEntity>()

    private lateinit var adapter: WorkAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (context != null) {
            works.add(WorkEntity(
                title = "Senior iOS Developer",
                name = "ZaloPay",
                time = "Mar 2017 - Apr 2022",
                location = "HCM, VietNam"
            ))
            works.add(WorkEntity(
                title = "iOS Developer",
                name = "VNG",
                time = "Jan 2013 - Mar 2017",
                location = "HCM, VietNam"
            ))
            recycleWorks.layoutManager = LinearLayoutManager(context)
            adapter = WorkAdapter(requireContext(), works)
            recycleWorks.adapter = adapter
        }
        btnFloatAdd.setOnClickListener {
            showPopup()
        }
    }

    private fun showPopup() {
        val dialog = WorkDialog()
        dialog.show(parentFragmentManager, WorkDialog::class.java.name)
    }

    fun addWOrk(work: WorkEntity) {
        works.add(work)
        adapter.notifyDataSetChanged()
    }
}