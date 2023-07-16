package it.polito.ezshop.model;


public class ProductType implements  it.polito.ezshop.data.ProductType {
       
    private Integer productID;
    private String barCode;
    private String description; 
    private Double sellPrice;
    private Integer quantity;
    private String notes;
    private String position;
	
    public ProductType (Integer productID, String barCode, String description, Double sellPrice, String notes, String position ){
           this.productID = productID;
		   this.barCode=barCode;
           this.description=description;
           this.sellPrice= sellPrice;
           this.notes=notes;
           this.position=position;
           this.quantity = 0;
    }
    
    public ProductType (Integer productID, String barCode, String description, Double sellPrice, Integer quantity, String notes, String position ){
        this.productID = productID;
		this.barCode=barCode;
        this.description=description;
        this.sellPrice= sellPrice;
        this.quantity=quantity;
        this.notes=notes;
        this.position=position;

 }

    @Override
	public Integer getQuantity() {
		return quantity;
	}

    @Override
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

   
    
    public String getBarCode(){
        return barCode;
    }
    @Override
    public void setBarCode(String barCode){
        this.barCode= barCode;
    }

	@Override
	public String getLocation() {
	
		return position;
	}
	@Override
	public void setLocation(String location) {
			this.position = location;
		
	}
	@Override
	public String getNote() {
        return notes;
	}
	@Override
	public void setNote(String note) {
		this.notes = note;
		
	}
	@Override
	public String getProductDescription() {
		return description;
        
	}
	@Override
	public void setProductDescription(String productDescription) {
	   this.description = productDescription;
		
	}
	@Override
	public Double getPricePerUnit() {
		
        return sellPrice;
	}
	@Override
	public void setPricePerUnit(Double pricePerUnit) {
		this.sellPrice = pricePerUnit;
		
	}
	@Override
	public Integer getId() {
		
		return productID;
	}
	@Override
	public void setId(Integer id) {
		this.productID = id;
		
	}

}
