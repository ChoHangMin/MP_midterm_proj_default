package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class ShowWinnerActivity extends AppCompatActivity {

    TextView textView3;
    ImageView imageView3;
    Button menuButton;

    Bitmap image3;

    String tableName;
    DataHandler dataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_winner);


        Log.d("MP_proj", "Open Show Winner Activity");

        textView3 = findViewById(R.id.TextView3);
        imageView3 = findViewById(R.id.ImageButton3);
        menuButton = findViewById(R.id.MenuButton);

        menuButton.setText("메인 메뉴로 이동");


        TreeNode receivedNode = getIntent().getParcelableExtra("TreeNode");
        tableName = getIntent().getStringExtra("tableName");

        dataHandler = new DataHandler(this, tableName);
        Log.d("MP_proj", "Success Intent Open");

        if (receivedNode != null) {
            image3 = dataHandler.getImageByText(receivedNode.playerName);
            textView3.setText(receivedNode.playerName);
            imageView3.setImageBitmap(image3);
        } else {
            textView3.setText("결승 불러오기 실패");
        }

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowWinnerActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }

}
