package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar eekBar;
    TextView textView;
    int main;
    boolean aBoolean=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         eekBar = (SeekBar) findViewById(R.id.seekBar);
         textView = (TextView) findViewById(R.id.textView);
        eekBar.setMax(600);
        eekBar.setProgress(300);

        eekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = progress / 60;
                main=progress;
                int sec = progress - minutes * 60;
                String second = Integer.toString(sec);
                if (second.equals("0")) {
                    second = "00";
                } else if (second.equals("1") || second.equals("2") || second.equals("3") || second.equals("4") || second.equals("5") || second.equals("6") || second.equals("7") || second.equals("8") || second.equals("9")) {
                    second = "0" + second;
                }
                textView.setText(Integer.toString(minutes) + " : " + second);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
        public void buttonClick(View v) {
            final Button btn ;
            btn = (Button) findViewById(R.id.button);
            CountDownTimer count = null;
            if ( aBoolean==false){
                eekBar.setEnabled(false);
                 //btn = (Button) findViewById(R.id.button);
                btn.setText("Stop!!");
                aBoolean=true;
                 count =new CountDownTimer(main * 1000, 1000) {


                    @Override
                    public void onTick(long millisUntilFinished) {
                        long i = millisUntilFinished / 60000;
                        long sec = millisUntilFinished / 1000 - i * 60;
                        Log.i("message is", String.valueOf(millisUntilFinished / 1000));
                        String second = String.valueOf(sec);
                        if (second.equals("0")) {
                            second = "00";
                        } else if (second.equals("1") || second.equals("2") || second.equals("3") || second.equals("4") || second.equals("5") || second.equals("6") || second.equals("7") || second.equals("8") || second.equals("9")) {
                            second = "0" + second;
                        }
                        textView.setText(String.valueOf(i) + " : " + second);
                       // main(i, second);


                    }



                    public void onFinish() {
                        callit();
                        Log.i("time", "end");
                        //btn.setVisibility(View.VISIBLE);
                       //eekBar.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
            else{

                    textView.setText("00:30");
                    eekBar.setProgress(30);
                    eekBar.setEnabled(true);
                    btn.setText("Go!!");
                    count.cancel();
                    aBoolean=false;
                    
                    

            }
        }

    private void callit() {
        MediaPlayer c = MediaPlayer.create(this, R.raw.shapeofu);
        c.start();
    }
}
