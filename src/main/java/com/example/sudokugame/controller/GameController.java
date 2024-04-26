package com.example.sudokugame.controller;

import com.example.sudokugame.model.NumberField;
import com.example.sudokugame.model.Sudoku;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class GameController {
    int lifes = 7;
    NumberField numberField;
    Sudoku sudoku = new Sudoku();
    ArrayList<ArrayList<NumberField>> fields = new ArrayList<>();
    @FXML
    private GridPane sudokuGridPane;
    @FXML
    private Label playerNickname;
    @FXML
    private Label lifesCounter;

    @FXML
    public void initialize(){
        for (int i=0; i<9; i++) {
            ArrayList<NumberField> fieldsRow = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                numberField = new NumberField(i, j);
                if (sudoku.isNotEmpty(i, j)) {
                    gameField(numberField.getField(), sudoku.getNumber(i, j));
                } else
                    emptyField(numberField.getField());
                fieldsRow.add(numberField);
                sudokuGridPane.add(numberField.getField(), j, i);
            }
            fields.add(fieldsRow);
        }
        keyEvent();
        updateLifes();
    }
    private void keyEvent(){
        for (ArrayList<NumberField> row: fields ){
            for (NumberField element: row){
                element.getField().setOnKeyTyped(event -> updateNumber(event, element.getRow(), element.getCol()));
            }
        }
    }
    private void updateNumber(Event event, int row, int col){
        TextField field = (TextField) event.getSource();
        if(!field.getText().isEmpty()){
            char numberInField = field.getText().charAt(0);
            field.setText("");
            if(Character.isDigit(numberInField) && numberInField != '0'){
                int number = Integer.parseInt(String.valueOf(numberInField));
                field.setText(String.valueOf(numberInField));
                sudoku.setNumber(number, row, col);
                checkNumberInSudokuTable(field, Integer.parseInt(String.valueOf(numberInField)),row, col);
                checkWin();
            }else
                emptyField(field);
        } else
            emptyField(field);
    }

    private void checkNumberInSudokuTable(TextField field, int number, int row, int col){
        boolean isRepeat = false;
        // Check number in row
        for (int i = 0; i < 9; i++) {
            if (i == row)
                continue;
            if (number == sudoku.getNumber(i,col)) {
                isRepeat = true;
                break;}
        }
        // Check number in column
        for (int i = 0; i < 9; i++) {
            if (i == col)
                continue;
            if (number == sudoku.getNumber(row,i)) {
                isRepeat = true;
                break;}
        }
        // Check number in quadrant
        int qRow = row/3;
        int qCol = col/3;
        for (int i = qRow*3; i < (qRow+1)*3; i++){
            for (int j = qCol*3; j < (qCol+1)*3; j++){
                if (i == row && j == col)
                    continue;
                if(number == sudoku.getNumber(i,j)){
                    isRepeat = true;
                    break;
                }
            }
        }
        // Field style depends isRepeat boolean value
        if (isRepeat){
            lifes--;
            updateLifes();
            wrongNumber(field);}
        else
            emptyField(field);
    }
    public void gameField(TextField field, int number){
        field.setText(String.valueOf(number));
        field.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        field.setStyle("-fx-text-fill: INDIGO; -fx-background-color: #E6E6FA");
    }
    public void emptyField(TextField field){
        field.setStyle("-fx-text-fill: BLACK");
    }
    private void wrongNumber(TextField field){
        field.setStyle("-fx-text-fill: RED; -fx-background-color: #FFCBCB");
    }
    private void updateLifes(){
        String lifesHearts = "💖".repeat(lifes);
        lifesCounter.setText(lifesHearts);
    }
    private void checkWin() {
        if(sudoku.sudokuComplete())
            System.out.println("YOU WIN");
        else if(lifes == 0)
            System.out.println("YOU LOSE");

    }
    public void getPlayerNickname(String nickname){
        playerNickname.setText(nickname);
    }
}