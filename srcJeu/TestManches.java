package srcJeu;

import srcJeu.metier.manches.Manche;

public class TestManches
{
    public static void main(String[] args) 
    {
        Manche manche = Manche.creerManche("Chlorophite");
        
        System.out.println(manche);
        System.out.println("Espece manche :" + manche.getEspece());
        System.out.println("Manche finie: " + manche.estMancheFinie());

        System.out.println(manche.getCarte());

        manche.getPioche().enleverCarte("Gazeuze", true);
        manche.getPioche().enleverCarte("Océan", true);
        manche.getPioche().enleverCarte("Tellurique", true);
        manche.getPioche().enleverCarte("Volcanique", true);
        manche.getPioche().enleverCarte("Joker", true);
        System.out.println(manche);

        System.out.println("Manche finie: " + manche.estMancheFinie());

        manche.decouvrirCarte();
        System.out.println(manche);
        manche.decouvrirCarte();
        System.out.println(manche);
    }
}