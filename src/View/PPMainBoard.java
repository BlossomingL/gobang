package View;

import Model.Chess;
import Model.timerThread;
import Net.NetTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by Blossoming on 2016/11/30.
 */
public class PPMainBoard extends MainBoard {
    private PPChessBoard cb;
    private JButton btn_startGame;
    private JButton btn_back;
    private JTextArea ta_chess_info;
    private JTextField tf_ip;
    private String ip;
    private DatagramSocket socket;
    private String gameState;
    private String enemyGameState;
    //接收到发送端的信息
    private ArrayList<String> enemyMsg;
    private WinDialog dialog;
    public JButton getBtn_startGame(){return btn_startGame;}
    public String getIp() {return ip;}
    public JTextField getTf() {return tf_ip;}
    public DatagramSocket getSocket() {return socket;}
    public PPMainBoard()
    {
        label_timeCount.setBounds(602, 330, 230, 40);
        init();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void init()
    {
        gameState="NOT_START";
        enemyGameState="NOT_START";
        enemyMsg=new ArrayList<String>();
        tf_ip=new JTextField("请输入IP地址");
        tf_ip.setBounds(582, 379, 200, 30);
        tf_ip.addMouseListener(this);
        btn_startGame=new JButton("准备游戏");
        btn_startGame.setFocusPainted(false);
        btn_startGame.setBackground(Color.CYAN);
        btn_startGame.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
        btn_startGame.addActionListener(this);
        btn_startGame.setBounds(582, 429, 200, 50);
        btn_back=new JButton("退        出");
        btn_back.setFocusPainted(false);
        btn_back.setBackground(Color.CYAN);
        btn_back.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
        btn_back.addActionListener(this);
        btn_back.setBounds(582, 499, 200, 50);
        ta_chess_info=new JTextArea();
        //ta_chess_info.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
        ta_chess_info.setEnabled(false);
        ta_chess_info.setBackground(Color.BLUE);
        ta_chess_info.setForeground(Color.black);
        //ta_chess_info.setBounds(550, 55, 230, 300);
        JScrollPane p = new JScrollPane(ta_chess_info);
        p.setBounds(582, 20, 200, 300);
        dialog=new WinDialog(this,"恭喜",true);
        cb=new PPChessBoard(this,dialog);
        cb.setClickable(PPMainBoard.CAN_NOT_CLICK_INFO);
        cb.setBounds(0, 20, 570, 585);
        cb.setVisible(true);
        cb.setInfoBoard(ta_chess_info);
        add(tf_ip);
        add(cb);
        add(btn_startGame);
        add(p);
        add(btn_back);
//      add(label_timeCount);
        ReicThread();
        repaint();
    }
    /**
     * 接收信息放在线程中
     */
    public void ReicThread()
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    byte buf[]=new byte[1024];
                    socket=new DatagramSocket(2222);
                    DatagramPacket dp=new DatagramPacket(buf, buf.length);
                    while(true)
                    {
                        socket.receive(dp);
                        //0.接收到的发送端的主机名
                        InetAddress ia=dp.getAddress();
                        //enemyMsg.add(new String(ia.getHostName()));

                        //1.接收到的内容
                        String data=new String(dp.getData(),0,dp.getLength());
                        if(data.isEmpty())
                        {
                            cb.setClickable(CAN_NOT_CLICK_INFO);
                        }
                        else {
                            //enemyMsg.add(data);
                            String[] msg = data.split(",");
                            System.out.println(msg[0]);
                            //接收到对面准备信息并且自己点击了准备
                            if(msg[0].equals("ready"))
                            {
                                enemyGameState="ready";
                                System.out.println("对方已准备");
                                if(gameState.equals("ready"))
                                {
                                    gameState="FIGHTING";
                                    cb.setClickable(CAN_CLICK_INFO);
                                    btn_startGame.setText("正在游戏");
                                    timer=new timerThread(label_timeCount);
                                    timer.start();
                                }
                            }
                            else if(msg[0].equals("POS")){
                                System.out.println("发送坐标");
                                //接受坐标以及角色
                                cb.setCoord(Integer.parseInt(msg[1]), Integer.parseInt(msg[2]), Integer.parseInt(msg[3]));
                            }

                        }
                        //2.接受到的IP地址
                        //enemyMsg.add(new String(ia.getAddress()));
                        //3.接收到的发送端的端口
                        //enemyMsg.add(dp.getPort()+"");
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_startGame)
        {
            if(!tf_ip.getText().isEmpty()&&
                    !tf_ip.getText().equals("不能为空")&&
                        !tf_ip.getText().equals("请输入IP地址")&&
                            !tf_ip.getText().equals("不能连接到此IP")) {
                ip=tf_ip.getText();
                btn_startGame.setEnabled(false);
                btn_startGame.setText("等待对方准备");
                tf_ip.setEditable(false);
                NetTool.sendUdpBroadCast(ip,"ready");
                gameState="ready";
                if(enemyGameState=="ready")
                {
                    gameState="FIGHTING";
                    cb.setClickable(CAN_CLICK_INFO);
                    btn_startGame.setText("正在游戏");
                    timer=new timerThread(label_timeCount);
                    timer.start();
                }
            }
            else
            {
                tf_ip.setText("不能为空");
            }
        }
        else if(e.getSource()==btn_back)
        {
            System.exit(0);
        }
    }
}
