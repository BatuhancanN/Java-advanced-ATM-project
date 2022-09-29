import java.util.Scanner;
public class Main
{

    public static void main(String[] args)
    {
        Scanner key = new Scanner(System.in);
        HesapYonetimi hesapYonetimi = new HesapYonetimi();
        boolean app = false;

        System.out.print("ID girin: ");
        String id = key.next();

        System.out.print("Şifre girin: ");
        String sifre = key.next();

        if(id.equals(hesapYonetimi.getId()) && sifre.equals(hesapYonetimi.getSifre()))
        {
            app = true;
            System.out.println("GİRİŞ BAŞARILI \tHoş geldiniz sayın " + hesapYonetimi.getAdSoyad());
            app(app);
        }
        else
        {
            app = false;
            System.err.println("HATALI GİRİŞ");
            app(app);
        }
    }

    public static void app(boolean app)
    {
        Hesap hesap = new Hesap();
        HesapYonetimi hesapYonetimi = new HesapYonetimi();
        KrediYonetimi krediYonetimi = new KrediYonetimi();
        Scanner key = new Scanner(System.in);

        while(app)
        {
            System.out.println("\n\n0 - Çıkış\n1 - Bilgi\n2 - Yatır\n3 - Çek\n4 - Krediye başvur\n5 - Kredi borcu öde\n6 - Şifre değiştir\nSeçim: ");
            int secim = key.nextInt();

            switch(secim)
            {
                case 0:
                {
                    app = false;
                    System.err.println("BAŞARIYLA ÇIKIŞ YAPILDI");
                    break;
                }
                case 1:
                {
                    System.out.println("----------------------------");
                    System.out.println("\t" + hesap.getAdSoyad() + "\n\t" + hesap.getPara() + " TL" + "\n\tKredi borcu: " + krediYonetimi.getKrediBorcu() + " TL");
                    System.out.println("----------------------------");
                    break;
                }
                case 2:
                {
                    if(hesap.getPara() < 0)
                    {
                        System.err.println("YATIRILACAK TUTAR, BORCUNUZDAN DÜŞÜRÜLECEKTİR...");

                        System.out.println("\nYatırılacak tutar: ");
                        double yatir = key.nextDouble();
                        if(yatir > (hesap.getPara() * -1))
                        {
                            System.err.println("YATIRILAN TUTAR, BORCUNUZDAN BÜYÜK OLAMAZ.");
                        }
                        else
                        {
                            hesap.setPara(yatir);
                            System.out.println("\nHesaba nakit girişi sağlandı. Mevcut bakiye: " + hesap.getPara() + " TL");
                        }
                    }
                    else
                    {
                        System.out.println("\nYatırılacak tutar: ");
                        double yatir = key.nextDouble();
                        hesap.setPara(yatir);
                        System.out.println("\nHesaba nakit girişi sağlandı. Mevcut bakiye: " + hesap.getPara() + " TL");
                    }
                    break;
                }
                case 3:
                {
                    System.out.println("\nÇekilecek tutar: ");
                    double cek = key.nextDouble();

                    double borc = cek - hesap.getPara();
                    if(hesap.getPara() < cek)
                    {
                        System.out.println("Eğer " + cek + " tutarını çekerseniz, hesabınıza " + borc + " Tl borç yansıtılacaktır. 24 saat içinde yatırmanız durumunda faiz uygulanmayacaktır." +
                                "\nKabul ediyorsanız 1, etmiyorsanız 2'yi tuşlayınız.");
                        int secBorc = key.nextInt();
                        switch(secBorc)
                        {
                            case 1:
                            {
                                hesap.setPara(cek * -1);
                                System.out.println("\nHesaptan nakit çıkışı sağlandı. Mevcut bakiye: " + hesap.getPara() + " TL");
                                break;
                            }
                            case 2:
                            {
                                break;
                            }
                            default: System.err.println("HATALI SEÇİM"); break;
                        }

                    }
                    else
                    {
                        hesap.setPara(cek * -1);
                        System.out.println("\nHesaptan nakit çıkışı sağlandı. Mevcut bakiye: " + hesap.getPara() + " TL");
                    }
                    break;
                }
                case 4:
                {
                    if(hesap.getPara() < 0)
                    {
                        System.err.println("MAALESEF KREDİ BAŞVURUNUZ REDDEDİLDİ.");
                    }
                    else
                    {
                        System.out.print("\nLütfen çekilecek kredi miktarını girin: ");
                        double istenen = key.nextDouble();
                        System.out.print("\nLütfen 3-36 arası vade seçin: ");
                        double vade = key.nextDouble();


                        if(krediYonetimi.getKrediNotu() < 400)
                        {
                            System.err.println("MAALESEF KREDİ BAŞVURUNUZ REDDEDİLDİ.");
                        }
                        else if(krediYonetimi.getKrediNotu() < 800 && istenen < 5000)
                        {
                            if(krediYonetimi.getKrediBorcu() > 0)
                            {
                                System.err.println("MAALESEF KREDİ BAŞVURUNUZ REDDEDİLDİ.");
                            }
                            else
                            {
                                krediYonetimi.setKrediBorcu(faizHesapla(istenen,vade));
                                hesap.setPara(istenen);
                                System.out.println("Krediniz onaylandı ve hesabınıza yansıtıldı.");
                            }
                        }
                        else if(krediYonetimi.getKrediNotu() >= 800)
                        {
                            krediYonetimi.setKrediBorcu(faizHesapla(istenen,vade));
                            hesap.setPara(istenen);
                            System.out.println("Krediniz onaylandı ve hesabınıza yansıtıldı.");
                        }
                        else
                        {
                            System.err.println("MAALESEF KREDİ BAŞVURUNUZ REDDEDİLDİ.");
                        }
                    }
                    break;
                }
                case 5:
                {
                    if(krediYonetimi.getKrediBorcu() <= 0)
                    {
                        System.err.println("BORCUNUZ BULUNMAMAKTADIR");
                    }
                    else
                    {
                        System.out.print("Ödemek istediğiniz miktarı girin: ");
                        double odeme = key.nextDouble();

                        if(hesap.getPara() < odeme)
                        {
                            System.err.println("YETERSİZ BAKİYE");
                        }
                        else
                        {
                            if(odeme > krediYonetimi.getKrediBorcu())
                            {
                                System.err.println("YATIRDIĞINIZ PARA, BORCUNUZU GEÇİYOR...");
                            }
                            else
                            {
                                hesap.setPara(odeme * -1);
                                krediYonetimi.setKrediBorcu((odeme * -1));
                                System.out.println("Ödeme tamamlandı. \tKalan borç: " + krediYonetimi.getKrediBorcu());
                            }
                        }
                        if(krediYonetimi.getKrediBorcu() <= 0.09 && krediYonetimi.getKrediBorcu() >0)
                        {
                            krediYonetimi.yuvarlaKrediBorcu(0);
                        }
                    }
                    break;
                }
                case 6:
                {
                    System.out.print("\nMevcut şifre: ");
                    String mevcutSifre = key.next();
                    if(hesapYonetimi.getSifre().equals(mevcutSifre))
                    {
                        System.out.print("\nYeni şifre: ");
                        String yeniSifre1 = key.next();

                        System.out.print("Yeni şifreyi tekrar girin: ");
                        String yeniSifre2 = key.next();

                        if(yeniSifre1.equals(yeniSifre2))
                        {
                            String yeniSifre = yeniSifre1 = yeniSifre2;
                            hesapYonetimi.setSifre(yeniSifre);
                            System.out.println("\n\tŞİFRENİZ BAŞARIYLA DEĞİŞTİRİLDİ.");
                        }
                        else System.err.println("\n\tŞifreler uyuşmuyor.");
                    }
                    else System.err.println("\n\tMevcut şifre hatalı girildi.");
                    break;
                }
                default: System.err.println("HATALI SEÇİM"); break;
            }
        }
    }
    public static double faizHesapla(double para, double vade)
    {
        KrediYonetimi krediYonetimi = new KrediYonetimi();

        double taksit = para / vade;
        double aylikFaiz = ((taksit/100) * (krediYonetimi.getFaiz()/12) * (vade-1));
        double kkdf = ((taksit) * 0.15);
        double bsmv = ((taksit) * 0.05);
        double vergiler = ((kkdf + bsmv) * (vade-1));
        double sonuc = aylikFaiz + para + vergiler;
        return sonuc;
    }
}
