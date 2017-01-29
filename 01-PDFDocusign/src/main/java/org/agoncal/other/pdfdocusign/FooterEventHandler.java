package org.agoncal.other.pdfdocusign;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;

import java.io.IOException;

public class FooterEventHandler implements IEventHandler {

    protected Document doc;

    public FooterEventHandler(Document doc) {
        this.doc = doc;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfCanvas canvas = new PdfCanvas(docEvent.getPage());
        Rectangle pageSize = docEvent.getPage().getPageSize();
        canvas.beginText();
        try {
            canvas.setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA_OBLIQUE), 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        canvas.moveText((pageSize.getRight() - doc.getRightMargin() - (pageSize.getLeft() + doc.getLeftMargin())) / 2 + doc.getLeftMargin(), 10).showText("Me Myself and I Company");
    }
}
