import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by d853693 on 1/6/2017.
 */
public class InvertColors
{
    //The idea is for each pixel, for each color value in the RGB array, flip it's value
    // on [0-255] to the other side of 128

    public static void main(String[] args) throws Exception
    {
        File imgOne = new File("G:/My Pictures/ImageEditing/Originals/me.jpg");
        File output = new File("G:/My Pictures/ImageEditing/Modified/mef.jpg");
        //File text = new File("G:/My Pictures/Sharepoint/Final_Fantasy/sephiroth_2.txt");

        BufferedImage image = ImageIO.read(imgOne);

        int width = image.getWidth();
        int height = image.getHeight();
        int red;
        int green;
        int blue;
        Color thisColor;


        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                thisColor = new Color(image.getRGB(i, j));

                red = flipColor(thisColor.getRed());
                green = flipColor(thisColor.getGreen());
                blue = flipColor(thisColor.getBlue());
                //System.out.println(String.format("Red: %d, Green: %d, Blue: %d", red, green, blue));
                thisColor = new Color(red, green, blue);
                image.setRGB(i, j, thisColor.getRGB());
            }

        }
        ImageIO.write(image, "png", output);

    }

    public static int flipColor(int oldColor)
    {
        if (oldColor == 128)
        {
            return oldColor; //no need to flip
        }
        if (oldColor > 128)
        {
            int temp = oldColor - 128;
            return (128 - temp);
        }
        else //(oldColor < 128)
        {
            int temp = 127 - oldColor;
            return (127 + temp);
        }
    }
}
