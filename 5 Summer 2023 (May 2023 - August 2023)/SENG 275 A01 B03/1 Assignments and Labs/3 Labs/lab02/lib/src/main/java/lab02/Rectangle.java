/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lab02;

import com.google.common.annotations.VisibleForTesting;

public class Rectangle {
    private int X;
    private int Y;
    private int Width;
    private int Height;

    public Rectangle()
    {
        this(0,0,1,1);
    }


    public Rectangle (int X, int Y, int Width, int Height)
    {
        this.X = X;
        this.Y = Y;
        this.Width = Width;
        this.Height = Height;

        Rectangle2(this.X,this.Y,this.Width,this.Height);
    }

    public void Rectangle2 (int X, int Y, int Width, int Height)
    {
        this.X = X;
        this.Y = Y;
        this.Width = Width;
        this.Height = Height;
    }
    public int getX() { return this.X; }
    public int getY() { return this.Y; }

    public void setX(int X)
    {
        this.X = X;
    }

    public void setY(int Y)
    {
        this.Y = Y;
    }
    public int getWidth() { return this.Width; }
    public void setWidth(int Width)
    {
        this.Width = Math.abs(Width);
    }
    public int getHeight() { return this.Height; }

    public void setHeight(int Height)
    {
        this.Height = Math.abs(Height);
    }

    public int getArea () {
        return (getWidth() * getHeight());
    }
//    public void make
}