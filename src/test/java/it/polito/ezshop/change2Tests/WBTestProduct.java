package it.polito.ezshop.change2Tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezshop.model.Product;

public class WBTestProduct {
    

    @SuppressWarnings("unused")
	@Test
    public void testConstuctorsSettersGetters(){
       String rfID = "AVDEFGAPATYE";
	   String status = "SALE";
	   Integer pID = 012345000011;
	   Integer saleID = 10;
       Product p = new Product("AVAILABLEOIA", 012345000010, 15);
       Product p1 = new Product("AVAILABLEOIA", 012345000010,"AVAILABLE", 15);

       p.setRfID(rfID);
       p.setStatus(status);
       p.setpID(pID);
       p.setSaleID(saleID);

       assertTrue(rfID==p.getRfID() && status== p.getStatus() && pID==p.getpID() && saleID== p.getSaleID());
    }
}
