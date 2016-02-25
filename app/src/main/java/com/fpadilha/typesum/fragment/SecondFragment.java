package com.fpadilha.typesum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.fpadilha.typesum.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by fpadilha on 22/10/2015.
 */
@EFragment(R.layout.fragment_second)
public class SecondFragment extends Fragment {
    @ViewById TextView text;

    @AfterViews void afterViews(){


    }
}
