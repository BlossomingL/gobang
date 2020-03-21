package View;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Blossoming on 2016/12/11.
 */
public class ChooseLevel extends JFrame implements MouseListener{
    public static final int PRIMARY_LEVEL=1;
    public static final int MEDIUM_LEVEL=2;
    public static final int SENIOR_LEVEL=3;
    public ChooseLevel()
    {
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        setUndecorated(true);
        setVisible(true);
        setLayout(new FlowLayout());
        setBounds(580, 185, 290, 390);
        setResizable(false);
        paintBg();
        addMouseListener(this);
        AWTUtilities.setWindowShape(this,
                new RoundRectangle2D.Double(0D, 0D, this.getWidth(),
                        this.getHeight(), 24.0D, 24.0D));
    }
    public void paintBg() {
        ImageIcon image = new ImageIcon("level.jpg");
        JLabel la3 = new JLabel(image);
        la3.setBounds(0, 0, this.getWidth(), this.getHeight());//添加图片，设置图片大小为窗口的大小。
        this.getLayeredPane().add(la3, new Integer(Integer.MIN_VALUE)); //将JLabel加入到面板容器的最高层
        JPanel jp = (JPanel) this.getContentPane();
        jp.setOpaque(false); //设置面板容器为透明
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        System.out.println(x+" "+y);
        if(x>=65&&x<=222&&y>=92&&y<=120)
        {
            //初级场
            dispose();
            new PCMainBoard(PRIMARY_LEVEL);
        }
        else if(x>=65&&x<=222&&y>=170&&y<=206)
        {
            //中级场
            dispose();
            new PCMainBoard(MEDIUM_LEVEL);
        }
        else if(x>=65&&x<=222&&y>=257&&y<=291)
        {
            //高级场
            dispose();
            new PCMainBoard(SENIOR_LEVEL);
        }
    }

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
