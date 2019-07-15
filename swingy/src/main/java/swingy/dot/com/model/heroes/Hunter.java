package swingy.dot.com.model.heroes;

public class Hunter extends SwingyHero {

    public Hunter(String name){
        super(name);
        this.type = "Hunter";
        this.level = 1;
        this.experience = 0;
        this.armor = "Berserker";
        this.weapon = "Taser";
        this.helm = "Armet";
        characterStats();
    }

    public Hunter(String name, String type, int level, int xp, String armor, String weapon){
        super(name, type, level, xp, armor, weapon);
        this.helm = "Armet";
        characterStats();
    }

}
