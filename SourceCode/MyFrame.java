package SourceCode;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame{

    public static final int WIDTH = 600, HEIGHT = 638; // do not change !
    public Board board;

    public MyFrame() {
       setWindow();
    }

    private void setWindow(){
        setBounds(1350, 250, WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        board = new Board();
        add(board, BorderLayout.CENTER);
        addKeyListener(board);
        setVisible(true);
    }
}