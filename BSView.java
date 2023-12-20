class BSView 
{
    // prints user board, fleet, commands, computer board (user pov), and computer fleet (user pov)
    void printUserPov(BSBoard board) {
        int val = 9;
        System.out.print("   Computer (User POV)\t\t       Computer Fleet\t\t\t User\t\t\t\t  User Fleet\t\t   ");
        if (!board.begin)
            System.out.print("(Setup) ");
        System.out.println("Commands:");
        for (int i = 0; i < 9; i++, val--) {
            System.out.print(val + " | ");
            for (int k = 0; k < 9; k++)
                System.out.print(board.compView[i][k] + " ");
            System.out.print("|\t\t");
            if (i < 6 && i > 0)
                System.out.print(board.fleetChkL[i - 1] + "  " + board.compHeads[i - 1] + " |      " + board.compScrtFleet[i - 1]);
            else
                System.out.print("\t\t\t");
            System.out.print("\t" + val + " | ");
            for (int k = 0; k < 9; k++)
                System.out.print(board.user[i][k] + " ");
            System.out.print("|\t\t");
            if (i < 6 && i > 0)
                System.out.print(board.fleetChkL[i - 1] + "  " + board.fleetHeads[i - 1] + " |      " + board.userFleet[i - 1]);
            if (!board.begin) {
                if (i == 1)
                    System.out.print("\t\ts : set ship");
                if (i == 2)
                    System.out.print("\t\tr : remove ship");
                if (i == 3)
                    System.out.print("\t\te : empty board");
                if (i == 4)
                    System.out.print("\t\tg : generate board");
                if (i == 5)
                    System.out.print("\t\tb : begin game");
            }
            if (board.begin) {
                if (i == 1)
                    System.out.print("\t\ta : attack");
                if (i == 2)
                    System.out.print("\t\tf : forfeit");
            }
            System.out.println();
        }
        System.out.println("    A B C D E F G H I\t\t\t\t\t\t    A B C D E F G H I\n");
    }

    // prints user board, fleet, commands, computer board, and computer fleet
    void printFinalPov(BSBoard board) {
        int val = 9;
        System.out.print("    \tComputer          \t       Computer Fleet\t\t\t User\t\t\t\t  User Fleet\t\t   ");
        System.out.println("Commands:");
        for (int i = 0; i < 9; i++, val--) {
            System.out.print(val + " | ");
            for (int k = 0; k < 9; k++)
                System.out.print(board.compScrt[i][k] + " ");
            System.out.print("|\t\t");
            if (i < 6 && i > 0)
                System.out.print(board.fleetChkL[i - 1] + board.scrtHeads[i - 1] + " |      " + board.compScrtFleet[i - 1]);
            else
                System.out.print("\t\t\t");
            System.out.print("\t" + val + " | ");
            for (int k = 0; k < 9; k++)
                System.out.print(board.user[i][k] + " ");
            System.out.print("|\t\t");
            if (i < 6 && i > 0)
                System.out.print(board.fleetChkL[i - 1] + "  " + board.fleetHeads[i - 1] + " |      " + board.userFleet[i - 1]);
            if (i == 1)
                System.out.print("\t\ty : start new game");
            if (i == 2)
                System.out.print("\t\tn : quit program");
            System.out.println();
        }
        System.out.println("    A B C D E F G H I\t\t\t\t\t\t    A B C D E F G H I\n");
    }

    // prints user board, fleet, commands, computer board, and computer fleet during the game. includes testing commands
    // and is used for testing purposes
    void printTestPov(BSBoard board) {
        int val = 9;
        System.out.print("    \tComputer          \t       Computer Fleet\t\t\t User\t\t\t\t  User Fleet\t\t   ");
        System.out.println("Commands:");
        for (int i = 0; i < 9; i++, val--) {
            System.out.print(val + " | ");
            for (int k = 0; k < 9; k++)
                System.out.print(board.compScrt[i][k] + " ");
            System.out.print("|\t\t");
            if (i < 6 && i > 0)
                System.out.print(board.fleetChkL[i - 1] + board.scrtHeads[i - 1] + " |      " + board.compScrtFleet[i - 1]);
            else
                System.out.print("\t\t\t");
            System.out.print("\t" + val + " | ");
            for (int k = 0; k < 9; k++)
                System.out.print(board.user[i][k] + " ");
            System.out.print("|\t\t");
            if (i < 6 && i > 0)
                System.out.print(board.fleetChkL[i - 1] + "  " + board.fleetHeads[i - 1] + " |      " + board.userFleet[i - 1]);
            if (i >= 6)
                System.out.print("\t\t\t");
            if (i == 1)
                System.out.print("\t\ta : attack");
            if (i == 2)
                System.out.print("\t\tf : forfeit");
            if (i == 4)
                System.out.print("\t   Testing Commands:");
            if (i == 6)
                System.out.print("\t\tr : testing pov");
            if (i == 7)
                System.out.print("\t\tb : barrage");
            System.out.println();
        }
        System.out.println("    A B C D E F G H I\t\t\t\t\t\t    A B C D E F G H I\n");
    }
}