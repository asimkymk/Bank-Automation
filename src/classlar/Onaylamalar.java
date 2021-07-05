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
public interface Onaylamalar {
    public boolean krediOnayla(String hesap, float yatirilanPara);
    public boolean krediReddet(String hesap);
    public boolean krediKartiOnayla(String hesap);
    public boolean krediKartiReddet(String hesap);
    public boolean transferOnayla(String gondericiIban, String aliciIban,Float para);
    public boolean transferReddet(String hesap);
    public boolean krediKartiLimitArtirimiOnayla(String musteriId, String kartNo,Float para);
    public boolean krediKartiLimitArtirimiReddet(String hesap);
}
