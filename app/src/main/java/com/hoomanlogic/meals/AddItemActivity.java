package com.hoomanlogic.meals;

import android.net.Uri;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AddItemActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue = null;
    final private Map<String, String> mHeaders = new ArrayMap<>();
    final private String URL = "https://api.wit.ai/message?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_add_item);
        mHeaders.put("Authorization", "Bearer 6DQ2DC3A3DBNMM4AM4XPAKDBAUKA3TPU");
        Button btn = (Button)findViewById(R.id.btnSendRequest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView vwResponse = (TextView)findViewById(R.id.vwResponse);
                final String url = URL + Uri.encode("add lemon to grocery list");
                JsonObjectRequest request = new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                vwResponse.setText(url + ":" + response.toString());
                                
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // BLAH
                            }
                        }
                ) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        Map<String, String> headers = new HashMap<>();
//                        headers.put()
                        return mHeaders;
                    }
                };
                mRequestQueue.add(request);
            }
        });
    }

//    public void onSendRequestClick (View view) {
//        final TextView vwResponse = (TextView)view.findViewById(R.id.vwResponse);
//        JSONObject body = new JSONObject();
//
//        JsonObjectRequest request = new JsonObjectRequest(
//            URL,
//            body,
//            new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    vwResponse.setText(response.toString());
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // BLAH
//                }
//            }
//        ) {
//            @Override
//            public Map<String, String> getHeaders() {
//                return mHeaders;
//            }
//        };
//        mRequestQueue.add(request);
//    }
}
