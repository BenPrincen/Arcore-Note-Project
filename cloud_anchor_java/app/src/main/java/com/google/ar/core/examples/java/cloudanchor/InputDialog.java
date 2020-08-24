package com.google.ar.core.examples.java.cloudanchor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class InputDialog extends DialogFragment {
    private String TAG = "TestFragment";

    public interface OnInputListener {
        void sendInput(String input);
    }

    OnInputListener inputListener;

    private EditText textInput;
    private TextView actionOk, actionCancel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_dialog, container, false);
        textInput = view.findViewById(R.id.input);

        actionOk = view.findViewById(R.id.action_ok);
        actionCancel = view.findViewById(R.id.action_cancel);
        actionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = textInput.getText().toString();
                Log.d(TAG, "onClick: sending input");
                inputListener.sendInput(input);
                getDialog().dismiss();
            }
        });

        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            inputListener = (OnInputListener) getActivity();
        } catch(ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}
