package paagbi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateDirectoryStructure {
    public static void main(String[] args) {
        // Eskatutako egituraren bideak
        Path base = Path.of("karpeta_berriak");

        Path arrainak = base.resolve("animaliak/arrainak");
        Path ugaztunak = base.resolve("animaliak/ugaztunak");
        Path barazkiak = base.resolve("elikagaiak/barazkiak");
        Path esnekiak = base.resolve("elikagaiak/esnekiak");

        try {
            // Files.createDirectories-ek karpeta eta guraso-karpeta guztiak sortzen ditu
            Files.createDirectories(arrainak);
            Files.createDirectories(ugaztunak);
            Files.createDirectories(barazkiak);
            Files.createDirectories(esnekiak);

            System.out.println("Karpeta egitura guztia ondo sortu da!");
        } catch (IOException e) {
            System.err.println("Errorea karpetak sortzean: " + e.getMessage());
        }
    }
}