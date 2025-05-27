import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int highScore = 0;
        int boardSize = 10;
        int speed = 5; // Default speed
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to SNAKE");
        System.out.println("Set board size: ");
        boardSize = in.nextInt();
        System.out.println("Set speed (1-10, 1 is slowest, 10 is fastest): ");
        speed = in.nextInt();
        // Ensure speed is between 1 and 10
        if (speed < 1) speed = 1;
        if (speed > 10) speed = 10;
        Game game = new Game(boardSize);
        game.setSpeed(speed);
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