package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Blossoming on 2016/12/5.
 */
public class WinDialog extends JDialog implements ActionListener{
    public static final int RESTART=1;
    public static final int BACK=0;
    private JButton btn_restart,btn_back;
    private JLabel label;
    private int msg;
    public WinDialog(JFrame f,String title,boolean b)
    {
        super(f,title,b);
        setLayout(null);
        setResizable(false);
        setBounds(500,300,300,200);
        init();
    }
    public int getMsg()
    {
        return msg;
    }
    public void setWinnerInfo(String winnerInfo)
    {
        label.setText(winnerInfo);
    }
    private void init()
    {
        btn_restart=new JButton("重新开始");
        btn_restart.setFocusPainted(false);
        btn_restart.setBackground(Color.CYAN);
        btn_restart.addActionListener(this);
        btn_restart.setBounds(20, 110, 115, 40);
        btn_back=new JButton("返回主页面");
        btn_back.setBounds(155,110,115,40);
        btn_back.setBackground(Color.CYAN);
        btn_back.addActionListener(this);
        label=new JLabel();
        label.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
        label.setBounds(100,10,100,100);
        add(btn_restart);
        add(btn_back);
        add(label);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_restart)
        {
            msg=RESTART;
            setVisible(false);
        }
        else if(e.getSource()==btn_back)
        {
            msg=BACK;
            setVisible(false);
        }
    }
}
