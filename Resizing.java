/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resizing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author Bojo Alcisto
 */
public class Resizing {

    /**
     * @param result
     * @param input
     * @param times
     * @param percentage
     */
    public static void increase(BufferedImage result, BufferedImage input, int times){
        for(int i = 0; i < input.getHeight(); i++){
            for(int j = 0; j < input.getWidth(); j++){
                Color c = new Color(input.getRGB(j, i));
                Color draw = new Color(c.getRed(), c.getGreen(), c.getBlue());
                
                for(int k = 0; k<times; k++){
                    for(int p = 0; p<times; p++){
                        result.setRGB((times*j)+k, (times*i)+p, draw.getRGB());
                    }
                }
            }
        }
    }
    
    public static void decrease(BufferedImage result, BufferedImage input, int times){
        for(int i = 0; i < input.getHeight(); i++){
            for(int j = 0; j < input.getWidth(); j++){
                Color c = new Color(input.getRGB(j, i));
                Color draw = new Color(c.getRed(), c.getGreen(), c.getBlue());
                
                for(int k = 0; k<times; k++){
                    for(int p = 0; p<times; p++){
                        result.setRGB((times*j)+k, (times*i)+p, draw.getRGB());
                    }
                }
            }
        }
    }    
    public static void resize(BufferedImage input, int height, int width, double percentage){
        
        int scaleHeight = (height * (int)(percentage/100));
        int scaleWidth = (width * (int)(percentage/100));
        
        System.out.println("Original Height: " + height);
        System.out.println("Scale Height: " + scaleHeight);
        System.out.println("Original Width: " + width);
        System.out.println("Scale Width: " + scaleWidth);
        
        BufferedImage result = new BufferedImage(
                scaleWidth,
                scaleHeight,
                BufferedImage.TYPE_INT_RGB
        );
        
        Graphics2D graphic = result.createGraphics();
        graphic.drawImage(input, 0, 0, Color.WHITE, null);
       
        if(percentage < 100){
            decrease(result, input, (int)(percentage/100));
        }else{
            increase(result, input, (int)(percentage/100));
        }
        
        try{
            File output = new File("C:/Users/Bojo Alcisto/Desktop/ImageProcessing/IncreaseSize.png");
            ImageIO.write(result, "png", output);
        }catch(IOException e){
            System.out.println("Cannot output file");
        }
    }
    
    public static void main(String[] args) {
        try{
            File input = new File("C:/Users/Bojo Alcisto/Desktop/ImageProcessing/Test.png");
            BufferedImage imageInput = ImageIO.read(input);
            
            Resizing.resize(imageInput, imageInput.getHeight(), imageInput.getWidth(), 600.0);
            
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
}
