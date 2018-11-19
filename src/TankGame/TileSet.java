package TankGame;

import java.awt.*;

public class TileSet {

    private int x, y, row, col, val;
    private int TileSize = 50;

    public TileSet(int x, int y, int val){
        this.col = x;
        this.row = y;
        this.x = x * TileSize;
        this.y = y * TileSize;
        this.val = val;
    }

    public int getCol(){ return this.col;}

    public int getRow(){return this.row;}

    public int getVal(){return this.val;}

    public Rectangle getRectangle(){
        return new Rectangle(this.x, this.y, TileSize, TileSize);
    }
}
