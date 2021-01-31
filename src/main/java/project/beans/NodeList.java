package project.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NodeList {

    @XmlElement(name="nodeList")
    private ArrayList<NodeDescriptor> nodeList;
    private static NodeList instance;

    private NodeList() {
        nodeList = new ArrayList<NodeDescriptor>();
    }

    // Singleton
    public synchronized static NodeList getInstance(){
        if(instance==null)
            instance = new NodeList();
        return instance;
    }

    public synchronized ArrayList<NodeDescriptor> getNodeList() {
        return new ArrayList<>(nodeList);
    }

    public synchronized int size(){
        return nodeList.size();
    }

    // All synchronized because i can't risk to work on a list that has been modified while computing the operation
    public synchronized List<NodeDescriptor> add(NodeDescriptor toAdd) {
        List<NodeDescriptor> nodeDescriptors = getNodeList();
        boolean duplicate = false;
        for(NodeDescriptor n: nodeDescriptors)
            if(n.getId().equals(toAdd.getId()))
                duplicate = true;
        if(!duplicate) {
            nodeList.add(toAdd);
            return getNodeList();
        }
        else
            return null;
    }

    // All synchronized because i can't risk to work on a list that has been modified while computing the operation
    public synchronized void delete(int id) {
        NodeDescriptor nodeDescriptor = getById(""+id);
        for(int i = 0; i<nodeList.size(); i++) {
            if(nodeDescriptor.getId().equals(nodeList.get(i).getId())) {
                nodeList.remove(i);
                return;
            }
        }
    }

    // Not synchronized because it works on a local copy taken from a method with synchronized access
    public NodeDescriptor getById(String id){
        List<NodeDescriptor> nodeDescriptorCopy = getNodeList();
        for(NodeDescriptor nodeDescriptor : nodeDescriptorCopy)
            if(nodeDescriptor.getId().equals(id))
                return nodeDescriptor;
        return null;
    }

    // This method is mostly used on nodes
    public synchronized String getSuccessor(String id) {
        nodeList.sort(Comparator.comparing(NodeDescriptor::getId));
        // if there is only one node on the network
        if(nodeList.size() == 1) {
            return "localhost:" + nodeList.get(0).getPort();
        }
        else {
            // if the node has the highest id, rollback to position 0
            if(Integer.parseInt(nodeList.get(nodeList.size()-1).getId()) == Integer.parseInt(id)) {
                return "localhost:" + nodeList.get(0).getPort();
            }
            // find index of node with id=id and return his successor's url in the list
            else{
                for(int i=0; i<size(); i++)
                    if(Integer.parseInt(nodeList.get(i).getId()) == Integer.parseInt(id))
                        return "localhost:" + nodeList.get(i+1).getPort();
            }
        }
        return "localhost:" + nodeList.get(0).getPort();
    }
}
