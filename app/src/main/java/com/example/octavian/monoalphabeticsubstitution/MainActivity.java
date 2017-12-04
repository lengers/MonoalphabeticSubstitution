package com.example.octavian.monoalphabeticsubstitution;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button decryptButton = (Button) findViewById(R.id.decryptButton);
        Button encryptButton = (Button) findViewById(R.id.encryptButton);
        final TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cipherResult = cipherWork(false);
                Log.e("DEBUG", cipherResult);
                resultTextView.setText(cipherResult);
            }
        });

        encryptButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               String cipherResult = cipherWork(true);
               Log.e("DEBUG", cipherResult);
               resultTextView.setText(cipherResult);
           }
        });
    }

    public String cipherWork(Boolean encrypt) {
        EditText alphabetText = (EditText) findViewById(R.id.AlphabetEdit);
        String cipherAlphabetString = alphabetText.getText().toString().toUpperCase();
        char[] cipherAlphabet = cipherAlphabetString.toCharArray();
        char[] cleanedAlphabet = removeDuplicates(cipherAlphabet);

        EditText inputText = (EditText) findViewById(R.id.stringEdit);
        char[] inputTextString = inputText.getText().toString().toUpperCase().toCharArray();
        Log.d("DEBUG", String.valueOf(cleanedAlphabet.length));
        Log.d("DEBUG", String.valueOf(cleanedAlphabet.toString()));
        String output = "";


        if (cipherAlphabet.length != 26) {
            Toast.makeText(MainActivity.this, "The alphabet you provide needs to be 26 characters long.", Toast.LENGTH_LONG).show();
            return output;
        } else if (cipherAlphabet.length != cleanedAlphabet.length) {
            Toast.makeText(MainActivity.this, "Some characters seem to occur more than just once in your alphabet.", Toast.LENGTH_LONG).show();
            return output;
        } else if (cipherAlphabetString.matches("([A-Z]{26})")) {
            for (char c : inputTextString) {
                output = output + (encrypt ?
                        cipherAlphabetString.charAt(((int) c) - 65) :
                        (char) (cipherAlphabetString.indexOf(c) + 65)
                );
            }

            return output.toLowerCase();
        } else {
            Toast.makeText(MainActivity.this, "Your alphabet contains characters that I do not understand.", Toast.LENGTH_LONG).show();
            return output;
        }
    }

    private char[] removeDuplicates(char[] array) {
        String _array = "";
        for(int i = 0; i < array.length; i++) {
            if(_array.indexOf(array[i]) == -1) // check if a char already exist, if not exist then return -1
                _array = _array+array[i];      // add new char
        }
        return _array.toCharArray();
    }

    public void getAlphabet() {}

}
