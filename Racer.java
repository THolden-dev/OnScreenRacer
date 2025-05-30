import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;
import java.lang.Math;
import javax.swing.*;
import java.awt.*;

class RacerStart
{
    private javax.swing.Timer timer;
    private JFrame ScreenGUI = new JFrame("Racer");
    private JPanel MainPanel = new JPanel(null);
    private HashMap<String, ImageIcon> ImgResources = new HashMap<String, ImageIcon>();
    private JLabel Motor = new JLabel();
    private int [] RacerPos = {0,0};

    public RacerStart()
    {
        LoadAssets();
        ScreenDrive();
    }

    void LoadAssets()
    {
        ImgResources.put("Motor", new ImageIcon("Assets/motorcycle.png"));
    }

    void ScreenDrive()
    {
        ScreenGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScreenGUI.setLayout(null);

        
        Motor.setIcon(ImgResources.get("Motor"));
        Motor.setLocation(300,300);
        Motor.setSize(200,200);
        ScreenGUI.add(Motor);

        ScreenEdgeDrive();
        PackGUI();

    }

    void ScreenEdgeDrive()
    {
        Dimension ScreenSize = ScreenGUI.getSize();
        int ScreenHeight = ScreenSize.height;
        int ScreenWidth = ScreenSize.width;

        timer = new javax.swing.Timer(50, e -> {

            updateRacerPosition();
            Motor.setBounds(RacerPos[0], RacerPos[1], 300, 300);
            Motor.revalidate();
        });
        timer.setRepeats(true); // Only fire once
        timer.start();
    }

    private void updateRacerPosition(int ScreenSizeX, int ScreenSizeY)
    {
        final int SizeXRacer = 300;
        final int SizeyRacer = 300;
        final int Speed = 5;
        int DirX = 0;
        int DirY = 0;

        if (RacerPos[0] < ScreenSizeX - SizeXRacer)
        {
            DirX = 1;
        }
        else if (RacerPos[1] > ScreenSizeY - SizeXRacer)
        {
            DirY = -1;
        }
        else if (RacerPos[0] > 0)
        {
            DirX = -1;
        }
        else if (RacerPos[1] < ScreenSizeY - SizeXRacer)
        {
            
        }
    }
    
    private void PackGUI()
    {
        ScreenGUI.setUndecorated(true);
        ScreenGUI.setBackground(new Color(1.0f,1.0f,1.0f,0f));
        ScreenGUI.pack();
        ScreenGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ScreenGUI.setVisible(true);
    }
}

class Racer
{
    public static void main(String [] Args)
    {
        RacerStart Screen = new RacerStart();

    }
}