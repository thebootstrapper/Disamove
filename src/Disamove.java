import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class Disamove extends JFrame {
	
	private static final long serialVersionUID = 1L;

	static JLabel handicape_label_footer;
    
    // constante inmodifiable
    static final String MAL_VOYANT="Mal voyant(e)";
    static final String MAL_ENTENDANT="Mal entendant(e)";
    static final String PERSONE_A_MOBILITE_REDUITE="Personne à mobilité réduite";

    //éléments de la barre de parametrage
	private JMenuItem menuItemMalVoyant ;
	private JMenuItem menuItemMalEntendant ;
	private JMenuItem menuItemPersonneAMobiliteReduite;



	JPanel map_et_text;

	//boutons de la partie doite de la fenetre
	private JButton depart;
	private JButton arrive ;
	private JButton resultat; 
		
	static JPanel handicape_panel;

	JFrame fenetrePosition;
	private JComboBox etages ;
	private JComboBox destinations ;
	private JButton ok ;


	static final int nombreColonne = 10;
	static final int nombreLigne = 10;
	
	 

	public Disamove(String nom_application) {
		super(nom_application);
			
		
		// j'essaye d'attribuer à la fenetre l'aspect d'un  logiciel du systeme
		// utilisé car par défaut elle a l'apect une d'une application java
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			System.out.print("pb lors du  Changement du theme:"+e);
		}
		
		// paremetrage de la fenetre
		setBounds(100,100,1400,1000);//setBounds(x, y, width, height); (x,y) point en haut à gauche
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// quitter le programme avec le botton de fermeture par defaut (X)
		Container contenu= this.getContentPane();
		
		contenu.setLayout(new BorderLayout());
		

		//System.exit(0); => fermer la fenetre
		
		
		// initialisation du menu Parametres		
		JMenu menu = new JMenu("Paramètres");
		
		JMenuItem Handicapes = new JMenu("Handicapes");

		menuItemMalVoyant = new JMenuItem(Disamove.MAL_VOYANT);
		menuItemMalVoyant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				handicape_label_footer.setText(Disamove.MAL_VOYANT);				
			}
		});
		
		menuItemMalEntendant = new JMenuItem(Disamove.MAL_ENTENDANT);
		menuItemMalEntendant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				handicape_label_footer.setText(Disamove.MAL_ENTENDANT);
			}
		});
		
		menuItemPersonneAMobiliteReduite = new JMenuItem(Disamove.PERSONE_A_MOBILITE_REDUITE);
		menuItemPersonneAMobiliteReduite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				handicape_label_footer.setText(Disamove.PERSONE_A_MOBILITE_REDUITE);
				
			}
		});
		
		Handicapes.add(menuItemMalVoyant);  
		Handicapes.add(menuItemMalEntendant);  
		Handicapes.add(menuItemPersonneAMobiliteReduite); 
		
		menu.add(Handicapes);
		JMenuBar menuBar = new JMenuBar(); 
		setJMenuBar(menuBar);  
		menuBar.add(menu);  
				

		// initialisation de la partie qui contiendra la map

		map_et_text = new JPanel( new BorderLayout());
		Border blackline = BorderFactory.createLineBorder(Color.gray,1);
		JPanel panneau_map = new JPanel (new GridLayout (nombreLigne,nombreColonne));
		for(int l = 0; l<nombreLigne;l++){
			for(int c = 0; c<nombreLigne;c++){

				Case element = new Case(typeCase.MUR,new Position(l, c,0));
			   element.setBackground(Color.WHITE);
			   element.setBorder(blackline);
//			   element.setMargin(new Insets(0, 100, 0, 100));
			   element.setBackground(Color.black);
//			   element.setOpaque(true);
			   
			   
			   panneau_map.add(element);
			}
		}

		map_et_text.add(panneau_map, BorderLayout.CENTER);

		//initialisation de la partie qui contiendra le texte
		JPanel panneau_text = new JPanel(new GridLayout(1, 1));
		panneau_text.setBackground(Color.BLACK);

		JLabel zoneTexte = new JLabel();
		zoneTexte.setText(" Dans 50, pas tournez à droite");
		zoneTexte.setForeground(Color.WHITE);
		panneau_text.add(zoneTexte);
	//	contenu.add(panneau_text, grille_virtuelle);

		panneau_text.setPreferredSize(new Dimension(100, 100));
		panneau_text.revalidate();
		map_et_text.add(panneau_text, BorderLayout.SOUTH);
		contenu.add(map_et_text, BorderLayout.CENTER);
		
		
		
		//  initialisation de la partie contenant les bouttons et la bousole
		JPanel boutons_et_boussol = new JPanel(new BorderLayout());
		JPanel panneau_bouton = new JPanel(new FlowLayout()); 

		//initialisation des boutons
		depart =new JButton("Depart");
		depart.addActionListener(new DepartArriveeListener());// il est un déclancheur d'évenement
		depart.setForeground(Color.BLACK);// couleur du texte
		depart.setActionCommand("depart");
		depart.setPreferredSize(new Dimension(100, 50));//largeur,hauteur
		depart.revalidate();// application de la dimension

		panneau_bouton.add(depart);
		
		arrive =new JButton("Arrivée");
		arrive.setEnabled(false);
		arrive.addActionListener( new DepartArriveeListener());
		arrive.setActionCommand("arrivée");
		arrive.setForeground(Color.BLACK);
		arrive.setPreferredSize(new Dimension(100, 50));//largeur,hauteur
		arrive.revalidate();
		panneau_bouton.add(arrive);

		resultat =new JButton("Résultat");
		resultat.addActionListener(new ResultatListener());
		resultat.setEnabled(false);
		resultat.setForeground(Color.BLACK);
		resultat.setPreferredSize(new Dimension(100, 50));//largeur,hauteur
		resultat.revalidate();
		panneau_bouton.add(resultat);
		panneau_bouton.setBackground(Color.ORANGE);
		panneau_bouton.setPreferredSize(new Dimension(400, 200));
		panneau_bouton.revalidate();

		
		JPanel panneau_boussole = new JPanel();
		panneau_boussole.setBackground(Color.ORANGE);
		
		boutons_et_boussol.add(panneau_bouton, BorderLayout.NORTH);
		boutons_et_boussol.add(panneau_boussole,BorderLayout.CENTER);
		contenu.add(boutons_et_boussol, BorderLayout.EAST);
		
		// initialisation du label qui affiche l'handicpe choisie au pied de l'application
		handicape_label_footer = new JLabel(Disamove.PERSONE_A_MOBILITE_REDUITE);
		handicape_label_footer.setForeground(Color.WHITE);
		
				
		handicape_panel = new JPanel(); 
		handicape_panel.setBackground(Color.BLUE);
		handicape_panel.add(Disamove.handicape_label_footer);
		contenu.add(handicape_panel, BorderLayout.SOUTH);
	  
	 
		  fenetrePosition = null;
		  JComboBox etages=null; ;
		  JComboBox destinations=null ;
		  ok =null;
		  
		  this.repaint();
	}

	public void afficheMap( int [][] mat) throws  MatriceVideException {

		System.out.print("\n ### Appelle de la methode: afficheMatrice #### \n");
		if( mat.length ==0){
			throw  new MatriceVideException();
		}

		Border blackline = BorderFactory.createLineBorder(Color.gray,1);
		JPanel map = new JPanel (new GridLayout (mat.length,mat[0].length));
		for(int ligne =0; ligne < mat.length; ligne++){
			for( int colonne =0; colonne < mat[ligne].length; colonne++) {

		System.out.print(" \nParcours de la matrice\n");
				typeCase type = typeCase.values()[mat[ligne][colonne]];

				Case element = new Case(type, new Position(ligne, colonne,0));//ligne, colonne, étage
				element.setBackground(Color.WHITE);
				element.setBorder(blackline);
				element.setBackground(Color.RED);
				map.add(element);
			}

		}

//		setPanneau_map(map);
		JPanel _map_et_texte = getMap_et_text();
		_map_et_texte.add(map, BorderLayout.CENTER);
		setMap_et_text(_map_et_texte);
		this.repaint();
	}
		

	public class ResultatListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	}
	
	public class DepartArriveeListener implements ActionListener ,ItemListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			fenetrePosition= new JFrame ("Position");
			fenetrePosition.setBounds(500,500,700,150);//setBounds(x, y, width, height); (x,y) point en haut à gauche
			fenetrePosition.setVisible(true);
			Container contenuPosition= fenetrePosition.getContentPane();
			contenuPosition.setLayout(new GridLayout(1,3,10,10));//ligne,colonne,espace horizontale, espace verticale
				
	
			JPanel pannelPosition = new JPanel();
			pannelPosition.setBackground(Color.ORANGE);
			
			String listeEtage []= {"etage 0", "etage 1", "etage 2"};
			etages = new JComboBox(listeEtage);
			etages.setEditable(true);
			etages.addItemListener(this);
			etages.setPreferredSize(new Dimension(300, 50));//largeur,hauteur
			etages.revalidate();
			pannelPosition.add(etages);
			
			String listeDestinations []= {"CT201", "toilette homme", "toilette femme", "CT108"};
			destinations = new JComboBox(listeDestinations);
			destinations.setEditable(true);
			//destinations.addActionListener(this);
			destinations.addItemListener(this); 
			destinations.setPreferredSize(new Dimension(300, 50));//largeur,hauteur
			destinations.revalidate();
			pannelPosition.add(destinations);
			
			JButton source = (JButton)e.getSource();
			ok = new JButton("OK");	arrive.setEnabled(false);
			ok.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					// écouteurs des boutons Ok
					// s'il s agit de la fenetre créee en appuyant sur Depart
					if ( getOk().getActionCommand().equals(getDepart().getActionCommand())) {
						System.out.println(" Etage sélectionné :"+ getEtages().getSelectedItem());
						System.out.println(" Destination sélectionné :"+getDestinations().getSelectedItem());
						getArrive().setEnabled(true);
					}

					// s'il s agit de la fenetre créee en appuyant sur Arrivée
					else if (getOk().getActionCommand().equals(getArrive().getActionCommand())){				
						System.out.println(" Etage sélectionné :"+ getEtages().getSelectedItem());
						System.out.println(" Destination sélectionné :"+getDestinations().getSelectedItem());
						getResultat().setEnabled(true);
						getArrive().setEnabled(true);
					}
					else {
						System.out.println("mauvaise création de la fenetre de position");				
					}

					getFenetrePosition().dispose();
					setFenetrePosition(null);
				
					
				}
			});
			
			ok.setForeground(Color.BLACK);
			ok.setActionCommand(source.getActionCommand());
			ok.setPreferredSize(new Dimension(100, 50));//largeur,hauteur
			ok.revalidate(); 
			pannelPosition.add(ok);
			
			fenetrePosition.add(pannelPosition);
			fenetrePosition.getDefaultCloseOperation();
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			//quand l'utilisateur choisit un étage
			if (e.getSource().equals(getEtages())){ 
			}
			//quand l'uitlisateur choisit une destination
			if ( e.getSource().equals(getDestinations())) { 
			}
		
		}
	 
	}
	 

	public JFrame getFenetrePosition() {
			return fenetrePosition;
		}

	public void setFenetrePosition(JFrame fenetrePosition) {
			this.fenetrePosition = fenetrePosition;
		}


	public JButton getOk() {
		return ok;
	}


	public void setOk(JButton ok) {
		this.ok = ok;
	} 
		
	 public JComboBox getEtages() {
		return etages;
	}

	public void setEtages(JComboBox etages) {
		this.etages = etages;
	}

	public JComboBox getDestinations() {
		return destinations;
	}

	public void setDestinations(JComboBox destinations) {
		this.destinations = destinations;
	}

	public void actionPerformedArrive(ActionEvent e) {
		 getResultat().setEnabled(true);
	}
	 	 
	public void actionPerformedResultat(ActionEvent e) {
	
	}
		
	public JMenuItem getMenuItemMalVoyant() {
		return menuItemMalVoyant;
	}

	public void setMenuItemMalVoyant(JMenuItem menuItemMalVoyant) {
		this.menuItemMalVoyant = menuItemMalVoyant;
	}

	public JMenuItem getMenuItemMalEntendant() {
		return menuItemMalEntendant;
	}

	public void setMenuItemMalEntendant(JMenuItem menuItemMalEntendant) {
		this.menuItemMalEntendant = menuItemMalEntendant;
	}

	public JMenuItem getMenuItemPersonneAMobiliteReduite() {
		return menuItemPersonneAMobiliteReduite;
	}

	public void setMenuItemPersonneAMobiliteReduite(JMenuItem menuItemPersonneAMobiliteReduite) {
		this.menuItemPersonneAMobiliteReduite = menuItemPersonneAMobiliteReduite;
	}

	public JButton getDepart() {
		return depart;
	}

	public void setDepart(JButton depart) {
		this.depart = depart;
	}

	public JButton getArrive() {
		return arrive;
	}

	public void setArrive(JButton arrive) {
		this.arrive = arrive;
	}

	public JButton getResultat() {
		return resultat;
	}

	public void setResultat(JButton resultat) {
		this.resultat = resultat;
	}

	public static String getPersoneAMobiliteReduite() {
		return PERSONE_A_MOBILITE_REDUITE;
	}

	public JPanel getMap_et_text() {
		return map_et_text;
	}

	public void setMap_et_text(JPanel map_et_text) {
		this.map_et_text = map_et_text;
	}

	
}
