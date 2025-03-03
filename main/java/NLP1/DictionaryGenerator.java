package NLP1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DictionaryGenerator {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public DictionaryGenerator(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public void generateDictionary(String outputFile) throws SQLException, IOException {
        Map<String, List<String>> dictionary = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String[] columns = {"type_contrat", "ville", "Specialite_diplome"};

            for (String column : columns) {
                List<String> values = new ArrayList<>();
                String query = "SELECT DISTINCT " + column + " FROM offres";
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                    while (rs.next()) {
                        String value = rs.getString(1).toLowerCase();
                        if (column.equals("Specialite_diplome") || column.equals("type_contrat")) {
                            String[] splitValues = value.split("[/\\-]");
                            for (String splitValue : splitValues) {
                                if (!splitValue.trim().isEmpty()) {
                                    values.add(splitValue.trim());
                                }
                            }
                        } else {
                            values.add(value.trim());
                        }
                    }
                }
                dictionary.put(column, values);
            }
        }

        // Save dictionary to JSON with pretty printing
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), dictionary);
    }

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/jobsoffers";
        String dbUser = "root";
        String dbPassword = "";
        String outputFile = "C:/Users/Princello/Downloads/scraping_data/src/main/java/NLP/dictionary.json";

        try {
            DictionaryGenerator generator = new DictionaryGenerator(dbUrl, dbUser, dbPassword);
            generator.generateDictionary(outputFile);
            System.out.println("Dictionary generated successfully!");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
