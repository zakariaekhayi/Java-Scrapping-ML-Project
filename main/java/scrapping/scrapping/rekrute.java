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

public class rekrute {

    static String base_url = "https://www.rekrute.com/fr/offres.html?s=3&p=";
    static int page_number = 1;
    static int total_page = 50;
    static String next_page_selector = ".pagination .next";

    public void scrappe() {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Offres Rekrute");

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
                    int randomDelay = ThreadLocalRandom.current().nextInt(1000, 5000);
                    Thread.sleep(randomDelay);

                    Document document = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                            .get();

                    Elements elements = document.select("ul.job-list2#post-data > li.post-id");

                    if (elements.isEmpty()) {
                        System.out.println("Aucune offre trouvée sur la page " + page_number);
                        break;
                    }

                    for (Element element : elements) {
                        String link_details = "https://www.rekrute.com" +element.select("h2 > a.titreJob").attr("href");
                        String datePublication =element.select("em > span:first-of-type").text();
                        String entreprise_nom = element.select(".col-xs-12 > a > img").attr("alt");


                        // Ajouter un délai aléatoire avant de scraper la page des détails
                        randomDelay = ThreadLocalRandom.current().nextInt(1000, 5000);
                        Thread.sleep(randomDelay);

                        try {
                            Document detailDoc = Jsoup.connect(link_details)
                                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                                    .get();

                            //Extraire les donnees
                            String titre = detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > h1").text();

                            String siteName= "rekrute.com";
                            String urlName = link_details;
                            String dateMAx =detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-12.col-sm-12.col-xs-12 > span > b").text();
                            String adresseEntreprise=detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div:nth-child(6) > #address").text();
                            String siteEntreprise =detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-2.col-sm-12.col-xs-12 > div > a").attr("href");
                            String descriptionEntreprise = detailDoc.select("#recruiterDescription > p").text();
                            String descriptionPoste= detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div:nth-child(4)").text();
                            String region = detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > ul:nth-child(3) > li:nth-child(2)").text();
                            String ville = "Non diponible";
                            String secteurActivite = detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > h2").text();
                            String metier = titre;
                            String typeContrat = detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > ul:nth-child(5) > li:nth-child(1) > span").text();
                            String niveauxEtude = detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > ul:nth-child(3) > li:nth-child(3)").text();
                            String specialiteDiplome = secteurActivite ;
                            String experience = detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > ul:nth-child(3) > li:nth-child(1)").text();
                            String profilRecherche = detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div:nth-child(5) > p").text();
                            String traitsPersonnalite = detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div:nth-child(7) > p > span").text();
                            String hardSkills = "Non disponible";
                            String softSkills = detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > div > p >span").text();
                            String competencesRecommandes = "Non diponible";
                            String longue = "Non disponible";
                            String niveauxLongue = "Non disponible";
                            String salaire = "Non disponible";
                            String avantageSociaux = "Non disponible";
                            String teletravail = detailDoc.select("#fortopscroll > div.container.anno > div:nth-child(2) > div > div.col-md-12.info.blc.noback > div > div > div.col-md-10.col-sm-12.col-xs-12 > ul:nth-child(5) > li:nth-child(2) > span").text();
                            if (teletravail.contains(":")) {
                                teletravail = teletravail.split(":")[1].trim();
                            } else {
                                teletravail = "Non disponible";
                            }


                            // Insérer les données dans le fichier Excel
                            Row row = sheet.createRow(rowNum++);
                            String[] data = {
                                    titre, urlName, siteName, datePublication, dateMAx,
                                    adresseEntreprise, siteEntreprise, entreprise_nom,
                                    descriptionEntreprise, descriptionPoste, region, ville,
                                    secteurActivite, metier, typeContrat, niveauxEtude,
                                    specialiteDiplome, experience, profilRecherche, traitsPersonnalite,
                                    hardSkills, softSkills, "Non disponible", "Non disponible",
                                    "Non disponible", "Non disponible", "Non disponible", teletravail
                            };
                            for (int i = 0; i < data.length; i++) {
                                row.createCell(i).setCellValue(data[i] != null && !data[i].isEmpty() ? data[i] : "Non disponible");
                            }

                        } catch (IOException e) {
                            System.err.println("Erreur lors de la connexion à l'offre détaillée : " + e.getMessage());
                        }
                    }

                    Elements nextPageLink = document.select(next_page_selector);
                    if (nextPageLink.isEmpty()) {
                        System.out.println("Aucune page suivante trouvée.");
                        break;
                    } else {
                        page_number++;
                    }

                } catch (IOException | InterruptedException e) {
                    System.err.println("Erreur lors de la connexion à la page " + page_number + ": " + e.getMessage());
                }
            }

            // Enregistrer le fichier Excel
            try (FileOutputStream fileOut = new FileOutputStream("offres_rekrute_test.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Les données ont été enregistrées dans offres_rekrute_test.xlsx");
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier Excel : " + e.getMessage());
        }
    }
}
