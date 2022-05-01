package cats;

import java.util.Random;

public class Market {
    private static final int PRICE = 100;
    private int money, baseBuyPrice, baseSellPrice, evolutionPrice;
    private Random rand = new Random();

    public Market(int money) {
        this.money = money;
        this.baseBuyPrice = Math.round(money / 5);
        this.baseSellPrice = Math.round(money / 8);
        this.evolutionPrice = Math.round(money / 10);
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
            System.out.println(multiplierFur);
            index = index + 1;
        }
        index = 0;
        for (String trait: gene.getBellyTraitList()) {
            multiplierBelly = multiplierBelly + getMultiplier(gene.getBellyTraitLevel(trait), gene.getProbablity(index));
            System.out.println(multiplierBelly);
            index = index + 1;
        }
        index = 0;
         for (String trait: gene.getEyeTraitList()) {
            multiplierEye = multiplierEye + getMultiplier(gene.getEyeTraitLevel(trait), gene.getProbablity(index));
            System.out.println(multiplierEye);
            index = index + 1;
        }
        index = 0;
        for (String trait: gene.getAccessoriesTraitList()) {
            multiplierAccessories = multiplierAccessories + getMultiplier(gene.getAccessoriesTraitLevel(trait), gene.getProbablity(index));
            System.out.println(multiplierAccessories);
            index = index + 1;
        }
        double finalMultiplier = multiplierFur * multiplierBelly * multiplierEye * multiplierAccessories / (100000000.0);
        System.out.println(finalMultiplier);
        sellPrice = (int) Math.round(sellPrice * finalMultiplier);
        return sellPrice;
    }
    public int getMultiplier(int level, int probablility) {
        int multiplier = 0;
        System.out.println(level);
        System.out.println(probablility);
        if (level == 0) {
            multiplier = rand.nextInt( (int) Math.round(Math.floor(probablility / 10)) + 1);
        } else if (level == 1) {
            multiplier = rand.nextInt((int) Math.round(Math.floor(probablility / 5)) + 1);
        } else if (level == 2) {
            multiplier = rand.nextInt((int) Math.round(Math.floor(probablility / 3)) + 1);
        } else if (level == 3) {
            multiplier = rand.nextInt((int) Math.round(Math.floor(probablility / 2)) + 1);
        } else {
            multiplier = rand.nextInt(probablility + 1);
        }
        System.out.println(multiplier);
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

    public void sellCat(Cat cat) {
        this.money += priceCat(cat);
    }

    public Cat buyCat(String name) {
        if (money - baseBuyPrice < 0) 
            return null;
        else {
            money = money - baseBuyPrice;
            return new Cat(name, generateRandomLevel(), generateRandomLevel(), generateRandomLevel(), generateRandomLevel());
        }
    }

    public int getMoney() {
        return money;
    }
    public static void main(String[] args) {
        Market market = new Market(1000);
        System.out.println(market.getMoney());
        market.sellCat(new Cat("Tundra", 3, 3, 3, 3));
        System.out.println(market.getMoney());
        market.buyCat("Henry");
        market.buyCat("Michael");
        System.out.println(market.getMoney());

    }
}
