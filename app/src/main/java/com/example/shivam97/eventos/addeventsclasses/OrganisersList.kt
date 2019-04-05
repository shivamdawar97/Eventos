package com.example.shivam97.eventos.addeventsclasses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.shivam97.eventos.R

class OrganisersList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organisers_list)


    }

    inner class ListAdapter : BaseAdapter() {

        val organiserModels=ArrayList<OrganiserModel>()

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            return layoutInflater.inflate(R.layout.user_card,p2,false)
        }

        override fun getItem(p0: Int): Any {
           return organiserModels[p0]
        }

        override fun getItemId(p0: Int): Long {
            return organiserModels[p0].organiseId.toLong()
        }

        override fun getCount(): Int {
            return organiserModels.size
        }

    }
}
