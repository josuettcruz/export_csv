/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author josue
 */
public class Numero {
    
    private int num;
    private boolean valid;
    
    public Numero(String txt){
        
        int numb = 0;
        this.valid = true;
        
        for(int i = 0; i < txt.length(); i ++){
            
            switch(txt.charAt(i)){
                
                case '1':
                numb = numb*10 + 1;
                break;
                
                case '2':
                numb = numb*10 + 2;
                break;
                
                case '3':
                numb = numb*10 + 3;
                break;
                
                case '4':
                numb = numb*10 + 4;
                break;
                
                case '5':
                numb = numb*10 + 5;
                break;
                
                case '6':
                numb = numb*10 + 6;
                break;
                
                case '7':
                numb = numb*10 + 7;
                break;
                
                case '8':
                numb = numb*10 + 8;
                break;
                
                case '9':
                numb = numb*10 + 9;
                break;
                
                case '0':
                numb = numb*10;
                break;
                
                default:
                this.valid = false;
                
            }//switch(txt.charAt(i))
            
        }//for(int i = 0; i < txt.length(); i ++)
        
        this.num = numb;
        
    }//Numero(String txt)
    
    public int Num(){
        return this.num;
    }
    
    public boolean Val(){
        return this.valid;
    }
    
}