package com.mycompany.txttopdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmelo
 */
public class Main {

    public static void main(String[] args) {
        Document pdfDoc = new Document(PageSize.A4.rotate());
        pdfDoc.setMargins(2f, 2f, 2f, 2f);
        
        try {
            try {
                PdfWriter.getInstance(pdfDoc, new FileOutputStream("src/output/txt.pdf"))
                        .setPdfVersion(PdfWriter.PDF_VERSION_1_7);
            } catch (DocumentException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        pdfDoc.open();
        Font myfont = new Font();
        myfont.setStyle(Font.NORMAL);
        myfont.setSize(8);
        try {
            pdfDoc.add(new Paragraph("\n"));
        } catch (DocumentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        String filename = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        String strLine;
        try {
            while ((strLine = br.readLine()) != null) {
                Paragraph para = new Paragraph(strLine + "\n", myfont);
                para.setAlignment(Element.ALIGN_JUSTIFIED);
                try {
                    pdfDoc.add(para);
                } catch (DocumentException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        pdfDoc.close();
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
