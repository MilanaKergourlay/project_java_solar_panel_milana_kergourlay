public class Soleil {

/* ============================ */
/* === Données de la classe === */
/* ============================ */
    int    numeroJour;
    double latitudeDegre;
    double latitudeRadian;
    double constanteSolaire;
    double declinaisonDegre;
    double declinaisonRadian;
    double angleHoraireDegre;
    double angleHoraireRadian;
    double angleHoraireAuLeverRadian;
    double angleHoraireAuCoucherRadian;
    double angleHoraireAuLeverDegre;
    double angleHoraireAuCoucherDegre;
    int heureLeverSeconde;
    int heureCoucherSeconde;
    int dureeDuJourSeconde;
    double hauteurDegre;
    double hauteurRadian;
    double azimutDegre;
    double azimutRadian;
    double irradiationGlobaleJournaliereG0;
    double irradiationG;
    double irradiationD;
    double irradiationS;
    double a,b;
    double fluxDirect,fluxDiffus;
    Meteo meteo;

/* ==================== */
/* === Constructeur === */
/* ==================== */
    Soleil(int numeroJour, double latitudeDegre){
        this.numeroJour = numeroJour;
        this.latitudeDegre = latitudeDegre;
        this.latitudeRadian = latitudeDegre * Math.PI / 180.0;
        this.constanteSolaire = 1353.0 * (1.0 + 0.033 * Math.cos(0.984 * this.numeroJour * Math.PI / 180.0));
        this.declinaisonDegre = 23.45 * Math.sin(0.98 * (this.numeroJour + 284) * Math.PI / 180.0);
        this.declinaisonRadian = this.declinaisonDegre * Math.PI / 180.0;
        this.angleHoraireAuLeverRadian = Math.acos(-Math.tan(this.latitudeRadian)*Math.tan(this.declinaisonRadian));
        this.angleHoraireAuCoucherRadian = - this.angleHoraireAuLeverRadian;
        this.angleHoraireAuLeverDegre = 180.0 * this.angleHoraireAuLeverRadian / Math.PI;
        this.angleHoraireAuCoucherDegre = 180.0 * this.angleHoraireAuCoucherRadian / Math.PI;
        this.heureLeverSeconde = (int) ((12 - this.angleHoraireAuLeverDegre / 15.0) * 3600);
        this.dureeDuJourSeconde = (int) ((2.0 * this.angleHoraireAuLeverDegre / 15.0) * 3600);
        this.heureCoucherSeconde = this.heureLeverSeconde + this.dureeDuJourSeconde;
        this.irradiationGlobaleJournaliereG0 = 37950000.0 * Math.cos(this.latitudeRadian) * Math.cos(this.declinaisonRadian) *
                                               (Math.sin(this.angleHoraireAuLeverRadian) - this.angleHoraireAuLeverRadian * Math.cos(this.angleHoraireAuLeverRadian)) / 86400;
        this.a = 0.409 + 0.502 * Math.sin(this.angleHoraireAuLeverRadian - 1.04719755);
        this.b = 0.661 - 0.477 * Math.sin(this.angleHoraireAuLeverRadian - 1.04719755);
    }
    
/* ========================= */
/* === Fonction toString === */
/* ========================= */
    public String toString(){
        return "numero du jour = " + this.numeroJour + "\n" +
               "latitude en degres = " + this.latitudeDegre + " dg" + "\n" +
               "latitude en radians = " + this.latitudeRadian + " rad" + "\n" +
               "constante solaire = " + this.constanteSolaire + " W/m²" + "\n" +
               "declinaison en degres = " + this.declinaisonDegre + " dg" + "\n" +
               "declinaison en radians = " + this.declinaisonRadian + " rad" + "\n" +
               "heure lever en secondes = " + this.heureLeverSeconde + " s" + "\n" +
               "duree du jour en secondes = " + this.dureeDuJourSeconde + " s" + "\n" +
               "G0 = " + this.irradiationGlobaleJournaliereG0 + " W/m²";
               
    }

/* ============================ */
/* === Fonction mettreAjour === */
/* ============================ */
void mettreAjour(int time){
    this.angleHoraireDegre = 15.0 * (time - 43200) / 3600.0;
    this.angleHoraireRadian = this.angleHoraireDegre * Math.PI / 180.0;
    this.hauteurRadian = Math.asin(Math.sin(this.latitudeRadian) * Math.sin(this.declinaisonRadian) +
                                   Math.cos(this.latitudeRadian) * Math.cos(this.declinaisonRadian) * Math.cos(this.angleHoraireRadian));
    this.hauteurDegre = this.hauteurRadian * 180.0 / Math.PI;
    this.azimutRadian = Math.asin(Math.cos(this.declinaisonRadian)*Math.sin(this.angleHoraireRadian)/Math.cos(this.hauteurRadian));
    this.azimutDegre = this.azimutRadian * 180.0 / Math.PI;
    this.irradiationG = this.irradiationGlobaleJournaliereG0 * (Math.sqrt(2.0 * this.meteo.getNebulosite(time) + 1.0) - 0.72);
    double kt = this.irradiationG / this.irradiationGlobaleJournaliereG0;
    if (kt <= 0.17){
        this.irradiationD = 0.99 * this.irradiationG;
    }else if (kt <= 0.75){
        this.irradiationD = (1.188 - 2.272 * kt + 9.473 * kt * kt - 21.865 * kt * kt * kt + 14.648 * kt * kt * kt * kt) * this.irradiationG;
    }else if (kt <= 0.80){
        this.irradiationD = (0.632 - 0.54 * kt) * this.irradiationG;
    }else{
        this.irradiationD = 0.2 * this.irradiationG;
    }
    this.irradiationS = this.irradiationG - this.irradiationD;
    double coefficientDeRepartition;
    coefficientDeRepartition = Math.PI * (this.a + this.b * Math.cos(this.angleHoraireRadian))
                                       * (Math.cos(this.angleHoraireRadian) - Math.cos(this.angleHoraireAuLeverRadian))
                                       / (Math.sin(this.angleHoraireAuLeverRadian) - this.angleHoraireAuLeverRadian * Math.cos(this.angleHoraireAuLeverRadian));
    this.fluxDirect = coefficientDeRepartition * this.irradiationS;
    this.fluxDiffus = coefficientDeRepartition * this.irradiationD;
}

/* ===================================== */
/* === Fonction getHeureLeverSeconde === */
/* ===================================== */
int getHeureLeverSeconde(){
    return this.heureLeverSeconde;
}

/* =================================== */
/* === Fonction getFluxDirectAuSol === */
/* =================================== */
double getFluxDirectAuSol(){
    return this.fluxDirect;
}

/* =================================== */
/* === Fonction getFluxDiffusAuSol === */
/* =================================== */
double getFluxDiffusAuSol(){
    return this.fluxDiffus;
}

/* ======================================= */
/* === Fonction getHeureCoucherSeconde === */
/* ======================================= */
int getHeureCoucherSeconde(){
    return this.heureCoucherSeconde;
}

/* ========================= */
/* === Fonction setMeteo === */
/* ========================= */
void setMeteo(Meteo meteo){
    this.meteo = meteo;
}

/* ===================== */
/* === Fonction main === */
/* ===================== */
    public static void main(String[] args) {
        Soleil soleil = new Soleil(150,42.0);
        Meteo meteo = new Meteo(20.0,0.8);
        soleil.setMeteo(meteo);
        System.out.println(soleil);
        int currentTime = soleil.getHeureLeverSeconde();
        while (currentTime < soleil.getHeureCoucherSeconde()){
            currentTime += 300;
            soleil.mettreAjour(currentTime);
            System.out.println("t = " + currentTime + " h = " + soleil.hauteurDegre + " azimut = " + soleil.azimutDegre + " Wdirect = " + soleil.fluxDirect + " Wdiffus = " + soleil.fluxDiffus);
        }
    }
}
