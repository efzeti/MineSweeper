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

    private static boolean minesVisible = false;
    private static String mineSymbol = ANSI_YELLOW + "¤" + ANSI_RESET;


    private boolean isBorder;
    private boolean isMine;

    private int x;
    private int y;

    private boolean isCovered;
    private boolean isFlagged;

    String symbol = " ";

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isBorder = false;
        this.isCovered = true;  // FALSE FOR TESTING
        this.isMine = false;
        this.isFlagged = false;
    }


    public void makeBorder() {
        isBorder = true;
        symbol = "&";
        isCovered = false;
    }

    public void makeMine() {
        isMine = true;
        symbol = mineSymbol;
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
//        symbol = ANSI_YELLOW + "¤" + ANSI_RESET;
    }

    public boolean makeVisible(){
        if (isMine)
        {
            minesVisible = true;
            symbol = ANSI_RED + "X" + ANSI_RESET;

            return false;
        }

        if (isFlagged)
        {
            isFlagged = false;
        }
        isCovered = false;

        return true;
    }

    public void makeFlagged(){
        if (isCovered || (!isCovered && isFlagged))
        {
            isFlagged = !isFlagged;
            isCovered = !isCovered;
        }
        else
        {
            System.out.println("Cannot flag uncovered cell.");
        }
    }

    public String getSymbol() {

        if(isMine && minesVisible){
            return symbol;
        } else if (isCovered){
            return "■";
        } else if(isFlagged){
            return ANSI_WHITE + "F" + ANSI_RESET;
        }
        else
        {
            return symbol;
        }

    }

    public boolean isBorder() {
        return isBorder;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
