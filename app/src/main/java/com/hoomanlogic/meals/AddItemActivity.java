package com.hoomanlogic.meals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hoomanlogic.ai.BaseOperator;
import com.hoomanlogic.ai.LuisAiOperator;

import java.util.Map;

public class AddItemActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue = null;
    private BaseOperator mOperator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // Get references to view parts
        final TextView responseText = (TextView)findViewById(R.id.vwResponse);
        final Button btn = (Button)findViewById(R.id.btnSendRequest);

        // Set up network request queue
        mRequestQueue = Volley.newRequestQueue(this);

        // Set up operator
        mOperator = new LuisAiOperator(mRequestQueue) {
            @Override
            public void onOperatorResponse(OperatorResponse response) {
                System.out.println(response.Intent);
                for (Map.Entry<String, String> entry : response.Parameters.entrySet()) {
                    String pair = entry.getKey() + ":" + entry.getValue();
                    System.out.println(pair);
                    responseText.setText(responseText.getText().toString() + ";" + pair);
                }
            }
        };

        // Hook button click to operator.interpret
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = (EditText)findViewById(R.id.editText);
                mOperator.interpret(editText.getText().toString());
            }
        });
    }
}
