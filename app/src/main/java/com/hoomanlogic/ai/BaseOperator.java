package com.hoomanlogic.ai;

import com.hoomanlogic.mealhub.OperatorResponse;

public abstract class BaseOperator {
    public abstract void interpret(String request);
    public abstract void onOperatorResponse (OperatorResponse response);
}