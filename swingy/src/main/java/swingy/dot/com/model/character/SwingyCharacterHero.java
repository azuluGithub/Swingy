package swingy.dot.com.model.character;

import java.util.Random;

import javax.validation.constraints.*;

public abstract class SwingyCharacterHero {

    @NotBlank(message = "Name cannot be blank")
    protected String name;

    @NotBlank(message = "Type cannot be blank")
    public String type;

    @NotBlank(message = "Armor cannot be empty")
    protected String armor;

    @NotBlank(message = "Weapon cannot be blank")
    protected String weapon;

    @NotBlank(message = "Helm cannot be blank")
    protected String helm;

    @Min(value=1, message= "Minimum level should be level 1")
    @Max(value=5, message= "Maximum level should be level 5")
    protected int level;

    @Min(value=0, message= "Minimum Experience value should be 0")
    protected int experience;

    protected int attack;

    protected int defense;

    protected int hp;

    protected int px;

    protected int py;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getHelm() {
        return helm;
    }

    public void setHelm(String helm) {
        this.helm = helm;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    protected void characterStats() {
        this.attack = 50 + new Random().nextInt(100);
        this.defense = 50 + new Random().nextInt(100);
        this.hp = 50 + new Random().nextInt(100);
    }
}