package swingy.dot.com.view;

public class VisibilityManager {

    private SwingyPanel swingyPanel;

    public VisibilityManager(SwingyPanel swingyPanel) {
        this.swingyPanel = swingyPanel;
    }

    public void showStartScreen() {
        swingyPanel.swingyNamePanel.setVisible(true);
        swingyPanel.welcomeNamePanel.setVisible(true);
        swingyPanel.startButtonPanel.setVisible(true);

        swingyPanel.choicePanel.setVisible(false);
        swingyPanel.choiceButtonPanel.setVisible(false);

        swingyPanel.newHeroPanel.setVisible(false);
        swingyPanel.heroFieldPanel.setVisible(false);
        swingyPanel.newHeroButtonPanel.setVisible(false);

        swingyPanel.newHeroTypePanel.setVisible(false);
        swingyPanel.heroTypeFieldPanel.setVisible(false);
        swingyPanel.heroTypeSelectPanel.setVisible(false);
        swingyPanel.newHeroTypeButtonPanel.setVisible(false);

        swingyPanel.heroStatsPanel.setVisible(false);
        swingyPanel.heroStatsButtonPanel.setVisible(false);

        swingyPanel.oldHeroPanel.setVisible(false);
        swingyPanel.oldChPanel.setVisible(false);
        swingyPanel.oldFieldPanel.setVisible(false);
        swingyPanel.oldButtonPanel.setVisible(false);

        swingyPanel.playerPanel.setVisible(false);
        swingyPanel.mainTextPanel.setVisible(false);
        swingyPanel.directionButtonPanel.setVisible(false);
    }

    public void showChoiceScreen(){
        swingyPanel.swingyNamePanel.setVisible(false);
        swingyPanel.welcomeNamePanel.setVisible(false);
        swingyPanel.startButtonPanel.setVisible(false);

        swingyPanel.choicePanel.setVisible(true);
        swingyPanel.choiceButtonPanel.setVisible(true);

        swingyPanel.newHeroPanel.setVisible(false);
        swingyPanel.heroFieldPanel.setVisible(false);
        swingyPanel.newHeroButtonPanel.setVisible(false);

        swingyPanel.newHeroTypePanel.setVisible(false);
        swingyPanel.heroTypeFieldPanel.setVisible(false);
        swingyPanel.heroTypeSelectPanel.setVisible(false);
        swingyPanel.newHeroTypeButtonPanel.setVisible(false);

        swingyPanel.heroStatsPanel.setVisible(false);
        swingyPanel.heroStatsButtonPanel.setVisible(false);

        swingyPanel.oldHeroPanel.setVisible(false);
        swingyPanel.oldChPanel.setVisible(false);
        swingyPanel.oldFieldPanel.setVisible(false);
        swingyPanel.oldButtonPanel.setVisible(false);

        swingyPanel.playerPanel.setVisible(false);
        swingyPanel.mainTextPanel.setVisible(false);
        swingyPanel.directionButtonPanel.setVisible(false);
    }

    public void showNewHeroScreen() {
        swingyPanel.swingyNamePanel.setVisible(false);
        swingyPanel.welcomeNamePanel.setVisible(false);
        swingyPanel.startButtonPanel.setVisible(false);

        swingyPanel.choicePanel.setVisible(false);
        swingyPanel.choiceButtonPanel.setVisible(false);

        swingyPanel.newHeroPanel.setVisible(true);
        swingyPanel.heroFieldPanel.setVisible(true);
        swingyPanel.newHeroButtonPanel.setVisible(true);

        swingyPanel.newHeroTypePanel.setVisible(false);
        swingyPanel.heroTypeFieldPanel.setVisible(false);
        swingyPanel.heroTypeSelectPanel.setVisible(false);
        swingyPanel.newHeroTypeButtonPanel.setVisible(false);

        swingyPanel.heroStatsPanel.setVisible(false);
        swingyPanel.heroStatsButtonPanel.setVisible(false);

        swingyPanel.oldHeroPanel.setVisible(false);
        swingyPanel.oldChPanel.setVisible(false);
        swingyPanel.oldFieldPanel.setVisible(false);
        swingyPanel.oldButtonPanel.setVisible(false);

        swingyPanel.playerPanel.setVisible(false);
        swingyPanel.mainTextPanel.setVisible(false);
        swingyPanel.directionButtonPanel.setVisible(false);
    }

    public void showHeroTypeScreen() {
        swingyPanel.swingyNamePanel.setVisible(false);
        swingyPanel.welcomeNamePanel.setVisible(false);
        swingyPanel.startButtonPanel.setVisible(false);

        swingyPanel.choicePanel.setVisible(false);
        swingyPanel.choiceButtonPanel.setVisible(false);

        swingyPanel.newHeroPanel.setVisible(false);
        swingyPanel.heroFieldPanel.setVisible(false);
        swingyPanel.newHeroButtonPanel.setVisible(false);

        swingyPanel.newHeroTypePanel.setVisible(true);
        swingyPanel.heroTypeFieldPanel.setVisible(true);
        swingyPanel.heroTypeSelectPanel.setVisible(true);
        swingyPanel.newHeroTypeButtonPanel.setVisible(true);

        swingyPanel.heroStatsPanel.setVisible(false);
        swingyPanel.heroStatsButtonPanel.setVisible(false);

        swingyPanel.oldHeroPanel.setVisible(false);
        swingyPanel.oldChPanel.setVisible(false);
        swingyPanel.oldFieldPanel.setVisible(false);
        swingyPanel.oldButtonPanel.setVisible(false);

        swingyPanel.playerPanel.setVisible(false);
        swingyPanel.mainTextPanel.setVisible(false);
        swingyPanel.directionButtonPanel.setVisible(false);
    }

    public void showHeroStatsScreen() {
        swingyPanel.swingyNamePanel.setVisible(false);
        swingyPanel.welcomeNamePanel.setVisible(false);
        swingyPanel.startButtonPanel.setVisible(false);

        swingyPanel.choicePanel.setVisible(false);
        swingyPanel.choiceButtonPanel.setVisible(false);

        swingyPanel.newHeroPanel.setVisible(false);
        swingyPanel.heroFieldPanel.setVisible(false);
        swingyPanel.newHeroButtonPanel.setVisible(false);

        swingyPanel.newHeroTypePanel.setVisible(false);
        swingyPanel.heroTypeFieldPanel.setVisible(false);
        swingyPanel.heroTypeSelectPanel.setVisible(false);
        swingyPanel.newHeroTypeButtonPanel.setVisible(false);

        swingyPanel.heroStatsPanel.setVisible(true);
        swingyPanel.heroStatsButtonPanel.setVisible(true);

        swingyPanel.oldHeroPanel.setVisible(false);
        swingyPanel.oldChPanel.setVisible(false);
        swingyPanel.oldFieldPanel.setVisible(false);
        swingyPanel.oldButtonPanel.setVisible(false);

        swingyPanel.playerPanel.setVisible(false);
        swingyPanel.mainTextPanel.setVisible(false);
        swingyPanel.directionButtonPanel.setVisible(false);
    }

    public void showOldHeroScreen(){
        swingyPanel.swingyNamePanel.setVisible(false);
        swingyPanel.welcomeNamePanel.setVisible(false);
        swingyPanel.startButtonPanel.setVisible(false);

        swingyPanel.choicePanel.setVisible(false);
        swingyPanel.choiceButtonPanel.setVisible(false);

        swingyPanel.newHeroPanel.setVisible(false);
        swingyPanel.heroFieldPanel.setVisible(false);
        swingyPanel.newHeroButtonPanel.setVisible(false);

        swingyPanel.newHeroTypePanel.setVisible(false);
        swingyPanel.heroTypeFieldPanel.setVisible(false);
        swingyPanel.heroTypeSelectPanel.setVisible(false);
        swingyPanel.newHeroTypeButtonPanel.setVisible(false);

        swingyPanel.heroStatsPanel.setVisible(false);
        swingyPanel.heroStatsButtonPanel.setVisible(false);

        swingyPanel.oldHeroPanel.setVisible(true);
        swingyPanel.oldChPanel.setVisible(true);
        swingyPanel.oldFieldPanel.setVisible(true);
        swingyPanel.oldButtonPanel.setVisible(true);

        swingyPanel.playerPanel.setVisible(false);
        swingyPanel.mainTextPanel.setVisible(false);
        swingyPanel.directionButtonPanel.setVisible(false);
    }

    public void showGameScreen(){
        swingyPanel.swingyNamePanel.setVisible(false);
        swingyPanel.welcomeNamePanel.setVisible(false);
        swingyPanel.startButtonPanel.setVisible(false);

        swingyPanel.choicePanel.setVisible(false);
        swingyPanel.choiceButtonPanel.setVisible(false);

        swingyPanel.newHeroPanel.setVisible(false);
        swingyPanel.heroFieldPanel.setVisible(false);
        swingyPanel.newHeroButtonPanel.setVisible(false);

        swingyPanel.newHeroTypePanel.setVisible(false);
        swingyPanel.heroTypeFieldPanel.setVisible(false);
        swingyPanel.heroTypeSelectPanel.setVisible(false);
        swingyPanel.newHeroTypeButtonPanel.setVisible(false);

        swingyPanel.heroStatsPanel.setVisible(false);
        swingyPanel.heroStatsButtonPanel.setVisible(false);

        swingyPanel.oldHeroPanel.setVisible(false);
        swingyPanel.oldChPanel.setVisible(false);
        swingyPanel.oldFieldPanel.setVisible(false);
        swingyPanel.oldButtonPanel.setVisible(false);

        swingyPanel.playerPanel.setVisible(true);
        swingyPanel.mainTextPanel.setVisible(true);
        swingyPanel.directionButtonPanel.setVisible(true);
    }

}
