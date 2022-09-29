public class KrediYonetimi
{
    private double faiz = 0.99;
    private int krediNotu = 1200; // 1 ile 1200 arası olmalı. Yüksek olması güvenli müşteri demektir.
    private double krediBorcu;

    public double getKrediBorcu() {
        return krediBorcu;
    }

    public void setKrediBorcu(double krediBorcu)
    {
        this.krediBorcu += krediBorcu;
    }
    public void yuvarlaKrediBorcu(double krediBorcu)
    {
        this.krediBorcu = krediBorcu;
    }

    public double getFaiz() {
        return faiz;
    }

    public int getKrediNotu() {
        return krediNotu;
    }
}
