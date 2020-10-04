package academy.learnprogramming;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {

    int sizeX;
    int sizeY;
    int bombCount;

    Map<Integer, Cell> initBoard = new HashMap<>();

    Map<Integer, Cell> borderBoard;
    Map<Integer, Cell> bombBoard;
    Map<Integer, Cell> playBoard;

    int fieldCount = 0;

    public Board(int sizeXPlayer, int sizeYPlayer, int bombsPlayer) {

        if (sizeXPlayer > 28 || sizeYPlayer > 28 || bombsPlayer >= ((sizeXPlayer * sizeYPlayer)/4) || sizeXPlayer < 10 || sizeYPlayer < 10){
            System.out.println("Sizes X and Y shall be within 10-28, bomb count shall not exceed 1/4 of board area. \n" +
                    "Setting 16x16 board with 32 bombs.");

            this.sizeX = 16;
            this.sizeY = 16;
            this.bombCount = 32;


        } else {

            this.sizeX = sizeXPlayer;
            this.sizeY = sizeYPlayer;
            this.bombCount = bombsPlayer;

        }


        // making borders

        for (int i = 0; i <= sizeY + 1;i++){
            for (int j = 0; j <= sizeX + 1;j++){

                fieldCount++;

                initBoard.put(fieldCount, new Cell(j, i));

                if (i == 0 || i == sizeY + 1 || j == 0 || j == sizeX + 1){
//                    System.out.println("i: "+ i + " j: " + j);
                    initBoard.get(fieldCount).makeBorder();
                }



            }
        }

        // making bombs

        int bombs = 0;
        int picker;

        while(bombs < bombCount){
            picker = 1 + (int) (Math.random() * fieldCount);
//            System.out.println("Picker: " + picker);
            if (!initBoard.get(picker).isMine() && !initBoard.get(picker).isBorder()){
                initBoard.get(picker).makeMine();
                bombs++;
            }
        }


        // making numbered fields
        for (int i = 1; i < fieldCount; i++){
            fieldCheck(i);
        }



        System.out.println(fieldCount + " fields made.");
        System.out.println(bombs + " bombs made.");




    }



    public void show(){

        fieldCount = 0;

        System.out.println("  ____    __  ____   _  ______  __  __  __  _____  _____   "
                + "\n |    \\  /  ||    \\ | ||   ___||  \\/  \\|  ||     ||     |  "
                + "\n |     \\/   ||     \\| | `-.`-. |     /\\   ||    _||     \\  "
                + "\n |__/\\__/|__||__/\\____||______||____/  \\__||___|  |__|\\__\\ ");

        System.out.println("MINESWEEPER v0.1 board: " + sizeX + "x" + sizeY + " bombs: " +bombCount);
        for(int i = 0; i <=sizeY + 1; i++) {
            for (int j = 0; j <= sizeX + 1; j++) {
                fieldCount++;
                System.out.print(initBoard.get(fieldCount).getSymbol() + " ");
            }
            System.out.println();
        }

    }

    int rowUp;
    int rowDown;
    int surrBombs = 0;

    private void fieldCheck(int fieldNumber){
        if (!initBoard.get(fieldNumber).isBorder() && !initBoard.get(fieldNumber).isMine()){

            rowUp = fieldNumber - sizeX;
            rowDown = fieldNumber + sizeX;

            for (int i = 1; i <=3; i++){
                if (initBoard.get(rowUp - i).isMine()){
                    surrBombs++;
                }
            }

            if (initBoard.get(fieldNumber - 1).isMine()){
                surrBombs++;
            }

            if (initBoard.get(fieldNumber + 1).isMine()){
                surrBombs++;
            }

            for (int i = 1; i <=3; i++){
                if (initBoard.get(rowDown + i).isMine()){
                    surrBombs++;
                }
            }

            initBoard.get(fieldNumber).makeNumber(surrBombs);

        }



        surrBombs = 0;
    }

}
