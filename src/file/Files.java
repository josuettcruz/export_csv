/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author josue
 */
public class Files {
    
    private String opner;
    
    private ArrayList<String> text = new ArrayList<>();
    
    private ArrayList<String> tema = new ArrayList<>();
    
    public Files(String arq){
        
        this.opner = arq;
        
        try{
        
            File menu = new File(this.opner);

            if(menu.createNewFile()){

                System.out.println("Arquivo: \"" + arq + "\" criado!");

            }/* else {

                System.out.println("\"" + arq + "\"");

            }/*if(menu.createNewFile())*/

            Scanner reading = new Scanner(menu);

            while (reading.hasNextLine()){

                String data = reading.nextLine();

                this.text.add(data);

            }
        
        }catch(IOException evt){
            
            System.out.println(evt);
            
        }
        
    }//Files
    
    public void Write(String insert){
        
        text.clear();
        
        try{
        
            File menu = new File(opner);

            Scanner reading = new Scanner(menu);

            while (reading.hasNextLine()){

                text.add(reading.nextLine());

            }

            try (FileWriter dir = new FileWriter(opner)) {
                if(!text.isEmpty()){
                    
                    for(int i = 0; i < text.size(); i++){
                        
                        dir.write(text.get(i) + "\n");
                        
                    }//for(int i = 0; i < text.size(); i++)
                    
                }//if(!text.isEmpty())
                
                dir.write(insert);
            }

            this.text.add(insert);
        
        }catch(IOException evt){
            
            System.out.println(evt);
            
        }
        
        this.tema.clear();
        
    }//Write(String insert)
    
    public void WriteAll(List<String> insert){
        
        this.text.clear();
        this.tema.clear();
        
        try{
        
            File menu = new File(opner);

            Scanner reading = new Scanner(menu);

            while (reading.hasNextLine()){

                this.text.add(reading.nextLine());

            }

            try (FileWriter dir = new FileWriter(opner)) {
                
                if(!this.text.isEmpty()){
                    
                    for(int i = 0; i < this.text.size(); i++){
                        
                        dir.write(this.text.get(i) + "\n");
                        
                    }//for(int i = 0; i < text.size(); i++)
                    
                }//if(!text.isEmpty())
                
                for(int x = 0; x < insert.size(); x++) {
                    
                    this.text.add(insert.get(x));
                    
                    if(x > 0){
                        
                        dir.write("\n");
                        
                    }//if(x < insert.length-1)
                    
                    dir.write(insert.get(x));
                    
                }//(String e : insert)
                
            }
        
        }catch(IOException evt){
            
            System.out.println(evt);
            
        }
        
    }//WriteAll(List<String> insert)
    
    public void WriteAll(String[] insert){
        
        this.text.clear();
        this.tema.clear();
        
        try{
        
            File menu = new File(opner);

            Scanner reading = new Scanner(menu);

            while (reading.hasNextLine()){

                this.text.add(reading.nextLine());

            }

            try (FileWriter dir = new FileWriter(opner)) {
                
                if(!this.text.isEmpty()){
                    
                    for(int i = 0; i < this.text.size(); i++){
                        
                        dir.write(this.text.get(i) + "\n");
                        
                    }//for(int i = 0; i < text.size(); i++)
                    
                }//if(!text.isEmpty())
                
                for(int x = 0; x < insert.length; x++) {
                    
                    this.text.add(insert[x]);
                    
                    if(x > 0){
                        
                        dir.write("\n");
                        
                    }//if(x < insert.length-1)
                    
                    dir.write(insert[x]);
                    
                }//(String e : insert)
                
            }
        
        }catch(IOException evt){
            
            System.out.println(evt);
            
        }
        
    }//WriteAll(String[] insert)
    
    public void Delete(int op){
        
        try{
        
            text.clear();
            tema.clear();

            int i = 0;
            int x = 0;

            File menu = new File(opner);

            Scanner reading = new Scanner(menu);

            while (reading.hasNextLine()){

                tema.add(reading.nextLine());

            }//while (reading.hasNextLine())

            FileWriter dir = new FileWriter(opner);

            do{

                if(x != op){

                    if(i > 0){

                        dir.write("\n");

                    }

                    dir.write(tema.get(x));

                    text.add(tema.get(x));

                    i++;
                }

                x++;

            }while(x < tema.size());

            dir.close();
            tema.clear();
        
        }catch(IOException evt){
            
            System.out.println(evt);
            
        }
        
    }//public void Delete(int op)
    
    public void Modify(int op, String txt){
        
        try{
        
            text.clear();
            tema.clear();

            //int i = 0;
            int x = 0;

            File menu = new File(opner);

            Scanner reading = new Scanner(menu);

            while (reading.hasNextLine()){

                tema.add(reading.nextLine());

            }//while (reading.hasNextLine())

            FileWriter dir = new FileWriter(opner);

            do{
                
                if(x > 0){

                    dir.write("\n");

                }
                
                if(x == op){
                    
                    dir.write(txt);
                    text.add(txt);
                    
                } else {

                    dir.write(tema.get(x));
                    text.add(tema.get(x));
                    
                }

                x++;

            }while(x < tema.size());

            dir.close();
            tema.clear();
        
        }catch(IOException evt){
            
            System.out.println(evt);
            
        }
        
    }//public void Delete(int op)
    
    public int Max(){
        
        if(text.isEmpty()){
            
            return -1;
            
        } else {
            
            return text.size();
            
        }
        
    }//Max()
    
    public String getLine(int j){
        
        String line = "invalid";
        
        if(j >= 0 && j < this.Max()){
            
            line = this.text.get(j);
            
        }//if(j >= 0 && j < this.Max())
        
        return line;
        
    }//String getLine(int j)
    
    public void Clear(){
        
        try{
        
            text.clear();

            FileWriter dir = new FileWriter(opner);

            dir.write("");
        
        }catch(IOException evt){
            
            System.out.println(evt);
            
        }
        
    }//Clear()
    
}//Class
