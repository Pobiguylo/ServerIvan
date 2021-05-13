package com.example.serverivan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.net.wifi.WifiConfiguration.Status.strings;

public class MainActivity extends AppCompatActivity {
    EditText mail;
    EditText pass;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mail = findViewById(R.id.mail);
        this.pass = findViewById(R.id.pass);
        this.save = findViewById(R.id.button);

        class MyTask extends AsyncTask<String,Void,String>{
            @Override
            protected String doInBackground(String... strings) {
                String email = strings[0];
                String pwd = strings[1];
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://10.0.2.2:8003");
                    HttpParams params = new BasicHttpParams();
                    List<NameValuePair> nameValuePairs = new ArrayList<>(2);
                    nameValuePairs.add(new BasicNameValuePair("email", email));
                    nameValuePairs.add(new BasicNameValuePair("pwd",pwd));
                    httpPost.setParams((HttpParams) new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
                    HttpResponse response =client.execute(httpPost);
                    HttpEntity entity =  response.getEntity();
                    String s = EntityUtils.toString(entity);
                    Intent intent =new Intent(MainActivity.this,SuperActivity.class);
                    intent.putExtra("1",s);
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return  null;
            }
            @Override
            protected void onPostExecute(String aVoid) {
               super.onPostExecute(aVoid);
            }
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail.getText().toString().isEmpty()||pass.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();

                }
                MyTask task = new MyTask();
                task.execute(mail.getText().toString(),pass.getText().toString());

            }
        });


    }
}
