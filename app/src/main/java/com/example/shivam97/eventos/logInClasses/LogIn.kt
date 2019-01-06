package com.example.shivam97.eventos.logInClasses
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.shivam97.eventos.R
import kotlinx.android.synthetic.main.a_log_in.*

class LogIn : AppCompatActivity() {

    lateinit var anim:Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_log_in)
        anim= AnimationUtils.loadAnimation(this,R.anim.zoom_in)
        callSignIn(sign_in)
        sign_in.setOnClickListener {
            view->
           callSignIn(view)
        }

        sign_up.setOnClickListener {view->

            view.elevation=20f
            view.background=getDrawable(R.drawable.half_round)
            view.startAnimation(anim)
            sign_in.clearAnimation()
            sign_in.elevation=0f
            sign_in.background=resources.getDrawable(R.drawable.half_round_faded)
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.f_anim,0)
                    .replace(R.id.frameLayout,SignUp()).commit()
        }
    }

    private fun callSignIn(view: View?) {

        view?.elevation=20f
        view?.background=resources.getDrawable(R.drawable.half_round)
        view?.startAnimation(anim)
        sign_up.clearAnimation()
        sign_up.elevation=0f
        sign_up.background=resources.getDrawable(R.drawable.half_round_faded)

        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.f_anim,0)
                .replace(R.id.frameLayout,SignIn()).commit()
    }
}
