package View;

import Control.JudgeWinner;
import Model.Chess;
import Model.Computer;
import Model.Coord;
import Net.NetTool;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Blossoming on 2016/12/6.
 */
public class PCChessBoard extends ChessBoard{
    public static final int BACK_MESSAGE=1;
    private int role;
    private int comPos[]=new int[2];
    private int result=1;
    private PCMainBoard mb;
    private WinDialog dialog;
    private Computer com;
    private int msg_back=0;
    private int step[][]=new int[30*30][2];
    private int stepCount=0;
    private Coord coord=new Coord();
    public void setMsg_back(){this.msg_back=msg_back;}
    public int getMsg_back(){return msg_back;}
    public void setResult(int result){this.result=result;}
    public PCChessBoard(PCMainBoard mb)
    {
        this.mb=mb;
        role=Chess.WHITE;
        com=new Computer();
        dialog=new WinDialog(mb,"恭喜",true);
        setBorder(new LineBorder(new Color(0, 0, 0)));
    }

    /**
     * 保存黑白棋子的坐标于二维数组中
     * @param posX
     * @param posY
     */
    private void saveStep(int posX,int posY)
    {
        stepCount++;
        step[stepCount][0]=posX;
        step[stepCount][1]=posY;
    }


    public void backStep()
    {
        if(stepCount>=2)
        {
            chess[step[stepCount][0]][step[stepCount][1]]=0;
            chess[step[stepCount-1][0]][step[stepCount-1][1]]=0;
            stepCount=stepCount-2;
        }
    }
    public void WinEvent(int winner)
    {
        //白棋赢
        if(winner == Chess.WHITE) {
            result=GAME_OVER;
            try {
                mb.getTimer().interrupt();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            mb.getBtn_startGame().setText("开始游戏");
            mb.getBtn_startGame().setEnabled(true);
            dialog.setWinnerInfo("白棋获胜!");
            setClickable(MainBoard.CAN_NOT_CLICK_INFO);
            dialog.setVisible(true);
            if(dialog.getMsg()==WinDialog.BACK)
            {
                //System.exit(0);
                mb.dispose();
                new SelectMenu();
            }
            else
            {
                initArray();
                //setClickable(MainBoard.CAN_CLICK_INFO);
            }
        }
        //黑棋赢
        else if(winner==Chess.BLACK){
            result=GAME_OVER;
            try {
                mb.getTimer().interrupt();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            mb.getBtn_startGame().setText("开始游戏");
            mb.getBtn_startGame().setEnabled(true);
            setClickable(MainBoard.CAN_NOT_CLICK_INFO);
            dialog.setWinnerInfo("黑棋获胜!");
            dialog.setVisible(true);
            if(dialog.getMsg()==WinDialog.BACK)
            {
                //System.exit(0);
                mb.dispose();
                new SelectMenu();
            }
            else
            {
                initArray();
                //setClickable(MainBoard.CAN_CLICK_INFO);
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int winner;
        if(clickable==MainBoard.CAN_CLICK_INFO) {
            chessX = e.getX();
            chessY = e.getY();
            if (chessX < 524 && chessX > 50 && chessY < 523 && chessY > 50) {
                float x = (chessX - 49) / 25;
                float y = (chessY - 50) / 25;
                int x1 = (int) x;
                int y1 = (int) y;
                //如果这个地方没有棋子
                if (chess[x1][y1] == Chess.NO_CHESS) {
                    chess[x1][y1] = role;
                    saveStep(x1,y1);
                    setClickable(MainBoard.CAN_NOT_CLICK_INFO);
                    winner= JudgeWinner.PPJudge(x1, y1, chess, role);
                    WinEvent(winner);
                    if(result!=GAME_OVER) {
                        coord = com.computePos(Chess.BLACK, chess,mb.getLevel());
                        chess[coord.getX()][coord.getY()] = Chess.BLACK;
                        saveStep(coord.getX(),coord.getY());
                        winner = JudgeWinner.PPJudge(coord.getX(), coord.getY(), chess, Chess.BLACK);
                        WinEvent(winner);
                        if(result!=GAME_OVER) {
                            setClickable(MainBoard.CAN_CLICK_INFO);
                        }
                    }
                }
            }
        }
    }

}
