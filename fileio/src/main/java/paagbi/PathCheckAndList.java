package paagbi;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class PathCheckAndList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Erabiltzaileari bide absolutoa eskatu eta egiaztatu
        System.out.print("Sartu fitxategi edo direktorio baten bide absolutoa: ");
        String inputPath = scanner.nextLine();
        
        // Path.of() erabiltzen da (Paths.get() zaharkituta dago)
        Path path = Path.of(inputPath); 

        if (Files.exists(path)) {
            System.out.println("Egiaztatua: Bidea existitzen da fitxategi-sisteman!");
            
            // 2. Karpeta bada, lehen mailako edukia bistaratu
            if (Files.isDirectory(path)) {
                System.out.println("\n'" + path.getFileName() + "' karpetaren edukia (lehen maila):");
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                    for (Path entry : stream) {
                        String mota = Files.isDirectory(entry) ? "[KARPETA]" : "[FITXATEGIA]";
                        System.out.println("  " + mota + " " + entry.getFileName());
                    }
                } catch (IOException e) {
                    System.err.println("Errorea karpeta irakurtzean: " + e.getMessage());
                }
            } else {
                System.out.println("Sartutako bidea fitxategia da (ez karpeta).");
            }
        } else {
            System.out.println("Errorea: Bidea EZ da existitzen fitxategi-sisteman.");
        }

        scanner.close();
    }
}