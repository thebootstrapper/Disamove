
public class Position {

	private int ligne;
	private int colonne;
	private int etage;
	
	
	
	public Position(int ligne, int colonne, int etage) {
		super();
		this.ligne = ligne;
		this.colonne = colonne;
		this.etage = etage;
	}
	
	 
	public int getLigne() {
		return ligne;
	}
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	public int getColonne() {
		return colonne;
	}
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	public int getEtage() {
		return etage;
	}
	public void setEtage(int etage) {
		this.etage = etage;
	}
	
	



}
