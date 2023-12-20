class BSBoard {

    Fleet cFleet;
    Fleet uFleet;

    String[] fleetNames = { "Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer" };
    String[] fleetChkL = { "Carrier     ",
            "Battleship  ",
            "Cruiser     ",
            "Submarine   ",
            "Destroyer   " };
    String[] fleetHeads = { "(5p) ", "(4p) ", "(3p) ", "(3p) ", "(2p) " };
    String[] compHeads  = { "?  ", "?  ", "?  ", "?  ", "?  " };
    String[] scrtHeads = { "?  ", "?  ", "?  ", "?  ", "?  " };
    boolean begin = false;
    boolean win = false;
    boolean lose = false;
    byte uSunk, cSunk;
    char[][] compView;
    char[][] compScrt;
    char[][] user;
    char[] userFleet;
    char[] compScrtFleet;
    char[] compViewFleet; 
    
    // constructor intializes member variables
    BSBoard() {
        cFleet = new Fleet();
        uFleet = new Fleet();
        uSunk = 0;
        cSunk = 0;
        compView = new char[9][9];
        compScrt = new char[9][9];
        user = new char[9][9];
        userFleet = new char[5];
        userFleet  = new char[] {'o','o','o','o','o'};
        compScrtFleet  = new char[] {'o','o','o','o','o'};
        compViewFleet  = new char[] {'o','o','o','o','o'};
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                user[i][k] = '-';
                compScrt[i][k] = '-';
                compView[i][k] = '-';
            }
        }
    }
}