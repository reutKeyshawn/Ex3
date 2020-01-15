package dataStructure;

import utils.Point3D;

import java.io.Serializable;

public class Node_Data implements node_data, Serializable {


    private int key;
    private double weight;
    private Point3D location;
    private String info;
    private int tag;

    public Node_Data(int key, double weight, Point3D location, String info, int tag) {
        super();
        this.key = key;
        this.weight = weight;
        this.location = location;
        this.info = info;
        this.tag = tag;
    }


    public Node_Data(int key, Point3D location) {
        this.key = key;
        this.location = location;

        if (key == 1) {
            setWeight(0);
        } else {
            setWeight(Double.POSITIVE_INFINITY);
        }
        this.info = "Node's key (id) is:" + key + " Node's weight:" + weight + " Node's location:" + location + "Node's tag:" + tag;

    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public Point3D getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(Point3D p) {
        this.location = p;

    }


    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node_Data other = (Node_Data) obj;
        if (info == null) {
            if (other.info != null)
                return false;
        } else if (!info.equals(other.info))
            return false;
        if (key != other.key)
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (tag != other.tag)
            return false;
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
            return false;
        return true;
    }


}





