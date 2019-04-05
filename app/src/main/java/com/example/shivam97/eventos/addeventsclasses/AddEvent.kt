package com.example.shivam97.eventos.addeventsclasses

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.example.shivam97.eventos.Eventos.getCompressed
import com.example.shivam97.eventos.R
import kotlinx.android.synthetic.main.a_add_event.*
import java.io.IOException




class AddEvent : AppCompatActivity() {

    private val pICK = 2
    private val PICK_CROP = 1
    private lateinit var  bitmap:Bitmap
    private var rounds: ArrayList<RoundLayout>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_add_event)
        rounds= ArrayList()

        add_event_image.setOnClickListener {
            val i = Intent("com.android.camera.action.CROP")
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(i, "Select An Image"), pICK)

        }
            add_rounds.setOnClickListener {

                rounds!!.add(RoundLayout(this@AddEvent, (rounds!!.size + 1).toString()))
                no_of_rounds.text=(rounds!!.size).toString()
                rounds_container.addView(rounds!![rounds!!.size-1])

            }

            remove_rounds.setOnClickListener {

                if(rounds!!.size==0)
                    return@setOnClickListener
                rounds!!.removeAt(rounds!!.size-1)
                no_of_rounds.text= rounds!!.size.toString()
                rounds_container.removeViewAt(rounds!!.size)

            }


        add_event_organisers.setOnClickListener {
            
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pICK && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
           val filepath = data.data
            try {
                bitmap=MediaStore.Images.Media.getBitmap(contentResolver,filepath)
                bitmap = getCompressed(bitmap)
                add_event_image.setImageBitmap(bitmap)

            } catch (/*| URISyntaxException */e: IOException) {
                e.printStackTrace()
            }

        }

    }

    //when button clicked
    fun createEvent(view:View){

        val title=event_title.text.toString()
        val sdesc=short_desc.text.toString()
        val fee=RegistrationFee.text.toString()
        val prize=prize_view.text.toString()

        if(title.isEmpty() || sdesc.isEmpty()||fee.isEmpty()||prize.isEmpty() || rounds?.size==0)
            return

        val eventService=CreateEventService()
        eventService.name=title
        eventService.description=sdesc
        eventService.fees=fee
        eventService.setRoundDetails(rounds!!)
        eventService.uploadEventDetails(view.context)

    }

    fun finish(view: View){
        finish()
    }
}
