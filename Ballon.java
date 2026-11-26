class Ballon{
    TuyauRetour tuyauRetour;
    TuyauDepart tuyauDepart;
    Pompe pompe; 
    Capteur capteur;
    double temperatureSortie ;
    double tint;
    
    Ballon(double tint){
        this.tint = tint;
    }

    
    void setTuyauRetour(TuyauRetour tuyauRetour){
        this.tuyauRetour=tuyauRetour;
    }
    void setTuyauDepart(TuyauDepart tuyauDepart){
        this.tuyauDepart=tuyauDepart;
    }
    void setPompe(Pompe pompe) {
        this.pompe = pompe;
    }
    void setCapteur(Capteur capteur) {
        this.capteur = capteur;
    }

    
    double simule(int pasDeTemps) {
        double h = 1.0 ; // (W.m^-2.K^-1) // A calculer avec les resistances equivalentes
        double S = 5.0 ; // (m^2)
        double c_eau = 4182 ; // (J/kg·K)
        double c_fluide = 4182 ; // c_fluide = c_eau
        double M_eau = 1000 ; // (kg)
        
        double Tfe = this.tuyauRetour.getTemperatureSortie();
        double Tfs = this.temperatureSortie;
        
        this.temperatureSortie = pasDeTemps / (c_eau * M_eau) * (pompe.getDebit() * c_fluide * (Tfe - Tfs) + h * S * tint) + this.temperatureSortie * (1 - h * S * pasDeTemps /(c_eau * M_eau));


        return this.temperatureSortie;
    }

    double getTemperatureSortie() {
        return this.temperatureSortie;
    }
    public static void main(String[] args){
        
    }
    // A faire
}