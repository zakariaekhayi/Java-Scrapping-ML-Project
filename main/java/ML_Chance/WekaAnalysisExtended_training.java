package ML_Chance;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Method;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import java.util.Random;
import weka.core.SerializationHelper;

import javax.swing.*;

public class WekaAnalysisExtended_training extends JFrame {

    private Instances data;
    private NaiveBayes model;


    // Variables pour x1 (12 valeurs) Ville
    private int x10 = 0, x11 = 1, x12 = 2, x13 = 3, x14 = 4, x15 = 5;
    private int x16 = 6, x17 = 7, x18 = 8, x19 = 9, x110 = 10, x111 = 11;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Déclarations des variables  x20 à x2117 secteur d'activite">//GEN-BEGIN:initComponents
    public int x20 = 0;
    public int x21 = 1;
    public int x22 = 2;
    public int x23 = 3;
    public int x24 = 4;
    public int x25 = 5;
    public int x26 = 6;
    public int x27 = 7;
    public int x28 = 8;
    public int x29 = 9;
    public int x210 = 10;
    public int x211 = 11;
    public int x212 = 12;
    public int x213 = 13;
    public int x214 = 14;
    public int x215 = 15;
    public int x216 = 16;
    public int x217 = 17;
    public int x218 = 18;
    public int x219 = 19;
    public int x220 = 20;
    public int x221 = 21;
    public int x222 = 22;
    public int x223 = 23;
    public int x224 = 24;
    public int x225 = 25;
    public int x226 = 26;
    public int x227 = 27;
    public int x228 = 28;
    public int x229 = 29;
    public int x230 = 30;
    public int x231 = 31;
    public int x232 = 32;
    public int x233 = 33;
    public int x234 = 34;
    public int x235 = 35;
    public int x236 = 36;
    public int x237 = 37;
    public int x238 = 38;
    public int x239 = 39;
    public int x240 = 40;
    public int x241 = 41;
    public int x242 = 42;
    public int x243 = 43;
    public int x244 = 44;
    public int x245 = 45;
    public int x246 = 46;
    public int x247 = 47;
    public int x248 = 48;
    public int x249 = 49;
    public int x250 = 50;
    public int x251 = 51;
    public int x252 = 52;
    public int x253 = 53;
    public int x254 = 54;
    public int x255 = 55;
    public int x256 = 56;
    public int x257 = 57;
    public int x258 = 58;
    public int x259 = 59;
    public int x260 = 60;
    public int x261 = 61;
    public int x262 = 62;
    public int x263 = 63;
    public int x264 = 64;
    public int x265 = 65;
    public int x266 = 66;
    public int x267 = 67;
    public int x268 = 68;
    public int x269 = 69;
    public int x270 = 70;
    public int x271 = 71;
    public int x272 = 72;
    public int x273 = 73;
    public int x274 = 74;
    public int x275 = 75;
    public int x276 = 76;
    public int x277 = 77;
    public int x278 = 78;
    public int x279 = 79;
    public int x280 = 80;
    public int x281 = 81;
    public int x282 = 82;
    public int x283 = 83;
    public int x284 = 84;
    public int x285 = 85;
    public int x286 = 86;
    public int x287 = 87;
    public int x288 = 88;
    public int x289 = 89;
    public int x290 = 90;
    public int x291 = 91;
    public int x292 = 92;
    public int x293 = 93;
    public int x294 = 94;
    public int x295 = 95;
    public int x296 = 96;
    public int x297 = 97;
    public int x298 = 98;
    public int x299 = 99;
    public int x2100 = 100;
    public int x2101 = 101;
    public int x2102 = 102;
    public int x2103 = 103;
    public int x2104 = 104;
    public int x2105 = 105;
    public int x2106 = 106;
    public int x2107 = 107;
    public int x2108 = 108;
    public int x2109 = 109;
    public int x2110 = 110;
    public int x2111 = 111;
    public int x2112 = 112;
    public int x2113 = 113;
    public int x2114 = 114;
    public int x2115 = 115;
    public int x2116 = 116;
    public int x2117 = 117;
    // </editor-fold>//GEN-END:initComponents




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="declaration des variable Type de contrat 11 ">//GEN-BEGIN:initComponents
    // Variables pour x3 (12 valeurs)
    private int x30 = 0, x31 = 1, x32 = 2, x33 = 3, x34 = 4, x35 = 5;
    private int x36 = 6, x37 = 7, x38 = 8, x39 = 9, x310 = 10, x311 = 11;
    // </editor-fold>//GEN-END:initComponents


    // Variables pour x4 (5 valeurs) Niveau d'etude
    private int x40 = 0, x41 = 1, x42 = 2, x43 = 3, x44 = 4;

    // Variables pour x5 (4 valeurs)
    private int x50 = 0, x51 = 1, x52 = 2, x53 = 3;

    // Variables pour x6 (3 valeurs)
    private int x60 = 0, x61 = 1, x62 = 2;

    // Variables pour x7 (3 valeurs)
    private int x70 = 0, x71 = 1, x72 = 2;

    // Pourcentages pour x1
    private double p_x10, p_x11, p_x12, p_x13, p_x14, p_x15;
    private double p_x16, p_x17, p_x18, p_x19, p_x110, p_x111;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="pourcentage de chaque valeur de secteur d'activit">//GEN-BEGIN:initComponents
    public double p_x20 = 0;
    public double p_x21 = 1;
    public double p_x22 = 2;
    public double p_x23 = 3;
    public double p_x24 = 4;
    public double p_x25 = 5;
    public double p_x26 = 6;
    public double p_x27 = 7;
    public double p_x28 = 8;
    public double p_x29 = 9;
    public double p_x210 = 10;
    public double p_x211 = 11;
    public double p_x212 = 12;
    public double p_x213 = 13;
    public double p_x214 = 14;
    public double p_x215 = 15;
    public double p_x216 = 16;
    public double p_x217 = 17;
    public double p_x218 = 18;
    public double p_x219 = 19;
    public double p_x220 = 20;
    public double p_x221 = 21;
    public double p_x222 = 22;
    public double p_x223 = 23;
    public double p_x224 = 24;
    public double p_x225 = 25;
    public double p_x226 = 26;
    public double p_x227 = 27;
    public double p_x228 = 28;
    public double p_x229 = 29;
    public double p_x230 = 30;
    public double p_x231 = 31;
    public double p_x232 = 32;
    public double p_x233 = 33;
    public double p_x234 = 34;
    public double p_x235 = 35;
    public double p_x236 = 36;
    public double p_x237 = 37;
    public double p_x238 = 38;
    public double p_x239 = 39;
    public double p_x240 = 40;
    public double p_x241 = 41;
    public double p_x242 = 42;
    public double p_x243 = 43;
    public double p_x244 = 44;
    public double p_x245 = 45;
    public double p_x246 = 46;
    public double p_x247 = 47;
    public double p_x248 = 48;
    public double p_x249 = 49;
    public double p_x250 = 50;
    public double p_x251 = 51;
    public double p_x252 = 52;
    public double p_x253 = 53;
    public double p_x254 = 54;
    public double p_x255 = 55;
    public double p_x256 = 56;
    public double p_x257 = 57;
    public double p_x258 = 58;
    public double p_x259 = 59;
    public double p_x260 = 60;
    public double p_x261 = 61;
    public double p_x262 = 62;
    public double p_x263 = 63;
    public double p_x264 = 64;
    public double p_x265 = 65;
    public double p_x266 = 66;
    public double p_x267 = 67;
    public double p_x268 = 68;
    public double p_x269 = 69;
    public double p_x270 = 70;
    public double p_x271 = 71;
    public double p_x272 = 72;
    public double p_x273 = 73;
    public double p_x274 = 74;
    public double p_x275 = 75;
    public double p_x276 = 76;
    public double p_x277 = 77;
    public double p_x278 = 78;
    public double p_x279 = 79;
    public double p_x280 = 80;
    public double p_x281 = 81;
    public double p_x282 = 82;
    public double p_x283 = 83;
    public double p_x284 = 84;
    public double p_x285 = 85;
    public double p_x286 = 86;
    public double p_x287 = 87;
    public double p_x288 = 88;
    public double p_x289 = 89;
    public double p_x290 = 90;
    public double p_x291 = 91;
    public double p_x292 = 92;
    public double p_x293 = 93;
    public double p_x294 = 94;
    public double p_x295 = 95;
    public double p_x296 = 96;
    public double p_x297 = 97;
    public double p_x298 = 98;
    public double p_x299 = 99;
    public double p_x2100 = 100;
    public double p_x2101 = 101;
    public double p_x2102 = 102;
    public double p_x2103 = 103;
    public double p_x2104 = 104;
    public double p_x2105 = 105;
    public double p_x2106 = 106;
    public double p_x2107 = 107;
    public double p_x2108 = 108;
    public double p_x2109 = 109;
    public double p_x2110 = 110;
    public double p_x2111 = 111;
    public double p_x2112 = 112;
    public double p_x2113 = 113;
    public double p_x2114 = 114;
    public double p_x2115 = 115;
    public double p_x2116 = 116;
    public double p_x2117 = 117;

// </editor-fold>//GEN-END:initComponents



    // Pourcentages pour x3
    private double p_x30, p_x31, p_x32, p_x33, p_x34, p_x35;
    private double p_x36, p_x37, p_x38, p_x39, p_x310, p_x311;

    // Pourcentages pour x4
    private double p_x40, p_x41, p_x42, p_x43, p_x44;

    // Pourcentages pour x5
    private double p_x50, p_x51, p_x52, p_x53;

    // Pourcentages pour x6
    private double p_x60, p_x61, p_x62;

    // Pourcentages pour x7
    private double p_x70, p_x71, p_x72;


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Getters pour tous les pourcentages">//GEN-BEGIN:initComponents
    public double getP_x10() { return p_x10; }
    public double getP_x11() { return p_x11; }
    public double getP_x12() { return p_x12; }
    public double getP_x13() { return p_x13; }
    public double getP_x14() { return p_x14; }
    public double getP_x15() { return p_x15; }
    public double getP_x16() { return p_x16; }
    public double getP_x17() { return p_x17; }
    public double getP_x18() { return p_x18; }
    public double getP_x19() { return p_x19; }
    public double getP_x110() { return p_x110; }
    public double getP_x111() { return p_x111; }



    public double getP_x20() { return p_x20; }
    public double getP_x21() { return p_x21; }
    public double getP_x22() { return p_x22; }
    public double getP_x23() { return p_x23; }
    public double getP_x24() { return p_x24; }
    public double getP_x25() { return p_x25; }
    public double getP_x26() { return p_x26; }
    public double getP_x27() { return p_x27; }
    public double getP_x28() { return p_x28; }
    public double getP_x29() { return p_x29; }
    public double getP_x210() { return p_x210; }
    public double getP_x211() { return p_x211; }
    public double getP_x212() { return p_x212; }
    public double getP_x213() { return p_x213; }
    public double getP_x214() { return p_x214; }
    public double getP_x215() { return p_x215; }
    public double getP_x216() { return p_x216; }
    public double getP_x217() { return p_x217; }
    public double getP_x218() { return p_x218; }
    public double getP_x219() { return p_x219; }
    public double getP_x220() { return p_x220; }
    public double getP_x221() { return p_x221; }
    public double getP_x222() { return p_x222; }
    public double getP_x223() { return p_x223; }
    public double getP_x224() { return p_x224; }
    public double getP_x225() { return p_x225; }
    public double getP_x226() { return p_x226; }
    public double getP_x227() { return p_x227; }
    public double getP_x228() { return p_x228; }
    public double getP_x229() { return p_x229; }
    public double getP_x230() { return p_x230; }
    public double getP_x231() { return p_x231; }
    public double getP_x232() { return p_x232; }
    public double getP_x233() { return p_x233; }
    public double getP_x234() { return p_x234; }
    public double getP_x235() { return p_x235; }
    public double getP_x236() { return p_x236; }
    public double getP_x237() { return p_x237; }
    public double getP_x238() { return p_x238; }
    public double getP_x239() { return p_x239; }
    public double getP_x240() { return p_x240; }
    public double getP_x241() { return p_x241; }
    public double getP_x242() { return p_x242; }
    public double getP_x243() { return p_x243; }
    public double getP_x244() { return p_x244; }
    public double getP_x245() { return p_x245; }
    public double getP_x246() { return p_x246; }
    public double getP_x247() { return p_x247; }
    public double getP_x248() { return p_x248; }
    public double getP_x249() { return p_x249; }
    public double getP_x250() { return p_x250; }
    public double getP_x251() { return p_x251; }
    public double getP_x252() { return p_x252; }
    public double getP_x253() { return p_x253; }
    public double getP_x254() { return p_x254; }
    public double getP_x255() { return p_x255; }
    public double getP_x256() { return p_x256; }
    public double getP_x257() { return p_x257; }
    public double getP_x258() { return p_x258; }
    public double getP_x259() { return p_x259; }
    public double getP_x260() { return p_x260; }
    public double getP_x261() { return p_x261; }
    public double getP_x262() { return p_x262; }
    public double getP_x263() { return p_x263; }
    public double getP_x264() { return p_x264; }
    public double getP_x265() { return p_x265; }
    public double getP_x266() { return p_x266; }
    public double getP_x267() { return p_x267; }
    public double getP_x268() { return p_x268; }
    public double getP_x269() { return p_x269; }
    public double getP_x270() { return p_x270; }
    public double getP_x271() { return p_x271; }
    public double getP_x272() { return p_x272; }
    public double getP_x273() { return p_x273; }
    public double getP_x274() { return p_x274; }
    public double getP_x275() { return p_x275; }
    public double getP_x276() { return p_x276; }
    public double getP_x277() { return p_x277; }
    public double getP_x278() { return p_x278; }
    public double getP_x279() { return p_x279; }
    public double getP_x280() { return p_x280; }
    public double getP_x281() { return p_x281; }
    public double getP_x282() { return p_x282; }
    public double getP_x283() { return p_x283; }
    public double getP_x284() { return p_x284; }
    public double getP_x285() { return p_x285; }
    public double getP_x286() { return p_x286; }
    public double getP_x287() { return p_x287; }
    public double getP_x288() { return p_x288; }
    public double getP_x289() { return p_x289; }
    public double getP_x290() { return p_x290; }
    public double getP_x291() { return p_x291; }
    public double getP_x292() { return p_x292; }
    public double getP_x293() { return p_x293; }
    public double getP_x294() { return p_x294; }
    public double getP_x295() { return p_x295; }
    public double getP_x296() { return p_x296; }
    public double getP_x297() { return p_x297; }
    public double getP_x298() { return p_x298; }
    public double getP_x299() { return p_x299; }
    public double getP_x2100() { return p_x2100; }
    public double getP_x2101() { return p_x2101; }
    public double getP_x2102() { return p_x2102; }
    public double getP_x2103() { return p_x2103; }
    public double getP_x2104() { return p_x2104; }
    public double getP_x2105() { return p_x2105; }
    public double getP_x2106() { return p_x2106; }
    public double getP_x2107() { return p_x2107; }
    public double getP_x2108() { return p_x2108; }
    public double getP_x2109() { return p_x2109; }
    public double getP_x2110() { return p_x2110; }
    public double getP_x2111() { return p_x2111; }
    public double getP_x2112() { return p_x2112; }
    public double getP_x2113() { return p_x2113; }
    public double getP_x2114() { return p_x2114; }
    public double getP_x2115() { return p_x2115; }
    public double getP_x2116() { return p_x2116; }
    public double getP_x2117() { return p_x2117; }



    public double getP_x30() { return p_x30; }
    public double getP_x31() { return p_x31; }
    public double getP_x32() { return p_x32; }
    public double getP_x33() { return p_x33; }
    public double getP_x34() { return p_x34; }
    public double getP_x35() { return p_x35; }
    public double getP_x36() { return p_x36; }
    public double getP_x37() { return p_x37; }
    public double getP_x38() { return p_x38; }
    public double getP_x39() { return p_x39; }
    public double getP_x310() { return p_x310; }
    public double getP_x311() { return p_x311; }




    public double getP_x40() { return p_x40; }
    public double getP_x41() { return p_x41; }
    public double getP_x42() { return p_x42; }
    public double getP_x43() { return p_x43; }
    public double getP_x44() { return p_x44; }




    public double getP_x50() { return p_x50; }
    public double getP_x51() { return p_x51; }
    public double getP_x52() { return p_x52; }
    public double getP_x53() { return p_x53; }




    public double getP_x60() { return p_x60; }
    public double getP_x61() { return p_x61; }
    public double getP_x62() { return p_x62; }





    public double getP_x70() { return p_x70; }
    public double getP_x71() { return p_x71; }
    public double getP_x72() { return p_x72; }


// </editor-fold>//GEN-END:initComponents

    // Tableaux pour stocker les limites de chaque attribut
    private final int[] limits = {11, 117, 11, 4, 3, 2, 2};

    public void analyzeData() {
        try {
            // Charger le fichier ARFF
            DataSource source = new DataSource("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Chance/fillo_num.arff");
            data = source.getDataSet();

            // Définir le dernier attribut comme classe (important pour Weka)
            data.setClassIndex(data.numAttributes() - 1);

            System.out.println("Données chargées avec succès: " + data.numInstances() + " instances");

            // Pour chaque attribut (x1 à x7)
            for (int attr = 0; attr < 7; attr++) {
                Map<Double, Integer> valueCounts = new HashMap<>();

                // Compter les occurrences
                for (int i = 0; i < data.numInstances(); i++) {
                    double value = data.instance(i).value(attr);
                    valueCounts.merge(value, 1, Integer::sum);
                }

                // Calculer et stocker les pourcentages
                System.out.println("\nAnalyse de l'attribut x" + (attr + 1) + ":");
                for (int val = 0; val <= limits[attr]; val++) {
                    int count = valueCounts.getOrDefault((double) val, 0);
                    double percentage = (count * 100.0) / data.numInstances();
                    storePercentage(attr + 1, val, percentage);
                    System.out.printf("Valeur %d: %.1f%% (%d occurrences)\n",
                            val, percentage, count);
                }
            }

        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des données: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void storePercentage(int attribute, int value, double percentage) {
        switch (attribute) {
            case 0: // Pour x1
                switch (value) {
                    case 0: p_x10 = percentage; break;
                    case 1: p_x11 = percentage; break;
                    case 2: p_x12 = percentage; break;
                    case 3: p_x13 = percentage; break;
                    case 4: p_x14 = percentage; break;
                    case 5: p_x15 = percentage; break;
                    case 6: p_x16 = percentage; break;
                    case 7: p_x17 = percentage; break;
                    case 8: p_x18 = percentage; break;
                    case 9: p_x19 = percentage; break;
                    case 10: p_x110 = percentage; break;
                    case 11: p_x111 = percentage; break;

                }
                break;

            case 1: // Pour x2
                switch (value) {
                    case 0: p_x20 = percentage; break;
                    case 1: p_x21 = percentage; break;
                    case 2: p_x22 = percentage; break;
                    case 3: p_x23 = percentage; break;
                    case 4: p_x24 = percentage; break;
                    case 5: p_x25 = percentage; break;
                    case 6: p_x26 = percentage; break;
                    case 7: p_x27 = percentage; break;
                    case 8: p_x28 = percentage; break;
                    case 9: p_x29 = percentage; break;
                    case 10: p_x210 = percentage; break;
                    case 11: p_x211 = percentage; break;
                    case 12: p_x212 = percentage; break;
                    case 13: p_x213 = percentage; break;
                    case 14: p_x214 = percentage; break;
                    case 15: p_x215 = percentage; break;
                    case 16: p_x216 = percentage; break;
                    case 17: p_x217 = percentage; break;
                    case 18: p_x218 = percentage; break;
                    case 19: p_x219 = percentage; break;
                    case 20: p_x220 = percentage; break;
                    case 21: p_x221 = percentage; break;
                    case 22: p_x222 = percentage; break;
                    case 23: p_x223 = percentage; break;
                    case 24: p_x224 = percentage; break;
                    case 25: p_x225 = percentage; break;
                    case 26: p_x226 = percentage; break;
                    case 27: p_x227 = percentage; break;
                    case 28: p_x228 = percentage; break;
                    case 29: p_x229 = percentage; break;
                    case 30: p_x230 = percentage; break;
                    case 31: p_x231 = percentage; break;
                    case 32: p_x232 = percentage; break;
                    case 33: p_x233 = percentage; break;
                    case 34: p_x234 = percentage; break;
                    case 35: p_x235 = percentage; break;
                    case 36: p_x236 = percentage; break;
                    case 37: p_x237 = percentage; break;
                    case 38: p_x238 = percentage; break;
                    case 39: p_x239 = percentage; break;
                    case 40: p_x240 = percentage; break;
                    case 41: p_x241 = percentage; break;
                    case 42: p_x242 = percentage; break;
                    case 43: p_x243 = percentage; break;
                    case 44: p_x244 = percentage; break;
                    case 45: p_x245 = percentage; break;
                    case 46: p_x246 = percentage; break;
                    case 47: p_x247 = percentage; break;
                    case 48: p_x248 = percentage; break;
                    case 49: p_x249 = percentage; break;
                    case 50: p_x250 = percentage; break;
                    case 51: p_x251 = percentage; break;
                    case 52: p_x252 = percentage; break;
                    case 53: p_x253 = percentage; break;
                    case 54: p_x254 = percentage; break;
                    case 55: p_x255 = percentage; break;
                    case 56: p_x256 = percentage; break;
                    case 57: p_x257 = percentage; break;
                    case 58: p_x258 = percentage; break;
                    case 59: p_x259 = percentage; break;
                    case 60: p_x260 = percentage; break;
                    case 61: p_x261 = percentage; break;
                    case 62: p_x262 = percentage; break;
                    case 63: p_x263 = percentage; break;
                    case 64: p_x264 = percentage; break;
                    case 65: p_x265 = percentage; break;
                    case 66: p_x266 = percentage; break;
                    case 67: p_x267 = percentage; break;
                    case 68: p_x268 = percentage; break;
                    case 69: p_x269 = percentage; break;
                    case 70: p_x270 = percentage; break;
                    case 71: p_x271 = percentage; break;
                    case 72: p_x272 = percentage; break;
                    case 73: p_x273 = percentage; break;
                    case 74: p_x274 = percentage; break;
                    case 75: p_x275 = percentage; break;
                    case 76: p_x276 = percentage; break;
                    case 77: p_x277 = percentage; break;
                    case 78: p_x278 = percentage; break;
                    case 79: p_x279 = percentage; break;
                    case 80: p_x280 = percentage; break;
                    case 81: p_x281 = percentage; break;
                    case 82: p_x282 = percentage; break;
                    case 83: p_x283 = percentage; break;
                    case 84: p_x284 = percentage; break;
                    case 85: p_x285 = percentage; break;
                    case 86: p_x286 = percentage; break;
                    case 87: p_x287 = percentage; break;
                    case 88: p_x288 = percentage; break;
                    case 89: p_x289 = percentage; break;
                    case 90: p_x290 = percentage; break;
                    case 91: p_x291 = percentage; break;
                    case 92: p_x292 = percentage; break;
                    case 93: p_x293 = percentage; break;
                    case 94: p_x294 = percentage; break;
                    case 95: p_x295 = percentage; break;
                    case 96: p_x296 = percentage; break;
                    case 97: p_x297 = percentage; break;
                    case 98: p_x298 = percentage; break;
                    case 99: p_x299 = percentage; break;
                    case 100: p_x2100 = percentage; break;
                    case 101: p_x2101 = percentage; break;
                    case 102: p_x2102 = percentage; break;
                    case 103: p_x2103 = percentage; break;
                    case 104: p_x2104 = percentage; break;
                    case 105: p_x2105 = percentage; break;
                    case 106: p_x2106 = percentage; break;
                    case 107: p_x2107 = percentage; break;
                    case 108: p_x2108 = percentage; break;
                    case 109: p_x2109 = percentage; break;
                    case 110: p_x2110 = percentage; break;
                    case 111: p_x2111 = percentage; break;
                    case 112: p_x2112 = percentage; break;
                    case 113: p_x2113 = percentage; break;
                    case 114: p_x2114 = percentage; break;
                    case 115: p_x2115 = percentage; break;
                    case 116: p_x2116 = percentage; break;
                    case 117: p_x2117 = percentage; break;

                }
                break;

            // Continuer pour x3 à x7
            case 2: // Pour x3
                switch (value) {
                    case 0: p_x30 = percentage; break;
                    case 1: p_x31 = percentage; break;
                    case 2: p_x32 = percentage; break;
                    case 3: p_x33 = percentage; break;
                    case 4: p_x34 = percentage; break;
                    case 5: p_x35 = percentage; break;
                    case 6: p_x36 = percentage; break;
                    case 7: p_x37 = percentage; break;
                    case 8: p_x38 = percentage; break;
                    case 9: p_x39 = percentage; break;
                    case 10: p_x310 = percentage; break;
                    case 11: p_x311 = percentage; break;

                }
                break;

            case 3: // Pour x4
                switch (value) {
                    case 0: p_x40 = percentage; break;
                    case 1: p_x41 = percentage; break;
                    case 2: p_x42 = percentage; break;
                    case 3: p_x43 = percentage; break;
                    case 4: p_x44 = percentage; break;

                }
                break;

            case 4: // Pour x5
                switch (value) {
                    case 0: p_x50 = percentage; break;
                    case 1: p_x51 = percentage; break;
                    case 2: p_x52 = percentage; break;
                    case 3: p_x53 = percentage; break;

                }
                break;

            case 5: // Pour x6
                switch (value) {
                    case 0: p_x60 = percentage; break;
                    case 1: p_x61 = percentage; break;
                    case 2: p_x62 = percentage; break;

                }
                break;

            case 6: // Pour x7
                switch (value) {
                    case 0: p_x70 = percentage; break;
                    case 1: p_x71 = percentage; break;
                    case 2: p_x72 = percentage; break;

                }
                break;
        }
    }

    public void displayResults() {
        try {
            System.out.println("\nRésultats pour tous les attributs:");

            // Pour chaque xi (7 attributs)
            for (int i = 1; i <= 7; i++) {
                System.out.printf("\nPourcentages pour x%d:\n", i);

                // Pour chaque valeur possible de l'attribut actuel
                for (int j = 0; j <= limits[i-1]; j++) {
                    String methodName = String.format("getP_x%d%d", i, j);
                    Method method = this.getClass().getMethod(methodName);
                    double percentage = (double) method.invoke(this);

                    System.out.printf("x%d=%d: %.1f%%\n", i, j, percentage);
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Fonction pour prédire la chance d'apparition d'une combinaison
    public double predict_chance(int y1, int y2, int y3, int y4, int y5, int y6, int y7) {
        double totalProbability = 0.0;

        try {
            // Pour y1
            for (int i = 0; i <= limits[0]; i++) {
                if (y1 == i) {
                    String methodName = String.format("getP_x1%d", i);
                    Method method = this.getClass().getMethod(methodName);
                    double p_y1 = (double) method.invoke(this);
                    totalProbability += p_y1;
                }
            }

            // Pour y2
            for (int i = 0; i <= limits[1]; i++) {
                if (y2 == i) {
                    String methodName = String.format("getP_x2%d", i);
                    Method method = this.getClass().getMethod(methodName);
                    double p_y2 = (double) method.invoke(this);
                    totalProbability += p_y2;
                }
            }

            // Pour y3
            for (int i = 0; i <= limits[2]; i++) {
                if (y3 == i) {
                    String methodName = String.format("getP_x3%d", i);
                    Method method = this.getClass().getMethod(methodName);
                    double p_y3 = (double) method.invoke(this);
                    totalProbability += p_y3;
                }
            }

            // Pour y4
            for (int i = 0; i <= limits[3]; i++) {
                if (y4 == i) {
                    String methodName = String.format("getP_x4%d", i);
                    Method method = this.getClass().getMethod(methodName);
                    double p_y4 = (double) method.invoke(this);
                    totalProbability += p_y4;
                }
            }

            // Pour y5
            for (int i = 0; i <= limits[4]; i++) {
                if (y5 == i) {
                    String methodName = String.format("getP_x5%d", i);
                    Method method = this.getClass().getMethod(methodName);
                    double p_y5 = (double) method.invoke(this);
                    totalProbability += p_y5;
                }
            }

            // Pour y6
            for (int i = 0; i <= limits[5]; i++) {
                if (y6 == i) {
                    String methodName = String.format("getP_x6%d", i);
                    Method method = this.getClass().getMethod(methodName);
                    double p_y6 = (double) method.invoke(this);
                    totalProbability += p_y6;
                }
            }

            // Pour y7
            for (int i = 0; i <= limits[6]; i++) {
                if (y7 == i) {
                    String methodName = String.format("getP_x7%d", i);
                    Method method = this.getClass().getMethod(methodName);
                    double p_y7 = (double) method.invoke(this);
                    totalProbability += p_y7;
                }
            }

            // Calculer la moyenne des probabilités
            return totalProbability / 7.0;

        } catch (Exception e) {
            System.out.println("Erreur lors de la prédiction: " + e.getMessage());
            return -1.0;
        }
    }
    // Fonction pour entrainer le modèle
    public void trainModel() {
        try {
            if (data == null) {
                throw new IllegalStateException("Les données n'ont pas été chargées. Appelez d'abord analyzeData().");
            }

            model = new NaiveBayes();
            model.buildClassifier(data);
            System.out.println("Modèle entrainé avec succès");

        } catch (Exception e) {
            System.out.println("Erreur lors de l'entrainement du modèle: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Fonction pour évaluer le modèle
    public void evaluateModel() {
        try {
            if (model == null) {
                throw new IllegalStateException("Le modèle n'a pas été entrainé. Appelez d'abord trainModel().");
            }
            if (data == null) {
                throw new IllegalStateException("Les données n'ont pas été chargées. Appelez d'abord analyzeData().");
            }

            Evaluation eval = new Evaluation(data);
            eval.crossValidateModel(model, data, 10, new Random(1));

            System.out.println("\nÉvaluation du modèle:");
            System.out.println("Accuracy: " + String.format("%.2f%%", eval.pctCorrect()));
            System.out.println("Precision: " + String.format("%.3f", eval.weightedPrecision()));
            System.out.println("Recall: " + String.format("%.3f", eval.weightedRecall()));
            System.out.println("F1-Score: " + String.format("%.3f", eval.weightedFMeasure()));

        } catch (Exception e) {
            System.out.println("Erreur lors de l'évaluation du modèle: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Fonction pour afficher les statistiques du modèle
    public void displayModelStats() {
        try {
            if (data == null) {
                throw new IllegalStateException("Les données n'ont pas été chargées. Appelez d'abord analyzeData().");
            }

            System.out.println("\nStatistiques du modèle:");
            System.out.println("Nombre total d'instances: " + data.numInstances());
            System.out.println("Nombre d'attributs: " + data.numAttributes());

            // Statistiques pour chaque attribut
            for (int i = 0; i < data.numAttributes(); i++) {
                System.out.printf("\nAttribut x%d:\n", i+1);
                System.out.printf("Min: %.0f\n", data.attributeStats(i).numericStats.min);
                System.out.printf("Max: %.0f\n", data.attributeStats(i).numericStats.max);
                System.out.printf("Moyenne: %.2f\n", data.attributeStats(i).numericStats.mean);
                System.out.printf("Écart-type: %.2f\n", data.attributeStats(i).numericStats.stdDev);
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage des statistiques: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Fonction pour extraire le modèle
    public NaiveBayes getModel() {
        return model;
    }

    // Fonction pour prédire avec le modèle entrainé
    public double[] predictWithModel(int[] values) {
        try {
            // Créer une instance avec les valeurs fournies
            double[] instanceValue = new double[values.length];
            for (int i = 0; i < values.length; i++) {
                instanceValue[i] = values[i];
            }

            return model.distributionForInstance(data.instance(0));
        } catch (Exception e) {
            System.out.println("Erreur lors de la prédiction: " + e.getMessage());
            return null;
        }
    }


    public void saveModel(String modelPath) {
        try {
            // Créer les dossiers si nécessaire
            File modelFile = new File(modelPath);
            modelFile.getParentFile().mkdirs();

            // Sauvegarder le modèle
            SerializationHelper.write(modelPath, model);
            System.out.println("Modèle sauvegardé avec succès dans: " + modelPath);
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde du modèle: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Fonction pour charger un modèle à partir d'un fichier
    public void loadModel(String modelPath) {
        try {
            model = (NaiveBayes) SerializationHelper.read(modelPath);
            System.out.println("Modèle chargé avec succès depuis: " + modelPath);
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement du modèle: " + e.getMessage());
            e.printStackTrace();
        }
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="getters des valeurs xij">//GEN-BEGIN:initComponents
    public double getx10() { return 0; }
    public double getx11() { return 1; }
    public double getx12() { return 2; }
    public double getx13() { return 3; }
    public double getx14() { return 4; }
    public double getx15() { return 5; }
    public double getx16() { return 6; }
    public double getx17() { return 7; }
    public double getx18() { return 8; }
    public double getx19() { return 9; }
    public double getx110() { return 10; }
    public double getx111() { return 11; }


    public double getx20() { return 0; }
    public double getx21() { return 1; }
    public double getx22() { return 2; }
    public double getx23() { return 3; }
    public double getx24() { return 4; }
    public double getx25() { return 5; }
    public double getx26() { return 6; }
    public double getx27() { return 7; }
    public double getx28() { return 8; }
    public double getx29() { return 9; }
    public double getx210() { return 10; }
    public double getx211() { return 11; }
    public double getx212() { return 12; }
    public double getx213() { return 13; }
    public double getx214() { return 14; }
    public double getx215() { return 15; }
    public double getx216() { return 16; }
    public double getx217() { return 17; }
    public double getx218() { return 18; }
    public double getx219() { return 19; }
    public double getx220() { return 20; }
    public double getx221() { return 21; }
    public double getx222() { return 22; }
    public double getx223() { return 23; }
    public double getx224() { return 24; }
    public double getx225() { return 25; }
    public double getx226() { return 26; }
    public double getx227() { return 27; }
    public double getx228() { return 28; }
    public double getx229() { return 29; }
    public double getx230() { return 30; }
    public double getx231() { return 31; }
    public double getx232() { return 32; }
    public double getx233() { return 33; }
    public double getx234() { return 34; }
    public double getx235() { return 35; }
    public double getx236() { return 36; }
    public double getx237() { return 37; }
    public double getx238() { return 38; }
    public double getx239() { return 39; }
    public double getx240() { return 40; }
    public double getx241() { return 41; }
    public double getx242() { return 42; }
    public double getx243() { return 43; }
    public double getx244() { return 44; }
    public double getx245() { return 45; }
    public double getx246() { return 46; }
    public double getx247() { return 47; }
    public double getx248() { return 48; }
    public double getx249() { return 49; }
    public double getx250() { return 50; }
    public double getx251() { return 51; }
    public double getx252() { return 52; }
    public double getx253() { return 53; }
    public double getx254() { return 54; }
    public double getx255() { return 55; }
    public double getx256() { return 56; }
    public double getx257() { return 57; }
    public double getx258() { return 58; }
    public double getx259() { return 59; }
    public double getx260() { return 60; }
    public double getx261() { return 61; }
    public double getx262() { return 62; }
    public double getx263() { return 63; }
    public double getx264() { return 64; }
    public double getx265() { return 65; }
    public double getx266() { return 66; }
    public double getx267() { return 67; }
    public double getx268() { return 68; }
    public double getx269() { return 69; }
    public double getx270() { return 70; }
    public double getx271() { return 71; }
    public double getx272() { return 72; }
    public double getx273() { return 73; }
    public double getx274() { return 74; }
    public double getx275() { return 75; }
    public double getx276() { return 76; }
    public double getx277() { return 77; }
    public double getx278() { return 78; }
    public double getx279() { return 79; }
    public double getx280() { return 80; }
    public double getx281() { return 81; }
    public double getx282() { return 82; }
    public double getx283() { return 83; }
    public double getx284() { return 84; }
    public double getx285() { return 85; }
    public double getx286() { return 86; }
    public double getx287() { return 87; }
    public double getx288() { return 88; }
    public double getx289() { return 89; }
    public double getx290() { return 90; }
    public double getx291() { return 91; }
    public double getx292() { return 92; }
    public double getx293() { return 93; }
    public double getx294() { return 94; }
    public double getx295() { return 95; }
    public double getx296() { return 96; }
    public double getx297() { return 97; }
    public double getx298() { return 98; }
    public double getx299() { return 99; }
    public double getx2100() { return 100; }
    public double getx2101() { return 101; }
    public double getx2102() { return 102; }
    public double getx2103() { return 103; }
    public double getx2104() { return 104; }
    public double getx2105() { return 105; }
    public double getx2106() { return 106; }
    public double getx2107() { return 107; }
    public double getx2108() { return 108; }
    public double getx2109() { return 109; }
    public double getx2110() { return 110; }
    public double getx2111() { return 111; }
    public double getx2112() { return 112; }
    public double getx2113() { return 113; }
    public double getx2114() { return 114; }
    public double getx2115() { return 115; }
    public double getx2116() { return 116; }
    public double getx2117() { return 117; }


    public double getx30() { return 0; }
    public double getx31() { return 1; }
    public double getx32() { return 2; }
    public double getx33() { return 3; }
    public double getx34() { return 4; }
    public double getx35() { return 5; }
    public double getx36() { return 6; }
    public double getx37() { return 7; }
    public double getx38() { return 8; }
    public double getx39() { return 9; }
    public double getx310() { return 10; }
    public double getx311() { return 11; }


    public double getx40() { return 0; }
    public double getx41() { return 1; }
    public double getx42() { return 2; }
    public double getx43() { return 3; }
    public double getx44() { return 4; }

    public double getx50() { return 0; }
    public double getx51() { return 1; }
    public double getx52() { return 2; }
    public double getx53() { return 3; }


    public double getx60() { return 0; }
    public double getx61() { return 1; }
    public double getx62() { return 2; }


    public double getx70() { return 0; }
    public double getx71() { return 1; }
    public double getx72() { return 2; }








// </editor-fold>//GEN-END:initComponents



    public void showPanel(WekaAnalysisExtended_training analyzer, String filePath) throws IOException {
        analyzer.analyzeData();

        // Lecture des données et prédiction
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        // Ignorer les lignes d'en-tête jusqu'à @data
        while ((line = reader.readLine()) != null && !line.equals("@data")) {
            continue;
        }

        // Lire la première ligne de données
        if ((line = reader.readLine()) != null) {
            // Diviser la ligne en valeurs en utilisant la virgule comme séparateur
            String[] values = line.split(",");

            // Convertir et stocker chaque valeur
            int x1 = Integer.parseInt(values[0]); // Ville
            int x2 = Integer.parseInt(values[1]); // Secteur_activite
            int x3 = Integer.parseInt(values[2]); // Type_contrat
            int x4 = Integer.parseInt(values[3]); // Niveau_etudes
            int x5 = Integer.parseInt(values[4]); // Experience
            int x6 = Integer.parseInt(values[5]); // Langue
            int x7 = Integer.parseInt(values[6]); // Teletravail

            // Afficher les valeurs extraites
            System.out.println("Valeurs extraites :");
            System.out.println("Ville (x1) : " + x1);
            System.out.println("Secteur_activite (x2) : " + x2);
            System.out.println("Type_contrat (x3) : " + x3);
            System.out.println("Niveau_etudes (x4) : " + x4);
            System.out.println("Experience (x5) : " + x5);
            System.out.println("Langue (x6) : " + x6);
            System.out.println("Teletravail (x7) : " + x7);

            // Prédiction
            double chance1 = analyzer.predict_chance(x1, x2, x3, x4, x5, x6, x7);
            String salaireTexte = String.format("La combinaison de votre profil est de pourcentage de : %.2f%%", chance1);
            System.out.printf("\nTest du modèle : Chance de la combinaison : %.2f%%\n", chance1);

            // Création de la fenêtre
            JFrame frame = new JFrame("Prédiction de Chance");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(650, 600);
            frame.setFont(new Font("Arial", Font.BOLD, 75));

            // Définir un label avec une image de fond
            JLabel backgroundLabel = new JLabel(new ImageIcon("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Chance/ai.jpeg")); // Remplacez par votre chemin d'image
            backgroundLabel.setLayout(new BorderLayout()); // Permet d'ajouter des composants au-dessus de l'image

            // Création du label pour le texte
            JLabel label = new JLabel(salaireTexte, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setForeground(Color.BLACK); // Pour rendre le texte lisible sur une image sombre

            // Ajout du label texte à l'arrière-plan
            backgroundLabel.add(label, BorderLayout.CENTER);

            // Définir le backgroundLabel comme contenu de la fenêtre
            frame.setContentPane(backgroundLabel);

            // Centrer la fenêtre sur l'écran
            frame.setLocationRelativeTo(null);

            // Rendre la fenêtre visible
            frame.setVisible(true);
        }
    }






    public static void main(String[] args) throws IOException {
        WekaAnalysisExtended_training analyzer = new WekaAnalysisExtended_training();


        // Chemin pour sauvegarder le modèle
        String modelPath = "C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Chance/chance_prediction_model.model";

        // Entrainer et sauvegarder le modèle
        analyzer.analyzeData();
       // analyzer.trainModel();
        //analyzer.evaluateModel();
       // analyzer.displayModelStats();
       // analyzer.saveModel(modelPath);
      // analyzer.displayResults();

        // Exemple de prédiction
        double chance = analyzer.predict_chance(0,0,0,0,0,0,0);
        System.out.printf("\ntest de model Chance de la combinaison : %.2f%%\n", chance);




        int x1, x2, x3, x4, x5, x6, x7;

        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/ML_Chance/output_form1.arff"));
        String line;

        // Ignorer les lignes d'en-tête jusqu'à @data
        while ((line = reader.readLine()) != null && !line.equals("@data")) {
            continue;
        }


        // Lire la première ligne de données
        if ((line = reader.readLine()) != null) {
            // Diviser la ligne en valeurs en utilisant la virgule comme séparateur
            String[] values = line.split(",");

            // Convertir et stocker chaque valeur
            x1 = Integer.parseInt(values[0]); // Ville
            x2 = Integer.parseInt(values[1]); // Secteur_activite
            x3 = Integer.parseInt(values[2]); // Type_contrat
            x4 = Integer.parseInt(values[3]); // Niveau_etudes
            x5 = Integer.parseInt(values[4]); // Experience
            x6 = Integer.parseInt(values[5]); // Langue
            x7 = Integer.parseInt(values[6]); // Teletravail

            // À ce point, vous pouvez utiliser les variables x1 à x7
            System.out.println("Valeurs extraites :");
            System.out.println("Ville (x1) : " + x1);
            System.out.println("Secteur_activite (x2) : " + x2);
            System.out.println("Type_contrat (x3) : " + x3);
            System.out.println("Niveau_etudes (x4) : " + x4);
            System.out.println("Experience (x5) : " + x5);
            System.out.println("Langue (x6) : " + x6);
            System.out.println("Teletravail (x7) : " + x7);

            // Exemple de prédiction

            double chance1 = analyzer.predict_chance(+x1,x2,x3,x4,x5,x6,x7);
            String salaireTexte = String.format("La combinaison de votre profil est de  pourcentage de  : %.2f%%", chance1);
            System.out.printf("\ntest de model Chance de la combinaison : %.2f%%\n", chance1);

            JFrame frame = new JFrame("Prédiction de Chance");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 400);

            // Centrer la fenêtre sur l'écran
            frame.setLocationRelativeTo(null);

            // Création du label
            JLabel label = new JLabel(salaireTexte, SwingConstants.CENTER);

            // Configuration de la disposition et ajout du label
            frame.setLayout(new BorderLayout());
            frame.add(label, BorderLayout.CENTER);

            // Rendre la fenêtre visible
            frame.setVisible(true);









        }}
}