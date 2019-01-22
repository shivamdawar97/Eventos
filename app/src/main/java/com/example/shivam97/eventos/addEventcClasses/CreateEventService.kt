package com.example.shivam97.eventos.addEventcClasses

import com.example.shivam97.eventos.Eventos.BASE_URL
import org.json.JSONArray
import org.json.JSONObject

class CreateEventService
{

  private  val CREATE_EVENT_API=BASE_URL+"/api/event/create"
   lateinit var name:String
   lateinit var fees:String
   lateinit var round_details:String
   lateinit var description:String
   var category_id:Int=0


    fun setRoundDetails(array: ArrayList<RoundLayout> )
    {
         val array=JSONArray()
        for ( i in 0..array.length() )
        {
            val obj=JSONObject()
            obj.put("name","roundName")

        }

    }
    fun uploadEventDetails(){


    }



}
