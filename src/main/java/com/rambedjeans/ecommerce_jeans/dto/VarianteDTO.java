package com.rambedjeans.ecommerce_jeans.dto;

public class VarianteDTO {
    private String referenciaId;
    private Integer colorId;
    private Integer tallaId;
    private Integer stock;
    
    


    public VarianteDTO() {
    }

    
    public VarianteDTO(String referenciaId, Integer colorId, Integer tallaId, Integer stock) {
        this.referenciaId = referenciaId;
        this.colorId = colorId;
        this.tallaId = tallaId;
        this.stock = stock;
    }


    public String getReferenciaId() {
        return referenciaId;
    }
    public void setReferenciaId(String referenciaId) {
        this.referenciaId = referenciaId;
    }
    public Integer getColorId() {
        return colorId;
    }
    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }
    public Integer getTallaId() {
        return tallaId;
    }
    public void setTallaId(Integer tallaId) {
        this.tallaId = tallaId;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }


    
    
}
