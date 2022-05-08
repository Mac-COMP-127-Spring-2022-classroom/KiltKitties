package cats;

import java.util.List;

public class Achievement {
    private List<String> uncompleted, completed;
    private List<String> accessoryTraits = List.of(
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
    private List<String> bellyTraits = List.of(
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
    private List<String> eyeTraits = List.of(
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
    private List<String> furTraits = List.of(
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

    public List<String> getCompleted() {
        return completed;
    }

    public void setCompleted(List<String> completed) {
        this.completed = completed;
    }

    public List<String> getUncompleted() {
        return uncompleted;
    }

    public void setUncompleted(List<String> uncompleted) {
        this.uncompleted = uncompleted;
    }

    public List<String> getAccessoryTraits() {
        return accessoryTraits;
    }

    public List<String> getBellyTraits() {
        return bellyTraits;
    }

    public List<String> getEyeTraits() {
        return eyeTraits;
    }

    public List<String> getFurTraits() {
        return furTraits;
    }

    public void setUpUncompleted(){
        uncompleted = getUncompleted();
        uncompleted.addAll(getAccessoryTraits());
        uncompleted.addAll(getBellyTraits());
        uncompleted.addAll(getEyeTraits());
        uncompleted.addAll(getFurTraits());
        setUncompleted(uncompleted);
    }

}
