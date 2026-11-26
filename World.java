// Online Java - IDE, Code Editor, Compiler

// Online Java is a quick and easy tool that helps you to build, compile, test your programs online.

// Write your Java code here

public class World {
    Soleil soleil;
    Capteur capteur;
    TuyauDepart tuyauDepart;
    TuyauRetour tuyauRetour;
    Meteo meteo;
    Regulateur regulateur;
    Pompe pompe;
    Ballon ballon; 
    
    
    public static void main(String[] arg){
       World monde= new World(); 
       monde.simule(60, 80);
    }
    
    World(){
        final int NUMERO_JOUR=56;
        final double LATITUDE_DEGRE=42.0;
        final double AZIMUT_CAPTEUR=0.0;
        final double ELEVATION_CAPTEUR_DEGRE= 60.0 ;
        final double SURFACE_CAPTEUR= 8.96;
        final double FACTEUROPTIQUE_CAPTEUR= 0.814;
        final double H1_CAPTEUR= 3.87;
        final double H2_CAPTEUR= 0.0126;
        final double LONGUEUR_TUYAU = 20.0;
        final double DIAMETREHYDRAULIQUE_TUYAU = 0.01;//(m)
        final double EPAISSEURISOLANT_TUYAU = 0.04;//(m)
        final double LAMBDAISOLANT_TUYAU = 0.035;//(W/m.K)
        final double TEMPERATURE_EXT = 20.0 ; // en deg
        final double NEBULOSITE = 0.5;// compris entre 0 et 1
        final double DTOFF_REGULATEUR = 2.0; //en deg
        final double DTON_REGULATEUR = 2.0; // en deg
        final double DEBIT_POMPE = 0.0000833; //(m^3/s)
        final double TINT = 20.0;
        
        soleil = new Soleil(NUMERO_JOUR, LATITUDE_DEGRE);
        capteur = new Capteur(AZIMUT_CAPTEUR, ELEVATION_CAPTEUR_DEGRE,SURFACE_CAPTEUR, FACTEUROPTIQUE_CAPTEUR, H1_CAPTEUR, H2_CAPTEUR);
        tuyauDepart = new TuyauDepart(LONGUEUR_TUYAU,DIAMETREHYDRAULIQUE_TUYAU,EPAISSEURISOLANT_TUYAU,LAMBDAISOLANT_TUYAU);
        tuyauRetour = new TuyauRetour(LONGUEUR_TUYAU,DIAMETREHYDRAULIQUE_TUYAU,EPAISSEURISOLANT_TUYAU,LAMBDAISOLANT_TUYAU);
        meteo = new Meteo(TEMPERATURE_EXT, NEBULOSITE);
        regulateur = new Regulateur(DTOFF_REGULATEUR,DTON_REGULATEUR);
        ballon = new Ballon(TINT);
        pompe = new Pompe(DEBIT_POMPE);
    }
    
    void simuleOneStep(int dt, int heure){
        // a faire
        // permet de faire le dernier pas
        // a completer avec le simule du ballon 
    }
    
    void simule(int pasDeTemps, int pasDeTempsSortie){
        int currentTime = this.soleil.getHeureLeverSeconde();
        int endTime = this.soleil.getHeureCoucherSeconde();
        int nextSaveTime = currentTime + pasDeTempsSortie;
        while(currentTime <= endTime){
            int dt = pasDeTemps;
            if(currentTime+pasDeTempsSortie > nextSaveTime){
                dt = nextSaveTime - currentTime;
            }
            this.simuleOneStep(dt, currentTime);
            currentTime += dt;
            if(currentTime == nextSaveTime){
                nextSaveTime += pasDeTempsSortie;
            }
        }
        
    }
}
