class Fleet extends Battleships
{
    Carrier carry;
    Battleship battle;
    Cruiser cruise;
    Submarine sub;
    Destroyer destroy;

    // initializes member variables
    Fleet()
    {
        carry = new Carrier();
        battle = new Battleship();
        cruise = new Cruiser();
        sub = new Submarine();
        destroy = new Destroyer();
    }

    // determines if fleet is sunk v true if computer, false if user
    byte sunkFleet(boolean v, BSBoard board)
    {
        if (carry.sunk(v,board))
            return 0;
        if (battle.sunk(v,board))
            return 1;
        if (cruise.sunk(v,board))
            return 2;
        if (sub.sunk(v,board))
            return 3;
        if (destroy.sunk(v,board))
            return 4;
        return -1;
    }
}