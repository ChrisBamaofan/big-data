package com.nb.bigdata.configuratin.dto.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PredictResult implements Serializable {
    private Long id;

    private Long sentenceId;

    private String modelId;

    private String positivePlateId;

    private String positivePlateName;

    private String negativePlateId;

    private String negativePlateName;

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

    public String getPositivePlateId() {
        return positivePlateId;
    }

    public void setPositivePlateId(String positivePlateId) {
        this.positivePlateId = positivePlateId == null ? null : positivePlateId.trim();
    }

    public String getPositivePlateName() {
        return positivePlateName;
    }

    public void setPositivePlateName(String positivePlateName) {
        this.positivePlateName = positivePlateName == null ? null : positivePlateName.trim();
    }

    public String getNegativePlateId() {
        return negativePlateId;
    }

    public void setNegativePlateId(String negativePlateId) {
        this.negativePlateId = negativePlateId == null ? null : negativePlateId.trim();
    }

    public String getNegativePlateName() {
        return negativePlateName;
    }

    public void setNegativePlateName(String negativePlateName) {
        this.negativePlateName = negativePlateName == null ? null : negativePlateName.trim();
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