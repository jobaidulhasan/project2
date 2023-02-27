package com.example.api;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {



    private List<DATA> dataList;
    String product_name[];
    String product_details[];
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView=findViewById(R.id.recyclerView);
        //////////////set Adapter--------------------->

        Adapter adapter=new Adapter(this,product_name,product_details);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.OnitemClickListener(new Adapter.OntiemClick() {
            @Override
            public void onclick(View view, int position) {

                Toast.makeText(Home.this, "Click"+position, Toast.LENGTH_SHORT).show();
            }
        });
        getDataOnServer();
    }


    /////////////////////Get data On server ////////////////////////////////////
    private void getDataOnServer(){
        RequestQueue queue= Volley.newRequestQueue(this);
        String url="https://pqstec.com/invoiceapps/Values/GetProductList";
        dataList=new ArrayList<>();
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null)
                {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            dataList.add(new DATA(jsonObject.getString("Name"),
                                    jsonObject.getString("Type"),
                                    jsonObject.getString("Price"),
                                    jsonObject.getString("ColorName"),
                                    jsonObject.getString("Id"),
                                    jsonObject.getString("Code")
                                    )
                            );
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Check your internet Connection ", Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(stringRequest);

    }



}