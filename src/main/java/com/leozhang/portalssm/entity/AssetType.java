package com.leozhang.portalssm.entity;

public class AssetType {
    private Long id;

    private String assetTypeNam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetTypeNam() {
        return assetTypeNam;
    }

    public void setAssetTypeNam(String assetTypeNam) {
        this.assetTypeNam = assetTypeNam == null ? null : assetTypeNam.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", assetTypeNam=").append(assetTypeNam);
        sb.append("]");
        return sb.toString();
    }
}