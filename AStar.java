// unfinished version of AStar used to add style. Finished work can be found in AStar repository

class AStar {
    String[][] stars;

    AStar(String str) {
        stars = new String[str.length()][5];
        for (int i = 0; i < str.length(); i++) {
            stars[i] = convert(str.charAt(i));
        }
    }

    void printLong(int mult) {
        for (int i = 0; i < 7; i++) {
            for (int l = mult; l > 0; l--) {
                for (int k = 0; k < stars.length; k++) {
                    System.out.print(stars[k][i] + "\t");
                }
                System.out.println();
            }
        }
    }
    void printWide(int mult)
    {
        for (int i = 0; i < 7; i++) {
                for (int k = 0; k < stars.length; k++) {
                    for (int c = 0; c < 5; c++)
                    {
                        for (int w = mult; w > 0; w--)
                            System.out.print(stars[k][i].charAt(c));
                    }
                    System.out.print("\t");
                }
                System.out.println();
        }
    }
    void printDiag(int mult)
    {
        for (int i = 0; i < 7; i++) {
            for (int l = mult; l > 0; l--) {
                for (int k = 0; k < stars.length; k++) {
                    for (int c = 0; c < 5; c++)
                    {
                        for (int w = mult; w > 0; w--)
                            System.out.print(stars[k][i].charAt(c));
                    }
                    System.out.print("\t");
                }
                System.out.println();
            }
        }
    }

    void print() {
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < stars.length; k++) {
                System.out.print(stars[k][i] + "\t");
            }
            System.out.println();
        }
    }

    String[] convert(char c) {
        String[] star = new String[7];
        switch (Character.toUpperCase(c)) {
            case 'A':
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "*****";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = "*   *";
                break;
            case 'B':
                star[0] = "**** ";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "**** ";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = "**** ";
                break;
            case 'C':
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = "*    ";
                star[3] = "*    ";
                star[4] = "*    ";
                star[5] = "*   *";
                star[6] = " *** ";
                break;
            case 'D':
                star[0] = "**** ";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "*   *";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = "**** ";
                break;
            case 'E':
                star[0] = " ****";
                star[1] = "*    ";
                star[2] = "*    ";
                star[3] = "*****";
                star[4] = "*    ";
                star[5] = "*    ";
                star[6] = " ****";
                break;
            case 'F':
                star[0] = " ****";
                star[1] = "*    ";
                star[2] = "*    ";
                star[3] = "*****";
                star[4] = "*    ";
                star[5] = "*    ";
                star[6] = "*    ";
                break;
            case 'G':
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = "*    ";
                star[3] = "*    ";
                star[4] = "* ***";
                star[5] = "*   *";
                star[6] = " *** ";
                break;
            case 'H':
                star[0] = "*   *";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "*****";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = "*   *";
                break;
            case 'I':
                star[0] = "*****";
                star[1] = "  *  ";
                star[2] = "  *  ";
                star[3] = "  *  ";
                star[4] = "  *  ";
                star[5] = "  *  ";
                star[6] = "*****";
                break;
            case 'J':
                star[0] = "*****";
                star[1] = "    *";
                star[2] = "    *";
                star[3] = "    *";
                star[4] = "    *";
                star[5] = "*   *";
                star[6] = " *** ";
                break;
            case 'K':
                star[0] = "*   *";
                star[1] = "*  * ";
                star[2] = "*  * ";
                star[3] = "***  ";
                star[4] = "*  * ";
                star[5] = "*  * ";
                star[6] = "*   *";
                break;
            case 'L':
                star[0] = "*    ";
                star[1] = "*    ";
                star[2] = "*    ";
                star[3] = "*    ";
                star[4] = "*    ";
                star[5] = "*    ";
                star[6] = "*****";
                break;
            case 'M':
                star[0] = "*   *";
                star[1] = "** **";
                star[2] = "* * *";
                star[3] = "*   *";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = "*   *";
                break;
            case 'N':
                star[0] = "*   *";
                star[1] = "**  *";
                star[2] = "* * *";
                star[3] = "*  **";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = "*   *";
                break;
            case 'O':
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "*   *";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = " *** ";
                break;
            case 'P':
                star[0] = "**** ";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "**** ";
                star[4] = "*    ";
                star[5] = "*    ";
                star[6] = "*    ";
                break;
            case 'Q':
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "*   *";
                star[4] = "*   *";
                star[5] = "*  * ";
                star[6] = " ** *";
                break;
            case 'R':
                star[0] = "**** ";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "***  ";
                star[4] = "*  * ";
                star[5] = "*   *";
                star[6] = "*   *";
                break;
            case 'S':
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = " *   ";
                star[3] = "  *  ";
                star[4] = "   * ";
                star[5] = "*   *";
                star[6] = " *** ";
                break;
            case 'T':
                star[0] = "*****";
                star[1] = "  *  ";
                star[2] = "  *  ";
                star[3] = "  *  ";
                star[4] = "  *  ";
                star[5] = "  *  ";
                star[6] = "  *  ";
                break;
            case 'U':
                star[0] = "*   *";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "*   *";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = " *** ";
                break;
            case 'V':
                star[0] = "*   *";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "*   *";
                star[4] = "*   *";
                star[5] = " * * ";
                star[6] = "  *  ";
                break;
            case 'W':
                star[0] = "*   *";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "*   *";
                star[4] = "* * *";
                star[5] = "** **";
                star[6] = "*   *";
                break;
            case 'X':
                star[0] = "*   *";
                star[1] = "*   *";
                star[2] = " * * ";
                star[3] = "  *  ";
                star[4] = " * * ";
                star[5] = "*   *";
                star[6] = "*   *";
                break;
            case 'Y':
                star[0] = "*   *";
                star[1] = "*   *";
                star[2] = " * * ";
                star[3] = "  *  ";
                star[4] = "  *  ";
                star[5] = "  *  ";
                star[6] = "  *  ";
                break;
            case 'Z':
                star[0] = "*****";
                star[1] = "    *";
                star[2] = "   * ";
                star[3] = "  *  ";
                star[4] = " *   ";
                star[5] = "*    ";
                star[6] = "*****";
                break;
            case ' ':
                star[0] = "     ";
                star[1] = "     ";
                star[2] = "     ";
                star[3] = "     ";
                star[4] = "     ";
                star[5] = "     ";
                star[6] = "     ";
                break;
            case '!':
                star[0] = "  *  ";
                star[1] = "  *  ";
                star[2] = "  *  ";
                star[3] = "  *  ";
                star[4] = "  *  ";
                star[5] = "     ";
                star[6] = "  *  ";
                break;
            case '?':
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = "    *";
                star[3] = "   * ";
                star[4] = "  *  ";
                star[5] = "     ";
                star[6] = "  *  ";
                break;
            case '1':
                star[0] = "  *  ";
                star[1] = "* *  ";
                star[2] = "  *  ";
                star[3] = "  *  ";
                star[4] = "  *  ";
                star[5] = "  *  ";
                star[6] = "*****";
                break;
            case '2':
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = "    *";
                star[3] = "   * ";
                star[4] = "  *  ";
                star[5] = " *   ";
                star[6] = "*****";
                break;
            case '3':
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = "    *";
                star[3] = " *** ";
                star[4] = "    *";
                star[5] = "*   *";
                star[6] = " *** ";
                break;
            case '4':
                star[0] = "    *";
                star[1] = "   **";
                star[2] = "  * *";
                star[3] = " *  *";
                star[4] = "*****";
                star[5] = "    *";
                star[6] = "    *";
                break;
            case '5':
                star[0] = "*****";
                star[1] = "*    ";
                star[2] = "*    ";
                star[3] = "*****";
                star[4] = "    *";
                star[5] = "    *";
                star[6] = "**** ";
                break;
            case '6':
                star[0] = "  ***";
                star[1] = " *   ";
                star[2] = "*    ";
                star[3] = "*****";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = " *** ";
                break;
            case '7':
                star[0] = "*****";
                star[1] = "    *";
                star[2] = "    *";
                star[3] = "    *";
                star[4] = "    *";
                star[5] = "    *";
                star[6] = "    *";
                break;
            case '8': 
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = " *** ";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = " *** ";
                break;
            case '9':
                star[0] = " *** ";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = " ****";
                star[4] = "    *";
                star[5] = "    *";
                star[6] = "    *";
                break;
            case '0':
                star[0] = "*****";
                star[1] = "*   *";
                star[2] = "*   *";
                star[3] = "*   *";
                star[4] = "*   *";
                star[5] = "*   *";
                star[6] = "*****";
                break;
            default:
                break;
        }
        return star;
    }
}