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
    private JPanel MainPanel = new JPanel(new FlowLayout());
    private HashMap<String, ImageIcon> ImgResources = new HashMap<String, ImageIcon>();
    private JLabel [] Motors = {new JLabel(), new JLabel(),new JLabel(),new JLabel()};
    private JTextField BetInput = new JTextField("",8);
    private JLabel BetAmountLabel = new JLabel("Wagered: 0");
    private JLabel MoneyLabel = new JLabel("Money: 100");
    private JLabel BetOutcomeLabel = new JLabel("");
    JButton WagerButton = new JButton ("Wager");


    String [] RacerList = {"Blue", "Green", "Red", "Black"};
    String [] ModeList = {"Normal", "Endless"};

    JComboBox RacerDrop = new JComboBox(RacerList);
    JComboBox ModeDrop = new JComboBox(ModeList);
    boolean WonBet = false;
    private MotorRacer [] Racers = new MotorRacer [4];
    final int IntermissionWaitTime = 300;
    int IntermissionTime = 0;
    int WageredAmount = 0;
    int RacerWageredOn = 0;
    int Money = 100;

    boolean RaceFinished = false;


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


        MainPanel.setSize(200,200);
        MainPanel.add(MoneyLabel);
        MainPanel.add(BetAmountLabel);
        MainPanel.add(BetInput);
        MainPanel.add(RacerDrop);
        WagerButton.addActionListener(e -> wagerAmount());
        MainPanel.add(WagerButton);
        MainPanel.add(ModeDrop);
        MainPanel.add(BetOutcomeLabel);
        ScreenGUI.add(MainPanel);


        PackGUI();
        ScreenEdgeDrive();

    }

    void wagerAmount()
    {
        int RacIndex = RacerDrop.getSelectedIndex();
        String StWagerAm = BetInput.getText();

        if (isNumber(StWagerAm))
        {
            RacerWageredOn = RacIndex;
            Money = Money + WageredAmount;
            WageredAmount = Integer.parseInt(StWagerAm);
            Money = Money - WageredAmount;
            MoneyLabel.setText("Money: " + Money);
            BetAmountLabel.setText("Wagered: " + WageredAmount);
            MoneyLabel.revalidate();
            BetAmountLabel.revalidate();
        }

    }

    boolean isNumber(String Text)
    {
        for (int i = 0; i < Text.length(); i++)
        {
            if (!(Text.charAt(i) >= '0' && Text.charAt(i) <= '9'))
            {
                return false;
            }
        }

        return true;
    }
    void ScreenEdgeDrive()
    {
        Dimension ScreenSize = ScreenGUI.getSize();
        int ScreenHeight = ScreenSize.height;
        int ScreenWidth = ScreenSize.width;

        AddRacers(ScreenWidth, ScreenHeight);

        timer = new javax.swing.Timer(20, e -> {

            if (!RaceFinished) {
                MainPanel.setVisible(false);
                updateRacersPosition();
                for (int i = 0; i < 4; i++) {
                    Motors[i].setIcon(ImgResources.get(Racers[i].getImage()));
                    Motors[i].setBounds(Racers[i].getPosX(), Racers[i].getPosY(), Racers[i].getWidth(), Racers[i].getHeight());
                    Motors[i].revalidate();
                }
            }
            else
            {
                MainPanel.setVisible(true);
                if (IntermissionTime < IntermissionWaitTime && ModeDrop.getSelectedIndex() == 0)
                {
                    for (int i = 0; i < 4; i++)
                    {
                        Racers[i].SendToStart();
                    }
                    IntermissionTime++;
                }
                else
                {
                    IntermissionTime = 0;
                    RaceFinished = false;
                }

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
        boolean AllFinished = true;
        int RacersFinished = 0;
        int IndOfRac = -1;
        for (int i =0; i < 4; i++)
        {
            if (!Racers[i].isFinished() || ModeDrop.getSelectedIndex() == 1)
            {
                AllFinished = false;
                Racers[i].UpdatePos();
            }
            else
            {
                RacersFinished++;
                IndOfRac = i;
            }

        }
        if (RacersFinished == 4)
        {
            RaceFinished = true;
            MoneyLabel.revalidate();
            BetAmountLabel.revalidate();
            BetOutcomeLabel.revalidate();
        }
        else if (RacersFinished == 1 && WageredAmount != 0)
        {
            if (IndOfRac == RacerWageredOn)
            {
                WonBet = true;
                BetOutcomeLabel.setText("You won: " + WageredAmount * 2);
                Money += WageredAmount * 2;
                WageredAmount = 0;
            }
            else
            {
                BetOutcomeLabel.setText("You lost: " + WageredAmount);
                WageredAmount = 0;
                WonBet = false;
            }

            MoneyLabel.setText("Money: " + Money);
            BetAmountLabel.setText("WageredAmount: " + WageredAmount);
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

