package swingy.dot.com.model.enemies;

public class Goblin extends SwingyEnemy {
    public Goblin(String ename, int ex, int ey){
        super(ename,ex,ey);
        this.eArmor = "Faulds";
        this.eWeapon = "BusterSword";
        this.eHelm = "Cervelliere";
        this.eInitials = "G";
        characterEnemyStats();
    }
}
