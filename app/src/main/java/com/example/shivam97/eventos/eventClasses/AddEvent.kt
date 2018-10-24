package com.example.shivam97.eventos.eventClasses
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.example.shivam97.eventos.Eventos.getCompressed
import com.example.shivam97.eventos.R
import kotlinx.android.synthetic.main.a_add_event.*
import java.io.IOException

class AddEvent : AppCompatActivity() {
    private val pICK = 234
    private lateinit var  bitmap:Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_add_event)

        add_event_image.setOnClickListener {
            val i = Intent()
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(i, "Select An Image"), pICK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pICK && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
           val filepath = data.data
            try {

                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
                bitmap = getCompressed(bitmap)

                add_event_image.setImageBitmap(bitmap)
            } catch (/*| URISyntaxException */e: IOException) {
                e.printStackTrace()
            }

        }

    }
    fun createEvent(view:View){
        if(checkIfEmpty()){}
    }

    private fun checkIfEmpty(): Boolean {
        val title=event_title.text.toString()
        val sdesc=short_desc.text.toString()
        val fee=RegistrationFee.text.toString()
        val date=dates.text.toString()
        val time=timings.text.toString()
        val venue=event_venue.text.toString()
        val prize=prize_view.text.toString()

        return true
    }
    fun finish(view: View){
        finish()
    }
}
