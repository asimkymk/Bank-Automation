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
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author asimk
 */
public class Yonetici extends Calisan{

    public Yonetici(String loginNo, String ad, String soyad, int yas, String dogumTarihi, String tcKimlik, String telefon, String cinsiyet, String mail, String password, String personType) {
        super(loginNo, ad, soyad, yas, dogumTarihi, tcKimlik, telefon, cinsiyet, mail, password, personType);
    }
    
    public Yonetici(String loginNo, String password, String personType) {
        super(loginNo, password, personType);
    }
    public boolean maasAyarla(String id,Float maas,String pt){
        try {
            String eskiler[] = ",,,,".split(",");
            String yeniler[] = ",,,,".split(",");
            File myObj = new File("datas/loginDatas.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String []bilgiler = data.split(",");
                
                if (bilgiler[0].equals(id)) {
                    File myInf = new File("datas/" + pt.toLowerCase() + "Infos.txt");
                    Scanner myInfReader = new Scanner(myInf);
                    while (myInfReader.hasNextLine()) {
                        String infData = myInfReader.nextLine();
                        
                        String kk[] = infData.split(",");
                        if (kk[0].equals(id)) {
                            eskiler = infData.split(",");

                            yeniler = infData.split(",");
                            myInfReader.close();
                            break;
                            
                        }

                    }
                    break;
                }

            }
            
            
            yeniler[9] = String.valueOf(maas);
            //System.out.println(String.join(",",eskiler));
            //System.out.println(String.join(",",yeniler));
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/"+pt.toLowerCase()+"Infos.txt"), StandardCharsets.UTF_8));
            
            
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(String.join(",",eskiler))) {
                    fileContent.set(i, String.join(",", yeniler));
                    break;
                }
            }

            Files.write(Paths.get("datas/"+pt.toLowerCase()+"Infos.txt"), fileContent, StandardCharsets.UTF_8);
            myReader.close();
            setMaas(maas);
            return true;
        } catch (IOException e) {
            
            e.printStackTrace();
            return false;
        }
        
    }
    @Override
    ArrayList<Object[]> hesapListele() {
       ArrayList<Object[]> liste = new ArrayList<Object[]>();
        try {
            
            File myObj = new File("datas/kullanıcıInfos.txt");
            Scanner myReader = new Scanner(myObj);
            int index = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                
                String loginNo = bilgiler[0];
                
                Object[] o1 = {index++, loginNo,"Müşteri"};
                liste.add(o1);
                
            }
            myReader.close();
            File myObj1 = new File("datas/personelInfos.txt");
            Scanner myReader1 = new Scanner(myObj1);
            
            while (myReader1.hasNextLine()) {
                String data1 = myReader1.nextLine();
                String[] bilgiler1 = data1.split(",");
                
                String loginNo1 = bilgiler1[0];
                
                Object[] o1 = {index++, loginNo1,"Personel"};
                liste.add(o1);
                
            }
            myReader1.close();
            return liste;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
    public ArrayList<Object[]> calisanListele() {
       ArrayList<Object[]> liste = new ArrayList<Object[]>();
        try {
            
            File myObj = new File("datas/yöneticiInfos.txt");
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                
                String loginNo = bilgiler[0];
                String isim = bilgiler[1] + " "+ bilgiler[2];
                Object[] o1 = {loginNo,isim,"Yönetici",bilgiler[9]};
                liste.add(o1);
                
            }
            myReader.close();
            File myObj1 = new File("datas/personelInfos.txt");
            Scanner myReader1 = new Scanner(myObj1);
            
            while (myReader1.hasNextLine()) {
                String data1 = myReader1.nextLine();
                String[] bilgiler1 = data1.split(",");
                
                String loginNo1 = bilgiler1[0];
                String isim1 = bilgiler1[1] + " "+bilgiler1[2];
                Object[] o1 = { loginNo1,isim1,"Personel",bilgiler1[9]};
                liste.add(o1);
                
            }
            myReader1.close();
            return liste;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
    
    public boolean istenCikar(String id,String pt){
        try{
            File inputFile = new File("datas/loginDatas.txt");
            File tempFile = new File("datas/loginDatastmp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.split(",")[0].equals(id)) {
                    //System.out.println(id);
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(new File("datas/loginDatas.txt"));
            File tempFile12 = new File("datas/loginDatastmp.txt");
            tempFile12.delete();
            
            
            
            
            File inputFile1 = new File("datas/" + pt.toLowerCase() + "Infos.txt");
            File tempFile1 = new File("datas/" + pt.toLowerCase() + "Infostmp.txt");

            BufferedReader reader1 = new BufferedReader(new FileReader(inputFile1));
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(tempFile1));

            
            String currentLine1;

            while ((currentLine1 = reader1.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine1 = currentLine1.trim();
                if (trimmedLine1.split(",")[0].equals(id)) {
                    continue;
                }
                writer1.write(currentLine1 + System.getProperty("line.separator"));
            }
            writer1.close();
            reader1.close();
            inputFile1.delete();
            tempFile1.renameTo(new File("datas/" + pt.toLowerCase() + "Infos.txt"));
            File tempFile11 = new File("datas/" + pt.toLowerCase() + "Infostmp.txt");
            tempFile11.delete();
            return true;
            
        }
        catch(IOException e){
            return false;
        }
        
    }
    
    public static boolean kullaniciDuzenle(Kullanici p1,String isim,String tel,String mail,String TCKN){
        String type = p1.getPersonType();
        //System.out.println(type);
        //System.out.println(p1.getAd());
        //System.out.println(p1.getMail());
        //System.out.println(p1.getTcKimlik());
        //System.out.println(p1.getTelefon());
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/"+type.toLowerCase()+"Infos.txt"), StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).split(",")[1].equals(p1.getAd()) && fileContent.get(i).split(",")[6].equals(p1.getTelefon()) && fileContent.get(i).split(",")[8].equals(p1.getMail()) && fileContent.get(i).split(",")[5].equals(p1.getTcKimlik())) {
                    String detaylar[] = fileContent.get(i).split(",");
                    //System.out.println("okkkkkkkkkkk");
                    detaylar[1] = isim;
                    detaylar[6] = tel;
                    detaylar[8] = mail;
                    detaylar[5] = TCKN;
                    fileContent.set(i, String.join(",", detaylar));
                    break;
                }
            }

            Files.write(Paths.get("datas/"+type.toLowerCase()+"Infos.txt"), fileContent, StandardCharsets.UTF_8);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public void cikisYapMesaj(){
        JOptionPane.showMessageDialog(null,this.getAd() +" isimli Yönetici hesabınızdan çıkış yaptınız");
    }
    
}
