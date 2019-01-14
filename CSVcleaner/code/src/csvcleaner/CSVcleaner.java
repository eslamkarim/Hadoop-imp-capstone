package csvcleaner;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
public class CSVcleaner {
    static String temp = "";
    public static void main(String[] args) throws IOException {
    int year = 1988;
    String name = args[0];
    String outputfolder = args[1];
      for(int i=0; i<=20; i++){
        
        File inputfolder = new File(name+year); //put aviation directory here
        System.out.println("Reading files under the folder "+ inputfolder.getAbsolutePath());
        listFilesForFolder(inputfolder, year, outputfolder);
        year++;
  }}
  public static void listFilesForFolder(final File folder,int year,String outputfolder) throws IOException {
    String zfile="";
    String[] record = null;
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isFile()) {
          temp = fileEntry.getName();
          if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("zip")){
              zfile = folder.getAbsolutePath()+ "\\" + fileEntry.getName();
              System.out.println(zfile);
               ZipFile zipFile = new ZipFile(zfile);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while(entries.hasMoreElements()){
            StringBuilder sb = new StringBuilder();
        
            ZipEntry entry = entries.nextElement();
            if(entry.getName().contains("csv")){
                int trig =0;
                FileWriter fw = new FileWriter(outputfolder+"\\"+year+"\\"+year+".csv",true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);
                InputStream stream = zipFile.getInputStream(entry);
                try (CSVReader reader = new CSVReader(new InputStreamReader(stream, "UTF-8"))) {
                    while ((record = reader.readNext()) != null) {
                        if(trig !=1){
                            sb.append(record[2]); //month
                            sb.append(",");
                            sb.append(record[4]); //day of month
                            sb.append(",");
                            sb.append(record[6]); //unique carrier id
                            sb.append(",");
                            sb.append(record[41]); //canceled
                            sb.append(",");
                            sb.append(record[25]); //dept delay
                            sb.append(",");
                            sb.append(record[36]); //arr delay
                            sb.append(",");
                            sb.append(record[11]); //origin airport
                            sb.append(",");
                            sb.append(record[17]); //destination airport
                            sb.append(",");
                            sb.append("\n");
                            trig++;
                            continue;
                        }
                        if(record[41].length() != 0 && Double.parseDouble(record[41].replaceAll("\"", "")) != 1 && record[37].length() != 0){
                            sb.append(record[2]); // month
                            sb.append(",");
                            sb.append(record[4]); //day of month
                            sb.append(",");
                            sb.append(record[6]); //unique carrier id
                            sb.append(",");
                            sb.append(record[41]); //canceled
                            sb.append(",");
                            sb.append(record[25]); //dept delay
                            sb.append(",");
                            sb.append(record[36]); //arr delay
                            sb.append(",");
                            sb.append(record[11]); //origin airport
                            sb.append(",");
                            sb.append(record[17]); //destination airport
                            sb.append(",");
                            sb.append("\n");
                           
                        }
                        
                    }
                            out.write(sb.toString());
		            System.out.println("DOne!!!!");
                            reader.close();
                            
                }}}
        zipFile.close();
        }}}
    
    }
  }

