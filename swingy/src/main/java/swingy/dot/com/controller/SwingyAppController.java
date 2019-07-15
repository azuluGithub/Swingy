package swingy.dot.com.controller;

import swingy.dot.com.model.battle.SwingyBattle;
import swingy.dot.com.model.enemies.SwingyEnemy;
import swingy.dot.com.model.game.HeroStats;
import swingy.dot.com.model.game.SwingyPlay;
import swingy.dot.com.model.heroes.SwingyHero;
import swingy.dot.com.model.map.Map;
import swingy.dot.com.model.read_write.*;
import swingy.dot.com.view.SwingyFrame;

import java.util.Scanner;

public class SwingyAppController {

    private FileReading fileReading;
    private FileWriting fileWriting;
    private HeroStats stats;
    private Map map;
    private SwingyPlay play;
    public SwingyHero hero;
    public SwingyEnemy enemy;
    public SwingyBattle battle;
    public int enemyHp;
    public int enemyDefense;
    public int enemyAttack;
    public Scanner userInput;

    public SwingyAppController() {
        fileReading = new FileReading();
        fileReading.readFile();
        stats = new HeroStats();
        map = new Map(this);
        fileWriting = new FileWriting(this);
        userInput = new Scanner(System.in);
        play = new SwingyPlay(this);
        battle = new SwingyBattle(this);
    }

    public FileReading getFileReading() {
        return fileReading;
    }

    public HeroStats getStats() {
        return stats;
    }

    public Map getMap() {
        return map;
    }

    public  SwingyPlay getPlay() {
        return play;
    }

    public FileWriting getFileWriting() {
        return fileWriting;
    }

    public SwingyBattle getBattle() {
        return battle;
    }

    public void startGui(){
        new SwingyFrame(this);
    }

    public void startConsole() {
        play.consoleGame();
    }
}
