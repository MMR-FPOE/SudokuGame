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

    // NumberField constructor that add all TextField characteristic
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

    // Return row position
    public int getRow(){
        return rowPos;
    }

    // Return column position
    public int getCol(){
        return colPos;
    }

    // Return TextField Object
    public TextField getField(){
        return numberField;
    }

    // Method that put margins among quadrants
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