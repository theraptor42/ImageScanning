import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;

public class Overlay
{
    public static void main(String[] args) throws Exception {
        File greenOne = new File("G:/My Pictures/ImageEditing/Modified/b3.jpg");
        File base = new File("G:/My Pictures/ImageEditing/Modified/b3.jpg");
        File output = new File("G:/My Pictures/ImageEditing/Modified/b4.6.jpg");

        BufferedImage hasGreenImage = ImageIO.read(greenOne);
        BufferedImage baseImg = ImageIO.read(base);

        int width = hasGreenImage.getWidth();
        int height = hasGreenImage.getHeight();
        Color converter;
        int red;
        int green;
        int blue;
        int overlay_rgb;
        int base_rgb;;

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                overlay_rgb = hasGreenImage.getRGB(i, j);
                converter = new Color(overlay_rgb);
                red = converter.getRed();
                green = converter.getGreen();
                blue = converter.getBlue();

                if (green > 180 && red+blue <150)
                {
                    base_rgb = baseImg.getRGB(i,j);
                    hasGreenImage.setRGB(i, j, base_rgb);
                }
            }
        }
        ImageIO.write(hasGreenImage, "jpg", output);

    }
}