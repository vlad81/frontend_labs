package com.example.frontendlab1;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText passwordText;
    private EditText passwordText2;
    private EditText loginText;
    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        passwordText = findViewById(R.id.passwordText);
        passwordText2 = findViewById(R.id.passwordText2);
        loginText = findViewById(R.id.loginText);
        emailText = findViewById(R.id.emailText);
    }

    public void onCreateNewAccountClick(View view) {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        boolean isConnected = cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

        if (isConnected) {
            String userLogin = loginText.getText().toString();
            String userPassword = passwordText.getText().toString();
            String userPassword2 = passwordText2.getText().toString();
            String userMail = emailText.getText().toString();

            if (createNewAccount(userLogin, userPassword, userPassword2, userMail)) {
                Intent intent = new Intent(getApplicationContext(), ArticlesListActivity.class);
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean createNewAccount(String userLogin, String userPassword, String userPassword2, String userEmail) {
        if (userPassword.equals(userPassword2)) {
            URL url;
            HttpURLConnection urlConnection = null;
            String result;
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try {
                url = new URL("http://localhost:8000/account/register/?email=" + userLogin + "&password=" + userPassword + "&username=" + userLogin);

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

            return successfulRegister(result);
        } else {
            Toast.makeText(getApplicationContext(), "Passwords doesn't matches!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean successfulRegister(String result) {
        boolean res = false;
        try {
            JSONObject jsonObj = new JSONObject(result);
            res = (boolean) jsonObj.get("successful_register");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
