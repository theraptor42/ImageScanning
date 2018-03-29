//Caspian Peavyhouse
//8/6/2015
//Simple project to learn about edge detection and image scanning

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.awt.image.renderable.RenderableImage;
import java.util.*;
import java.io.*;

public class EdgeDetection
{
    public static void main(String[] args) throws Exception
    {
        File source = new File("G:/My Pictures/ImageEditing/Originals/b3.jpg");
        File output = new File("G:/My Pictures/ImageEditing/Modified/b3.jpg");
        BufferedImage img = ImageIO.read(source);

        int[] colors = new int[img.getWidth() * img.getHeight()];
        int width = img.getWidth();
        int height = img.getHeight();
        int red=0;
        int blue=0;
        int green=0;

        int[][][] pixels = new int[width][height][3];
        int count=0;
        for( int i = 0; i < width; i++ )
            for( int j = 0; j < height; j++ )
            {

            }
        System.out.println(count);
    }
}