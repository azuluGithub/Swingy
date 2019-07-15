package swingy.dot.com.view;

import swingy.dot.com.controller.SwingyAppController;
import swingy.dot.com.model.enemies.SwingyEnemy;
import swingy.dot.com.model.factory.*;
import swingy.dot.com.model.heroes.SwingyHero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SwingyPanel extends JPanel {

    private SwingyAppController baseController;
    private ChoiceHandler choiceHandler;
    private VisibilityManager vm;
    private String newHeroName = "";
    private ButtonHandler buttonHandler;
    private String position1, position2, position3, position4;

    JPanel swingyNamePanel;
    private JLabel swingyNameLabel;
    JPanel welcomeNamePanel;
    private JLabel welcomeNameLabel;
    JPanel choicePanel;
    private JLabel choiceLabel;
    JPanel startButtonPanel;
    private JButton startButton;
    private JButton choiceButton1, choiceButton2;
    JPanel choiceButtonPanel;
    private Font biggestFont, biggerFont, bigFont, mediumFont, smallFont, smallestFont;
    JPanel newHeroPanel;
    private JLabel newHeroLabel;
    JPanel heroFieldPanel;
    private JTextField newHeroTextField;
    JPanel newHeroButtonPanel;
    private JButton newHeroButton;
    JPanel newHeroTypePanel;
    private JLabel newHeroTypeLabel;
    JPanel heroTypeSelectPanel;
    private JLabel heroTypeSelectLabel;
    JPanel heroTypeFieldPanel;
    private JTextField newHeroTypeTextField;
    JPanel newHeroTypeButtonPanel;
    private JButton newHeroTypeButton;
    JPanel heroStatsPanel;
    private JTextArea heroStatsTextArea;
    private JScrollPane scrollbar1, scrollbar2, scrollbar3;
    JPanel heroStatsButtonPanel;
    private JButton heroStatsButton;
    JPanel oldHeroPanel;
    private JTextArea oldHeroTextArea;
    JPanel oldChPanel;
    private JLabel oldChLabel;
    JPanel oldFieldPanel;
    private JTextField oldField;
    JPanel oldButtonPanel;
    private JButton oldButton;
    JPanel playerPanel;
    private JLabel nameLabel, typeLabel, attkLabel, defLabel, hpLabel, weaponLabel, armorLabel, helmLabel, lvlLabel, xpLabel;
    private JLabel nameLabelName, typeLabelName, attkLabelNum, defLabelNum, hpLabelNum, weaponLabelName, armorLabelName, helmLabelName, lvlLabelNum, xpLabelNum;
    JPanel mainTextPanel;
    private JTextArea mainTextArea;
    JPanel directionButtonPanel;
    private JButton goNorth, goEast, goSouth, goWest, goCenter;

    public SwingyPanel(SwingyAppController baseController){
        this.baseController = baseController;
        choiceHandler = new ChoiceHandler();
        vm = new VisibilityManager(this);
        buttonHandler = new ButtonHandler();
        setUpLayout();
        vm.showStartScreen();
    }

    private void setUpLayout() {
        setUpPanel();
        welcomeWindow();
        choiceWindow();
        newHeroWindow();
        heroTypeWindow();
        heroStatsWindow();
        oldHero();
        gameWindow();
    }

    public class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();
            if (yourChoice.equals("start")) {
                vm.showChoiceScreen();
            } else if (yourChoice.equals("newHero")) {
                vm.showNewHeroScreen();
            } else if (yourChoice.equals("oldHero")) {
                showHeroList();
            } else if (yourChoice.equals("heroName")) {
                setHeroName();
            } else if (yourChoice.equals("heroType")) {
                saveNewHero();
            } else if (yourChoice.equals("selectOld")) {
                saveSelectedHero();
            } else if (yourChoice.equals("heroStats")) {
                playGame();
            }
        }
    }

    private boolean checkHeroName(String inputName) {
        boolean isNameValid = false;
        if (inputName.matches("^[a-zA-Z]*$") && !inputName.equals("")) {
            isNameValid = true;
        } else {
            JOptionPane.showMessageDialog(this,"Name must contain: a-z and A-Z");
        }
        return isNameValid;
    }

    private boolean checkHeroType(String inputType) {
        boolean isTypeValid = false;
        if (inputType.equalsIgnoreCase("Fighter")  || inputType.equalsIgnoreCase("Hunter") || inputType.equalsIgnoreCase("Warrior") ) {
            isTypeValid = true;
        } else {
            JOptionPane.showMessageDialog(this,"Type must be: Fighter/ Hunter/ Warrior");
        }
        return isTypeValid;
    }

    private boolean checkSelectedHero(String inputValue) {
        boolean isNumValid = false;
        if (inputValue.matches("^[0-9]*$") && !inputValue.isEmpty()) {
            isNumValid = true;
        } else {
            JOptionPane.showMessageDialog(this,"Enter a number!");
        }
        return isNumValid;
    }

    private void showHeroList() {
        if (baseController.getFileReading().listHeroes.size() <= 0){
            JOptionPane.showMessageDialog(this, "There are no old Heroes to select from");
            vm.showStartScreen();
        } else {
            oldHeroTextArea.setText(" LIST OF AVAILABLE HEROEs:\n\n");
            int i = 0;
            for (SwingyHero h : baseController.getFileReading().listHeroes) {
                oldHeroTextArea.append(" " + i + ". "+ h.getName() + " |Type:" + h.getType() + " |LvL:" + h.getLevel() + " |Xp:" + h.getExperience() + " |Armor:" + h.getArmor() + " |Weapon:" + h.getWeapon() + "\n");
                i++;
            }
            vm.showOldHeroScreen();
        }
    }

    private void saveSelectedHero(){
        if (checkSelectedHero(oldField.getText())) {
            int value = Integer.parseInt(oldField.getText());
            if (value >= 0 && value < baseController.getFileReading().listHeroes.size() && oldField.getText().matches("^[0-9]*$")) {
                baseController.hero = baseController.getFileReading().listHeroes.get(value);
                statsList();
            } else {
                JOptionPane.showMessageDialog(this,"Input out of Range.. Please Enter a Valid number!");
            }
        }
    }

    private void setHeroName(){
        if (checkHeroName(newHeroTextField.getText())) {
            newHeroName = newHeroTextField.getText();
            vm.showHeroTypeScreen();
        }
    }

    private void saveNewHero(){
        if (checkHeroType(newHeroTypeTextField.getText())){
            baseController.hero = SwingyFactory.newSwingyHero(newHeroName, newHeroTypeTextField.getText());
            baseController.getFileReading().listHeroes.add(baseController.hero);
            statsList();
        }
    }

    private void statsList() {
        baseController.getStats().heroStats(baseController.hero);
        heroStatsTextArea.setText("");
        for (String list : baseController.getStats().statsList) {
            heroStatsTextArea.append(list + "\n");
        }
        vm.showHeroStatsScreen();
    }

    private void playGame() {
        setUpStats();
        centerMap();
        setUpMap();
        vm.showGameScreen();
    }

    private void setUpMap() {
        baseController.getMap().newMap();
        baseController.getMap().initMap();
        baseController.getMap().placePlayers();
    }

    public class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String btnChoice = event.getActionCommand();
            if (btnChoice.equals("quit")) {
                baseController.getFileWriting().addListToFile();
                System.exit(1);
            } else if (btnChoice.equals("btnNorth")) {
                selectPosition(position1);
            } else if (btnChoice.equals("btnEast")) {
                selectPosition(position2);
            } else if (btnChoice.equals("btnSouth")) {
                selectPosition(position3);
            } else if (btnChoice.equals("btnWest")) {
                selectPosition(position4);
            }
        }
    }

    private void centerMap(){
        mainTextArea.setText("You are at the Center of the map. If you keep going in a certain direction you will either reach the map boundary or encounter an enemy. If you Kill an enemy you will gain Experience and if you gain more experience you will move to the next level.\n\nWhere do you wanna go?\nChoose: ▲/►/▼/◄");
        position1 = "north";
        position2 = "east";
        position3 = "south";
        position4 = "west";
    }

    private void selectPosition(String Pos) {
        if (Pos.equals("north")){
            baseController.getMap().northDirection();
            direction();
        } else if (Pos.equals("east")) {
            baseController.getMap().eastDirection();
            direction();
        } else if (Pos.equals("south")) {
            baseController.getMap().southDirection();
            direction();
        } else if (Pos.equals("west")) {
            baseController.getMap().westDirection();
            direction();
        } else if (Pos.equals("fight")) {
            fight();
        } else if (Pos.equals("enemyDead")) {
            enemyDead();
        } else if (Pos.equals("playerAttackEnemy")) {
            playerAttackEnemy();
        } else if (Pos.equals("enemyAttackPlayer")) {
            enemyAttackPlayer();
        } else if (Pos.equals("run")) {
            run();
        } else if (Pos.equals("ending")) {
            ending();
        } else if (Pos.equals("pickWeapon")) {
            pickWeapon();
        } else if (Pos.equals("ignore")) {
            ignore();
        } else if (Pos.equals("pickArmor")) {
            pickArmor();
        } else if (Pos.equals("pickHelm")) {
            pickHelm();
        }
    }

    private void winBounds() {
        if (baseController.hero.getPx() == 0 || baseController.hero.getPx() == baseController.getMap().mapDimension - 1 ||
                baseController.hero.getPy() == 0 || baseController.hero.getPy() == baseController.getMap().mapDimension - 1) {
            baseController.getPlay().gainExperience();
            xpLabelNum.setText("" + baseController.hero.getExperience());
            baseController.getFileWriting().addListToFile();
            JOptionPane.showMessageDialog(this,"Congratulations " + baseController.hero.getName() + " You've  WON THE GAME!!");
            System.exit(1);
        }
    }

    private void levelUp() {
        if (baseController.hero.getExperience() >= ((baseController.hero.getLevel() * 1000) + ((baseController.hero.getLevel() - 1) * (baseController.hero.getLevel() - 1) *450))) {
            if (baseController.hero.getHp() <= 50) {
                baseController.hero.setHp(baseController.hero.getHp() * 2 + 10);
            }
            baseController.getPlay().removeEnemies();
            baseController.hero.setLevel(baseController.hero.getLevel() + 1);
            baseController.getFileWriting().addListToFile();
            JOptionPane.showMessageDialog(this, "Level " + (baseController.hero.getLevel() - 1) + " Complete! Press OK to move to the next level");
            lvlLabelNum.setText("" + baseController.hero.getLevel());
            setUpMap();
        }
    }

    private void direction(){
        levelUp();
        printMap();
        winBounds();
        chooseDirection();
        checkGuiCollision();
    }

    private void checkGuiCollision(){
        for (SwingyEnemy se: baseController.getMap().listEnemies) {
            if (se.getEx() == baseController.hero.getPx() && se.getEy() == baseController.hero.getPy()) {
                goNorth.setText("Fight");
                goEast.setText("●");
                goSouth.setText("Run");
                goWest.setText("●");
                position1 = "fight";
                position2 = "";
                position3 = "run";
                position4 = "";
                JOptionPane.showMessageDialog(this,"\nYou encountered the monster " + se.getEname() + "\n\nFight or Run?");
                baseController.enemy = se;
                baseController.enemyHp = baseController.enemy.getEhp();
                baseController.enemyDefense = baseController.enemy.getEdefense();
                baseController.enemyAttack = baseController.enemy.getEattack();
                break;
            }
        }
    }

    private void run() {
        int rand = 1 + (int)(Math.random() * 2);
        if (rand == 1) {
            JOptionPane.showMessageDialog(this, "You escaped your Enemy successfully!");
            chooseDirection();
        } else if (rand == 2) {
            JOptionPane.showMessageDialog(this, "Sorry " + baseController.hero.getName() + " you Couldn't runaway!\nYou have to fight your Enemy to proceed!");
            goNorth.setText("Fight");
            noDirection();

            position1 = "fight";
            noPosition();
        }
    }

    private void noDirection() {
        goEast.setText("●");
        goSouth.setText("●");
        goWest.setText("●");
    }

    private void noPosition() {
        position2 = "";
        position3 = "";
        position4 = "";
    }


    private void fight() {
        mainTextArea.setText("\n _______" + baseController.enemy.getEname() + "'s Stats_______\n\n" +
                "♦ Enemy Name : " + baseController.enemy.getEname() + "\n♦ Attack     : " + baseController.enemy.getEattack() +
                "\n♦ Defense    : " + baseController.enemy.getEdefense() + "\n♦ Hit Points : " + baseController.enemy.getEhp() +
                "\n♦ Weapon     : " + baseController.enemy.getEweapon() + "\n\n What do u wanna do?");
        goNorth.setText("Attack");
        noDirection();
        position1 = "playerAttackEnemy";
        noPosition();
    }

    private void playerAttackEnemy() {
        int	playerAttack = new Random().nextInt(baseController.hero.getAttack());
        int enemyDefense = new Random().nextInt(baseController.enemy.getEdefense());
        int damageToEnemy = playerAttack - enemyDefense;

        if (damageToEnemy > 0) {
            baseController.enemy.setEhp(baseController.enemy.getEhp() - damageToEnemy);
        } else {
            damageToEnemy = 0;
        }
        mainTextArea.setText(" " + baseController.hero.getName() + " hit " + baseController.enemy.getEname() + " and damage = " + damageToEnemy);
        mainTextArea.append("\n " + baseController.enemy.getEname() + "'s Hp = " + baseController.enemy.getEhp());
        isEnemyDead();
    }

    private void isEnemyDead() {
        if (baseController.enemy.getEhp() > 0) {
            goNorth.setText("Defend");
            noDirection();
            position1 = "enemyAttackPlayer";
            noPosition();
        } else if (baseController.enemy.getEhp() < 1) {
            goNorth.setText("●");
            goEast.setText("►");
            goSouth.setText("●");
            goWest.setText("●");
            position1 = "";
            position2 = "enemyDead";
            position3 = "";
            position4 = "";
        }
    }

    private void enemyAttackPlayer(){
        int	enemyAttack = 10 + new Random().nextInt(baseController.enemy.getEattack());
        int playerDefense = 10 + new Random().nextInt(baseController.hero.getDefense());
        int damageToPlayer = enemyAttack - playerDefense;

        if (damageToPlayer > 0) {
            baseController.hero.setHp(baseController.hero.getHp() - damageToPlayer);
        } else {
            damageToPlayer = 0;
        }
        mainTextArea.setText(" " + baseController.enemy.getEname() + " hit " + baseController.hero.getName() + " and damage = " + damageToPlayer);
        mainTextArea.append("\n " + baseController.hero.getName() + "'s Hp = " + baseController.hero.getHp());
        isHeroDead();
    }

    private void isHeroDead() {
        if (baseController.hero.getHp() > 0) {
            hpLabelNum.setText("" + baseController.hero.getHp());
            goNorth.setText("Attack");
            noDirection();
            position1 = "playerAttackEnemy";
            noPosition();
        } else if (baseController.hero.getHp() < 1) {
            hpLabelNum.setText("" + 0);
            ending();
        }
    }

    private void enemyDead(){
        baseController.getBattle().updateEnemyStats();
        JOptionPane.showMessageDialog(this, "\nCongrats you killed " + baseController.enemy.getEname());
        baseController.getPlay().manageExperience();
        xpLabelNum.setText("" + baseController.hero.getExperience());
        getArtifact();
        baseController.getFileWriting().addListToFile();
    }

    private void ending(){
        baseController.getFileWriting().addListToFile();
        mainTextArea.setText(" You Lost. Game Over.\nThank you for playing!");
        goNorth.setText("●");
        goEast.setText("●");
        goSouth.setText("●");
        goWest.setText("●");
        position1 = "";
        position2 = "";
        position3 = "";
        position4 = "";
    }

    private void getArtifact(){
        int rand = 1 + (int) (Math.random() * 12);
        if (rand == 2) {
            droppedWeapon();
        } else if (rand == 4) {
            droppedArmor();
        } else if (rand == 6) {
           droppedHelm();
        }
        chooseDirection();
    }

    private void pickWeapon() {
        baseController.hero.setWeapon(baseController.enemy.getEweapon());
        baseController.hero.setAttack(baseController.hero.getAttack() + baseController.getPlay().weaponValue());
        weaponLabelName.setText(baseController.hero.getWeapon());
        attkLabelNum.setText("" + baseController.hero.getAttack());
        mainTextArea.setText(baseController.getPlay().weaponQuality() + " You picked a " + baseController.hero.getWeapon() + ".\nYour attack value has increased to " + baseController.hero.getAttack());
        chooseDirection();
    }

    private void chooseDirection() {
        goNorth.setText("N");
        goEast.setText("E");
        goSouth.setText("S");
        goWest.setText("W");
        position1 = "north";
        position2 = "east";
        position3 = "south";
        position4 = "west";
    }

    private void ignore() {
        mainTextArea.setText("You ignored the Artifact");
        chooseDirection();
    }

    private void pickChoice() {
        goNorth.setText("1");
        goEast.setText("●");
        goSouth.setText("2");
        goWest.setText("●");
    }

    private void droppedWeapon() {
        mainTextArea.setText("The Enemy fell and Dropped a weapon (" + baseController.enemy.getEweapon() + " ).\n\nTo pick it press 1.\nTo ignore press 2.");
        pickChoice();
        position1 = "pickWeapon";
        position2 = "";
        position3 = "ignore";
        position4 = "";
    }

    private void droppedArmor() {
        mainTextArea.setText("The Enemy fell and Dropped an armor (" + baseController.enemy.getEarmor() + " ).\n\nTo pick it press 1.\nTo ignore press 2.");
        pickChoice();
        position1 = "pickArmor";
        position2 = "";
        position3 = "ignore";
        position4 = "";
    }

    private void pickArmor() {
        baseController.hero.setDefense(baseController.hero.getDefense() + 40);
        baseController.hero.setArmor(baseController.enemy.getEarmor());
        armorLabelName.setText(baseController.hero.getArmor());
        defLabelNum.setText("" + baseController.hero.getDefense());
        mainTextArea.setText("You obtained a " + baseController.hero.getArmor() + ". Your defense value has increased to " + baseController.hero.getDefense());
        chooseDirection();
    }

    private void droppedHelm() {
        mainTextArea.setText("The Enemy fell and Dropped a Helmet (" + baseController.enemy.getEhelm() + " ).\n\nTo pick it press 1.\nTo ignore press 2.");
        pickChoice();
        position1 = "pickHelm";
        position2 = "";
        position3 = "ignore";
        position4 = "";
    }

    private void pickHelm() {
        baseController.hero.setHp(baseController.hero.getHp() + 60);
        baseController.hero.setHelm(baseController.enemy.getEhelm());
        helmLabelName.setText(baseController.hero.getHelm());
        hpLabelNum.setText("" + baseController.hero.getHp());
        mainTextArea.setText("You obtained a " + baseController.hero.getHelm() + ". Your Hp value has increased to " + baseController.hero.getHp());
        chooseDirection();
    }

    private void printMap() {
        mainTextArea.setText(" ");
        for(int a = 0; a < baseController.getMap().mapDimension; a++){
            mainTextArea.append(" ");
            for (int b = 0; b < baseController.getMap().mapDimension; b++){
                if (baseController.getMap().map[a][b].equals("P")){
                    mainTextArea.append(baseController.getMap().map[a][b]);
                } else if (baseController.getMap().map[a][b].equals(".")) {
                    mainTextArea.append(baseController.getMap().map[a][b]);
                } else if (!baseController.getMap().map[a][b].equals(".") && !baseController.getMap().map[a][b].equals("P")) {
                    mainTextArea.append(baseController.getMap().map[a][b]);
                }
                mainTextArea.append(" ");
            }
            mainTextArea.append("\n");
        }
        mainTextArea.append("\nYou are: P");
    }

    private void setUpStats() {
        nameLabelName.setText(baseController.hero.getName());
        typeLabelName.setText(baseController.hero.getType());
        attkLabelNum.setText("" + baseController.hero.getAttack());
        defLabelNum.setText("" + baseController.hero.getDefense());
        hpLabelNum.setText("" + baseController.hero.getHp());
        weaponLabelName.setText(baseController.hero.getWeapon());
        armorLabelName.setText(baseController.hero.getArmor());
        helmLabelName.setText(baseController.hero.getHelm());
        lvlLabelNum.setText("" + baseController.hero.getLevel());
        xpLabelNum.setText("" + baseController.hero.getExperience());
    }

    private void setUpPanel() {
        swingyNamePanel = new JPanel();
        swingyNameLabel = new JLabel("WELCOME TO SWINGY RPG");
        startButtonPanel = new JPanel();
        welcomeNamePanel = new JPanel();
        welcomeNameLabel = new JLabel("Press Start To Play");
        startButton = new JButton("START");
        choicePanel = new JPanel();
        choiceLabel = new JLabel("What would you like to do?");
        choiceButtonPanel = new JPanel();
        choiceButton1 = new JButton("Create Hero");
        choiceButton2 = new JButton("Use Created Hero");
        newHeroPanel = new JPanel();
        newHeroLabel = new JLabel("What's your favorite Hero Name?");
        heroFieldPanel = new JPanel();
        newHeroTextField = new JTextField(20);
        newHeroButtonPanel = new JPanel();
        newHeroButton = new JButton("Submit");
        newHeroTypePanel = new JPanel();
        newHeroTypeLabel = new JLabel("Select Hero type:");
        heroTypeSelectPanel = new JPanel();
        heroTypeSelectLabel = new JLabel("Hunter / Fighter / Warrior");
        heroTypeFieldPanel = new JPanel();
        newHeroTypeTextField = new JTextField(20);
        newHeroTypeButtonPanel = new JPanel();
        newHeroTypeButton = new JButton("Submit");
        heroStatsPanel = new JPanel();
        heroStatsTextArea = new JTextArea("azulu swingy");
        heroStatsButtonPanel = new JPanel();
        heroStatsButton = new JButton("Start game");
        scrollbar1 = new JScrollPane(heroStatsTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollbar2 = new JScrollPane(mainTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollbar3 = new JScrollPane(oldHeroTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        oldHeroPanel = new JPanel();
        oldHeroTextArea = new JTextArea("Available Heroes");
        oldChPanel = new JPanel();
        oldChLabel = new JLabel("Enter number:");
        oldFieldPanel = new JPanel();
        oldField = new JTextField(25);
        oldButtonPanel = new JPanel();
        oldButton = new JButton("Submit");
        playerPanel = new JPanel();
        nameLabel = new JLabel(" HeroName:");
        nameLabelName = new JLabel();
        typeLabel = new JLabel(" HeroType:");
        typeLabelName = new JLabel();
        attkLabel = new JLabel(" Attack:");
        attkLabelNum = new JLabel();
        defLabel = new JLabel(" Defense:");
        defLabelNum = new JLabel();
        hpLabel = new JLabel(" HitPoints:");
        hpLabelNum = new JLabel();
        weaponLabel = new JLabel(" Weapon:");
        weaponLabelName = new JLabel();
        armorLabel = new JLabel(" Armor:");
        armorLabelName = new JLabel();
        helmLabel = new JLabel(" Helm:");
        helmLabelName = new JLabel();
        lvlLabel = new JLabel(" Level:");
        lvlLabelNum = new JLabel();
        xpLabel = new JLabel(" Experience:");
        xpLabelNum = new JLabel();
        mainTextPanel = new JPanel();
        mainTextArea = new JTextArea("You are at the Center of the map. If you keep going in a certain direction you will either reach the map boundary or encounter an enemy.\n\nWhere do you wanna go??? ▲/►/▼/◄");
        directionButtonPanel = new JPanel();
        goNorth = new JButton("▲");
        goEast = new JButton("►");
        goSouth = new JButton("▼");
        goWest = new JButton("◄");
        goCenter = new JButton("Quit");
        biggestFont  = new Font("Times New Roman", Font.BOLD, 40);
        biggerFont = new Font("Times New Roman", Font.PLAIN, 23);
        bigFont = new Font("Times New Roman", Font.PLAIN, 20);
        mediumFont = new Font("Times New Roman", Font.PLAIN, 18);
        smallFont = new Font("Times New Roman", Font.PLAIN, 16);
        smallestFont = new Font("Times New Roman", Font.PLAIN, 12);

        this.setSize(800, 600);
        this.setBackground(Color.BLACK);
        this.add(welcomeNamePanel);
        this.add(startButtonPanel);
        this.add(swingyNamePanel);
        this.add(choicePanel);
        this.add(choiceButtonPanel);
        this.add(newHeroPanel);
        this.add(heroFieldPanel);
        this.add(newHeroButtonPanel);
        this.add(newHeroTypePanel);
        this.add(heroTypeSelectPanel);
        this.add(heroTypeFieldPanel);
        this.add(newHeroTypeButtonPanel);
        this.add(heroStatsPanel);
        this.add(heroStatsButtonPanel);
        this.add(oldHeroPanel);
        this.add(oldChPanel);
        this.add(oldFieldPanel);
        this.add(oldButtonPanel);
        this.add(playerPanel);
        this.add(mainTextPanel);
        this.add(directionButtonPanel);
    }

    private void welcomeWindow(){
        swingyNamePanel.setBounds(100 , 150 , 600, 120);
        swingyNamePanel.setBackground(Color.BLACK);
        swingyNameLabel.setForeground(Color.GREEN);
        swingyNameLabel.setFont(biggestFont);
        swingyNamePanel.add(swingyNameLabel);

        welcomeNamePanel.setBounds(100 , 300, 600, 50);
        welcomeNamePanel.setBackground(Color.black);
        welcomeNameLabel.setFont(biggerFont);
        welcomeNameLabel.setForeground(Color.RED);
        welcomeNamePanel.add(welcomeNameLabel);

        startButtonPanel.setBounds(300 , 400, 200, 100);
        startButtonPanel.setBackground(Color.black);
        startButton.setToolTipText("Click to start Game");
        startButton.setForeground(Color.MAGENTA);
        startButton.setBackground(Color.black);
        startButton.setFocusPainted(false);
        startButton.setFont(smallFont);
        startButtonPanel.add(startButton);
        startButton.addActionListener(choiceHandler);
        startButton.setActionCommand("start");
    }

    private void choiceWindow() {
        choicePanel.setBounds(200, 200, 350, 75);
        choicePanel.setBackground(Color.BLACK);
        choiceLabel.setForeground(Color.ORANGE);
        choiceLabel.setBackground(Color.BLACK);
        choicePanel.add(choiceLabel);
        choiceLabel.setFont(bigFont);

        choiceButtonPanel.setBounds(260, 275, 220, 80);
        choiceButtonPanel.setLayout(new GridLayout(2, 1, 2, 4));
        choiceButtonPanel.setBackground(Color.BLACK);
        choiceButton1.setForeground(Color.MAGENTA);
        choiceButton1.setBackground(Color.BLACK);
        choiceButton1.setFocusPainted(false);
        choiceButton1.setFont(smallFont);
        choiceButtonPanel.add(choiceButton1);
        choiceButton1.addActionListener(choiceHandler);
        choiceButton1.setActionCommand("newHero");

        choiceButton2.setFocusPainted(false);
        choiceButton2.setFont(smallFont);
        choiceButtonPanel.add(choiceButton2);
        choiceButton2.addActionListener(choiceHandler);
        choiceButton2.setActionCommand("oldHero");
    }

    private void newHeroWindow(){
        newHeroPanel.setBounds(180, 200, 400, 100);
        newHeroPanel.setBackground(Color.BLACK);
        newHeroLabel.setForeground(Color.ORANGE);
        newHeroLabel.setFont(mediumFont);
        newHeroPanel.add(newHeroLabel);

        heroFieldPanel.setBounds(250, 250, 250, 50);
        heroFieldPanel.setBackground(Color.BLACK);
        heroFieldPanel.add(newHeroTextField);

        newHeroButtonPanel.setBounds(250, 300, 250, 80);
        newHeroButtonPanel.setBackground(Color.BLACK);
        newHeroButton.setForeground(Color.MAGENTA);
        newHeroButton.setBackground(Color.BLACK);
        newHeroButton.setFocusPainted(false);
        newHeroButton.setFont(smallFont);
        newHeroButtonPanel.add(newHeroButton);
        newHeroButton.addActionListener(choiceHandler);
        newHeroButton.setActionCommand("heroName");
    }

    private void heroTypeWindow() {
        newHeroTypePanel.setBounds(220, 200, 300, 50);
        newHeroTypePanel.setBackground(Color.BLACK);
        newHeroTypeLabel.setForeground(Color.ORANGE);
        newHeroTypePanel.add(newHeroTypeLabel);
        newHeroTypeLabel.setFont(mediumFont);

        heroTypeSelectPanel.setBounds(220, 250, 300, 50);
        heroTypeSelectPanel.setBackground(Color.BLACK);
        heroTypeSelectLabel.setForeground(Color.GREEN);
        heroTypeSelectLabel.setBackground(Color.BLACK);
        heroTypeSelectPanel.add(heroTypeSelectLabel);
        heroTypeSelectLabel.setFont(smallFont);

        heroTypeFieldPanel.setBounds(250, 300, 250, 50);
        heroTypeFieldPanel.setBackground(Color.BLACK);
        heroTypeFieldPanel.add(newHeroTypeTextField);

        newHeroTypeButtonPanel.setBounds(250, 350, 250, 80);
        newHeroTypeButtonPanel.setBackground(Color.BLACK);
        newHeroTypeButton.setForeground(Color.MAGENTA);
        newHeroTypeButton.setBackground(Color.BLACK);
        newHeroTypeButton.setFocusPainted(false);
        newHeroTypeButton.setFont(smallFont);
        newHeroTypeButtonPanel.add(newHeroTypeButton);
        newHeroTypeButton.addActionListener(choiceHandler);
        newHeroTypeButton.setActionCommand("heroType");
    }

    private void heroStatsWindow() {
        heroStatsPanel.setBounds(100, 100, 600, 300);
        heroStatsPanel.setBackground(Color.black);
        heroStatsTextArea.setBounds(100, 100, 220, 300);
        heroStatsTextArea.setForeground(Color.orange);
        heroStatsTextArea.setBackground(Color.DARK_GRAY);
        heroStatsTextArea.setLineWrap(true);
        heroStatsTextArea.setWrapStyleWord(true);
        heroStatsPanel.add(heroStatsTextArea);
        heroStatsPanel.add(scrollbar1);
        heroStatsTextArea.setFont(smallFont);

        heroStatsButtonPanel.setBounds(280, 440, 250, 80);
        heroStatsButtonPanel.setBackground(Color.BLACK);
        heroStatsButton.setForeground(Color.MAGENTA);
        heroStatsButton.setBackground(Color.BLACK);
        heroStatsButton.setFocusPainted(false);
        heroStatsButton.setFont(smallFont);
        heroStatsButtonPanel.add(heroStatsButton);
        heroStatsButton.addActionListener(choiceHandler);
        heroStatsButton.setActionCommand("heroStats");
    }

    private void oldHero() {
        oldHeroPanel.setBounds(100, 50, 600, 350);
        oldHeroPanel.setBackground(Color.darkGray);
        oldHeroTextArea.setBounds(100, 50, 600, 350);
        oldHeroTextArea.setBackground(Color.darkGray);
        oldHeroTextArea.setForeground(Color.ORANGE);
        oldHeroTextArea.setFont(smallestFont);
        oldHeroTextArea.setLineWrap(true);
        oldHeroTextArea.setWrapStyleWord(true);
        oldHeroPanel.add(scrollbar3);
        oldHeroPanel.add(oldHeroTextArea);

        oldChPanel.setBounds(250, 400, 300, 50);
        oldChPanel.setBackground(Color.black);
        oldChLabel.setForeground(Color.GREEN);
        oldChLabel.setBackground(Color.black);
        oldChPanel.add(oldChLabel);
        oldChLabel.setFont(smallFont);

        oldFieldPanel.setBounds(250, 450, 300, 50);
        oldFieldPanel.setBackground(Color.black);
        oldFieldPanel.add(oldField);

        oldButtonPanel.setBounds(280, 500, 250, 80);
        oldButtonPanel.setBackground(Color.black);
        oldButton.setForeground(Color.MAGENTA);
        oldButton.setBackground(Color.BLACK);
        oldButton.setFocusPainted(false);
        oldButton.setFont(smallFont);
        oldButtonPanel.add(oldButton);
        oldButton.addActionListener(choiceHandler);
        oldButton.setActionCommand("selectOld");
    }

    private void gameWindow() {
        playerPanel.setBounds(50, 50, 200, 250);
        playerPanel.setBackground(Color.darkGray);
        playerPanel.setLayout(new GridLayout(10, 2));

        nameLabel.setForeground(Color.MAGENTA);
        nameLabel.setFont(smallFont);
        playerPanel.add(nameLabel);
        nameLabelName.setForeground(Color.CYAN);
        nameLabelName.setFont(smallFont);
        playerPanel.add(nameLabelName);

        typeLabel.setForeground(Color.MAGENTA);
        typeLabel.setFont(smallFont);
        playerPanel.add(typeLabel);
        typeLabelName.setForeground(Color.CYAN);
        typeLabelName.setFont(smallFont);
        playerPanel.add(typeLabelName);

        attkLabel.setForeground(Color.MAGENTA);
        attkLabel.setFont(smallFont);
        playerPanel.add(attkLabel);
        attkLabelNum.setForeground(Color.CYAN);
        attkLabelNum.setFont(smallFont);
        playerPanel.add(attkLabelNum);

        defLabel.setForeground(Color.MAGENTA);
        defLabel.setFont(smallFont);
        playerPanel.add(defLabel);
        defLabelNum.setForeground(Color.CYAN);
        defLabelNum.setFont(smallFont);
        playerPanel.add(defLabelNum);

        hpLabel.setForeground(Color.MAGENTA);
        hpLabel.setFont(smallFont);
        playerPanel.add(hpLabel);
        hpLabelNum.setForeground(Color.CYAN);
        hpLabelNum.setFont(smallFont);
        playerPanel.add(hpLabelNum);

        weaponLabel.setForeground(Color.MAGENTA);
        weaponLabel.setFont(smallFont);
        playerPanel.add(weaponLabel);
        weaponLabelName.setForeground(Color.CYAN);
        weaponLabelName.setFont(smallFont);
        playerPanel.add(weaponLabelName);

        armorLabel.setForeground(Color.MAGENTA);
        armorLabel.setFont(smallFont);
        playerPanel.add(armorLabel);
        armorLabelName.setForeground(Color.CYAN);
        armorLabelName.setFont(smallFont);
        playerPanel.add(armorLabelName);

        helmLabel.setForeground(Color.MAGENTA);
        helmLabel.setFont(smallFont);
        playerPanel.add(helmLabel);
        helmLabelName.setForeground(Color.CYAN);
        helmLabelName.setFont(smallFont);
        playerPanel.add(helmLabelName);

        lvlLabel.setForeground(Color.MAGENTA);
        lvlLabel.setFont(smallFont);
        playerPanel.add(lvlLabel);
        lvlLabelNum.setForeground(Color.CYAN);
        lvlLabelNum.setFont(smallFont);
        playerPanel.add(lvlLabelNum);

        xpLabel.setForeground(Color.MAGENTA);
        xpLabel.setFont(smallFont);
        playerPanel.add(xpLabel);
        xpLabelNum.setForeground(Color.CYAN);
        xpLabelNum.setFont(smallFont);
        playerPanel.add(xpLabelNum);

        mainTextPanel.setBounds(300, 50, 400, 500);
        mainTextPanel.setBackground(Color.darkGray);
        mainTextArea.setBounds(320, 50, 360, 500);
        mainTextArea.setBackground(Color.darkGray);
        mainTextArea.setForeground(Color.ORANGE);
        mainTextArea.setFont(smallFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextPanel.add(scrollbar2);
        mainTextPanel.add(mainTextArea);

        directionButtonPanel.setBounds(50, 350, 200, 200);
        directionButtonPanel.setLayout(new BorderLayout(/*10,10*/));
        directionButtonPanel.setBackground(Color.darkGray);

        goNorth.setForeground(Color.black);
        goNorth.addActionListener(buttonHandler);
        goNorth.setActionCommand("btnNorth");

        goEast.setForeground(Color.black);
        goEast.addActionListener(buttonHandler);
        goEast.setActionCommand("btnEast");

        goSouth.setForeground(Color.black);
        goSouth.addActionListener(buttonHandler);
        goSouth.setActionCommand("btnSouth");

        goWest.setForeground(Color.black);
        goWest.addActionListener(buttonHandler);
        goWest.setActionCommand("btnWest");

        goCenter.setForeground(Color.black);
        goCenter.addActionListener(buttonHandler);
        goCenter.setActionCommand("quit");

        directionButtonPanel.add(goNorth, BorderLayout.NORTH);
        directionButtonPanel.add(goEast, BorderLayout.EAST);
        directionButtonPanel.add(goCenter, BorderLayout.CENTER);
        directionButtonPanel.add(goSouth, BorderLayout.SOUTH);
        directionButtonPanel.add(goWest, BorderLayout.WEST);
    }

}
























