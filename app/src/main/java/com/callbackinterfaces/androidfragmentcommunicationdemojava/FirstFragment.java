package com.callbackinterfaces.androidfragmentcommunicationdemojava;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {
    public interface OnFirstFragTextSentListener {
        void onFirstFragTextSent(String text);
    }
    private TextView textView;
    private OnFirstFragTextSentListener onFirstFragTextSentListener;
    private EditText editText;
    private Button send;
    private View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.first_fragment_layout,container,false);
        textView=v.findViewById(R.id.firstFragMessageTextView);
        editText=v.findViewById(R.id.editText);
        send=v.findViewById(R.id.first_frag_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFirstFragTextSentListener.onFirstFragTextSent(editText.getText().toString());
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args=getArguments();
        if(args!=null) {
            textView.setText(args.getString("RECEIVED"));
        }
        ((MainActivity)getActivity()).setActionBarTitle("FRAG 1");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onFirstFragTextSentListener=(OnFirstFragTextSentListener)context;
    }

}
