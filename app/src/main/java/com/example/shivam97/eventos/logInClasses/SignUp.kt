package com.example.shivam97.eventos.logInClasses

import android.content.Intent
import android.os.Bundle
import android.support.constraint.Constraints
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.android.volley.Request
import com.example.shivam97.eventos.Eventos
import com.example.shivam97.eventos.MyNetworkRequest
import com.example.shivam97.eventos.R
import com.example.shivam97.eventos.eventClasses.AddEvent
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.a_acc_setup.*
import kotlinx.android.synthetic.main.a_log_in.*
import kotlinx.android.synthetic.main.f_sign_up.view.*

class SignUp : Fragment() {

    private lateinit var mCallbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var  phone: String;  private lateinit var  name: String
    private lateinit var email:String;lateinit var pass:String
    private var API= "${Eventos.BASE_URL}/api/user/create"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.f_sign_up, container, false)
        v.btn_sign.setOnClickListener {
           checkFields(v)

        }
        mCallbacks=object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential?) {
                Log.d(Constraints.TAG, "onVerificationCompleted:$credential")
                setProgress(false)
            }


            override fun onVerificationFailed(e: FirebaseException?) {
               setProgress(false)
                Log.w(Constraints.TAG, "onVerificationFailed", e)
                if (e is FirebaseAuthInvalidCredentialsException)
                    Toast.makeText(context,"Invalid Request: $e", Toast.LENGTH_LONG).show()
                else if (e is FirebaseTooManyRequestsException)
                    Toast.makeText(context,"The SMS quota for the project has been exceeded: $e", Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(verificationId: String?, token: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(verificationId, token)
                activity?.finish()
                Toast.makeText(context,"code sent",Toast.LENGTH_LONG).show()

                goToSignIn(verificationId)

            }

        }
        return v

    }


    private fun goToSignIn(verificationId:String?) {

            setProgress(true)
            val body= HashMap<String,String>()
            body["name"] = name
            body["email"]=email
            body["phone"]=phone
            body["password"]=pass

        MyNetworkRequest().makeRequest(Request.Method.POST,API,body, object : MyNetworkRequest.Callback {

                override fun onSuccessResponse(response: String?) {
                    val data=SignUpResponse(response)
                    if (data.status == "login_redirect")
                    {
                        Toast.makeText(context,"Account created",
                                Toast.LENGTH_LONG).show()

                        Eventos.EVENTOS.setUserID(data.userId)
                        val i=Intent(context,AccSetup::class.java)
                        i.putExtra("id",verificationId)
                        activity?.finish()
                        startActivity(i)

                    }
                    else
                        Toast.makeText(context,"Some error occurred while creating you account",
                                Toast.LENGTH_LONG).show()
                }

                override fun onFailed(errorResponse: String?) {
                    Log.e("SignUp Error",errorResponse)
                }

            })

            setProgress(false)

    }

    private fun checkFields(v: View?) {

        name = v?.user_name?.text.toString()
        phone = v?.phone?.text.toString()
        pass = v?.pass?.text.toString()
        email=v?.user_email?.text.toString()

        if(TextUtils.isEmpty(name)) {
            v?.user_name?.error = "Required"
            return }
        if(TextUtils.isEmpty(phone))
        {   v?.phone?.error="Required"
            return}
        if(TextUtils.isEmpty(email)){
            v?.user_email?.error="Required"
            return
        }
        if(TextUtils.isEmpty(pass))
        {   v?.pass?.error="Required"
            return}
        if(v?.c_pass?.text.toString() != pass)
        {   v?.c_pass?.error="Password doesn't match"
            return
        }
        phone="+91$phone"
        goToSignIn(null)
       // verifyPhone(phone)
    }

    private fun verifyPhone(phone: String) {
        setProgress(true)

           // OnVerificationStateChangedCallbacks
    }
    fun setProgress(s:Boolean){
        if(s) {
            activity?.login_progress?.visibility = View.VISIBLE
            activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }else {
            //To get user interaction back you just need to add the following code
            activity?.login_progress?.visibility = View.INVISIBLE
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }
}
