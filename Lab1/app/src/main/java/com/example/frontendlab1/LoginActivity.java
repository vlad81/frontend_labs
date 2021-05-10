package com.example.frontendlab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private EditText passwordText;
    private EditText loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        passwordText = findViewById(R.id.passwordText1);
        loginText = findViewById(R.id.loginText1);
    }

    public void onLogInClick(View view) {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        boolean isConnected = cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

        if (isConnected) {
            String userLogin = loginText.getText().toString();
            String userPassword = passwordText.getText().toString();

            if (validateUserInfo(userLogin, userPassword)) {
                Intent intent = new Intent(getApplicationContext(), ArticlesListActivity.class);
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateUserInfo(String userLogin, String userPassword) {
        URL url;
        HttpURLConnection urlConnection = null;
        String result;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            url = new URL("http://localhost:8000/account/login/?email=" + userLogin + "&password=" + userPassword);

            urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.setConnectTimeout(8000);
          //  urlConnection.setRequestProperty("User-Agent", "okhttp");

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isReader = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(isReader);
            StringBuffer sb = new StringBuffer();
            String str;
            while((str = reader.readLine())!= null){
                sb.append(str);
            }

            result = sb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return successfulLogin(result);
    }

    private boolean successfulLogin(String responseResult) {
        boolean res = false;
        try {
            JSONObject jsonObj = new JSONObject(responseResult);
            res = (boolean) jsonObj.get("successful_login");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public void onCreateAccountClick(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
}
