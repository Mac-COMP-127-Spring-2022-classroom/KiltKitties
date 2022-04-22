

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import edu.macalester.graphics.GraphicsObject;

public class Cat {
    // private File graphicSource = new File("src/catGraphic.png");
    private BufferedImage basicCatShape, currentCatShape;
    private String name;
    private String currentCatShapeFilepath;
    private int generation;
    private int furRed, furGreen, furBlue;
    private int eyeRed, eyeGreen, eyeBlue;
    private int bellyRed, bellyGreen, bellyBlue;

    public Cat(String name) {
        try {
            this.basicCatShape = ImageIO.read(new File("res/cats/catGraphic.png"));
            this.currentCatShape = ImageIO.read(new File("res/cats/catGraphic.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        this.name = name;
        this.generation = 0;
        this.furRed = 255;
        this.furGreen = 255;
        this.furBlue = 255;
        this.eyeRed = 255;
        this.eyeGreen = 255;
        this.eyeBlue = 255;
        this.bellyRed = 255;
        this.bellyGreen = 255;
        this.bellyBlue = 255;
        updateColor();
    }

    private void updateColor() {
        int alpha = 255;
        int fur = basicCatShape.getRGB(0, 0);
        int eye = basicCatShape.getRGB(0, 0);
        int belly = basicCatShape.getRGB(0, 0);
        fur = (alpha<<24) | (furRed<<16) | (furGreen<<8) | furBlue;
        eye = (alpha<<24) | (eyeRed<<16) | (eyeGreen<<8) | eyeBlue;
        belly = (alpha<<24) | (bellyRed<<16) | (bellyGreen<<8) | bellyBlue;
        for (int y = 0; y < basicCatShape.getHeight(); y++) {
            for (int x = 0; x < basicCatShape.getWidth(); x++) {
                int p = basicCatShape.getRGB(x, y);
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;
                // int red = (pixelRGB & 0x00ff0000) >> 16;
                // int green = (pixelRGB & 0x00ff0000) >> 8;
                // int blue = (pixelRGB & 0x00ff0000);
                if (r > 250) {
                    basicCatShape.setRGB(x, y, fur);
                } else if(g > 250) {
                    basicCatShape.setRGB(x, y, eye);
                } else if (b > 250) {
                    basicCatShape.setRGB(x, y, belly);
                }
            }
        }
        this.currentCatShape = this.basicCatShape;
        try {
            this.basicCatShape = ImageIO.read(new File("res/cats/catGraphic.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        exportImage();
    }

    private void exportImage() {
        String filepath = "res/cats/"+ name + ".png";
        File f = new File(filepath);
        try {
            f.delete();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            ImageIO.write(this.currentCatShape, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
        this.currentCatShapeFilepath = filepath;
    }

    public void setFurColor(int red, int green, int blue) {
        this.furRed = red;
        this.furBlue = blue;
        this.furGreen = green;
        updateColor();
    }

    public void setBellyColor(int red, int green, int blue) {
        this.bellyRed = red;
        this.bellyBlue = blue;
        this.bellyGreen = green;
        updateColor();
    }

    private void setEyeColor(int red, int green, int blue) {
        this.eyeRed = red;
        this.eyeBlue = blue;
        this.eyeGreen = green;
        updateColor();
    }

    public String getFilepathKilt() {
        return this.currentCatShapeFilepath.substring(4);
    }

    public String getFilepath() {
        return this.currentCatShapeFilepath;
    }

    public static void main(String[] args) throws Exception {
        Cat nevis = new Cat("nevis");
        Cat nevis1 = new Cat("matthew");
        nevis.setFurColor(120, 0, 0);
        nevis1.setEyeColor(0, 66, 122);
        nevis.setBellyColor(0, 220, 12);
        System.out.println(nevis.getFilepath());
        System.out.println(nevis1.getFilepath());
        
    }

   
}