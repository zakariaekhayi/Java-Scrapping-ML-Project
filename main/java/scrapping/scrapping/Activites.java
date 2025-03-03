package scrapping;

import java.util.*;

public class Activites {

    // Objet contenant toutes les activités du marché
    private Map<String, List<String>> activityDatabase;

    public Activites() {
        this.activityDatabase = initializeActivities();
    }

        // Méthode pour initialiser les activités avec leurs mots-clés associés
        public Map<String, List<String>> initializeActivities() {
            Map<String, List<String>> activities = new HashMap<>();

            // Technologie et numérique
            activities.put("Développement logiciel", Arrays.asList(
                    "logiciel", "programmation", "codage", "application", "développement", "software", "système d'information", "outils numériques", "applications mobiles"
            ));
            activities.put("Intelligence artificielle", Arrays.asList(
                    "IA", "intelligence artificielle", "machine learning", "apprentissage automatique", "réseaux neuronaux", "deep learning", "NLP", "génération automatique"
            ));
            activities.put("Big data", Arrays.asList(
                    "big data", "données massives", "analyse de données", "data science", "data engineering", "traitement de données", "modélisation de données"
            ));
            activities.put("Cybersécurité", Arrays.asList(
                    "cybersécurité", "sécurité informatique", "protection des données", "hacking", "piratage", "firewall", "encryption", "authentification", "malware"
            ));
            activities.put("Développement web", Arrays.asList(
                    "développement web", "site internet", "applications web", "e-commerce", "frameworks", "frontend", "backend", "web design", "javascript", "html", "css"
            ));

            // Énergies et environnement
            activities.put("Énergies renouvelables", Arrays.asList(
                    "énergie solaire", "énergie éolienne", "renouvelable", "écologie", "environnement", "transition énergétique", "biodiversité", "hydroélectricité"
            ));
            activities.put("Traitement de l'eau", Arrays.asList(
                    "traitement de l'eau", "eau potable", "recyclage de l'eau", "gestion des ressources hydriques", "assainissement", "gestion des eaux usées"
            ));

            // Construction et immobilier
            activities.put("Construction de bâtiments", Arrays.asList(
                    "construction", "bâtiments", "infrastructure", "chantier", "immobilier", "maçonnerie", "architecture", "ingénierie structurelle", "travaux publics"
            ));
            activities.put("Immobilier", Arrays.asList(
                    "vente immobilière", "agence immobilière", "location immobilière", "transactions immobilières", "projets résidentiels", "gestion locative"
            ));

            // Finance et assurances
            activities.put("Banque", Arrays.asList(
                    "banque", "services financiers", "crédit", "compte bancaire", "transactions", "investissements", "banque en ligne", "prêts", "épargne"
            ));
            activities.put("Assurance", Arrays.asList(
                    "assurance", "protection", "risques", "indemnisation", "assurance vie", "assurance habitation", "assurance automobile", "gestion des sinistres"
            ));
            activities.put("Trading et finance", Arrays.asList(
                    "trading", "bourse", "finance", "actions", "marchés financiers", "crypto-monnaie", "investissements", "hedge funds", "analyse financière"
            ));

            // Santé
            activities.put("Santé et bien-être", Arrays.asList(
                    "hôpital", "soins médicaux", "médecine", "chirurgie", "clinique", "pharmacie", "médecine préventive", "bien-être", "santé mentale", "yoga"
            ));
            activities.put("Biotechnologie", Arrays.asList(
                    "biotech", "génie génétique", "recherche médicale", "thérapie génique", "biologie moléculaire", "pharmacologie", "vaccins", "immunologie"
            ));

            // Commerce et distribution
            activities.put("Commerce", Arrays.asList(
                    "vente", "distribution", "commerce de détail", "commerce en gros", "supermarché", "magasin", "produits de consommation", "boutique"
            ));
            activities.put("E-commerce", Arrays.asList(
                    "e-commerce", "plateformes en ligne", "ventes en ligne", "livraison", "paiements électroniques", "boutiques virtuelles", "shopping en ligne"
            ));

            // Industrie
            activities.put("Industrie manufacturière", Arrays.asList(
                    "manufacture", "fabrication", "usine", "production", "chaîne de montage", "industries mécaniques", "usinage", "équipements industriels"
            ));
            activities.put("Automobile", Arrays.asList(
                    "voitures", "automobile", "mécanique", "industrie automobile", "conception de véhicules", "réparations automobiles", "pièces détachées"
            ));

            // Autres domaines
            activities.put("Éducation et formation", Arrays.asList(
                    "formation", "enseignement", "éducation", "cours en ligne", "e-learning", "coaching", "tutorat", "pédagogie", "université"
            ));
            activities.put("Tourisme et loisirs", Arrays.asList(
                    "tourisme", "voyages", "hôtellerie", "restauration", "loisirs", "agences de voyage", "guides touristiques", "réservations"
            ));
            activities.put("Logistique et transport", Arrays.asList(
                    "logistique", "transport", "livraison", "chaîne d'approvisionnement", "fret", "camions", "entreposage", "expédition"
            ));
            activities.put("Ressources Humaines", Arrays.asList("recrutement", "formation", "personnel", "talents", "salaires", "bien-être au travail"));
            activities.put("Logistique", Arrays.asList("transport", "livraison", "chaîne d'approvisionnement", "stockage", "gestion des stocks", "entrepôt"));
            activities.put("Alimentation", Arrays.asList("restaurant", "catering", "nourriture", "boissons", "traiteur", "fast-food", "cafés"));
            activities.put("Transport", Arrays.asList("logistique", "livraison", "véhicules", "transports en commun", "mobilité", "chauffeurs"));
            activities.put("Médias", Arrays.asList("journalisme", "télévision", "radio", "publications", "contenu numérique", "influenceurs"));

            return activities;
        }
    }



