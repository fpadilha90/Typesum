/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fpadilha.typesum.helper;

import android.content.Context;

import com.fpadilha.typesum.model.User;
import com.fpadilha.typesum.model.User_;
import com.fpadilha.typesum.util.MyPrefs_;

import java.util.Calendar;

/**
 * Easy storage and retrieval of preferences.
 */
public class PreferencesHelper {

    private PreferencesHelper() {
        //no instance
    }

    /**
     * Writes a {@link User} to preferences.
     *
     * @param user    The {@link User} to write.
     * @param myPrefs The Context which to obtain the SharedPreferences from.
     */
    public static void saveUser(User user, MyPrefs_ myPrefs) {
        myPrefs.edit()
                .preferenceuserid().put(user.getId())
                .preferenceuserpassword().put(user.getPassword())
                .preferenceuregid().put(user.getRegId()).apply();

    }

    /**
     * Retrieves a {@link User} from preferences.
     *
     * @param myPrefs The Context which to obtain the SharedPreferences from.
     * @return A previously saved user or <code>new @link User</code> if none was saved previously.
     */
    public static User getUser(Context context, MyPrefs_ myPrefs) {
        User user = User_.getInstance_(context);

        user.setId(myPrefs.preferenceuserid().get());
        user.setPassword(myPrefs.preferenceuserpassword().get());
        user.setRegId(myPrefs.preferenceuregid().get());

        return user;
    }

    /**
     * Remove the current {@link User} data from preferences.
     *
     * @param myPrefs The Context which to obtain the SharedPreferences from.
     */
    public static void disallow(Context context, MyPrefs_ myPrefs) {
        User user = User_.getInstance_(context);

        user.setId("");
        user.setPassword("");
        user.setRegId("");

        saveUser(user, myPrefs);
    }

    /**
     * Check whether a user is currently signed in.
     *
     * @param myPrefs The context to check this in.
     * @return <code>true</code> if login data exists, else <code>false</code>.
     */
    public static boolean isRegIn(MyPrefs_ myPrefs) {
        return !"".equals(myPrefs.preferenceuregid().get());
    }

}
