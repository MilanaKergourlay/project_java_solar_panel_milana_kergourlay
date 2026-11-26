class Regulateur{
    double dToff,dTon;
    Capteur capteur;
    Pompe pompe;
    Ballon ballon;
    TuyauRetour tuyauRetour;
    void setCapteur(Capteur capteur){
        this.capteur=capteur;
    }
    void setBallon(Ballon ballon){
        this.ballon=ballon;
    }
    void setTuyauRetour(TuyauRetour tuyauRetour){
        this.tuyauRetour=tuyauRetour;
    }
    Regulateur(double dToff, double dTon){
        this.dToff=dToff;
    }
    void regule(){
        if(this.pompe.isOn()){
            if(this.tuyauRetour.getTemperatureSortie()-this.ballon.getTemperatureSortie()>=this.dToff){
                //RAF
            }else{
                this.pompe.turnOff();
            }
            
        }else{
            if(this.capteur.getTemperatureMoyenne()-this.ballon.getTemperatureSortie()>=this.dTon){
               this.pompe.turnOn(); 
            }else{
                //RAF
            }
            
        }
    }
}