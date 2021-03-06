package com.example.shivam97.eventos.logInClasses


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.example.shivam97.eventos.Eventos.*
import com.example.shivam97.eventos.MyNetworkRequest

import com.example.shivam97.eventos.R
import com.example.shivam97.eventos.mainFragments.MainActivity
import org.json.JSONObject

class SignIn : Fragment() {

    private val SIGNIN_API= "$BASE_URL/api/user/login";
    private lateinit var editUsername:EditText
    private lateinit var editPassword:EditText
    private lateinit var btnSignIn:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.f_sign_in, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editUsername=view.findViewById(R.id.edit_username)
        editPassword=view.findViewById(R.id.edit_password)
        btnSignIn=view.findViewById(R.id.btn_sign)

        btnSignIn.setOnClickListener {
            val username=editUsername.text.toString()
            val password=editPassword.text.toString()

            if(username != "" && password!="")
            {
                val body= HashMap<String,String>()
                body["userID"] = username
                body["password"] = password
                request.makeRequest(Request.Method.POST,SIGNIN_API,body,object : MyNetworkRequest.Callback{
                    override fun onSuccessResponse(response: String?)  {
                        val obj=JSONObject(response)
                        val s=obj.getString("status")
                        if(s== "login_redirect")

                        {
                            preferences.userID=obj.getJSONObject("data").getString("id")
                            preferences.token=obj.getJSONObject("data").getString("remember_token")
                            startActivity(Intent(context,MainActivity::class.java))
                            activity?.finish()
                        }
                        else{
                            Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailed(errorResponse: String?) {
                        Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show()
                    }
                })
            }

        }

    }


}
