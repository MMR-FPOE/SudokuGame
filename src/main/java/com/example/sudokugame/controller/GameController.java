package com.example.sudokugame.controller;

import com.example.sudokugame.model.NumberField;
import com.example.sudokugame.model.Sudoku;
import com.example.sudokugame.view.alert.AlertBox;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class GameController {
    // Game's lifes
    int lifes = 7;
    NumberField numberField;
    Sudoku sudoku = new Sudoku();

    // ArrayList where are all NumberField fields
    ArrayList<ArrayList<NumberField>> fields = new ArrayList<>();

    // Graphic Interface variables
    @FXML
    private GridPane sudokuGridPane;
    @FXML
    private Label playerNickname;
    @FXML
    private Label lifesCounter;

    /**
     * Start controller class
     */
    @FXML
    public void initialize(){
        for (int i=0; i<9; i++) {
            // ArrayList that will be a row of fields ArrayList
            ArrayList<NumberField> fieldsRow = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                // NumberField Object
                numberField = new NumberField(i, j);
                if (sudoku.isNotEmpty(i, j)) {
                    gameField(numberField.getField(), sudoku.getNumber(i, j));
                } else
                    emptyField(numberField.getField());
                // Field being added to ArrayList
                fieldsRow.add(numberField);
                // TextField being added to GridPane
                sudokuGridPane.add(numberField.getField(), j, i);
            }
            // ArrayList being added to ArrayList<ArrayList>
            fields.add(fieldsRow);
        }
        keyEvent();
        updateLifes();
    }

    /**
     * KeyEvent assigned to each NumberField in ArrayList<ArrayList>
     */
    private void keyEvent(){
        for (ArrayList<NumberField> row: fields ){
            for (NumberField element: row){
                element.getField().setOnKeyTyped(event -> updateNumber(event, element.getRow(), element.getCol()));
            }
        }
    }

    /**
     * Check every character that is typed in any TextField
     * @param event     Event that is happening
     * @param row       TextField row position
     * @param col       TextField column position
     */
    private void updateNumber(Event event, int row, int col){
        // This TextField is where the event happened
        TextField field = (TextField) event.getSource();
        // TextField content is not empty
        if(!field.getText().isEmpty()){
            char numberInField = field.getText().charAt(0);
            field.setText("");
            // This TextField only receive digits between 1 and 9
            if(Character.isDigit(numberInField) && numberInField != '0'){
                int number = Integer.parseInt(String.valueOf(numberInField));
                field.setText(String.valueOf(numberInField));
                // Put this number in Sudoku Table
                sudoku.setNumber(number, row, col);
                checkNumberInSudokuTable(field, Integer.parseInt(String.valueOf(numberInField)),row, col);
                checkWin();
            }else
                // If number is not digits between 1 and 9, TextField will have a emptyField style
                emptyField(field);
        }
        // TextField content is empty
        else if (field.getText().isEmpty()){
            // Put zero in Sudoku Table
            sudoku.setNumber(0,row,col);
            emptyField(field);}
    }

    /**
     * Check if number doesn't repeat in row, column and quadrant
     *
     * @param field     TextField Object
     * @param number    Number in TextField
     * @param row       TextField row position
     * @param col       TextField column position
     */
    private void checkNumberInSudokuTable(TextField field, int number, int row, int col){
        boolean isRepeat = false;
        // Check number in row
        for (int i = 0; i < 9; i++) {
            // Ignore same position
            if (i == row)
                continue;
            // It finds a coincidence
            if (number == sudoku.getNumber(i,col)) {
                isRepeat = true;
                break;}
        }
        // Check number in column
        for (int i = 0; i < 9; i++) {
            // Ignore same position
            if (i == col)
                continue;
            // It finds a coincidence
            if (number == sudoku.getNumber(row,i)) {
                isRepeat = true;
                break;}
        }
        // Check number in quadrant
        int qRow = row/3;   // Quadrant Row
        int qCol = col/3;   // Quadrant Column
        for (int i = qRow*3; i < (qRow+1)*3; i++){
            for (int j = qCol*3; j < (qCol+1)*3; j++){
                // Ignore same position
                if (i == row && j == col)
                    continue;
                // It finds a coincidence
                if(number == sudoku.getNumber(i,j)){
                    isRepeat = true;
                    break;
                }
            }
        }
        // Field style depends isRepeat boolean value
        if (isRepeat){
            lifes--;    // Player lose a life
            updateLifes();  // Update Hearts Lifes
            wrongNumber(field);}    // Red text indicates player error
        else
            emptyField(field);      // Default TextField Style
    }

    /**
     * Default numbers and TextFields Styles
     *
     * @param field     TextField Object
     * @param number    Number in TextField
     */
    public void gameField(TextField field, int number){
        field.setText(String.valueOf(number));
        field.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        field.setStyle("-fx-text-fill: INDIGO; -fx-background-color: #E6E6FA");
    }

    //

    /**
     * Default TextField Style
     *
     * @param field     TextField Object
     */
    public void emptyField(TextField field){
        field.setStyle("-fx-text-fill: BLACK");
    }

    //

    /**
     * When number is repeat in row, column or quadrant, TextField will adopt this style
     *
     * @param field     TextField Object
     */
    private void wrongNumber(TextField field){
        field.setStyle("-fx-text-fill: RED; -fx-background-color: #FFCBCB");
    }

    /**
     * Update Hearts Lifes variable and set it in game-view
     * */
    private void updateLifes(){
        String lifesHearts = "💖".repeat(lifes);
        lifesCounter.setText(lifesHearts);
    }

    /**
     * Check if Sudoku Table is full
     */
    private void checkWin() {
        if(sudoku.sudokuComplete()) {
            new AlertBox().WinOrLose("Ganaste", "El juego terminó", "¡Felicitades has completado el Sudoku :)");
        }else if(lifes == 0) {
            new AlertBox().WinOrLose("Perdiste", "El juego terminó", "No has completado el Sudoku");
        }
    }

    /**
     * Receive Player name to Put in game-view
     *
     * @param nickname  Assigned in WelcomeController
     */
    public void getPlayerNickname(String nickname){
        playerNickname.setText(nickname);
    }
}