package com.google.ar.core.examples.java.cloudanchor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class InputDialog extends DialogFragment {
    public interface OkListener {
        void sendInputTwo(String input);
    }

    private final String TAG = "TestDialog";

    InputDialog.OkListener listener;
    EditText textInput;
    Context context;

    public InputDialog(Context context) {
        textInput = new EditText(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        textInput.setLayoutParams(lp);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(textInput);
        builder
                .setTitle("Enter Room")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDialog().dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String input = textInput.getText().toString();
                        listener.sendInputTwo(input);
                        getDialog().dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (OkListener) getActivity();
        } catch(ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}
