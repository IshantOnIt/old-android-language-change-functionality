package com.example.languagetestapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {

    private static final String PREF_LANG_KEY = "SelectedLanguage";
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the language preference before setting the content view
        loadLocale();
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        Button btnEnglish = findViewById(R.id.btnEnglish);
        Button btnFrench = findViewById(R.id.btnFrench);

        // Set click listener for the English button
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the locale to English
                setLocale("en");
            }
        });

        // Set click listener for the French button
        btnFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the locale to French
                setLocale("fr");
            }
        });
    }

    private void setLocale(String langCode) {
        // Check if the selected language is already the current one
        SharedPreferences prefs = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String currentLang = prefs.getString(PREF_LANG_KEY, "en");

        if (!currentLang.equals(langCode)) {
            // Update the language and trigger a restart only if it's a new selection
            Locale locale = new Locale(langCode);
            Locale.setDefault(locale);
    
            Resources resources = getResources();
            Configuration config = resources.getConfiguration();
            config.setLocale(locale);
            resources.updateConfiguration(config, resources.getDisplayMetrics());
    
            SharedPreferences.Editor editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
            editor.putString(PREF_LANG_KEY, langCode);
            editor.apply();
    
            Toast.makeText(MainActivity.this, "Language changed!", Toast.LENGTH_SHORT).show();
            recreate();
        }
    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String langCode = prefs.getString(PREF_LANG_KEY, "en");
        
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
