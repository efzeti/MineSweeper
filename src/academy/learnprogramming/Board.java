package academy.learnprogramming;

import java.util.*;

public class Board {

    int sizeX;
    int sizeY;
    int bombCount;

    Map<Integer, Cell> gameBoard = new HashMap<>();

    int fieldCount = 0;

    public Board(int sizeXPlayer, int sizeYPlayer, int bombsPlayer)
    {

        if (sizeXPlayer > 28 || sizeYPlayer > 28 || bombsPlayer >= ((sizeXPlayer * sizeYPlayer) / 4) || sizeXPlayer < 8 || sizeYPlayer < 8)
        {
            System.out.println("Sizes X and Y shall be within 10-28, bomb count shall not exceed 1/4 of board area. \n" +
                    "Setting 16x16 board with 32 bombs.");

            this.sizeX = 16;
            this.sizeY = 16;
            this.bombCount = 32;


        }
        else
        {
            this.sizeX = sizeXPlayer;
            this.sizeY = sizeYPlayer;
            this.bombCount = bombsPlayer;

        }


        // making borders

        for (int i = 0; i <= sizeY + 1; i++)
        {
            for (int j = 0; j <= sizeX + 1; j++)
            {

                fieldCount++;

                gameBoard.put(fieldCount, new Cell(j, i));

                if (i == 0 || i == sizeY + 1 || j == 0 || j == sizeX + 1)
                {
//                    System.out.println("i: "+ i + " j: " + j);
                    gameBoard.get(fieldCount).makeBorder();
                }


            }
        }

        // making bombs

        int bombs = 0;
        int picker;

        while (bombs < bombCount)
        {
            picker = 1 + (int) (Math.random() * fieldCount);
//            System.out.println("Picker: " + picker);
            if (!gameBoard.get(picker).isMine() && !gameBoard.get(picker).isBorder()) {
                gameBoard.get(picker).makeMine();
                bombs++;
            }
        }


        // making numbered fields
        for (int i = 1; i < fieldCount; i++)
        {
            fieldCheck(i);
        }


        System.out.println(fieldCount + " fields made.");
        System.out.println(bombs + " bombs made.");


    }

    boolean isFirstShow = true;

    public void show()
    {

        fieldCount = 0;

        if (isFirstShow)
        {
            System.out.println("  ____    __  ____   _  ______  __  __  __  _____  _____   "
                    + "\n |    \\  /  ||    \\ | ||   ___||  \\/  \\|  ||     ||     |  "
                    + "\n |     \\/   ||     \\| | `-.`-. |     /\\   ||    _||     \\  "
                    + "\n |__/\\__/|__||__/\\____||______||____/  \\__||___|  |__|\\__\\ ");

            System.out.println("MINESWEEPER v0.1 board: " + sizeX + "x" + sizeY + " bombs: " + bombCount);
            isFirstShow = false;
        }

        for (int i = 0; i <= sizeY + 1; i++)
        {
            for (int j = 0; j <= sizeX + 1; j++)
            {
                fieldCount++;
                System.out.print(gameBoard.get(fieldCount).getSymbol() + " ");
            }
            System.out.println();
        }

    }

    Scanner s = new Scanner(System.in);

    public void play()
    {

        boolean firstTurn = true;
        String chooseTurn = "E";
        boolean notLost = true;

        while(firstTurn || !chooseTurn.equals("E") && notLost)
        {
            firstTurn = false;
            System.out.println("Type F to flag a field. Type U to uncover a cell. Type E to exit.");
            chooseTurn = s.next().toUpperCase();

            switch (chooseTurn)
            {
                case "F":
                    flag();
                    break;
                case "U":
                    notLost = click();
                    if (!notLost)
                    {
                        System.out.println("                                                       \n" +
                                " __    _ _____  __   _   ____    _____  ______   __    \n" +
                                " \\ \\  ///     \\|  | | | |    |  /     \\|   ___|_|  |_  \n" +
                                "  \\ \\// |     ||  |_| | |    |_ |     | `-.`-.|_    _| \n" +
                                "  /__/  \\_____/|______| |______|\\_____/|______| |__|   \n" +
                                "                                                       \n" +
                                "                                                       ");
                    }
                    break;
            }

            if (gameWon()){
                System.out.println(" __    _ _____  __   _   __  __  __  _____  ____   _  ___ \\n\n" +
                        " \\ \\  ///     \\|  | | | |  \\/  \\|  |/     \\|    \\ | ||   |\\n\n" +
                        "  \\ \\// |     ||  |_| | |     /\\   ||     ||     \\| ||___|\\n\n" +
                        "  /__/  \\_____/|______| |____/  \\__|\\_____/|__/\\____||___|\\n");
                break;
            }

        }


    }

    private void flag()
    {

        int index;

        System.out.print("Select column: ");
        String stringX = s.next();
        System.out.print("Select row: ");
        String stringY = s.next();

        if (stringX.chars().allMatch( Character::isDigit) && stringY.chars().allMatch( Character::isDigit)
                && Integer.parseInt(stringX) > 0 && Integer.parseInt(stringX) <= sizeX
                && Integer.parseInt(stringY) > 0 && Integer.parseInt(stringY) <= sizeY){

            index = Integer.parseInt(stringY) * (sizeY + 2) + Integer.parseInt(stringX) + 1;

            gameBoard.get(index).makeFlagged();
            show();

//            System.out.println("Continue? (Y/N)");
//            if (s.next().toUpperCase().equals("Y")){
//                click();
//            }

        }
        else
        {
            System.out.println("Row and column indexes must be postitive integers smaller\nthan board dimensions.");
        }

    }



    private boolean click(){

        boolean notLost;

        int index;

        System.out.print("Select column: ");
        String stringX = s.next();
        System.out.print("Select row: ");
        String stringY = s.next();

        if (stringX.chars().allMatch( Character::isDigit) && stringY.chars().allMatch( Character::isDigit)
                && Integer.parseInt(stringX) > 0 && Integer.parseInt(stringX) <= sizeX
                && Integer.parseInt(stringY) > 0 && Integer.parseInt(stringY) <= sizeY)
        {

            index = Integer.parseInt(stringY) * (sizeY + 2) + Integer.parseInt(stringX) + 1;

            notLost = gameBoard.get(index).makeVisible();
            show();

//            System.out.println("Continue? (Y/N)");
//            if (s.next().toUpperCase().equals("Y")){
//                click();
//            }

            return notLost;
        }
        else
        {
            System.out.println("Row and column indexes must be postitive integers smaller\nthan board dimensions.");
            return true;
        }


    }

    int rowUp;
    int rowDown;
    int surrBombs = 0;

    private void fieldCheck(int fieldNumber){ // checks how many bombs there are around a field and assigns a number to field
        if (!gameBoard.get(fieldNumber).isBorder() && !gameBoard.get(fieldNumber).isMine())
        {

            rowUp = fieldNumber - sizeX;
            rowDown = fieldNumber + sizeX;

            for (int i = 1; i <=3; i++)
            {
                if (gameBoard.get(rowUp - i).isMine())
                {
                    surrBombs++;
                }
            }

            if (gameBoard.get(fieldNumber - 1).isMine())
            {
                surrBombs++;
            }

            if (gameBoard.get(fieldNumber + 1).isMine())
            {
                surrBombs++;
            }

            for (int i = 1; i <=3; i++){
                if (gameBoard.get(rowDown + i).isMine())
                {
                    surrBombs++;
                }
            }

            gameBoard.get(fieldNumber).makeNumber(surrBombs);

        }



        surrBombs = 0;
    }

    private boolean gameWon()
    {

        int bombsFlagged = 0;

        for (Map.Entry<Integer, Cell> entry : gameBoard.entrySet())
        {
            bombsFlagged = entry.getValue().isMine() && entry.getValue().isFlagged? bombsFlagged + 1 : bombsFlagged;
        }

        if (bombsFlagged == this.bombCount)
        {
            return true;
        }

        return false;
    }

}
