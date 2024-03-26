// NoteActivity.java
package com.example.stenotes;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    private EditText noteEditText;
    private Button cancelButton;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteEditText = findViewById(R.id.noteText);
        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveButton);

        String selectedNote = getIntent().getStringExtra("selectedNote");
        noteEditText.setText(selectedNote);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Discard changes?")
                .setMessage("Are you sure you want to discard your changes to this note?")
                .setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton(R.string.no_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing if the user clicks "No"
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void saveNote() {
        String noteText = noteEditText.getText().toString().trim();

        if (!noteText.isEmpty()) {
            String updatedNote = "";
            updatedNote += noteText;

            finishWithResult(true, updatedNote);
        } else {
            Toast.makeText(this, "Title or Note Text cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }



    private void finishWithResult(boolean saveChanges, String updatedNote) {
        Intent resultIntent = new Intent();
        if (saveChanges) {
            resultIntent.putExtra("updatedNote", updatedNote);
        }
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void showCancelConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Lose changes?")
                .setMessage("Are you sure you want to lose your changes to this note?")
                .setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishWithResult(true, null);
                    }
                })
                .setNegativeButton(R.string.no_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Không làm gì khi nhấn "No"
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public void onBackPressed() {
        showCancelConfirmationDialog();
    }
}
