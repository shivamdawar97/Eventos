package com.example.shivam97.eventos.logInClasses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.Constraints
import com.android.volley.Request
import com.example.shivam97.eventos.Eventos
import com.example.shivam97.eventos.Eventos.*
import com.example.shivam97.eventos.mainFragments.MainActivity
import com.example.shivam97.eventos.MyNetworkRequest
import com.example.shivam97.eventos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.a_acc_setup.*

 class AccSetup : AppCompatActivity() {

   private lateinit var mVerificationId: String

     private var CREATE_ACC_API= "${Eventos.BASE_URL}/api/user/create"
     private var CREATE_PROFSN_API= "${Eventos.BASE_URL}/api/user/createProfession"

     private lateinit var data: HashMap<String,String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_acc_setup)
        overridePendingTransition(R.anim.flash,0)

        goToSignIn()
//        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,OTPFrag())
//                .setCustomAnimations(R.anim.flash,0)
//                .commit()

    }

    fun verifyOTPAndCreateAcc(code: String) {

         login_progress1.visibility=View.VISIBLE
         mVerificationId=intent.getStringExtra("id")
        val credential = PhoneAuthProvider.getCredential(mVerificationId, code)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful){
                // Sign in success, update UI with the signed-in user's information
                Log.d(Constraints.TAG, "signInWithCredential:success")
                Toast.makeText(baseContext,"Number Verified", Toast.LENGTH_LONG).show()
               login_progress1.visibility= View.INVISIBLE
                goToSignIn()

            }
            else{
                // Sign in failed, display a message and update the UI
                login_progress1.visibility= View.INVISIBLE
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
        val params=  HashMap<String,String>()
       //  params.put("userID",)
       //  request.makeRequest()
        startActivity(Intent(baseContext, MainActivity::class.java))
    }

     private fun goToSignIn() {

         data=intent.getSerializableExtra("data") as HashMap<String, String>
         MyNetworkRequest().makeRequest(Request.Method.POST,CREATE_ACC_API,data, object : MyNetworkRequest.Callback {

             override fun onSuccessResponse(response: String?) {
                 val data=SignUpResponse(response)

                 if (data.status == "login_redirect")
                 {
                     Toast.makeText(this@AccSetup,"Account created",
                             Toast.LENGTH_LONG).show()
                        preferences.userID=data.userId
                        preferences.token=data.token

                     supportFragmentManager.beginTransaction().replace(R.id.frameLayout,OTPFrag())
                             .setCustomAnimations(R.anim.flash,0)
                             .commit()

                 }
                 else
                     Toast.makeText(this@AccSetup,"Some error occurred while creating you account",
                             Toast.LENGTH_LONG).show()
             }

             override fun onFailed(errorResponse: String?) {
                 Log.e("SignUp Error",errorResponse)
             }

         })
     }

}
