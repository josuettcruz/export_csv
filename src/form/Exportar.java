/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

import file.*;
import java.util.ArrayList;
import java.util.List;
import model.Data;
import model.Hora;
import model.Numero;

public class Exportar {
    
    private csv code;
    
    private final int max_end_separator_paragraphy = 100;
    
    private final String ext[] = {
            "avi",
            "mpg",
            "mp4",
            "mov"
        };
    
    private String host;
    
    private boolean tag;
    private boolean meta;
    private boolean aspas;
    private final String h1 = "tema";
    private final String p = "texto";
    
    public Exportar(csv code, String sent){
        
        this.code = code;
        
        this.host = sent;
        
        this.tag = false;
        this.meta = false;
        this.aspas = false;
        
    }//Exportar(csv code, String sent)
    
    private String Reverse(String text){
        
        boolean span = true;
        
        List<String> rew = new ArrayList<>();
        
        for(int r = text.length()-1; r >= 0; r--){
            
            char ds = text.charAt(r);
            
            switch(ds){
                
                case'>' ->{
                    
                    if(span){
                        
                        rew.add("</span>");
                        span = false;
                        
                    } else {//if(span)
                        
                        rew.add(">");
                        
                    }//if(span)
                    
                }//case'>'
                
                default ->{
                    
                    rew.add(ds + "");
                    
                }//default
                
            }//switch(ds)
            
        }//for(int r = text.length()-1; r >= 0; r--)
        
        String txt = "";
        
        for(int letter = rew.size()-1; letter >= 0; letter--){
            
            txt += rew.get(letter);
            
        }//for(int letter = rew.size()-1; letter >= 0; letter--)
        
        return txt;
        
    }//Reverse(String text)
    
    private String Tag(String dig, int note){
        
        boolean space = true;
        boolean reverse = false;
        
        String txt = "";
        String node = "";
        
        for(int x = 0; x < dig.length(); x++){
            
            char ds = dig.charAt(x);
            
            switch(ds){
                
                case'<' ->{
                    
                    if(this.meta){
                        
                        txt += "<";
                        node += "<";
                        
                    } else {
                        
                        txt += "<span>";
                        node += "<span>";
                        this.meta = true;
                        
                    }
                    
                }//case'<'
                
                case'>' ->{
                    
                    node += ">";
                    
                    if(this.meta){
                        
                        txt += "</span>";
                        
                        this.meta = false;
                        
                    } else {
                        
                        reverse = true;
                        
                    }
                    
                }//case'>'
                
                case'\\' ->{
                    
                    if(this.tag){
                        
                        txt += "\\";
                        node += "\\";
                        
                    } else if(space && note > 0){//if(this.meta)
                        
                        txt += "<br/>";
                        node += "<br/>";
                        
                        space = false;
                        
                    }//if(this.meta)
                    
                }//case'\\'
                
                default ->{
                    
                    txt += ds;
                    node += ds;
                    
                }//default
                
            }//switch(ds)
            
        }//for(int x = 0; x < dig.length(); x++)
        
        return reverse ? Reverse(node) : txt;
        
    }//Tag(String dig)
    
    private String phrase(String dig, int note){
        
        boolean space = true;
        
        String txt = "";
        
        for(int t = 0; t < dig.length(); t++){
            
            char ds = dig.charAt(t);
            
            switch(ds){
                
                case'\\' ->{
                    
                    if(this.tag){
                        
                        txt += "\\";
                        
                    } else if(space && note > 0){//if(this.meta)
                        
                        txt += "<br/>";
                        
                        space = false;
                        
                    }//if(this.meta)
                    
                }//case'\\'
                
                case'\"' ->{
                    
                    if(this.aspas){
                        
                        txt += "</q>";
                        this.aspas = false;
                    } else {
                        txt += "<q>";
                        this.aspas = true;
                    }
                    
                }//case'\\'
                
                default ->{
                    txt += ds;
                    space = true;
                }
                
            }//switch(ds)
            
        }//for(int t = 0; t < dig.length(); t++)
        
        return txt;
        
    }//phrase(String dig)
    
    private boolean Tx(String text){
        
        int node = 0;
        
        for(int d = 0; d < text.length(); d++){
            
            char ds = text.charAt(d);
            
            switch(ds){
                
                case ')':
                case ']':
                case '}':
                node = 1;
                break;
                
                default:
                if(node == 1){
                    node = 2;
                } else {
                    node = 0;
                }
                break;
                
            }//switch(ds)
            
        }//for(int d = 0; d < text.length(); d++)
        
        return node > 0;
        
    }//Tx(String text)
    
    private String T(String text){
        
        String txt = "";
        
        int col = 0;
        
        String live_text[] = text.split(" ");
        
        List<String> dig = new ArrayList<>();
        
        for(String insert : live_text){
            
            if(!insert.isBlank()){
                dig.add(insert);
            }
            
        }//for(String insert : live_text)
        
        for(String tx : dig){
            
            Data d = new Data(tx);
            Hora h = new Hora(tx);
            
            boolean into_1 = tx.charAt(0) == '(';
            boolean into_2 = tx.charAt(0) == '[';
            boolean into_3 = tx.charAt(0) == '{';
            boolean into = into_1 || into_2 || into_3;
            
            int end_num = tx.length() > 1 ? tx.length()-1 : 0;
            boolean end_1 = tx.charAt(end_num) == '.';
            boolean end_2 = tx.charAt(end_num) == '!';
            boolean end_3 = tx.charAt(end_num) == '?';
            boolean end = end_1 || end_2 || end_3;
            
            String node = "";
            
            switch(col){
                
                case 1 ->{
                    node = " ";
                }
                
                case 2 ->{
                    node = "<br/>";
                }
                
                case 3 ->{
                    node = " - ";
                }
                
            }//switch(col)
            
            if(tx.contains("<") || tx.contains(">")){//if
                
                this.tag = true;
                
                col = 1;
                
                txt += node;
                
                txt += Tag(tx, col);
                
            } else if(tx.equalsIgnoreCase("|")){//if
                
                col = 2;
                
            } else if(tx.equalsIgnoreCase("-")){//if
                
                col = 3;
                
            } else if(d.Val()){//if
                
                if(col > 0){txt += "<br/>";}
                
                col = 2;
                
                txt += d.DataCompleta(true);
                
            } else if(h.Val()){//if
                
                if(col > 0){txt += "<br/>";}
                
                col = 2;
                
                txt += h.getNodeHora(true);
                
            } else if(into && Tx(tx)){//if
                
                if(col > 0){txt += "<br/>";}
                
                col = 2;
                
                txt += phrase(tx, col);
                
            } else if(into){//if
                
                if(col > 0){txt += "<br/>";}
                
                col = 1;
                
                txt += phrase(tx, col);
                
            } else if(Tx(tx)){//if
                
                txt += node;
                
                col = 2;
                
                txt += phrase(tx, col);
                
            } else if(end && text.length() >= this.max_end_separator_paragraphy){//if
                
                txt += node;
                
                col = 2;
                
                txt += phrase(tx, col);
                
            } else {//if
                
                txt += node;
                
                col = 1;
                
                txt += phrase(tx, col);
                
            }//if
            
        }//for(String tx : dig)
        
        if(this.meta){
            
            txt += "</span>";
            this.meta = false;
            
        }//if(this.meta)
        
        if(this.aspas){
            
            txt += "</q>";
            this.meta = false;
            
        }//if(this.meta)

        return txt;

    }//T(String dig)
    
    private String P(String paragraphy){
        
        Data d = new Data(paragraphy);
        
        if(d.Val()){
            
            return "<p class=\"" + this.p + "\">" + d.DataCompleta(true) + "</p>";
            
        } else {
        
            return "<p class=\"" + this.p + "\">" + T(paragraphy) + "</p>";
        
        }
        
    }//P(String paragraphy)
    
    private String P(String paragraphy, String link){
        
        String txt = "<p class=\"" + this.p + "\"><a href=\"";
        txt += link;
        txt += "\" target=\"_blank\">";
        
        Data d = new Data(paragraphy);
        
        if(d.Val()){
            
            txt += d.DataCompleta(true);
            
        } else {
            
            txt += T(paragraphy);
            
        }
        
        txt += "</a></p>";
        
        
        return txt;
        
    }//P(String paragraphy, String link)
    
    private String Numb(int numb){
        
        int num = 0;
        
        if(numb < 0){
            num = numb - numb*2;
        } else {
            num = numb;
        }
        
        String txt = "";
        
        if(num < 10){
            txt += "00";
            txt += num;
        } else if(num < 100){
            txt += "0";
            txt += num;
        } else if(num < 1000){
            txt += num;
        } else {
            txt = "---";
        }
        
        return txt;
        
    }//Numb(int numb)
    
    private boolean mpeg(String txt){
        
        boolean val = false;
        String text = "";
        
        for(int a = 0; a < txt.length(); a++){
            
            switch(txt.charAt(a)){
                
                case '.' ->{
                    text = "";
                }
                
                default ->{
                    text += txt.charAt(a);
                }
                
            }//switch(txt.charAt(a))
            
        }//for(int a = 0; a < txt.length(); a++)
        

        
        for(String g : this.ext){
            
            if(g.equalsIgnoreCase(text)){val = true;break;}
            
        }//for(String p : ext)
        
        return val;
        
    }//mpeg(String txt)
    
    public void Export(String name){
        
        final int max_char_title = 80;
        
        cod c = new cod();
        
        List<String> doc = new ArrayList();
        
        String arq_1 = "";
        String arq_2 = "";
        boolean ext_val = false;
        final int into_arq = 0;
        int sub_arq = into_arq;
        int arquivo = 0;
            
        boolean extend = false;
        int ex = 0;

        while(ex < this.code.Tot() && !extend){

            if(mpeg(this.code.Read(ex, 0))){extend = true;}
            ex++;

        }//while(ex < this.code.Tot() && !extend)
        
        boolean cd = this.code.Tot() > 0;
        
        String select_title;
        
        select_title = name;
        
        if(name.contains(" ") && name.length() >= max_char_title){
            
            select_title = new Data().DataAbreviada(false);
            
        } else if(name.length() >= max_char_title){
            
            select_title = "(";
            select_title += new Data().DataAbreviada(false);
            select_title += ") ";
            select_title += name;
            
        } else {
            
            select_title = name;
            
        }
        
        doc.add("<html>");
        doc.add("<head>");
        doc.add("<title>" + select_title + "</title>");
        doc.add("<meta charset=\"utf-8\" />");
        doc.add("<!--<link rel=\"icon\" href=\"pasta\\arquivo.ico\" type=\"image/x-icon\">-->");
        doc.add("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        doc.add("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">");
        doc.add("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>");
        doc.add("<link href=\"https://fonts.googleapis.com/css2?family=Bytesized&family=Kavoon&family=Montserrat+Underline:ital,wght@0,100..900;1,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100..900;1,100..900&family=Sofia+Sans+Extra+Condensed:ital,wght@0,1..1000;1,1..1000&family=Winky+Sans:ital,wght@0,300..900;1,300..900&display=swap\" rel=\"stylesheet\">");
        doc.add("<style>");
        
        if(cd){
            
            doc.add("   a:link, a:hover, a:active, a:visited{");
            doc.add("      font-family: \"Montserrat Underline\";");
            doc.add("      font-weight: bold;");
            doc.add("      text-decoration: none;");
            doc.add("   }");
            
            doc.add("   a:link{");
            doc.add("      color: black;");
            doc.add("   }");
            
            doc.add("   a:hover, a:active, a:visited{");
            doc.add("      color: teal;");
            doc.add("   }");
            
            doc.add("   body{");
            doc.add("      background-color:white;");
            doc.add("   }");
            
            doc.add("   div.txt{");
            doc.add("      margin-left:5%;");
            doc.add("      margin-top:10%;");
            doc.add("      width:90%;");
            doc.add("      border: 5px solid black;");
            doc.add("      background-color:transparent;");
            doc.add("      min-height:100px;");
            doc.add("      overflow-y:visible;");
            doc.add("      border-radius: 2em;");
            doc.add("   }");
            
            doc.add("   div.space{");
            doc.add("      width:100%;");
            doc.add("      height:5px;");
            doc.add("      background-color:black;");
            doc.add("   }");
            
            if(extend){
                
                doc.add("   h1.arquivo{");
                doc.add("      color:black;");
                doc.add("      margin-left:10%;");
                doc.add("      font-weight: normal;");
                doc.add("      font-size:calc(20px + 1vw);");
                doc.add("      font-family: \"Winky Sans\";");
                doc.add("      word-wrap: break-word;");
                doc.add("   }");
                
                doc.add("   h1.cabecalho{");
                doc.add("      color:black;");
                doc.add("      margin-left:5%;");
                doc.add("      font-weight: normal;");
                doc.add("      font-size:calc(15px + 1vw);");
                doc.add("      font-family: \"Kavoon\";");
                doc.add("      line-height:2em;");
                doc.add("      word-wrap: break-word;");
                doc.add("   }");
                
            }//if(extend)
            
            doc.add("   h1.tema{");
            doc.add("      color:black;");
            doc.add("      margin-left:2%;");
            doc.add("      font-weight: normal;");
            doc.add("      font-size:calc(15px + 1vw);");
            doc.add("      font-family: \"Winky Sans\";");
            doc.add("      word-wrap: break-word;");
            doc.add("      line-height:2em;");
            doc.add("   }");
            
            doc.add("   p.texto{");
            doc.add("      color:black;");
            doc.add("      font-weight: normal;");
            doc.add("      margin-top:10px;");
            doc.add("      margin-left:2%;");
            doc.add("      margin-right:1%;");
            doc.add("      font-weight: normal;");
            doc.add("      font-size:calc(10px + 1vw);");
            doc.add("      font-family: \"Sofia Sans Extra Condensed\";");
            doc.add("      word-wrap: break-word;");
            doc.add("      line-height:2em;");
            doc.add("   }");
            
            doc.add("   div.divide{");
            doc.add("      width:100%;");
            doc.add("      height:20px;");
            doc.add("      background-color:transparent;");
            doc.add("   }");
            
            doc.add("   p.ended{");
            doc.add("      padding:50px;");
            doc.add("   }");
            
        } else {
            
            doc.add("div.txt{");
            doc.add("  margin-top:20%;");
            doc.add("  margin-left:5%;");
            doc.add("  font-size:7vw;");
            doc.add("}");
            
        }
        
        doc.add("</style>");
        doc.add("</head>");
        doc.add("<body>");
        doc.add("");
        
        if(cd){//if(cd) - 1
            
            for(int x = 0; x < this.code.Tot(); x++){
                
                Numero number = new Numero(this.code.Read(x, 0));
                
                if(mpeg(this.code.Read(x, 0))){
                    
                    int max = this.code.Read(x, 0).lastIndexOf(".");
                    
                    arq_1 = "[";
                    
                    switch(this.code.Read(x, 0).substring(max+1).toLowerCase()){
                        
                        case "mpg" ->{
                            
                            arq_1 += "MPEG-2";
                            
                        }//case "mpg"
                        
                        case "mp4" ->{
                            
                            arq_1 += "MPEG-4";
                            
                        }//case "mp4"
                        
                        case "avi" ->{
                            
                            arq_1 += "ÁUDIO & VÍDEO";
                            
                        }//case "avi"
                        
                        default ->{
                            
                            arq_1 += this.code.Read(x, 0).substring(0,max+1).toUpperCase();
                            
                        }//case "mov"
                        
                    }//switch(this.code.Read(x, 0).substring(0,max+1))
                    
                    arq_1 += "]<br/>";
                    arq_1 += this.code.Read(x, 0).substring(0,max);
                    arq_2 = this.code.Read(x, 0).substring(0,max).toUpperCase();
                    ext_val = true;
                    arquivo++;
                    sub_arq = into_arq;
                    
                } else if(number.Val() && number.Num() == 0){//if(mpeg(this.code.Read(x, 0)))
                    
                    ext_val = false;
                    arquivo = 0;
                    sub_arq = into_arq;
                    
                } else {//if(mpeg(this.code.Read(x, 0)))
                    
                    sub_arq++;
                    
                }//if(mpeg(this.code.Read(x, 0)))
                
                String tx = "";
                
                for(int y = 0; y < this.code.Tot(x); y++){
                    
                    if(y == 0){
                        
                        tx += "<div class=\"txt\">";
                        
                        if(ext_val){
                            
                            if(sub_arq == into_arq){
                                
                                tx += "<h1 class=\"arquivo\">VÍDEO: ";
                                tx += Numb(arquivo);
                                tx += "</h1></h1><div class=\"space\"></div><h1 class=\"";
                                tx += this.h1;
                                tx += "\">";
                                tx += arq_1;
                                tx += "</h1>";
                                
                            } else {//if(sub_arq == 1)
                                
                                tx += "<h1 class=\"cabecalho\">";
                                tx += Numb(sub_arq);
                                tx += "<br/>";
                                tx += arq_2;
                                tx += "</h1></h1><div class=\"space\"></div><h1 class=\"";
                                tx += this.h1;
                                tx += "\">";
                                tx += T(this.code.Read(x, 0));
                                tx += "</h1>";
                                
                            }//if(sub_arq == 1)
                            
                        } else {//if(ext_val)
                            
                            Numero numer = new Numero(this.code.Read(x, 0));
                            
                            tx += "<h1 class=\"";
                            tx += this.h1;
                            tx += "\">";
                            
                            if(numer.Num() == 0 && numer.Val()){
                                
                                tx += T(this.code.Read(0, 0));
                                
                            } else {//if(numer.Num() == 0 && numer.Val())
                                
                                tx += T(this.code.Read(x, 0));
                                
                            }//if(numer.Num() == 0 && numer.Val())
                            
                            tx += "</h1>";
                            
                        }//if(ext_val)
                        
                    } else {//if(y == 0)
                        
                        if(c.Link(this.code.Read(x, y))){
                            
                            tx += "<div class=\"space\"></div>";
                            
                            if(c.Link(this.code.Read(x, y-1))){
                                
                                tx += P(this.code.Read(x, y),this.code.Read(x, y));
                                
                            } else {//if(c.Link(this.code.Read(x, y-1)))
                                
                                tx += P(this.code.Read(x, y-1),this.code.Read(x, y));
                                
                            }//if(c.Link(this.code.Read(x, y-1)))
                            
                        } else if(y == this.code.Tot(x)-1){//if(c.Link(this.code.Read(x, y)))
                            
                            tx += "<div class=\"space\"></div>";
                            tx += P(this.code.Read(x, y));
                            
                        } else {//if(c.Link(this.code.Read(x, y)))
                            
                            if(!c.Link(this.code.Read(x, y+1))){
                                
                                tx += "<div class=\"space\"></div>";
                                tx += P(this.code.Read(x, y));
                                
                            }//if(!c.Link(this.code.Read(x, y+1)))
                            
                        }//if(c.Link(this.code.Read(x, y)))
                        
                    }//if(y == 0)
                    
                }//for(int y = 0; y < this.code.Tot(x); y++)
                
                doc.add(tx + "<div class=\"divide\"></div></div>");
                
            }//for(int x = 0; x < this.code.Tot(); x++)
            
            arq_1 = "";
            arq_2 = "";
            ext_val = false;
            
            doc.add("");
            doc.add("<p class=\"ended\"></p>");
            
            if(this.tag){
                
                doc.add("");
                doc.add("<script>");
                doc.add("   ");
                doc.add("   const metatag = document.getElementsByTagName(\"span\");");
                doc.add("   ");
                doc.add("   for(var i = 0; i < metatag.length; i++){");
                doc.add("      ");
                doc.add("      metatag[i].innerText = \"<\" + metatag[i].innerHTML + \">\";");
                doc.add("      metatag[i].style.letterSpacing = \"1%\";");
                doc.add("      ");
                doc.add("   }");
                doc.add("   ");
                doc.add("</script>");
                
            }//if(this.tag)
            
        } else {//if(cd) - 1
            
            doc.add("<div class=\"txt\">" + name + "</div>");
            
        }//if(cd) - 1
        
        doc.add("");
        doc.add("</body>");
        doc.add("</html>");
        
        if(cd){//if(cd) - 2
            
            doc.add("");
            
            doc.add("<!-- " + name + " --");
            
            for(int x = 0; x < this.code.Tot(); x++){
                
                String tx = "";
                
                for(int y = 0; y < this.code.Tot(x); y++){
                    
                    if(y > 0){
                        tx += ";";
                    }
                    
                    tx += this.code.Read(x, y);
                    
                }//for(int y = 0; y < this.code.Tot(x); y++)
                
                doc.add(tx);
                
            }//for(int x = 0; x < this.code.Tot(); x++)
            
        }//if(cd) - 2
        
        Files line_page = new Files(this.host + 
                "page_" +
                new Data().Load() + 
                "_" + 
                new Hora(true).Load() + 
                ".htm");
        
        line_page.Clear();
        line_page.WriteAll(doc);
        
        doc.clear();
        
    }//Export(String title, boolean target)
    
}//Exportar