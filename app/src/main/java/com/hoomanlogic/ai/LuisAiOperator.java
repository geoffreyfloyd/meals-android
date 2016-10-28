package com.hoomanlogic.ai;

import android.net.Uri;
import android.support.v4.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hoomanlogic.meals.OperatorResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public abstract class LuisAiOperator extends BaseOperator {
    final private String LUIS_AI_URL = "https://api.projectoxford.ai/luis/v1/application?id=49a25a7d-dc2a-444b-b791-309e19798088&subscription-key=4fa466a853cf4c6c8a25cb521b110d48&q=";
    private RequestQueue mRequestQueue = null;

    public LuisAiOperator(RequestQueue queue) {
        mRequestQueue = queue;
    }

    @Override
    public void interpret(String request) {
        // Build api request url
        String url = LUIS_AI_URL + Uri.encode(request);
        // Create http request and add to request queue
        JsonObjectRequest httpRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // FOO
                        OperatorResponse opResponse = new OperatorResponse();
                        try {
                            // First (highest-scoring) intent is our action
                            JSONObject bestMatchingIntent = response.getJSONArray("intents").getJSONObject(0);
                            opResponse.Intent = bestMatchingIntent.getString("intent");

                            // Get parameters
                            JSONArray entities = response.getJSONArray("entities");
                            for (int i = 0; i < entities.length(); i++) {
                                String key = entities.getJSONObject(i).getString("type");
                                String value = entities.getJSONObject(i).getString("entity");
                                opResponse.Parameters.put(key, value);
                            }
                            onOperatorResponse(opResponse);
                        }
                        catch (JSONException e) {
                            System.out.println(e.toString());
                        }
                        catch (Exception e) {
                            System.out.println(e.toString());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // BAR
                    }
                }
        );
        mRequestQueue.add(httpRequest);
    }
}

