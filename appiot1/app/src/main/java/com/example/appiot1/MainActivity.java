package com.example.appiot1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToThingSpeak();
            }
        });
    }

    private void sendDataToThingSpeak() {
        String data = editText.getText().toString();
        if (!data.isEmpty()) {
            try {
                int intValue = Integer.parseInt(data);
                sendToThingSpeak(intValue);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập một số nguyên", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendToThingSpeak(int value) {
        String channelId = "2403674";
        String apiKey = "1B1LFHAG9GYTB4SG";
        ThingSpeakChannel channel = new ThingSpeakChannel(channelId, apiKey);
        channel.connect();
        channel.send("Nhiệt Độ", value);
        Toast.makeText(MainActivity.this, "Dữ liệu đã được gửi lên ThingSpeak: " + value, Toast.LENGTH_SHORT).show();
    }

}
