package com.example.shivam97.eventos.logInClasses

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.Constraints
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.example.shivam97.eventos.Eventos.*
import com.example.shivam97.eventos.MainActivity
import com.example.shivam97.eventos.MyNetworkRequest
import com.example.shivam97.eventos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.a_acc_setup.*

abstract class AccSetup : AppCompatActivity() {

    private lateinit var mVerificationId: String
    private var API= "$BASE_URL/api/user/create"
   private lateinit var i: Intent
    private lateinit var myNetworkRequest: MyNetworkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_acc_setup)
        overridePendingTransition(R.anim.flash,0)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout,UsrTypFrag()) /*OTPFrag*/
                .setCustomAnimations(R.anim.f_anim,0)
                .commit()
        i=intent
        myNetworkRequest= MyNetworkRequest()
    }

     fun verifyOTPAndSignIn(code: String) {

         login_progress.visibility=View.VISIBLE
         mVerificationId=intent.getStringExtra("id")
        val credential = PhoneAuthProvider.getCredential(mVerificationId, code)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful){
                // Sign in success, update UI with the signed-in user's information
                Log.d(Constraints.TAG, "signInWithCredential:success")
                Toast.makeText(baseContext,"Number Verified", Toast.LENGTH_LONG).show()
               login_progress.visibility= View.INVISIBLE

                val body= HashMap<String,String>()
                body["name"] = i.getStringExtra("name")
                body["email"]=i.getStringExtra("email")
                body["phone"]=i.getStringExtra("phn")
                body["password"]=i.getStringExtra("pass")

                myNetworkRequest.makeRequest(Request.Method.POST,API,body, object : MyNetworkRequest.Callback {

                    override fun onSuccessResponse(response: String?) {
                        val data=SignUpResponse(response)
                        if (data.status == "login_redirect")
                        {
                            Toast.makeText(this@AccSetup,"Account created",
                                    Toast.LENGTH_LONG).show()
                            EVENTOS.setUserID(data.userId)
                        }
                        else
                            Toast.makeText(this@AccSetup,"Some error occurred while creating you account",
                                    Toast.LENGTH_LONG).show()
                    }

                    override fun onFailed(errorResponse: String?) {
                        Log.e("SignUp Error",errorResponse)
                    }



                })
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout,UsrTypFrag())
                        .setCustomAnimations(R.anim.flash,0)
                        .commit()


            }
            else{
                // Sign in failed, display a message and update the UI
                login_progress.visibility= View.INVISIBLE
                Log.w(Constraints.TAG, "signInWithCredential:failure", task.exception)
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    // The verification code entered was not invalid
                    Toast.makeText(baseContext,"Code doesn't match", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun setUsrTypAndClg(userType: Int, clg: Int) {
        finish()
        startActivity(Intent(baseContext,MainActivity::class.java))
    }
}
