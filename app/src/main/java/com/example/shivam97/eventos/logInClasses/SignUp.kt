package com.example.shivam97.eventos.logInClasses


import android.app.Activity
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
import com.example.shivam97.eventos.R
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.a_log_in.*
import kotlinx.android.synthetic.main.f_sign_up.view.*
import java.util.concurrent.TimeUnit

class SignUp : Fragment() {
    private lateinit var mCallbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
   private lateinit var  phone: String;  private lateinit var  name: String
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
                val i=Intent(context,AccSetup::class.java)
                i.putExtra("id",verificationId)
                i.putExtra("phn",phone)
                i.putExtra("name",name)
                startActivity(i)
            }

        }
        return v

    }

    private fun checkFields(v: View?) {

        name = v?.user_name?.text.toString()
        phone = v?.phone?.text.toString()
        val pass:String = v?.pass?.text.toString()
        if(TextUtils.isEmpty(name)) {
            v?.user_name?.error = "Required"
            return }
        if(TextUtils.isEmpty(phone))
        {   v?.phone?.error="Required"
            return}
        if(TextUtils.isEmpty(pass))
        {   v?.pass?.error="Required"
            return}
        if(v?.c_pass?.text.toString() != pass)
        {   v?.c_pass?.error="Password doesn't match"
            return
        }

        phone="+91$phone"
        verifyPhone(phone)
    }

    private fun verifyPhone(phone: String) {
        setProgress(true)
        val refer= FirebaseDatabase.getInstance().reference
        refer.child("users").orderByChild("phn").equalTo(phone)
                .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.value!=null) {
                        setProgress(false)
                        Toast.makeText(context, "Number Already Registered!", Toast.LENGTH_LONG).show()
                        return
                    }
                        else{
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                phone,        // Phone number to verify
                                60,                 // Timeout duration
                                TimeUnit.SECONDS,   // Unit of timeout
                                context as Activity,               // Activity (for callback binding)
                                mCallbacks)
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
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
