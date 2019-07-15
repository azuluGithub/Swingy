package swingy.dot.com.model.enemies;

public class Danger extends SwingyEnemy {
    public Danger(String ename, int ex, int ey){
        super(ename,ex,ey);
        this.eArmor = "Plackart";
        this.eWeapon = "BladesOfChaos";
        this.eHelm = "Burgonet";
        this.eInitials = "D";
        characterEnemyStats();
    }
}