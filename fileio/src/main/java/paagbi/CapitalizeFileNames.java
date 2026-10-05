package paagbi;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CapitalizeFileNames {
    public static void main(String[] args) {
        Path hasierakoKarpeta = Path.of("karpeta_berriak");

        try {
            prozesatuKarpeta(hasierakoKarpeta);
            System.out.println("Fitxategi guztiak maiuskulaz jarri dira!");
        } catch (IOException e) {
            System.err.println("Errorea prozesatzean: " + e.getMessage());
        }
    }

    public static void prozesatuKarpeta(Path dir) throws IOException {
        if (!Files.exists(dir) || !Files.isDirectory(dir)) return;

        // DirectoryStream erabili Glob ereduarekin ("*") edukia lortzeko
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*")) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    // Azpikarpetak errekurtsiboki aztertu
                    prozesatuKarpeta(entry);
                } else if (Files.isRegularFile(entry)) {
                    // Fitxategiaren lehen hizkia maiuskulaz jarri
                    renombrarMaiuskulaz(entry);
                }
            }
        }
    }

    private static void renombrarMaiuskulaz(Path file) throws IOException {
        String filename = file.getFileName().toString();
        
        if (filename.length() > 0 && Character.isLowerCase(filename.charAt(0))) {
            String izenBerria = Character.toUpperCase(filename.charAt(0)) + filename.substring(1);
            Path newPath = file.resolveSibling(izenBerria);
            
            Files.move(file, newPath);
            System.out.println("Izena aldatua: " + filename + " -> " + izenBerria);
        }
    }
}