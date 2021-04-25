package input;
import java.io.*;

/**
 * Klasa służy do zapisu nazwy gracza oraz ilości uzysknych przez niego punktów do pliku txt
 */
public class SaveData{

    public SaveData(int score,String nick, String fileName)throws IOException{
        Writer output=new BufferedWriter(new FileWriter(fileName,true));
        output.append(nick+"\n"+score+"\n");
        output.close();
        }
}