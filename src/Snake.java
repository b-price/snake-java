import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private static int DEFAULT_X = 7;
    private static int DEFAULT_Y = 7;
    private static int MOVE_DISTANCE = 1;
    private int length;
    private ArrayList<Point> segments; //tail is always element 0, head is last element

    private int direction; //0 = down, 1 = left, 2 = up, 3 = right

    public Snake(){
        direction = 0;
        length = 1;
        segments = new ArrayList<Point>();
        segments.add(new Point(DEFAULT_X - length, DEFAULT_Y)); //add tail
        segments.add(new Point(DEFAULT_X, DEFAULT_Y)); //add head
    }

    public Snake(int boardSizeX, int boardSizeY){
        direction = 0;
        length = 1;
        segments = new ArrayList<Point>();
        segments.add(new Point((boardSizeX / 2) - length, boardSizeY / 2)); //add tail
        segments.add(new Point(boardSizeX / 2, boardSizeY / 2)); //add head
    }

    public Snake(Point initSnake){
        direction = 0;
        length = 1;
        segments = new ArrayList<Point>();
        segments.add(new Point(initSnake.x - length, initSnake.y)); //add tail
        segments.add(new Point(initSnake.x, initSnake.y)); //add head
    }

    public Snake(int boardSizeX, int boardSizeY, int length){
        direction = 0;
        this.length = length;
        segments = new ArrayList<Point>();
        segments.add(new Point((boardSizeX / 2) - length, boardSizeY / 2)); //add tail
        segments.add(new Point(boardSizeX / 2, boardSizeY / 2)); //add head
    }

    public void setDirection(int numDirection){
        if(numDirection >= 0 && numDirection < 4){
            direction = numDirection;
        }
    }

    public void setDirection(char wasdDirection){
        switch (wasdDirection){
            case 'w', 'W':
                direction = 2;
                break;
            case 'a', 'A':
                direction = 1;
                break;
            case 's', 'S':
                direction = 0;
                break;
            case 'd', 'D':
                direction = 3;
                break;
            default:
                break;
        }
    }

    private void shift(int x, int y){
        Point temp = new Point(segments.get(segments.size() - 1));
        segments.add(temp); // copy last (head) to new element
        segments.get(segments.size() - 1).translate(x, y); // move head
        segments.remove(0); // move tail
    }


    public void move(){
        switch (direction){
            case 1: //left
                shift(0, -MOVE_DISTANCE);
                break;
            case 2: //up
                shift(-MOVE_DISTANCE, 0);
                break;
            case 3: //right
                shift(0, MOVE_DISTANCE);
                break;
            default: //down
                shift(MOVE_DISTANCE, 0);
                break;
        }
    }

    public void setHead(int x, int y){
        segments.get(segments.size() - 1).setLocation(x, y);
    }

    public void setHead(Point coordinates){
        segments.get(segments.size() - 1).setLocation(coordinates);
    }

    public Point getHead(){
        return segments.get(segments.size() - 1);
    }

    public Point getTail(){
        return segments.get(0);
    }

    public Point getSegment(int index){
        return segments.get(index);
    }

    public ArrayList<Point> getSegments(){
        return segments;
    }

    public int getLength(){
        return length;
    }

    public void grow(Point lastTail){
        segments.add(0, lastTail);
        length++;
    }

}
