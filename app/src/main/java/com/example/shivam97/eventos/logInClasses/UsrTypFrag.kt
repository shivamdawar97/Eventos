package com.example.shivam97.eventos.logInClasses

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.cardview.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Spinner
import com.example.shivam97.eventos.R
import kotlinx.android.synthetic.main.f_user_type.view.*
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.f_user_type.*


class UsrTypFrag: Fragment(){
   private var userType=0
   private lateinit var card1: CardView
   private lateinit var card2: CardView
   private lateinit var spinner:Spinner
    internal lateinit var view: View
    private lateinit var clickListener: View.OnClickListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view= inflater.inflate(R.layout.f_user_type, container, false)
        card1=view.findViewById(R.id.student_card)
        card2=view.findViewById(R.id.faculty_card)

        spinner=view.clg_spinner
        val anim= AnimationUtils.loadAnimation(context,R.anim.zoom_in)
        clickListener= View.OnClickListener {
            view ->
            when(view.id)
            {
                R.id.student_card->
                {
                    userType=1
                    card1.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.colorPrimaryDark))
                    card1.startAnimation(anim)
                    card2.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.opaque))
                    card2.clearAnimation()

                }
                R.id.faculty_card->
                {
                    userType=2
                    card1.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.opaque))
                    card1.clearAnimation()
                    card2.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.colorPrimaryDark))
                    card2.startAnimation(anim)
                }


            }
        }
        view.student_card.setOnClickListener { clickListener }
        view.faculty_card.setOnClickListener { clickListener }
        view.guest.setOnClickListener { clickListener }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        var clg:Int
        val array =ArrayList<String>()
        array.add("Inderprastha Engineering College")
        val spinnerArrayAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item,
                array) //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item)
        spinner.adapter = spinnerArrayAdapter

        view.btn_sub.setOnClickListener {
            val s= spinner.selectedItem
            clg=if (s!=null)array.indexOf(s)+1 else 0
            if(clg!=0 && userType!=0)(context as AccSetup).setUsrTypAndClg(userType,clg)
        }

        skip.setOnClickListener {
            activity?.finish()
            (context as AccSetup).setUsrTypAndClg(0,0)
        }


    }
}


