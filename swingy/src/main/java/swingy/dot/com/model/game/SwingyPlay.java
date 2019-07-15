package swingy.dot.com.model.game;

import swingy.dot.com.controller.SwingyAppController;
import swingy.dot.com.model.enemies.SwingyEnemy;
import swingy.dot.com.model.factory.SwingyFactory;
import swingy.dot.com.model.heroes.SwingyHero;

import static swingy.dot.com.model.color.Color.*;

public class SwingyPlay {

    private SwingyAppController apc;

    public SwingyPlay(SwingyAppController apc) {
        this.apc = apc;
    }

    public void consoleGame() {
        welcomeMessage();
        gameOptions();
        enterOption();
    }

    private void welcomeMessage() {
        System.out.print(BLANK);
        System.out.println(PURPLE + "WELCOME TO SWINGY - ROLE PLAY GAME!" + RESET);
        System.out.println(RED + "\n___________Game menu______________" + RESET);
    }

    private void gameOptions() {
        System.out.println(YELLOW + "\nWhat would you like to do?" + RESET);
        System.out.println(CYAN + "1. Create a Hero" + RESET);
        System.out.println(CYAN + "2. Select previously created Hero" + RESET);
        System.out.println(CYAN + "3. Quit" + RESET);
        System.out.print(GREEN + "Choose Number: " + RESET);
    }

    private void enterOption() {
        while (true) {
            int input = Integer.parseInt(apc.userInput.nextLine());
            switch (input) {
                case 1:
                    createNewHero();
                    startOption();
                    break;
                case 2:
                    useOldHero();
                    startOption();
                    break;
                case 3:
                    System.exit(1);
                    break;
                default:
                    erroMessage();
            }
        }
    }

    private void createNewHero() {
        apc.hero = create();
        apc.getFileReading().listHeroes.add(apc.hero);
        statsList();
        startOption();
        startPlay();
    }

    private void statsList() {
        System.out.print(BLANK);
        apc.getStats().heroStats(apc.hero);
        for (String list : apc.getStats().statsList) {
            System.out.println(PURPLE + list + RESET);
        }
    }

    private void selectOption() {
        System.out.print(BLANK);
        System.out.println(YELLOW + "What's Your Hero Type: " + RESET);
        System.out.print(RED + "Hunter " + RESET);
        System.out.print(" / ");
        System.out.print(PURPLE + "Fighter " + RESET);
        System.out.print(" / ");
        System.out.print(CYAN + "Warrior " + RESET);
        System.out.print(GREEN + "\nChoose type: " + RESET);
    }

    private void heroTypeOption() {
        System.out.print(BLANK);
        System.out.println(PURPLE + "What's your favorite Hero Name?" + RESET);
        System.out.print(GREEN + "Enter name: " + RESET);
    }

    private SwingyHero create() {
        String type = "";
        String heroType;
        String heroName = "";
        heroTypeOption();
        while (apc.userInput.hasNextLine()) {
            heroName = apc.userInput.nextLine();
            if (heroName.matches("^[a-zA-Z]*$") && !heroName.equals("")) {
                selectOption();
                heroName = heroName.toLowerCase();
                heroName = heroName.substring(0, 1).toUpperCase() + heroName.substring(1);
                while (apc.userInput.hasNextLine()) {
                    heroType = apc.userInput.nextLine();
                    if (heroType.equalsIgnoreCase("Fighter")) {
                        type = heroType;
                        break;
                    } else if (heroType.equalsIgnoreCase("Warrior")) {
                        type = heroType;
                        break;
                    } else if (heroType.equalsIgnoreCase("Hunter")) {
                        type = heroType;
                        break;
                    } else {
                        erroMessage();
                    }
                }
                return (SwingyFactory.newSwingyHero(heroName, type));
            } else {
                erroMessage();
            }
        }
        return (SwingyFactory.newSwingyHero(heroName, type));
    }

    private void useOldHero(){
        System.out.print(BLANK);
        if (apc.getFileReading().listHeroes.size() <= 0){
            System.out.println(RED + "Error:: SORRY HERO LIST IS EMPTY!" + RESET);
            System.exit(1);
        } else {
            showAvailable();
            System.out.print(YELLOW + "\nChoose number: " + RESET);
            while (true) {
                String a = apc.userInput.nextLine();
                try {
                    int input = Integer.parseInt(a);
                    if (input < 0 || input > apc.getFileReading().listHeroes.size()) {
                        System.out.println(RED + "Error:: Value out of range. Please Try again" + RESET);
                    } else if (input <= apc.getFileReading().listHeroes.size()) {
                        saveOldHero(input);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(RED + "Error:: Input must be a number. Please try again" + RESET);
                }
            }
            statsList();
        }
    }

    private void showAvailable() {
        System.out.println(PURPLE + "\nLIST OF AVAILABLE HEROEs: " + RESET);
        int i = 0;
        for (SwingyHero h : apc.getFileReading().listHeroes) {
            System.out.println(i + ". " + PURPLE + "Name:" + RESET + h.getName()  + PURPLE +  " |Type:" + RESET + h.getType()  + PURPLE +  " |LvL:" + RESET + h.getLevel()  + PURPLE +  " |Xp:" + RESET + h.getExperience() + PURPLE + " |Armor:" + RESET + h.getArmor() + PURPLE + " |Weapon:" + RESET + h.getWeapon());
            i++;
        }
    }

    private void saveOldHero(int input) {
        int j = 0;
        while (j <= apc.getFileReading().listHeroes.size()) {
            if (input == j) {
                apc.hero = apc.getFileReading().listHeroes.get(input);
            }
            j++;
        }
    }

    private void startOption() {
        System.out.println(YELLOW + "\nWhat would you like to do?" + RESET);
        System.out.println(CYAN + "1. Start Game" + RESET);
        System.out.println(CYAN + "2. Exit Game" + RESET);
        System.out.print(GREEN + "Choose Number: " + RESET);
        startPlay();
    }

    private void erroMessage() {
        System.out.println(RED + "Error:: You entered an invalid command!" + RESET);
        System.out.println(GREEN + "Please Try again" + RESET);
    }

    private void startPlay() {
        int in = Integer.parseInt(apc.userInput.nextLine());
        switch (in) {
            case 1:
                startMessage();
                break;
            case 2:
                apc.getFileWriting().addListToFile();
                System.exit(1);
                break;
            default:
                erroMessage();
                break;
        }
    }

    private void startMessage() {
        System.out.print(BLANK);
        System.out.println(RED + "Defeat some Enemies to proceed to the next level!!" + RESET);
        System.out.println(PURPLE + "You can also win a game by Reaching map borders!" + RESET);
        System.out.println(GREEN + "Press Enter to Play" + RESET);
        apc.userInput.nextLine();
        PlayGame();
    }

    private void setUpMap() {
        apc.getMap().newMap();
        apc.getMap().initMap();
        apc.getMap().placePlayers();
        apc.getMap().printMap();
    }

    private void PlayGame() {
        setUpMap();
        while(true) {
            chooseDirection();
            movePlayer();
            levelUp();
        }
    }

    private void chooseDirection() {
        System.out.println(YELLOW + "\nWhere do you wanna go? " + RESET);
        System.out.println(CYAN + "1. Go North" + RESET);
        System.out.println(CYAN + "2. Go East" + RESET);
        System.out.println(CYAN + "3. Go South" + RESET);
        System.out.println(CYAN + "4. Go West" + RESET);
        System.out.println(CYAN + "5. Exit Game" + RESET);
        System.out.print(GREEN + "Choose Number: " + RESET);
    }

    private void movePlayer() {
        while(true) {
            int input = Integer.parseInt(apc.userInput.nextLine());
            switch (input) {
                case 1:
                    apc.getMap().northDirection();
                    apc.getMap().checkBounds();
                    break;
                case 2:
                    apc.getMap().eastDirection();
                    apc.getMap().checkBounds();
                    break;
                case 3:
                    apc.getMap().southDirection();
                    apc.getMap().checkBounds();
                    break;
                case 4:
                    apc.getMap().westDirection();
                    apc.getMap().checkBounds();
                    break;
                case 5:
                    apc.getFileWriting().addListToFile();
                    System.exit(0);
                    break;
                default:
                    System.out.println(RED + "\nError:: Choose numbers 1 - 5" + RESET);
                    break;
            }
            if (input == 1 || input == 2 || input == 3 || input == 4) {
                break;
            }
        }
    }

    public void winBounds() {
        if (apc.hero.getPx() == 0 || apc.hero.getPx() == apc.getMap().mapDimension - 1 ||
                apc.hero.getPy() == 0 || apc.hero.getPy() == apc.getMap().mapDimension - 1) {
            apc.getPlay().gainExperience();
            System.out.println(GREEN + "\nCongratulations " + apc.hero.getName() + " you've won the game!!" + RESET);
            boundaryOptions();
            boundaryChoice();
        }
    }

    private void boundaryOptions() {
        System.out.println(YELLOW + "\nWhat would you like to do?" + RESET);
        System.out.println(CYAN + "1. Go back to the game" + RESET);
        System.out.println(CYAN + "2. Exit game" + RESET);
        System.out.print(GREEN + "Choose Number: " + RESET);
    }

    private void boundaryChoice() {
        while(true){
            int input = Integer.parseInt(apc.userInput.nextLine());
            switch (input){
                case 1:
                    removeEnemies();
                    PlayGame();
                    break;
                case 2:
                    apc.getFileWriting().addListToFile();
                    System.exit(1);
                    break;
                default:
                    System.out.println(RED + "\nError:: Choose 1 or 2" + RESET);
                    break;
            }
            if (input == 1){
                break;
            }
        }
    }

    public void playerStats() {
        System.out.print(PURPLE + "Level :" + apc.hero.getLevel());
        System.out.print(PURPLE + "                      XP :" + apc.hero.getExperience() + RESET);
        System.out.print(RED + "\nAttack:" + RESET + apc.hero.getAttack());
        System.out.print(RED + "|Defense:" + RESET + apc.hero.getDefense());
        System.out.print(RED + "|HP:" + RESET + apc.hero.getHp());
        System.out.print(RED + "|Weapon:" + RESET + apc.hero.getWeapon());
        System.out.print(RED + "|" + RESET + "\n");
    }

    public void checkCollision(){
        for (SwingyEnemy se: apc.getMap().listEnemies) {
            if (se.getEx() == apc.hero.getPx() && se.getEy() == apc.hero.getPy()) {
                System.out.println(PURPLE + "\nYOU ENCOUNTERED THE MONSTER " + "\"" + se.getEname().toUpperCase() + "\"" + RESET);
                System.out.println(PURPLE + "\n Enemy Name : " + RESET + se.getEname());
                System.out.println(PURPLE + " Attack     : " + RESET + se.getEattack());
                System.out.println(PURPLE + " Defense    : " + RESET + se.getEdefense());
                System.out.println(PURPLE + " Hit Points : " + RESET + se.getEhp());
                System.out.println(PURPLE + " Weapon     : " + RESET + se.getEweapon());
                apc.enemy = se;
                apc.enemyHp = apc.enemy.getEhp();
                apc.enemyDefense = apc.enemy.getEdefense();
                apc.enemyAttack = apc.enemy.getEattack();
                apc.getBattle().encounterMessage();
                apc.getBattle().encounterEnemy();
            }
        }
    }

    public void levelUp() {
        if (apc.hero.getExperience() >= ((apc.hero.getLevel() * 1000) + ((apc.hero.getLevel() - 1) * (apc.hero.getLevel() - 1) *450))) {
            if (apc.hero.getHp() <= 50) {
                apc.hero.setHp(apc.hero.getHp() * 2 + 10);
            }
            apc.getPlay().removeEnemies();
            apc.hero.setLevel(apc.hero.getLevel() + 1);
            apc.getFileWriting().addListToFile();
            System.out.println(GREEN + "\nLevel " + (apc.hero.getLevel() - 1) + " Complete! Press Enter to move to the next level" + RESET);
            apc.userInput.nextLine();
            PlayGame();
        }
    }

    public void gainExperience () {
        apc.hero.setExperience(apc.hero.getExperience() + 20);
    }

    public void manageExperience() {
        if (apc.enemyAttack >= 30 && apc.enemyAttack < 60){
            apc.hero.setExperience(apc.hero.getExperience() + 200);
        } else if (apc.enemyAttack >= 60 && apc.enemyAttack < 70){
            apc.hero.setExperience(apc.hero.getExperience() + 250);
        } else if (apc.enemyAttack >= 70 && apc.enemyAttack < 91) {
            apc.hero.setExperience(apc.hero.getExperience() + 300);
        } else if (apc.enemyAttack >= 91) {
            apc.hero.setExperience(apc.hero.getExperience() + 350);
        }
    }

    public String weaponQuality() {
        if (apc.enemyAttack >= 60 && apc.enemyAttack < 70){
            return "You obtained ordinary weapon quality.";
        } else if (apc.enemyAttack >= 70 && apc.enemyAttack < 91) {
            return "You obtained good weapon quality.";
        } else if (apc.enemyAttack >= 91) {
            return "You obtained the best weapon quality.";
        }
        return "You obtained poor weapon quality.";
    }

    public int weaponValue() {
        String wQty = weaponQuality();
        int value = 10;
        if (wQty.equals("You obtained ordinary weapon quality.")){
            value = 15;
        } else if (wQty.equals("You obtained good weapon quality.")) {
            value = 20;
        } else if (wQty.equals("You obtained the best weapon quality.")) {
            value = 25;
        }
        return value;
    }

    public void removeEnemies(){
        int i = 0;
        while (i < apc.getMap().listEnemies.size()) {
            apc.getMap().listEnemies.remove(i);
            i++;
        }
    }
}
