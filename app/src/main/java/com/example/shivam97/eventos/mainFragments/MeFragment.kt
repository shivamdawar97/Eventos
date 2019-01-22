package com.example.shivam97.eventos.mainFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils

import com.example.shivam97.eventos.R
import kotlinx.android.synthetic.main.f_me.view.*


/**
 * A simple [Fragment] subclass.
 */
class MeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.f_me, container, false)
        view.image_back.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.flash))
        return view
    }

}// Required empty public constructor
