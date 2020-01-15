package dataStructure;

import java.io.Serializable;
import java.util.*;

import utils.Point3D;


public class DGraph implements graph, Serializable {


    private Map<Integer, node_data> nodes = new HashMap<Integer, node_data>();
    private List<edge_data> edges = new LinkedList<edge_data>();
    private int modifyCount = 0;


    public DGraph() {
        nodes = new HashMap<Integer, node_data>();
        edges = new LinkedList<edge_data>();
        modifyCount = 0;
    }

    public DGraph(DGraph d) {
        for (Integer key : d.nodes.keySet()) {
            node_data dNodeData = d.nodes.get(key);
            Node_Data nodeData = new Node_Data(
                    dNodeData.getKey(),
                    dNodeData.getWeight(),
                    new Point3D(dNodeData.getLocation().x(),
                            dNodeData.getLocation().y(),
                            dNodeData.getLocation().z()),
                    dNodeData.getInfo(),
                    dNodeData.getTag());
            this.nodes.put(key, nodeData);
        }
        for (int i = 0; i < d.edges.size(); i++) {
            edge_data edgeData = new Edge_Data(
                    d.edges.get(i).getSrc(),
                    d.edges.get(i).getDest(),
                    d.edges.get(i).getWeight(),
                    d.edges.get(i).getInfo(),
                    d.edges.get(i).getTag());
            this.edges.add(edgeData);
        }
    }

    public node_data getNode(int key) {
        return nodes.get(key);
    }


    @Override
    public edge_data getEdge(int src, int dest) {

        for (edge_data edge : edges) {

            if ((edge.getSrc() == src) && (edge.getDest() == dest)) {
                return edge;
            }
        }

        return null;

    }

    @Override
    public void addNode(node_data n) {
        nodes.put(n.getKey(), n);
        modifyCount++;
    }

    @Override
    public void connect(int src, int dest, double w) {

        if (nodes.containsKey(dest) && nodes.containsKey(src)) {
            nodes.get(dest).setWeight(src + w);
            edges.add(new Edge_Data(src, dest, w, null, 0));
        }

    }

    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        List<edge_data> srcEdges = new ArrayList<>();

        for (edge_data edgeData : this.edges) {
            if (edgeData.getSrc() == node_id) {
                srcEdges.add(edgeData);
            }
        }

        return srcEdges;
    }

    @Override
    public node_data removeNode(int key) {

        if (!nodes.containsKey(key)) {
            return null;
        }

        modifyCount++;


        node_data deleteNode = nodes.get(key);
        nodes.remove(key);

        edges.removeIf(edge -> (edge.getDest() == key || edge.getSrc() == key));

        return deleteNode;

    }

    @Override
    public edge_data removeEdge(int src, int dest) {

        edge_data deleteEdge = new Edge_Data(src, dest);

        if (!edges.contains(deleteEdge)) {
            return null;
        }

        modifyCount++;

        edges.remove(deleteEdge);

        return deleteEdge;
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int getMC() {
        return modifyCount;
    }


    public Iterator<node_data> iterator() {
        return nodes.values().iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DGraph other = (DGraph) obj;
        if (edges == null) {
            if (other.edges != null)
                return false;
        } else if (!edges.equals(other.edges))
            return false;
        if (nodes == null) {
            if (other.nodes != null)
                return false;
        } else if (!nodes.equals(other.nodes))
            return false;
        return true;
    }


}


