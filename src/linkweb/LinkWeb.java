/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package linkweb;

import model.Data;
import form.Index;
import java.time.LocalDate;

/**
 *
 * @author josue
 */
public class LinkWeb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Data upgrade = new Data(LocalDate.of(2025, 4, 9));
            
        System.out.println("Projeto:\t\tExportCSV");
        System.out.print("Última atualização:\t");
        System.out.println(upgrade.DataLinha(true));
        System.out.println();
        
        String set_title = "Projeto atualizado ";
        set_title += upgrade.DataLinha(false);
        set_title += "!";
        
        new Index().Enter(set_title);
        
    }
    
}
