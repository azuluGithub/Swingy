package swingy.dot.com.model.read_write;

import swingy.dot.com.controller.SwingyAppController;
import swingy.dot.com.model.color.Color;
import swingy.dot.com.model.heroes.SwingyHero;

import java.io.*;

public class FileWriting {

    private SwingyAppController appController;

    public FileWriting(SwingyAppController appController) {
        this.appController = appController;
    }

    public void addListToFile() {
        try {
            BufferedWriter bW = new BufferedWriter(new FileWriter("heroes.txt"));
            for (SwingyHero sh : appController.getFileReading().listHeroes) {
                bW.write(sh.getName() + "," + sh.getType() + "," + sh.getLevel() + "," + sh.getExperience() + "," + sh.getArmor() + "," + sh.getWeapon());
                bW.append('\n');
            }
            bW.flush();
            bW.close();
        } catch (IOException e) {
            System.err.println(Color.RED + "Error:: Problem encountered while writing to the file 'heroes.txt'" + Color.RESET);
            e.printStackTrace();
        }
    }

}
