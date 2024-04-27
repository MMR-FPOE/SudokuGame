package com.example.sudokugame.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Sudoku implements ISudoku{
    Random rand = new Random();
    ArrayList<ArrayList<Integer>> fullSudokuTable;

    // Public Sudoku constructor that defines Sudoku ArrayList<ArrayList>
    public Sudoku(){
        fullSudokuTable = new ArrayList<>();
        fullSudokuTable.add(new ArrayList<>(Arrays.asList(3, 1, 6, 5, 7, 8, 4, 9, 2)));
        fullSudokuTable.add(new ArrayList<>(Arrays.asList(5, 2, 9, 1, 3, 4, 7, 6, 8)));
        fullSudokuTable.add(new ArrayList<>(Arrays.asList(4, 8, 7, 6, 2, 9, 5, 3, 1)));
        fullSudokuTable.add(new ArrayList<>(Arrays.asList(2, 6, 3, 4, 1, 5, 9, 8, 7)));
        fullSudokuTable.add(new ArrayList<>(Arrays.asList(9, 7, 4, 8, 6, 3, 1, 2, 5)));
        fullSudokuTable.add(new ArrayList<>(Arrays.asList(8, 5, 1, 7, 9, 2, 6, 4, 3)));
        fullSudokuTable.add(new ArrayList<>(Arrays.asList(1, 3, 8, 9, 4, 7, 2, 5, 6)));
        fullSudokuTable.add(new ArrayList<>(Arrays.asList(6, 9, 2, 3, 5, 1, 8, 7, 4)));
        fullSudokuTable.add(new ArrayList<>(Arrays.asList(7, 4, 5, 2, 8, 6, 3, 1, 9)));
        gameTable();    // Choose randoms number to change with zeros
    }

    // For each quadrante, 5 numbers will change with zeros, creating a random illusion
    private void gameTable(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                // ArrayList with 5 no-repeat number between 0 and 8
                ArrayList<Integer> randomNumbers = getRandomNumbers();
                for(int k=0; k<3; k++){
                    for(int l=0; l<3; l++){
                        if(randomNumbers.contains(k*3+l))
                            fullSudokuTable.get(i*3+k).set(j*3+l,0);
                    }
                }
            }
        }
    }

    // Method that creates an ArrayList that have 5 random indexes to change numbers
    private ArrayList<Integer> getRandomNumbers(){
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < 5){
            int number = rand.nextInt(9);
            // No-Repeat numbers
            if(!randomNumbers.contains(number))
                randomNumbers.add(number);
        }
        return randomNumbers;
    }

    // Set number in (row,col) position
    public void setNumber(int number,int row, int col){
        fullSudokuTable.get(row).set(col, number);
    }

    // Verify if a number in (row,col) position is different from zero
    public boolean isNotEmpty(int row, int col){
        return (fullSudokuTable.get(row).get(col) != 0);
    }

    // Return a number in (row,col) position
    public int getNumber(int row, int col){
        return fullSudokuTable.get(row).get(col);
    }

    // Return true if Sudoku Table doesn't have any zeros
    public boolean sudokuComplete() {
        for (ArrayList<Integer> row : fullSudokuTable) {
            for (int number : row) {
                if (number == 0)
                    return false;
            }
        }
        return true;
    }
}
