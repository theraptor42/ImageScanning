import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by d853693 on 1/9/2017.
 */
public class SubtractImage
{
    public static void main(String[] args) throws Exception {
        //File source = new File("G:/My Pictures/ImageEditing/Originals/b3.1.jpg");
        File source = CustomMenu.chooseAFile();
        File addition = new File("G:/My Pictures/ImageEditing/Modified/b3.1f.jpg");
        File output = new File("G:/My Pictures/ImageEditing/Modified/what3.jpg");

        BufferedImage img = ImageIO.read(source);
        BufferedImage add = ImageIO.read(addition);

        BufferedImage[] tempArray = MiscTools.padImagesToSameSize(img, add);
        img = tempArray[0];
        add = tempArray[1];

        //FileWriter writer = new FileWriter(text);
        int width = img.getWidth();
        int height = img.getHeight();
        int redOne;
        int greenOne;
        int blueOne;
        int redTwo;
        int greenTwo;
        int blueTwo;
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

                if (redOne - redTwo <0)
                    redOne = 0;
                else
                    redOne = redOne - redTwo;

                if (greenOne - greenTwo <0)
                    greenOne = 0;
                else
                    greenOne = greenOne - greenTwo;

                if (blueOne - blueTwo <0)
                    blueOne = 0;
                else
                    blueOne = blueOne - blueTwo;

                Color outRGB = new Color(redOne, greenOne, blueOne);
                img.setRGB(i, j, outRGB.getRGB());

            }
        }

        //writer.close();
        ImageIO.write(img, "png", output);

    }
}
