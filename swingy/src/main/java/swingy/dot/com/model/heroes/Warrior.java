package swingy.dot.com.model.heroes;

public class Warrior extends SwingyHero {

    public Warrior(String name){
        super(name);
        this.type = "Warrior";
        this.level = 1;
        this.experience = 0;
        this.armor = "Cuirass";
        this.weapon = "ChainSaw";
        this.helm = "Wooden";
        characterStats();
    }

    public Warrior(String name, String type, int level, int xp, String armor, String weapon){
        super(name, type, level, xp, armor, weapon);
        this.helm = "Wooden";
        characterStats();
    }
}
