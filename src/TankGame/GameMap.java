package TankGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.util.ArrayList;

public class GameMap {

    private int[][] map;
    private final int mapWidth = 30;
    private final int mapHeight = 20;
    private ArrayList<TileSet> tiles;
    private BufferedImage[] brWall;
    private BufferedImage star, flag1, flag2, uWall;

    public GameMap(BufferedReader br, BufferedImage[] brWall, BufferedImage star, BufferedImage flag1,
                   BufferedImage flag2, BufferedImage uWall){
        this.tiles = new ArrayList<>();
        this.map = new int[mapHeight][mapWidth];
        this.brWall = brWall;
        this.star = star;
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.uWall = uWall;

        try{
            for(int row = 0; row < mapHeight; row++){
                String[] tokens = br.readLine().split("\t");
                for(int col = 0; col < mapWidth; col++){
                    int num = Integer.parseInt(tokens[col]);
                    map[row][col] = num;

                    if(num!=0){
                        TileSet tile = new TileSet(col, row, num);
                        this.tiles.add(tile);
                    }
                }
            }
        } catch(Exception e){
            e.getMessage();
        }
    }


    public void render(Graphics g){
        for(int row = 0; row < this.mapHeight; row++){
            for(int col = 0; col < this.mapWidth; col++){
                int tile = this.map[row][col];

                switch(tile){
                    case 1:
                        g.drawImage(brWall[0], col * 50, row * 50, null);
                        break;
                    case 2:
                        g.drawImage(uWall, col * 50, row * 50, null);
                        break;
                    case 3:
                        g.drawImage(brWall[1], col * 50, row * 50, null);
                        break;
                    case 4:
                        g.drawImage(flag1, col * 50, row * 50, null);
                        break;
                    case 5:
                        g.drawImage(flag2, col * 50, row * 50, null);
                        break;
                    case 7:
                        g.drawImage(star, col*50, row*50, null);
                        break;
                }
            }
        }
    }


    public ArrayList<TileSet> getTileArray(){
        return this.tiles;
    }

    public void setTileNum(int i, int j, int num){
        this.map[i][j] = num;
    }

    public int getTileNum(int i, int j){
        return this.map[i][j];
    }

}
