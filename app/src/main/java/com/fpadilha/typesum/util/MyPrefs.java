package com.fpadilha.typesum.util;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by fpadilha on 18/06/2014.
 */
@SharedPref(value= SharedPref.Scope.UNIQUE)
public interface MyPrefs {

    @DefaultString("")
    String preferenceuserid();

    @DefaultString("")
    String preferenceuserpassword();

    @DefaultString("")
    String preferenceuregid();

}