package com.nb.bigdata.configuratin.dto.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PredictResult implements Serializable {
    private Long id;

    private Long sentenceId;

    private String modelId;

    private String plateId;

    private Integer type;

    private BigDecimal confidenceRate;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Long sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId == null ? null : plateId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getConfidenceRate() {
        return confidenceRate;
    }

    public void setConfidenceRate(BigDecimal confidenceRate) {
        this.confidenceRate = confidenceRate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}