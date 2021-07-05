/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classlar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author asimk
 */
public class Personel extends Calisan{

    public Personel(String loginNo, String ad, String soyad, int yas, String dogumTarihi, String tcKimlik, String telefon, String cinsiyet, String mail, String password, String personType) {
        super(loginNo, ad, soyad, yas, dogumTarihi, tcKimlik, telefon, cinsiyet, mail, password, personType);
    }

    public Personel(String loginNo, String password, String personType) {
        super(loginNo, password, personType);
    }

    public Personel(String loginNo, String personType) {
        super(loginNo, personType);
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
            
            return liste;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
    @Override
    public void cikisYapMesaj(){
        JOptionPane.showMessageDialog(null,this.getAd() +" isimli Personel hesabınızdan çıkış yaptınız");
    }
    
}
