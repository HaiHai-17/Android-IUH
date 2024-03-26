package com.example.appiot;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MainActivity extends AppCompatActivity {

    private EditText edtNhap;
    private Button btnGui;

    // Adafruit IO thông tin kết nối MQTT
    private static final String BROKER_URL = "tcp://io.adafruit.com:1883";
    private static final String USERNAME = "guen";
    private static final String KEY = "nhietdo";
    private static final String TOPIC = USERNAME + "/feeds/nhietdo";

    private MqttClient mqttClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNhap = findViewById(R.id.edtNhap);
        btnGui = findViewById(R.id.btnGui);

        try {
            mqttClient = new MqttClient(BROKER_URL, MqttClient.generateClientId(), new MemoryPersistence());
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d("MQTT", "Kết nối đã mất");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // Xử lý khi nhận được tin nhắn từ Adafruit IO (nếu cần)
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d("MQTT", "Gửi dữ liệu thành công");
                }
            });

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(USERNAME);
            options.setPassword(KEY.toCharArray());

            mqttClient.connect();

        } catch (MqttException e) {
            e.printStackTrace();
        }

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guiDuLieuDenAdafruit();
            }
        });
    }

    private void guiDuLieuDenAdafruit() {
        // Lấy giá trị từ EditText
        String duLieuStr = edtNhap.getText().toString();

        try {
            // Chuyển đổi dữ liệu từ kiểu String sang kiểu int
            int duLieu = Integer.parseInt(duLieuStr);

            // Gửi dữ liệu lên Adafruit IO
            MqttMessage message = new MqttMessage();
            message.setPayload(String.valueOf(duLieu).getBytes());
            mqttClient.publish(TOPIC, message);

            // Hiển thị thông báo sau khi gửi thành công
            Toast.makeText(getApplicationContext(), "Đã gửi dữ liệu: " + duLieu, Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException | MqttException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Lỗi khi gửi dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mqttClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
