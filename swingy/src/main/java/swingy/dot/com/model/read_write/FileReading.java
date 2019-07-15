package swingy.dot.com.model.read_write;

import swingy.dot.com.model.color.Color;
import swingy.dot.com.model.factory.SwingyFactory;
import swingy.dot.com.model.heroes.SwingyHero;

import java.io.*;
import java.util.*;

public class FileReading {

    public List<SwingyHero> listHeroes = new ArrayList<>();

    public void readFile() {
        try {
            File file = new File("heroes.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);

            if (file.length() != 0) {
                int i = 0;
                String s;
                while ((s = br.readLine()) != null) {
                    i = i + 1;
                    String[] str = s.split(",");
                    if (str.length != 6)
                        throw new CustomExceptions("Error on Line " + i + ": Number of parameters must be equal to 5");
                    checkErrors(s.split(",")[0],  s.split(",")[1], s.split(",")[2], s.split(",")[3], s.split(",")[4], s.split(",")[5], i);
                    listHeroes.add(SwingyFactory.newSwingyHero(s.split(",")[0],
                            s.split(",")[1],
                            Integer.parseInt(s.split(",")[2]),
                            Integer.parseInt(s.split(",")[3]),
                            s.split(",")[4],
                            s.split(",")[5]));
                }
                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(Color.RED + "Error:: \"heroes.txt\" was'nt found. Please include the text file" + Color.RESET);
            System.exit(1);
        } catch (Exception e) {
            System.out.println(Color.RED + e.getMessage() + Color.RESET);
            System.exit(1);
        }
    }

    private void checkErrors(String name, String type, String level, String experience, String armor, String weapon, int lineNum)throws CustomExceptions {
        if (!name.matches("^[a-zA-Z]*$") && !name.equals(" ")) {
            throw new CustomExceptions("Error on Line " + lineNum + ":: Name must be a Word");
        } else if (name.isEmpty()) {
            throw new CustomExceptions("Error on Line " + lineNum + ":: Name field is Empty");
        } else if (!type.matches("^[a-zA-Z]*$") && !type.equals(" ")){
            throw new CustomExceptions("Error on Line " + lineNum + ":: Type must be a Word");
        } else if (level.equals(" ") || level.matches("^[a-zA-Z]*$")) {
            throw new CustomExceptions("Error on Line " + lineNum + ":: Level must be a number");
        } else if (experience.equals(" ") || experience.matches("^[a-zA-Z]*$")) {
            throw new CustomExceptions("Error on Line " + lineNum + ":: Experience must be a number");
        } else if (!armor.matches("^[a-zA-Z]*$") && !armor.equals(" ")){
            throw new CustomExceptions("Error on Line " + lineNum + ":: Armor must be a Word");
        } else if (!weapon.matches("^[a-zA-Z]*$") && !weapon.equals(" ")){
            throw new CustomExceptions("Error on Line " + lineNum + ":: Weapon must be a Word");
        }
    }

}

