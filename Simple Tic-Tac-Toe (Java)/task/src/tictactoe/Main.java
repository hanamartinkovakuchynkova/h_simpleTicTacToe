package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializace hry
        // Funkce pro vytvoření prázdné hrací plochy
        char[][] board = new char[3][3];

        // Proměnné pro sledování stavu hry
        boolean gameOver = false;
        String gameState = "";

        // Naplnění prázdné hrací plochy mezerami
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        // Vytištění aktuální hrací plochy
        printCurrentBoard(board);

        // Hlavní herní smyčka
        while (!gameOver) {

            // Krok hráče X
            while (true) {
                int souradniceX, souradniceY;
                try {
                    souradniceX = scanner.nextInt();
                    souradniceY = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                    continue;
                }

                // Kontrola platných souřadnic
                if (souradniceX < 1 || souradniceX > 3 || souradniceY < 1 || souradniceY > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                // Získání indexu v herním poli
                int indexX = souradniceX - 1;
                int indexY = souradniceY - 1;

                // Kontrola obsazenosti buňky
                if (board[indexX][indexY] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                // Buňka je volná, umístění symbolu X na danou pozici
                board[indexX][indexY] = 'X';
                break; // Pokračování ve hře
            }

            // Vytištění aktuální hrací plochy
            printCurrentBoard(board);

            // Kontrola ukončení hry
            gameState = analyzeGameState(board);
            if (!gameState.equals("Game not finished")) {
                gameOver = true;
                break;
            }

            // Krok hráče O
            while (true) {
                int souradniceX, souradniceY;
                try {
                    souradniceX = scanner.nextInt();
                    souradniceY = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                    continue;
                }

                // Kontrola platných souřadnic
                if (souradniceX < 1 || souradniceX > 3 || souradniceY < 1 || souradniceY > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                // Získání indexu v herním poli
                int indexX = souradniceX - 1;
                int indexY = souradniceY - 1;

                // Kontrola obsazenosti buňky
                if (board[indexX][indexY] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                // Buňka je volná, umístění symbolu X na danou pozici
                board[indexX][indexY] = 'O';
                break; // Pokračování ve hře
            }

            // Vytištění aktuální hrací plochy
            printCurrentBoard(board);

            // Kontrola ukončení hry
            gameState = analyzeGameState(board);
            if (!gameState.equals("Game not finished")) {
                gameOver = true;
                break;
            }

        }
        System.out.println(gameState);

    }

    private static void printCurrentBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static String analyzeGameState(char[][] board) {
        int countX = 0;
        int countO = 0;
        int countEmpty = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    countX++;
                } else if (board[i][j] == 'O') {
                    countO++;
                } else {
                    countEmpty++;
                }
            }
        }

        boolean xWins = checkWin(board, 'X');
        boolean oWins = checkWin(board, 'O');

        if (xWins && oWins || Math.abs(countX - countO) > 1) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (countEmpty == 0) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }

    public static boolean checkWin(char[][] board, char symbol) {
        // Kontrola řádků a sloupců
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                    (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }

        // Kontrola diagonál
        if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }

        return false;
    }
}

