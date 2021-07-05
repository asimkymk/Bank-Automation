/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classlar;

/**
 *
 * @author asimk
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Kullanici extends Iletisim {
    private String loginNo;
    private String ad;
    private String soyad;
    private int yas;
    private String dogumTarihi;
    private String tcKimlik;
    private String cinsiyet;
    private String password;
    private String personType;
    private File f = new File("datas");

    public Kullanici(String loginNo, String ad, String soyad, int yas, String dogumTarihi, String tcKimlik, String telefon, String cinsiyet, String mail, String password,String personType) {
        this.loginNo = loginNo;
        this.ad = ad;
        this.soyad = soyad;
        this.yas = yas;
        this.dogumTarihi = dogumTarihi;
        this.tcKimlik = tcKimlik;
        setMail(mail);
        setTelefon(telefon);
        this.cinsiyet = cinsiyet;
        this.password = password;
        this.personType = personType;
    }

    public Kullanici(String loginNo, String password, String personType) {
        this.loginNo = loginNo;
        this.password = password;
        this.personType = personType;
    }
    public Kullanici(String loginNo,String personType) {
        this.loginNo = loginNo;
        this.personType = personType;
        this.bilgileriDoldur();
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getTcKimlik() {
        return tcKimlik;
    }

    public void setTcKimlik(String tcKimlik) {
        this.tcKimlik = tcKimlik;
    }


    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private void DosyaKontrol(){
        if(!f.exists()){
            f.mkdirs();
        }
    }
    
    private void bilgileriDoldur(){
        try {
            File myObj = new File("datas/loginDatas.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String []bilgiler = data.split(",");
                if(bilgiler[2].equals(getPersonType())){
                    if(bilgiler[0].equals(getLoginNo())){
                        File myInf = new File("datas/"+getPersonType().toLowerCase()+"Infos.txt");
                        Scanner myInfReader = new Scanner(myInf);
                        while(myInfReader.hasNextLine()){
                            String infData = myInfReader.nextLine();
                            String []bilgilerInf = infData.split(",");
                            if(bilgilerInf[0].equals(this.loginNo)){
                                //System.out.println("ok");
                                setAd(bilgilerInf[1]);
                                setSoyad(bilgilerInf[2]);
                                setYas(Integer.parseInt(bilgilerInf[3]));
                                setDogumTarihi(bilgilerInf[4]);
                                setTcKimlik(bilgilerInf[5]);               
                                setTelefon(bilgilerInf[6]);
                                setCinsiyet(bilgilerInf[7]);
                                setMail(bilgilerInf[8]);
                                break;
                            }
                        }
                        
                    }
                }

            }
            myReader.close();
            
        } catch (FileNotFoundException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
        }
       
    }
    public void dataDosyasiOlustur(){
        DosyaKontrol();
        try{
            FileReader fr = new FileReader("datas/loginDatas.txt");

        }
        catch(FileNotFoundException e){
            try{
                FileWriter fw = new FileWriter("datas/loginDatas.txt");


            }
            catch(IOException ex){
                //System.out.println("Dosya1 olusturmada bir hata oldu");
                System.exit(1);
            }
        }
        try{

            FileReader dt = new FileReader("datas/"+this.personType.toLowerCase()+"Infos.txt");
        }
        catch(FileNotFoundException e){
            try{

                FileReader dt = new FileReader("datas/"+this.personType.toLowerCase()+"Infos.txt");

            }
            catch(IOException ex){
                //System.out.println("Dosya1 olusturmada bir hata oldu");
                System.exit(1);
            }
        }


    }
    public boolean KayitOl(){
        dataDosyasiOlustur();
        try {

            FileWriter fileWritter = new FileWriter("datas/loginDatas.txt",true);
            FileWriter infoWriter = new FileWriter("datas/"+getPersonType().toLowerCase()+"Infos.txt",true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            BufferedWriter iw = new BufferedWriter(infoWriter);
            boolean status = true;
            try {
                File myObj = new File("datas/loginDatas.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] bilgiler = data.split(",");
                    if (bilgiler[2].equals(getPersonType())) {
                        if (bilgiler[0].equals(getLoginNo()) && bilgiler[1].equals(getPassword())) {
                            status = false;
                            break;
                        }
                    }
                }
            }
            catch(IOException et){
                et.printStackTrace();
            }
            if(status){
                bw.write(getLoginNo() +","+getPassword()+","+getPersonType()+"\n");
                if(getPersonType().equals("Kullanıcı")){
                    iw.write(getLoginNo()+","+getAd() +","+getSoyad()+","+getYas()+"," + getDogumTarihi() +"," + getTcKimlik()+","+getTelefon()+","+getCinsiyet()+","+getMail()+"\n");
                }
                if(getPersonType().equals("Personel")){
                    iw.write(getLoginNo()+","+getAd() +","+getSoyad()+","+getYas()+"," + getDogumTarihi() +"," + getTcKimlik()+","+getTelefon()+","+getCinsiyet()+","+getMail()+","+5250.0f+"\n");
                }
                if(getPersonType().equals("Yönetici")){
                    iw.write(getLoginNo()+","+getAd() +","+getSoyad()+","+getYas()+"," + getDogumTarihi() +"," + getTcKimlik()+","+getTelefon()+","+getCinsiyet()+","+getMail()+","+9870.0f+"\n");
                }
                
                bw.close();
                iw.close();
                return true;
            }
            else return false;

        } catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean GirisYap() {

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
                            if(bilgilerInf[0].equals(this.loginNo)){
                                setAd(bilgilerInf[1]);
                                setSoyad(bilgilerInf[2]);
                                setYas(Integer.parseInt(bilgilerInf[3]));
                                setDogumTarihi(bilgilerInf[4]);
                                setTcKimlik(bilgilerInf[5]);               
                                setTelefon(bilgilerInf[6]);
                                setCinsiyet(bilgilerInf[7]);
                                setMail(bilgilerInf[8]);
                                
                                break;
                            }
                        }
                        myReader.close();
                        return true;
                    }
                }

            }
            myReader.close();
            return false;
        } catch (FileNotFoundException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return false;
    }
    public static String SifremiUnuttum(String mail,String personType){
        try {
            File myObj = new File("datas/"+personType.toLowerCase()+"Infos.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String []bilgiler = data.split(",");
                if(bilgiler[8].equals(mail)){

                    File myInf = new File("datas/loginDatas.txt");
                    Scanner myInfReader = new Scanner(myInf);
                    while(myInfReader.hasNextLine()) {
                        String infData = myInfReader.nextLine();
                        String[] bilgilerInf = infData.split(",");
                        if (bilgilerInf[0].equals(bilgiler[0])) {
                            myInfReader.close();
                            return bilgilerInf[1];
                        }
                    }
                    myInfReader.close();
                        return "";
                }


            }
            myReader.close();
            return "";
        } catch (FileNotFoundException e) {
           // System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "";
    }
     public boolean sifreGuncelle(String eski, String yeni) {
        if (eski.equals(this.getPassword())) {
            try {
                String eskiSatir = this.getLoginNo() + "," + this.getPassword() + "," + getPersonType();
                String yenihesapBilgileri = this.getLoginNo() + "," + yeni + ","+ getPersonType();
                List < String > fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/loginDatas.txt"), StandardCharsets.UTF_8));
                
                for (int i = 0; i < fileContent.size(); i++) {
                    if (fileContent.get(i).equals(eskiSatir)) {
                        fileContent.set(i, yenihesapBilgileri);
                        break;
                    }
                }
                
                Files.write(Paths.get("datas/loginDatas.txt"), fileContent, StandardCharsets.UTF_8);
                this.setPassword(yeni);
                
                return true;
            } catch (IOException ex) {
                return false;
            }
        }
        else return false;
        

    }
    
    public boolean bilgilerimiGuncelle(String isim,String tel,String mail,String TCKN ) {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/"+this.getPersonType().toLowerCase()+"Infos.txt"), StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).split(",")[1].equals(this.getAd()) && fileContent.get(i).split(",")[6].equals(this.getTelefon()) && fileContent.get(i).split(",")[8].equals(this.getMail()) && fileContent.get(i).split(",")[5].equals(this.getTcKimlik())) {
                    String detaylar[] = fileContent.get(i).split(",");
                    detaylar[1] = isim;
                    detaylar[6] = tel;
                    detaylar[8] = mail;
                    detaylar[5] = TCKN;
                    fileContent.set(i, String.join(",", detaylar));
                    break;
                }
            }

            Files.write(Paths.get("datas/hesaplar.txt"), fileContent, StandardCharsets.UTF_8);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void cikisYapMesaj(){
        JOptionPane.showMessageDialog(null,this.getAd() +" isimli Kullanıcı hesabınızdan çıkış yaptınız");
    }
}