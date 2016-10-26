package com.hoomanlogic.meals;

import android.support.v4.util.ArrayMap;

import java.util.Map;

/**
 * Created by owner on 10/25/16.
 */

public class OperatorResponse {
    public String Action;
    public String ContextId;
    public Map<String, String> Parameters = new ArrayMap<>();
    public Map<String, String> Prompts = new ArrayMap<>();
}
