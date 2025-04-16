/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package linkweb;

import model.Data;
import form.Index;
import java.time.LocalDate;
import model.Registro;

/**
 *
 * @author josue
 */
public class LinkWeb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        System.out.print("Projeto:");
        Registro.Tab("Projeto:",27);
        System.out.println("ExportCSV");
        System.out.print("Última atualização:");
        Registro.Tab("Última atualização:",27);
        System.out.println(Registro.upgrade.DataLinha(true));
        System.out.println();
        
        String set_title = "Projeto atualizado ";
        set_title += Registro.upgrade.DataLinha(false);
        set_title += "!";
        
        new Index().Enter(set_title);
        
    }
    
}
