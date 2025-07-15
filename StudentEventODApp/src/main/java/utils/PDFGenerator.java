// File: src/main/java/utils/PDFGenerator.java
package utils;

import model.ODRequest;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import java.io.IOException;

public class PDFGenerator {

    public static void generateODForm(ODRequest request, String filePath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream content = new PDPageContentStream(document, page);

            float margin = 50;
            float y = page.getMediaBox().getHeight() - margin;

            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.beginText();
            content.newLineAtOffset(margin + 70, y);
            content.showText("SRI KRISHNA ARTS AND SCIENCE COLLEGE");
            content.endText();

            // Underline
            y -= 5;
            content.moveTo(margin + 65, y);
            content.lineTo(page.getMediaBox().getWidth() - margin - 65, y);
            content.stroke();

            y -= 30;
            float tableStartY = y;
            float tableRowHeight = 30;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float col1Width = tableWidth * 0.25f;
            float col2Width = tableWidth * 0.75f;

            String[][] data = {
                {"Name", request.getName()},
                {"Reg. No", request.getRegNo()},
                {"Department", request.getDepartment()},
                {"Event", request.getEvent()},
                {"Date", request.getDate()},
                {"Email", request.getEmail()}
            };

            content.setFont(PDType1Font.HELVETICA_BOLD, 12);
            for (String[] row : data) {
                content.addRect(margin, y - tableRowHeight, col1Width, tableRowHeight);
                content.addRect(margin + col1Width, y - tableRowHeight, col2Width, tableRowHeight);
                content.stroke();

                content.beginText();
                content.newLineAtOffset(margin + 5, y - 20);
                content.showText(row[0]);
                content.endText();

                content.setFont(PDType1Font.HELVETICA, 12);
                content.beginText();
                content.newLineAtOffset(margin + col1Width + 5, y - 20);
                content.showText(row[1]);
                content.endText();

                content.setFont(PDType1Font.HELVETICA_BOLD, 12);
                y -= tableRowHeight;
            }

            // Signature section
            y -= 50;
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 12);
            content.newLineAtOffset(margin, y);
            content.showText("Tutor Signature");
            content.endText();

            content.beginText();
            content.newLineAtOffset(page.getMediaBox().getWidth() / 2 - 50, y);
            content.showText("HOD Signature");
            content.endText();

            content.beginText();
            content.newLineAtOffset(page.getMediaBox().getWidth() - margin - 150, y);
            content.showText("Programme Officer Signature");
            content.endText();

            content.close();
            document.save(filePath);
        } catch (IOException e) {
        }
    }
}

