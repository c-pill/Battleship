import java.util.Random;

class BSPlay extends Fleet {
    BSBoard board;
    String lastHit;
    String nextGuess;
    char nextDir;

    // constructor that copies board and intializes member variables. also edits existing variables for 
    // presentation during play
    BSPlay(BSBoard board) {
        this.board = board;
        nextGuess = "";
        lastHit = "";
        nextDir = ' ';
        board.compViewFleet = new char[] { 'o', 'o', 'o', 'o', 'o' };
        board.compScrtFleet = new char[] { 'o', 'o', 'o', 'o', 'o' };
        board.userFleet = new char[] { 'o', 'o', 'o', 'o', 'o' };
    }

    // method takes in user guess and checks if an enemy ship is hit, as well as if any ships were sunk if
    // a guess is correct. returns true if hit, false if miss
    boolean userGuess(String pos) {
        int x = pos.toUpperCase().charAt(0) - '0' - 17;
        int y = pos.charAt(1) - '0' - 1;
        boolean inGrid = x + y < 17 && x < 9 && y < 9 && x >= 0 && y >= 0;
        if (!inGrid)
            return false;
        System.out.print(pos.toUpperCase() + "... ");
        if (board.compScrt[8 - y][x] == 'X') {
            board.compView[8 - y][x] = 'H';
            board.compScrt[8 - y][x] = 'H';
            System.out.println("HIT!");
            byte ship = board.cFleet.sunkFleet(true, board);
            char dir = ' ';
            byte size = 0;
            switch (ship) {
                case -1:
                    return true;
                case 0:
                    x = board.cFleet.carry.x;
                    y = board.cFleet.carry.y;
                    dir = board.cFleet.carry.dir;
                    size = 5;
                    break;
                case 1:
                    x = board.cFleet.battle.x;
                    y = board.cFleet.battle.y;
                    dir = board.cFleet.battle.dir;
                    size = 4;
                    break;
                case 2:
                    x = board.cFleet.cruise.x;
                    y = board.cFleet.cruise.y;
                    dir = board.cFleet.cruise.dir;
                    size = 3;
                    break;
                case 3:
                    x = board.cFleet.sub.x;
                    y = board.cFleet.sub.y;
                    dir = board.cFleet.sub.dir;
                    size = 3;
                    break;
                case 4:
                    x = board.cFleet.destroy.x;
                    y = board.cFleet.destroy.y;
                    dir = board.cFleet.destroy.dir;
                    size = 2;
                    break;
            }
            board.cSunk++;
            board.compViewFleet[ship] = 'x';
            board.compScrtFleet[ship] = 'x';
            board.compHeads[ship] = board.scrtHeads[ship];
            System.out.println(board.fleetNames[ship] + " SUNK!");
            for (int i = 0; i < size; i++) {
                if (dir == 'V') {
                    board.compScrt[8 - y + i][x] = 'S';
                    board.compView[8 - y + i][x] = 'S';
                } else if (dir == 'H') {
                    board.compScrt[8 - y][x + i] = 'S';
                    board.compView[8 - y][x + i] = 'S';
                }
            }
            return true;
        } else {
            board.compScrt[8 - y][x] = 'o';
            board.compView[8 - y][x] = 'o';
            System.out.println("Miss!");
            return false;
        }
    }

    // method that creates the computer's next guess if no previous hits and checks whether or not the attack 
    // was successful. If a successful hit, will check if any ships were sunk using sunkFleet(boolean, BSBoard)
    // from class Fleet in Battleships.java. If no ships are sunk, will call nextGuess(String) and dirGuess(String)
    // to generate computer's next guess. returns true if hit and false if missed
    boolean compGuess(char user[][]) {
        Random rand = new Random();
        String pos;
        int x = 0;
        int y = 0;
        boolean valid = false;
        if (nextGuess.equals("")) {
            while (!valid) {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                if (nextDir == ' ')
                {
                    int z = rand.nextInt(2);
                    if (z == 0)
                        nextDir = 'V';
                    else
                        nextDir = 'H';
                }
                if (x + y < 17 && x < 9 && y < 9 && x >= 0 && y >= 0) {
                    if (user[8 - y][x] == '-' || user[8 - y][x] == 'X')
                    {
                        pos = (char) (x + 65) + "" + (y + 1);
                        System.out.println(pos);
                        if (!validGuess(pos))
                        {
                            if (nextDir == 'V')
                                nextDir = 'H';
                            else
                                nextDir = 'V';
                        }
                        if (validGuess(pos))
                            valid = true;
                    }
                }
            }
            pos = (char) (x + 65) + "" + (y + 1);
        } else {
            pos = nextGuess;
            x = nextGuess.toUpperCase().charAt(0) - '0' - 17;
            y = nextGuess.charAt(1) - '0' - 1;
        }
        System.out.print("Computer attacks " + pos + "... ");
        if (user[8 - y][x] == 'X') {
            System.out.println("HIT!");
            user[8 - y][x] = 'H';
            
            if (!lastHit.isEmpty())
                dirGuess(pos);
            lastHit = pos;
            
            byte ship = board.uFleet.sunkFleet(false, board);
            byte size = 0;
            char dir = ' ';
            switch (ship) {
                case -1:
                    compNextGuess(lastHit);
                    return true;
                case 0:
                    x = board.uFleet.carry.x;
                    y = board.uFleet.carry.y;
                    dir = board.uFleet.carry.dir;
                    size = 5;
                    break;
                case 1:
                    x = board.uFleet.battle.x;
                    y = board.uFleet.battle.y;
                    dir = board.uFleet.battle.dir;
                    size = 4;
                    break;
                case 2:
                    x = board.uFleet.cruise.x;
                    y = board.uFleet.cruise.y;
                    dir = board.uFleet.cruise.dir;
                    size = 3;
                    break;
                case 3:
                    x = board.uFleet.sub.x;
                    y = board.uFleet.sub.y;
                    dir = board.uFleet.sub.dir;
                    size = 3;
                    break;
                case 4:
                    x = board.uFleet.destroy.x;
                    y = board.uFleet.destroy.y;
                    dir = board.uFleet.destroy.dir;
                    size = 2;
                    break;
            }
            board.uSunk++;
            board.userFleet[ship] = 'x';
            System.out.println(board.fleetNames[ship] + " SUNK!");
            for (int i = 0; i < size; i++) {
                if (dir == 'V')
                    board.user[8 - y + i][x] = 'S';
                else if (dir == 'H')
                    board.user[8 - y][x + i] = 'S';
            }
            
            boolean hits = false;
            for (int i = 0; i < 9; i++)
            {
                for (int k = 0; k < 9; k++)
                {
                    if (board.user[i][k] == 'H')
                    {
                        hits = true;
                        lastHit = (char) (i + 65) + "" + k;
                        break;
                    }
                }
            }
            
            if (!hits)
                lastHit = "";
            
            nextGuess = "";
            nextDir = ' ';
            return true;
        } else {
            user[8 - y][x] = 'o';
            System.out.println("MISS!");
            if (!lastHit.isEmpty())
                compNextGuess(lastHit);
            if (nextDir == 'V')
                nextDir = 'H';
            else
                nextDir = 'V';
            return false;
        }
    }

    // method that creates computer's next guess based on the last hit. calculates if a ship can fit 
    // in the guess area, and runs validGuess(String) to check if the guess is valid. if not valid, 
    // the direction of guessed ship will switch 
    void compNextGuess(String lastHit) {
        int x = lastHit.toUpperCase().charAt(0) - '0' - 17;
        int y = lastHit.charAt(1) - '0' - 1;
        boolean back = false;
        while (board.user[8 - y][x] != '-' && board.user[8 - y][x] != 'X') {
            if (!back) {
                if (nextDir == 'V') {
                    y--;
                    if (!(x + y < 17 && x < 9 && y < 9 && x >= 0 && y >= 0)) {
                        y++;
                        back = true;
                    } else if (board.user[8 - y][x] == 'o') {
                        y++;
                        back = true;
                    }
                } else {
                    x++;
                    if (!(x + y < 17 && x < 9 && y < 9 && x >= 0 && y >= 0)) {
                        x--;
                        back = true;
                    } else if (board.user[8 - y][x] == 'o') {
                        x--;
                        back = true;
                    }
                }
            } else {
                if (nextDir == 'V') {
                    y++;
                    if (!(x + y < 17 && x < 9 && y < 9 && x >= 0 && y >= 0)) {
                        y--;
                        back = false;
                        nextDir = 'H';
                    } else if (board.user[8 - y][x] == 'o') {
                        y--;
                        back = false;
                        nextDir = 'H';
                    }
                } else {
                    x--;
                    if (!(x + y < 17 && x < 9 && y < 9 && x >= 0 && y >= 0)) {
                        x++;
                        back = false;
                        nextDir = 'V';
                    } else if (board.user[8 - y][x] == 'o') {
                        x++;
                        back = false;
                        nextDir = 'V';
                    }
                }
            }
        }

        nextGuess = (char) (x + 65) + "" + (y + 1);
        if (!validGuess(nextGuess))
        {
            if (nextDir == 'V')
                nextDir = 'H';
            else
                nextDir = 'V';
        }
    }

    // method checks if a guess is valid, making sure the potential ship fits where the computer wants to attack.
    // returns true if valid, false if not
    boolean validGuess(String guess)
    {
        int x = guess.toUpperCase().charAt(0) - '0' - 17;
        int y = guess.charAt(1) - '0' - 1;
        int room = 0;
        int size;
        int tmpY = y;
        int tmpX = x;
        boolean forward = true;
        while (board.user[8-tmpY][x] != 'o')
        {
            if (nextDir == 'V')
            {
                if (forward)
                    tmpY--;
                else
                    tmpY++;
                if (8-tmpY < 0 || 8-tmpY > 8)
                {
                    if (forward)
                    {
                        forward = false;
                        tmpY = y;
                    }
                    else
                        break;
                }
                room++;
            }
            else if (nextDir == 'H')
            {
                if (forward) 
                    tmpX++;
                else 
                    tmpX--;
                if (tmpX > 8 || tmpX < 0)
                {
                    if (forward)
                    {
                        forward = false;
                        tmpX = x;
                    }
                    else
                        break;
                }
                room++;
            }
        }
        for (int i = 0; i < 5; i++)
        {
            if (i == 0)
                size = 5;
            else if (i == 1)
                size = 4;
            else if (i == 2 || i == 3)
                size = 3;
            else
                size = 2;
            if (board.userFleet[i] == 'o')
            {
                if (size < room)
                    return true;
            }
        }
        return false;
    }

    // method that makes sure the computer guesses the ship of the last hit follows a single direction,
    // avoiding unintellgient guesses 
    void dirGuess(String pos)
    {
        int pX = pos.toUpperCase().charAt(0) - '0' - 17;
        int lX = lastHit.toUpperCase().charAt(0) - '0' - 17;
        
        if (pX == lX)
            nextDir = 'V';
        else
            nextDir = 'H';
    }
}