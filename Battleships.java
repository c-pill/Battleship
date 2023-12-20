class Battleships
{
    String head;
    // x & y coords of head
    int x;
    int y;
    // size of ship
    int size;
    // pos of ship in checklist
    int fleetPos;
    // if ship already sunk
    boolean sunk = false;
    // dir of ship
    char dir;

    // get statements for member variables
    int getX() {return x;}
    int getY() {return y;}
    String getHead() {return head;}
    // returns size of ship
    int getSize() {return size;}

    // returns pos of ship on checklist
    int getFleetPos() {return fleetPos;}
    
    // returns direction of ship
    char getDirection() {return dir;}

    // sets head of ship
    void setHead(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    void setHead(String pos) {head = pos;}
    
    // sets direction of ship
    void setDirection(char dir) {this.dir = dir;}
    
    //remove ship, returns old direction
    char remove() {return dir;}

    // true if sunk, else false. v true if computer, false if user
    boolean sunk(boolean v, BSBoard board)
    {
        if (sunk)
            return false;
        for (int i = 0; i < getSize(); i++)
        {
            if (dir == 'V')
            {
                if (board.compScrt[8-y+i][x] != 'H' && v)
                    return false;
                else if (board.user[8-y+i][x] != 'H' && !v)
                    return false;
            }
            else
            {
                if (board.compScrt[8-y][x+i] != 'H' && v)
                    return false;
                else if (board.user[8-y][x+i] != 'H' && !v)
                    return false;
            }
        }
        sunk = true;
        return true;
    } 

    // copies battleship (used in copying generic battleship to specified type)
    void copy(Battleships raft)
    {
        setHead(raft.getX(), raft.getY());
        setHead(raft.getHead());
        setDirection(raft.getDirection());
    }
}


// types of Battleships with get methods   
class Carrier extends Battleships
{
    int getSize() {return 5;}
    int getFleetPos() {return 0;}
}
class Battleship extends Battleships
{
    int getSize() {return 4;}
    int getFleetPos() {return 1;}
}
class Cruiser extends Battleships
{
    int getSize() {return 3;}
    int getFleetPos() {return 2;}
}
class Submarine extends Battleships
{
    int getSize() {return 3;}
    int getFleetPos() {return 3;}
}
class Destroyer extends Battleships
{
    int getSize() {return 2;}
    int getFleetPos() {return 4;}
}