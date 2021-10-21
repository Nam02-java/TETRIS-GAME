package SourceCode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static SourceCode.Shape.score;

public class Board extends JPanel implements KeyListener {

    private BufferedImage blocks;
    private final int blockSize = 30;
    private final int boardWidth = 10, boardHeight = 20;
    private int[][] board = new int[boardHeight][boardWidth];

    private Shape[] shapes = new Shape[7];
    private Shape2[] shape2s = new Shape2[7];
    private Shape currentShape;
    private Shape2 currentShape2; // wanring demo
    private Timer timer;
    private final int FPS = 60;
    private final int delay = 1000 / FPS;
    private boolean gameOver = false;
    Image image = new ImageIcon("C:\\Users\\User\\Desktop\\rhjXZQ1.jpg").getImage();
    public static JLabel labelScore = new JLabel("Score " + score); // the orgin value is zero
    public static int indexDemo;

    public Board() {
        setLayout(null); // do not change !
        add(labelScore);
        labelScore.setBounds(350, 20, 300, 100);
        labelScore.setFont(new Font("Ink Free", Font.BOLD, 30));
        labelScore.setForeground(Color.WHITE);

        try {
            blocks = ImageIO.read(Board.class.getResource("icons8-square-30.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                update();
                repaint();
            }
        });


        timer.start();
        // set shape 1
        shapes[0] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][]{{1, 1, 1, 1} // I shape
        }, this, 1);
        shapes[1] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{1, 1, 0}, {0, 1, 1} // Z shape
                }, this, 2);
        shapes[2] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{0, 1, 1}, {1, 1, 0} // S shape
                }, this, 3);
        shapes[3] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{1, 1, 1}, {0, 0, 1} // J shape
                }, this, 4);
        shapes[4] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{1, 1, 1}, {1, 0, 0} // L shape
                }, this, 5);
        shapes[5] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{1, 1, 1}, {0, 1, 0} // T shape
                }, this, 6);
        shapes[6] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{1, 1}, {1, 1} // O shape
                }, this, 7);

        // set shape 2 ( new update ) 12:03AM 21/10
        shape2s[0] = new Shape2(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][]{{1, 1, 1, 1} // I shape
        }, this, 1);
        shape2s[1] = new Shape2(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{1, 1, 0}, {0, 1, 1} // Z shape
                }, this, 2);
        shape2s[2] = new Shape2(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{0, 1, 1}, {1, 1, 0} // S shape
                }, this, 3);
        shape2s[3] = new Shape2(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{1, 1, 1}, {0, 0, 1} // J shape
                }, this, 4);
        shape2s[4] = new Shape2(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{1, 1, 1}, {1, 0, 0} // L shape
                }, this, 5);
        shape2s[5] = new Shape2(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{1, 1, 1}, {0, 1, 0} // T shape
                }, this, 6);
        shape2s[6] = new Shape2(blocks.getSubimage(0, 0, blockSize, blockSize),
                new int[][]{{1, 1}, {1, 1} // O shape
                }, this, 7);

        setNextShape(); // this line is very important
        setNextShapeDemo(); // wanring demo

    }

    public void update() {
        currentShape.update();
        if (gameOver) {
            timer.stop();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // set backGround
        currentShape.render(g);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 0)
                    g.drawImage(blocks.getSubimage(0, 0, blockSize, blockSize), col * blockSize, row * blockSize, null);
            }
        }

        //setNextShapeDemo();
        currentShape2.renderDemo2(g); // warning line

        for (int i = 0; i < boardHeight; i++) {
            g.drawLine(0, i * blockSize, boardWidth * blockSize, i * blockSize);
        }
        for (int j = 0; j < boardWidth; j++) {
            g.drawLine(j * blockSize, 0, j * blockSize, boardHeight * blockSize);
        }
        g.drawLine(300, 0, 300, 650); // important code line (updated at 20/10)
    }

    public void setNextShape() {
        int index = (int) (Math.random() * shapes.length);

        Shape newShape = new Shape(shapes[index].getBlock(), shapes[index].getCoords(), this, shapes[index].getColor());
        currentShape = newShape;

        for (int row = 0; row < currentShape.getCoords().length; row++) {
            for (int col = 0; col < currentShape.getCoords()[row].length; col++) {
                if (currentShape.getCoords()[row][col] != 0) {
                    if (board[row][col + 3] != 0)
                        gameOver = true; // you can develop it if you want to have popup
                }
            }
        }
    }

    public void setNextShapeDemo() {
        indexDemo = (int) (Math.random() * shape2s.length); // this value take all shape random
        Shape2 newShape2 = new Shape2(shape2s[indexDemo].getBlock(), shape2s[indexDemo].getCoords(), this, shape2s[indexDemo].getColor());
        currentShape2 = newShape2;
    }


    public int getBlockSize() {
        return blockSize;
    }

    public int[][] getBoard() {
        return board;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentShape.setDeltaX(-1);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentShape.setDeltaX(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedDown();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            currentShape.rotate();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.normalSpeed();
        }
    }
}