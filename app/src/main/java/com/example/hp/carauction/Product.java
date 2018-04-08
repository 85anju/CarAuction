package com.example.hp.carauction;

/**
 * Created by hp on 2/10/17.
 */


public class Product {

    String productid;

    String procompany;
    String  productname;


    String  avg;

    public void setBid1(Long bid1) {
        this.bid1 = bid1;
    }


    String  yrbuy;
    String typehand;

    Long price;


    String  sellerid;

    String b1;
    Long bid1;


    int soldout;
    int  biddone;


    public String getProductid() {
        return productid;
    }

    public String getProcompany() {
        return procompany;
    }

    public String getProductname() {
        return productname;
    }

    public String getAvg() {
        return avg;
    }

    public String getYrbuy() {
        return yrbuy;
    }

    public String getTypehand() {
        return typehand;
    }

    public Long getPrice() {
        return price;
    }

    public String getSellerid() {
        return sellerid;
    }

    public String getB1() {
        return b1;
    }


    public Long getBid1() {
        return bid1;
    }



    public int getSoldout() {
        return soldout;
    }

    public int getBiddone() {
        return biddone;
    }

    public Product()   {


    }

    public Product(String productid, String procompany, String productname, String avg, String yrbuy, String typehand, Long price, String sellerid, String b1, Long bid1, int soldout, int biddone) {
        this.productid = productid;
        this.procompany = procompany;
        this.productname = productname;
        this.avg = avg;
        this.yrbuy = yrbuy;
        this.typehand = typehand;
        this.price = price;
        this.sellerid = sellerid;
        this.b1 = b1;
        this.bid1 = bid1;
        this.soldout = soldout;
        this.biddone = biddone;
    }

   /* public Product(String b1, Long bid1,int biddone)
    {
        this.bid1=bid1;
        this.b1=b1;
        this.productid = getProductid();
        this.procompany =getProcompany();
        this.productname = getProductname();
        this.avg = getAvg();
        this.yrbuy = getYrbuy();
        this.typehand = getTypehand();
        this.price = getPrice();
        this.sellerid = getSellerid();

        this.soldout = getSoldout();
        this.biddone = biddone;




    }*/
}

