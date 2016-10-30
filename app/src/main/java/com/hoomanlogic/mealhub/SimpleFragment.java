package com.hoomanlogic.mealhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SimpleFragment extends Fragment {
    private int mNum;

    static SimpleFragment newInstance(int num) {
        SimpleFragment fragment = new SimpleFragment();

        Bundle args = new Bundle();
        args.putInt("num", num);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_simple, container, false);
        TextView text = (TextView)view.findViewById(R.id.textView);
        Button btn = (Button)view.findViewById(R.id.sendRequest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditGrocery.class);
                intent.putExtra("id", "bananas");
                startActivity(intent);
            }
        });
        text.setText(String.valueOf(mNum));
        return view;
    }

}
