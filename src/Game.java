import java.awt.*;
import java.util.Scanner;

public class Game {
    private final int SCORE_MULTIPLIER = 10;
    private final int SIZE_BONUS_CNST = 100;
    private int time;
    private int speed;
    private boolean gameover;
    private int fruits;
    private Board board;
    private Snake snake;
    private Scanner scan;

    public Game(){
        time = 0;
        fruits = 0;
        board = new Board();
        scan = new Scanner(System.in);
    }

    public Game(int boardSizeX, int boardSizeY){
        time = 0;
        fruits = 0;
        board = new Board(boardSizeX, boardSizeY);
        scan = new Scanner(System.in);
    }

    public Game(int boardSizeSquare){
        time = 0;
        fruits = 0;
        board = new Board(boardSizeSquare);
        scan = new Scanner(System.in);
    }

    public void start(){
        initialize();
        System.out.println("~~~S~~N~~A~~K~~E~~~");
        char move;
        snake = new Snake(board.getX_length(), board.getY_length());
        gameover = false;
        board.setBoard();
        board.setFlag(snake.getSegments());
        board.setFruit();
        board.displayBoard();
        while(!gameover){
            Point lastTail = new Point(snake.getTail());
            System.out.println("Enter move (WASD): ");
            move = scan.next().charAt(0);
            snake.setDirection(move);
            snake.move();
            time++;
            if(hitWall() || board.getCell(snake.getHead()).snakeHere()){
                gameover = true;
                System.out.println("YOU LOSE!");
                displayStats();
                break;
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
    }

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
