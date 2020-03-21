package View;

import Model.Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Blossoming on 2016/12/6.
 */
public class ChessBoard extends JPanel implements MouseListener{
    public static final int GAME_OVER=0;
    public static final int COLS=19;
    public static final int RAWS=19;
    public int result=1;
    public Image whiteChess;
    //黑白棋子
    public Image chessBoardImage;
    public Image laceImage;
    public Image blackChess;
    //棋子的横坐标
    public int chessX;
    //棋子的纵坐标
    public int chessY;
    //棋盘上隐形的坐标，每一个小区域代表一个数组元素
    public int chess[][]=new int[COLS][RAWS];
    public int clickable;

    /**
     * 构造函数，初始化棋盘的图片，初始化数组
     * @param
     */

    ChessBoard() {
        chessBoardImage = Toolkit.getDefaultToolkit().getImage("gobang1.jpg");
        whiteChess = Toolkit.getDefaultToolkit().getImage("white.png");
        blackChess=Toolkit.getDefaultToolkit().getImage("black.png");
        laceImage=Toolkit.getDefaultToolkit().getImage("lace.jpg");
        initArray();
        addMouseListener(this);
    }
    public void setClickable(int clickable)
    {
        this.clickable=clickable;
    }
    /**
     * 初始化数组为全零
     */
    public void initArray()
    {
        for(int i=0;i<19;i++)
        {
            for(int j=0;j<19;j++)
            {
                chess[i][j]= Chess.NO_CHESS;
            }
        }
    }
    /**
     * 从父类继承的方法，自动调用，绘画图形
     * @param g 该参数是绘制图形的句柄
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(laceImage,0,0,null);
        g.drawImage(chessBoardImage, 35, 35,null);
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (chess[i][j] == Chess.BLACK) {
                    g.drawImage(blackChess, 60 + i * 25 - 11, 62 + j * 25 - 12, null);
                } else if (chess[i][j] == Chess.WHITE) {
                    g.drawImage(whiteChess, 60 + i * 25 - 11, 62 + j * 25 - 12, null);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    /**
     * 按下鼠标时，记录鼠标的位置，并改写数组的数值，重新绘制图形
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
     }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
