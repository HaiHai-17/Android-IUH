package com.example.bai10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, bang, thuong, tich, tong, hieu, cham, del;
    private EditText edtKq;
    private TextView txtKq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtKq = findViewById(R.id.edtKetqua);
        txtKq = findViewById(R.id.txtKetqua);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        thuong = findViewById(R.id.btnthuong);
        tich = findViewById(R.id.btntich);
        tong = findViewById(R.id.btntong);
        hieu = findViewById(R.id.btnhieu);
        cham = findViewById(R.id.btncham);
        del = findViewById(R.id.btnDel);
        bang = findViewById(R.id.btnbang);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.setText("");
                txtKq.setText("");
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("9");
            }
        });

        thuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("/");
            }
        });

        tich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("*");
            }
        });

        tong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("+");
            }
        });

        hieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append("-");
            }
        });

        cham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKq.append(".");
            }
        });


        bang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression = edtKq.getText().toString();
                try {
                    // Thực hiện tính toán biểu thức
                    double result = evaluateExpression(expression);
                    // Hiển thị kết quả lên TextView
                    txtKq.setText(String.valueOf(result));
                } catch (Exception e) {
                    // Xử lý nếu có lỗi xảy ra trong quá trình tính toán
                    txtKq.setText("Lỗi");
                }
            }

            // Phương thức để tính toán biểu thức
            private double evaluateExpression(String expression) {
                // Tách các phần tử của biểu thức (số và toán tử) ra thành mảng
                String[] elements = expression.split("(?<=[-+*/])|(?=[-+*/])");

                // Sử dụng một danh sách liên kết để lưu trữ các phần tử
                LinkedList<String> tokens = new LinkedList<>(Arrays.asList(elements));

                // Thực hiện tính toán trong vòng lặp
                while (tokens.size() > 1) {
                    // Tìm vị trí của toán tử ưu tiên cao nhất (* hoặc /)
                    int operatorIndex = -1;
                    for (int i = 0; i < tokens.size(); i++) {
                        if (tokens.get(i).equals("*") || tokens.get(i).equals("/")) {
                            operatorIndex = i;
                            break;
                        }
                    }

                    // Nếu không tìm thấy toán tử ưu tiên cao nhất, tìm toán tử tiếp theo (+ hoặc -)
                    if (operatorIndex == -1) {
                        for (int i = 0; i < tokens.size(); i++) {
                            if (tokens.get(i).equals("+") || tokens.get(i).equals("-")) {
                                operatorIndex = i;
                                break;
                            }
                        }
                    }

                    // Lấy hai số trước và sau toán tử
                    double num1 = Double.parseDouble(tokens.get(operatorIndex - 1));
                    double num2 = Double.parseDouble(tokens.get(operatorIndex + 1));

                    // Thực hiện phép toán và thay thế phần tử tại vị trí toán tử và hai số trước và sau nó
                    double result;
                    switch (tokens.get(operatorIndex)) {
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            result = num1 / num2;
                            break;
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        default:
                            throw new IllegalArgumentException("Toán tử không hợp lệ: " + tokens.get(operatorIndex));
                    }

                    tokens.set(operatorIndex, String.valueOf(result));
                    tokens.remove(operatorIndex + 1);
                    tokens.remove(operatorIndex - 1);
                }

                return Double.parseDouble(tokens.get(0));
            }
        });
    }
}
