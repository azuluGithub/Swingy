package swingy.dot.com.model.game;

import swingy.dot.com.model.heroes.SwingyHero;

import java.util.*;

public class HeroStats {

    public List <String> statsList = new ArrayList<>();

    public void heroStats(SwingyHero hero) {
        statsList.add("\n _______YOUR STATS_______");
        statsList.add("\n Hero Name : " + hero.getName());
        statsList.add(" Hero Type : " + hero.getType());
        statsList.add(" Level     : " + hero.getLevel());
        statsList.add(" Experience: " + hero.getExperience());
        statsList.add(" Attack    : " + hero.getAttack());
        statsList.add(" Defense   : " + hero.getDefense());
        statsList.add(" Hit Points: " + hero.getHp());
        statsList.add(" Weapon    : " + hero.getWeapon());
        statsList.add(" Helm      : " + hero.getHelm());
        statsList.add(" Armor     : " + hero.getArmor());
    }
}
