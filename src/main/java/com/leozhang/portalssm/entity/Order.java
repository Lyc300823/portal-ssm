package com.leozhang.portalssm.entity;

import java.util.Date;

public class Order {
    private Long id;

    private Long equipmentId;

    private Long publicUserId;

    private Long targetUserId;

    private Long orderStatusId;

    private Date insertTime;

    private String description;

    private String reason;

    private Date problemTime;

    private Date handleTime;

    private String handleResult;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getPublicUserId() {
        return publicUserId;
    }

    public void setPublicUserId(Long publicUserId) {
        this.publicUserId = publicUserId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getProblemTime() {
        return problemTime;
    }

    public void setProblemTime(Date problemTime) {
        this.problemTime = problemTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult == null ? null : handleResult.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", publicUserId=").append(publicUserId);
        sb.append(", targetUserId=").append(targetUserId);
        sb.append(", orderStatusId=").append(orderStatusId);
        sb.append(", insertTime=").append(insertTime);
        sb.append(", description=").append(description);
        sb.append(", reason=").append(reason);
        sb.append(", problemTime=").append(problemTime);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", handleResult=").append(handleResult);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}