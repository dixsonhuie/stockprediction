package com.gigaspaces.demo.common;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;

import java.util.Objects;

@SpaceClass
public class Prediction implements java.io.Serializable {
    private static final long serialVersionUID = 0L;

    private String id;
    private String label;
    private Integer cluster;
    private Boolean processed;

    public Prediction() {}

    public Prediction(String id, String label, Integer cluster) {
        this.id = id;
        this.label = label;
        this.cluster = cluster;
        this.processed = Boolean.FALSE;
    }


    @SpaceId(autoGenerate = false)
    @SpaceRouting
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getCluster() {
        return cluster;
    }

    public void setCluster(Integer cluster) {
        this.cluster = cluster;
    }

    public Boolean isProcessed() { return this.processed; }

    public void setProcessed(Boolean processed) {this.processed = processed; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prediction that = (Prediction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(label, that.label) &&
                Objects.equals(cluster, that.cluster) &&
                Objects.equals(processed, that.processed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, cluster, processed);
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", cluster=" + cluster +
                ", processed=" + processed +
                '}';
    }
}
