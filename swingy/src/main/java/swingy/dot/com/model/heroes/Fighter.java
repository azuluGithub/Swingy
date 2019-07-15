package swingy.dot.com.model.heroes;

public class Fighter extends SwingyHero {

    public Fighter(String name){
        super(name);
        this.type = "Fighter";
        this.level = 1;
        this.experience = 0;
        this.armor = "Brigandine";
        this.weapon = "VegasClaw";
        this.helm = "Sallet";
        characterStats();
    }

    public Fighter(String name, String type, int level, int xp, String armor, String weapon){
        super(name, type, level, xp, armor, weapon);
        this.helm = "Sallet";
        characterStats();
    }

}
