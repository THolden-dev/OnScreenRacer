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
        ImgResources.put("MotorUp", new ImageIcon("Assets/motorcycleUp.png"));
        ImgResources.put("MotorDown", new ImageIcon("Assets/motorcycleDown.png"));
        ImgResources.put("MotorFlipped", new ImageIcon("Assets/motorcyclefliped.png"));
    }

    void ScreenDrive()
    {
        ScreenGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScreenGUI.setLayout(null);

        
        Motor.setIcon(ImgResources.get("Motor"));
        Motor.setLocation(300,300);
        Motor.setSize(200,200);
        ScreenGUI.add(Motor);

        PackGUI();
        ScreenEdgeDrive();

    }

    void ScreenEdgeDrive()
    {
        Dimension ScreenSize = ScreenGUI.getSize();
        int ScreenHeight = ScreenSize.height;
        int ScreenWidth = ScreenSize.width;
        System.out.println(ScreenHeight);
        System.out.println(ScreenWidth);
        RacerPos[1] = ScreenHeight - 200;

        timer = new javax.swing.Timer(20, e -> {

            updateRacerPosition(ScreenWidth, ScreenHeight);
            Motor.setBounds(RacerPos[0], RacerPos[1], 300, 300);
            Motor.revalidate();
        });
        timer.setRepeats(true); // Only fire once
        timer.start();
    }

    private void updateRacerPosition(int ScreenSizeX, int ScreenSizeY)
    {
        final int SizeXRacer = 300;
        final int SizeYRacer = 300;
        final int Speed = 10;
        int DirX = 0;
        int DirY = 0;

        if (RacerPos[0] < ScreenSizeX - SizeXRacer && RacerPos[1] >= ScreenSizeY - SizeYRacer)
        {
            Motor.setIcon(ImgResources.get("Motor"));
            DirX = 1;
        }
        else if (RacerPos[1] > 0 && RacerPos[0] >= ScreenSizeX - SizeXRacer)
        {
            Motor.setIcon(ImgResources.get("MotorUp"));
            DirY = -1;
        }
        else if (RacerPos[0] > 0)
        {
            Motor.setIcon(ImgResources.get("MotorFlipped"));
            DirX = -1;
        }
        else if (RacerPos[1] < ScreenSizeY - SizeXRacer)
        {
            Motor.setIcon(ImgResources.get("MotorDown"));
            DirY = 1;
        }



        RacerPos[0] += Speed * DirX;
        RacerPos[1] += Speed * DirY;
    }
    
    private void PackGUI()
    {
        ScreenGUI.setUndecorated(true);
        ScreenGUI.setBackground(new Color(1.0f,1.0f,1.0f,0f));
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