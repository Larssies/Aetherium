package com.larssies.aetherium.checks;

public enum CheckType {

    SPEED("Speed", "Movement",  "Checks if the player is moving too fast.", 20),
    REACH("Reach", "Combat", "Checks if the player is reaching too far.", 20),
    FLY("Fly", "Movement", "Checks if the player is flying.", 20),
    NO_FALL("NoFall", "Movement", "Checks if the player is falling.", 20),
    SCAFFOLD("Scaffold", "Movement", "Checks if the player is using scaffold.", 20),
    AUTO_CLICKER("AutoClicker", "Combat", "Checks if the player is clicking too fast.", 20);

    private final String name;
    private final String category;
    private final String description;
    private final int maxVL;

    CheckType(String name, String category, String description, int maxVL) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.maxVL = maxVL;
    }

    public String getName() {
        return name;
    }

    public static String getBypassPermission() {
        return "aetherium.bypass";
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxVL() {
        return maxVL;
    }

}
