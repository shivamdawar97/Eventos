package com.example.shivam97.eventos.logInClasses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shivam97.eventos.R
import kotlinx.android.synthetic.main.f_otp.view.*

class OTPFrag : Fragment() {
      internal lateinit var view: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.f_otp, container, false)
        view.btn_sub.setOnClickListener {
            val code= if( view.editText.text !=null) view.editText.text.toString() else null
            if(code!=null)
            (context as AccSetup).verifyOTPAndCreateAcc(code)
        }
        return view
    }

}

