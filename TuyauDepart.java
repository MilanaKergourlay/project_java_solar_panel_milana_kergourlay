class TuyauDepart extends Tuyau{
    Ballon ballon;

    double simule(int pasDeTemps){
        // A faire
        return 0.0;
    }
    TuyauDepart(double longueur, double diametreHydraulique, double epaisseurIsolant, double lambdaIsolant){
        super(longueur, diametreHydraulique, epaisseurIsolant, lambdaIsolant);
    }
    void setBallon(Ballon ballon){
        this.ballon=ballon;
    }
        // A faire
}