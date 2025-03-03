package scrapping.scrap;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.Map;

public class ExportToExcel {

    public static void exportJobMapToExcel(Map<String, jobDetails> jobMap, String fileName) {
        // Création du classeur Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Job Details");

        // Style des en-têtes
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        // Ajouter les en-têtes de colonne
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {"Job Title", "Experience Level", "Function", "Activity", "Niveau d'Étude"};
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
            cell.setCellStyle(headerStyle);
        }

        // Ajouter les données
        int rowNum = 1;
        for (Map.Entry<String, jobDetails> entry : jobMap.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            jobDetails details = entry.getValue();

            row.createCell(0).setCellValue(entry.getKey()); // Job Title
            row.createCell(1).setCellValue(details.getExperienceLevel()); // Experience Level
            row.createCell(2).setCellValue(details.getFunction()); // Function
            row.createCell(3).setCellValue(details.getActivity()); // Activity
            row.createCell(4).setCellValue(details.getNiveauEtude()); // Niveau d'Étude
        }

        // Ajuster automatiquement la largeur des colonnes
        for (int i = 0; i < columnHeaders.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Écrire dans le fichier
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
            System.out.println("Données exportées avec succès dans " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

