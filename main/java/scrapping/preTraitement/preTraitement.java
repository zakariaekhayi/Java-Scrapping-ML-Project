package scrapping.preTraitement;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import scrapping.Activites;

import java.io.*;
import java.util.*;

public class preTraitement {

    public void normaliser(String inputFilePath, String outputFilePath, Activites activites,PosteListe posteListe) {
        try (FileInputStream fis = new FileInputStream(new File(inputFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Appliquer les trois traitements
//            normaliserDescription(workbook, activites);
//            normaliserVille(workbook);
//            normaliserPoste(workbook, posteListe);
//            normaliserNiveauEtude(workbook);
//            normaliserSecteurActivite(workbook);
//            normaliserExperience(workbook);
            normaliserSalaries(workbook);
            normaliserLangue(workbook);

            // Écrire le fichier de sortie
            try (FileOutputStream fos = new FileOutputStream(new File(outputFilePath))) {
                workbook.write(fos);
            }

            System.out.println("Tous les traitements ont été appliqués avec succès. Fichier généré : " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void normaliserDescription(Workbook workbook, Activites marketActivities) {
        Sheet sheet = workbook.getSheetAt(0);

        Row headerRow = sheet.getRow(0);
        int descriptionColumnIndex = findColumnIndex(headerRow, "Description_entreprise");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell descriptionCell = row.getCell(descriptionColumnIndex);
                String description = descriptionCell != null ? descriptionCell.getStringCellValue().trim() : "";

                if (!description.isEmpty()) {
                    String mainActivity = detectMainActivity(description, marketActivities);
                    if (descriptionCell == null) {
                        descriptionCell = row.createCell(descriptionColumnIndex);
                    }
                    descriptionCell.setCellValue(mainActivity);
                }
            }
        }
        System.out.println("Traitement des descriptions terminé.");
    }

    private void normaliserPoste(Workbook workbook, PosteListe marketPoste) {
        Sheet sheet = workbook.getSheetAt(0);

        Row headerRow = sheet.getRow(0);
        int posteColumnIndex = findColumnIndex(headerRow, "Description_poste");
        int titreColumnIndex = findColumnIndex(headerRow, "Titre");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell titreCell = row.getCell(titreColumnIndex);
                Cell posteCell = row.getCell(posteColumnIndex);
                String titre = titreCell != null ? titreCell.getStringCellValue().trim() : "";
                String descriptionPoste = posteCell != null ? posteCell.getStringCellValue().trim() : "";

                String nouveauPoste;
                if (titre.equalsIgnoreCase("Non disponible")) {
                    nouveauPoste = detectPoste(descriptionPoste, marketPoste);
                } else {
                    nouveauPoste = titre.contains("-") ? titre.split("-")[0].trim() : titre;
                }

                if (posteCell == null) {
                    posteCell = row.createCell(posteColumnIndex);
                }
                posteCell.setCellValue(nouveauPoste);
            }
        }
        System.out.println("Traitement des postes terminé.");
    }

    private void normaliserVille(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);

        Row headerRow = sheet.getRow(0);
        int regionColumnIndex = findColumnIndex(headerRow, "Region");
        int villeColumnIndex = findOrCreateColumn(headerRow, "Ville");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell regionCell = row.getCell(regionColumnIndex);
                String region = regionCell != null ? regionCell.getStringCellValue().trim() : "";

                String detectedVille = DetectVille(region);
                Cell villeCell = row.getCell(villeColumnIndex);
                if (villeCell == null) {
                    villeCell = row.createCell(villeColumnIndex);
                }
                villeCell.setCellValue(detectedVille);
            }
        }
        System.out.println("Traitement des villes terminé.");
    }

    private int findColumnIndex(Row headerRow, String columnName) {
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("La colonne '" + columnName + "' est introuvable.");
    }

    private int findOrCreateColumn(Row headerRow, String columnName) {
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        int newColumnIndex = headerRow.getLastCellNum();
        headerRow.createCell(newColumnIndex).setCellValue(columnName);
        return newColumnIndex;
    }

    private String detectMainActivity(String description, Activites marketActivities) {
        for (Map.Entry<String, List<String>> entry : marketActivities.initializeActivities().entrySet()) {
            String sector = entry.getKey();
            List<String> keywords = entry.getValue();

            for (String keyword : keywords) {
                if (description.toLowerCase().contains(keyword.toLowerCase())) {
                    return sector;
                }
            }
        }
        return "Non identifié";
    }

    private String detectPoste(String description_poste, PosteListe marketPostes) {
        for (Map.Entry<String, List<String>> entry : marketPostes.initialiserPostes().entrySet()) {
            String sector = entry.getKey();
            List<String> keywords = entry.getValue();

            for (String keyword : keywords) {
                if (description_poste.toLowerCase().contains(keyword.toLowerCase())) {
                    return sector;
                }
            }
        }
        return "Non identifié";
    }

    public String DetectVille(String region) {
        List<String> villes = VilleMaroc.getVillesMaroc();
        for (String ville : villes) {
            if (region.toLowerCase().contains(ville.toLowerCase())) {
                return ville;
            }
        }
        return "Casablanca";
    }
    private void normaliserNiveauEtude(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);

        // Trouver la colonne "Niveau_etudes"
        Row headerRow = sheet.getRow(0);
        int niveauEtudeColumnIndex = findColumnIndex(headerRow, "Niveau_etudes");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell niveauEtudeCell = row.getCell(niveauEtudeColumnIndex);
                String niveauEtude = niveauEtudeCell != null ? niveauEtudeCell.getStringCellValue().trim() : "";

                // Normalisation des valeurs
                String niveauNormalise;
                if (niveauEtude.toLowerCase().contains("bac +2") || niveauEtude.toLowerCase().contains("bac+2") || niveauEtude.toLowerCase().contains("bac+ 2")) {
                    niveauNormalise = "Bac +2";
                } else if (niveauEtude.toLowerCase().contains("bac +3") ||niveauEtude.toLowerCase().contains("bac+3") || niveauEtude.toLowerCase().contains("bac+ 3")) {
                    niveauNormalise = "Bac +3";
                } else if (niveauEtude.toLowerCase().contains("bac +5") ||niveauEtude.toLowerCase().contains("bac+5") || niveauEtude.toLowerCase().contains("bac+ 5")) {
                    niveauNormalise = "Bac +5";
                } else if (niveauEtude.toLowerCase().contains("qualification avant bac")) {
                    niveauNormalise = "Qualification avant Bac";
                } else if (niveauEtude.toLowerCase().contains("non disponible")) {
                    niveauNormalise = "Non spécifié";
                } else {
                    niveauNormalise = "Autre";
                }

                // Mettre à jour la cellule
                if (niveauEtudeCell == null) {
                    niveauEtudeCell = row.createCell(niveauEtudeColumnIndex);
                }
                niveauEtudeCell.setCellValue(niveauNormalise);
            }
        }

        System.out.println("Normalisation de la colonne Niveau_etudes terminée.");
    }
    public void normaliserSecteurActivite(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);


            Row headerRow = sheet.getRow(0);

            // Identifier la colonne "secteur_activite"
            int secteurActiviteColumnIndex = -1;
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("Secteur_activite")) {
                    secteurActiviteColumnIndex = i;
                    break;
                }
            }

            if (secteurActiviteColumnIndex == -1) {
                throw new IllegalArgumentException("La colonne 'secteur_activite' est introuvable.");
            }

            // Parcourir les lignes pour normaliser les données
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell secteurCell = row.getCell(secteurActiviteColumnIndex);
                    String secteurActivite = "";

                    // Lire la valeur actuelle
                    if (secteurCell != null && secteurCell.getCellType() == CellType.STRING) {
                        secteurActivite = secteurCell.getStringCellValue().trim();
                    }

                    // Normaliser la valeur
                    String secteurNormalise = normaliserSecteur(secteurActivite);

                    // Écrire la valeur normalisée
                    if (secteurCell == null) {
                        secteurCell = row.createCell(secteurActiviteColumnIndex);
                    }
                    secteurCell.setCellValue(secteurNormalise);
                }
            }


            System.out.println("Normalisation terminée. Fichier généré : ");


    }

    // Méthode pour normaliser un secteur
    private String normaliserSecteur(String secteurActivite) {
        int slashIndex = secteurActivite.indexOf("/");
        if (secteurActivite == null || secteurActivite.isEmpty() || secteurActivite.equalsIgnoreCase("Non disponible")) {
            return "Non disponible";
        }
        if(slashIndex==-1){
            return secteurActivite ;
        }


            return secteurActivite.substring(0, slashIndex).trim(); // Extraire après "Secteur"



    }
    public void normaliserExperience(Workbook workbook){
        Sheet sheet = workbook.getSheetAt(0);


        Row headerRow = sheet.getRow(0);

        // Identifier la colonne "experience"
        int secteurActiviteColumnIndex = -1;
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("Experience")) {
                secteurActiviteColumnIndex = i;
                break;
            }
        }

        if (secteurActiviteColumnIndex == -1) {
            throw new IllegalArgumentException("La colonne 'Experience' est introuvable.");
        }

        // Parcourir les lignes pour normaliser les données
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell secteurCell = row.getCell(secteurActiviteColumnIndex);
                String experience = "";

                // Lire la valeur actuelle
                if (secteurCell != null && secteurCell.getCellType() == CellType.STRING) {
                    experience = secteurCell.getStringCellValue().trim();
                }

                // Normaliser la valeur
                String secteurNormalise = normaliserexperince(experience);

                // Écrire la valeur normalisée
                if (secteurCell == null) {
                    secteurCell = row.createCell(secteurActiviteColumnIndex);
                }
                secteurCell.setCellValue(secteurNormalise);
            }
        }

    }
    public String normaliserexperince(String experience){
        if(experience.toLowerCase().contains("de 3 à 5 ans")){
            return "Intermediaire";
        }
        if(experience.toLowerCase().contains("de 1 à 3 ans") || experience.toLowerCase().contains("débutant") || experience.toLowerCase().contains("moins de 1 an") || experience.toLowerCase().contains("fraichement diplômé") || experience.toLowerCase().contains("bac")  || experience.toLowerCase().contains("arabe") || experience.toLowerCase().contains("français")){
            return "Debutant";
        }
        if(experience.toLowerCase().contains("de 5 à 10 ans") || experience.toLowerCase().contains("de 7 à 10 ans") || experience.toLowerCase().contains("de de 5 à 7 ans") ){
            return "Senior";
        }
        if(experience.toLowerCase().contains("plus de 10 ans") || experience.toLowerCase().contains("de 10 à 20 ans")){
            return "Expert ";
        }


        return "Non disponible";

    }

    public void normaliserSalaries(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);

        // Trouver ou créer la colonne "Salaire Normalisé"
        Row headerRow = sheet.getRow(0);
        // Identifier la colonne "experience"
        int salaryColumnIndex = findColumnIndex(headerRow,"Salaire");
        int experienceColumnIndex=findColumnIndex(headerRow,"Experience");



        if (experienceColumnIndex == -1) {
            throw new IllegalArgumentException("La colonne 'Experience' est introuvable.");
        }
        if (salaryColumnIndex == -1) {
            throw new IllegalArgumentException("La colonne 'Experience' est introuvable.");
        }

        // Parcourir les lignes pour normaliser les salaires
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell experienceCell = row.getCell(experienceColumnIndex);
                String experience = experienceCell != null ? experienceCell.getStringCellValue().trim().toLowerCase() : "";

                // Générer un salaire basé sur l'expérience
                int normalizedSalary = generateSalary(experience);

                // Mettre à jour la colonne "Salaire Normalisé"
                Cell salaryCell = row.getCell(salaryColumnIndex);
                if (salaryCell == null) {
                    salaryCell = row.createCell(salaryColumnIndex);
                }
                salaryCell.setCellValue(normalizedSalary);
            }
        }
        System.out.println("Normalisation des salaires terminée.");
    }

    private int generateSalary(String experience) {
        Random random = new Random();
        int min = 0, max = 0;

        switch (experience) {
            case "debutant":
                min = 5000;
                max = 9000;
                break;
            case "intermediare":
                min = 10000;
                max = 14000;
                break;
            case "senior":
                min = 15000;
                max = 20000;
                break;
            case "expert":
                min = 25000;
                max = 50000;
                break;
            default:
                return 0; // Retourner 0 si l'expérience est invalide
        }

        // Générer un salaire aléatoire entre min et max avec un incrément de 200
        int range = (max - min) / 200 + 1;
        return min + random.nextInt(range) * 200;
    }

    public void normaliserLangue(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);

        // Trouver ou créer la colonne "Langue Normalisée"
        Row headerRow = sheet.getRow(0);
        int langueColumnIndex = findColumnIndex(headerRow, "Langue");


        // Parcourir les lignes pour normaliser les langues
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell langueCell = row.getCell(langueColumnIndex);
                String langue = langueCell != null ? langueCell.getStringCellValue().trim().toLowerCase() : "";

                // Générer une langue aléatoire
                String normalizedLangue = generateRandomLangue();

                // Mettre à jour la colonne "Langue Normalisée"
                Cell normalizedLangueCell = row.getCell(langueColumnIndex);
                if (normalizedLangueCell == null) {
                    normalizedLangueCell = row.createCell(langueColumnIndex);
                }
                normalizedLangueCell.setCellValue(normalizedLangue);
            }
        }
        System.out.println("Normalisation des langues terminée.");
    }

    private String generateRandomLangue() {
        Random random = new Random();
        String[] langues = {"Français", "Anglais","Anglais Français"};
        return langues[random.nextInt(langues.length)];
    }




    public static void main(String[] args) {
        String inputFilePath = "final_clean.xlsx";
        String outputFilePath = "final_clean2.xlsx";
        preTraitement traite = new preTraitement();
        Activites activites = new Activites();
        PosteListe posteListe = new PosteListe();


        traite.normaliser(inputFilePath, outputFilePath, activites, posteListe);
    }
}
