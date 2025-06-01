import java.lang.Math;
class MotorRacer
{
    private int PosX = 0;
    private int PosY = 0;
    private int ScreenX;
    private int ScreenY;
    private int [] DifaultSize = new int [2];
    private int SizeX;
    private int SizeY;
    private String Type;
    private String ImageDir;
    private final int MaxSpeed = 10;
    private int Speed;
    private int DirX = 0;
    private int DirY = 0;
    private int Timer = 0;
    private final int MaxTime = 100;
    private final int MinSpeed = 1;
    private boolean Finished = false;

    public MotorRacer(String Type, String ImageDir, int ScreenX, int ScreenY, int SizeX, int SizeY)
    {
        this.Type = Type;
        this.ImageDir = ImageDir;
        this.ScreenX = ScreenX;
        this.ScreenY = ScreenY;
        this.SizeX = SizeX;
        this.SizeY = SizeY;
        this.DifaultSize[0] = SizeX;
        this.DifaultSize[1] = SizeY;
    }

    void SendToStart()
    {
        this.PosY = this.ScreenY - this.SizeY;
        this.PosX =  ((int) this.ScreenX / 2) - (int) this.SizeX / 2;
        this.ImageDir = "";
        this.Finished = false;
    }

    private void VarySpeed()
    {
        if (this.Timer == 0)
        {
            this.Timer = 1 + (int)(Math.random() * MaxTime);
            this.Speed = this.MinSpeed + (int)(Math.random() * MaxSpeed);
        }
        else
        {
            this.Timer -= 1;
        }
    }

    void UpdatePos()
    {
        VarySpeed();
        if (this.PosX < this.ScreenX - this.SizeX && this.PosY >= this.ScreenY - this.DifaultSize[1])
        {
            this.ImageDir = "";
            this.SizeX = this.DifaultSize[0];
            this.SizeY = this.DifaultSize[1];
            this.DirY = 0;
            this.DirX = 1;

            final int finishX = ((int) this.ScreenX / 2) - (int) this.SizeX / 2;

            if (this.PosX < finishX && this.PosX + this.Speed * this.DirX >= finishX)
            {
                this.Finished = true;
            }

        }
        else if (this.PosY > 0 && this.PosX >= this.ScreenX - this.DifaultSize[0])
        {
            this.ImageDir = "Up";
            this.SizeX = this.DifaultSize[1];
            this.SizeY = this.DifaultSize[0];
            this.PosX = this.ScreenX - this.DifaultSize[1];
            this.DirX = 0;
            this.DirY = -1;
        }
        else if (this.PosX > 0)
        {
            this.ImageDir = "Flipped";
            this.SizeX = this.DifaultSize[0];
            this.SizeY = this.DifaultSize[1];
            this.DirY = 0;
            this.DirX = -1;
        }
        else if (this.PosY < this.ScreenY - this.DifaultSize[0])
        {
            this.ImageDir = "Down";
            this.SizeX = this.DifaultSize[1];
            this.SizeY = this.DifaultSize[0];
            this.DirX = 0;
            this.DirY = 1;
        }
        this.PosX += this.Speed * DirX;
        this.PosY += this.Speed * DirY;
    }

    int getPosX()
    {
        return this.PosX;
    }

    int getPosY()
    {
        return this.PosY;
    }

    int getWidth()
    {
        return this.SizeX;
    }

    int getHeight()
    {
        return this.SizeY;
    }


    String getImage()
    {
        return  this.Type + this.ImageDir;
    }

    boolean isFinished()
    {
        return  this.Finished;
    }
}