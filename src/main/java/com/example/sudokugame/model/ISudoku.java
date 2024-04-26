package com.example.sudokugame.model;

public interface ISudoku {

    void setNumber(int number,int row, int col);

    boolean isNotEmpty(int row, int col);

    int getNumber(int row, int col);

    boolean sudokuComplete();
}
