package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Blossoming on 2016/12/20.
 */
public class Btns extends JButton{
    private String[] image_url;
    private boolean hasImage;
    public Btns()
    {
        super();
        hasImage=false;
    }
    public Btns(String text)
    {
        super(text);
        hasImage=false;
    }
    public Btns(String[] image_url,String text)
    {
        super(text);
        hasImage=true;
        this.image_url=image_url;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ButtonModel model = getModel();
        Image image;
        //当按钮被按下
        if (hasImage == true) {
            if (model.isPressed()) {
                Color c = new Color(0, 0, 0);
                g.setColor(c);
                image = Toolkit.getDefaultToolkit().getImage(image_url[1]);
                g.drawImage(image, 0, 0, null);
            }
            else if (model.isRollover()) {
                Color c = new Color(0, 0, 0);
                g.setColor(c);
                image =Toolkit.getDefaultToolkit().getImage(image_url[2]);

                g.drawImage(image, 0, 0 , null);
            }
            else {
                Color c = new Color(120, 120, 120);
                g.setColor(c);

                image = Toolkit.getDefaultToolkit().getImage(image_url[0]);
                g.drawImage(image , 0, 0 , null);
            }

        }
    }
}
