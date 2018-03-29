import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by d853693 on 1/12/2017.
 */
public class Thumbnail extends JLabel
{
    public Thumbnail(Icon icon)
    {
        if (icon != null)
        {
            this.setIcon(icon);
        }
    }

    public Thumbnail(BufferedImage img)
    {
        if (img != null)
        {
            //default scaling is 1/10th original size
            Image thumb = img.getScaledInstance(img.getWidth()/10,img.getHeight()/10, BufferedImage.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(thumb);
            this.setIcon(icon);
        }

    }

    public Thumbnail(BufferedImage img, Dimension scale)
    {
        if (img != null)
        {
            Image thumb = img.getScaledInstance((int)scale.getWidth(),
                    (int)scale.getHeight(), BufferedImage.SCALE_SMOOTH);
            ImageIcon icon =  new ImageIcon(thumb);
            this.setIcon(icon);
        }

    }

    public Thumbnail(File imageFile)
    {
        try
        {
            BufferedImage img = ImageIO.read(imageFile);
            this.setIcon((new Thumbnail(img)).getIcon());
        }
        catch (IOException|NullPointerException err)
        {
            System.out.println(err);
            this.setIcon(null);
            return;
        }
    }
    public Thumbnail(File imageFile, Dimension scale)
    {
        try
        {
            BufferedImage img = ImageIO.read(imageFile);
            this.setIcon((new Thumbnail(img, scale)).getIcon());
        }
        catch (IOException|NullPointerException err)
        {
            System.out.println(err);
            this.setIcon(null);
            return;
        }
    }

    public boolean setNewIcon(BufferedImage img)
    {
        if (img != null)
        {
            Image thumb = img.getScaledInstance(img.getWidth()/10,img.getHeight()/10, BufferedImage.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(thumb);
            this.setIcon(icon);
            return true;
        }
        return false;
    }

    public boolean setNewIcon(BufferedImage img, Dimension scale)
    {
        if (img != null || scale == null)
        {
            Image thumb = img.getScaledInstance((int)scale.getWidth(),(int)scale.getHeight(), BufferedImage.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(thumb);
            this.setIcon(icon);
            return true;
        }
        return false;
    }

    public boolean setNewIconKeepRatio(BufferedImage img, Dimension spaceAvailable)
    {
        if (img != null || spaceAvailable == null)
        {
            //
            Dimension newDimensions = fitImageInSpaceWithRatio(img, spaceAvailable);
            return this.setNewIcon(img, newDimensions);
        }
        return false;
    }

    public Dimension fitImageInSpaceWithRatio(BufferedImage img, Dimension spaceAvailable)
    {
        //will only process  from 1/1th to 1/20th scale
        //max of 200 loops
        int newWidth = img.getWidth();
        int newHeight = img.getHeight();

        double counter = 1;
        while ( counter <= 20 && newWidth > spaceAvailable.getWidth()
                && newHeight > spaceAvailable.getHeight())
        {
            counter +=.1;
            newWidth = (int)(img.getWidth() / counter);
            newHeight = (int)(img.getHeight() / counter);

        }

        counter -=.1;
        newWidth = (int)(img.getWidth() / counter);
        newHeight = (int)(img.getHeight() / counter);

        return new Dimension(newWidth, newHeight);
    }

}
