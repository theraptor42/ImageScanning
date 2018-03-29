import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class ColorSwitch
{
    public static void main(String[] args) throws Exception {
        File source = new File("G:/My Pictures/ImageEditing/Originals/b3.jpg");
        File output = new File("G:/My Pictures/ImageEditing/Modified/b3.jpg");

        BufferedImage img = ImageIO.read(source);
        //FileWriter writer = new FileWriter(text);
        int width = img.getWidth();
        int height = img.getHeight();
        Color converter;
        int red;
        int green;
        int blue;
        int orig_rgb;
        //int count = 0;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                orig_rgb = img.getRGB(i, j);
                converter = new Color(orig_rgb);
                red = converter.getRed();
                green = converter.getGreen();
                blue = converter.getBlue();
                Color switchedColor = new Color(blue,green,red);
                //Color switchedColor = new Color(blue,green,red);

                //.if (red+green >= 200)
                //if (count % 2 == 1)
                {
                    img.setRGB(i, j, switchedColor.getRGB());
                }
                //count ++;

            }
            //writer.write("\r\n");
            //System.out.println("\r\n" + count);
        }
        //writer.close();
        ImageIO.write(img, "png", output);

    }
}
