package NLP1;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JobOffersPanel extends JPanel {
    private final JLabel jobDetailsLabel;
    private final JButton nextButton;
    private final JButton prevButton;
    private final JButton exportButton; // New export button
    private List<Map<String, Object>> jobOffers;
    private int currentIndex;

    // Constructor to initialize the panel and components
    public JobOffersPanel() {
        setLayout(new BorderLayout());

        // Job details label to display the current job
        jobDetailsLabel = new JLabel("", SwingConstants.CENTER);
        jobDetailsLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Load images for buttons
        ImageIcon nextIcon = new ImageIcon("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/NLP1/next.png");
        ImageIcon prevIcon = new ImageIcon("C:/Users/HP/IdeaProjects/scraping_data/src/main/java/NLP1/previous.png");

        // Navigation buttons with images
        nextButton = new JButton(nextIcon);
        prevButton = new JButton(prevIcon);

        // Export button to export job offers to Excel
        exportButton = new JButton("Export to Excel");
        exportButton.addActionListener(e -> exportToExcel());

        // Customize button appearance (optional)
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setFocusPainted(false);

        prevButton.setBorderPainted(false);
        prevButton.setContentAreaFilled(false);
        prevButton.setFocusPainted(false);

        // Button panel for navigation and export button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(exportButton); // Add export button to the panel

        // Add components to the panel
        add(jobDetailsLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        nextButton.addActionListener(e -> showNextJob());
        prevButton.addActionListener(e -> showPreviousJob());
    }

    // Method to update the job offers list and display the first job
    public void updateJobOffers(List<Map<String, Object>> jobOffers) {
        this.jobOffers = jobOffers;
        currentIndex = 0;

        if (jobOffers == null || jobOffers.isEmpty()) {
            // Display "no job offers" message
            jobDetailsLabel.setText("<html><div style='text-align: center; color: red; font-size: 18px;'>"
                    + "No job offers available based on your prompt.<br>"
                    + "</div></html>");
            nextButton.setEnabled(false);
            prevButton.setEnabled(false);
        } else {
            // Enable navigation buttons and show the first job
            nextButton.setEnabled(true);
            prevButton.setEnabled(true);
            showJobDetails(currentIndex);
        }
    }

        // Method to display the details of the current job
        private void showJobDetails(int index) {
            if (jobOffers == null || jobOffers.isEmpty() || index < 0 || index >= jobOffers.size()) {
                return;
            }
    
            Map<String, Object> job = jobOffers.get(index);
    
            String jobDetails = String.format(
                    "<html>"
                            + "<div style='text-align: left; font-family: Arial, sans-serif; background-color: #F5F7FA; padding: 15px; border-radius: 10px; border: 1px solid #DDE2E5;'>"
                            + "<b style='font-size: 18px; color: #2A3F54;'>Title:</b> <span style='font-size: 16px; color: #4A6572;'>%s</span><br><br>"
                            + "<b style='font-size: 18px; color: #2A3F54;'>City:</b> <span style='font-size: 16px; color: #4A6572;'>%s</span><br><br>"
                            + "<b style='font-size: 18px; color: #2A3F54;'>Contract Type:</b> <span style='font-size: 16px; color: #4A6572;'>%s</span><br><br>"
                            + "<b style='font-size: 18px; color: #2A3F54;'>Speciality:</b> <span style='font-size: 16px; color: #4A6572;'>%s</span><br><br>"
                            + "<b style='font-size: 18px; color: #2A3F54;'>Description:</b> <span style='font-size: 16px; color: #4A6572;'>%s</span><br><br>"
                            + "</div>"
                            + "</html>",
                    job.get("title"),
                    job.get("city"),
                    job.get("contractType"),
                    job.get("speciality"),
                    job.get("description")
            );
    
            jobDetailsLabel.setText(jobDetails);
        }
    

    // Show the next job
    private void showNextJob() {
        if (currentIndex < jobOffers.size() - 1) {
            currentIndex++;
            showJobDetails(currentIndex);
        }
    }

    // Show the previous job
    private void showPreviousJob() {
        if (currentIndex > 0) {
            currentIndex--;
            showJobDetails(currentIndex);
        }
    }

    // Method to export job offers to Excel
    private void exportToExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Job Offers");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Title", "City", "Contract Type", "Speciality", "Description"};
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Add job offer data
            int rowNum = 1;
            for (Map<String, Object> job : jobOffers) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue((String) job.get("title"));
                row.createCell(1).setCellValue((String) job.get("city"));
                row.createCell(2).setCellValue((String) job.get("contractType"));
                row.createCell(3).setCellValue((String) job.get("speciality"));
                row.createCell(4).setCellValue((String) job.get("description"));
            }

            // Write the Excel file to disk
            File file = new File("job_offers.xlsx");
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(this, "Job offers exported successfully!");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting job offers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to create and show the frame containing the job offers panel
    public static void showJobOffersPanel(List<Map<String, Object>> jobOffers) {
        JFrame frame = new JFrame("Job Offers");
        JobOffersPanel panel = new JobOffersPanel();
        panel.updateJobOffers(jobOffers);
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centrer la fenêtre
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

}