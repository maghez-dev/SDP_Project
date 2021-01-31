package project.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NodeDescriptor {
    private String id;
    private String ip;
    private String port;

    public NodeDescriptor() {}

    public NodeDescriptor(String id, String ip, String port) {
        this.id = id;
        this.ip = ip;
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "(id = " + this.id + ", port = " + this.port + ")";
    }
}
