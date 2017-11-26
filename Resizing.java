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
    public static void increase(BufferedImage result, BufferedImage input){
        int times = 2;
        
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
    
    public static void decrease(BufferedImage result, BufferedImage input){
        int times = 2;
        
        int r = 0;
        int g = 0;
        int b = 0;
        
        for(int i = 0; i <= input.getHeight() - times ; i+=times){
            for(int j = 0; j <= input.getWidth() - times; j+=times){
                r = 0;
                g = 0;
                b = 0;
                
                for(int k = i; k<(i+times); k++){
                    for(int p = j; p<(j+times); p++){
                        Color c = new Color(input.getRGB(p, k));
                        r += c.getRed();
                        g += c.getGreen();
                        b += c.getBlue();
                        //result.setRGB((times*j)+k, (times*i)+p, draw.getRGB());
                    }
                }
                r /= (times*times);
                g /= (times*times);
                b /= (times*times);
                Color draw = new Color(r, g, b);
                result.setRGB((j/times),(i/times),draw.getRGB());
            }
        }
    }    
    public static void resize(BufferedImage input, int height, int width, boolean increase){
        
        double percentage = (increase == false) ? 50.0 : 200.0;
        int scaleHeight = (int)((double)height * (percentage/100));
        int scaleWidth = (int)((double)width * (percentage/100));
        
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
       
        if(increase == false){
            decrease(result, input);
        }else{
            increase(result, input);
        }
        
        try{
            File output;
            if(increase) output = new File("C:/Users/Bojo Alcisto/Desktop/ImageProcessing/IncreaseSize.png");
            else output = new File("C:/Users/Bojo Alcisto/Desktop/ImageProcessing/DecreasedSize.png");
            ImageIO.write(result, "png", output);
        }catch(IOException e){
            System.out.println("Cannot output file");
        }
    }
    
    public static void main(String[] args) {
        try{
            File input = new File("C:/Users/Bojo Alcisto/Desktop/ImageProcessing/Test.png");
            BufferedImage imageInput = ImageIO.read(input);
            
            Resizing.resize(imageInput, imageInput.getHeight(), imageInput.getWidth(), false);
            
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
}
