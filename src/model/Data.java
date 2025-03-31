/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;

/**
 *
 * @author josue
 */
public class Data {
    
    private LocalDate data;
    private String error;
    
    public Data(){
        
        this.data = LocalDate.now();
        this.error = "";
        
    }
    
    public Data(LocalDate data){
        
        this.data = data;
        this.error = "";
        
    }
    
    public Data(String data){
        
        String tema = "";
        
        int year = 0;
        int month = 0;
        int day = 0;
        
        try{
        
            if(data.matches("[0-9]{2}[/][0-9]{2}[/][0-9]{4}")){

                for(int i = 0; i < 10; i++){

                    int num;

                    switch(data.charAt(i)){

                        case '1':
                        num = 1;
                        break;

                        case '2':
                        num = 2;
                        break;

                        case '3':
                        num = 3;
                        break;

                        case '4':
                        num = 4;
                        break;

                        case '5':
                        num = 5;
                        break;

                        case '6':
                        num = 6;
                        break;

                        case '7':
                        num = 7;
                        break;

                        case '8':
                        num = 8;
                        break;

                        case '9':
                        num = 9;
                        break;

                        default:
                        num = 0;
                        break;

                    }//switch(data.charAt(i))

                    switch(i+1){

                        case 1:
                        day = num;
                        break;

                        case 2:
                        day = day*10 + num;
                        break;

                        case 4:
                        month = num;
                        break;

                        case 5:
                        month = month*10 + num;
                        break;

                        case 7:
                        year = num;
                        break;

                        case 8:
                        year = year*10 + num;
                        break;

                        case 9:
                        year = year*10 + num;
                        break;

                        case 10:
                        year = year*10 + num;
                        break;

                    }//switch(i+1)

                }//for(int i = 0; i < 10; i++)
                
                this.data = LocalDate.of(year, month, day);

            } else if(data.matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2}")){

                for(int i = 0; i < 10; i++){

                    int num;

                    switch(data.charAt(i)){

                        case '1':
                        num = 1;
                        break;

                        case '2':
                        num = 2;
                        break;

                        case '3':
                        num = 3;
                        break;

                        case '4':
                        num = 4;
                        break;

                        case '5':
                        num = 5;
                        break;

                        case '6':
                        num = 6;
                        break;

                        case '7':
                        num = 7;
                        break;

                        case '8':
                        num = 8;
                        break;

                        case '9':
                        num = 9;
                        break;

                        default:
                        num = 0;
                        break;

                    }//switch(data.charAt(i))

                    switch(i+1){

                        case 1:
                        year = num;
                        break;

                        case 2:
                        year = year*10 + num;
                        break;

                        case 3:
                        year = year*10 + num;
                        break;

                        case 4:
                        year = year*10 + num;
                        break;

                        case 6:
                        month = num;
                        break;

                        case 7:
                        month = month*10 + num;
                        break;

                        case 9:
                        day = num;
                        break;

                        case 10:
                        day = day*10 + num;
                        break;

                    }//switch(i+1)

                }//for(int i = 0; i < 10; i++)
                
                this.data = LocalDate.of(year, month, day);

            } else {
                
                this.data = LocalDate.now();
                tema = "Data inválida!";

            }//if
        
        } catch(Exception e){
            
            this.data = LocalDate.now();
            tema = e.getMessage();
            
        }
        
        this.error =  tema;
        
    }//public Data(String data)
    
    public String Error(){
        return this.error;
    }
    
    public boolean Val(){
        return this.error.isBlank();
    }
    
    public LocalDate getDate(){
        return this.data;
    }
    
    public String DataAbreviada(boolean week){
        
        String sep = "/";
        
        String txt = "";
        
        if(week){
            
            int s = this.data.getDayOfWeek().getValue();
            
            switch(s){
                
                case 1 ->{
                    txt += "Segunda";
                }
                
                case 2 ->{
                    txt += "Terça";
                }
                
                case 3 ->{
                    txt += "Quarta";
                }
                
                case 4 ->{
                    txt += "Quinta";
                }
                
                case 5 ->{
                    txt += "Sexta";
                }
                
                case 6 ->{
                    txt += "Sábado";
                }
                
                case 7 ->{
                    txt += "Domingo";
                }
                
            }//switch(s)
            
            txt += ", ";
            
        }//if(week)
        
        int a = this.data.getYear();
        int m = this.data.getMonthValue();
        int d = this.data.getDayOfMonth();
        
        if(d < 10){
            txt += "0";
        }
        
        txt += d;
        txt += sep;
        
        if(m < 10){
            txt += "0";
        }
        
        txt += m;
        txt += sep;
        txt += a;
        
        return txt;
        
    }//DataAbreviada(String sep)
    
    public String DataCompleta(boolean week){
        
        String txt = "";
        
        int a = this.data.getYear();
        int m = this.data.getMonthValue();
        int d = this.data.getDayOfMonth();
        
        if(week){
            
            int s = this.data.getDayOfWeek().getValue();

            switch(s){

                case 1 ->{
                    txt += "Segunda";
                }

                case 2 ->{
                    txt += "Terça";
                }

                case 3 ->{
                    txt += "Quarta";
                }

                case 4 ->{
                    txt += "Quinta";
                }

                case 5 ->{
                    txt += "Sexta";
                }

                case 6 ->{
                    txt += "Sábado";
                }

                case 7 ->{
                    txt += "Domingo";
                }

            }//switch(s)

            if(s < 6){
                txt += "-feira";
            }

            txt += ", dia ";
            
        }//if(semana)
        
        txt += d;
        
        if(d == 1){
            txt += "º";
        }
        
        txt += " de ";
        
        switch(m){
            
            case 1 ->{
                txt += "Janeiro";
            }
            
            case 2 ->{
                txt += "Fevereiro";
            }
            
            case 3 ->{
                txt += "Março";
            }
            
            case 4 ->{
                txt += "Abril";
            }
            
            case 5 ->{
                txt += "Maio";
            }
            
            case 6 ->{
                txt += "Junho";
            }
            
            case 7 ->{
                txt += "Julho";
            }
            
            case 8 ->{
                txt += "Agosto";
            }
            
            case 9 ->{
                txt += "Setembro";
            }
            
            case 10 ->{
                txt += "Outubro";
            }
            
            case 11 ->{
                txt += "Novembro";
            }
            
            case 12 ->{
                txt += "Dezembro";
            }
            
        }//switch(m)
        
        txt += " de ";
        txt += a;
        
        return txt;
        
    }//DataCompleta(boolean semana)
    
    public String DataLinha(boolean ifo){
        
        String txt = "";
        
        int dg = LocalDate.now().getDayOfYear();
        int dt = this.data.getDayOfYear();
        
        int ag = LocalDate.now().getYear();
        int at = this.data.getYear();
        
        if(ag - at == 1){//if
            
            if(ifo){
                txt += "ANO PASSADO";
            } else {
                txt += "ano passado";
            }
            
        } else if(ag > at){//if
            
            int year = ag - at;
            
            if(!ifo){
                txt += "há ";
            }
            
            txt += year;
            
            if(ifo){
                txt += " ANOS ATRÁS";
            } else {
                txt += " anos atrás";
            }
            
        } else if(dg == dt && ag >= at){//if
            
            if(ifo){
                txt += "HOJE";
            } else {
                txt += "hoje";
            }
            
        } else if(dg - dt == 1 && ag >= at){//if
            
            if(ifo){
                txt += "ONTEM";
            } else {
                txt += "ontem";
            }
            
        } else if(dg - dt > 30 && ag >= at){//if
            
            int mes = LocalDate.now().getMonthValue() - this.data.getMonthValue();
            
            if(mes == 0){
                
                if(ifo){
                    txt += "ESTE MÊS";
                } else {
                    txt += "este mês";
                }
                
            } else if(mes == 1){//if(mes == 0)
                
                if(ifo){
                    txt += "MÊS PASSADO";
                } else {
                    txt += "no mês passado";
                }
                
            } else {//if(mes == 0)
                
                if(!ifo){
                    txt += "há ";
                }
                
                txt += mes;
                
                if(ifo){
                    txt += " MESES ATRÁS";
                } else {
                    txt += " meses atrás";
                }
                
            }//if(mes == 0)
        
        } else if(dg > dt && ag >= at){//if
            
            int day = dg - dt;
            
            if(!ifo){
                txt += "há ";
            }
            
            txt += day;
            
            if(ifo){
                txt += " DIAS ATRÁS";
            } else {
                txt += " dias atrás";
            }
        
        } else if(ifo){//if
            
            txt += "NO FUTURO";
            
        } else {//if
            
            txt += "no futuro";
            
        }//if
        
        return txt;
        
    }//DataLinha(String start, String end)
    
    public String Load(){
        
        final char sep = '-';
        
        String txt = "";
        
        int a = this.data.getYear();
        int m = this.data.getMonthValue();
        int d = this.data.getDayOfMonth();
        
        txt += a;
        txt += sep;
        
        if(m < 10){
            txt += "0";
        }
        
        txt += m;
        txt += sep;
        
        if(d < 10){
            txt += "0";
        }
        
        txt += d;
        
        return txt;
        
    }
    
}