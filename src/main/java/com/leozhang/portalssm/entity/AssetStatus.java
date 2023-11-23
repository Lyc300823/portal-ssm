package com.leozhang.portalssm.entity;

public class AssetStatus {
    private Long id;

    private String assetStatusNa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetStatusNa() {
        return assetStatusNa;
    }

    public void setAssetStatusNa(String assetStatusNa) {
        this.assetStatusNa = assetStatusNa == null ? null : assetStatusNa.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", assetStatusNa=").append(assetStatusNa);
        sb.append("]");
        return sb.toString();
    }
}