package com.example.ox;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public TextView[][] cells = new TextView[3][3];
    int[][] cellsColors = new int[3][3]; //*
    public String[][] cellsText = new String[3][3]; //*
    public boolean changeTurn = true;
    public boolean won = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cells[0][0] = findViewById(R.id.cell00);
        cells[0][1] = findViewById(R.id.cell01);
        cells[0][2] = findViewById(R.id.cell02);
        cells[1][0] = findViewById(R.id.cell10);
        cells[1][1] = findViewById(R.id.cell11);
        cells[1][2] = findViewById(R.id.cell12);
        cells[2][0] = findViewById(R.id.cell20);
        cells[2][1] = findViewById(R.id.cell21);
        cells[2][2] = findViewById(R.id.cell22);
        Button button = findViewById( R.id.resetButton);
        button.setBackgroundColor(Color.rgb(15,15,15));
        if(savedInstanceState != null) //*
        {
            won = savedInstanceState.getBoolean("won");
            changeTurn = savedInstanceState.getBoolean("changeTurn");
            cellsText[0] = savedInstanceState.getStringArray("buttonText");
            cellsText[1] = savedInstanceState.getStringArray("buttonText1");
            cellsText[2] = savedInstanceState.getStringArray("buttonText2");
            cellsColors[0] = savedInstanceState.getIntArray("buttonColor");
            cellsColors[1] = savedInstanceState.getIntArray("buttonColor1");
            cellsColors[2] = savedInstanceState.getIntArray("buttonColor2");
            for (int i = 0; i < cellsColors.length; i++) {
                for (int j = 0; j < cellsColors[i].length; j++) {
                    Color color = Color.valueOf(cellsColors[i][j]);
                    cells[i][j].setBackgroundColor(color.toArgb());
                    cells[i][j].setText(cellsText[i][j]);
                }
            }
        }
        else {
            changeTurn = true;
            won = false;
            startColors(); //*
        }
    }

    public void startColors() //*
    {
        for (int i = 0; i < cellsColors.length; i++) {
            for (int j = 0; j < cellsColors[i].length; j++) {
                cellsColors[i][j] = Color.BLACK;
            }
        }
    }

    public void onCellClick(View v) {
        TextView cell = (TextView) v;
        String currentText = cell.getText().toString();
        if (currentText.isEmpty() && !won) {
            if (changeTurn) {
                cell.setText("X");
            } else {
                cell.setText("O");
            }
            if (checkIfWin()) {
                won = true;
            } else {
                changeTurn = !changeTurn;
            }
        }
    }

    private boolean checkIfWin() {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0].getText().toString().equals(cells[i][1].getText().toString()) &&
                    cells[i][1].getText().toString().equals(cells[i][2].getText().toString()) &&
                    !cells[i][0].getText().toString().isEmpty()) {
                cells[i][0].setBackgroundColor(Color.GREEN);
                cellsColors[i][0] = Color.GREEN;//*
                cells[i][1].setBackgroundColor(Color.GREEN);
                cellsColors[i][1] = Color.GREEN;//*
                cells[i][2].setBackgroundColor(Color.GREEN);
                cellsColors[i][2] = Color.GREEN;//*
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (cells[0][j].getText().toString().equals(cells[1][j].getText().toString()) &&
                    cells[1][j].getText().toString().equals(cells[2][j].getText().toString()) &&
                    !cells[0][j].getText().toString().isEmpty()) {
                cells[0][j].setBackgroundColor(Color.GREEN);
                cellsColors[0][j] = Color.GREEN;
                cells[1][j].setBackgroundColor(Color.GREEN);
                cellsColors[1][j] = Color.GREEN;
                cells[2][j].setBackgroundColor(Color.GREEN);
                cellsColors[2][j] =Color.GREEN;
                return true;
            }
        }

        if (cells[0][0].getText().toString().equals(cells[1][1].getText().toString()) &&
                cells[1][1].getText().toString().equals(cells[2][2].getText().toString()) &&
                !cells[0][0].getText().toString().isEmpty()) {
            cells[0][0].setBackgroundColor(Color.GREEN);
            cellsColors[0][0] = Color.GREEN;
            cells[1][1].setBackgroundColor(Color.GREEN);
            cellsColors[1][1] = Color.GREEN;
            cells[2][2].setBackgroundColor(Color.GREEN);
            cellsColors[2][2] = Color.GREEN;
            return true;
        }
        if (cells[0][2].getText().toString().equals(cells[1][1].getText().toString()) &&
                cells[1][1].getText().toString().equals(cells[2][0].getText().toString()) &&
                !cells[0][2].getText().toString().isEmpty()) {
            cells[0][2].setBackgroundColor(Color.GREEN);
            cellsColors[0][2] = Color.GREEN;
            cells[1][1].setBackgroundColor(Color.GREEN);
            cellsColors[1][1] = Color.GREEN;
            cells[2][0].setBackgroundColor(Color.GREEN);
            cellsColors[2][0] = Color.GREEN;
            return true;
        }

        return false;
    }

    public void resetGame(View v) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText("");
                cells[i][j].setBackgroundColor(Color.rgb(15,15,15));
            }
        }
        startColors();
        won = false;
        changeTurn = true;
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)//*
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("won",won);
        savedInstanceState.putBoolean("changeTurn",changeTurn);
        for(int i =0; i < cells.length; i++)
        {
            for (int j = 0; j < cells[i].length; j++) {
                cellsText[i][j] = cells[i][j].getText().toString();
            }
        }
        savedInstanceState.putStringArray("buttonText", cellsText[0]);
        savedInstanceState.putStringArray("buttonText1", cellsText[1]);
        savedInstanceState.putStringArray("buttonText2", cellsText[2]);
        savedInstanceState.putIntArray("buttonColor", cellsColors[0]);
        savedInstanceState.putIntArray("buttonColor1", cellsColors[1]);
        savedInstanceState.putIntArray("buttonColor2", cellsColors[2]);
        
    }

}