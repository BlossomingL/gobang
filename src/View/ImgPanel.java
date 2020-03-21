package View;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImgPanel extends JPanel {
    private static BufferedImage image;
    private static BufferedImage default_image;

    public ImgPanel() {
        try {
            URL url = ImgPanel.class.getResource("images/main-theme.png");
            default_image = ImageIO.read(url);
            image=default_image;
        } catch (IOException ex) {
            // do nothing
            System.out.println(ex);

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image tmp = image.getScaledInstance(435, 435, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(435, 435, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, new Color(255,255,255),null);
        g2d.dispose();
        g.drawImage(dimg, 0, 0, null);
    }

    // paint img by Graphics
    public void setImage(BufferedImage Image) {
        if (Image != null){
            image = Image;
        } else {
            image = default_image;
        }
        this.paintComponent(getGraphics());
        this.paintAll(getGraphics());

    }

}
