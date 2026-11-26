 abstract class Tuyau{
    double longueur;
    double diametreHydraulique;
    double epaisseurIsolant, lambdaIsolant;
    Meteo meteo;
    Tuyau(double longueur, double diametreHydraulique, double epaisseurIsolant, double lambdaIsolant){
        this.longueur=longueur;
        this.diametreHydraulique=diametreHydraulique;
        this.epaisseurIsolant=epaisseurIsolant;
        this.lambdaIsolant=lambdaIsolant;
    }
    void setMeteo(Meteo meteo){
        this.meteo=meteo;
    }
    abstract double simule(int pasDeTemps);

}