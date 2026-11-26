class Meteo {

/* ============================ */
/* === Données de la classe === */
/* ============================ */
    double temperature;
    double nebulosite;

/* ==================== */
/* === Constructeur === */
/* ==================== */
    Meteo(double temperature, double nebulosite){
        this.temperature = temperature;
        this.nebulosite = nebulosite;
    }

/* =============================== */
/* === Fonction getTemperature === */
/* =============================== */
    double getTemperature(int time){
        return this.temperature;
    }

/* ============================== */
/* === Fonction getNebulosite === */
/* ============================== */
    double getNebulosite(int time){
        return this.nebulosite;
    }
}