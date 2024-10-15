package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

/**
 *
 * @author Oliwier Gebczynski
 */
public class Board {
    int width = 20;
    int height = 20;
    char[][] board = new char[width][height]; 
    
    //Getters
    public int getWidth() {
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public char[][] getBoard(){
        return board;
    }
    
    //Setters
    public void setWidth(int newWidth) {
        this.width = newWidth;
    }
    
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }
}
