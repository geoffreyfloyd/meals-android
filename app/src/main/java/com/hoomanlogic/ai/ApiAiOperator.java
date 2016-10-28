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

import static java.util.UUID.randomUUID;

public abstract class ApiAiOperator extends BaseOperator {
    final private String API_AI_KEY = "aa7097029025417ba0bfd38b00757b27";
    final private String API_AI_URL = "https://api.api.ai/api/query?lang=en&query=";
    final private Map<String, String> mHeaders = new ArrayMap<>();
    final private String mSessionId = randomUUID().toString();
    private RequestQueue mRequestQueue = null;

    public ApiAiOperator(RequestQueue queue) {
        mRequestQueue = queue;
        mHeaders.put("Authorization", "Bearer " + API_AI_KEY);
    }

    @Override
    public void interpret(String request) {
        // Build api request url
        String url = API_AI_URL + Uri.encode(request) + "&sessionId=" + mSessionId;
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
                            // Check status returned in response
                            Boolean success = response.getJSONObject("status").getInt("code") == 200;
                            if (!success) {
                                throw new JSONException("API Request was not successful");
                            }
                            // Get context id and suggested action
                            opResponse.ContextId = response.getString("sessionId");
                            opResponse.Intent = response.getJSONObject("result").getString("action");
                            JSONObject entities = response.getJSONObject("result").getJSONObject("parameters");
                            JSONArray parameterKeys = entities.names();
                            int keyLen = parameterKeys.length();
                            for (int i = 0; i < keyLen; i++) {
                                String key = parameterKeys.getString(i);
                                String value = entities.getString(parameterKeys.getString(i));
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