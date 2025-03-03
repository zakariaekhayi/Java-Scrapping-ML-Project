package test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Interface Graphique - Projet ML, Scraping et Filtrage");

        // Barre de navigation (Navbar)
        HBox navbar = createNavbar();

        // Contenu principal (Area centrale)
        StackPane mainContent = new StackPane();
        mainContent.setPadding(new Insets(20));

        // Layout principal
        BorderPane root = new BorderPane();
        root.setBottom(navbar);
        root.setCenter(mainContent);

        // Gestion des interactions avec la barre de navigation
        setupNavbarActions(navbar, mainContent);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createNavbar() {
        HBox navbar = new HBox();
        navbar.setPadding(new Insets(10));
        navbar.setSpacing(20);
        navbar.setStyle("-fx-background-color: #333333;");

        Button importCVButton = createNavbarButton("Importer CV", "cv_icon.png");
        Button aiButton = createNavbarButton("AI", "ai_icon.png");
        Button statsButton = createNavbarButton("Statistiques", "stats_icon.png");
        Button filterButton = createNavbarButton("Filtrage", "filter_icon.png");

        navbar.getChildren().addAll(importCVButton, aiButton, statsButton, filterButton);

        return navbar;
    }

    private Button createNavbarButton(String text, String iconPath) {
        Button button = new Button(text);
        button.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(iconPath))));
        button.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: transparent;");
        return button;
    }

    private void setupNavbarActions(HBox navbar, StackPane mainContent) {
        Button importCVButton = (Button) navbar.getChildren().get(0);
        Button aiButton = (Button) navbar.getChildren().get(1);
        Button statsButton = (Button) navbar.getChildren().get(2);
        Button filterButton = (Button) navbar.getChildren().get(3);

        importCVButton.setOnAction(e -> showImportCVSection(mainContent));
        aiButton.setOnAction(e -> showAISection(mainContent));
        statsButton.setOnAction(e -> showStatsSection(mainContent));
        filterButton.setOnAction(e -> showFilterSection(mainContent));
    }

    private void showImportCVSection(StackPane mainContent) {
        VBox importCVLayout = new VBox(10);
        importCVLayout.setPadding(new Insets(20));

        Label label = new Label("Importer votre CV");
        Button uploadButton = new Button("Importer CV");
        uploadButton.setStyle("-fx-border-color: #0078D7; -fx-border-width: 2; -fx-padding: 20; -fx-background-color: transparent;");

        uploadButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Importation de CV");
            alert.setHeaderText(null);
            alert.setContentText("Fonctionnalité d'importation de CV en développement.");
            alert.showAndWait();
        });

        importCVLayout.getChildren().addAll(label, uploadButton);
        mainContent.getChildren().setAll(importCVLayout);
    }

    private void showAISection(StackPane mainContent) {
        VBox aiLayout = new VBox(10);
        aiLayout.setPadding(new Insets(20));

        TextField userInput = new TextField();
        userInput.setPromptText("Posez une question...");

        Button sendButton = new Button("Envoyer");
        sendButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Interaction avec AI");
            alert.setHeaderText(null);
            alert.setContentText("Chatbot : Fonctionnalité en développement.");
            alert.showAndWait();
        });

        aiLayout.getChildren().addAll(userInput, sendButton);
        mainContent.getChildren().setAll(aiLayout);
    }

    private void showStatsSection(StackPane mainContent) {
        VBox statsLayout = new VBox(10);
        statsLayout.setPadding(new Insets(20));

        Label statsLabel = new Label("Statistiques (Graphiques à venir)");
        statsLayout.getChildren().add(statsLabel);

        mainContent.getChildren().setAll(statsLayout);
    }

    private void showFilterSection(StackPane mainContent) {
        VBox filterLayout = new VBox(10);
        filterLayout.setPadding(new Insets(20));

        Label filterLabel = new Label("Filtrage des données");

        ComboBox<String> cityFilter = new ComboBox<>();
        cityFilter.getItems().addAll("Tunis", "Sfax", "Sousse");
        cityFilter.setPromptText("Ville");

        ComboBox<String> sectorFilter = new ComboBox<>();
        sectorFilter.getItems().addAll("IT", "Génie Civil", "Mécatronique");
        sectorFilter.setPromptText("Secteur");

        ComboBox<String> contractFilter = new ComboBox<>();
        contractFilter.getItems().addAll("CDI", "CDD", "Stage");
        contractFilter.setPromptText("Type de contrat");

        ComboBox<String> educationFilter = new ComboBox<>();
        educationFilter.getItems().addAll("Bac+3", "Bac+5", "Doctorat");
        educationFilter.setPromptText("Niveau d'étude");

        filterLayout.getChildren().addAll(filterLabel, cityFilter, sectorFilter, contractFilter, educationFilter);
        mainContent.getChildren().setAll(filterLayout);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
