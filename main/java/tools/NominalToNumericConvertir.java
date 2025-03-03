package ML;

import weka.core.Instances;
import weka.core.Attribute;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

public class NominalToNumericConvertir {

    public static void main(String[] args) {
        try {
            // Charger les données ARFF
            System.out.println("Chargement des données ARFF...");
            DataSource source = new DataSource("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML3/fillo.arff");
            Instances data = source.getDataSet();

            // Pour chaque attribut nominal, créer un mapping des valeurs vers des nombres
            HashMap<String, HashMap<String, Double>> attributeMappings = new HashMap<>();

            // Parcourir tous les attributs sauf le salaire
            for (int i = 0; i < data.numAttributes(); i++) {
                Attribute att = data.attribute(i);
                if (att.isNominal()) {  // Si l'attribut est nominal
                    HashMap<String, Double> valueMapping = new HashMap<>();
                    // Créer un mapping pour chaque valeur possible
                    for (int j = 0; j < att.numValues(); j++) {
                        valueMapping.put(att.value(j), (double) j);
                    }
                    attributeMappings.put(att.name(), valueMapping);
                }
            }

            // Créer un nouveau dataset avec les mêmes attributs mais en version numérique
            ArrayList<Attribute> newAttributes = new ArrayList<>();
            for (int i = 0; i < data.numAttributes(); i++) {
                Attribute att = data.attribute(i);
                if (att.isNominal()) {
                    // Créer un nouvel attribut numérique
                    newAttributes.add(new Attribute(att.name()));
                } else {
                    // Garder l'attribut numérique tel quel
                    newAttributes.add(att);
                }
            }

            Instances numericData = new Instances("NumericData", newAttributes, data.numInstances());

            // Convertir toutes les instances
            for (int i = 0; i < data.numInstances(); i++) {
                double[] values = new double[data.numAttributes()];
                for (int j = 0; j < data.numAttributes(); j++) {
                    if (data.attribute(j).isNominal()) {
                        String nominalValue = data.instance(i).stringValue(j);
                        values[j] = attributeMappings.get(data.attribute(j).name()).get(nominalValue);
                    } else {
                        values[j] = data.instance(i).value(j);
                    }
                }
                numericData.add(data.instance(i).copy(values));
            }

            // Sauvegarder les données transformées
            ArffSaver saver = new ArffSaver();
            saver.setInstances(numericData);
            saver.setFile(new File("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML3/fillo_num.arff"));
            saver.writeBatch();

            System.out.println("Transformation terminée !");

            // Afficher le mapping pour référence
            System.out.println("\nMapping des valeurs :");
            attributeMappings.forEach((attributeName, mapping) -> {
                System.out.println("\nPour l'attribut " + attributeName + ":");
                mapping.forEach((original, numeric) ->
                        System.out.println(original + " -> " + numeric));
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}