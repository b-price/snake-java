import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {
    private final int SCORE_MULTIPLIER = 10;
    private final int SIZE_BONUS_CNST = 100;
    private final int MAX_SPEED = 10;
    private final int MIN_SPEED = 1;
    private final int MAX_DELAY_MS = 2000;
    private final int MIN_DELAY_MS = 200;
    private int time;
    private int speed;
    private boolean gameover;
    private int fruits;
    private Board board;
    private Snake snake;
    private Scanner scan;
    private ScheduledExecutorService executor;
    private char lastMove;

    public Game(){
        time = 0;
        fruits = 0;
        board = new Board();
        scan = new Scanner(System.in);
        lastMove = 's'; // Default direction
    }

    public Game(int boardSizeX, int boardSizeY){
        time = 0;
        fruits = 0;
        board = new Board(boardSizeX, boardSizeY);
        scan = new Scanner(System.in);
        lastMove = 's'; // Default direction
    }

    public Game(int boardSizeSquare){
        time = 0;
        fruits = 0;
        board = new Board(boardSizeSquare);
        scan = new Scanner(System.in);
        lastMove = 's'; // Default direction
    }

    public void start(){
        initialize();
        System.out.println("~~~S~~N~~A~~K~~E~~~");

        // Get speed from player
        System.out.println("Set game speed (1-10, 1=slowest, 10=fastest): ");
        speed = scan.nextInt();
        if (speed < MIN_SPEED) speed = MIN_SPEED;
        if (speed > MAX_SPEED) speed = MAX_SPEED;

        // Calculate delay in milliseconds based on speed
        int delayMs = MAX_DELAY_MS - ((speed - 1) * (MAX_DELAY_MS - MIN_DELAY_MS) / (MAX_SPEED - 1));

        snake = new Snake(board.getX_length(), board.getY_length());
        gameover = false;
        board.setBoard();
        board.setFlag(snake.getSegments());
        board.setFruit();
        board.displayBoard();

        System.out.println("Game started! Use WASD keys to change direction.");
        System.out.println("Snake will move automatically every " + delayMs + "ms");

        // Create a separate thread for input handling
        Thread inputThread = new Thread(() -> {
            while (!gameover) {
                if (scan.hasNext()) {
                    lastMove = scan.next().charAt(0);
                    snake.setDirection(lastMove);
                }
            }
        });
        inputThread.start();

        // Create a scheduled executor for automatic movement
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            if (!gameover) {
                updateGame();
            } else {
                executor.shutdown();
            }
        }, 0, delayMs, TimeUnit.MILLISECONDS);

        // Wait for the game to end
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            inputThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Game interrupted");
        }
    }

    private void updateGame() {
        Point lastTail = new Point(snake.getTail());
        snake.move();
        time++;

        if(hitWall() || board.getCell(snake.getHead()).snakeHere()){
            gameover = true;
            System.out.println("YOU LOSE!");
            displayStats();
            executor.shutdown();
            return;
        }
        else if(board.getCell(snake.getHead()).fruitHere()){
            fruits++;
            snake.grow(lastTail);
            board.getCell(snake.getHead()).removeFruit();
            board.setFruit();
        }

        board.resetFlag(snake.getSegments(), lastTail);
        board.setFlag(snake.getSegments());
        displayStats();
        board.displayBoard();
    }

    // ... rest of the methods remain unchanged
    public boolean hitWall(){
        if (snake.getHead().x < 0 || snake.getHead().y < 0 || snake.getHead().x >= board.getX_length()
                || snake.getHead().y >= board.getY_length()){
            return true;
        }
        else return false;
    }

    public int getScore(){
        return (snake.getLength() * time * SCORE_MULTIPLIER * (SIZE_BONUS_CNST / (board.getX_length()) + board.getY_length()));
    }

    public void displayStats(){
        System.out.println("Time: " + time + " Score: " + getScore() + " Fruits Eaten: " + fruits);
    }

    public void initialize(){
        time = 0;
        fruits = 0;
    }
}