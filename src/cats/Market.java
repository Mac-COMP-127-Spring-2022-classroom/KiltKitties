package cats;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Map.Entry;
import java.util.function.Predicate;

/**
 * Handle user interaction with cats, including buying, selling, and evolving cats
 */
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
        this.baseBuyPrice = 100;
        this.baseSellPrice = 80;
        this.evolutionPrice = 100;
    }

    /**
     * Clear cats before running
     * @param directory the cat directory
     */
    private void deleteDirectory(File directory) {
        Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(Predicate.not(File::isDirectory))
                .forEach(File::delete);
    }

    /**
     * Get the price of a cat
     * @param cat the cat concerned
     * @return the value of it
     */
    private int priceCat(Cat cat) {
        int sellPrice = this.baseSellPrice;
        int multiplierFur = 100;
        int multiplierBelly = 100;
        int multiplierEye = 100;
        int multiplierAccessories = 100;
        int index = 0;
        Gene gene = cat.getGene();
        for (String trait: gene.getFurTraitList()) {
            multiplierFur = multiplierFur + getMultiplier(gene.getFurTraitLevel(trait), gene.getProbablity(index));
            index = index + 1;
        }
        index = 0;
        for (String trait: gene.getBellyTraitList()) {
            multiplierBelly = multiplierBelly + getMultiplier(gene.getBellyTraitLevel(trait), gene.getProbablity(index));
            index = index + 1;
        }
        index = 0;
         for (String trait: gene.getEyeTraitList()) {
            multiplierEye = multiplierEye + getMultiplier(gene.getEyeTraitLevel(trait), gene.getProbablity(index));
            index = index + 1;
        }
        index = 0;
        for (String trait: gene.getAccessoriesTraitList()) {
            multiplierAccessories = multiplierAccessories + getMultiplier(gene.getAccessoriesTraitLevel(trait), gene.getProbablity(index));
            index = index + 1;
        }
        double finalMultiplier = multiplierFur * multiplierBelly * multiplierEye * multiplierAccessories / (100000000.0);
        sellPrice = (int) Math.round(sellPrice * finalMultiplier);
        return sellPrice;
    }

    /**
     * Generating multipliers to value the cats
     * @param level the level of the trait
     * @param probablility the probability of the trait being passed on
     * @return the multiplier
     */
    private int getMultiplier(int level, int probablility) {
        int multiplier = 0;
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
        return multiplier;
    }

    /**
     * Get a random level
     * @return random number from 0 to 3
     */
    private int generateRandomLevel(){
        int seed = rand.nextInt(100);
        if (seed < 55) {
            return 0;
        } else if (seed < 80) {
            return 1;
        } else if (seed < 90) {
            return 2;
        } else if (seed < 95) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * Pick a random name
     * @return the new name
     */
    private String pickRandomNames() {
        int index = rand.nextInt(catNamesNotInUse.size());
        String newName = catNamesNotInUse.get(index);
        catNamesInUse.add(newName);
        catNamesNotInUse.remove(index);
        return newName;
    }

    /**
     * Sell a cat, and gain the money
     * @param cat a cat being sold
     */
    public void sellCat(Cat cat) {
        this.money += priceCat(cat);
        catNamesInUse.remove(catNamesInUse.indexOf(cat.getName()));
        catNamesNotInUse.add(cat.getName());
        new File(cat.getFilepath()).delete();
    }

    /**
     * Buy a cat, dealing with money and names
     * @return
     */
    public Cat buyCat() {
        if (money - baseBuyPrice < 0) 
            return null;
        else {
            money = money - baseBuyPrice;
            String name = pickRandomNames();
            return new Cat(name, generateRandomLevel(), generateRandomLevel(), generateRandomLevel(), generateRandomLevel());
        }
    }

    /**
     * Evolution of the cats
     * @param cat1 first cat being used
     * @param cat2 second cat being used
     * @return a new cat from those 2
     */
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
        int furGeneratedProbability = rand.nextInt(100) + 1;
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
        int furMaxLevel = Math.max(cat1.getGene().getFurMaxLevel(), cat2.getGene().getFurMaxLevel());
        int bellyMaxLevel = Math.max(cat1.getGene().getBellyMaxLevel(), cat2.getGene().getBellyMaxLevel());
        int eyeMaxLevel = Math.max(cat1.getGene().getEyeMaxLevel(), cat2.getGene().getEyeMaxLevel());
        int accessoriesMaxLevel = Math.max(cat1.getGene().getAccessoriesMaxLevel(), cat2.getGene().getAccessoriesMaxLevel());
        if (geneGeneral.getFurTraitLevel(furEvolvedTrait) < 4 && rand.nextInt(100) < 3) {
            furEvolvedTrait = geneGeneral.getRandomFurGeneLevel(geneGeneral.getFurTraitLevel(furEvolvedTrait) + 1);
        }
        if (geneGeneral.getBellyTraitLevel(bellyEvolvedTrait) < 4 && rand.nextInt(100) < 3) {
            bellyEvolvedTrait = geneGeneral.getRandomBellyGeneLevel(geneGeneral.getBellyTraitLevel(bellyEvolvedTrait) + 1);
        }
        if (geneGeneral.getEyeTraitLevel(eyeEvolvedTrait) < 4 && rand.nextInt(100) < 3) {
            eyeEvolvedTrait = geneGeneral.getRandomEyeGeneLevel(geneGeneral.getEyeTraitLevel(eyeEvolvedTrait) + 1);
        }
        if (geneGeneral.getAccessoriesTraitLevel(accessoriesEvolvedTrait) < 4 && rand.nextInt(100) < 3) {
            if (geneGeneral.getAccessoriesTraitLevel(accessoriesEvolvedTrait) != 0) {
                accessoriesEvolvedTrait = geneGeneral.getRandomAccessoriesGeneLevel(geneGeneral.getAccessoriesTraitLevel(accessoriesEvolvedTrait) + 1);
            } else if(geneGeneral.getAccessoriesTraitLevel(accessoriesEvolvedTrait) != 0) {
                accessoriesEvolvedTrait = geneGeneral.getRandomAccessoriesGeneLevel(2);
            }
        }
        String name = pickRandomNames();
        return new Cat(name, furEvolvedTrait, bellyEvolvedTrait, eyeEvolvedTrait, accessoriesEvolvedTrait, furMaxLevel, bellyMaxLevel, eyeMaxLevel, accessoriesMaxLevel);
    }

    /**
     * Return the amount of money
     * @return the money
     */
    public int getMoney() {
        int returnMoney = money;
        return returnMoney;
    }

    /**
     * Evolving cat, dealing with money
     * @param cat1 cat number 1
     * @param cat2 cat number 2
     * @return
     */
    public Cat evolutionCat(Cat cat1, Cat cat2) {
        if (money - evolutionPrice < 0) {
            return null;
        } else {
            money = money - evolutionPrice;
            Cat cat = evolve(cat1, cat2);
            return cat;
        }
    }
}
