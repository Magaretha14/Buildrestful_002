/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

/**
 *
 * @author ASUS
 */
public class Product {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDisc() {
        return disc;
    }

    public void setDisc(Double disc) {
        this.disc = disc;
    }

    public Double getTotal() {
        //total = price - (price * disc/100);
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    private String id;
    private String name;
    private Double price;
    private Double disc;
    private Double total;

    
}
