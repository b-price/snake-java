import java.awt.*;

public class Cell {
    private Point location;
    private boolean hasFruit;
    private boolean hasSnake;
    private boolean hasHead;

    public Cell(){
        location = new Point(0, 0);
        hasFruit = false;
        hasSnake = false;
        hasHead = false;
    }

    public Cell(int x, int y){
        location = new Point(x, y);
        hasFruit = false;
        hasSnake = false;
        hasHead = false;
    }

    public Cell(Point coordinates){
        location = new Point(coordinates);
        hasFruit = false;
        hasSnake = false;
        hasHead = false;
    }

    public int getX() {
        return location.x;
    }

    public int getY() {
        return location.y;
    }

    public Point getLocation(){
        return location;
    }

    public boolean fruitHere() {
        return hasFruit;
    }

    public boolean snakeHere() {
        return hasSnake;
    }

    public void setFruit(){
        hasFruit = true;
    }

    public void removeFruit(){
        hasFruit = false;
    }

    public void setSnake() {
        hasSnake = true;
    }

    public void removeSnake() {
        hasSnake = false;
    }

    public void setHead(){
        hasHead = true;
    }

    public void removeHead(){
        hasHead = false;
    }

    public boolean headHere(){
        return hasHead;
    }

    public String toString(){
        return "x: " + location.x + " y: " + location.y + " fruit: " + hasFruit + " snake: " + hasSnake + " head: " + hasHead;
    }
}
