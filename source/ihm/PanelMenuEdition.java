package source.ihm;

public class PanelMenuEdition
{
	private static final Font  POLICE_TEXTE  = new Font    ("Goldman", Font.BOLD, 100);
	private static final Color COULEUR_TEXTE = Color.decode("#f1c232");

	private JLabel  lblMenu   ;

	private JButton btnNouveau  ;
	private JButton btnOuvrir;

	private Controleur ctrl;

	public PanelMenuEdition(Controleur ctrl)
	{
		JPanel sPanelAction;
		this.ctrl = ctrl;

		this.setLayout(new GridLayout(3, 1) );

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		sPanelAction = new JPanel();
		sPanelAction = new JPanel();

		this.lblMenu = new JLabel("MENU", SwingConstants.CENTER);
		this.lblMenu.setFont(PanelMenu.POLICE_TEXTE);
		this.lblMenu.setForeground(PanelMenu.COULEUR_TEXTE);

		this.btnNouveau   = new JButton("Nouveau");
		this.btnOuvrir = new JButton("Ouvrir");

		
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */
		
		sPanelAction.add(this.btnNouveau);
		sPanelAction.add(this.btnOuvrir);

		this.add(this.lblMenu);
		this.add( new JPanel() );
		this.add(sPanelAction, new GridBagConstraints() );

	}
}