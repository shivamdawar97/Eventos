package com.example.shivam97.eventos.LogInClasses


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast

import com.example.shivam97.eventos.R
import kotlinx.android.synthetic.main.a_log_in.*
import kotlinx.android.synthetic.main.f_sign_up.view.*

class SignUp : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.f_sign_up, container, false)
        v.btn_sign.setOnClickListener {
           checkFields(v)

            /* To get user interaction back you just need to add the following code
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            */
        }
        return v

    }

    private fun checkFields(v: View?) {

        val name:String = v?.user_name?.text.toString()
        val phone:String = v?.phone?.text.toString()
        val pass:String = v?.pass?.text.toString()
        val confirm:String = v?.c_pass?.text.toString()
        if(TextUtils.isEmpty(name)) {
            v?.user_name?.error = "Required"
            return }
        if(TextUtils.isEmpty(phone))
        {   v?.phone?.error="Required"
            return}
        if(TextUtils.isEmpty(pass))
        {   v?.pass?.error="Required"
            return}
        if(confirm != pass)
        {   v?.c_pass?.error="Password doesn't match"
            return
        }
        activity?.login_progress?.visibility=View.VISIBLE

        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)


    }
}
