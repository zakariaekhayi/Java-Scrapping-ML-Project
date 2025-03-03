package preTraitement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HardSkillListe {

    // Objet contenant toutes les activités du marché
    private Map<String, List<String>> activityDatabase;

    public HardSkillListe() {
        this.activityDatabase = initializeHardSkill();
    }

    // Méthode pour initialiser les activités avec leurs mots-clés associés
    public Map<String, List<String>> initializeHardSkill() {
        Map<String, List<String>> hardSkill = new HashMap<>();

        // Technologie et numérique
        hardSkill.put("Développement logiciel", Arrays.asList(
                "logiciel", "programmation", "codage", "application", "développement", "software", "système d'information", "outils numériques", "applications mobiles"
        ));
        hardSkill.put("Intelligence artificielle", Arrays.asList(
                "IA", "intelligence artificielle", "machine learning", "apprentissage automatique", "réseaux neuronaux", "deep learning", "NLP", "génération automatique"
        ));
        hardSkill.put("Big data", Arrays.asList(
                "big data", "données massives", "analyse de données", "data science", "data engineering", "traitement de données", "modélisation de données"
        ));
        hardSkill.put("Cybersécurité", Arrays.asList(
                "cybersécurité", "sécurité informatique", "protection des données", "hacking", "piratage", "firewall", "encryption", "authentification", "malware"
        ));
        hardSkill.put("Développement web", Arrays.asList(
                "développement web", "site internet", "applications web", "e-commerce", "frameworks", "frontend", "backend", "web design", "javascript", "html", "css"
        ));

        // Énergies et environnement
        hardSkill.put("Énergies renouvelables", Arrays.asList(
                "énergie solaire", "énergie éolienne", "renouvelable", "écologie", "environnement", "transition énergétique", "biodiversité", "hydroélectricité"
        ));
        hardSkill.put("Traitement de l'eau", Arrays.asList(
                "traitement de l'eau", "eau potable", "recyclage de l'eau", "gestion des ressources hydriques", "assainissement", "gestion des eaux usées"
        ));

        // Construction et immobilier
        hardSkill.put("Construction de bâtiments", Arrays.asList(
                "construction", "bâtiments", "infrastructure", "chantier", "immobilier", "maçonnerie", "architecture", "ingénierie structurelle", "travaux publics"
        ));
        hardSkill.put("Immobilier", Arrays.asList(
                "vente immobilière", "agence immobilière", "location immobilière", "transactions immobilières", "projets résidentiels", "gestion locative"
        ));

        // Finance et assurances
        hardSkill.put("Banque", Arrays.asList(
                "banque", "services financiers", "crédit", "compte bancaire", "transactions", "investissements", "banque en ligne", "prêts", "épargne"
        ));
        hardSkill.put("Assurance", Arrays.asList(
                "assurance", "protection", "risques", "indemnisation", "assurance vie", "assurance habitation", "assurance automobile", "gestion des sinistres"
        ));
        hardSkill.put("Trading et finance", Arrays.asList(
                "trading", "bourse", "finance", "actions", "marchés financiers", "crypto-monnaie", "investissements", "hedge funds", "analyse financière"
        ));

        // Santé
        hardSkill.put("Santé et bien-être", Arrays.asList(
                "hôpital", "soins médicaux", "médecine", "chirurgie", "clinique", "pharmacie", "médecine préventive", "bien-être", "santé mentale", "yoga"
        ));
        hardSkill.put("Biotechnologie", Arrays.asList(
                "biotech", "génie génétique", "recherche médicale", "thérapie génique", "biologie moléculaire", "pharmacologie", "vaccins", "immunologie"
        ));

        // Commerce et distribution
        hardSkill.put("Commerce", Arrays.asList(
                "vente", "distribution", "commerce de détail", "commerce en gros", "supermarché", "magasin", "produits de consommation", "boutique"
        ));
        hardSkill.put("E-commerce", Arrays.asList(
                "e-commerce", "plateformes en ligne", "ventes en ligne", "livraison", "paiements électroniques", "boutiques virtuelles", "shopping en ligne"
        ));

        // Industrie
        hardSkill.put("Industrie manufacturière", Arrays.asList(
                "manufacture", "fabrication", "usine", "production", "chaîne de montage", "industries mécaniques", "usinage", "équipements industriels"
        ));
        hardSkill.put("Automobile", Arrays.asList(
                "voitures", "automobile", "mécanique", "industrie automobile", "conception de véhicules", "réparations automobiles", "pièces détachées"
        ));

        // Autres domaines
        hardSkill.put("Éducation et formation", Arrays.asList(
                "formation", "enseignement", "éducation", "cours en ligne", "e-learning", "coaching", "tutorat", "pédagogie", "université"
        ));
        hardSkill.put("Tourisme et loisirs", Arrays.asList(
                "tourisme", "voyages", "hôtellerie", "restauration", "loisirs", "agences de voyage", "guides touristiques", "réservations"
        ));
        hardSkill.put("Logistique et transport", Arrays.asList(
                "logistique", "transport", "livraison", "chaîne d'approvisionnement", "fret", "camions", "entreposage", "expédition"
        ));
        hardSkill.put("Ressources Humaines", Arrays.asList("recrutement", "formation", "personnel", "talents", "salaires", "bien-être au travail"));
        hardSkill.put("Logistique", Arrays.asList("transport", "livraison", "chaîne d'approvisionnement", "stockage", "gestion des stocks", "entrepôt"));
        hardSkill.put("Alimentation", Arrays.asList("restaurant", "catering", "nourriture", "boissons", "traiteur", "fast-food", "cafés"));
        hardSkill.put("Transport", Arrays.asList("logistique", "livraison", "véhicules", "transports en commun", "mobilité", "chauffeurs"));
        hardSkill.put("Médias", Arrays.asList("journalisme", "télévision", "radio", "publications", "contenu numérique", "influenceurs"));

        return hardSkill;
    }
}



