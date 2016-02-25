package com.fpadilha.typesum.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.fpadilha.myapplication.backend.registration.Registration;
import com.fpadilha.typesum.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AppCompatActivity {

    @ViewById Toolbar toolbar;
    @ViewById FloatingActionButton fab;

    @AfterViews void afterViews(){
        setSupportActionBar(toolbar);
        new GcmRegistrationAsyncTask(this).execute();
    }

//    @Click void fab(){
//        Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.luxfacta.actionlog");
//        if (launchIntent != null) {
//            startActivity(launchIntent);
//        }else{
//            try {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.luxfacta.actionlog")));
//            } catch (android.content.ActivityNotFoundException anfe) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.luxfacta.actionlog")));
//            }
//        }
//    }

    @OptionsItem(R.id.action_settings)
    void settings() {
//        Snackbar.make(fab, "Settings", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();

//        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.luxfacta.simplificaai");
//        startActivity(launchIntent);
    }

    class GcmRegistrationAsyncTask extends AsyncTask<Void, Void, String> {
        private Registration regService = null;
        private GoogleCloudMessaging gcm;
        private Context context;

        private static final String SENDER_ID = "110855666843";

        public GcmRegistrationAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... params) {
            if (regService == null) {
//                Registration.Builder builder = new Registration.Builder(AndroidHttp.newCompatibleTransport(),
//                        new AndroidJsonFactory(), null)
//                        // Need setRootUrl and setGoogleClientRequestInitializer only for local testing,
//                        // otherwise they can be skipped
//                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                            @Override
//                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
//                                    throws IOException {
//                                abstractGoogleClientRequest.setDisableGZipContent(true);
//                            }
//                        });
                // end of optional local run code
                Registration.Builder builder = new Registration.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://heroic-icon-122511.appspot.com/_ah/api/");

                regService = builder.build();
            }

            String msg = "";
            try {

                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                String regId = gcm.register(SENDER_ID);
                msg = "Device registered, registration ID=" + regId;

                // You should send the registration ID to your server over HTTP,
                // so it can use GCM/HTTP or CCS to send messages to your app.
                // The request to your server should be authenticated if your app
                // is using accounts.
                regService.register(regId).execute();

            } catch (IOException ex) {
                ex.printStackTrace();
                msg = "Error: " + ex.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            Logger.getLogger("REGISTRATION").log(Level.INFO, msg);
        }
    }
}
