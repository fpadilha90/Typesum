package com.fpadilha.typesum.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.fpadilha.typesum.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    @ViewById EditText text;
    @ViewById Button textClear;
    @ViewById TextView result;
    @ViewById Button generateButton;

    //    @ViewsById({R.id.checkUppercase, R.id.checkLowercase, R.id.checkInteger, R.id.checkEspecial})
//    List<CheckBox> checkBoxes;
    @ViewById CheckBox checkUppercase;
    @ViewById CheckBox checkLowercase;
    @ViewById CheckBox checkInteger;
    @ViewById CheckBox checkEspecial;
    @ViewById CheckBox checkBlank;

    @ViewById EditText length;

    @ViewById TextView generateResult;

    @TextChange void text() {
        int count = text.length();
        result.setText(String.valueOf(count));

        if (count == 0) {
            textClear.setVisibility(View.GONE);
        } else {
            textClear.setVisibility(View.VISIBLE);
        }

    }

    @Click void textClear() {
        text.setText("");

    }

    @Click void generateButton() {
        if (!"".equals(length.getText().toString())) {
//            List<CheckBox> checkedList = new ArrayList<>();
//            for (CheckBox check : checkBoxes) {
//                checkedList.add(check);
//            }

            int maxLength = Integer.valueOf(length.getText().toString());

//            int size = maxLength / checkedList.size();

            String alphabet = "";

            if (checkUppercase.isChecked()) alphabet += "A";
            if (checkLowercase.isChecked()) alphabet += "a";
            if (checkInteger.isChecked()) alphabet += "0";
            if (checkEspecial.isChecked()) alphabet += "ä";
            if (checkBlank.isChecked()) alphabet += " ";

            int N = alphabet.length();
            Random r = new Random();

            String textGenerated = "";
            if (N > 0)
                for (int i = 0; i < maxLength; i++) {
                    textGenerated = textGenerated.concat(String.valueOf(alphabet.charAt(r.nextInt(N))));
                }

            generateResult.setText(textGenerated);

            ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);

            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", textGenerated);
            clipboard.setPrimaryClip(clip);
        }
    }


}
