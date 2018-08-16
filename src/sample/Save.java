package sample;

import com.oracle.javafx.jmx.json.JSONDocument;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Nefast on 29/02/16.
 */
public class Save {
    private File saveFile;
    private Scanner readIn;
    private PrintWriter writeOut;

    private String name;
    private int lvl;
    private int xp;
    private int type;
    private int roomLvl;
    private Room room;



    public Save(){
        this.saveFile = new File("save.txt");
    }

    public void open() throws Exception{
        try {
            FileReader rfile = new FileReader(this.saveFile);
            BufferedReader bfile = new BufferedReader(rfile);
            this.readIn = new Scanner(bfile);
        }
        catch(FileNotFoundException e) {
            System.out.println("Le fichier " + this.saveFile.getName() + "n'existe pas");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void openWrite() throws Exception{
        try {
            FileWriter wfile = new FileWriter(this.saveFile,true);
            BufferedWriter wbuffer = new BufferedWriter(wfile);
            this.writeOut = new PrintWriter(wbuffer);
        }
        catch(FileNotFoundException e) {
            System.out.println("Le fichier " + this.saveFile.getName() + "n'existe pas");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public boolean load() throws Exception{
        String text = "";
        String[] split;

        try{
            while(this.readIn.hasNextLine()) {
                text = this.readIn.nextLine();
            }
        }catch (Exception e){
            return false;
        }
        split = text.split("/");
        this.setLvl(Integer.parseInt(split[0]));
        this.setXp(Integer.parseInt(split[1]));
        this.setName(split[2]);
        this.setType(Integer.parseInt(split[3]));
        this.setRoomLvl(Integer.parseInt(split[4]));
        return true;
    }

    public void save(Personnage me, Room room, int choosenClass){
        String result = me.getLvl() + "/" + me.getXp() + "/" + me.getName() + "/" + choosenClass + "/" + room.getRoomLvl();
        try{
            this.writeOut.println(result);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void close(){
        try {
            this.readIn.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeWrite(){
        try {
            this.writeOut.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public File getSaveFile() {
        return saveFile;
    }

    public void setSaveFile(File saveFile) {
        this.saveFile = saveFile;
    }

    public Scanner getReadIn() {
        return readIn;
    }

    public void setReadIn(Scanner readIn) {
        this.readIn = readIn;
    }

    public PrintWriter getWriteOut() {
        return writeOut;
    }

    public void setWriteOut(PrintWriter writeOut) {
        this.writeOut = writeOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getRoomLvl() {
        return roomLvl;
    }

    public void setRoomLvl(int roomLvl) {
        this.roomLvl = roomLvl;
    }
}
