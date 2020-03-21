package View;

import Model.Chess;
import Model.timerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Blossoming on 2016/12/6.
 */
public class PCMainBoard extends MainBoard{
    private PCChessBoard cb;
    private JButton btn_startGame;
    private JButton btn_back;
    private JButton btn_exit;
    private int level;
    private ChessLine cl1;
    private ChessLine cl2;
    private ChessLine cl3;
    private ChessLine cl4;
    private ChessLine cl5;
    public int getLevel(){return level;}
    public JButton getBtn_startGame(){return btn_startGame;}
    public JButton getBtn_back(){return btn_back;}
    public PCMainBoard(int level)
    {
        init();
        this.level=level;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void init()
    {
        cb=new PCChessBoard(this);
        cb.setClickable(PPMainBoard.CAN_NOT_CLICK_INFO);
        cb.setBounds(0, 20, 570, 585);
        cb.setVisible(true);
        btn_startGame=new JButton("开始游戏");
        btn_startGame.setBounds(582,205, 200, 50);
        btn_startGame.setBackground(new Color(227, 164, 113));
        btn_startGame.setFocusable(false);
        btn_startGame.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        btn_startGame.addActionListener(this);
        btn_back=new JButton("悔      棋");
        btn_back.setBounds(582, 270, 200, 50);
        btn_back.setBackground(new Color(144, 142, 153));
        btn_back.setFocusable(false);
        btn_back.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
        btn_back.addActionListener(this);
        btn_exit=new JButton("返      回");
        btn_exit.setBackground(new Color(82,109,165));
        btn_exit.setBounds(582,335,200,50);
        btn_exit.setFocusable(false);
        btn_exit.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
        btn_exit.addActionListener(this);
        cl1=new ChessLine(Chess.BLACK);
        cl1.setBounds(570, 20, 230, 22);
        cl2=new ChessLine(Chess.BLACK);
        cl2.setBounds(570,100,230,22);
        cl3=new ChessLine(Chess.WHITE);
        cl3.setBounds(570,180,230,22);
        add(cb);
        add(cl1);
        add(cl2);
        add(cl3);
        add(btn_back);
        add(btn_startGame);
        add(btn_exit);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_startGame)
        {
            btn_startGame.setEnabled(false);
            btn_startGame.setText("正在游戏");
            cb.setClickable(CAN_CLICK_INFO);
            timer=new timerThread(label_timeCount);
            timer.start();
            cb.setResult(1);
        }
        else if(e.getSource()==btn_back)
        {
            cb.backStep();
        }
        else if(e.getSource()==btn_exit)
        {
            dispose();
            new SelectMenu();
        }
    }

}
