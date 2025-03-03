package BD;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InsertDB {
    // URL de la base de données
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projetscrappingai";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        String excelFilePath = "donnees_output_20241226_020008.xlsx"; // Remplacez par le chemin de votre fichier Excel
        String insertSQL = "INSERT INTO offres (id, Titre, URL, Site_name, Date_publication, Date_postuler, "
                + "Adresse_entreprise, Site_web_entreprise, Nom_entreprise, Description_entreprise, "
                + "Description_poste, Region, Ville, Secteur_activite, Metier, Type_contrat, "
                + "Niveau_etudes, Specialite_diplome, Experience, Profil_recherche, Traits_personnalite, "
                + "hard_skills, Soft_skills, Competences_recommandees, Langue, Salaire, Teletravail) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {

            XSSFSheet sheet = workbook.getSheetAt(0); // Accéder à la première feuille
            int idCounter = 1; // Initialiser le compteur d'ID
            for (Row row : sheet) {
                // Ignorer la première ligne si elle contient les en-têtes
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Ajouter l'ID généré
                pstmt.setInt(1, idCounter++);

                pstmt.setString(2, getCellValue(row.getCell(0))); // Titre
                pstmt.setString(3, getCellValue(row.getCell(1))); // URL
                pstmt.setString(4, getCellValue(row.getCell(2))); // Site_name
                pstmt.setString(5, getCellValue(row.getCell(3))); // Date_publication
                pstmt.setString(6, getCellValue(row.getCell(4))); // Date_postuler
                pstmt.setString(7, getCellValue(row.getCell(5))); // Adresse_entreprise
                pstmt.setString(8, getCellValue(row.getCell(6))); // Site_web_entreprise
                pstmt.setString(9, getCellValue(row.getCell(7))); // Nom_entreprise
                pstmt.setString(10, getCellValue(row.getCell(8))); // Description_entreprise
                pstmt.setString(11, getCellValue(row.getCell(9))); // Description_poste
                pstmt.setString(12, getCellValue(row.getCell(10))); // Region
                pstmt.setString(13, getCellValue(row.getCell(11))); // Ville
                pstmt.setString(14, getCellValue(row.getCell(12))); // Secteur_activite
                pstmt.setString(15, getCellValue(row.getCell(13))); // Metier
                pstmt.setString(16, getCellValue(row.getCell(14))); // Type_contrat
                pstmt.setString(17, getCellValue(row.getCell(15))); // Niveau_etudes
                pstmt.setString(18, getCellValue(row.getCell(16))); // Specialite_diplome
                pstmt.setString(19, getCellValue(row.getCell(17))); // Experience
                pstmt.setString(20, getCellValue(row.getCell(18))); // Profil_recherche
                pstmt.setString(21, getCellValue(row.getCell(19))); // Traits_personnalite
                pstmt.setString(22, getCellValue(row.getCell(20))); // hard_skills
                pstmt.setString(23, getCellValue(row.getCell(21))); // Soft_skills
                pstmt.setString(24, getCellValue(row.getCell(22))); // Competences_recommandees
                pstmt.setString(25, getCellValue(row.getCell(23))); // Langue

                // Vérifier la cellule du salaire
                String salaireString = getCellValue(row.getCell(24));
                if (salaireString.isEmpty()) {
                    pstmt.setFloat(26, 0); // Valeur par défaut si le salaire est vide
                } else {
                    try {
                        pstmt.setFloat(26, Float.parseFloat(salaireString)); // Salaire
                    } catch (NumberFormatException e) {
                        pstmt.setFloat(26, 0); // Valeur par défaut si la conversion échoue
                    }
                }


                pstmt.setString(27, getCellValue(row.getCell(25))); // Teletravail

                pstmt.addBatch(); // Ajouter à la batch

                // Exécuter la batch tous les 1000 enregistrements pour éviter les problèmes de mémoire
                if (row.getRowNum() % 1000 == 0) {
                    pstmt.executeBatch();
                }
            }
            pstmt.executeBatch(); // Exécuter le reste des enregistrements
            System.out.println("Insertion terminée !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir la valeur d'une cellule en tant que String
    private static String getCellValue(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
