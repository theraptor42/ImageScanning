import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by d853693 on 1/4/2017.
 */
public class AddImage
{
    public static void main(String[] args) throws Exception {
        File source = new File("G:/My Pictures/ImageEditing/Originals/b3.jpg");
        File addition = new File("G:/My Pictures/ImageEditing/Originals/b3.jpg");
        File output = new File("G:/My Pictures/ImageEditing/Modified/b3+.jpg");

        BufferedImage img = ImageIO.read(source);
        BufferedImage add = ImageIO.read(addition);

        BufferedImage[] tempArray = MiscTools.padImagesToSameSize(img, add);
        img = tempArray[0];
        add = tempArray[1];

        //FileWriter writer = new FileWriter(text);
        int width = img.getWidth();
        int height = img.getHeight();
        Color converter;
        int redOne;
        int greenOne;
        int blueOne;
        int redTwo;
        int greenTwo;
        int blueTwo;
        int orig_rgb;
        //int count = 0;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                redOne = (new Color(img.getRGB(i,j)).getRed());
                greenOne = (new Color(img.getRGB(i,j)).getGreen());
                blueOne = (new Color(img.getRGB(i,j)).getBlue());

                redTwo = (new Color(add.getRGB(i,j)).getRed());
                greenTwo = (new Color(add.getRGB(i,j)).getGreen());
                blueTwo = (new Color(add.getRGB(i,j)).getBlue());

                if (redOne + redTwo > 255)
                    redOne = 255;
                else
                    redOne = redOne + redTwo;

                if (greenOne + greenTwo > 255)
                    greenOne = 255;
                else
                    greenOne = greenOne + greenTwo;

                if (blueOne + blueTwo > 255)
                    blueOne = 255;
                else
                    blueOne = blueOne + blueTwo;

                Color outRGB = new Color(redOne, greenOne, blueOne);
                img.setRGB(i, j, outRGB.getRGB());

            }
        }

        //writer.close();
        ImageIO.write(img, "png", output);

    }
}
