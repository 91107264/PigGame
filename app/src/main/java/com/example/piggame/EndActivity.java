package com.example.piggame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_end);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.end), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button backBtn = findViewById(R.id.button3);
        backBtn.setOnClickListener(view -> {

            Toast.makeText(this, "You clicked the confirm button!",
                    Toast.LENGTH_SHORT).show();

            Log.i("Learn", "Back Button Clicked!");
            startActivity(new Intent(EndActivity.this, PlayerSelect.class));
        });
    }
}