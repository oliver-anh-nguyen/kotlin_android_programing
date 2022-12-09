package edu.miu.profileapp

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_work.*

class WorkDialog : DialogFragment() {
    private lateinit var delegate: WorkDialogDelegate
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_work, null)

            builder.setView(view).apply {
                view.findViewById<Button>(R.id.btnCancel)?.setOnClickListener {
                    dismiss()
                }

                view.findViewById<Button>(R.id.btnAdd)?.setOnClickListener {
                    val title = view.findViewById<EditText>(R.id.tvTitle).text.toString().trim()
                    val name = view.findViewById<EditText>(R.id.tvName).text.toString().trim()
                    val time = view.findViewById<EditText>(R.id.tvTime).text.toString().trim()
                    val location = view.findViewById<EditText>(R.id.tvLocation).text.toString().trim()
                    if (validate(title, name, time, location)) {
                        delegate.addWork(WorkEntity(name, title, time, location))
                    }
                    dismiss()
                }

            }
            builder.create()
        } ?: throw IllegalStateException("Error!!!")

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    fun validate(title: String, name: String, time: String, location: String): Boolean{
        if(title.isEmpty()){
            Toast.makeText(context, "Please enter title!", Toast.LENGTH_LONG).show()
            return false
        }
        if(name.isEmpty()){
            Toast.makeText(context, "Please enter name!", Toast.LENGTH_LONG).show()
            return false
        }
        if(time.isEmpty()){
            Toast.makeText(context, "Please enter duration!", Toast.LENGTH_LONG).show()
            return false
        }
        if(location.isEmpty()){
            Toast.makeText(context, "Please enter location!", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        delegate = context as WorkDialogDelegate
    }
}