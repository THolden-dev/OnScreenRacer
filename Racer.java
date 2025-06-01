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
    private JLabel [] Motors = {new JLabel(), new JLabel(),new JLabel(),new JLabel()};
    private MotorRacer [] Racers = new MotorRacer [4];
    boolean Racing = false;


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

        ImgResources.put("MotorBlack", new ImageIcon("Assets/motorcycleBlack.png"));
        ImgResources.put("MotorBlackUp", new ImageIcon("Assets/motorcycleBlackUp.png"));
        ImgResources.put("MotorBlackDown", new ImageIcon("Assets/motorcycleBlackDown.png"));
        ImgResources.put("MotorBlackFlipped", new ImageIcon("Assets/motorcycleBlackfliped.png"));

        ImgResources.put("MotorGreen", new ImageIcon("Assets/motorcycleGreen.png"));
        ImgResources.put("MotorGreenUp", new ImageIcon("Assets/motorcycleGreenUp.png"));
        ImgResources.put("MotorGreenDown", new ImageIcon("Assets/motorcycleGreenDown.png"));
        ImgResources.put("MotorGreenFlipped", new ImageIcon("Assets/motorcycleGreenfliped.png"));

        ImgResources.put("MotorRed", new ImageIcon("Assets/motorcycleRed.png"));
        ImgResources.put("MotorRedUp", new ImageIcon("Assets/motorcycleRedUp.png"));
        ImgResources.put("MotorRedDown", new ImageIcon("Assets/motorcycleRedDown.png"));
        ImgResources.put("MotorRedFlipped", new ImageIcon("Assets/motorcycleRedfliped.png"));
    }

    void ScreenDrive()
    {
        ScreenGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScreenGUI.setLayout(null);
        ScreenGUI.setAlwaysOnTop (true);

        for (int i =0; i < 4; i++)
        {
            ScreenGUI.add(Motors[i]);
        }

        PackGUI();
        ScreenEdgeDrive();

    }

    void ScreenEdgeDrive()
    {
        Dimension ScreenSize = ScreenGUI.getSize();
        int ScreenHeight = ScreenSize.height;
        int ScreenWidth = ScreenSize.width;

        AddRacers(ScreenWidth, ScreenHeight);

        timer = new javax.swing.Timer(20, e -> {

            updateRacersPosition();
            for (int i = 0; i < 4; i++)
            {
                Motors[i].setIcon(ImgResources.get(Racers[i].getImage()));
                Motors[i].setBounds(Racers[i].getPosX(), Racers[i].getPosY(), Racers[i].getWidth(), Racers[i].getHeight());
                Motors[i].revalidate();
            }

        });
        timer.setRepeats(true); // Only fire once
        timer.start();
    }

    void AddRacers(int ScreenX, int ScreenY)
    {
        final int Width = ImgResources.get("Motor").getIconWidth();
        final int Height = ImgResources.get("Motor").getIconHeight();

        Racers[0] = new MotorRacer("Motor", "", ScreenX, ScreenY, Width, Height);
        Racers[0].SendToStart();
        Racers[1] = new MotorRacer("MotorGreen", "", ScreenX, ScreenY, Width, Height);
        Racers[1].SendToStart();
        Racers[2] = new MotorRacer("MotorRed", "", ScreenX, ScreenY, Width, Height);
        Racers[2].SendToStart();
        Racers[3] = new MotorRacer("MotorBlack", "", ScreenX, ScreenY, Width, Height);
        Racers[3].SendToStart();
    }

    private void updateRacersPosition()
    {
       for (int i =0; i < 4; i++)
       {
           Racers[i].UpdatePos();

       }
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