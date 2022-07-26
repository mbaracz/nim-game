package pl.mbaracz.nim.model;

public class Player {

    protected final String name;

    protected final boolean human;

    public Player(String name, boolean human) {
        this.name = name;
        this.human = human;
    }

    public String getName() {
        return name;
    }

    public boolean isHuman() {
        return human;
    }
}
