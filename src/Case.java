import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Case extends JButton {

	private Position position;
	private typeCase type;// si c est un mur, une porte, une allee, un ascenseur ou une sortie
	// si vous utiliser éclipse il faut l'initialiser à : "/images/"
	public static final String image_directory_path ="/home/eisti/Desktop/Ing1/S2/GL2/Projet_local/Disamove/images/";
	

	
	public Case(Position p) {
		super();
		this.position = p;


		//this.setIcon(getImage(typeCase.MUR));
	}
	
	public Case(typeCase type,Position p) {
		
		this(p);
		this.setIcon(getImage(type));
		System.out.print("\n \n construction d'un case de type: "+type +"\n");

	}
	

	// l'imge doiat etre du meme nom que le typeCase
	public ImageIcon getImage(typeCase t) {
//		/home/eisti/Desktop/Ing1/S2/GL2/disamove/Teste1/images/mur.png
		if ( t != typeCase.ALLEE) {
		String url = Case.image_directory_path;
		String img= t + ".png";
		img = img.toLowerCase();
		url = url + img;

		ImageIcon imageIcon = new ImageIcon(url);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(50,50, java.awt.Image.SCALE_SMOOTH);
		newimg.toString();

		System.out.print( "chemin de l'image : "+url);
		return new ImageIcon(newimg);
	}
		else {
			System.out.print(" création d'une case ALLEE: pas de chargement d'images ");
			return null;// si c est une allée on ne retourne pas d image, on colore juste en blanc
		}
	}
	
	
}
