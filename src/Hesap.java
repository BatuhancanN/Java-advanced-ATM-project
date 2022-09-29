public class Hesap
{
    private String adSoyad = "Batuhan Can";
    private double para = 5000;

    public String getAdSoyad() {
        return adSoyad;
    }

    public double getPara() {
        return para;
    }

    public void setPara(double para)
    {
        this.para += para;
    }

}
