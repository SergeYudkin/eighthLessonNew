package com.example.eighthlessonnew.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.eighthlessonnew.MainActivity;
import com.example.eighthlessonnew.R;


public class DialogBuilderFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.dialog_custom,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.dialog_card)
                .setView(view)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView textView = view.findViewById(R.id.textDialog);
                       // String answer = textView.getText().toString();
                        dismiss();

                        ((MainActivity) requireActivity()).onResultDialogFragment(textView.toString());
                    }
                });


        return super.onCreateDialog(savedInstanceState);
    }
}
