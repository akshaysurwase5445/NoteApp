package com.example.ashish.noteapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class Activity_pattern extends AppCompatActivity {

    PatternLockView patternLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);

        patternLockView = findViewById(R.id.patternView);

        patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List progressPattern) {

            }

            @Override
            public void onComplete(List pattern) {

                Log.d(getClass().getName(), "Pattern complete: " +
                        PatternLockUtils.patternToString(patternLockView, pattern));
                if (PatternLockUtils.patternToString(patternLockView, pattern).equalsIgnoreCase("123")) {
                    patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
                    Toast.makeText(Activity_pattern.this, "Welcome back", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Activity_pattern.this, NoteList.class);
                    startActivity(intent);
                } else {
                    patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                    Toast.makeText(Activity_pattern.this, "Incorrect password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCleared() {

            }
        });
    }
}