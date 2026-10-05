package paagbi;

import javax.swing.JFileChooser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ListDirectoryToFile {
    public static void main(String[] args) {
        // JFileChooser erabili karpeta grafikoki aukeratzeko
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Aukeratu zerrendatu nahi duzun karpeta");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            Path folderPath = selectedFolder.toPath();

            // Edukia gordeko dugun fitxategia
            Path irteeraFitxategia = Path.of("karpeta_edukia.txt");

            try (BufferedWriter writer = Files.newBufferedWriter(irteeraFitxategia);
                 DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {

                writer.write("Aukeratutako karpeta: " + folderPath.toAbsolutePath());
                writer.newLine();
                writer.write("--------------------------------------------------");
                writer.newLine();

                for (Path entry : stream) {
                    String mota = Files.isDirectory(entry) ? "[DIR] " : "[FILE] ";
                    writer.write(mota + entry.getFileName().toString());
                    writer.newLine();
                }

                System.out.println("Edukia 'karpeta_edukia.txt' fitxategian gorde da arrakastaz!");

            } catch (IOException e) {
                System.err.println("Errorea fitxategia idaztean: " + e.getMessage());
            }
        } else {
            System.out.println("Ez da karpetarik aukeratu.");
        }
    }
}
