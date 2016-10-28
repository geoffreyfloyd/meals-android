package com.hoomanlogic.ai;

import com.hoomanlogic.meals.OperatorResponse;

public abstract class BaseOperator {
    public abstract void interpret(String request);
    public abstract void onOperatorResponse (OperatorResponse response);
}