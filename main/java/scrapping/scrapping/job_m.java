package scrapping;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class job_m {
    static String base_url = "https://www.m-job.ma/recherche?page=";
    static int page_number = 1;
    static int total_page = 24;

    public void scrappe() {
        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet();

            // Créer une ligne d'entêtes dans Excel
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                    "Titre", "URL", "Site Name", "Date de publication", "Date pour postuler",
                    "Adresse d'entreprise", "Site web d'entreprise", "Nom d'entreprise",
                    "Description d'entreprise", "Description du poste", "Région", "Ville",
                    "Secteur d'activité", "Métier", "Type du contrat", "Niveau d'études",
                    "Spécialité/ Diplôme", "Expérience", "Profil recherché", "Traits de personnalité",
                    "Compétences requises (hard skills)", "Soft-Skills", "Compétences recommandées",
                    "Langue", "Niveau de la langue", "Salaire", "Avantages sociaux", "Télétravail"
            };
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            int rowNum = 1;

            while (page_number <= total_page) {
                String url = base_url + page_number;

                try {
                    // Ajouter un délai aléatoire pour éviter le blocage
                    int randomDelay = ThreadLocalRandom.current().nextInt(1000, 5000); // 1 à 5 secondes
                    Thread.sleep(randomDelay);

                    Document document = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                            .get();

                    Elements elements = document.select("div.offers-boxes > div.offer-box");

                    if (elements.isEmpty()) {
                        System.out.println("Aucune offre trouvée sur la page " + page_number);
                        break;
                    }

                    for (Element element : elements) {
                        String link_details = element.select("h3 > a").attr("abs:href"); // URL absolue
                        String siteName = "https://www.m-job.ma";
                        // Ajouter un délai aléatoire avant de scraper la page des détails
                        randomDelay = ThreadLocalRandom.current().nextInt(1000, 5000); // 1 à 5 secondes
                        Thread.sleep(randomDelay);

                        try {
                            Document detailDoc = Jsoup.connect(link_details)
                                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                                    .get();

                            // Extraire les données
                            String title = detailDoc.select(".offer-title").text();
                            String ville = detailDoc.select(".location").text();
                            String entreprise_nom = detailDoc.select("ul.list-details > li:first-child > h3").text();
                            String typeContrat = detailDoc.select("ul.list-details > li:nth-child(2) > h3").text();
                            String salaire = detailDoc.select("ul.list-details > li:nth-child(3) > h3").text();
                            String descriptionPoste = detailDoc.select(".the-content > div:nth-child(4) > p").text();
                            String profilRecherche = detailDoc.select(".the-content > div:nth-child(6) > p").text();
                            String metier = detailDoc.select(".the-content > div:nth-child(10)").text();
                            String experience = detailDoc.select(".the-content > div:nth-child(12)").text();
                            String niveauxEtude = detailDoc.select(".the-content > div:nth-child(14)").text();
                            String secteurActivite = detailDoc.select(".the-content > div:nth-child(8)").text();
                            String langue = detailDoc.select("body > section.main-details > div > div > div.the-content > div:nth-child(16)").text();
                            String descriptionEntreprise = detailDoc.select("body > section.main-details > div > div > div.the-content > div:nth-child(2) > p").text();
                            String date_publication = detailDoc.select(".bottom-content > span").text();

                            // Insérer les données dans le fichier Excel
                            Row row = sheet.createRow(rowNum++);
                            String[] data = {
                                    title, link_details, siteName, date_publication, "Non diponible",
                                    "Non disponible", "Non disponible", entreprise_nom,
                                    descriptionEntreprise, descriptionPoste, "Non disponible", ville,
                                    secteurActivite, metier, typeContrat, niveauxEtude,
                                    "Non disponible", experience, profilRecherche, "Non disponible",
                                    "Non disponible", "Non disponible", "Non disponible", langue,
                                    "Non disponible", salaire, "Non disponible", "Non disponible"
                            };
                            for (int i = 0; i < data.length; i++) {
                                row.createCell(i).setCellValue(data[i] != null && !data[i].isEmpty() ? data[i] : "Non disponible");
                            }
                        } catch (IOException e) {
                            System.err.println("Erreur lors de la connexion à l'offre détaillée : " + e.getMessage());
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Erreur lors de la connexion à la page " + page_number + ": " + e.getMessage());
                }

                page_number++;
            }

            // Enregistrer le fichier Excel
            FileOutputStream fileOut = new FileOutputStream("offres_job_m_test.xlsx");
            wb.write(fileOut);
            fileOut.close();

            System.out.println("Les données ont été enregistrées dans offres_job_m_test.xlsx");

        } catch (IOException | InterruptedException e) {
            System.err.println("Erreur : " + e.getMessage());
        }


    }

    public static void main(String[] args) {
        job_m scrappe = new job_m();
        scrappe.scrappe();
    }
}
