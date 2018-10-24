package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetnaTacka, krajnjaTacka;
    private boolean pocetnaPripadaIntervalu, krajnjaPripadaIntervalu;
    Interval(double poc, double kraj, boolean pocPripada, boolean krajPripada) throws IllegalArgumentException {
        if(poc > kraj) throw new IllegalArgumentException("Pocetna tacka je veca od krajnje!");
        pocetnaTacka = poc;
        krajnjaTacka = kraj;
        pocetnaPripadaIntervalu = pocPripada;
        krajnjaPripadaIntervalu = krajPripada;
    }
    Interval() {
        pocetnaTacka = 0;
        krajnjaTacka = 0;
        pocetnaPripadaIntervalu = false;
        krajnjaPripadaIntervalu = false;
    }
    @Override
    public String toString() {
        String s = "";
        if(!isNull()) {
            if (pocetnaPripadaIntervalu) s += "[";
            else s += "(";
            s += pocetnaTacka + "," + krajnjaTacka;
            if (krajnjaPripadaIntervalu) s += "]";
            else s += ")";
        } else {
            s += "()";
        }
        return s;
    }
    public boolean isIn(double tacka) {
        if(pocetnaPripadaIntervalu && krajnjaPripadaIntervalu && pocetnaTacka <= tacka && tacka <= krajnjaTacka) return  true;
        if(!pocetnaPripadaIntervalu && krajnjaPripadaIntervalu && pocetnaTacka < tacka && tacka <= krajnjaTacka) return true;
        if(!pocetnaPripadaIntervalu && !krajnjaPripadaIntervalu && pocetnaTacka < tacka && tacka < krajnjaTacka) return true;
        if(pocetnaPripadaIntervalu && !krajnjaPripadaIntervalu && pocetnaTacka <= tacka && tacka < krajnjaTacka) return true;
        return false;
    }
    public boolean isNull() {
        if(pocetnaTacka == 0 && krajnjaTacka == 0 && !pocetnaPripadaIntervalu && !krajnjaPripadaIntervalu) return true;
        return false;
    }
    public Interval intersect(Interval i) {
        if(this.equals(i)) return this;
        Interval presjek = new Interval();
        if(krajnjaTacka < i.pocetnaTacka && pocetnaTacka < i.krajnjaTacka) return new Interval();
        double min, max;
        if(pocetnaTacka > i.pocetnaTacka) {
            max = pocetnaTacka;
            presjek.pocetnaPripadaIntervalu = pocetnaPripadaIntervalu;
        } else {
            max = i.pocetnaTacka;
            presjek.pocetnaPripadaIntervalu = i.pocetnaPripadaIntervalu;
        }
        if(krajnjaTacka < i.krajnjaTacka) {
            min = krajnjaTacka;
            presjek.krajnjaPripadaIntervalu = i.krajnjaPripadaIntervalu;
        } else {
            min = i.krajnjaTacka;
            presjek.krajnjaPripadaIntervalu = i.krajnjaPripadaIntervalu;
        }
        presjek.pocetnaTacka = max;
        presjek.krajnjaTacka = min;
        return presjek;
    }
    public static Interval intersect(Interval i1, Interval i2) {
        if(i1.equals(i2)) return i1;
        if(i1.krajnjaTacka < i2.pocetnaTacka && i1.krajnjaTacka < i2.krajnjaTacka) return new Interval();
        double min, max;
        boolean pripadaPoc, pripadaKraj;
        if(i1.pocetnaTacka > i2.pocetnaTacka) {
            max = i1.pocetnaTacka;
            pripadaPoc = i1.pocetnaPripadaIntervalu;
        } else {
            max = i2.pocetnaTacka;
            pripadaPoc = i2.pocetnaPripadaIntervalu;
        }
        if(i1.krajnjaTacka < i2.krajnjaTacka) {
            min = i1.krajnjaTacka;
            pripadaKraj = i1.krajnjaPripadaIntervalu;
        } else {
            min = i2.krajnjaTacka;
            pripadaKraj = i2.krajnjaPripadaIntervalu;
        }
        return new Interval(max, min, pripadaPoc, pripadaKraj);
    }
    boolean equals(Interval i) {
        return pocetnaTacka == i.pocetnaTacka && krajnjaTacka == i.krajnjaTacka && pocetnaPripadaIntervalu == i.pocetnaPripadaIntervalu && krajnjaPripadaIntervalu == i.krajnjaPripadaIntervalu;
    }
}
