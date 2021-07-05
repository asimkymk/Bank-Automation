/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classlar;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author asimk
 */
public class Musteri extends Kullanici implements Hesaplar,Loglar,Basvurular {

    public Musteri(String loginNo, String ad, String soyad, int yas, String dogumTarihi, String tcKimlik, String telefon, String cinsiyet, String mail, String password, String personType) {
        super(loginNo, ad, soyad, yas, dogumTarihi, tcKimlik, telefon, cinsiyet, mail, password, personType);
    }

    public Musteri(String loginNo, String password, String personType) {
        super(loginNo, password, personType);
    }

    public Musteri(String loginNo, String personType) {
        super(loginNo, personType);
    }
    

    @Override
    public void hesapKontrol() {

        try {
            FileReader fr = new FileReader("datas/hesaplar.txt");

        } catch (FileNotFoundException e) {
            try {
                FileWriter fw = new FileWriter("datas/hesaplar.txt");

            } catch (IOException ex) {
                //System.out.println("Dosya olusturmada bir hata oldu");
                System.exit(1);
            }
        }
        try {
            FileReader fr = new FileReader("datas/basvurular.txt");

        } catch (FileNotFoundException e) {
            try {
                FileWriter fw = new FileWriter("datas/basvurular.txt");

            } catch (IOException ex) {
                //System.out.println("Dosya olusturmada bir hata oldu");
                System.exit(1);
            }
        }
    }

    @Override
    public ArrayList<String> hesaplariCek() {
        ArrayList<String> liste = new ArrayList<String>();
        hesapKontrol();
        try {

            File myObj = new File("datas/hesaplar.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[0].equals(getLoginNo()) && bilgiler[1].equals("hesap")) {
                    liste.add(data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public void hesapOlustur() {
        hesapKontrol();
        try {

            FileWriter fileWritter = new FileWriter("datas/hesaplar.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            Random rand = new Random();
            int min = 10;
            int max = 99;
            int x = (int) (Math.random() * (max - min + 1) + min);
            min = 1000;
            max = 9999;
            int y = (int) (Math.random() * (max - min + 1) + min);
            min = 10;
            max = 99;
            int z = (int) (Math.random() * (max - min + 1) + min);
            String iban = "TR53 0001 0001 69" + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z) + "50 01";
            bw.write(getLoginNo() + ",hesap," + "0" + "," + iban + "\n");
            bw.close();
            logKaydet(iban + " IBAN numaralı vadesiz TL hesabınız oluşturuldu.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void krediBasvur(float miktar, String krediTur, int hesapId) {
        hesapKontrol();
        try {

            FileWriter fileWritter = new FileWriter("datas/basvurular.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(getLoginNo() + ",kredi," + krediTur + "," + miktar + "," + hesapId + "\n");
            bw.close();
            logKaydet(hesaplariCek().get(hesapId).split(",")[3] + " IBAN numaralı vadesiz TL hesabınıza " + miktar + "TL' lik " + krediTur + " kredi başvurunuz alınmıştır.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void krediKartiBasvur(float limit) {
        hesapKontrol();
        try {

            FileWriter fileWritter = new FileWriter("datas/basvurular.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(getLoginNo() + ",kredikarti," + limit + "\n");
            bw.close();
            logKaydet(limit + "TL limitli Kredi kartı başvurunuz alınmıştır.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> krediBorcCek() {
        ArrayList<String> liste = new ArrayList<String>();
        hesapKontrol();
        try {

            File myObj = new File("datas/krediBorclari.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[0].equals(getLoginNo())) {
                    liste.add(data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<String> krediKartiCek() {
        ArrayList<String> liste = new ArrayList<String>();
        hesapKontrol();
        try {

            File myObj = new File("datas/hesaplar.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] bilgiler = data.split(",");
                if (bilgiler[0].equals(getLoginNo()) && bilgiler[1].equals("kredikarti")) {
                    liste.add(data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public boolean paraYatir(String eskihesapBilgileri, float yatirilanPara) {
        try {

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
            logKaydet(eskihesapBilgileri.split(",")[3] + " IBAN numaralı hesabınıza " + yatirilanPara + "TL para yatırılmıştır.");
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean paraCek(String eskihesapBilgileri, float harcananPara) {
        try {
            float hesaptakiPara = Float.valueOf(eskihesapBilgileri.split(",")[2]);
            String tp[] = eskihesapBilgileri.split(",");
            harcananPara = hesaptakiPara - harcananPara;
            tp[2] = String.valueOf(harcananPara);
            String yenihesapBilgileri = String.join(",", tp);
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/hesaplar.txt"), StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(eskihesapBilgileri)) {
                    fileContent.set(i, yenihesapBilgileri);
                    break;
                }
            }

            Files.write(Paths.get("datas/hesaplar.txt"), fileContent, StandardCharsets.UTF_8);
            logKaydet(eskihesapBilgileri.split(",")[3] + " IBAN numaralı hesabınızdan " + harcananPara + "TL para çekilmiştir.");
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean krediKartiBorcOde(String eskiSatir, float OdenenBorc) {
        try {

            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/hesaplar.txt"), StandardCharsets.UTF_8));
            String bilgiler[] = eskiSatir.split(",");
            bilgiler[6] = String.valueOf(Float.valueOf(bilgiler[6]) - OdenenBorc);
            bilgiler[7] = String.valueOf(Float.valueOf(bilgiler[7]) + OdenenBorc);
            String yenihesapBilgileri = String.join(",", bilgiler);
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(eskiSatir)) {
                    fileContent.set(i, yenihesapBilgileri);
                    break;
                }
            }

            Files.write(Paths.get("datas/hesaplar.txt"), fileContent, StandardCharsets.UTF_8);
            logKaydet(eskiSatir.split(",")[2] + " numaralı kredi kartınızın " + OdenenBorc + "TL borç ödenmiştir.");
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean krediKarttanHarca(String eskiSatir, float harcananMiktar) {
        try {

            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/hesaplar.txt"), StandardCharsets.UTF_8));
            String bilgiler[] = eskiSatir.split(",");
            bilgiler[6] = String.valueOf(Float.valueOf(bilgiler[6]) + harcananMiktar);
            bilgiler[7] = String.valueOf(Float.valueOf(bilgiler[7]) - harcananMiktar);
            String yenihesapBilgileri = String.join(",", bilgiler);
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(eskiSatir)) {
                    fileContent.set(i, yenihesapBilgileri);
                    break;
                }
            }

            Files.write(Paths.get("datas/hesaplar.txt"), fileContent, StandardCharsets.UTF_8);
            logKaydet(eskiSatir.split(",")[2] + " numaralı kredi kartınızdan " + harcananMiktar + "TL para harcanmıştır.");
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    @Override
    public void krediKartLimitArtirimiBasvur(float yeniMiktar, int index) {
        hesapKontrol();
        try {

            FileWriter fileWritter = new FileWriter("datas/basvurular.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(getLoginNo() + ",limitartirimi," + this.krediKartiCek().get(index).split(",")[2] + "," + yeniMiktar + "\n");

            bw.close();
            logKaydet(this.krediKartiCek().get(index).split(",")[2] + " numaralı kredi kartınız için " + yeniMiktar + "TL limit başvurusu alınmıştır.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float toplamBakiyeyiCek() {
        float toplam = 0;
        for (int i = 0; i < this.hesaplariCek().size(); i++) {
            String tmp[] = hesaplariCek().get(i).split(",");
            toplam += Float.valueOf(tmp[2]);

        }
        return toplam;
    }

    public boolean krediBorcOde(String silinecekBorc) {
        try {
            File file = new File("datas/krediBorclari.txt");
            List<String> out = Files.lines(file.toPath())
                    .filter(line -> !line.contains(silinecekBorc))
                    .collect(Collectors.toList());
            Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            logKaydet(silinecekBorc.split(",")[1] + " kredi borcunuz tamamen ödenmiştir (" + silinecekBorc.split(",")[3]);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public boolean krediBorcOde(String eskiSatir, float harcananMiktar) {
        try {

            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("datas/krediBorclari.txt"), StandardCharsets.UTF_8));
            String bilgiler[] = eskiSatir.split(",");
            bilgiler[3] = String.valueOf(Float.valueOf(bilgiler[3]) - harcananMiktar);
            String yenihesapBilgileri = String.join(",", bilgiler);
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(eskiSatir)) {
                    fileContent.set(i, yenihesapBilgileri);
                    break;
                }
            }

            Files.write(Paths.get("datas/krediBorclari.txt"), fileContent, StandardCharsets.UTF_8);
            logKaydet(eskiSatir.split(",")[1] + " kredi borcunuzun " + harcananMiktar + "TL'lik kısım borcu ödenmiştir.");
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    //
    @Override
    public boolean transferBasvuru(String gondericiIban, String aliciIban, float gonderilenBakiye) {
        hesapKontrol();
        try {

            FileWriter fileWritter = new FileWriter("datas/basvurular.txt", true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(gondericiIban + ",transfer," + aliciIban + "," + gonderilenBakiye + "\n");
            bw.close();
            logKaydet(gondericiIban + " IBAN numaralı hesabınızdan " + aliciIban + " numaralı hesaba " + gonderilenBakiye + "TL para transferi başvurunuz alınmıştır.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
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
        JOptionPane.showMessageDialog(null,this.getAd() +" isimli Müşteri hesabınızdan çıkış yaptınız");
    }
   
   

}
