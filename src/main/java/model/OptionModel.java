package model;

import java.util.ArrayList;
import java.util.List;

public class OptionModel {
    private Long id;
    private String name;
    private Integer maximumAmount;
    private Boolean required;
    private Long productCategoryId;

    private List<Long> ingredients = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(Integer maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public List<Long> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Long> ingredients) {
        this.ingredients = ingredients;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    @Override
    public String toString() {
        return "OptionModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maximumAmount=" + maximumAmount +
                ", required=" + required +
                ", productCategoryId=" + productCategoryId +
                ", ingredients=" + ingredients +
                '}';
    }
}
