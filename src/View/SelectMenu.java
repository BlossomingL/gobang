package View;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Blossoming on 2016/11/30.
 */
public class SelectMenu extends JFrame implements MouseListener,ActionListener,MouseMotionListener {
    private Btns close_btn;
    private Btns small_btn;
    private JButton btn;
    private JLabel headLabel;
    private Point pos;
    private Point newPos;
    private static String[] closeIcons = {
            "close_icon.png","close_pressed.png","close_pressed.png"
    };
    private static String[] smallIcons= {
            "small_icon.png","small_pressed.png","small_pressed.png"
    };
    public SelectMenu() {
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        init();
        setUndecorated(true);
        setVisible(true);
        setLayout(null);
        setBounds(580,185,290,420);
        setResizable(false);
        paintBg();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setDefaultLookAndFeelDecorated(true);
        addMouseListener(this);
        AWTUtilities.setWindowShape(this,
                new RoundRectangle2D.Double(0D, 0D, this.getWidth(),
                        this.getHeight(), 24.0D, 24.0D));
    }

    private void init() {

        close_btn=new Btns(closeIcons,null);
        close_btn.setBounds(260,0,30,30);
        close_btn.addActionListener(this);
        close_btn.setBorderPainted(false);
        btn=new JButton("哈哈哈");
        headLabel=new JLabel();
        headLabel.setBounds(30,0,230,30);
        headLabel.setOpaque(true);
        headLabel.setBackground(new Color(72,72,72));
        headLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                pos=new Point(e.getX(),e.getY());
                setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
            public void mouseReleased(MouseEvent e) {
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        headLabel.addMouseMotionListener(this);
        small_btn=new Btns(smallIcons,null);
        small_btn.setBounds(0,0,30,30);
        small_btn.addActionListener(this);
        small_btn.setBorderPainted(false);
        add(headLabel);
        add(close_btn);
        add(small_btn);
        add(btn);
    }

    public void paintBg() {
        ImageIcon image = new ImageIcon("5.jpg");
        JLabel la3 = new JLabel(image);
        la3.setBounds(0, 0, this.getWidth(), this.getHeight());//添加图片，设置图片大小为窗口的大小。
        this.getLayeredPane().add(la3, new Integer(Integer.MIN_VALUE)); //将JLabel加入到面板容器的最高层
        JPanel jp = (JPanel) this.getContentPane();
        jp.setOpaque(false); //设置面板容器为透明
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        if(x>=65&&x<=231&&y>=120&&y<=150)
        {
            //人机
            dispose();
            new ChooseLevel();
            //new PCMainBoard();
        }
        else if(x>=65&&x<=231&&y>=210&&y<=250)
        {
            //人人
            dispose();
            new PPMainBoard();
        }
        else if(x>=65&&x<=231&&y>=325&&y<=355)
        {
            //退出
            System.exit(0);
        }
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
    public static void main(String args[])
    {
        new SelectMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==small_btn)
        {
            setExtendedState(ICONIFIED);
        }
        else if(e.getSource()==close_btn)
        {
            dispose();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        newPos=new Point(getLocation().x + e.getX() - pos.x,getLocation().y + e.getY() - pos.y);
        setLocation(newPos.x,newPos.y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
