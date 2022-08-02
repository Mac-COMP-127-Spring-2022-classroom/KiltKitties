package cats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * A cat that can be bought, sold, as well as used to evolve to create new cats
 */
public class Cat {
    private BufferedImage basicCatShape, currentCatShape, overlay;
    private CustomImage currentCatImage;
    private String name;
    private String currentCatShapeFilepath;
    private int furRed, furGreen, furBlue;
    private int eyeRed, eyeGreen, eyeBlue;
    private int bellyRed, bellyGreen, bellyBlue;
    private Gene gene;
    private String furTrait, bellyTrait, eyeTrait, accessoriesTrait;
    private List<String> shownTraits;


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
        
        String shownFur = gene.getFurTraitList().get(0);
        String shownBelly = gene.getBellyTraitList().get(0);
        String shownEye = gene.getEyeTraitList().get(0);
        String shownAccessories = gene.getAccessoriesTraitList().get(0);

        setFurColorName(shownFur);
        setBellyColorName(shownBelly);
        setEyeColorName(shownEye);
        setAccesoriesName(shownAccessories);
    }
    /**
     * Update cats colors and patterns
     */
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

    /**
     * Export images for each cats
     */
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
        this.currentCatImage = new CustomImage(currentCatShape);
    }

    /**
     * Return a image of the cat
     * @return current image of the cat
     */
    public CustomImage getImage() {
        return currentCatImage;
    }

    /**
     * Setting cat fur with RGB
     * @param red
     * @param green
     * @param blue
     */
    private void setFurColor(int red, int green, int blue) {
        this.furRed = red;
        this.furBlue = blue;
        this.furGreen = green;
        updatePicture();
    }

    /**
     * Setting belly color with RGB
     * @param red
     * @param green
     * @param blue
     */
    private void setBellyColor(int red, int green, int blue) {
        this.bellyRed = red;
        this.bellyBlue = blue;
        this.bellyGreen = green;
        updatePicture();
    }

    /**
     * Settingn eye color with RGB
     * @param red
     * @param green
     * @param blue
     */
    private void setEyeColor(int red, int green, int blue) {
        this.eyeRed = red;
        this.eyeBlue = blue;
        this.eyeGreen = green;
        updatePicture();
    }

    /**
     * Setting fur color with a color name
     * @param name name of the color
     */
    private void setFurColorName(String name) {
        this.furTrait = name;
        int red = gene.getFurColor(name).get(0);
        int green = gene.getFurColor(name).get(1);
        int blue = gene.getFurColor(name).get(2);

        setFurColor(red, green, blue);
    }

    /**
     * Setting belly color with a color name
     * @param name name of the color
     */
    private void setBellyColorName(String name) {
        this.bellyTrait = name;
        int red = gene.getBellyColor(name).get(0);
        int green = gene.getBellyColor(name).get(1);
        int blue = gene.getBellyColor(name).get(2);

        setBellyColor(red, green, blue);
    }

    /**
     * Setting eye color with a color name
     * @param name name of the color
     */
    private void setEyeColorName(String name) {
        this.eyeTrait = name;
        int red = gene.getEyeColor(name).get(0);
        int green = gene.getEyeColor(name).get(1);
        int blue = gene.getEyeColor(name).get(2);

        setEyeColor(red, green, blue);
    }

    /**
     * Setting accesories with a name
     * @param name name of the accessories
     */
    private void setAccesoriesName(String name) {
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

    /**
     * Get filepath of the image
     * @return filepath
     */
    public String getFilepath() {
        return this.currentCatShapeFilepath;
    }

    /**
     * Filepath of the cat with kilt graphics
     * @return filepath for kilt graphics
     */
    public String getFilepathKilt() {
        return this.currentCatShapeFilepath.substring(4);
    }

    /**
     * Get the gene of the cat
     * @return the gene of the cat
     */
    public Gene getGene() {
        Gene catGene = this.gene;
        return catGene;
    }

    /**
     * Get the name of the cat
     * @return get the name of the cat
     */
    public String getName() {
        String catName = this.name;
        return catName;
    }

    /**
     * Return the rgb value of fur
     */
    public ArrayList<Integer> getFurColor() {
        return new ArrayList<>(List.of(this.furRed, this.furGreen, this.furBlue));
    }

    /** 
     * Return the rgb value of the belly
     */
    public ArrayList<Integer> getBellyColor() {
        return new ArrayList<>(List.of(this.bellyRed, this.bellyGreen, this.bellyBlue));
    }

    public static void main(String[] args) throws Exception {
        Cat nevis = new Cat("nevis", 0, 0, 0, 2);
        Cat matthew = new Cat("matthew", "Onyx", "Daffodil", "Gold", "Bow", 0, 0,0, 2);
        System.out.println(nevis.getFilepath());
        System.out.println(matthew.getFilepath());  
    }
}