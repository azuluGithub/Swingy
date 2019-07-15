package swingy.dot.com.model.heroes;

import swingy.dot.com.model.character.SwingyCharacterHero;

public class SwingyHero extends SwingyCharacterHero {

    public SwingyHero(String name){
        name = name.toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        this.name = name;
    }

    public SwingyHero(String name, String type, int level, int xp, String armor, String weapon) {
        name = name.toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        this.name = name;
        this.type = type;
        this.level = level;
        this.experience = xp;
        this.armor = armor;
        this.weapon = weapon;
    }

}
