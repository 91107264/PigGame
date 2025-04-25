package com.example.piggame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayerSelect extends AppCompatActivity {

    TextView textView;
    NumberPicker numberPicker;
    private static int playerCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player_select);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMinValue(2);
        numberPicker.setMaxValue(5);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textView.setText(String.format("Number Of Players: %s", i1));
                playerCount = i1;

            }
        });
        Button backBtn = findViewById(R.id.button2);
        backBtn.setOnClickListener(view -> {

            Toast.makeText(this, "You clicked the confirm button!",
                    Toast.LENGTH_SHORT).show();

            Log.i("Learn", "Back Button Clicked!");
            startActivity(new Intent(PlayerSelect.this, GameActivity.class));
        });


    }
    public static int getPlayercount()
    {
        return playerCount;
    }
}