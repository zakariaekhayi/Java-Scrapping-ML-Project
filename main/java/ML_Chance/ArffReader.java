package ML_Chance;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArffReader {
    public static int[] readLastDataLine(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String lastDataLine = null;

            // Lire jusqu'à la section @data
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("@data")) {
                    break;
                }
            }

            // Lire la dernière ligne de données
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lastDataLine = line;
                }
            }

            if (lastDataLine != null) {
                String[] values = lastDataLine.split(",");
                int[] result = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    result[i] = Integer.parseInt(values[i]);
                }
                return result;
            }

            throw new IOException("Aucune donnée trouvée dans le fichier ARFF");
        }
    }
}