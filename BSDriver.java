import java.util.*;

public class BSDriver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println();
        AStar intro = new AStar("Battleship");
        AStar setup = new AStar("  Setup");
        AStar begin = new AStar("  Begin!");
        AStar win = new AStar("You win!");
        AStar end = new AStar("Game over");

        String optionStr;
        String direction;
        String pos;
        String ship;
        boolean start = true;
        boolean testPov = false;
        char option;

        while (start) {
            start = false;
            intro.printWide(2);
            System.out.println("\n");
            setup.printWide(2);
            System.out.println("\n");

            BSBoard board = new BSBoard();
            BSSetup set = new BSSetup(board);
            BSView view = new BSView();

            set.generateBoard(true);
            System.out.println("Boards generated. User must set their ships before game can begin.\n");
            view.printUserPov(set.board);

            while (!set.board.begin) {
                System.out.print("Enter command: ");
                optionStr = in.nextLine();
                option = optionStr.charAt(0);
                System.out.println("---------------------------------");

                if (optionStr.length() > 1)
                    System.out.println("Invalid command, try again");
                else if (option == 's') {
                    if (set.set < 5) {
                        System.out.println("Set ship. ex: E4 Cruiser Vertical\n---------------------------------");
                        pos = in.next();
                        ship = in.next();
                        direction = in.nextLine();
                        try {
                            set.setShips(pos, ship, direction, false, false);
                        } catch (StringIndexOutOfBoundsException e) {
                        }
                    } else
                        System.out.println("No more ships to set");
                } else if (option == 'r') {
                    if (set.set > 0) {
                        System.out.println(
                                "Remove ship. ex: A4 => removes ship with head at A4\n---------------------------------");
                        pos = in.nextLine();
                        set.removeShip(pos);
                    } else
                        System.out.println("No more ships to remove");
                } else if (option == 'e') {
                    set.emptyBoard();
                } else if (option == 'g') {
                    System.out.println("New board generated.\n");
                    set.generateBoard(false);
                } else if (option == 'b') {
                    if (set.set == 5)
                    {
                        set.board.begin = true;
                        break;
                    }
                    else
                        System.out.println("Set remaining ships onto board to begin\n");
                } else
                    System.out.println("Invalid command, try again\n");
                view.printUserPov(set.board);
            }

            System.out.println("\n");
            begin.printWide(2);
            System.out.println("\n\n");
            BSPlay play = new BSPlay(set.board);

            while (board.begin && !board.win && !board.lose) {
                if (!testPov)
                    view.printUserPov(play.board);
                else
                    view.printTestPov(play.board);
                System.out.print("Enter command: ");
                optionStr = in.nextLine();
                option = optionStr.charAt(0);
                System.out.println("---------------------------------");

                if (optionStr.length() > 1)
                    System.out.println("Invalid command, try again\n");
                else if (option == 'a') {
                    System.out.println("ATTACK! where? ex: A4\n---------------------------------");
                    play.userGuess(in.nextLine());
                    System.out.println("\n---------------------------------\nComputer's Turn\n---------------------------------");
                    play.compGuess(board.user);
                    if (board.cSunk == 5)
                        board.win = true;
                    if (board.uSunk == 5)
                        board.lose = true;
                    System.out.println();
                        
                } else if (option == 'f')
                    board.lose = true;

                //Testing command. Reveals computer pov
                else if (option == 'r')
                {
                    if (testPov)
                        testPov = false;
                    else
                        testPov = true;
                    System.out.println("Revealing Computer Board\n---------------------------------\n");
                }
                
                //Testing command. Barrage continues until user is hit
                else if (option == 'b')
                {
                    boolean cont = play.compGuess(board.user);
                    while (!cont)
                        cont = play.compGuess(board.user);
                    System.out.println("\n");
                    if (board.uSunk == 5)
                        board.lose = true;
                }
                else
                    System.out.println("Invalid command, try again\n");
            }

            System.out.println("\n");
            if (board.win)
                win.printDiag(2);
            else
                end.printDiag(2);
            System.out.println("\n");
            view.printFinalPov(board);

            System.out.print("Play again?: ");
            optionStr = in.nextLine();
            option = optionStr.charAt(0);
            if (optionStr.length() == 1 && option == 'y')
                start = true;
            System.out.println("\n");
        }
        in.close();
    }
}