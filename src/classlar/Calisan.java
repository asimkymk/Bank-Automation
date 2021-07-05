/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classlar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author asimk
 */
abstract public class Calisan extends Kullanici implements Loglar,Onaylamalar{
    private float maas;
    public Calisan(String loginNo, String ad, String soyad, int yas, String dogumTarihi, String tcKimlik, String telefon, String cinsiyet, String mail, String password, String personType) {
        super(loginNo, ad, soyad, yas, dogumTarihi, tcKimlik, telefon, cinsiyet, mail, password, personType);
        
    }

    public Calisan(String loginNo, String password, String personType) {
        super(loginNo, password, personType);
    }

    public Calisan(String loginNo, String personType) {
        super(loginNo, personType);
    }
    
    abstract ArrayList<Object[]> hesapListele();

    public float getMaas() {
        try {
            File myObj = new File("datas/loginDatas.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String []bilgiler = data.split(",");
                if(bilgiler[2].equals(getPersonType())){
                    if(bilgiler[0].equals(getLoginNo()) && bilgiler[1].equals(getPassword())){
                        File myInf = new File("datas/"+getPersonType().toLowerCase()+"Infos.txt");
                        Scanner myInfReader = new Scanner(myInf);
                        while(myInfReader.hasNextLine()){
                            String infData = myInfReader.nextLine();
                            String []bilgilerInf = infData.split(",");
                            if(bilgilerInf[0].equals(this.getLoginNo())){
                                this.maas = Float.valueOf(bilgilerInf[9]);
                                break;
                            }
                            
                        }
                        myReader.close();
                        return this.maas;
                    }
                }

            }
            myReader.close();
            return this.maas;
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        return this.maas;
        
    }

    public void setMaas(float maas) {
        this.maas = maas;
    }

    
    public void musteriLogKaydet(String hesapId,String text){
        try {
            BufferedReader read = new BufferedReader(new FileReader("datas/bildirimler.txt"));
            ArrayList<String> list = new ArrayList<String>();

            String dataRow = read.readLine();
            while (dataRow != null) {
                list.add(dataRow);
                dataRow = read.readLine();
            }

            FileWriter writer = new FileWriter("datas/bildirimler.txt");
            writer.append(hesapId + "," + text);

            for (int i = 0; i < list.size(); i++) {
                writer.append(System.getProperty("line.separator"));
                writer.append(list.get(i));
            }
            writer.flush();
            writer.close();
           
        } catch (IOException e) {
            e.printStackTrace();
           
        }
    }
    public ArrayList<Object[]> kredileriCek(){
        ArrayList<Object[]> liste = new ArrayList<Object[]>();
        try {
            
            File myObj = new File("datas/basvurular.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[1].equals("kredi")) {
                    String loginNo = bilgiler[0];
                    String krediTur = bilgiler[2];
                    float istenenPara = Float.valueOf(bilgiler[3]);
                    int istenenHesapId = Integer.valueOf(bilgiler[4]);
                    float odenmesiGrekenPara = 0f;
                    //"İhtiyaç Kredisi (%5 Faiz)", "Konut Kredisi (%7 Faiz)", "Taşıt Kredisi (%10 Faiz)", "Ticarek Kredisi (%15 Faiz)"
                    if(krediTur.equals("Taşıt Kredisi (%10 Faiz)")){
                        odenmesiGrekenPara =istenenPara*110/100;
                    }
                    if(krediTur.equals("Konut Kredisi (%7 Faiz)")){
                        odenmesiGrekenPara =istenenPara*107/100;
                    }
                    if(krediTur.equals("İhtiyaç Kredisi (%5 Faiz)")){
                        odenmesiGrekenPara =istenenPara*105/100;
                    }
                    if(krediTur.equals("Ticaret Kredisi (%15 Faiz)")){
                        odenmesiGrekenPara =istenenPara*115/100;
                    }
                    Object[] o1 = {loginNo,krediTur,istenenPara,odenmesiGrekenPara,istenenHesapId+1};
                    liste.add(o1);
                }
            }
            
            myReader.close();
            return liste;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
    public ArrayList<Object[]> krediKartlariCek(){
        ArrayList<Object[]> liste = new ArrayList<Object[]>();
        try {
            
            File myObj = new File("datas/basvurular.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[1].equals("kredikarti")) {
                    String loginNo = bilgiler[0];
                    float istenenPara = Float.valueOf(bilgiler[2]);
                    Object[] o1 = {loginNo,istenenPara};
                    liste.add(o1);
                }
            }
            myReader.close();
            return liste;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
    public ArrayList<Object[]> transferleriCek(){
        ArrayList<Object[]> liste = new ArrayList<Object[]>();
        try {
            
            File myObj = new File("datas/basvurular.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[1].equals("transfer")) {
                    String gondericiIban = bilgiler[0];
                    String aliciIban = bilgiler[2];
                    float para = Float.valueOf(bilgiler[3]);
                    Object[] o1 = {gondericiIban,aliciIban,para};
                    liste.add(o1);
                }
            }
            myReader.close();
            return liste;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
    public ArrayList<String> hesaplariCek(String mLoginNo){
        ArrayList<String> liste = new ArrayList<String>();
        try {

            File myObj = new File("datas/hesaplar.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[0].equals(mLoginNo) && bilgiler[1].equals("hesap")) {
                    liste.add(data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
     public String[] ibanaAitHesabiCek(String iban){
       
       String [] liste = null;
        try {

            File myObj = new File("datas/hesaplar.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[3].equals(iban) && bilgiler[1].equals("hesap")) {
                    liste = data.split(",");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
    public ArrayList<Object[]> krediKartiLimitArtirmaCek(){
        ArrayList<Object[]> liste = new ArrayList<Object[]>();
        try {
            
            File myObj = new File("datas/basvurular.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[1].equals("limitartirimi")) {
                    String musteriNo = bilgiler[0];
                    String kartNo = bilgiler[2];
                    float para = Float.valueOf(bilgiler[3]);
                    Object[] o1 = {musteriNo,kartNo,para};
                    liste.add(o1);
                }
            }
            myReader.close();
            return liste;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
   @Override
    public boolean krediOnayla(String hesap, float yatirilanPara){
        try {
            String hesapId = hesap.split(",")[0];
            int hesapNo = Integer.valueOf(hesap.split(",")[4]);
            hesapNo--;
            String eskihesapBilgileri = this.hesaplariCek(hesapId).get(hesapNo);
            FileWriter fileWritter = new FileWriter("datas/krediBorclari.txt",true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(hesap.split(",")[0] + "," + hesap.split(",")[1] + "," + hesap.split(",")[2] + "," + hesap.split(",")[3] +"\n");
            bw.close();
            float hesaptakiPara = Float.valueOf(eskihesapBilgileri.split(",")[2]);
            String tp[] = eskihesapBilgileri.split(",");
            yatirilanPara = hesaptakiPara + yatirilanPara;
            tp[2] = String.valueOf(yatirilanPara);
            String yenihesapBilgileri = String.join(",", tp);
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/hesaplar.txt"), StandardCharsets.UTF_8));
            
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(eskihesapBilgileri)) {
                    fileContent.set(i, yenihesapBilgileri);
                    break;
                }
            }

            Files.write(Paths.get("datas/hesaplar.txt"), fileContent, StandardCharsets.UTF_8);
            File inputFile = new File("datas/basvurular.txt");
            File tempFile = new File("datas/basvurutmp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String arr[] = hesap.split(",");
            String lineToRemove = arr[0] + ",kredi," + arr[1] + "," +arr[2]+","+ hesapNo;
            //System.out.println(lineToRemove);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(new File("datas/basvurular.txt"));
            File f1 = new File("datas/basvurutmp.txt");
            f1.delete();
            //TR53 0001 0001 6996 5820 4350 01 IBAN numaralı vadesiz TL hesabınıza 15000.0TL' lik Taşıt Kredisi (%10 Faiz) kredi başvurunuz alınmıştır.
            logKaydet(this.hesaplariCek(hesapId).get(hesapNo).split(",")[3]+ " IBAN numaralı vadesiz TL hesaba " +hesap.split(",")[2]+"TL'lik " + hesap.split(",")[1] + " kredi başvurusu onaylandı.");
            this.musteriLogKaydet(hesapId, this.hesaplariCek(hesapId).get(hesapNo).split(",")[3]+ " IBAN numaralı vadesiz TL hesabınıza " +hesap.split(",")[2]+"TL'lik " + hesap.split(",")[1] + " kredi başvurunuz onaylanmıştır.");
            return true;   
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean krediReddet(String hesap){
        try {
            
            File inputFile = new File("datas/basvurular.txt");
            File tempFile = new File("datas/basvurutmp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String arr[] = hesap.split(",");
            int hesapid = Integer.valueOf(arr[4]);
            hesapid--;
            String lineToRemove = arr[0] + ",kredi," + arr[1] + "," +arr[2]+","+ hesapid;
            //System.out.println(lineToRemove);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(new File("datas/basvurular.txt"));
            File f1 = new File("datas/basvurutmp.txt");
            f1.delete();
             logKaydet(this.hesaplariCek(arr[0]).get(hesapid).split(",")[3]+ " IBAN numaralı vadesiz TL hesaba " +hesap.split(",")[3]+"TL'lik " + hesap.split(",")[2] + " kredi başvurusu reddedildi.");
            this.musteriLogKaydet(hesap.split(",")[0], this.hesaplariCek(arr[0]).get(hesapid).split(",")[3]+ " IBAN numaralı vadesiz TL hesabınıza " +hesap.split(",")[3]+"TL'lik " + hesap.split(",")[2] + " kredi başvurunuz reddedildi.");
           
            return true;   
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean krediKartiOnayla(String hesap){
        try {
            String hesapId = hesap.split(",")[0];
            float para = Float.valueOf(hesap.split(",")[1]);
            Random rand = new Random();
            int min = 1000;
            int max = 9999;
            int x = (int) (Math.random() * (max - min + 1) + min);
            int y = (int) (Math.random() * (max - min + 1) + min);
            int z = (int) (Math.random() * (max - min + 1) + min);
            int w = (int) (Math.random() * (max - min + 1) + min);
            min = 1;
            max = 12;
            int sktAy =(int) (Math.random() * (max - min + 1) + min);
            min = 22;
            max = 29;
            int sktYil = (int) (Math.random() * (max - min + 1) + min);
            min = 100;
            max = 999;
            int cvc = (int) (Math.random() * (max - min + 1) + min);
            FileWriter fileWritter = new FileWriter("datas/hesaplar.txt",true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            String kartNo = String.valueOf(x) + " "+ String.valueOf(y) + " " + String.valueOf(z) + " "+ String.valueOf(w);
            String skt = String.valueOf(sktAy) + "/" + String.valueOf(sktYil);
            
            bw.write(hesapId + ",kredikarti," + kartNo + "," + skt + "," + cvc+","+String.valueOf(para) + ",0.0,"+String.valueOf(para) +"\n");
            bw.close();
            File inputFile = new File("datas/basvurular.txt");
            File tempFile = new File("datas/basvurutmp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String lineToRemove = hesapId + ",kredikarti," +para;
            //System.out.println(lineToRemove);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(new File("datas/basvurular.txt"));
            File f1 = new File("datas/basvurutmp.txt");
            f1.delete();
            //8000.0TL limitli Kredi kartı başvurunuz alınmıştır.
            logKaydet(hesapId+" ID' li müşteriye ait "+ para +"TL limitli kredi kartı başvurusu onaylandı.");
            this.musteriLogKaydet(hesapId, para+"TL limitli kredi kartı başvurunuz onaylanmıştır.");
            
           
            return true;   
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean krediKartiReddet(String hesap){
        try {
            
            File inputFile = new File("datas/basvurular.txt");
            File tempFile = new File("datas/basvurutmp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String arr[] = hesap.split(",");
            String lineToRemove = arr[0] + ",kredikarti," + arr[1];
            //System.out.println(lineToRemove);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(new File("datas/basvurular.txt"));
            File f1 = new File("datas/basvurutmp.txt");
            f1.delete();
            logKaydet(arr[0]+" ID' li müşteriye ait "+ arr[1] +"TL limitli kredi kartı başvurusu reddedildi.");
            this.musteriLogKaydet(arr[0], arr[1]+"TL limitli kredi kartı başvurunuz reddedilmiştir.");
            return true;   
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean transferOnayla(String gondericiIban, String aliciIban,Float para){
        
        try {
            String [] eskiGonderici = ",,,".split(",");
            String [] eskiAlici = ",,,".split(",");
            String [] yeniGonderici = ",,,".split(",");
            String [] yeniAlici = ",,,".split(",");
            File myObj = new File("datas/hesaplar.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[3].equals(gondericiIban) && bilgiler[1].equals("hesap")) {
                    eskiGonderici = data.split(",");
                    yeniGonderici = data.split(",");
                }
                if (bilgiler[3].equals(aliciIban) && bilgiler[1].equals("hesap")) {
                    eskiAlici = data.split(",");
                    yeniAlici = data.split(",");
                }
            }
            myReader.close();
            if(Float.valueOf(eskiGonderici[2]) >= para){
                yeniGonderici[2] = String.valueOf(Float.valueOf(yeniGonderici[2]) - para);
                yeniAlici[2] = String.valueOf(Float.valueOf(yeniAlici[2]) +para);
                List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/hesaplar.txt"), StandardCharsets.UTF_8));

                for (int i = 0; i < fileContent.size(); i++) {
                    if (fileContent.get(i).equals(String.join(",", eskiGonderici))) {
                        fileContent.set(i, String.join(",", yeniGonderici));
                        
                    }
                    if (fileContent.get(i).equals(String.join(",", eskiAlici))) {
                        fileContent.set(i, String.join(",", yeniAlici));
                        
                    }
                }

                Files.write(Paths.get("datas/hesaplar.txt"), fileContent, StandardCharsets.UTF_8);
                File inputFile = new File("datas/basvurular.txt");
                File tempFile = new File("datas/basvurutmp.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                
                String lineToRemove = gondericiIban + ",transfer," + aliciIban+","+para;
                ///(lineToRemove);
                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    // trim newline when comparing with lineToRemove
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.equals(lineToRemove)) {
                        continue;
                    }
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                inputFile.delete();
                tempFile.renameTo(new File("datas/basvurular.txt"));
                File f1 = new File("datas/basvurutmp.txt");
                f1.delete();
                //gondericiIban + " IBAN numaralı hesabınızdan " + aliciIban + " numaralı hesaba " + gonderilenBakiye + "TL para transferi başvurunuz alınmıştır.");
                logKaydet(gondericiIban+" IBAN numaralı hesaptan "+aliciIban + " IBAN numaralı hesaba "+ para +"TL para transferi başvurusu onaylandı.");
                this.musteriLogKaydet(this.ibanaAitHesabiCek(gondericiIban)[0], gondericiIban+" IBAN numaralı hesabınızdan "+aliciIban + " IBAN numaralı hesaba "+ para +"TL para transferi başvurunuz onaylanmıştır.");
                return true;
            }
            else{
                return false;
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean transferReddet(String hesap){
        try {
            
            File inputFile = new File("datas/basvurular.txt");
            File tempFile = new File("datas/basvurutmp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            String lineToRemove = hesap;
            //System.out.println(lineToRemove);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(new File("datas/basvurular.txt"));
            File f1 = new File("datas/basvurutmp.txt");
            f1.delete();
            logKaydet(hesap.split(",")[0]+" IBAN numaralı hesaptan "+ hesap.split(",")[1] + " IBAN numaralı hesaba "+ hesap.split(",")[2] +"TL para transferi başvurusu reddedildi.");
             this.musteriLogKaydet(this.ibanaAitHesabiCek(hesap.split(",")[0])[0], hesap.split(",")[0]+" IBAN numaralı hesabınızdan "+hesap.split(",")[1] + " IBAN numaralı hesaba "+ hesap.split(",")[2] +"TL para transferi başvurunuz reddedilmiştir.");
                
            return true;   
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean krediKartiLimitArtirimiOnayla(String musteriId, String kartNo,Float para){
        String eskiBilgiler[] = ",,,,".split(",");
        String yeniBilgiler[] = ",,,,".split(",");
        try {
            File myObj = new File("datas/hesaplar.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[2].equals(kartNo) && bilgiler[1].equals("kredikarti")) {
                    eskiBilgiler = data.split(",");
                    yeniBilgiler = data.split(",");
                    
                }
            }
            myReader.close();
            
            yeniBilgiler[5] = String.valueOf(para);
            yeniBilgiler[7] = String.valueOf(para-Float.valueOf(yeniBilgiler[6]));
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/hesaplar.txt"), StandardCharsets.UTF_8));

                for (int i = 0; i < fileContent.size(); i++) {
                    if (fileContent.get(i).equals(String.join(",", eskiBilgiler))) {
                        fileContent.set(i, String.join(",", yeniBilgiler));
                        
                    }
                }

            Files.write(Paths.get("datas/hesaplar.txt"), fileContent, StandardCharsets.UTF_8);
            File inputFile = new File("datas/basvurular.txt");
                File tempFile = new File("datas/basvurutmp.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                
                String lineToRemove = musteriId + ",limitartirimi,"+kartNo+","+para;
                //System.out.println(lineToRemove);
                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    // trim newline when comparing with lineToRemove
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.equals(lineToRemove)) {
                        continue;
                    }
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                inputFile.delete();
                tempFile.renameTo(new File("datas/basvurular.txt"));
                File f1 = new File("datas/basvurutmp.txt");
                f1.delete();
                //1234 5678 9333 4567 numaralı kredi kartınız için 8000.0TL limit başvurusu alınmıştır.
                logKaydet(kartNo + " numaralı kredi kartı için "+para+"TL limit başvurusu onaylandı");
                this.musteriLogKaydet(musteriId,kartNo + " numaralı kredi kartınız için "+para+"TL limit başvurusu onaylanmıştır.");
                
                return true;
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean krediKartiLimitArtirimiReddet(String hesap){
        try {
            
            File inputFile = new File("datas/basvurular.txt");
            File tempFile = new File("datas/basvurutmp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            String lineToRemove = hesap;
            //System.out.println(lineToRemove);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(new File("datas/basvurular.txt"));
            File f1 = new File("datas/basvurutmp.txt");
            f1.delete();
            logKaydet(hesap.split(",")[2] + " numaralı kredi kartı için "+hesap.split(",")[3]+"TL limit başvurusu reddedildi");
            this.musteriLogKaydet(hesap.split(",")[0],hesap.split(",")[2] + " numaralı kredi kartınız için "+hesap.split(",")[3]+"TL limit başvurusu reddedilmiştir.");
            return true;   
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean logKaydet(String log) {
        try {
            BufferedReader read = new BufferedReader(new FileReader("datas/bildirimler.txt"));
            ArrayList<String> list = new ArrayList<String>();

            String dataRow = read.readLine();
            while (dataRow != null) {
                list.add(dataRow);
                dataRow = read.readLine();
            }

            FileWriter writer = new FileWriter("datas/bildirimler.txt");
            writer.append(this.getLoginNo() + "," + log);

            for (int i = 0; i < list.size(); i++) {
                writer.append(System.getProperty("line.separator"));
                writer.append(list.get(i));
            }
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public ArrayList<Object[]> logCek(){
        ArrayList<Object[]> liste = new ArrayList<Object[]>();
        try {
            
            File myObj = new File("datas/bildirimler.txt");
            Scanner myReader = new Scanner(myObj);
            int index = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                
                if (bilgiler[0].equals(this.getLoginNo())) {
                    String detay = bilgiler[1];
                    
                    Object[] o1 = {index++,detay};
                    liste.add(o1);
                }
            }
            myReader.close();
            return liste;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
    @Override
    public void cikisYapMesaj(){
        JOptionPane.showMessageDialog(null,this.getAd() +" isimli Çalışan hesabınızdan çıkış yaptınız");
    }
}
