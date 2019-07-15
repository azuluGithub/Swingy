package swingy.dot.com.model.battle;


import swingy.dot.com.controller.SwingyAppController;

import java.util.Random;

import static swingy.dot.com.model.color.Color.*;

public class SwingyBattle {

    private SwingyAppController apc;

    public SwingyBattle(SwingyAppController apc) {
        this.apc = apc;
    }

    public void encounterMessage(){
        System.out.println(YELLOW + "\nWhat would you like to do?" + RESET);
        System.out.println(CYAN + "1. Fight" + RESET);
        System.out.println(CYAN + "2. Run" + RESET);
        System.out.print(GREEN + "Choose Number: " + RESET);
    }

    public void encounterEnemy() {
         while(true){
            int input = Integer.parseInt(apc.userInput.nextLine());
            switch (input){
                case 1:
                    fight();
                    break;
                case 2:
                    run();
                    break;
                default:
                    System.out.println(RED + "\nChoose option 1 or 2." + RESET);
                    break;
            }
            if (input == 1 || input == 2){
                break;
            }
        }
    }

    private void run() {
        int rand = 1 + (int)(Math.random() * 2);
        if (rand == 1) {
            System.out.println(GREEN + "\nYou escaped " + apc.enemy.getEname() + " successfully!" + RESET);
        } else if (rand == 2) {
            System.out.println(RED + "Sorry " + apc.hero.getName() + " you Couldn't run! You have to fight " + apc.enemy.getEname() + " to proceed!" + RESET);
            System.out.println(GREEN + "Press Enter when you ready" + RESET);
            apc.userInput.nextLine();
            fight();
        }
    }

    private void fight(){
        System.out.print(BLANK);
        while (true) {
            enemyAttackPlayer();
            isHeroDead();
            playerAttackEnemy();
            if (apc.enemy.getEhp() <= 0) {
                updateEnemyStats();
                System.out.println(GREEN + "Congrats you killed " + apc.enemy.getEname() + RESET);
                droppedArtifact();
                apc.getPlay().manageExperience();
                break;
            }
            apc.getFileWriting().addListToFile();
        }
    }

    private void enemyAttackPlayer(){
        int	enemyAttack = 10 + new Random().nextInt(apc.enemy.getEattack());
        int playerDefense = 10 + new Random().nextInt(apc.hero.getDefense());
        int damageToPlayer = enemyAttack - playerDefense;

        if (damageToPlayer > 0) {
            apc.hero.setHp(apc.hero.getHp() - damageToPlayer);
        } else {
            damageToPlayer = 0;
        }
        System.out.println(CYAN + apc.enemy.getEname() + " hit " + apc.hero.getName() + " and damage = " + damageToPlayer + RESET);
        System.out.println(CYAN + apc.hero.getName() + "'s Hp = " + apc.hero.getHp() + RESET);
    }

    private void isHeroDead() {
        if (apc.hero.getHp() <= 0) {
            System.out.println(RED + "Sorry " + apc.enemy.getEname() + " killed you.\nGame Over" + RESET);
            apc.getFileWriting().addListToFile();
            System.exit(0);
        }
    }

    public void updateEnemyStats() {
        apc.enemy.setEhp(50 + new Random().nextInt(100));
        apc.enemy.setEattack(50 + new Random().nextInt(100));
        apc.enemy.setEdefense(50 + new Random().nextInt(100));
    }

    private void playerAttackEnemy() {
        int	playerAttack = new Random().nextInt(apc.hero.getAttack());
        int enemyDefense = new Random().nextInt(apc.enemy.getEdefense());
        int damageToEnemy = playerAttack - enemyDefense;

        if (damageToEnemy > 0) {
            apc.enemy.setEhp(apc.enemy.getEhp() - damageToEnemy);
        } else {
            damageToEnemy = 0;
        }
        System.out.println(PURPLE + apc.hero.getName() + " hit " + apc.enemy.getEname() + " and damage = " + damageToEnemy + RESET);
        System.out.println(PURPLE + apc.enemy.getEname() + "'s Hp = " + apc.enemy.getEhp() + RESET);
    }

    private void droppedArtifact(){
        int rand = 1 + (int) (Math.random() * 10);
        if (rand == 4) {
            droppedWeapon();
        } else if (rand == 6) {
            droppedArmor();
        } else if (rand == 8) {
            droppedHelm();
        }
    }

    private void droppedWeapon() {
        System.out.println(YELLOW + apc.enemy.getEname() + " fell and Dropped his weapon (" + apc.enemy.getEweapon() + ").\nWould you like to take " + apc.enemy.getEname() + "'s Weapon?" + RESET);
        System.out.println(CYAN + "1. Yes" + RESET);
        System.out.println(CYAN + "2. No" + RESET);
        System.out.print(GREEN + "Choose Number: " + RESET);
        weaponOption();
    }

    private void weaponOption(){
        int input = Integer.parseInt(apc.userInput.nextLine());
        switch (input){
            case 1:
                apc.hero.setWeapon(apc.enemy.getEweapon());
                apc.hero.setAttack(apc.hero.getAttack() + apc.getPlay().weaponValue());
                System.out.println(PURPLE + apc.getPlay().weaponQuality() + "\nYou picked a " + apc.hero.getWeapon() + ".\nYour attack value has increased to " + apc.hero.getAttack() + RESET);
                break;
            case 2:
                System.out.println(PURPLE + "You ignored the Artifact" + RESET);
                break;
            default:
                System.out.println(RED + "\nChoose 1 or 2" + RESET);
                break;
        }
    }

    private void droppedArmor() {
        System.out.println(YELLOW + apc.enemy.getEname() + " fell and Dropped his armor (" + apc.enemy.getEarmor() + ").\nWould you like to take " + apc.enemy.getEname() + "'s armor?" + RESET);
        System.out.println(CYAN + "1. Yes" + RESET);
        System.out.println(CYAN + "2. No" + RESET);
        System.out.print(GREEN + "Choose Number: " + RESET);
        armorOption();
    }

    private void armorOption(){
        int input = Integer.parseInt(apc.userInput.nextLine());
        switch (input){
            case 1:
                apc.hero.setArmor(apc.enemy.getEarmor());
                apc.hero.setAttack(apc.hero.getDefense() + 20);
                System.out.println(PURPLE + "\nYou picked a " + apc.hero.getArmor() + ".\nYour defense value has increased to " + apc.hero.getDefense() + RESET);

                break;
            case 2:
                System.out.println(PURPLE + "You ignored the Artifact" + RESET);
                break;
            default:
                System.out.println(RED + "\nChoose 1 or 2" + RESET);
                break;
        }
    }

    private void droppedHelm() {
        System.out.println(YELLOW + apc.enemy.getEname() + " fell and Dropped his helm (" + apc.enemy.getEhelm() + ").\nWould you like to take " + apc.enemy.getEname() + "'s helmet?" + RESET);
        System.out.println(CYAN + "1. Yes" + RESET);
        System.out.println(CYAN + "2. No" + RESET);
        System.out.print(GREEN + "Choose Number: " + RESET);
        helmOption();
    }

    private void helmOption(){
        int input = Integer.parseInt(apc.userInput.nextLine());
        switch (input){
            case 1:
                apc.hero.setHelm(apc.enemy.getEhelm());
                apc.hero.setHp(apc.hero.getHp() + 60);
                System.out.println(PURPLE + "\nYou picked a " + apc.hero.getHelm() + ".\nYour hp value has increased to " + apc.hero.getHp() + RESET);
                break;
            case 2:
                System.out.println(PURPLE + "\nYou ignored the Artifact" + RESET);
                break;
            default:
                System.out.println(RED + "\nChoose 1 or 2" + RESET);
                break;
        }
    }

}
