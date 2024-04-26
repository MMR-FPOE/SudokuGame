package com.example.sudokugame.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Sudoku implements ISudoku{
    Random rand = new Random();
    ArrayList<ArrayList<Integer>> fullSudokuTable;

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
        gameTable();
    }
    private void gameTable(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
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

    //private
    private ArrayList<Integer> getRandomNumbers(){
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < 5){
            int number = rand.nextInt(9);
            if(!randomNumbers.contains(number))
                randomNumbers.add(number);
        }
        return randomNumbers;
    }
    public void setNumber(int number,int row, int col){
        fullSudokuTable.get(row).set(col, number);
    }
    public boolean isNotEmpty(int row, int col){
        return (fullSudokuTable.get(row).get(col) != 0);
    }
    public int getNumber(int row, int col){
        return fullSudokuTable.get(row).get(col);
    }
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
