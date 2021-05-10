package com.example.frontendlab1;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ArticlesListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_list);
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getLatestArticles() );

        listView.setAdapter(arrayAdapter);

    }

    private ArrayList<String> getLatestArticles() {
        URL url;
        HttpURLConnection urlConnection = null;
        String result;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            url = new URL("http://localhost:8000/articles/");

            urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.setConnectTimeout(8000);

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

        return getArticlesNames(result);
    }

    private ArrayList<String> getArticlesNames(String result) {
        ArrayList<String> res = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(result);
            JSONObject articles = (JSONObject) jsonObj.get("latest_articles_list");
            Iterator<String> it = articles.keys();
            while (it.hasNext()) {
                JSONObject articleObj = (JSONObject) articles.get(it.next());
                res.add((String) articleObj.get("title"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

}
