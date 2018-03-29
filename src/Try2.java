import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;

public class Try2 {
    public static void main(String[] args) throws Exception {
        File source = new File("G:/My Pictures/ImageEditing/Originals/b3.jpg");
        File output = new File("G:/My Pictures/ImageEditing/Modified/b3.5.jpg");
        File text = new File("G:/My Pictures/ImageEditing/Originals/b3.txt");

        BufferedImage img = ImageIO.read(source);
        FileWriter writer = new FileWriter(text);
        int width = img.getWidth();
        int height = img.getHeight();
        Color converter;
        int red;
        int green;
        int blue;
        int orig_rgb;
        converter = new Color(0,255,0);
        int green_rgb = converter.getRGB();

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                orig_rgb = img.getRGB(i, j);
                converter = new Color(orig_rgb);
                red = converter.getRed();
                green = converter.getGreen();
                blue = converter.getBlue();

                if (j < 11600)
                {
                    if (blue > red && blue > green)
                    {

                    }
                    else
                    {
                        img.setRGB(i, j, new Color(0,255,0).getRGB());
                    }
                }
                else
                {
                    img.setRGB(i, j,  new Color(0,255,0).getRGB());
                }
                //writer.write(String.format("(%03d, %03d, %03d)  ", red, green, blue));
            }
            //writer.write("\r\n");
        }
        //writer.close();
        ImageIO.write(img, "jpg", output);

    }
}