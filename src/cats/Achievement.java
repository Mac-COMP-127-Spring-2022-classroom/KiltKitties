package cats;

import java.util.List;
// import cats.Cat;

public class Achievement {
    private static List<String> uncompleted;
    private static List<String> completed;
    private static List<String> accessoryTraits = List.of(
        "None",
        "Bow",
        "BowTie",
        "Collar",
        "Glasses",
        "Hat",
        "Mask",
        "Mustache",
        "Spots",
        "Stripes"
    );
    private static List<String> bellyTraits = List.of(
        "Belleblue",
        "Sandalwood",
        "Peach",
        "Icy",
        "GraniteGray",
        "CashewMilk",
        "KittenCream",
        "EmeraldGreen",
        "Shale",
        "PurpleHaze",
        "Hanauma",
        "Kalahari",
        "AzaleaBlush",
        "MissMuffet",
        "MorningGlory",
        "Frosting",
        "Daffodil",
        "Flamingo",
        "Buttercup",
        "Bloodred",
        "Atlantis",
        "Summer",
        "Periwinkle",
        "PatrickStar",
        "Seafoam",
        "Cobalt",
        "MallowFlower",
        "MintMacaron",
        "Sully",
        "FallSpice",
        "DreamBoat"
    );
    private static List<String> eyeTraits = List.of(
        "ThunderGrey",
        "Gold",
        "Topaz",
        "MintGreen",
        "Isotope",
        "Sizzurp",
        "Chestnut",
        "Strawberry",
        "Sapphire",
        "Forgetmenot",
        "Dahlia",
        "CoralSunrise",
        "Olive",
        "Dorid",
        "Parakeet",
        "Cyan",
        "Pumpkin",
        "LimeGreen",
        "Bridesmaid",
        "Bubblegum",
        "Twilight",
        "PaleJade",
        "Pinefresh",
        "Eclipse",
        "BabyPuke",
        "DownByTheDrain",
        "AutumnMoon",
        "Oasis",
        "Gemini",
        "Dioscuri",
        "Kaleidoscope"
    );
    private static List<String> furTraits = List.of(
        "ShadowGrey",
        "Salmon",
        "Meowgarine",
        "OrangeSoda",
        "CottonCandy",
        "Mauveover",
        "AquaMarine",
        "Nachocheez",
        "HarbourFog",
        "Cinderella",
        "GreyMatter",
        "Tundra",
        "Brownies",
        "DragonFruit",
        "Hintomint",
        "BananaCream",
        "CloudWhite",
        "CornFlower",
        "OldLace",
        "Koala",
        "Lavender",
        "Glacier",
        "RedVelvet",
        "Verigidis",
        "Icicle",
        "Onyx",
        "Hyacinth",
        "Martian",
        "HotCocoa",
        "Shamrock",
        "Firstblush"
    );
    private static List<String> money = List.of(
        "1100",
        "1500",
        "2000",
        "5000",
        "10000"
    );

    public static List<String> getCompleted() {
        return completed;
    }

    public void setCompleted(List<String> completed) {
        Achievement.completed = completed;
    }

    public static List<String> getUncompleted() {
        return uncompleted;
    }

    public static void setUncompleted(List<String> uncompleted) {
        Achievement.uncompleted = uncompleted;
    }

    public static List<String> getAccessoryTraits() {
        return accessoryTraits;
    }

    public static List<String> getBellyTraits() {
        return bellyTraits;
    }

    public static List<String> getEyeTraits() {
        return eyeTraits;
    }

    public static List<String> getFurTraits() {
        return furTraits;
    }

    public static List<String> getMoneyAcheivements() {
        return money;
    }

    public static void setUpUncompleted(){
        /*
        Creates a list of all incomplete achievements.
        */

        List<String> uncompleted = getUncompleted();
        uncompleted.addAll(getAccessoryTraits());
        uncompleted.addAll(getBellyTraits());
        uncompleted.addAll(getEyeTraits());
        uncompleted.addAll(getFurTraits());
        uncompleted.addAll(getMoneyAcheivements());
        setUncompleted(uncompleted);
    }

    // public static void updateTraitsAchievementLists(Cat newCat){
    //     for (String achievement : getUncompleted()) {
    //         if(newCat.getShownTraits().contains(achievement)){
    //             getUncompleted().remove(achievement);
    //             getCompleted().add(achievement);
    //         }
    //     }
    // }
}
