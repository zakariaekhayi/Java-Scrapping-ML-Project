package scrapping.scrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class webscrap {
    public static void main(String[] args) {
        try {
            String baseUrl = "https://www.rekrute.com/offres.html";
            String urlParams = "?workExperienceId%5B0%5D=1&workExperienceId%5B1%5D=2";
            Map<String, jobDetails> jobMap = new HashMap<>();
            int currentPage = 1;
            boolean hasNextPage = true;

            while (hasNextPage) {
                // Construct the URL
                String url = currentPage == 1
                        ? "https://www.rekrute.com/offres.html?s=1&p=1&o=1&workExperienceId%5B0%5D=1&workExperienceId%5B1%5D=2"
                        : baseUrl + "?p=" + currentPage + "&s=1&o=1" + urlParams;

                System.out.println("Scraping Page: " + currentPage);
                Document doc = Jsoup.connect(url).get();
                Elements jobs = doc.select(".section");

                if (jobs.isEmpty()) {
                    System.out.println("[WARNING] No jobs found on page " + currentPage);
                    break;
                }

                // Process jobs
                for (Element job : jobs) {
                    String title = job.select("h2 > a").text().trim();
                    String activity = job.select(".holder > .info > ul > li:contains(Secteur d\\'activité) a").text().trim();
                    String fonction = job.select(".holder > .info > ul > li:contains(Fonction) a").text().trim();
                    String experience = job.select(".holder > .info > ul > li:contains(Expérience requise) a").text().trim();
                    String niveauEtude = job.select(".holder > .info > ul > li:contains(Niveau d\\'étude demandé) a").text().trim();

                    // Vérifiez si le titre est valide et unique
                    if (!title.isEmpty() && !jobMap.containsKey(title)) {
                        jobDetails jobD = new jobDetails(experience, fonction, activity, niveauEtude);
                        jobMap.put(title, jobD);
                    }
                }

                // Check if the next page exists
                Element nextPageElement = doc.selectFirst(".next");
                if (nextPageElement != null && !nextPageElement.hasClass("disabled")) {
                    currentPage++;
                } else {
                    hasNextPage = false;
                }
            }

            // Print all job details
            for (Map.Entry<String, jobDetails> entry : jobMap.entrySet()) {
                System.out.println("Job Title: " + entry.getKey());
                System.out.println(entry.getValue());
                System.out.println("-------------");
            }

            // Exporter les données vers un fichier Excel
            ExportToExcel.exportJobMapToExcel(jobMap, "JobDetails.xlsx");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
