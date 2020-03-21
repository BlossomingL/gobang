package View;



import Control.JudgeWinner;
import Model.Chess;
import Net.NetTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Blossoming on 2016/11/30.
 */
public class PPChessBoard extends ChessBoard{
    private int role;
    private JTextArea ta_pos_info;
    private PPMainBoard mb;
    private WinDialog dialog;
    /**
     * 构造函数，初始化棋盘的图片，初始化数组
     */
    public PPChessBoard(PPMainBoard mb,WinDialog dialog) {
        super();
        this.mb=mb;
        this.dialog=dialog;
        setRole(Chess.WHITE);
    }
    public void setInfoBoard(JTextArea ta)
    {
        ta_pos_info=ta;
    }
    /**
     * 设置棋子横坐标
     * @param x,y,r 横坐标,纵坐标,对方的角色黑/白
     */
    public void setCoord(int x,int y,int r)
    {
        if(r==Chess.WHITE)
        {
            role=Chess.BLACK;
        }
        else
        {
            role = Chess.WHITE;
        }
        chess[x][y]=r;
        if(r == Chess.WHITE) {
            ta_pos_info.append("白棋位置为:" + x + "," + y + "\n");
        }
        else {
            ta_pos_info.append("黑棋位置为:" + x + "," +y +"\n");
        }
        int winner=JudgeWinner.PPJudge(x,y,chess,r);
        WinEvent(winner);
        setClickable(MainBoard.CAN_CLICK_INFO);
        repaint();
    }
    public void setRole(int role)
    {
        this.role=role;
    }
    public int getRole()
    {
        return role;
    }
    /**
     * 从父类继承的方法，自动调用，绘画图形
     * @param g 该参数是绘制图形的句柄
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    public int getResult()
    {
        return result;
    }
    public int getChessX()
    {
        return chessX;
    }
    public int getChessY()
    {
        return chessY;
    }
    public void WinEvent(int winner)
    {
        //白棋赢
        if(winner == Chess.WHITE) {
            try {
                mb.getTimer().interrupt();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            mb.getBtn_startGame().setText("开始游戏");
            mb.getBtn_startGame().setEnabled(true);
            ta_pos_info.append("白棋获胜\n");
            result=Chess.WHITE;
            dialog.setWinnerInfo("白棋获胜!");
            setClickable(PPMainBoard.CAN_NOT_CLICK_INFO);
            dialog.setVisible(true);
            if(dialog.getMsg()==WinDialog.BACK)
            {
                mb.dispose();
                new SelectMenu();
            }
            else
            {
                initArray();
                mb.getLabel().setText(null);
                ta_pos_info.setText(null);
            }
        }
        //黑棋赢
        else if(winner==Chess.BLACK){
            try {
                mb.getTimer().interrupt();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            mb.getBtn_startGame().setText("开始游戏");
            mb.getBtn_startGame().setEnabled(true);
            ta_pos_info.append("黑棋获胜\n");
            result=Chess.BLACK;
            setClickable(MainBoard.CAN_NOT_CLICK_INFO);
            dialog.setWinnerInfo("黑棋获胜!");
            dialog.setVisible(true);
            if(dialog.getMsg()==WinDialog.BACK)
            {
                mb.dispose();
                new SelectMenu();
            }
            else
            {
                initArray();
                mb.getLabel().setText(null);
                ta_pos_info.setText(null);
            }
        }
    }
    /**
     * 按下鼠标时，记录鼠标的位置，并改写数组的数值，重新绘制图形
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(clickable==PPMainBoard.CAN_CLICK_INFO) {
            chessX = e.getX();
            chessY = e.getY();
            if (chessX < 524 && chessX > 50 && chessY < 523 && chessY > 50) {
                float x = (chessX - 49) / 25;
                float y = (chessY - 50) / 25;
                int x1 = (int) x;
                int y1 = (int) y;
                //如果这个地方没有棋子
                if (chess[x1][y1] == 0) {
                    chess[x1][y1] = role;
                    if(role== Chess.WHITE) {
                        ta_pos_info.append("白棋位置为:" + x1 + "," + y1 + "\n");
                    }
                    else {
                        ta_pos_info.append("黑棋位置为:" + x1 + "," +y1 +"\n");
                    }
                    NetTool.sendUdpBroadCast(mb.getIp(),"POS"+","+x1 + "," + y1 + "," + role);
                    int winner=JudgeWinner.PPJudge(x1,y1,chess,role);
                    WinEvent(winner);
                    setClickable(MainBoard.CAN_NOT_CLICK_INFO);
                }
            }
        }
    }

}
