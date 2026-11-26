class Capteur{
    double azimut, elevationRad;
    double surface;
    Meteo meteo;
    Soleil soleil;
    double temperatureMoyenne;
    double facteurOptique;
    double h1, h2;
    Pompe pompe;
    TuyauDepart tuyaudepart;
    Capteur(double azimut, double elevationDeg, double surface, double facteurOptique, double h1, double h2){
        this.azimut = azimut;
        this.elevationRad = Math.PI * elevationDeg/180;
        this.surface = surface;
        this.facteurOptique = facteurOptique;
        this.h1 = h1; //(W/m^2/°C)coeficient de corelation d'echange
        this.h2 = h2; //(W/m^2/°C^2)
    }
    
    void setSoleil(Soleil soleil){
        this.soleil = soleil;
    }
    void setPompe(Pompe pompe){
        this.pompe = pompe;
    }
    void setMeteo(Meteo meteo) {
        this.meteo = meteo;
    }
    void setTemperatureMoyenne(){
        this.temperatureMoyenne = temperatureMoyenne;
    }
    
    double simule(int pasDeTemps) {
        

        return this.temperatureMoyenne;
    }
    double getTemperatureMoyenne(){
        return this.temperatureMoyenne;
    }
}