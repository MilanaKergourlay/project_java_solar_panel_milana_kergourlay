class TuyauRetour extends Tuyau {
    Capteur capteur;
    Pompe pompe;
    double temperatureSortie; 

    TuyauRetour(double longueur, double diametreHydraulique, double epaisseurIsolant, double lambdaIsolant) {
        super(longueur, diametreHydraulique, epaisseurIsolant, lambdaIsolant);
    }

    void setCapteur(Capteur capteur) {
        this.capteur = capteur;
    }

    void setPompe(Pompe pompe) {
        this.pompe = pompe;
    }


    double simule(int pasDeTemps) {
        double dExterne = diametreHydraulique + 2.0 * epaisseurIsolant;
        double tExt = this.meteo.getTemperature(0); 
        double cfluide = 4186; //(J/kg·K)  
        double h = 1.0;

        double debit = pompe.getDebit();
        double S = Math.PI * dExterne * longueur;

        double tEntree = capteur.getTemperatureMoyenne();
        this.temperatureSortie = tEntree - h * S * (tEntree - tExt) / (debit * cfluide) ;
        

        return this.temperatureSortie;
    }

    double getTemperatureSortie() {
        return this.temperatureSortie;
    }
}