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
public interface Basvurular {
     public boolean transferBasvuru(String gondericiIban, String aliciIban, float gonderilenBakiye);
     public void krediKartLimitArtirimiBasvur(float yeniMiktar, int index);
     public void krediBasvur(float miktar, String krediTur, int hesapId);
     public void krediKartiBasvur(float limit);
}
