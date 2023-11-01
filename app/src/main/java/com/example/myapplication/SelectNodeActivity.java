package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Semaphore;

public class SelectNodeActivity extends AppCompatActivity {

    ImageButton imageButton1, imageButton2; // 버튼 두개 생성
    TextView textView1, textView2;

    Bitmap image1, image2;
    TreeNode[] nodes; // 여기에 추가
    String tableName;

    DataHandler dataHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Log.d("MP_proj", "SelectNodeActivity xml Start!");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_select_node);


            Log.d("MP_proj", "SelectNodeActivity onCreate started.");

            imageButton1 = findViewById(R.id.ImageButton1);
            imageButton2 = findViewById(R.id.ImageButton2);

            textView1 = findViewById(R.id.TextView1);
            textView2 = findViewById(R.id.TextView2);


            Log.d("MP_proj", "Trying to get parcelableExtra from Intent");
            Parcelable[] parcelables = getIntent().getParcelableArrayExtra("pairNode");
            tableName = getIntent().getStringExtra("tableName");

            dataHandler = new DataHandler(this, tableName);
            if (parcelables != null) {
                nodes = new TreeNode[parcelables.length];
                for (int i = 0; i < parcelables.length; i++) {
                    nodes[i] = (TreeNode) parcelables[i];
                }

                if(nodes != null && nodes.length > 0) {
                    Log.d("MP_proj", "TreeNode array received successfully");
                    Log.d("MP_proj", "Received Pair node " + nodes[0].playerName + nodes[1].playerName);


                    image1 = dataHandler.getImageByText(nodes[0].playerName);
                    image2 = dataHandler.getImageByText(nodes[1].playerName);

                    imageButton1.setImageBitmap(image1);
                    imageButton2.setImageBitmap(image2);

                    textView1.setText(nodes[0].playerName);
                    textView2.setText(nodes[1].playerName);
                } else {
                    Log.e("MP_proj", "Failed to receive TreeNode array");
                }
            } else {
                Log.e("MP_proj", "Parcelable array is null");
            }

            imageButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selectedNode", nodes[0]);
                    Log.d("MP_proj", "nodes " + nodes[0].playerName);
                    Log.d("MP_proj", "Returning result with selectedNode 0.");
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            });

            imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selectedNode", nodes[1]);
                    Log.d("MP_proj", "nodes " + nodes[1].playerName);
                    Log.d("MP_proj", "Returning result with selectedNode 1.");
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            });
        } catch (Exception e) {
            Log.e("MP_proj", "Error in SelectNodeActivity onCreate: ", e);
        }
    }



}