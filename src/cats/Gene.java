package cats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Gene {
    private LinkedHashMap<String, List<Integer>> furGene = new LinkedHashMap<String, List<Integer>>();
    private LinkedHashMap<String, List<Integer>> bellyGene = new LinkedHashMap<String, List<Integer>>();
    private LinkedHashMap<String, List<Integer>> eyeGene = new LinkedHashMap<String, List<Integer>>();
    private LinkedHashMap<String, String> accessoriesGene = new LinkedHashMap<String, String>();

    private LinkedHashMap<Integer, String> furTraits = new LinkedHashMap<Integer, String>();
    private LinkedHashMap<Integer, String> bellyTraits = new LinkedHashMap<Integer, String>();
    private LinkedHashMap<Integer, String> eyeTraits = new LinkedHashMap<Integer, String>();
    private LinkedHashMap<Integer, String> accessoriesTraits = new LinkedHashMap<Integer, String>();


    
    public Gene(int furMaxLevel, int bellyMaxLevel, int eyeMaxLevel, int accessoriesMaxLevel) {
        this.furGene.put("ShadowGrey", List.of(177, 177, 190));
        this.furGene.put("Salmon", List.of(244, 167, 146));
        this.furGene.put("Meowgarine", List.of(252, 252, 149));
        this.furGene.put("OrangeSoda", List.of(247, 188, 86));
        this.furGene.put("CottonCandy", List.of(236, 209, 235));
        this.furGene.put("Mauveover", List.of(222, 208, 238));
        this.furGene.put("AquaMarine", List.of(222, 208, 238));
        this.furGene.put("Nachocheez", List.of(252, 218, 234));
        this.furGene.put("HarbourFog", List.of(175, 180, 213));
        this.furGene.put("Cinderella", List.of(90, 176, 241));
        this.furGene.put("GreyMatter", List.of(209, 218, 233));
        this.furGene.put("Tundra", List.of(219, 204, 191));
        this.furGene.put("Brownies", List.of(183, 134, 98));
        this.furGene.put("DragonFruit", List.of(236, 121, 242));
        this.furGene.put("Hintomint", List.of(208, 234, 210));
        this.furGene.put("BananaCream", List.of(248, 248, 224));
        this.furGene.put("CloudWhite", List.of(255, 255, 255));
        this.furGene.put("CornFlower", List.of(117, 146, 252));
        this.furGene.put("OldLace", List.of(255, 235, 233));
        this.furGene.put("Koala", List.of(133, 130, 138));
        this.furGene.put("Lavender", List.of(188, 153, 255));
        this.furGene.put("Glacier", List.of(204, 255, 255));
        this.furGene.put("RedVelvet", List.of(247, 114, 114));
        this.furGene.put("Verigidis", List.of(115, 255, 195));
        this.furGene.put("Icicle", List.of(197, 226, 255));
        this.furGene.put("Onyx", List.of(66, 65, 76));
        this.furGene.put("Hyacinth", List.of(164, 93, 226));
        this.furGene.put("Martian", List.of(164, 255, 111));
        this.furGene.put("HotCocoa", List.of(94, 74, 71));
        this.furGene.put("Shamrock", List.of(80, 200, 120));
        this.furGene.put("Firstblush", List.of(252, 208, 248));
    
        this.bellyGene.put("Belleblue", List.of(175, 208, 247));
        this.bellyGene.put("Sandalwood", List.of(184, 145, 108));
        this.bellyGene.put("Peach", List.of(249, 207, 173));
        this.bellyGene.put("Icy", List.of(238, 248, 248));
        this.bellyGene.put("GraniteGray", List.of(177, 174, 185));
        this.bellyGene.put("CashewMilk", List.of(249, 239, 239));
        this.bellyGene.put("KittenCream", List.of(247, 235, 218));
        this.bellyGene.put("EmeraldGreen", List.of(139, 225, 121));
        this.bellyGene.put("Shale", List.of(88, 86, 102));
        this.bellyGene.put("PurpleHaze", List.of(218, 214, 225));
        this.bellyGene.put("Hanauma", List.of(122, 204, 181));
        this.bellyGene.put("Kalahari", List.of(255, 207, 138));
        this.bellyGene.put("AzaleaBlush", List.of(255, 204, 216));
        this.bellyGene.put("MissMuffet", List.of(244, 179, 240));
        this.bellyGene.put("MorningGlory", List.of(136, 124, 255));
        this.bellyGene.put("Frosting", List.of(255, 220, 230));
        this.bellyGene.put("Daffodil", List.of(255, 240, 159));
        this.bellyGene.put("Flamingo", List.of(236, 135, 186));
        this.bellyGene.put("Buttercup", List.of(244, 230, 93));
        this.bellyGene.put("Bloodred", List.of(255, 122, 122));
        this.bellyGene.put("Atlantis", List.of(42, 127, 150));
        this.bellyGene.put("Summer", List.of(203, 176, 255));
        this.bellyGene.put("Periwinkle", List.of(202, 202, 255));
        this.bellyGene.put("PatrickStar", List.of(255, 173, 151));
        this.bellyGene.put("Seafoam", List.of(158, 238, 197));
        this.bellyGene.put("Cobalt", List.of(82, 98, 219));
        this.bellyGene.put("MallowFlower", List.of(193, 112, 177));
        this.bellyGene.put("MintMacaron", List.of(176, 241, 244));
        this.bellyGene.put("Sully", List.of(112, 249, 249));
        this.bellyGene.put("FallSpice", List.of(255, 147, 49));
        this.bellyGene.put("DreamBoat", List.of(253, 108, 213));
    
        this.eyeGene.put("ThunderGrey", List.of(238, 233, 232));
        this.eyeGene.put("Gold", List.of(250, 244, 207));
        this.eyeGene.put("Topaz", List.of(209, 238, 235));
        this.eyeGene.put("MintGreen", List.of(205, 245, 212));
        this.eyeGene.put("Isotope", List.of(239, 253, 202));
        this.eyeGene.put("Sizzurp", List.of(223, 223, 250));
        this.eyeGene.put("Chestnut", List.of(239, 225, 218));
        this.eyeGene.put("Strawberry", List.of(255, 224, 229));
        this.eyeGene.put("Sapphire", List.of(211, 232, 255));
        this.eyeGene.put("Forgetmenot", List.of(220, 235, 252));
        this.eyeGene.put("Dahlia", List.of(230, 234, 253));
        this.eyeGene.put("CoralSunrise", List.of(253, 233, 228));
        this.eyeGene.put("Olive", List.of(236, 244, 224));
        this.eyeGene.put("Dorid", List.of(250, 238, 250));
        this.eyeGene.put("Parakeet", List.of(229, 243, 226));
        this.eyeGene.put("Cyan", List.of(197, 238, 250));
        this.eyeGene.put("Pumpkin", List.of(250, 225, 202));
        this.eyeGene.put("LimeGreen", List.of(217, 245, 203));
        this.eyeGene.put("Bridesmaid", List.of(255, 213, 229));
        this.eyeGene.put("Bubblegum", List.of(250, 223, 244));
        this.eyeGene.put("Twilight", List.of(237, 226, 245));
        this.eyeGene.put("PaleJade", List.of(231, 241, 237));
        this.eyeGene.put("Pinefresh", List.of(219, 240, 208));
        this.eyeGene.put("Eclipse", List.of(229, 231, 239));
        this.eyeGene.put("BabyPuke", List.of(239, 241, 224));
        this.eyeGene.put("DownByTheDrain", List.of(205, 229, 209));
        this.eyeGene.put("AutumnMoon", List.of(253, 243, 224));
        this.eyeGene.put("Oasis", List.of(230, 250, 243));
        this.eyeGene.put("Gemini", List.of(239, 225, 218));
        this.eyeGene.put("Dioscuri", List.of(236, 244, 224));
        this.eyeGene.put("Kaleidoscope", List.of(197, 238, 250));

        this.accessoriesGene.put("None", "");
        this.accessoriesGene.put("Bow", "res/accessories/bowAccessory.png");
        this.accessoriesGene.put("BowTie", "res/accessories/bowtieAccessory.png");
        this.accessoriesGene.put("Collar", "res/accessories/collarAccessory.png");
        this.accessoriesGene.put("Glasses", "res/accessories/glassesAccessory.png");
        this.accessoriesGene.put("Hat", "res/accessories/hatAccessory.png");
        this.accessoriesGene.put("Mask", "res/accessories/maskAccessory.png");
        this.accessoriesGene.put("Mustache", "res/accessories/mustacheAccessory.png");
        this.accessoriesGene.put("Spots", "res/accessories/spots.png");
        this.accessoriesGene.put("Stripes", "res/accessories/stripes.png");

        
        furTraits.put(35, "");
        furTraits.put(10, "");
        furTraits.put(3, "");
        furTraits.put(2,"");
    
        bellyTraits.put(35, "");
        bellyTraits.put(10, "");
        bellyTraits.put(3, "");
        bellyTraits.put(2,"");
    
        eyeTraits.put(35, "");
        eyeTraits.put(10, "");
        eyeTraits.put(3, "");
        eyeTraits.put(2,"");

        accessoriesTraits.put(35, "");
        accessoriesTraits.put(10, "");
        accessoriesTraits.put(3, "");
        accessoriesTraits.put(2,"");

        Random rand = new Random();
        List<Integer> levelList = List.of(2, 3, 10, 35);
        for (Integer level: levelList) {
            furTraits.put(level, getRandomFurGeneLevel(rand.nextInt(furMaxLevel + 1)));
        }
        for (Integer level: levelList) {
            bellyTraits.put(level, getRandomBellyGeneLevel(rand.nextInt(bellyMaxLevel + 1)));
        }
        for (Integer level: levelList) {
            eyeTraits.put(level, getRandomEyeGeneLevel(rand.nextInt(eyeMaxLevel + 1)));
        }
        for (Integer level: levelList) {
            accessoriesTraits.put(level, getRandomAccessoriesGeneLevel(rand.nextInt(accessoriesMaxLevel + 1)));
        }
        System.out.println(furTraits);
        System.out.println(bellyTraits);
        System.out.println(eyeTraits);
        System.out.println(accessoriesTraits);
    }

    public List<Integer> getFurColor(String name) {
        return furGene.get(name);
    }

    public List<Integer> getBellyColor(String name) {
        return bellyGene.get(name);
    }

    public List<Integer> getEyeColor(String name) {
        return eyeGene.get(name);
    }

    public String getAccessories(String name) {
        return accessoriesGene.get(name);
    }

    public String getRandomFurGene() {
        Random rand = new Random();
        ArrayList<String> keys = new ArrayList<>(furGene.keySet()); 
        return keys.get(rand.nextInt(keys.size()));
    }

    public String getRandomFurGeneLevel(int level) {
        Random rand = new Random();
        ArrayList<String> keys = new ArrayList<>(furGene.keySet()); 
        String returnString = new String();
        if (level == 0) {
            returnString = keys.get(rand.nextInt(16));
        } else if (level == 1) {
            returnString = keys.get(16 + rand.nextInt(8));
        } else if (level == 2) {
            returnString = keys.get(24 + rand.nextInt(4));
        } else if (level == 3) {
            returnString = keys.get(28 + rand.nextInt(2));
        } else if (level == 4) {
            returnString = keys.get(30);
        }
        return returnString;
    }


    public String getRandomBellyGene() {
        Random rand = new Random();
        ArrayList<String> keys = new ArrayList<>(bellyGene.keySet()); 
        return keys.get(rand.nextInt(keys.size()));
    }

    public String getRandomBellyGeneLevel(int level) {
        Random rand = new Random();
        ArrayList<String> keys = new ArrayList<>(bellyGene.keySet()); 
        String returnString = new String();
        if (level == 0) {
            returnString = keys.get(rand.nextInt(16));
        } else if (level == 1) {
            returnString = keys.get(16 + rand.nextInt(8));
        } else if (level == 2) {
            returnString = keys.get(24 + rand.nextInt(4));
        } else if (level == 3) {
            returnString = keys.get(28 + rand.nextInt(2));
        } else if (level == 4) {
            returnString = keys.get(30);
        }
        return returnString;
    }

    public String getRandomEyeGene() {
        Random rand = new Random();
        ArrayList<String> keys = new ArrayList<>(eyeGene.keySet()); 
        return keys.get(rand.nextInt(keys.size()));
    }

    public String getRandomEyeGeneLevel(int level) {
        Random rand = new Random();
        ArrayList<String> keys = new ArrayList<>(eyeGene.keySet()); 
        String returnString = new String();
        if (level == 0) {
            returnString = keys.get(rand.nextInt(16));
        } else if (level == 1) {
            returnString = keys.get(16 + rand.nextInt(8));
        } else if (level == 2) {
            returnString = keys.get(24 + rand.nextInt(4));
        } else if (level == 3) {
            returnString = keys.get(28 + rand.nextInt(2));
        } else if (level == 4) {
            returnString = keys.get(30);
        }
        return returnString;
    }

    public String getRandomAccessoriesGene() {
        Random rand = new Random();
        ArrayList<String> keys = new ArrayList<>(accessoriesGene.keySet()); 
        return keys.get(rand.nextInt(keys.size()));
    }

    public String getRandomAccessoriesGeneLevel(int level) {
        Random rand = new Random();
        ArrayList<String> keys = new ArrayList<>(accessoriesGene.keySet()); 
        String returnString = new String();
        if (level == 0) {
            returnString = keys.get(0);
        } else if (level == 1) {
            returnString = keys.get(1 + rand.nextInt(4));
        } else if (level == 2) {
            returnString = keys.get(5 + rand.nextInt(3));
        } else if (level == 3) {
            returnString = keys.get(8 + rand.nextInt(2));
        }
        return returnString;
    }

    public ArrayList<String> getFurTraitList() {
        ArrayList<String> keys = new ArrayList<>(furTraits.values());
        System.out.println(keys);
        return keys;
    }

    public ArrayList<String> getBellyTraitList() {
        ArrayList<String> keys = new ArrayList<>(bellyTraits.values());
        System.out.println(keys);
        return keys;
    }

    public ArrayList<String> getEyeTraitList() {
        ArrayList<String> keys = new ArrayList<>(eyeTraits.values());
        System.out.println(keys);
        return keys;
    }

    public ArrayList<String> getAccessoriesTraitList() {
        ArrayList<String> keys = new ArrayList<>(accessoriesTraits.values());
        System.out.println(keys);
        return keys;
    }
}
