package com.example.shivam97.eventos.logInClasses

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.Constraints
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.shivam97.eventos.MainActivity
import com.example.shivam97.eventos.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.a_acc_setup.*

abstract class AccSetup : AppCompatActivity() {

    private lateinit var mVerificationId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_acc_setup)
        overridePendingTransition(R.anim.flash,0)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout,OTPFrag()) /*OTPFrag*/
                .setCustomAnimations(R.anim.f_anim,0)
                .commit()

    }

     fun verifyOTPAndSignIn(code: String) {
         login_progress.visibility=View.VISIBLE
         mVerificationId=intent.getStringExtra("id")
        val credential = PhoneAuthProvider.getCredential(mVerificationId, code)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful){
                // Sign in success, update UI with the signed-in user's information
                Log.d(Constraints.TAG, "signInWithCredential:success")
                val user = task.result?.user
                val refer=FirebaseDatabase.getInstance().reference.child("users").child(user!!.uid)
                refer.child("phn").setValue(intent.getStringExtra("phn"))
                refer.child("name").setValue(intent.getStringExtra("name"))
                Toast.makeText(baseContext,"Number Verified", Toast.LENGTH_LONG).show()
               login_progress.visibility= View.INVISIBLE
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout,UsrTypFrag())
                        .setCustomAnimations(R.anim.flash,0)
                        .commit()
            }
            else{
                // Sign in failed, display a message and update the UI
                login_progress.visibility= View.INVISIBLE
                Log.w(Constraints.TAG, "signInWithCredential:failure", task.exception)
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    // The verification code entered was invalid
                    Toast.makeText(baseContext,"Code doesn't match", Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    fun setUsrTypAndClg(userType: Int, clg: Int) {
        startActivity(Intent(baseContext,MainActivity::class.java))
    }
}
