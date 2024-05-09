package utils;

import model.user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static void saveUser(File file, List<user> list){

        try{
            FileOutputStream exit = new FileOutputStream(file);
            ObjectOutputStream objectExit = new ObjectOutputStream(exit);
            objectExit.writeObject(list);
            objectExit.close();
        } catch (FileNotFoundException e){
            System.out.println("The file doesnt exist");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static List<user> getUser(File file){
        List<user> users = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInput ois = new ObjectInputStream(fis);
            users = (List<user>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no existe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
