package com.example.shivam97.eventos.LogInClasses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shivam97.eventos.R

class OTPFrag : Fragment() {
      internal lateinit var view: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.f_otp, container, false)
        return view
    }

}

