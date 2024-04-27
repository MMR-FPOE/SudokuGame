package com.example.sudokugame.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NumberField{
    int rowPos, colPos;
    TextField numberField;

    /**
     * NumberField constructor that add all TextField characteristic
     *
     * @param row Row Position
     * @param col Column Position
     */
    public NumberField(int row, int col){
        rowPos = row;
        colPos = col;
        numberField = new TextField();
        numberField.setMaxHeight(60);
        numberField.setMaxWidth(60);
        numberField.setPadding(new Insets(0));
        numberField.setAlignment(Pos.CENTER);
        numberField.setFocusTraversable(false);
        numberField.setFont(Font.font("Arial", 30 ));
        numberField.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        // To create margins among quadrants
        marginAndBorders(row,col);
    }

    /**
     * Return row position
     */
    public int getRow(){
        return rowPos;
    }

    /**
     * Return column position
     *
     * @return col position
     */
    public int getCol(){
        return colPos;
    }

    /**
     * @return TextField Object
     */
    public TextField getField(){
        return numberField;
    }

    /**
     * Method that put margins among quadrants
     *
     * @param row   TextField Row
     * @param col   TextField Column
     */
    private void marginAndBorders(int row, int col){
        if(row > 2 && row < 6)
            numberField.setTranslateY(5);
        else if(row > 5)
            numberField.setTranslateY(10);
        if(col > 2 && col < 6)
            numberField.setTranslateX(5);
        else if(col > 5)
            numberField.setTranslateX(10);
    }
}