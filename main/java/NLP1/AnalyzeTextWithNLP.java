package NLP1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class AnalyzeTextWithNLP {
    private final Map<String, List<String>> dictionary;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    
    // Constructor to initialize dictionary and DB connection details
    public AnalyzeTextWithNLP(String dictionaryFile, String dbUrl, String dbUser, String dbPassword) throws IOException {
        this.dictionary = loadDictionary(dictionaryFile);
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }


    // Method to analyze text and extract keywords
    public Map<String, List<String>> analyzeText(String textFile) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(textFile)));

        // Tokenize and preprocess text
        String[] tokens = text.toLowerCase().split("\\W+");
        List<String> filteredTokens = removeStopWords(tokens);

        // Classify keywords
        Map<String, List<String>> classifiedKeywords = classifyKeywords(filteredTokens);

        // Query offers based on classified keywords
        List<Map<String, Object>> jobOffers = queryOffers(classifiedKeywords);

        // Display the job offers in a GUI panel
        JobOffersPanel.showJobOffersPanel(jobOffers);

        return classifiedKeywords;
    }

    // Method to load dictionary from JSON
    private Map<String, List<String>> loadDictionary(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(fileName), Map.class);
    }

    // Remove stop words from the tokens
    private List<String> removeStopWords(String[] tokens) {
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "in", "or", "and", "a", "of", "to", "for", "on"));
        List<String> filteredTokens = new ArrayList<>();
        for (String token : tokens) {
            if (!stopWords.contains(token)) {
                filteredTokens.add(token);
            }
        }
        return filteredTokens;
    }

    // Classify tokens into the appropriate categories based on the dictionary
    private Map<String, List<String>> classifyKeywords(List<String> tokens) {
        Map<String, List<String>> classifiedKeywords = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : dictionary.entrySet()) {
            String category = entry.getKey();
            List<String> values = entry.getValue();
            List<String> matches = new ArrayList<>();

            for (String token : tokens) {
                if (values.contains(token)) {
                    matches.add(token);
                }
            }

            classifiedKeywords.put(category, matches);
        }
        return classifiedKeywords;
    }

    // Method to query offers based on the classified keywords
    private List<Map<String, Object>> queryOffers(Map<String, List<String>> classifiedKeywords) {
        String queryTemplate = "SELECT * FROM offres WHERE ";

        // Build dynamic WHERE clause based on classified keywords
        StringBuilder whereClause = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : classifiedKeywords.entrySet()) {
            String column = entry.getKey();
            List<String> keywords = entry.getValue();

            if (!keywords.isEmpty()) {
                whereClause.append("(");
                for (int i = 0; i < keywords.size(); i++) {
                    whereClause.append(column).append(" LIKE ? ");
                    if (i < keywords.size() - 1) {
                        whereClause.append("OR ");
                    }
                }
                whereClause.append(") AND ");
            }
        }

        // Remove trailing "AND " if present
        if (whereClause.length() > 0) {
            whereClause.setLength(whereClause.length() - 5);
        } else {
            System.out.println("No keywords to query.");
            return Collections.emptyList();
        }

        String finalQuery = queryTemplate + whereClause;

        List<Map<String, Object>> jobOffers = new ArrayList<>();

        // Execute query to retrieve matching offers
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmt = connection.prepareStatement(finalQuery)) {

            // Set parameters for the query
            int paramIndex = 1;
            for (Map.Entry<String, List<String>> entry : classifiedKeywords.entrySet()) {
                List<String> keywords = entry.getValue();
                for (String keyword : keywords) {
                    stmt.setString(paramIndex++, "%" + keyword + "%");
                }
            }

            // Execute and process results
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> offer = new HashMap<>();
                    offer.put("title", rs.getString("Titre"));
                    offer.put("city", rs.getString("Ville"));
                    offer.put("contractType", rs.getString("Type_contrat"));
                    offer.put("speciality", rs.getString("Specialite_diplome"));
                    offer.put("description", rs.getString("Description_poste"));
                    jobOffers.add(offer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Print the stack trace for SQLException
        }

        return jobOffers;
    }

    // Main method to integrate AnalyzeTextWithNLP and query offers
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/jobsoffers";
        String dbUser = "root";
        String dbPassword = "";
        String dictionaryFile = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/NLP1/dictionary.json";
        String textFile = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/NLP1/prompt.txt";

        try {
            // Use AnalyzeTextWithNLP to extract keywords and query offers
            AnalyzeTextWithNLP analyzer = new AnalyzeTextWithNLP(dictionaryFile, dbUrl, dbUser, dbPassword);
            analyzer.analyzeText(textFile);  // This will both extract keywords and query the database
        } catch (IOException e) {
            e.printStackTrace();  // Print the stack trace for exceptions
        }
    }
}
