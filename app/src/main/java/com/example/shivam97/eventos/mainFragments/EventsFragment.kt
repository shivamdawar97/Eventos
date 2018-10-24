package com.example.shivam97.eventos.mainFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils

import com.example.shivam97.eventos.R
import kotlinx.android.synthetic.main.f_events.view.*


/**
 * A simple [Fragment] subclass.
 */
class EventsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.f_events, container, false)
         view.editText2.startAnimation(AnimationUtils.loadAnimation(context,
                 R.anim.slide_down))
        return view
    }

}// Required empty public constructor
