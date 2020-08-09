/*Written by: Pride Moyo St (09/08/2020)*/
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

public class SecondFragment extends Fragment {
    //callback interface to send data to MainActivity
    public interface OnSecondFragTextSentListener {
        void onSecondFragTextSent(String text);
    }
    private TextView textView;
    //This listener is our MainActivity
    private OnSecondFragTextSentListener onSecondFragTextSentListener;
    private EditText editText;
    private Button send;
    private View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.second_fragment_layout,container,false);
        textView=v.findViewById(R.id.secondFragMessageTextView);
        editText=v.findViewById(R.id.editText);
        send=v.findViewById(R.id.second_frag_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSecondFragTextSentListener.onSecondFragTextSent(editText.getText().toString());
            }
        });
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //retrieving data set in MainActivity as part of the arguments bundle for this Fragment
        Bundle args=getArguments();
        if(args!=null) {
            textView.setText(args.getString("RECEIVED"));
        }
        ((MainActivity)getActivity()).setActionBarTitle("FRAG 2");
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onSecondFragTextSentListener=(OnSecondFragTextSentListener)context;
    }
    //clearing our reference to MainActivity context.
    @Override
    public void onDettach() {
        super.onDettach();
         onSecondFragTextSentListener=null;
    }
        
}

