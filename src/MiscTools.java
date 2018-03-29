import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by d853693 on 1/4/2017.
 */
public class MiscTools
{
    public static BufferedImage[] padImagesToSameSize(BufferedImage img1, BufferedImage img2)
    {
        BufferedImage[] returnArray = new BufferedImage[2];
        if (img1.getWidth() != img2.getWidth())
        {
            if (img1.getWidth() > img2.getWidth())
            {
                img2 = MiscTools.padImageToSize(img1.getWidth(), img2.getHeight(), img2);
            }
            else
            {
                img1 = MiscTools.padImageToSize(img2.getWidth(), img1.getHeight(), img1);
            }
        }

        if (img1.getHeight() != img2.getHeight())
        {
            if (img1.getHeight() > img2.getHeight())
            {
                img2 = MiscTools.padImageToSize(img2.getWidth(), img1.getHeight(), img2);
            }
            else
            {
                img1 = MiscTools.padImageToSize(img1.getWidth(), img2.getHeight(), img1);
            }
        }

        returnArray[0] = img1;
        returnArray[1] = img2;

        return returnArray;
    }
    public static BufferedImage padImageToSize(int width, int height, BufferedImage img) {
        if (img == null) {
            return null;
        }
        if (width == img.getWidth() && height == img.getHeight())
        {
            return img;
        }
        int originalWidth = img.getWidth();
        int originalHeight = img.getHeight();
        BufferedImage returnImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int totalBorderWidth = returnImage.getWidth() - originalWidth;

        int leftBorderSize = totalBorderWidth / 2;

        int totalBorderHeight = returnImage.getHeight() - originalHeight;

        int topBorderSize = totalBorderHeight / 2;

        int topEdge = topBorderSize -1; //zero index
        int bottomEdge = topBorderSize + originalHeight;

        int leftEdge = leftBorderSize -1;
        int rightEdge = leftBorderSize + originalWidth;

        ArrayList<Color> orig = new ArrayList<Color>();
        for (int i = 0; i < originalWidth; i++)
        {
            for (int j = 0; j < originalHeight; j++)
            {
                orig.add(new Color(img.getRGB(i, j)));
            }
        }

        int index = 0;
        int size = orig.size();

        for (int i = 0; i < width; i++)
        {

            // Debug: System.out.println(String.format("Wrote pixel %d of %d : %d", index, size, (index* 100/size) ) + "% complete");

            for (int j = 0; j < height; j++)
            {
                if (i > leftEdge  && j > topEdge && i < rightEdge && j < bottomEdge)
                {

                    returnImage.setRGB(i, j, orig.get(index).getRGB());
                    index++;
                }
                else
                {
                    returnImage.setRGB(i, j, Color.BLACK.getRGB());
                }


            }
        }

        return returnImage;
    }
}
