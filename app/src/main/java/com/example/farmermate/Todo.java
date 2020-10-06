package com.example.farmermate;

public class Todo
{
    private int ID,Day;
    private String Name1;
    private String Step,Dtail,Rec,Warn;
    private String s1,r1,w1,s2,r2,w2,s3,r3,w3,s4,r4,w4,s5,r5,w5,s6,r6,w6,s7,r7,w7,s8,r8,w8,s9,r9,w9;
    private String s10,r10,	w10,s11,r11,w11,s12,r12,w12,s13,r13,w13,s14,r14,w14,s15,r15,w15,s16,r16,w16,s17,r17,w17,s18,r18,w18;
    private String s19,r19,w19,	s20,r20,w20,s21,r21,w21,s22,r22,w22,s23,r23,w23,s24,r24,w24,s25,r25,w25,s26,r26,w26;

    private String Description;
    private String Products;
    private String Advantage;

public Todo(int Day,String Step, String Dtail,String Rec, String Warn)
{
        this.ID = ID;
        this.Day = Day;
        this.Name1 = Name1;
        this.Step = Step;
        this.Dtail = Dtail;
        this.Rec = Rec;
        this.Warn = Warn;
        }

    public static Todo get(int i) {
    return null;
    }

    public int getDay(){
            return Day;
        }
        public void setDay(int Day){
        this.Day = Day;
    }
        public String getStep(){
        return Step;
        }
        public void setStep(String Step){
            this.Step = Step;
        }
        public String getDtail(){
            return Dtail;
        }
        public void setDtail(String Dtail){
            this.Dtail = Dtail;
        }
        public String getRec(){
            return Rec;
        }
        public void setRec(String Rec){
            this.Rec = Rec;
        }
        public String getWarn(){
            return Warn;
        }
        public void setWarn(String Warn){
            this.Warn = Warn;
        }
        public int getId () {
        return ID;
    }

        public void setId ( int ID){
        this.ID = ID;
    }

    }



