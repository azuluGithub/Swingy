package swingy.dot.com.model.enemies;

public class Undertaker extends SwingyEnemy {
    public Undertaker(String ename, int ex, int ey){
        super(ename,ex,ey);
        this.eArmor = "Octocamo";
        this.eWeapon = "CrowBar";
        this.eHelm = "Crusader";
        this.eInitials = "U";
        characterEnemyStats();
    }
}
