package com.example.inheritance;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.Date;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

public class AddPost extends AppCompatActivity {
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    EditText inputDate;
    EditText inputTitle;
    EditText inputDescription;

    private static String url_create_post "https://skgrocks.000webhostapp.com/connect/create_product.php";

    //JSON node names
    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //EditText
        inputDate = findViewById(R.id.inputDate);
        inputTitle = findViewById(R.id.inputTitle);
        inputDescription = findViewById(R.id.inputDesc);

        Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateNewProduct().execute();
            }
        });
    }

    class CreateNewProduct extends AsyncTask<String,String,String>{
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(AddPost.this);
            pDialog.setMessage("Creating product...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            String date = inputDate.getText().toString();
            String title = inputTitle.getText().toString();
            String description = inputDescription.getText().toString();

            //Building parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Date", date));
            params.add(new BasicNameValuePair("Title", title));
            params.add(new BasicNameValuePair("Description" , description));



        }

    }

}
