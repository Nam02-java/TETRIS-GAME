package SourceCode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Shape2 {
    private BufferedImage block;
    private int[][] coords;
    private Board board;
    private int deltaX = 0;
    private int x, y;
    static int score = 0;

    private int color;

    static public long time, lastTime; // old value is only public
    private int normalSpeed = 600, speedDown = 60, currentSpeed;
    //private boolean collision = false, moveX; don't need this

    public Shape2(BufferedImage block, int[][] coords, Board board, int color) {
        this.block = block;
        this.coords = coords;
        this.board = board;
        this.color = color;
        currentSpeed = normalSpeed;
        time = 0;
        lastTime = 0;

        x = 4;
        y = 0;
    }

    public Shape2(Shape2 shape2) {
    }

    public void renderDemo2(Graphics g) {
        // still update
        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[row].length; col++) {
                if (coords[row][col] != 0) {
                    g.drawImage(block, col * board.getBlockSize() + 13 * board.getBlockSize(), row * board.getBlockSize() + 11 * board.getBlockSize(), null);
                }
            }
        }
    }

    public BufferedImage getBlock() {
        return block;
    }

    public int[][] getCoords() {
        return coords;
    }

    public int getColor() {
        return color;
    }
}
