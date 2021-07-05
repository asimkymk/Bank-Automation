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
import java.util.ArrayList;

public interface Hesaplar {

    public void hesapKontrol();
    public ArrayList<String> hesaplariCek();
    public void hesapOlustur();
    public ArrayList<String> krediBorcCek();
    public ArrayList<String> krediKartiCek();

}