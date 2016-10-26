package com.hoomanlogic.meals;

public abstract class BaseOperator {
    public abstract void interpret(String request);
    public abstract void onOperatorResponse (OperatorResponse response);
}