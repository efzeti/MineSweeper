package academy.learnprogramming;

import org.w3c.dom.ls.LSOutput;

public class Cell {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    boolean isBorder;
    boolean isMine;

    int x;
    int y;

    boolean isCovered;
    boolean isFlagged;

    String symbol = " ";

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isBorder = false;
        this.isCovered= false;
        this.isMine = false;
        this.isFlagged = false;
    }


    public void makeBorder() {
        isBorder = true;
        symbol = "█";//  "■"
        isCovered = false;
    }

    public void makeMine() {
        isMine = true;
        symbol = ANSI_YELLOW + "¤" + ANSI_RESET;
    }

    public void makeNumber(int bombs) {
        switch (bombs){
                case 1:
                symbol = ANSI_BLUE + bombs + ANSI_RESET;
                break;

                case 2:
                symbol = ANSI_GREEN + bombs + ANSI_RESET;
                break;

                case 3:
                symbol = ANSI_RED + bombs + ANSI_RESET;
                break;

                case 4:
                symbol = ANSI_PURPLE + bombs + ANSI_RESET;
                break;

                case 5:
                symbol = RED_BOLD + bombs + ANSI_RESET;
                break;

                case 6:
                symbol = ANSI_CYAN + bombs + ANSI_RESET;
                break;

                case 7:
                symbol = ANSI_BLACK + bombs + ANSI_RESET;
                break;

                case 8:
                symbol = ANSI_RESET + bombs + ANSI_RESET;
                break;
        }
        symbol = ANSI_YELLOW + "¤" + ANSI_RESET;
    }

    public String getSymbol(){

        if (isCovered){
            return "■";
        } else {
            return symbol;
        }

    }


}
