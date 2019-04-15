package com.marion.androidmysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import ir.alirezabdn.wp7progress.WP7ProgressBar;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.inputBorrower)
    EditText inputBorrower;

    @BindView(R.id.inputAmount)
    EditText inputamount;

    @BindView(R.id.wp7progressBar)
    WP7ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonSave)
    public void save()
    {
        //save
        String name = inputBorrower.getText().toString().trim();
        String amount = inputamount.getText().toString().trim();

        if (name.isEmpty() || amount.isEmpty()){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            return;
        }
        String url ="";
        RequestParams params=new RequestParams();
        params.put("name", name);
        params.put("amount", amount);

        AsyncHttpClient client =new AsyncHttpClient();

        progressBar.showProgressBar();

        client.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
             progressBar.hideProgressBar();
                Toast.makeText(MainActivity.this, "Failed To save. Try again", Toast.LENGTH_SHORT).show();
             }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                progressBar.hideProgressBar();
                Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();

            }
        });



    }

    @OnClick(R.id.buttonView)
    public void fetch(){
        //navigate to next page
    }
}
