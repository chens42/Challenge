package com.shaojin.restaurant;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shaojin.restaurant.fragment.LoadingFragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_holder);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        FragmentTransaction replace = fragmentTransaction.replace(R.id.fragment_holder, new LoadingFragment());
        replace.commit();
        String string= "{" +
                "  \"CommandType\": \"GetAllNearestMerchant\"," +
                "  \"CuisineId\": \"\"," +
                "  \"CurrentDateTime\": \"29/09/2015 16:14:32\"," +
                "  \"ZipCode\": \"\"," +
                "  \"Radius\": \"15000\"," +
                "  \"Latitude\": \"43.643818\"," +
                "  \"Longitude\": \"-79.403018\"," +
                "  \"SearchText\": \"\"," +
                "  \"WaitingTimeInMinutes\": \"30\"" +
                "}";

        String url = "http://dev.grabb.ca/grabbapi";

        new LoadingData().execute(string,url);

    }
    private class LoadingData extends AsyncTask<String,Integer, Long>{

        @Override
        protected Long doInBackground(String... params) {



            HttpClient httpClient = new DefaultHttpClient(); //Deprecated
            try {
                HttpPost request = new HttpPost(params[1]);
                StringEntity stringEntity =new StringEntity(params[0].toString());
                request.addHeader("content-type", "application/x-www-form-urlencoded");
                request.setEntity(stringEntity);
                HttpResponse response = httpClient.execute(request);
                String json = EntityUtils.toString(response.getEntity());

                Log.e("","");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
