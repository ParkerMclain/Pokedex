package com.example.zero.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Questionaire extends AppCompatActivity {
    public Button btn_submitInfo;
    public void submitInfo()
    {
        btn_submitInfo = (Button)findViewById(R.id.btnSubmitInfo   );
        btn_submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent password = new Intent(Questionaire.this, Memory.class);
                startActivity(password);

            }
        });}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);
        submitInfo();
    }
}
