package swingy.dot.com.model.enemies;

public class Vergetta extends SwingyEnemy {
    public Vergetta(String ename, int ex, int ey){
        super(ename,ex,ey);
        this.eArmor = "ScorpionSuit";
        this.eWeapon = "GoldenGun";
        this.eHelm = "Bascinet";
        this.eInitials = "V";
        characterEnemyStats();
    }
}
