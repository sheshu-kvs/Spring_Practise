package com.vendor.vendordetails.model.cloudVendor;

public class cloudmodel {
    private int  vendorid;
    
    private String  vendorname;
    private String vendoraddress;
    private int Phno;
    public cloudmodel() {
    }


    public int getVendorid() {
        return vendorid;
    }


    public void setVendorid(int vendorid) {
        this.vendorid = vendorid;
    }


    public String getVendorname() {
        return vendorname;
    }


    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }


    public String getVendoraddress() {
        return vendoraddress;
    }


    public void setVendoraddress(String vendoraddress) {
        this.vendoraddress = vendoraddress;
    }


    public int getPhno() {
        return Phno;
    }


    public void setPhno(int phno) {
        Phno = phno;
    }


    public cloudmodel(int vendorid, String vendorname, String vendoraddress, int phno) {
        this.vendorid = vendorid;
        this.vendorname = vendorname;
        this.vendoraddress = vendoraddress;
        Phno = phno;
    }
}
