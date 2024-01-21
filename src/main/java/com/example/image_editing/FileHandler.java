package com.example.image_editing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;


public class FileHandler {

    public static void DuplicateImageAndSave(String path)
    {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("Couldnt read image");
        }
        String[] names = path.split("\\\\");
        FileHandler.SaveImage(img,"Images\\" + names[names.length-1]);
    }
    public static void SaveImage(BufferedImage img, String filename)
    {
        try {
            ImageIO.write(img, "png",new File(filename));

        } catch (Exception e) {
            System.out.println("Couldnt write image");
        }
    }
}
