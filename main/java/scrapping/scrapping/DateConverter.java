package scrapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateConverter {
    public static void main(String[] args) {
        // Exemple de chaîne de caractères
        String input = "L'offre a été publiée il y a 23 heures avant sur le site.";

        // Appeler la fonction
        String resultDate = convertToDate(input);
        if (resultDate != null) {
            System.out.println("Date convertie : " + resultDate);
        } else {
            System.out.println("Impossible de convertir la chaîne.");
        }
    }

    public static String convertToDate(String input) {
        try {
            // Extraire le nombre d'heures avec une expression régulière
            Pattern pattern = Pattern.compile("(\\d+) heures?");
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                // Récupérer le nombre d'heures
                int hoursAgo = Integer.parseInt(matcher.group(1));

                // Calculer la date en soustrayant les heures
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime resultDateTime = now.minusHours(hoursAgo);

                // Formatter uniquement la date sans l'heure
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return resultDateTime.format(formatter);
            } else {
                System.out.println("Aucune information temporelle trouvée dans la chaîne.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

