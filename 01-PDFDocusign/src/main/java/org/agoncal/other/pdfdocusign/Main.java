package org.agoncal.other.pdfdocusign;

public class Main {

    // Name of the generated PDF file
    private static final String BOOTH_ALLOCATION_PDF_FILE = "target/pdf/MyGenerated.pdf";

    public static void main(String[] args) throws Exception {
        // PdfGenerator.generatePdf(BOOTH_ALLOCATION_PDF_FILE);
        PdfSender.sendPdf(BOOTH_ALLOCATION_PDF_FILE);
    }
}
