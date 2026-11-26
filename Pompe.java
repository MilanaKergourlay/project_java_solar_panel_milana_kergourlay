class Pompe{
    boolean isOn;
    double debit; //kg/s
    Pompe(double debit){
        this.isOn = false;
        this.debit = debit;
    }
    void turnOn(){
        this.isOn = true;
    }
    void turnOff(){
        this.isOn = false;
    }
    double getDebit(){
        if(this.isOn){
            return this.debit;
        }else{
            return 0.0;
        }
    }
    boolean isOn(){
        return isOn;
    }

}