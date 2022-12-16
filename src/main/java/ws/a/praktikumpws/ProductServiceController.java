/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.a.praktikumpws;

import java.util.HashMap;
import java.util.Map;
import model2.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
public class ProductServiceController {
    
    //Tempat untuk meletakkan data product
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        //mengisi data product secara manual melalui code 
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        honey.setPrice(50000.0);
        honey.setDisc(10.0);
        honey.setTotal(honey.getPrice()-(honey.getPrice()*honey.getDisc()/100));
        productRepo.put(honey.getId(), honey);
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        almond.setPrice(120000.0);
        almond.setDisc(15.0);
        almond.setTotal(almond.getPrice()-(almond.getPrice()*almond.getDisc()/100));
        productRepo.put(almond.getId(), almond);
    }
    
    /*public Double Total(@PathVariable("id") String id, @RequestBody Product product){
        double total = product.getPrice() - (product.getPrice() * product.getDisc() / 100);
                
        return total;
    }*/
    
    //Menghapus data pada product
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        if(!productRepo.containsKey(id))
        {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
        else {
            productRepo.remove(id);
            return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
        }
    }
    
    //Menngedit data pada product
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        if(!productRepo.containsKey(id))
        {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
        else {
            productRepo.remove(id);
            product.setTotal(product.getPrice() - (product.getPrice()*product.getDisc()/100));
            product.setId(id);
            productRepo.put(id,product);
            return new ResponseEntity<>("Product is updates successfully", HttpStatus.OK);
        }
        
    }
    
    //Menambahkan data pada product
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        if(productRepo.containsKey(product.getId()))
        {
            return new ResponseEntity<>("Product Already Exist", HttpStatus.CONFLICT);
        }
        else {
            productRepo.put(product.getId(), product);
            product.setTotal(product.getPrice() - (product.getPrice()*product.getDisc()/100));
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
        }   
    }
    
    //Menampilkan data pada product
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    
    
}
