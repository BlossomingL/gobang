package View;

import Model.Chess;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Blossoming on 2016/12/22.
 */
public class ChessLine extends JPanel{
    private int chessColor;
    private Image whiteChess;
    private Image blackChess;
    public ChessLine(int chessColor)
    {
        whiteChess = Toolkit.getDefaultToolkit().getImage("white.png");
        blackChess=Toolkit.getDefaultToolkit().getImage("black.png");
        this.chessColor=chessColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(chessColor== Chess.WHITE) {
            g.drawImage(whiteChess, 0, 0, null);
            g.drawImage(whiteChess, 20, 0, null);
            g.drawImage(whiteChess, 40, 0, null);
            g.drawImage(whiteChess, 60, 0, null);
            g.drawImage(whiteChess, 80, 0, null);
            g.drawImage(whiteChess, 100, 0, null);
            g.drawImage(whiteChess, 120, 0, null);
            g.drawImage(whiteChess, 140, 0, null);
            g.drawImage(whiteChess, 160, 0, null);
            g.drawImage(whiteChess, 180, 0, null);
            g.drawImage(whiteChess, 200, 0, null);
        }
        else if(chessColor==Chess.BLACK)
        {
            g.drawImage(blackChess, 0, 0, null);
            g.drawImage(blackChess, 20, 0, null);
            g.drawImage(blackChess, 40, 0, null);
            g.drawImage(blackChess, 60, 0, null);
            g.drawImage(blackChess, 80, 0, null);
            g.drawImage(blackChess, 100, 0, null);
            g.drawImage(blackChess, 120, 0, null);
            g.drawImage(blackChess, 140, 0, null);
            g.drawImage(blackChess, 160, 0, null);
            g.drawImage(blackChess, 180, 0, null);
            g.drawImage(blackChess, 200, 0, null);
        }
    }
}
