package com.example.shivam97.eventos.addeventsclasses

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.example.shivam97.eventos.Eventos.*
import com.example.shivam97.eventos.MyNetworkRequest
import org.json.JSONArray
import org.json.JSONObject

class CreateEventService
{

    companion object {
        private val CREATE_EVENT_API= "$BASE_URL/api/event/create"
    }

   lateinit var name:String
   lateinit var fees:String
   lateinit var roundDetails:String
   lateinit var description:String
   private var categoryId:Int=8

    fun setRoundDetails(roundsArray: ArrayList<RoundLayout> )

    {
        val array=JSONArray()
        val obj=JSONObject()
        array.put(obj)
        for ( i in 0 until roundsArray.size)
        {
            obj.put("round_name",roundsArray[i].name)
            obj.put("round_number",i+1)
            obj.put("round_date",roundsArray[i].date)
            obj.put("venue",roundsArray[i].venue)
            obj.put("time",roundsArray[i].time)
            obj.put("description",roundsArray[i].desc)

        }
        roundDetails=array.toString()

    }

    fun uploadEventDetails(ctx: Context){

        showProgressDialog(ctx)
        val body=HashMap<String,String>()
        body["name"] = name
        body["user_id"] = preferences.userID
        body["remember_token"] = preferences.token
        body["category_id"] = categoryId.toString()
        body["fees"] = fees
        body["round_details"] = roundDetails
        body["description"] = description

        request.makeRequest(Request.Method.POST,CREATE_EVENT_API,body,object :MyNetworkRequest.Callback{
            override fun onSuccessResponse(response: String?) {
                    val jObject=JSONObject(response)
                    if(jObject.getString("status")=="success")
                    {
                        //Event Created
                        hideProgressDialog()
                        Toast.makeText(ctx,"event created",Toast.LENGTH_SHORT).show()
                    }
            }
            override fun onFailed(errorResponse: String?) {
                hideProgressDialog()
                Toast.makeText(ctx, "event failed$errorResponse",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
