package edu.miu.profileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class AboutFragment : Fragment(R.layout.fragment_about) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<LinearLayout>(R.id.view_mobile).setOnClickListener { onClickPhone() }
        view.findViewById<LinearLayout>(R.id.view_email).setOnClickListener { onClickEmail() }
        view.findViewById<LinearLayout>(R.id.view_linkedin).setOnClickListener { onClickLinkedin() }
        view.findViewById<LinearLayout>(R.id.view_github).setOnClickListener { onClickGithub() }
    }

    private fun onClickPhone() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:6418192598")
        startActivity(intent)
    }

    private fun onClickEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, "oliver.anh.nguyen@gmail.com")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Profile App")
        intent.putExtra(Intent.EXTRA_TEXT, "Hello everybody!!!")
        startActivity(intent)
    }

    private fun onClickLinkedin() {
        val intent = Intent(context, WebviewActivity::class.java)
        intent.putExtra("url", "https://www.linkedin.com/in/oliver-anh-nguyen1990/")
        startActivity(intent)
    }

    private fun onClickGithub() {
        val intent = Intent(context, WebviewActivity::class.java)
        intent.putExtra("url", "https://github.com/oliver-anh-nguyen")
        startActivity(intent)
    }
}