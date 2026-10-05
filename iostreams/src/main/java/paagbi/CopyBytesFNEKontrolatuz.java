package paagbi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CopyBytesFNEKontrolatuz {

    public static void main(String[] args) {
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("Fitxategiak/iostreams/xanadu.txt");
            out = new FileOutputStream("Fitxategiak/iostreams/outagain.txt");

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fitxategia ez da aurkitu: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Errorea fitxategia kopiatzean: " + e.getMessage());

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.out.println("Errorea fitxategiak ixtean: " + e.getMessage());
            }
        }
    }
}