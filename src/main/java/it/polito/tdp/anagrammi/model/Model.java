package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.anagrammi.DAO.AnagrammaDAO;

public class Model {
	
	List<String> listaAnagrammi;
	AnagrammaDAO anagrammaDAO = new AnagrammaDAO();
	
	public Model() {
		this.anagrammaDAO = anagrammaDAO;
	}
	
	public void anagramma(String s) {
		listaAnagrammi =  new ArrayList<String>();
		anagrammaRicorsiva("",0,s); //all'inizio non ho selezionato alcuna lettera, quindi stringa vuota, livello zero
		//Mi costruisco il livello 0, le precondizioni per poter avviare la ricorsione 
		
	}
	public void anagrammaRicorsiva(String parziale, int livello, String rimanenti) { //ESEMPIO: parziale="", livello=0, rimanenti="ABCD"
		if(rimanenti.length()==0) {
			//System.out.println(parziale);
			listaAnagrammi.add(parziale);
		}else {
			//caso normale, es: parziale="AC", livello=2,rimanenti="BD"
			//dovrò decomporre questo problema in tanti sottoprob quanti sono le lettere rimanenti
			for(int pos=0; pos<rimanenti.length();pos++) {
				String nuovaParziale = parziale+rimanenti.charAt(pos); //nuovaParziale=""+A (per il primo ciclo for pos=0 il carattere di rimanenti in posizione 0 sarà A)
				String nuoveRimanenti = rimanenti.substring(0, pos)+ rimanenti.substring(pos+1); //nuoveRimanenti sarà la stringa che avevo all'inizio come rimanenti a cui
				                                                                                 //però tolgo il carattere in posizione pos: quindi dovrò prendere la sottostringa
				                                                                                 //dall'inizio fino al carattere in posizione pos ESCLUSO + la sottostringa dal carattere
				                                                                                 //in posizione pos ESCLUSO fino alla fine della stringa rimanenti (NB: poichè substring
				                                                                                 //esclude l'ultimo carattere di default per prendere l'ultimo carattere della stringa rimanenti metto pos+1)
				anagrammaRicorsiva(nuovaParziale, livello+1, nuoveRimanenti);
			}
		}
	}
	
	public List<String> getListaAnagrammi() {
		return listaAnagrammi;
	}
	
	public boolean isCorretto(String anagramma) {
		return this.anagrammaDAO.isCorrect(anagramma);
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
        Model main = new Model();
		main.anagramma("ABCD");
	}*/
}
