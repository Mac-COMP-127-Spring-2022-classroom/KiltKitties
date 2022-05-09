package cats;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Map.Entry;
import java.util.function.Predicate;

public class Market{
    public ArrayList<String> catNamesNotInUse = 
    new ArrayList<>(List.of("francesca", "michael", "jay", "marvin", "nevis", "tundra", "pinky", "princess",
     "prince", "benjamin", "lechat", "bubbles", "catniss", "tom", "cathereine", "olivier", "henry", "olivia"));
    private ArrayList<String> catNamesInUse = new ArrayList<>(); 
    private int money, baseBuyPrice, baseSellPrice, evolutionPrice;
    private Random rand = new Random();

    public Market(int money) {
        File dir = new File("res/cat");
        deleteDirectory(dir);
        File dir2 = new File("res/cat");
        System.out.println(dir2.exists());
        this.money = money;
        // this.baseBuyPrice = Math.round(money / 12);
        // this.baseSellPrice = Math.round(money / 8);
        // this.evolutionPrice = Math.round(money / 10);
        this.baseBuyPrice = 100;
        this.baseSellPrice = 100;
        this.evolutionPrice = 100;
    }

    private void deleteDirectory(File directory) {
        Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(Predicate.not(File::isDirectory))
                .forEach(File::delete);
    }

    public int priceCat(Cat cat) {
        int sellPrice = this.baseSellPrice;
        int multiplierFur = 100;
        int multiplierBelly = 100;
        int multiplierEye = 100;
        int multiplierAccessories = 100;
        int index = 0;
        Gene gene = cat.getGene();
        for (String trait: gene.getFurTraitList()) {
            multiplierFur = multiplierFur + getMultiplier(gene.getFurTraitLevel(trait), gene.getProbablity(index));
            // System.out.println(multiplierFur);
            index = index + 1;
        }
        index = 0;
        for (String trait: gene.getBellyTraitList()) {
            multiplierBelly = multiplierBelly + getMultiplier(gene.getBellyTraitLevel(trait), gene.getProbablity(index));
            // System.out.println(multiplierBelly);
            index = index + 1;
        }
        index = 0;
         for (String trait: gene.getEyeTraitList()) {
            multiplierEye = multiplierEye + getMultiplier(gene.getEyeTraitLevel(trait), gene.getProbablity(index));
            // System.out.println(multiplierEye);
            index = index + 1;
        }
        index = 0;
        for (String trait: gene.getAccessoriesTraitList()) {
            multiplierAccessories = multiplierAccessories + getMultiplier(gene.getAccessoriesTraitLevel(trait), gene.getProbablity(index));
            // System.out.println(multiplierAccessories);
            index = index + 1;
        }
        double finalMultiplier = multiplierFur * multiplierBelly * multiplierEye * multiplierAccessories / (100000000.0);
        // System.out.println(finalMultiplier);
        sellPrice = (int) Math.round(sellPrice * finalMultiplier);
        return sellPrice;
    }
    public int getMultiplier(int level, int probablility) {
        int multiplier = 0;
        // System.out.println(level);
        // System.out.println(probablility);
        if (level == 0) {
            multiplier = rand.nextInt((int) Math.round(Math.floor(probablility / 10)) + 1);
        } else if (level == 1) {
            multiplier = rand.nextInt((int) Math.round(Math.floor(probablility / 5)) + 1);
        } else if (level == 2) {
            multiplier = rand.nextInt((int) Math.round(Math.floor(probablility / 3)) + 1);
        } else if (level == 3) {
            multiplier = rand.nextInt((int) Math.round(Math.floor(probablility / 2)) + 1);
        } else {
            multiplier = rand.nextInt(probablility + 1);
        }
        // System.out.println(multiplier);
        return multiplier;
    }

    private int generateRandomLevel(){
        int seed = rand.nextInt(100);
        if (seed < 45) {
            return 0;
        } else if (seed < 70) {
            return 1;
        } else if (seed < 85) {
            return 2;
        } else if (seed < 95) {
            return 3;
        } else {
            return 4;
        }
    }

    private String pickRandomNames() {
        int index = rand.nextInt(catNamesNotInUse.size());
        String newName = catNamesNotInUse.get(index);
        catNamesInUse.add(newName);
        catNamesNotInUse.remove(index);
        return newName;
    }

    public void sellCat(Cat cat) {
        this.money += priceCat(cat);
        catNamesInUse.remove(catNamesInUse.indexOf(cat.getName()));
        catNamesNotInUse.add(cat.getName());
        new File(cat.getFilepath()).delete();
    }

    public Cat buyCat() {
        if (money - baseBuyPrice < 0) 
            return null;
        else {
            money = money - baseBuyPrice;
            String name = pickRandomNames();
            return new Cat(name, generateRandomLevel(), generateRandomLevel(), generateRandomLevel(), generateRandomLevel());
        }
    }

    public Cat evolve(Cat cat1, Cat cat2) {
        Gene geneGeneral = cat1.getGene();
        String furEvolvedTrait = new String();
        String bellyEvolvedTrait = new String();
        String eyeEvolvedTrait = new String();
        String accessoriesEvolvedTrait = new String();
        HashMap<String, Integer> furProbability = new HashMap<String, Integer>();
        HashMap<String, Integer> bellyProbability = new HashMap<String, Integer>();
        HashMap<String, Integer> eyeProbability = new HashMap<String, Integer>();
        HashMap<String, Integer> accessoriesProbability = new HashMap<String, Integer>();
        for (Cat cat: List.of(cat1, cat2)) {
            Gene gene = cat.getGene();
            int index = 0;
            for (String furTrait: gene.getFurTraitList()) {
                furProbability.put(furTrait, furProbability.getOrDefault(furTrait, 0) + gene.getProbablity(index));
                index = index + 1;
            } 
            index = 0;
            for (String bellyTrait: gene.getBellyTraitList()) {
                bellyProbability.put(bellyTrait, bellyProbability.getOrDefault(bellyTrait, 0) + gene.getProbablity(index));
                index = index + 1;
            } 
            index = 0;
            for (String eyeTrait: gene.getEyeTraitList()) {
                eyeProbability.put(eyeTrait, eyeProbability.getOrDefault(eyeTrait, 0) + gene.getProbablity(index));
                index = index + 1;
            } 
            index = 0;
            for (String accessoriesTrait: gene.getAccessoriesTraitList()) {
                accessoriesProbability.put(accessoriesTrait, accessoriesProbability.getOrDefault(accessoriesTrait, 0) + gene.getProbablity(index));
                index = index + 1;
            } 
        }
        // System.out.println(furProbability);
        // System.out.println(bellyProbability);
        // System.out.println(eyeProbability);
        // System.out.println(accessoriesProbability);
        int furGeneratedProbability = rand.nextInt(100) + 1;
        // System.out.println(furGeneratedProbability);
        for(Entry<String, Integer> entry: furProbability.entrySet()) {
            if (furGeneratedProbability > 0) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (furGeneratedProbability > value) {
                    furGeneratedProbability = furGeneratedProbability - value;
                } else {
                    furGeneratedProbability = furGeneratedProbability - value;
                    furEvolvedTrait = key;
                }
            }
        }
        int bellyGeneratedProbability = rand.nextInt(100) + 1;
        // System.out.println(bellyGeneratedProbability);
        for(Entry<String, Integer> entry: bellyProbability.entrySet()) {
            if (bellyGeneratedProbability > 0) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (bellyGeneratedProbability > value) {
                    bellyGeneratedProbability = bellyGeneratedProbability - value;
                } else {
                    bellyGeneratedProbability = bellyGeneratedProbability - value;
                    bellyEvolvedTrait = key;
                }
            }
        } 
        int eyeGeneratedProbability = rand.nextInt(100) + 1;
        // System.out.println(eyeGeneratedProbability);
        for(Entry<String, Integer> entry: eyeProbability.entrySet()) {
            if (eyeGeneratedProbability > 0) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (eyeGeneratedProbability > value) {
                    eyeGeneratedProbability = eyeGeneratedProbability - value;
                } else {
                    eyeGeneratedProbability = eyeGeneratedProbability - value;
                    eyeEvolvedTrait = key;
                }
            }
        } 
        int accessoriesGeneratedProbability = rand.nextInt(100) + 1;
        // System.out.println(accessoriesGeneratedProbability);
        for(Entry<String, Integer> entry: accessoriesProbability.entrySet()) {
            if (accessoriesGeneratedProbability > 0) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (accessoriesGeneratedProbability > value) {
                    accessoriesGeneratedProbability = accessoriesGeneratedProbability - value;
                } else {
                    accessoriesGeneratedProbability = accessoriesGeneratedProbability - value;
                    accessoriesEvolvedTrait = key;
                }
            }
        } 
        // System.out.println(furEvolvedTrait);
        // System.out.println(bellyEvolvedTrait);
        // System.out.println(eyeEvolvedTrait);
        // System.out.println(accessoriesEvolvedTrait);
        int furMaxLevel = Math.max(cat1.getGene().getFurMaxLevel(), cat2.getGene().getFurMaxLevel());
        int bellyMaxLevel = Math.max(cat1.getGene().getBellyMaxLevel(), cat2.getGene().getBellyMaxLevel());
        int eyeMaxLevel = Math.max(cat1.getGene().getEyeMaxLevel(), cat2.getGene().getEyeMaxLevel());
        int accessoriesMaxLevel = Math.max(cat1.getGene().getAccessoriesMaxLevel(), cat2.getGene().getAccessoriesMaxLevel());
        if (geneGeneral.getFurTraitLevel(furEvolvedTrait) < 4 && rand.nextInt(100) < 10) {
            // System.out.println("Fur Evolved");
            furEvolvedTrait = geneGeneral.getRandomFurGeneLevel(geneGeneral.getFurTraitLevel(furEvolvedTrait) + 1);
        }
        if (geneGeneral.getBellyTraitLevel(bellyEvolvedTrait) < 4 && rand.nextInt(100) < 10) {
            // System.out.println("Belly Evolved");
            bellyEvolvedTrait = geneGeneral.getRandomBellyGeneLevel(geneGeneral.getBellyTraitLevel(bellyEvolvedTrait) + 1);
        }
        if (geneGeneral.getEyeTraitLevel(eyeEvolvedTrait) < 4 && rand.nextInt(100) < 10) {
            // System.out.println("Eye Evolved");
            eyeEvolvedTrait = geneGeneral.getRandomEyeGeneLevel(geneGeneral.getEyeTraitLevel(eyeEvolvedTrait) + 1);
        }
        if (geneGeneral.getAccessoriesTraitLevel(accessoriesEvolvedTrait) < 4 && rand.nextInt(100) < 10) {
            // System.out.println("Accessories Evolved");
            if (geneGeneral.getAccessoriesTraitLevel(accessoriesEvolvedTrait) != 0) {
                accessoriesEvolvedTrait = geneGeneral.getRandomAccessoriesGeneLevel(geneGeneral.getAccessoriesTraitLevel(accessoriesEvolvedTrait) + 1);
            } else if(geneGeneral.getAccessoriesTraitLevel(accessoriesEvolvedTrait) != 0) {
                accessoriesEvolvedTrait = geneGeneral.getRandomAccessoriesGeneLevel(2);
            }
        }
        // System.out.println(furEvolvedTrait);
        // System.out.println(bellyEvolvedTrait);
        // System.out.println(eyeEvolvedTrait);
        // System.out.println(accessoriesEvolvedTrait);
        String name = pickRandomNames();
        return new Cat(name, furEvolvedTrait, bellyEvolvedTrait, eyeEvolvedTrait, accessoriesEvolvedTrait, furMaxLevel, bellyMaxLevel, eyeMaxLevel, accessoriesMaxLevel);
    }

    public int getMoney() {
        return money;
    }

    public Cat evolutionCat(Cat cat1, Cat cat2) {
        if (money - evolutionPrice < 0) {
            return null;
        } else {
            money = money - evolutionPrice;
            Cat cat = evolve(cat1, cat2);
            return cat;
        }
    }
    public static void main(String[] args) {
        // change back to 1000
        Market market = new Market(1000);
        // System.out.println(market.getMoney());
        Cat cat1 = new Cat("maca", 3, 3, 3, 3);
        Cat cat2 = new Cat("lester", 2, 2, 1, 4);
        market.evolve(cat1, cat2);
        // System.out.println(market.catNamesInUse);
        // System.out.println(market.catNamesNotInUse);
        market.sellCat(cat1);
        // System.out.println(market.getMoney());
        market.buyCat();
        market.buyCat();
        // System.out.println(market.getMoney());
        // System.out.println(market.catNamesInUse);
        // System.out.println(market.catNamesNotInUse);
    }
}
