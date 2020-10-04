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

    public Board(int sizeX, int sizeY, int bombCount) {

        if (sizeX > 28 || sizeY > 28 || bombCount > ((sizeX * sizeY)/4) || sizeX < 10 || sizeY < 10){
            System.out.println("Sizes X and Y shall be within 10-28, bomb count shall not exceed 1/4 of board area. \n" +
                    "Setting 16x16 board with 32 bombs.");

            this.sizeX = 16;
            this.sizeY = 16;
            this.bombCount = 32;

        }

        this.sizeX = sizeX; // +1 for borders
        this.sizeY = sizeY;
        this.bombCount = bombCount;


        // making borders

        for (int i = 0; i <= sizeY + 1;i++){
            for (int j = 0; j <= sizeX + 1;j++){

                fieldCount++;

                initBoard.put(fieldCount, new Cell(j, i));

                if (i == 0 || i == sizeY + 1 || j == 0 || j == sizeX + 1){
                    System.out.println("i: "+ i + " j: " + j);
                    initBoard.get(fieldCount).makeBorder();
                }



            }
        }

        // making bombs

        int bombs = 0;
        int picker;

        while(bombs < bombCount){
            picker = 1 + (int) (Math.random() * fieldCount);
            System.out.println("Picker: " + picker);
            initBoard.get(1);
            initBoard.get(fieldCount);
            if (!initBoard.get(picker).isMine && !initBoard.get(picker).isBorder){
                initBoard.get(picker).makeMine();
                bombs++;
            }
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

}
