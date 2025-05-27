import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int highScore = 0;
        int boardSize = 10;
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to SNAKE");
        System.out.println("Set board size: ");
        boardSize = in.nextInt();
        Game game = new Game(boardSize);
        char key;
        do {
            game.start();
            if (highScore < game.getScore()){
                highScore = game.getScore();
                System.out.println("NEW RECORD!");
            }
            System.out.println("Your highest score: " + highScore);
            System.out.println("New game? (y/n) ");
            key  = in.next().charAt(0);
        }
        while (key == 'Y' || key == 'y');
    }
}