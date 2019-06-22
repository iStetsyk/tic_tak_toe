import java.util.Scanner;

public class TicTakToe {

    public static final String Empty = "   ";
    public static final String Cross = " X ";
    public static final String Zero = " O ";
    public static String ActivePlayerNow;

    public static final int Line = 3, Column = 3;
    public static String[][] net = new String[Line][Column];
    public static int gameStatus;
    public static final int TheGameContinue = 0, StatusRemis = 1,
            StatusWinner_X = 3, StatusWinner_O = 4;

    public static Scanner in = new Scanner(System.in);

    
    public static void main(String[] args) {
        StartGame();
        do {
            GetInputOfThePlayer();
            AnalyzeTheGrid();
            ShowGrid();
            if (gameStatus == StatusWinner_X) {
                System.out.println("Cross is WINNER!");
            } else if (gameStatus == StatusWinner_O) {
                System.out.println("Zero is WINNER!");
            } else if (gameStatus == StatusRemis) {
                System.out.println("No one winner!");
            }
            ActivePlayerNow = (ActivePlayerNow == Cross ? Zero:Cross);
        }
        while (gameStatus == TheGameContinue);
    }

    public static void StartGame() {
        for (int line = 0; line < Line; line++) {
            for (int column = 0; column < Column; column++) {
                net[line][column] = Empty;
            }
        }
        ActivePlayerNow = Cross;
        ShowGrid();
    }

    public static void GetInputOfThePlayer() {

        boolean inputTrue = false;
        do {
            System.out.println("Player '"+ActivePlayerNow+"', please write");
            int line = in.nextInt()-1;
            int column = in.nextInt()-1;
            if (line >= 0 && line < Line && column >= 0 && column < Column && net[line][column] == Empty) {
                net[line][column] = ActivePlayerNow;
                inputTrue = true;
            } else {
                System.out.println("Your input is false. Try again :)");
            }
        }
        while (!inputTrue);
    }

    public static void AnalyzeTheGrid() {
        String winner = FindTheWinner();
        if (winner.equals(Cross)) {
            gameStatus = StatusWinner_X;
        } else if (winner.equals(Zero)) {
            gameStatus = StatusWinner_O;
        } else if (EverythingFilledUp()) {
            gameStatus = StatusRemis;
        } else {
            gameStatus = TheGameContinue;
        }
    }

    public static boolean EverythingFilledUp() {
        for (int line = 0; line < Line; line++) {
            for (int column = 0; column < Column; column++) {
                if (net[line][column] == Empty) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String FindTheWinner() {

        int coincidence; // ilosc jednakowych symbolow

        for (int line = 0; line < Line; line++) {
            coincidence = 0;
            for (int column = 0; column < Column; column++) {
                if (net[line][0] != Empty && net[line][0] == net[line][column]) {
                    coincidence++;
                }
            }
            if (coincidence == 3) {
                return net[line][0];
            }
        }

        for (int column = 0; column < Column; column++) {
            coincidence = 0;
            for (int line = 0; line < Line; line++) {
                if (net[0][column] != Empty && net[0][column] == net[line][column]) {
                    coincidence++;
                }
            }
            if (coincidence == 3) {
                return net[0][column];
            }
        }
        if (net[0][0] != Empty && net[0][0] == net[1][1] && net[0][0] == net[2][2]) {
            return net[0][0];
        }
        if (net[0][2] != Empty && net[1][1] == net[0][2] && net[2][0] == net[0][2]) {
          return net[0][2];
        }
        return Empty;
    }

    public static void ShowGrid() {
        for (int line = 0; line < Line; line++) {
            for (int column = 0; column < Column; column++) {
                System.out.print(net[line][column]);
                if(column!= Column-1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if(line!= Line-1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }
}
