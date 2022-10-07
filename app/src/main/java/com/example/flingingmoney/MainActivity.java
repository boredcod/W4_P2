package com.example.flingingmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    TextView won;
    TextView yen;
    TextView yuan;
    TextView rupi;
    EditText dollar;
    Double d;
    String d_w;
    String d_yen;
    String d_yuan;
    String d_rupi;
    float initialY;
    private GestureDetector detector;
    static double parseDouble(String s) {
        return s == "" ? 0.00 : Double.parseDouble(s);
    }

    @Override
    public boolean onDown(MotionEvent e){
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Double yos = new Double(dollar.getText().toString());
        if (motionEvent.getY() < motionEvent1.getY()) {
            yos += 0.1;
            System.out.println("up");
            dollar.setText(yos.toString());
        }
        else{
            yos -= 0.1;
            System.out.println("down");
            dollar.setText(yos.toString());
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        this.detector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        Double yos = new Double(dollar.getText().toString());
        if (e1.getY() < e2.getY()) {
            yos += 1.0;
            System.out.println("up");
            dollar.setText(yos.toString());
        }
        else{
            yos -= 1.0;
            System.out.println("down");
            dollar.setText(yos.toString());
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        won = findViewById(R.id.won);
        yen = findViewById(R.id.yen);
        yuan = findViewById(R.id.yuan);
        dollar = findViewById(R.id.dollar);
        rupi = findViewById(R.id.rupi);
        detector = new GestureDetector(this,this);
        dollar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("hey");
                try {
                    d = new Double(dollar.getText().toString());
                } catch (NumberFormatException e) {
                    d = 0.0;
                }
                if (d > 0) {
                    d_w = Double.toString(d * 1400);
                    d_yen = Double.toString(d * 145);
                    d_yuan = Double.toString(d * 7);
                    d_rupi = Double.toString(d*82);

                    won.setText(d_w);
                    yen.setText(d_yen);
                    yuan.setText(d_yuan);
                    rupi.setText(d_rupi);
                } else {
                    d = 0.0;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}