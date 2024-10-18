import java.util.Scanner;

/**
 * @author Balazs Fentor
 * @version 1.0
 * 
 * Purpose: A simple tic tac toe game (AI to be implemented later)
 */

public class Game {
    final static String ANSI_HOME = "\u001b[H";
    final static String ESC = "\033[";
    final static String cls = ESC + "2J" + ANSI_HOME;
    static String[][] b = new String[3][3];
    static int[][] lu = {{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
    static Scanner in = new Scanner(System.in);
    static String p1n = "Player 1";
    static String p2n = "Player 2";
    
    public static void main(String[] args) {        
        // setup
        fill();
        print();
        
        boolean p1Win = false;
        boolean p2Win = false;
        while (!p1Win && !p2Win) {
            // player 1 move
            boolean p1next = false;
            boolean p2next = false;
            while (!p1next) {
                p1next = move(p1n, "X", true); 
            }
            p1Win = check("X");
            print();
            checkStalemate();
            if (p1Win) {
                System.out.println(p1n + " has won");
                break;
            }

            // player 2 move
            while (!p2next) {
                p2next = move(p2n, "O", true);
            }
            p2Win = check("O");
            print();
            checkStalemate();
            if (p2Win) {
                System.out.println(p2n + " has won");
                break;
            }
        }
    }
    public static boolean move(String n, String s, boolean print) {
        int ip = -1;
        try {
            if (print)
                System.out.print(n + " move: ");
            ip = Integer.parseInt(in.next());
        } catch (Exception e) {
            if (print) {
                System.out.println("Bad input");
                System.out.print(n + " move: ");
            }
            return false;
        }
        ip--;
        if (ip > -1 && ip < 9) {
            if (!b[lu[ip][0]][lu[ip][1]].equals("X") && !b[lu[ip][0]][lu[ip][1]].equals("O") && Integer.parseInt(b[lu[ip][0]][lu[ip][1]]) < 10) {
                b[lu[ip][0]][lu[ip][1]] = s;
            } else {
                if (print) {
                    System.out.println("Bad input");
                    //System.out.print(n + " move: ");
                }
                return false;
            }
        } else {
            if (print) {
                System.out.println("Bad input");
                //System.out.print(n + " move: ");
            }
            return false;
        }
        return true;
    }
    public static void checkStalemate() {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (b[i][k].equals("X") || b[i][k].equals("O"))
                    sum++;
            }
        }
        if (sum == 9) {
            System.out.println("Stalemate");
            System.exit(0);
        }
    }
    public static boolean check(String check) {
        //horizontal
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum = 0;
            for (int k = 0; k < 3; k++) {
                if (b[i][k].equals(check))
                    sum++;
            }
            if (sum == 3)
                return true;
        }
        //vertical
        sum = 0;
        for (int i = 0; i < 3; i++) {
            sum = 0;
            for (int k = 0; k < 3; k++) {
                if (b[k][i].equals(check))
                    sum++;
            }
            if (sum == 3)
                return true;
        }
        String diag = b[0][0] + b[1][1] + b[2][2];
        if (diag.equals(check + check + check))
            return true;
        diag = b[2][0] + b[1][1] + b[0][2];
        if (diag.equals(check + check + check))
            return true;
        return false;
    }
    public static void fill() {
        int num = 1;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                b[i][k] = String.valueOf(num);
                num++;
            }
        }
    }
    public static void print() {
        System.out.print(cls);
        System.out.println(" " + b[0][0] + " | " + b[0][1] + " | " + b[0][2]);
        System.out.println("---+---+---");
        System.out.println(" " + b[1][0] + " | " + b[1][1] + " | " + b[1][2]);
        System.out.println("---+---+---");
        System.out.println(" " + b[2][0] + " | " + b[2][1] + " | " + b[2][2]);
        //System.out.print("Move: ");
        //test
        /*
        System.out.println(" X | O | X ");
        System.out.println("---+---+---");
        System.out.println("   |   | X ");
        System.out.println("---+---+---");
        System.out.println("   |   |   ");
        */
    }
    public static void cmove() {
        
    }
}