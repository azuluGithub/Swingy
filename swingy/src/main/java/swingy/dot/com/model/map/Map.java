package swingy.dot.com.model.map;

import swingy.dot.com.controller.SwingyAppController;
import swingy.dot.com.model.factory.EnemyFactory;
import swingy.dot.com.model.enemies.SwingyEnemy;
import java.util.*;
import static swingy.dot.com.model.color.Color.*;

public class Map {

    public int mapDimension;
    public String [][] map;
    public List<SwingyEnemy> listEnemies = new ArrayList<>();
    private SwingyAppController apc;

    public Map(SwingyAppController apc) {
        this.apc = apc;
    }

    public void newMap() {
        mapDimension = (apc.hero.getLevel() - 1) * 5 + 10 - (apc.hero.getLevel() % 2);
        map = new String[mapDimension][mapDimension];
        apc.hero.setPx(mapDimension/2);
        apc.hero.setPy(mapDimension/2);
    }

    public void initMap() {
        for (int a = 0; a < mapDimension; a++) {
            for (int b = 0; b < mapDimension; b++) {
                int rand = new Random().nextInt(50);
                if (a != apc.hero.getPx()  || b != apc.hero.getPy()) {
                    switch (rand) {
                        case 3:
                            listEnemies.add(EnemyFactory.newEnemy("Goblin", a, b));
                        case 5:
                            listEnemies.add(EnemyFactory.newEnemy("Vergetta",a, b));
                            break;
                        case 13:
                            listEnemies.add(EnemyFactory.newEnemy("Undertaker", a, b));
                            break;
                        case 15:
                            listEnemies.add(EnemyFactory.newEnemy("Danger",a, b));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public void placePlayers() {
        for (int a = 0; a < mapDimension; a++) {
            for (int b = 0; b < mapDimension; b++) {
                map[a][b] = ".";
                if (apc.hero.getPx() == a && apc.hero.getPy() == b) {
                    map[a][b]= "P";
                }
                for (SwingyEnemy en: listEnemies) {
                    if (en.getEx() == a && en.getEy() == b) {
                        map[a][b] = en.getEinit();
                    }
                    if (en.getEx() == a && en.getEy() == b && apc.hero.getPx() == a && apc.hero.getPy() == b) {
                        map[a][b] = "x";
                    }
                }
            }
        }
    }

    public void printMap() {
        System.out.print(BLANK);
        apc.getPlay().playerStats();
        System.out.println();
        for(int a = 0; a < mapDimension; a++){
            System.out.print("\t");
            for (int b = 0; b < mapDimension; b++){
                if (map[a][b].equals("P")){
                    System.out.print(YELLOW + map[a][b] + RESET);
                } else if (map[a][b].equals(".")) {
                    System.out.print(PURPLE + map[a][b] + RESET);
                } else if (!map[a][b].equals(".") && !map[a][b].equals("P")) {
                    System.out.print(RED + map[a][b]);
                } else if (map[a][b].equals("x")) {
                    System.out.print(map[a][b]);
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.println(PURPLE + "\n\tYou are: P");
        apc.getPlay().checkCollision();
    }

    public void northDirection(){
        map[apc.hero.getPx()][apc.hero.getPy()] = ".";
        apc.hero.setPx(apc.hero.getPx() - 1);
        apc.hero.setPy(apc.hero.getPy());
        map[apc.hero.getPx()][apc.hero.getPy()] = "P";
        shakeUpEnemies();
        placePlayers();
    }

    public void checkBounds() {
        printMap();
        apc.getPlay().winBounds();
    }


    public void eastDirection(){
        map[apc.hero.getPx()][apc.hero.getPy()] = ".";
        apc.hero.setPy(apc.hero.getPy() + 1);
        apc.hero.setPx(apc.hero.getPx());
        map[apc.hero.getPx()][apc.hero.getPy()] = "P";
        shakeUpEnemies();
        placePlayers();
    }

    public void southDirection(){
        map[apc.hero.getPx()][apc.hero.getPy()] = ".";
        apc.hero.setPx(apc.hero.getPx() + 1);
        apc.hero.setPy(apc.hero.getPy());
        map[apc.hero.getPx()][apc.hero.getPy()] = "P";
        shakeUpEnemies();
        placePlayers();
    }

    public void westDirection(){
        map[apc.hero.getPx()][apc.hero.getPy()] = ".";
        apc.hero.setPy(apc.hero.getPy() - 1);
        apc.hero.setPx(apc.hero.getPx());
        map[apc.hero.getPx()][apc.hero.getPy()] = "P";
        shakeUpEnemies();
        placePlayers();
    }

    private void shakeUpEnemies() {
        for (SwingyEnemy se: listEnemies) {
            int num =  new Random().nextInt(4);
            if (num == 1) {
                se.setEx(se.getEx() + 1);
            }
            if (num == 2) {
                se.setEy(se.getEy() + 1);
            }
            if (num == 3) {
                se.setEx(se.getEx() - 1);
            }
            if (num == 4) {
                se.setEy(se.getEy() - 1);
            }
            checkEdge(se);
        }
    }

    private void checkEdge(SwingyEnemy se) {
        if (se.getEx() == 0){
            se.setEx(se.getEx() + 1);
        }
        if (se.getEx() == mapDimension){
            se.setEx(0);
        }
        if (se.getEy() == 0) {
            se.setEy(se.getEy() + 1);
        }
        if (se.getEy() == mapDimension) {
            se.setEy(0);
        }
    }



}
