package com.example.farmermate;

public class Work {
    int Day1;
    private String Step1,Detail,Reco,Warni;

    public Work(int Day1,String Step1, String Detail,String Reco, String Warni)
    {
        this.Day1 = Day1;
        this.Step1 = Step1;
        this.Detail = Detail;
        this.Reco = Reco;
        this.Warni = Warni;
    }


    public int getDay1(){
        return Day1;
    }
    public void setDay(int Day1){
        this.Day1 = Day1;
    }
    public String getStep1(){
        return Step1;
    }
    public void setStep1(String Step1){
        this.Step1 = Step1;
    }
    public String getDtail(){
        return Detail;
    }
    public void setDetail(String Detail){
        this.Detail = Detail;
    }
    public String getReco(){
        return Reco;
    }
    public void setReco(String Reco){
        this.Reco = Reco;
    }
    public String getWarni(){
        return Warni;
    }
    public void setWarni(String Warni){
        this.Warni = Warni;
    }

}
