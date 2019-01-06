package com.example.shivam97.eventos;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.example.shivam97.eventos.Eventos.queue;

public class MyNetworkRequest {

    public interface Callback{
        void onSuccessResponse(String response) throws  JSONException;
        void onFailed(String errorResponse) throws JSONException;

    }
    public interface Callback2{
        void onSuccessResponse(String response) ;
        void onFailed(VolleyError error);
    }


    public void makeRequest(int method, String url, final Map<String,String> params, final MyNetworkRequest.Callback callback){

        StringRequest stringRequest= new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    callback.onSuccessResponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String finalResponse="";
                NetworkResponse mResponse=error.networkResponse;
                if(mResponse!=null && mResponse.data!=null){
                    if(mResponse.statusCode ==400){
                        finalResponse= new String(mResponse.data);
                    }
                }
                try {
                    callback.onFailed(finalResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        queue.add(stringRequest);
    }
    public void makeRequestAndVolleyError(int method, String url, final Map<String,String> params, final MyNetworkRequest.Callback2 callback){

        StringRequest stringRequest= new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccessResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        queue.add(stringRequest);
    }
    public void makeRequestWithHeaders(int method, String url, final Map<String,String> headers, final MyNetworkRequest.Callback callback){

        StringRequest stringRequest= new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    callback.onSuccessResponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    callback.onFailed(error.getMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };

        queue.add(stringRequest);
    }

    public void makeRequest(int method, String url, final String body, final MyNetworkRequest.Callback callback){

        StringRequest stringRequest= new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    callback.onSuccessResponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    callback.onFailed(error.getMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return body == null ? null : body.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", body, "utf-8");
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
                                                        
        };

        queue.add(stringRequest);
    }
}
