package de.haevn.utils.network.webhook.discord;

public enum DiscordColors {
    Default("Default", 0, "#000000"),
    Aqua("Aqua", 1752220, "#1ABC9C"),
    DarkAqua("DarkAqua", 1146986, "#11806A"),
    Green("Green", 5763719, "#57F287"),
    DarkGreen("DarkGreen", 2067276, "#1F8B4C"),
    Blue("Blue", 3447003, "#3498DB"),
    DarkBlue("DarkBlue", 2123412, "#206694"),
    Purple("Purple", 10181046, "#9B59B6"),
    DarkPurple("DarkPurple", 7419530, "#71368A"),
    LuminousVividPink("LuminousVividPink", 15277667, "#E91E63"),
    DarkVividPink("DarkVividPink", 11342935, "#AD1457"),
    Gold("Gold", 15844367, "#F1C40F"),
    DarkGold("DarkGold", 12745742, "#C27C0E"),
    Orange("Orange", 15105570, "#E67E22"),
    DarkOrange("DarkOrange", 11027200, "#A84300"),
    Red("Red", 15548997, "#ED4245"),
    DarkRed("DarkRed", 10038562, "#992D22"),
    Grey("Grey", 9807270, "#95A5A6"),
    DarkGrey("DarkGrey", 9936031, "#979C9F"),
    DarkerGrey("DarkerGrey", 8359053, "#7F8C8D"),
    LightGrey("LightGrey", 12370112, "#BCC0C0"),
    Navy("Navy", 3426654, "#34495E"),
    DarkNavy("DarkNavy", 2899536, "#2C3E50"),
    Yellow("Yellow", 16776960, "#FFFF00"),
    White("White", 16777215, "#FFFFFF"),
    Greyple("Greyple", 10070709, "#99AAb5"),
    Black("Black", 2303786, "#23272A"),
    DarkButNotBlack("DarkButNotBlack", 2895667, "#2C2F33"),
    NotQuiteBlack("NotQuiteBlack", 2303786, "#23272A"),
    Blurple("Blurple", 5793266, "#5865F2"),
    OfficialGreen("OfficialGreen", 5763719, "#57F287"),
    OfficialYellow("OfficialYellow", 16705372, "#FEE75C"),
    Fuchsia("Fuchsia", 15418782, "#EB459E"),
    OfficialRed("OfficialRed", 15548997, "#ED4245");

    public final String name;
    public final int value;
    public final String hex;

    DiscordColors(String name, int value, String hex) {
        this.name = name;
        this.value = value;
        this.hex = hex;
    }
}