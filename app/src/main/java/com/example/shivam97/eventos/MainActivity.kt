package com.example.shivam97.eventos

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.example.shivam97.eventos.eventClasses.AddEvent
import com.example.shivam97.eventos.mainFragments.EventsFragment
import com.example.shivam97.eventos.mainFragments.MeFragment
import com.example.shivam97.eventos.mainFragments.NotifyFragment
import com.example.shivam97.eventos.mainFragments.SettingsFragment
import kotlinx.android.synthetic.main.a_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var checkBoxes = intArrayOf( R.id.nav_me,R.id.nav_event, R.id.nav_notify, R.id.nav_sett)
    private lateinit var title:TextView
    private lateinit var fragments:ArrayList<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_main)
        title=findViewById(R.id.nav_title)
        fragments=ArrayList()
        fragments.add(MeFragment())
        fragments.add(EventsFragment())
        fragments.add(NotifyFragment())
        fragments.add(SettingsFragment())
        uncheckOthers(R.id.nav_event)
        setFragment(1)
        title.text="Events"
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddEvent::class.java))

        }

    }


    fun navClicked(view: View) {
        val id = view.id
        when (id) {
            R.id.c_me ,checkBoxes[0]-> {uncheckOthers(checkBoxes[0])
                title.text="Me"
                setFragment(0)}
            R.id.c_event,checkBoxes[1] -> {uncheckOthers(checkBoxes[1])
                                title.text="Events"
                setFragment(1)}

            R.id.c_notify,checkBoxes[2] ->{ uncheckOthers(checkBoxes[2])
                                title.text="Notifications"
                setFragment(2)}
            R.id.c_sett,checkBoxes[3] -> {uncheckOthers(checkBoxes[3])
                                title.text="Settings"
                setFragment(3)}
        }


    }

    private fun setFragment(i: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.container_frame, fragments[i]).commit()
    }

    private fun uncheckOthers(id: Int) {
        for (i in checkBoxes) {
            (findViewById<View>(i) as CheckBox).isChecked = i == id
        }
    }
}
