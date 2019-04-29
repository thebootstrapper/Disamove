public class Main {
	//static public enum couleur { blanc, noir, jaune};
	//static public int[][]matrix;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		java.util.Scanner Clavier =   new java.util.Scanner(System.in);
		
		Disamove disamove = new Disamove("Disamove");



		int [][]mat = {
				{0, 1, 2, 3, 4, 5, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
				{0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		};

		try{
			disamove.afficheMap(mat);
		}
		catch (MatriceVideException e){
			System.err.print( e.getMessage() + "\n vous essayer de créer une carte à partir d'une matrice vide\n");
		}

	}

}

