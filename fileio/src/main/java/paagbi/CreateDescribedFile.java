package paagbi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CreateDescribedFile {
    public static void main(String[] args) {
        // try-with-resources erabiliz scanner-a automatikoki izteko
        try (Scanner scanner = new Scanner(System.in)) {

            // Erabiltzaileari galderak egin
            System.out.print("Zer zoaz deskribatzera? "); // Adibidez: ugaztunak
            String karpetaMota = scanner.nextLine().trim().toLowerCase();

            System.out.print("Zein? "); // Adibidez: tigrea
            String izena = scanner.nextLine().trim().toLowerCase();

            System.out.print("Nolakoa da? "); // Adibidez: Katu handi bat da...
            String deskribapena = scanner.nextLine();

            // Aurkitu dagokion karpeta 'karpeta_berriak' barruan
            Path karpetaBidea = BilatuKarpeta.aurkituKarpeta(Path.of("karpeta_berriak"), karpetaMota);

            if (karpetaBidea == null || !Files.exists(karpetaBidea)) {
                System.out.println("Errorea: Ez da '" + karpetaMota + "' karpeta aurkitu 'karpeta_berriak' egituran.");
                return;
            }

            // Fitxategiaren bidea osatu: .../ugaztunak/tigrea.txt
            Path fitxategiBidea = karpetaBidea.resolve(izena + ".txt");

            try {
                // Deskribapena fitxategian idatzi
                Files.writeString(fitxategiBidea, deskribapena);
                System.out.println("Fitxategia sortua: " + fitxategiBidea.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Errorea fitxategia idaztean: " + e.getMessage());
            }
        }
    }
}

class BilatuKarpeta {
    // Karpeta egituran bilatzeko laguntza-metodoa
    public static Path aurkituKarpeta(Path hasiera, String izena) {
        if (!Files.exists(hasiera)) return null;
        if (hasiera.getFileName().toString().equalsIgnoreCase(izena)) return hasiera;

        if (Files.isDirectory(hasiera)) {
            try (var stream = Files.newDirectoryStream(hasiera)) {
                for (Path entry : stream) {
                    if (Files.isDirectory(entry)) {
                        Path aurkitua = aurkituKarpeta(entry, izena);
                        if (aurkitua != null) return aurkitua;
                    }
                }
            } catch (IOException ignored) {}
        }
        return null;
    }
}