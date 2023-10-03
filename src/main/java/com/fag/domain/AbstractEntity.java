package com.fag.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractEntity {

    protected Map<String, Object> attrs = new HashMap<>();

    public void put(String key, Object value) {
        if (this.attrs == null) {
            this.attrs = new LinkedHashMap<>();
        }

        this.attrs.put(key, value);
    }

    public <T> T get(String key) {
        return (T) attrs.get(key);
    }

    public <T> T get(String key, T def) {
        Object objAttrByKey = attrs.get(key);

        if (objAttrByKey == null) {
            return def;
        }

        if (objAttrByKey instanceof String strObjAttrByKey) {
            if (strObjAttrByKey.isEmpty()) {
                return def;
            }
        }

        return (T) objAttrByKey;
    }

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
    }

    public Object containsAttrs(String key) {
        if (this.attrs.containsKey(key)) {
            return this.attrs.get(key);
        }

        return null;
    }

    public boolean containsKey(String key) {
        if (this.attrs == null) {
            this.attrs = new LinkedHashMap<>();
        }

        return this.attrs.containsKey(key);
    }

    public void del(String key) {
        remove(key);
    }

    public void remove(String key) {
        try {
            this.attrs.remove(key);
        } catch (Exception ignored) {
        }
    }

}
