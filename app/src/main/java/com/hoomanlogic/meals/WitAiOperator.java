package com.hoomanlogic.meals;

import android.net.Uri;
import android.support.v4.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public abstract class WitAiOperator extends BaseOperator {
    final private String WIT_AI_KEY = "KLHUGVC6PAI45WRMIKP4HWK625GM322R"; //"6DQ2DC3A3DBNMM4AM4XPAKDBAUKA3TPU";
    final private String WIT_AI_URL = "https://api.wit.ai/converse?q=";
    final private Map<String, String> mHeaders = new ArrayMap<>();
    private RequestQueue mRequestQueue = null;

    public WitAiOperator(RequestQueue queue) {
        mRequestQueue = queue;
        mHeaders.put("Authorization", "Bearer " + WIT_AI_KEY);
    }

    @Override
    public void interpret(String request) {
        // Build api request url
        String url = WIT_AI_URL + Uri.encode(request);
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
                            String type = response.getString("type");
                            if (type == "action") {
                                opResponse.Action = response.getString("action");
                            }
                            else if (type == "")

                            JSONObject entities = response.getJSONObject("entities");
                            JSONArray parameterKeys = entities.names();
                            int keyLen = parameterKeys.length();
                            for (int i = 0; i < keyLen; i++) {
                                String key = parameterKeys.getString(i);
                                String value = entities.getJSONArray(parameterKeys.getString(i)).getJSONObject(0).getString("value");
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
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return mHeaders;
            }
        };
        mRequestQueue.add(httpRequest);
    }
}

