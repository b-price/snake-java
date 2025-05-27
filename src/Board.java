import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
//0 right 1 up 2 left 3 down
public class Board {
    private final int DEFAULT_X = 10;
    private final int DEFAULT_Y = 10;
    private final int MAX_SIDE = 100;
    private int x_length;
    private int y_length;
    private Cell[][] cells;
    private Random rand;

    public Board(){
        x_length = DEFAULT_X;
        y_length = DEFAULT_Y;
        cells = new Cell[DEFAULT_X][DEFAULT_Y];
        rand = new Random();
    }

    public Board(int x, int y){
        if (x > 1 && x < MAX_SIDE){
            x_length = x;
        }
        else x_length = DEFAULT_X;
        if (y > 1 && y < MAX_SIDE){
            y_length = y;
        }
        else y_length = DEFAULT_Y;
        cells = new Cell[x][y];
        rand = new Random();
    }

    public Board(int squareSide){
        if (squareSide > 1 && squareSide < MAX_SIDE){
            x_length = squareSide;
            y_length = squareSide;
        }
        else {
            x_length = DEFAULT_X;
            y_length = DEFAULT_Y;
        }
        cells = new Cell[x_length][y_length];
        rand = new Random();
    }

    public void setBoard(){
        for (int i = 0; i < x_length; i++) {
            for (int j = 0; j < y_length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void setFruit(){
        boolean set = false;
        int fruitX = 0;
        int fruitY = 0;
        while(!set){
            fruitX = rand.nextInt(x_length);
            fruitY = rand.nextInt(y_length);
            if (!cells[fruitX][fruitY].fruitHere() && !cells[fruitX][fruitY].snakeHere()){
                set = true;
            }
        }
        cells[fruitX][fruitY].setFruit();
    }

    public void setFlag(Point coordinates, boolean head){
        if(head){
            cells[coordinates.x][coordinates.y].setHead();
        }
        cells[coordinates.x][coordinates.y].setSnake();
    }

    public void setFlag(ArrayList<Point> snake){
        for (int i = 0; i < snake.size(); i++){
            cells[snake.get(i).x][snake.get(i).y].setSnake();
        }
        cells[snake.get(snake.size()-1).x][snake.get(snake.size()-1).y].setHead();
    }

    public void resetFlag(ArrayList<Point> snake, Point last){
        cells[snake.get(snake.size()-2).x][snake.get(snake.size()-2).y].removeHead();
        cells[last.x][last.y].removeSnake();
    }

    public void resetFlag(Point coordinates, boolean head){
        if(head){
            cells[coordinates.x][coordinates.y].removeHead();
        }
        cells[coordinates.x][coordinates.y].removeSnake();
    }

    public void displayBoard(){
        for(int i = 0; i <= x_length + 1; i++){
            System.out.print(" - ");
        }
        System.out.println();
        for(int i = 0; i < x_length; i++){
            for(int j = -1; j <= y_length; j++){
                if(j == -1 || j == y_length){
                    System.out.print(" | ");
                }
                else if (cells[i][j].headHere()){
                    System.out.print(" @ ");
                }
                else if(cells[i][j].snakeHere()){
                    System.out.print(" * ");
                }
                else if(cells[i][j].fruitHere()){
                    System.out.print(" o ");
                }
                else System.out.print("   ");
            }
            System.out.println();
        }
        for(int i = 0; i <= x_length + 1; i++){
            System.out.print(" - ");
        }
        System.out.println();
    }

    public Cell getCell(Point coordinates){
        return cells[coordinates.x][coordinates.y];
    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    public int getX_length(){
        return x_length;
    }

    public int getY_length(){
        return y_length;
    }

}
