package com.warehouse.model;

public class WareHouse {
    private String maSP;
    private String name;
    private int quantity;
    private double unitPrice;
    private String nsx;
    private String hsd;
    private String origin;
    private long soNgayConLai;

    public WareHouse(String maSP, String name, int quantity, double unitPrice, String nsx, String hsd, String origin, long soNgayConLai){
        this.maSP = maSP;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.nsx = nsx;
        this.hsd = hsd;
        this.origin = origin;
        this.soNgayConLai = soNgayConLai;
    }

    public WareHouse(String maSP, String name, int quantity, double unitPrice, String nsx, String hsd, String origin){
        this.maSP = maSP;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.nsx = nsx;
        this.hsd = hsd;
        this.origin = origin;
    }

    public WareHouse(){

    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public String getHsd() {
        return hsd;
    }

    public void setHsd(String hsd) {
        this.hsd = hsd;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public long getSoNgayConLai(){
        return soNgayConLai;
    }

    public void setSoNgayConLai(long soNgayConLai){
        this.soNgayConLai = soNgayConLai;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof WareHouse){
            WareHouse wareHouse1 = (WareHouse) o;
            if (wareHouse1.getName().equals(this.name)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public String toStringAll(){
        return "Th??ng tin: | " + "M??: " + String.format("%-8s|",maSP) + " T??n : " + String.format("%-16s|",name) + " S??? l?????ng: " + String.format("%-6s|",quantity) + " ????n gi??: " + String.format("%-12s|",unitPrice) + " Ngu???n g???c: " + String.format("%-12s|",origin) + " Ng??y s???n xu???t: " + String.format("%-11s|",nsx) + " H???n s??? d???ng: " + String.format("%-11s|",hsd);
    }

    public String toStringEXP(){
        return "Th??ng tin: | " + "M??: " + String.format("%-8s|",maSP) + " T??n: " + String.format("%-14s|",name) + " Ng??y s???n xu???t: " + String.format("%-11s|",nsx) + " H???n s??? d???ng: " + String.format("%-11s|",hsd) + " C??n l???i: " + String.format("%-5s",soNgayConLai) + " ng??y " + " |";
    }

    @Override
    public String toString() {
        return "Th??ng tin: | " + "M??: " + String.format("%-8s|",maSP) + " T??n : " + String.format("%-16s|",name) + " S??? l?????ng: " + String.format("%-6s|",quantity) + " ????n gi??: " + String.format("%-12s|",unitPrice) + " Ngu???n g???c: " + String.format("%-12s|",origin);
    }

    public String toStringCSV(){
        return maSP+","+name+","+quantity+","+unitPrice+","+nsx+","+hsd+","+origin+","+soNgayConLai+"\n";
    }
}
