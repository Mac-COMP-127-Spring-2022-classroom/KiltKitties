package cats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Cat {
    // private File graphicSource = new File("src/catGraphic.png");
    private BufferedImage basicCatShape, currentCatShape, overlay;
    private String name;
    private String currentCatShapeFilepath;
    private int furRed, furGreen, furBlue;
    private int eyeRed, eyeGreen, eyeBlue;
    private int bellyRed, bellyGreen, bellyBlue;
    private Gene gene;
    private String furTrait, bellyTrait, eyeTrait, accessoriesTrait;

    public Cat(String name, int furMaxLevel, int bellyMaxLevel, int eyeMaxLevel, int accessoriesMaxLevel) {
        try {
            this.basicCatShape = ImageIO.read(new File("res/catGraphic.png"));
            this.currentCatShape = ImageIO.read(new File("res/catGraphic.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        this.name = name;
        this.gene = new Gene(furMaxLevel, bellyMaxLevel, eyeMaxLevel, accessoriesMaxLevel);
        setFurColorName(gene.getFurTraitList().get(0));
        setBellyColorName(gene.getBellyTraitList().get(0));
        setEyeColorName(gene.getEyeTraitList().get(0));
        setAccesoriesName(gene.getAccessoriesTraitList().get(0));
    }

    public Cat(String name, String furTrait, String bellyTrait, String eyeTrait, String accessoriesTrait, int furMaxLevel, int bellyMaxLevel, int eyeMaxLevel, int accessoriesMaxLevel) {
        try {
            this.basicCatShape = ImageIO.read(new File("res/catGraphic.png"));
            this.currentCatShape = ImageIO.read(new File("res/catGraphic.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        this.name = name;
        this.gene = new Gene(furTrait, bellyTrait, eyeTrait, accessoriesTrait, furMaxLevel, bellyMaxLevel, eyeMaxLevel, accessoriesMaxLevel);
        setFurColorName(gene.getFurTraitList().get(0));
        setBellyColorName(gene.getBellyTraitList().get(0));
        setEyeColorName(gene.getEyeTraitList().get(0));
        setAccesoriesName(gene.getAccessoriesTraitList().get(0));
    }

    private void updatePicture() {
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
                if (r == 255 && g == 0 && b == 0) {
                    basicCatShape.setRGB(x, y, fur);
                } else if(g == 255 && r == 0 && b == 0) {
                    basicCatShape.setRGB(x, y, eye);
                } else if (b == 255 && g == 0 && r == 0) {
                    basicCatShape.setRGB(x, y, belly);
                }
            }
        }
        if (this.overlay != null) { 
            for (int y = 0; y < overlay.getHeight(); y++) {
                for (int x = 0; x < overlay.getWidth(); x++) {
                    int p = overlay.getRGB(x, y);
                    int a = (p>>24)&0xff;
                    // int red = (pixelRGB & 0x00ff0000) >> 16;
                    // int green = (pixelRGB & 0x00ff0000) >> 8;
                    // int blue = (pixelRGB & 0x00ff0000);
                    if (a != 0) {
                        basicCatShape.setRGB(x, y, p);
                    }
                }
            }
        }    
        this.currentCatShape = this.basicCatShape;
        try {
            this.basicCatShape = ImageIO.read(new File("res/catGraphic.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        exportImage();
    }

    private void exportImage() {
        String filepath = "res/cat/"+ name + ".png";
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
        updatePicture();
    }

    public void setBellyColor(int red, int green, int blue) {
        this.bellyRed = red;
        this.bellyBlue = blue;
        this.bellyGreen = green;
        updatePicture();
    }

    public void setEyeColor(int red, int green, int blue) {
        this.eyeRed = red;
        this.eyeBlue = blue;
        this.eyeGreen = green;
        updatePicture();
    }

    public void setFurColorName(String name) {
        this.furTrait = name;
        int red = gene.getFurColor(name).get(0);
        int green = gene.getFurColor(name).get(1);
        int blue = gene.getFurColor(name).get(2);

        setFurColor(red, green, blue);
    }

    public void setBellyColorName(String name) {
        this.bellyTrait = name;
        int red = gene.getBellyColor(name).get(0);
        int green = gene.getBellyColor(name).get(1);
        int blue = gene.getBellyColor(name).get(2);

        setBellyColor(red, green, blue);
    }

    public void setEyeColorName(String name) {
        this.eyeTrait = name;
        int red = gene.getEyeColor(name).get(0);
        int green = gene.getEyeColor(name).get(1);
        int blue = gene.getEyeColor(name).get(2);

        setEyeColor(red, green, blue);
    }

    public void setAccesoriesName(String name) {
        this.accessoriesTrait = name;
        if (this.accessoriesTrait != "None") {
            try {
                this.overlay = ImageIO.read(new File(gene.getAccessories(this.accessoriesTrait)));
            }  catch (IOException e) {
                System.out.println(e);
            }
        }
        updatePicture();
    }

    public void setRandomGene() {
        this.setFurColorName(gene.getRandomFurGene());
        this.setBellyColorName(gene.getRandomBellyGene());
        this.setEyeColorName(gene.getRandomEyeGene());
        this.setAccesoriesName(gene.getRandomAccessoriesGene());
    }

    public String getFilepath() {
        return this.currentCatShapeFilepath;
    }

    public String getFilepathKilt() {
        return this.currentCatShapeFilepath.substring(4);
    }

    public Gene getGene() {
        return this.gene;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Integer> getFurColor() {
        return new ArrayList<>(List.of(this.furRed, this.furGreen, this.furBlue));
    }

    public static void main(String[] args) throws Exception {
        Cat nevis = new Cat("nevis", 0, 0, 0, 2);
        Cat matthew = new Cat("matthew", "Onyx", "Daffodil", "Gold", "Bow", 0, 0,0, 2);
        System.out.println(nevis.getFilepath());
        System.out.println(matthew.getFilepath());  
    }


}