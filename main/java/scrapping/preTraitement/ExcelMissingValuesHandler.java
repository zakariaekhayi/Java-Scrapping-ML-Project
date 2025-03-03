package preTraitement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Classe principale pour le traitement des valeurs manquantes
class ExcelMissingValuesHandler {
    private static final String[] MISSING_VALUES = {"Non disponible", "Non identifié","Non spécifié"};
    private Map<Integer, ProcessingStats> columnStats = new HashMap<>();

    public Map<Integer, ProcessingStats> getColumnStats() {
        return columnStats;
    }

    public void processExcelFile(String inputPath, String outputPath) {
        try (FileInputStream fis = new FileInputStream(inputPath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            // Traiter chaque colonne
            for (int columnIndex = 0; columnIndex < headerRow.getLastCellNum(); columnIndex++) {
                columnStats.put(columnIndex, new ProcessingStats());

                if (isNumericColumn(sheet, columnIndex)) {
                    processNumericColumn(sheet, columnIndex);
                } else {
                    processCategoricalColumn(sheet, columnIndex);
                }
            }

            // Sauvegarder les modifications
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                workbook.write(fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNumericColumn(Sheet sheet, int columnIndex) {
        int numericCount = 0;
        int totalCount = 0;

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null && !isMissingValue(cell)) {
                    totalCount++;
                    if (cell.getCellType() == CellType.NUMERIC) {
                        numericCount++;
                    }
                }
            }
        }

        boolean isNumeric = totalCount > 0 && (double) numericCount / totalCount > 0.7;
        columnStats.get(columnIndex).setNumeric(isNumeric);
        return isNumeric;
    }

    private void processNumericColumn(Sheet sheet, int columnIndex) {
        List<Double> validValues = new ArrayList<>();
        ProcessingStats stats = columnStats.get(columnIndex);

        // Première passe : collecter les valeurs valides
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header
            Cell cell = row.getCell(columnIndex);
            if (cell != null) {
                if (isMissingValue(cell)) {
                    stats.incrementMissingCount();
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    validValues.add(cell.getNumericCellValue());
                }
            } else {
                stats.incrementMissingCount();
            }
        }

        // Calculer la médiane
        double medianValue = calculateMedian(validValues);
        stats.setReplacementValue(String.valueOf(medianValue));

        // Deuxième passe : remplacer les valeurs manquantes
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header
            Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (isMissingValue(cell)) {
                cell.setCellValue(medianValue);
                stats.incrementReplacedCount();
            }
        }
    }

    private void processCategoricalColumn(Sheet sheet, int columnIndex) {
        Map<String, Integer> valueFrequency = new HashMap<>();
        ProcessingStats stats = columnStats.get(columnIndex);

        // Première passe : compter les fréquences
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header
            Cell cell = row.getCell(columnIndex);
            if (cell != null) {
                if (isMissingValue(cell)) {
                    stats.incrementMissingCount();
                } else {
                    String value = cell.toString().trim();
                    valueFrequency.merge(value, 1, Integer::sum);
                }
            } else {
                stats.incrementMissingCount();
            }
        }

        // Trouver la valeur la plus fréquente
        String mostFrequentValue = valueFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
        stats.setReplacementValue(mostFrequentValue);

        // Deuxième passe : remplacer les valeurs manquantes
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header
            Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (isMissingValue(cell)) {
                cell.setCellValue(mostFrequentValue);
                stats.incrementReplacedCount();
            }
        }
    }

    private boolean isMissingValue(Cell cell) {
        if (cell == null) return true;

        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue() == 0;

            case STRING:
                String value = cell.getStringCellValue().trim();
                return value.isEmpty() || Arrays.asList(MISSING_VALUES).contains(value);

            case BLANK:
                return true;

            default:
                return false;
        }
    }

    private double calculateMedian(List<Double> values) {
        if (values.isEmpty()) {
            return 0.0;
        }

        Collections.sort(values);
        int size = values.size();

        if (size % 2 == 0) {
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2;
        } else {
            return values.get(size / 2);
        }
    }
}

// Classe pour stocker les statistiques de traitement
class ProcessingStats {
    private boolean isNumeric;
    private int missingCount = 0;
    private int replacedCount = 0;
    private String replacementValue;

    public void setNumeric(boolean numeric) {
        isNumeric = numeric;
    }

    public void incrementMissingCount() {
        missingCount++;
    }

    public void incrementReplacedCount() {
        replacedCount++;
    }

    public void setReplacementValue(String value) {
        replacementValue = value;
    }

    public String getColumnType() {
        return isNumeric ? "Numérique" : "Catégorielle";
    }

    public int getMissingCount() {
        return missingCount;
    }

    public int getReplacedCount() {
        return replacedCount;
    }

    public String getReplacementValue() {
        return replacementValue;
    }
    public static void main(String[] args) {
        // Définir les chemins des fichiers
        String inputFile = "final_clean2.xlsx";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String outputFile = "donnees_output_" + timestamp + ".xlsx";

        // Créer une instance du gestionnaire
        ExcelMissingValuesHandler handler = new ExcelMissingValuesHandler();

        try {
            // Vérifier si le fichier d'entrée existe
            File input = new File(inputFile);
            if (!input.exists()) {
                System.err.println("Erreur : Le fichier " + inputFile + " n'existe pas.");
                return;
            }

            // Afficher le début du traitement
            System.out.println("Début du traitement des valeurs manquantes...");
            System.out.println("Fichier d'entrée : " + inputFile);
            System.out.println("Fichier de sortie : " + outputFile);

            // Mesurer le temps d'exécution
            long startTime = System.currentTimeMillis();

            // Traiter le fichier
            handler.processExcelFile(inputFile, outputFile);

            // Calculer le temps d'exécution
            long endTime = System.currentTimeMillis();
            double duration = (endTime - startTime) / 1000.0;

            // Afficher les statistiques
            System.out.println("\nStatistiques par colonne :");
            handler.getColumnStats().forEach((columnIndex, stats) -> {
                System.out.println("\nColonne " + (columnIndex + 1) + " :");
                System.out.println("- Type : " + stats.getColumnType());
                System.out.println("- Valeurs manquantes trouvées : " + stats.getMissingCount());
                System.out.println("- Valeurs remplacées : " + stats.getReplacedCount());
                System.out.println("- Valeur de remplacement : " + stats.getReplacementValue());
            });

            System.out.println("\nTraitement terminé avec succès !");
            System.out.println("Temps d'exécution : " + duration + " secondes");
            System.out.println("Le fichier traité a été sauvegardé : " + outputFile);

        } catch (Exception e) {
            System.err.println("\nErreur lors du traitement : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

