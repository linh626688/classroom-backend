package com.higgsup.fswd.classroommanager.model;

import java.util.HashMap;
import java.util.Map;

public abstract class HypermediaLinks {
    private Map<String, String> links = new HashMap<String, String>();

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }
}
