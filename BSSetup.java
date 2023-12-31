import java.util.Random;

class BSSetup {
    BSBoard board;
    short set = 0;

    // constructor that copies board
    BSSetup(BSBoard board)
    {
        this.board = board;
    }

    // sets ships onto user's board
    // pos on board, type of ship, direction, set for comp (true) or user (false), q
    // for quiet (no prints if true)
    boolean setShips(String pos, String ship, String dir, boolean v, boolean q) {
        if (pos.length() != 2 && !q) {
            System.out.println("\t- Position is not on the board");
            return false;
        }
        Battleships raft;
        int x = pos.toUpperCase().charAt(0) - '0' - 17;
        int y = (pos.charAt(1) - '0') - 1;
        char d = dir.toUpperCase().charAt(1);
        switch (ship.toLowerCase()) {
            case "carrier":
                raft = new Carrier();
                break;
            case "battleship":
                raft = new Battleship();
                break;
            case "cruiser":
                raft = new Cruiser();
                break;
            case "submarine":
                raft = new Submarine();
                break;
            case "destroyer":
                raft = new Destroyer();
                break;
            default:
                if (!q)
                    System.out.println("Invalid ship selected");
                return false;
        }
        if (validSet(x, y, raft.getSize(), raft.getFleetPos(), d, v, q)) {
            set++;
            raft.setDirection(d);
            raft.setHead(x, y);
            raft.setHead(pos);
            int size = raft.getSize();
            int flPos = raft.getFleetPos();
            if (!v) {
                board.fleetHeads[flPos] = " " + pos.toUpperCase() + "  ";
                board.userFleet[flPos] = 'x';
                for (int i = 0; i < size && d == 'V'; i++)
                    board.user[8 - y + i][x] = 'X';
                for (int i = 0; i < size && d == 'H'; i++)
                    board.user[8 - y][x + i] = 'X';
                if (flPos == 0)
                    board.uFleet.carry.copy(raft);
                else if (flPos == 1)
                    board.uFleet.battle.copy(raft);
                else if (flPos == 2)
                    board.uFleet.cruise.copy(raft);
                else if (flPos == 3)
                    board.uFleet.sub.copy(raft);
                else if (flPos == 4)
                    board.uFleet.destroy.copy(raft);
                return true;
            } else {
                board.scrtHeads[flPos] = " " + pos.toUpperCase() + "  ";
                board.compScrtFleet[flPos] = 'x';
                for (int i = 0; i < size && d == 'V'; i++)
                    board.compScrt[8 - y + i][x] = 'X';
                for (int i = 0; i < size && d == 'H'; i++)
                    board.compScrt[8 - y][x + i] = 'X';
                if (flPos == 0)
                    board.cFleet.carry.copy(raft);
                else if (flPos == 1)
                    board.cFleet.battle.copy(raft);
                else if (flPos == 2)
                    board.cFleet.cruise.copy(raft);
                else if (flPos == 3)
                    board.cFleet.sub.copy(raft);
                else if (flPos == 4)
                    board.cFleet.destroy.copy(raft);
                return true;
            }
        }
        return false;
    }

    // removes ship from user's board based on user input. returns true if successful, false if not
    boolean removeShip(String pos) {
        int x = pos.toUpperCase().charAt(0) - '0' - 17;
        int y = pos.charAt(1) - '0' - 1;
        boolean inGrid = x + y < 17 && x < 9 && y < 9 && x >= 0 && y >= 0;
        if (!inGrid || pos.length() != 2)
            return false;
        int p = 0;
        char dir = ' ';
        for (int i = 0; i < 5; i++) {
            if (board.fleetHeads[i].substring(1, 3).equals(pos.toUpperCase())) {
                set--;
                switch (i) {
                    case 0:
                        p = 5;
                        dir = board.uFleet.carry.remove();
                        board.fleetHeads[i] = "(5p) ";
                        board.userFleet[i] = 'o';
                        break;
                    case 1:
                        p = 4;
                        dir = board.uFleet.battle.remove();
                        board.fleetHeads[i] = "(4p) ";
                        board.userFleet[i] = 'o';
                        break;
                    case 2:
                        p = 3;
                        dir = board.uFleet.cruise.remove();
                        board.fleetHeads[i] = "(3p) ";
                        board.userFleet[i] = 'o';
                        break;
                    case 3:
                        p = 3;
                        dir = board.uFleet.sub.remove();
                        board.fleetHeads[i] = "(3p) ";
                        board.userFleet[i] = 'o';
                        break;
                    default:
                        p = 2;
                        dir = board.uFleet.destroy.remove();
                        board.fleetHeads[i] = "(2p) ";
                        board.userFleet[i] = 'o';
                        break;
                }
                for (int k = 0; k < p; k++) {
                    if (dir == 'V')
                        board.user[8 - y + k][x] = '-';
                    else
                        board.user[8 - y][x + k] = '-';
                }
                return true;
            }
        }
        return false;
    }
    
    // resets board and fleet
    void emptyBoard() {
        try {removeShip(board.uFleet.carry.getHead());} catch (NullPointerException e) {}
        try {removeShip(board.uFleet.battle.getHead());} catch (NullPointerException e) {}
        try {removeShip(board.uFleet.cruise.getHead());} catch (NullPointerException e) {}
        try {removeShip(board.uFleet.sub.getHead());} catch (NullPointerException e) {}
        try {removeShip(board.uFleet.destroy.getHead());} catch (NullPointerException e) {}
    }

    // generates board. v = (true for computer and false for user)
    void generateBoard(boolean v) {
        String dir, pos;
        int x, y, d, s;
        if (!v)
            emptyBoard();
        Random rand = new Random();
        while (set < 5) {
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            d = rand.nextInt(2);
            s = rand.nextInt(5);
            if (d == 0)
                dir = " V";
            else
                dir = " H";
            pos = (char) (x + 65) + "" + y;
            setShips(pos, board.fleetNames[s], dir, v, true);
        }
        if (v)
            set = 0;
    }

    // checks to see if space on board for ship at starting at position x,y
    // x and y on grid, no. of pegs, ship id, direction, valid set for (comp true or
    // user false) *if comp no prints,
    // q for quiet (true = no prints)
    boolean validSet(int x, int y, int p, int s, char d, boolean v, boolean q) {
        boolean val = true;
        boolean inGrid = x + y < 17 && x < 9 && y < 9 && x >= 0 && y >= 0;
        boolean runOff = (8 - y + p - 1 > 8 && d == 'V') || (x + p - 1 > 8 && d == 'H');
        if (board.userFleet[s] == 'x' && !v) {
            if (!q)
                System.out.println("\t- " + board.fleetNames[s] + " already set");
            val = false;
        }
        if (board.compScrtFleet[s] == 'x' && v)
            return false; 
        if (!inGrid) {
            if (!v && !q)
                System.out.println("\t- Position is not on the board\n");
            return false;
        }
        if (runOff) {
            if (!v && !q)
                System.out.println("\t- Cannot place, runs off board\n");
            return false;
        }
        for (int i = 0; i < p; i++) {
            if (d == 'V') {
                if (board.user[8 - y + i][x] == 'X' && !v) {
                    if (!q)
                        System.out.println("\t- Cannot place vertically, ship already present at " + (char) (65 + x)
                                + (9 - y + i));
                    val = false;
                    break;
                } else if (board.compScrt[8 - y + i][x] == 'X' && v)
                    return false;
            } else if (d == 'H') {
                if (board.user[8 - y][x + i] == 'X' && !v) {
                    if (!q)
                        System.out.println(
                                "\t- Cannot place horizontally, ship already present at " + (char) (65 + y) + (x + i));
                    val = false;
                    break;
                } else if (board.compScrt[8 - y][x + i] == 'X' && v)
                    return false;
            }
        }
        if (!v && !q)
            System.out.println();
        return val;
    }
}