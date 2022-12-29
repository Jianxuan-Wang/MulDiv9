import java.util.ArrayList;
import java.util.Random;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;

public class Main {
    public static final String DEST = "./target/tables/simple_table.pdf";

    public static void main(String[] args) throws Exception {
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();

        new Main().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        String[] multiply = new String[64];
        String[] divide = new String[64];
        int k=0,l=0;
        for(int i=2;i<=9;i++){
            for(int j=2;j<=9;j++){
                multiply[k++] = i+"×"+j + "=";
                divide[l++] = i*j + "÷" + j + "=";
            }
        }
        Random r = new Random();
        ArrayList<Integer> listMul = new ArrayList<Integer>();
        ArrayList<Integer> listDiv = new ArrayList<Integer>();
        while(listMul.size()<10){
            int ran = r.nextInt(64)+1;
            if(!listMul.contains(ran)){
                listMul.add(ran);
            }
        }
        while(listDiv.size()<10){
            int ran = r.nextInt(64)+1;
            if(!listDiv.contains(ran)){
                listDiv.add(ran);
            }
        }
        for (int i = 0; i < 10; i++) {
            table.addCell(multiply[listMul.get(i)]);
            table.addCell(divide[listDiv.get(i)]);
        }

        doc.add(table);

        doc.close();
    }
}
