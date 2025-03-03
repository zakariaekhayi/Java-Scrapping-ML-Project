package scrapping.preTraitement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosteListe {
    private Map<String, List<String>> postesDatabase;
    public PosteListe() {
        this.postesDatabase = initialiserPostes();
    }
    public Map<String, List<String>> initialiserPostes() {
        Map<String, List<String>> postes = new HashMap<>();


        postes.put("Développeur Java", Arrays.asList("développeur", "programmeur", "Java", "ingénieur logiciel"));
        postes.put("Développeur Python", Arrays.asList("développeur", "Python", "programmeur", "ingénieur logiciel"));
        postes.put("Analyste de données", Arrays.asList("analyste", "data analyst", "science des données", "statisticien"));
        postes.put("Chef de projet", Arrays.asList("gestion de projet", "project manager", "leader", "coordinateur de projet"));
        postes.put("Développeur front-end", Arrays.asList("développeur web", "front-end", "HTML", "CSS", "JavaScript"));
        postes.put("Développeur back-end", Arrays.asList("back-end", "API", "serveur", "base de données"));
        postes.put("Développeur full-stack", Arrays.asList("full-stack", "développeur", "front-end", "back-end"));
        postes.put("Testeur QA", Arrays.asList("assurance qualité", "testeur", "QA", "test de logiciels"));
        postes.put("Administrateur système", Arrays.asList("sysadmin", "administration système", "serveur", "infrastructure"));
        postes.put("Administrateur de base de données", Arrays.asList("DBA", "administration de base de données", "gestion de données", "SQL"));
        postes.put("Ingénieur DevOps", Arrays.asList("DevOps", "automatisation", "intégration continue", "déploiement"));
        postes.put("Ingénieur réseau", Arrays.asList("réseau", "administration réseau", "sécurité réseau", "support technique"));
        postes.put("Graphiste", Arrays.asList("design", "graphique", "créatif", "artiste"));
        postes.put("Responsable marketing", Arrays.asList("marketing", "responsable marketing", "stratégie", "publicité"));
        postes.put("Assistant administratif", Arrays.asList("administratif", "secrétaire", "bureau", "organisation"));
        postes.put("Ingénieur logiciel", Arrays.asList("ingénieur", "développement", "logiciel", "application"));
        postes.put("Spécialiste SEO", Arrays.asList("SEO", "référencement", "optimisation", "marketing digital"));
        postes.put("Consultant en informatique", Arrays.asList("consultant", "informatique", "conseil", "stratégie IT"));
        postes.put("Formateur", Arrays.asList("formation", "éducation", "instructeur", "mentor"));
        postes.put("Chef de produit", Arrays.asList("produit", "gestion de produit", "stratégie produit", "responsable produit"));
        postes.put("Développeur mobile", Arrays.asList("mobile", "développeur", "iOS", "Android"));
        postes.put("Architecte logiciel", Arrays.asList("architecture", "logiciel", "design", "technique"));
        postes.put("Analyste fonctionnel", Arrays.asList("analyste", "fonctionnel", "exigences", "spécifications"));
        postes.put("Technicien support", Arrays.asList("support technique", "technicien", "assistance", "service client"));
        postes.put("Responsable des ventes", Arrays.asList("ventes", "responsable", "commerce", "stratégie commerciale"));

        // Ajoutez d'autres postes selon vos besoins

        return postes;
    }
}

