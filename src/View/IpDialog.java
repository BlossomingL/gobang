package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Blossoming on 2016/12/5.
 */
public class IpDialog extends JDialog implements ActionListener{
    private JTextField tf_ip;
    private JButton btn_ok;
    public IpDialog(JFrame f,String title,boolean b)
    {
        super(f,title,b);
        setLayout(null);
        setResizable(false);
        setBounds(500,300,300,200);
        init();
    }
    private void init()
    {
        tf_ip=new JTextField("请输入对方IP地址");
        btn_ok=new JButton("确认");
        btn_ok.addActionListener(this);
        add(tf_ip);
        add(btn_ok);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
