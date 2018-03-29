import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;

public class Average
{
    public static void main(String[] args) throws Exception {
        File imgOne = new File("G:/My Pictures/ImageEditing/Originals/b3.jpg");
        File imgTwo = new File("G:/My Pictures/ImageEditing/Originals/b3.1.jpg");
        File output = new File("G:/My Pictures/ImageEditing/Modified/b3x.jpg");
        //File text = new File("G:/My Pictures/Sharepoint/Final_Fantasy/sephiroth_2.txt");

        BufferedImage imageOne = ImageIO.read(imgOne);
        BufferedImage imageTwo = ImageIO.read(imgTwo);

        BufferedImage[] tempArray = MiscTools.padImagesToSameSize(imageOne, imageTwo);
        imageOne = tempArray[0];
        imageTwo = tempArray[1];

        //FileWriter writer = new FileWriter(text);
        int width = imageOne.getWidth();
        int height = imageOne.getHeight();
        Color oneColor;
        Color twoColor;
        int redOne;
        int greenOne;
        int blueOne;
        int redTwo;
        int greenTwo;
        int blueTwo;
        int oneRGB;
        int twoRGB;

        int redAvg;
        int greenAvg;
        int blueAvg;
        int avgRGB;
        Color avgColor;

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {


                oneRGB = imageOne.getRGB(i, j);
                oneColor = new Color(oneRGB);
                redOne = oneColor.getRed();
                greenOne = oneColor.getGreen();
                blueOne = oneColor.getBlue();

                twoRGB = imageTwo.getRGB(i, j);
                twoColor = new Color(twoRGB);
                redTwo = twoColor.getRed();
                greenTwo = twoColor.getGreen();
                blueTwo = twoColor.getBlue();


                redAvg = (redOne+redTwo)/2;
                greenAvg = (greenOne+greenTwo)/2;
                blueAvg = (blueOne+blueTwo)/2;
                if  (blueAvg > 255)
                    blueAvg=255;
                /*
                if (redAvg <= 20 && blueAvg <= 20 && greenAvg <= 20)
                {
                    redAvg =0;
                    blueAvg = 0;
                    greenAvg =0;
                }
                else
                {
                    redAvg +=40;
                    blueAvg +=40;
                    greenAvg +=40;
                }

                if  (blueAvg > 255)
                    blueAvg=255;
                if  (redAvg > 255)
                    redAvg=255;
                if  (greenAvg > 255)
                    greenAvg=255;
                */
                avgColor= new Color(redAvg, greenAvg, blueAvg);
                avgRGB = avgColor.getRGB();
                imageOne.setRGB(i, j, avgRGB);
                oneColor = new Color(twoRGB);
                redOne = oneColor.getRed();
                greenOne = oneColor.getGreen();
                blueOne = oneColor.getBlue();

                //writer.write(String.format("(%03d, %03d, %03d)  ", redOne, greenOne, blueOne));
            }
            //writer.write("\r\n");
        }
        //writer.close();
        ImageIO.write(imageOne, "png", output);

    }
}