package com.instructure.xfdfexport;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Iterator;


/**
 * Created by jgorr on 1/19/17.
 */
public class App {
    public App() {
    }

    public void exportFDF(File inputFile) {
        PDDocument pdf = null;
        try {
            pdf = PDDocument.load(inputFile);
            PDPageTree pageTree =  pdf.getDocumentCatalog().getPages();
            Iterator<PDPage> i = pageTree.iterator();
            while( i.hasNext() ){
                PDPage page = i.next();
                List<PDAnnotation> annotations= page.getAnnotations();
                for(PDAnnotation annotation: page.getAnnotations()){
                    System.out.println("annotation.toString() = " + annotation.toString());
                    System.out.println("annotation.getAnnotationName() = " + annotation.getAnnotationName());
                    System.out.println("annotation.getContents() = " + annotation.getContents());
                }

            }
            // get  annotation dictionaries
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            close(pdf);
        }
    }

    private void close(PDDocument doc) {
        try {
            if(doc != null) {
                doc.close();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    private void close(FDFDocument doc) {
       try {
           if(doc !=null) {
               doc.close();
           }
       } catch(Exception e){
           e.printStackTrace();
       }
    }

    public static void main(String args[]) {

        App app = new App();
        File inputFile = new File("lambda-output.pdf");
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String text = br.readLine();
            System.out.println("text = " + text);
            br.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        app.exportFDF(inputFile);
        System.exit(0);
    }
}
