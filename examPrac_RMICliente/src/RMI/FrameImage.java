/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 *
 * @author Omen
 */
public class FrameImage extends JInternalFrame implements Runnable {

    private Image image;
    private int windowsNumber;

    public FrameImage(Image image, int windowsNumber) {
        this.image = image;
        this.windowsNumber = windowsNumber;
    }

    public void run() {
        try {
            java.awt.Image awtImage = null;
            URL url = new URL(image.getUrl());
            awtImage = ImageIO.read(url);
            
            JLabel nombreLabel = new JLabel(image.getName());
            this.add(nombreLabel, BorderLayout.NORTH);
            
            ImageIcon imageIcon = new ImageIcon(awtImage);
            JLabel picLabel = new JLabel(imageIcon);
            
            // Guardar la imagen en Disco Duro
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), 
                    imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            bufferedImage.getGraphics().drawImage(awtImage, 0, 0, null);
            
            ImageIO.write(bufferedImage, "jpg", new File( image.getName() + ".jpg"));
            
            
            this.add(picLabel);
            setBounds(windowsNumber * 20, windowsNumber * 30, 350,350);
            setVisible(true);
            setClosable(true);
            
            Cliente.desktopPane.add(this);
            
            
        } catch (Exception ex) {
            System.err.print("Error en: " + ex.getMessage());
        }

    }

}
