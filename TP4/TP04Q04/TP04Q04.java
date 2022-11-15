/**
 
 * @file Game.java
 * @author Pedro Lopes
 * @version 0.2
 * @date 2022-10-02
 * @copyright Copyright (c) 2022
 
**/

// ----------------------------------------------------------------------------------------------------------------- //

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
 
// ----------------------------------------------------------------------------------------------------------------- //

class Game {

    static SimpleDateFormat default_dateFormat = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);

    private String name, owners, website, developers;
    private ArrayList<String> languages, genres;
    private Date release_date;
    private int app_id, age, dlcs, avg_playtime;
    private float price, upvotes;
    private boolean windows, mac, linux;

    public Game() {

        this.name = this.owners = this.website = this.developers = null;
        this.languages = new ArrayList<String>();
        this.genres = new ArrayList<String>();
        this.release_date = null;
        this.app_id = this.age = this.dlcs = this.avg_playtime = -1;
        this.price = this.upvotes = -1;
        this.windows = this.mac = this.linux = false;
    }

    public Game(String name, String owners, String website, String developers, ArrayList<String> languages, ArrayList<String> genres, Date release_date, int app_id, int age, int dlcs, int upvotes, int avg_playtime, float price, boolean windows, boolean mac, boolean linux) {

        this.name = name;
        this.owners = owners;
        this.website = website;
        this.developers = developers;
        this.languages = languages;
        this.genres = genres;
        this.release_date = release_date;
        this.app_id = app_id;
        this.age = age;
        this.dlcs = dlcs;
        this.upvotes = upvotes;
        this.avg_playtime = avg_playtime;
        this.price = price;
        this.windows = windows;
        this.mac = mac;
        this.linux = linux;
    }

    public void setName(String name) { this.name = name; }
    public void setOwners(String owners) { this.owners = owners; }
    public void setWebsite(String website) { this.website = website; }
    public void setDevelopers(String developers) { this.developers = developers; }
    public void setLanguages(ArrayList<String> languages) { this.languages = languages; }
    public void setGenres(ArrayList<String> genres) { this.genres = genres; }
    public void setReleaseDate(Date release_date) { this.release_date = release_date; }
    public void setAppId(int app_id) { this.app_id = app_id; }
    public void setAge(int age) { this.age = age; }
    public void setDlcs(int dlcs) { this.dlcs = dlcs; }
    public void setAvgPlaytime(int avg_playtime) { this.avg_playtime = avg_playtime; }
    public void setPrice(float price) { this.price = price; }
    public void setUpvotes(float upvotes) { this.upvotes = upvotes; }
    public void setWindows(boolean windows) { this.windows = windows; }
    public void setMac(boolean mac) { this.mac = mac; }
    public void setLinux(boolean linux) { this.linux = linux; }

    public String getName() { return this.name; }
    public String getOwners() { return this.owners; }
    public String getWebsite() { return this.website; }
    public String getDevelopers() { return this.developers; }
    public ArrayList<String> getLanguages() { return this.languages; }
    public ArrayList<String> getGenres() { return this.genres; }
    public Date getReleaseDate() { return this.release_date; }
    public int getAppId() { return this.app_id; }
    public int getAge() { return this.age; }
    public int getDlcs() { return this.dlcs; }
    public int getAvgPlaytime() { return this.avg_playtime; }
    public float getPrice() { return this.price; }
    public float getUpvotes() { return this.upvotes; }
    public boolean getWindows() { return this.windows; }
    public boolean getMac() { return this.mac; }
    public boolean getLinux() { return this.linux; }
    
    public Game clone() {

        Game cloned = new Game();

        cloned.name = this.name;
        cloned.owners = this.owners;
        cloned.website = this.website;
        cloned.developers = this.developers;
        cloned.languages = this.languages;
        cloned.genres = this.genres;
        cloned.release_date = this.release_date;
        cloned.app_id = this.app_id;
        cloned.age = this.age;
        cloned.dlcs = this.dlcs;
        cloned.avg_playtime = this.avg_playtime;
        cloned.price = this.price;
        cloned.upvotes = this.upvotes;
        cloned.windows = this.windows;
        cloned.mac = this.mac;
        cloned.linux = this.linux;

        return cloned;
    }

    public void read(String line) {

        char c_search;
        int index = 0, atr_index = 0;

        // ---------------------------------- //

        // Find "AppID"
        while(true) {

            index++;

            if(line.charAt(index) == ',') {
                
                this.app_id = Integer.parseInt(line.substring(atr_index, index));

                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        
        // Find "Name"
        if(line.charAt(atr_index) != ',') {

            if(line.charAt(atr_index) == '\"') {
                
                atr_index++;
                c_search = '\"';
            }
            else c_search = ',';
            
            while(true) {

                index++;

                if(line.charAt(index) == c_search) {
                    
                    this.name = line.substring(atr_index, index);

                    if(c_search == ',') index++;
                    else if(c_search == '\"') index += 2;
                    
                    atr_index = index;
                    break;
                }
            }
        }
        else atr_index = ++index;

        // ---------------------------------- //
        
        // Find release date
        if(line.charAt(atr_index) != ',') {

            SimpleDateFormat df;
            
            if(line.charAt(atr_index) == '\"') {
                
                df = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

                atr_index++;
                c_search = '\"';
            }
            else {
                
                df = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);

                c_search = ',';
            }

            while(true) {

                index++;

                if(line.charAt(index) == c_search) {
                    
                    try { this.release_date = df.parse(line.substring(atr_index, index)); } 
                    catch (java.text.ParseException e) { e.printStackTrace(); }

                    if(c_search == ',') index++;
                    else if(c_search == '\"') index += 2;
                    
                    atr_index = index;
                    break;
                }
            }
        }
        else atr_index = ++index;

        // ---------------------------------- //
        
        // Find "Owners"
        while(true) {

            index++;

            if(line.charAt(index) == ',') {
                
                this.owners = line.substring(atr_index, index);

                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        
        // Find "Age"
        while(true) {

            index++;

            if(line.charAt(index) == ',') {

                this.age = Integer.parseInt(line.substring(atr_index, index));

                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        
        // Find "Price"
        while(true) {

            index++;

            if(line.charAt(index) == ',') {
                
                this.price = Float.parseFloat(line.substring(atr_index, index));

                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        
        // Find "DLCs"
        while(true) {

            index++;

            if(line.charAt(index) == ',') {
                
                this.dlcs = Integer.parseInt(line.substring(atr_index, index));

                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        
        // Find "Languages"
        while(true) {

            index++;

            if(line.charAt(index) == ']') {

                index++;
                
                if(line.charAt(index) == ',') index++;
                else if(line.charAt(index) == '\"') index += 2;

                atr_index = index;
                break;
            }
            else if(line.charAt(index) == '\'') {

                int wordStart = index + 1;

                while(true) {

                    index++;

                    if(line.charAt(index) == '\'') {
                        
                        this.languages.add(line.substring(wordStart, index));
                        break;
                    }
                }
            }
        }

        // ---------------------------------- //
        
        // Find "Website"
        if(line.charAt(atr_index) != ',') {

            if(line.charAt(atr_index) == '\"') {
                
                atr_index++;
                c_search = '\"';
            }
            else c_search = ',';
            
            while(true) {

                index++;

                if(line.charAt(index) == c_search) {
                    
                    this.website = line.substring(atr_index, index);

                    atr_index = ++index;
                    break;
                }
            }
        }
        else atr_index = ++index;

        // ---------------------------------- //
        
        // Find "Windows"
        while(true) {

            index++;

            if(line.charAt(index) == ',') {

                this.windows = Boolean.parseBoolean(line.substring(atr_index, index));

                atr_index = ++index;
                break;
            }
        }

        // Find "Mac"
        while(true) {

            index++;

            if(line.charAt(index) == ',') {

                this.mac = Boolean.parseBoolean(line.substring(atr_index, index));

                atr_index = ++index;
                break;
            }
        }

        // Find "Linux"
        while(true) {

            index++;

            if(line.charAt(index) == ',') {

                this.linux = Boolean.parseBoolean(line.substring(atr_index, index));

                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        
        // Find "Upvotes"
        int positives, negatives;

        while(true) {

            index++;

            if(line.charAt(index) == ',') {

                positives = Integer.parseInt(line.substring(atr_index, index));

                atr_index = ++index;
                break;
            }
        }

        while(true) {

            index++;

            if(line.charAt(index) == ',') {

                negatives = Integer.parseInt(line.substring(atr_index, index));

                atr_index = ++index;
                break;
            }
        }

        this.upvotes = (float)(positives * 100) / (float)(positives + negatives);

        // ---------------------------------- //
        
        // Find "AVG Playtime"
        while(true) {

            index++;

            if(line.charAt(index) == ',') {

                this.avg_playtime = Integer.parseInt(line.substring(atr_index, index));

                atr_index = ++index;
                break;
            }
        }

        // ---------------------------------- //
        
        // Find "Developers"
        if(line.charAt(atr_index) != ',') {

            if(line.charAt(atr_index) == '\"') {
                
                atr_index++;
                c_search = '\"';
            }
            else c_search = ',';
            
            while(true) {

                index++;

                if(line.charAt(index) == c_search) {
                    
                    this.developers = line.substring(atr_index, index);

                    atr_index = ++index;
                    break;
                }
            }
        }
        else atr_index = ++index;
       
        // ---------------------------------- //
        
        // Find "Genres"
        if(index < line.length() - 1) {

            if(line.charAt(index) == ',') atr_index = ++index;                    
            if(line.charAt(atr_index) == '\"') {

                atr_index++;
                
                while(true) {

                    index++;

                    if(line.charAt(index) == ',') {
                        
                        this.genres.add(line.substring(atr_index, index));

                        atr_index = ++index;
                    }
                    else if(line.charAt(index) == '\"') {

                        this.genres.add(line.substring(atr_index, line.length() - 1));
                        break;
                    }
                }
            }
            else this.genres.add(line.substring(atr_index, line.length()));
        }

        // -------------------------------------------------------------------------------- //
    }

    public void print() {

        String avg_pt = null;

        if(this.avg_playtime == 0) avg_pt = "null ";
        else if(this.avg_playtime < 60) avg_pt = this.avg_playtime + "m ";
        else {

            if(this.avg_playtime % 60 == 0) avg_pt = this.avg_playtime / 60 + "h ";
            else avg_pt = (this.avg_playtime / 60) + "h " + (this.avg_playtime % 60) + "m ";
        }

        DecimalFormat df = new DecimalFormat("##");

        System.out.println(this.app_id + " " + this.name + " " + default_dateFormat.format(this.release_date) + " " + this.owners + " " + this.age + " " + String.format(Locale.ENGLISH, "%.2f", this.price) + " " + this.dlcs + " " + this.languages + " " + this.website + " " + this.windows + " " + this.mac + " " + this.linux + " " + (Float.isNaN(this.upvotes) ? "0% " : df.format(this.upvotes) + "% ") + avg_pt + this.developers + " " + this.genres);
    }

    // -------------------------------------------------------------------------------------- //

    public static boolean isFim(String line) { return line.compareTo("FIM") == 0; }

    // -------------------------------------------------------------------------------------- //

    public static void main(String[] args) throws Exception {

        ArrayList<Game> games = new ArrayList<Game>();
                
        // ------------------------------------------------------------------------------ //

        try {

            // Read CSV file
            String basefile = "/tmp/games.csv";

            FileInputStream fstream = new FileInputStream(basefile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            // ------------------------------------ //

            // Explode CSV file reading games
            String line;
  
            while((line = br.readLine()) != null) {

                Game game = new Game();

                game.read(line);
                games.add(game);
            }

            // Close CSV file
            fstream.close();
        }
        catch(IOException e) { e.printStackTrace(); }

        // ---------------------------------------------------------------------------------------------- //

        // Read .in file
        Scanner scr = new Scanner(System.in);
        String line = scr.nextLine();
        
        while(true) {

            if(isFim(line)) break;

            int app_id = Integer.parseInt(line);

            // Search game with .in id
            for(Game game : games) {
                
                if(game.getAppId() == app_id) game.print();
            }

            line = scr.nextLine();
        }

        // ---------------------------------------------------------------------------------------------- //

        scr.close();
    }

    // ---------------------------------------------------------------------------------------------- //
}

/**
 * Geracao de elementos de um array
 * @author Max do Val Machado
 * @version 3 08/2020
 */

class Geracao {

   /**
    * Retorna o timestamp atual
    * @return timestamp atual
    */
   public long now(){
      return new Date().getTime();
   }

}

//LISTA DUPLAMENTE CADEADA - INICIO
class CCelulaDup {

	public Game item; // O Item armazendo pela clula
	public CCelulaDup ant; // Referencia a celula anterior
	public CCelulaDup prox; // Referencia a proxima celula

	public CCelulaDup() {
		item = null;
		ant = null;
		prox = null;
	}

	public CCelulaDup(Game valorItem) {
		item = valorItem;
		ant = null;
		prox = null;
	}

	public CCelulaDup(Game valorItem, CCelulaDup celulaAnt, CCelulaDup proxCelula) {
		item = valorItem;
		ant = celulaAnt;
		prox = proxCelula;
	}
}

class CListaDup {
	private CCelulaDup primeira; // Referencia a primeira celula da lista (celula cabeca)
	private CCelulaDup ultima; // Referencia a ultima celula da lista
	private int qtde, comp, mov;

	public CListaDup() {
		primeira = new CCelulaDup();
		ultima = primeira;
        qtde = 0;
        comp = 0;
        mov = 0;
	}

    public int getComp() {return comp;}
    public int getMov() {return mov;}

	public boolean vazia() {
		return primeira == ultima;
	}

	public void insereFim(Game valorItem) {
		ultima.prox = new CCelulaDup(valorItem, ultima, null);
		ultima = ultima.prox;
		qtde++;
	}

	public void insereComeco(Game valorItem) {
		if (primeira == ultima) { // Se a lista estiver vazia insere no fim
			ultima.prox = new CCelulaDup(valorItem, ultima, null);
			ultima = ultima.prox;
		} else { // senao insere no comeco
			primeira.prox = new CCelulaDup(valorItem, primeira, primeira.prox);
			primeira.prox.prox.ant = primeira.prox;
		}
		qtde++;
	}

	public void removeComecoSemRetorno() {
		if (primeira != ultima) {
			primeira = primeira.prox;
			primeira.ant = null;
			qtde--;
		}
	}

	public void imprime() {
        for (CCelulaDup i = primeira.prox; i != null; i = i.prox) {
            i.item.print();
        }

		// CCelulaDup aux = primeira.prox;
		// while (aux != null) {
		// 	System.out.println(aux.item);
		// 	aux = aux.prox;
		// }
	}

	public void imprimeFor() {
		for (CCelulaDup aux = primeira.prox; aux != null; aux = aux.prox)
			System.out.println(aux.item);
	}

	public void imprimeInv() {
		CCelulaDup aux = ultima;
		while (aux.ant != null) {
			System.out.println(aux.item);
			aux = aux.ant;
		}
	}

	public void imprimeInvFor() {
		for (CCelulaDup aux = ultima; aux.ant != null; aux = aux.ant)
			System.out.println(aux.item);
	}

	public boolean contem(Game elemento) {
		boolean achou = false;
		CCelulaDup aux = primeira.prox;
		while (aux != null && !achou) {
			achou = aux.item.equals(elemento);
			aux = aux.prox;
		}
		return achou;
	}

	public boolean contemFor(Game elemento) {
		boolean achou = false;
		for (CCelulaDup aux = primeira.prox; aux != null && !achou; aux = aux.prox)
			achou = aux.item.equals(elemento);
		return achou;
	}

	public Game retornaPrimeiro() {
		if (primeira != ultima)
			return primeira.prox.item;
		return null;
	}

	public Game retornaIndice(int posicao) {
		if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {
			CCelulaDup aux = primeira.prox;
			// Procura a posicao a ser inserido
			for (int i = 1; i < posicao; i++, aux = aux.prox)
				;
			if (aux != null)
				return aux.item;
		}
		return null;
	}

	public Game retornaUltimo() {
		if (primeira != ultima)
			return ultima.item;
		return null;
	}

	public void removeFimSemRetorno() {
		if (primeira != ultima) {
			ultima = ultima.ant;
			ultima.prox = null;
			qtde--;
		}
	}

	public void remove(Game valorItem) {
		if (primeira != ultima) {
			CCelulaDup aux = primeira.prox;
			boolean achou = false;
			while (aux != null && !achou) {
				achou = aux.item.equals(valorItem);
				if (!achou)
					aux = aux.prox;
			}
			if (achou) { // achou o elemento
				CCelulaDup anterior = aux.ant;
				CCelulaDup proximo = aux.prox;
				anterior.prox = proximo;
				if (proximo != null)
					proximo.ant = anterior;
				else
					ultima = anterior;
				qtde--;
			}
		}
	}

	public Game removeRetornaComeco() {
		if (primeira != ultima) {
			CCelulaDup aux = primeira.prox;
			primeira = primeira.prox;
			primeira.ant = null;
			qtde--;
			return aux.item;
		}
		return null;
	}

	public Game removeRetornaFim() {
		if (primeira != ultima) {
			CCelulaDup aux = ultima;
			ultima = ultima.ant;
			ultima.prox = null;
			qtde--;
			return aux.item;
		}
		return null;
	}

	public int quantidade() {
		return qtde;
	}

	public void teste() {
		CCelulaDup i = primeira.prox;
		CCelulaDup j = ultima;
		CCelulaDup k;
		while (j.prox != i) {
			Game tmp = i.item;
			i.item = j.item;
			j.item = tmp;
			i = i.prox;
			for (k = primeira; k.prox != j; k = k.prox)
				;
			j = k;
		}
	}

    //quicksort--------------------------------------------------------
    public void quicksort() {
        quicksort(0, qtde-1);
    }

    public CCelulaDup getCelulaIndex(int pos){
        int tmp = 0;
        CCelulaDup i;
        for( i = primeira.prox; tmp < pos ; i = i.prox, tmp++);
        return i;
    }

    private void quicksort(int esq, int dir) {
        int i = esq, j = dir;
        Game pivo = getCelulaIndex((esq + dir)/2 ).item;
        while (i <= j) {
            CCelulaDup aux = getCelulaIndex(i);
            comp++;
            while ((aux.item.getReleaseDate().compareTo(pivo.getReleaseDate()) < 0) || ((aux.item.getReleaseDate().compareTo(pivo.getReleaseDate()) == 0) && (aux.item.getName().compareTo(pivo.getName()) < 0))){
                i++;
                aux = aux.prox;
            }
            CCelulaDup index = getCelulaIndex(j);
            while ((index.item.getReleaseDate().compareTo(pivo.getReleaseDate()) > 0) || ((index.item.getReleaseDate().compareTo(pivo.getReleaseDate()) == 0) && (index.item.getName().compareTo(pivo.getName()) > 0))){
                j--;
                index = index.ant;
            }

            if (i <= j) {
                comp++;
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j) {
            comp++;
            quicksort(esq, j);
        }
        if (i < dir) {
            comp++;
            quicksort(i, dir);
        }

    }

    public void swap(int i, int primeiro) {
        Game aux = getCelulaIndex(i).item;
        getCelulaIndex(i).item = getCelulaIndex(primeiro).item;
        getCelulaIndex(primeiro).item = aux;
        mov++;
    }
    //quicksort--------------------------------------------------------

}
//LISTA DUPLAMENTE CADEADA - FIM



class TP04Q04{
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");

        Geracao algoritmo = new Geracao();
        double inicio = algoritmo.now(), fim;

        //FIRST PART
        CListaDup games = new CListaDup();

        String[] entrada = new String[100];

        String[] line = new String[5000];
        String[] id = new String[5000];
        int index = 0, indexID = 0;

        String arq = "//tmp//games.csv";
        BufferedReader leitor = null;

        //CREATE AND ARRAY WITH ALL LINES FROM FILE
        try{
            //read from file
            leitor = new BufferedReader(new FileReader(arq)); //reading from games.csv

            //put all lines in an array
            while((line[indexID] = leitor.readLine()) != null) {

                String separador[] =  line[indexID].split(",");
                
                //put all ids in an array
                id[indexID++] = separador[0];
            }
        }catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally{
            if (leitor != null){
                try {
                    leitor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //read until FIM
        do{
            entrada[index] = MyIO.readLine();
        }while(!isFim(entrada[index++]));
        index--;

        //insert games in a List
        for(int j = 0; j < index; j++){
            for(int k = 0; k < indexID; k++){

                //find id
                if(entrada[j].equals(id[k])){
                    Game game = new Game();
                    //create new game and fill it with the correspondent line of the id

                    game.read(line[k]);   
                    //game.imprimir();     

                    //insert games in a List
                    games.insereFim(game);
                    break;
                }
            }
        }

        //print list
        // MyIO.println("----------lista original----------");
        // games.mostrar();
        // MyIO.println("----------------------------------");

        //SECOND PART - ORDER
        games.quicksort();

        //print list
        // MyIO.println("----------------lista final--------------");
        games.imprime();
        // MyIO.println("----------------------------------");

        fim = algoritmo.now();

        double tempo = (fim-inicio)/1000.0;

        //file to write time and nº of comparassions
        File log = new File("matricula_quicksort2.txt");

        try {
            if (!log.exists()) {
                log.createNewFile();
            }
            
            FileWriter fw = new FileWriter(log, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("766430\t" + games.getComp() + "\t" + games.getMov() + "\t" + tempo);

            bw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}