package com.tusharpatil.ecommapp.models;

public class ChildParentId {
    int parentId, childId;

    public ChildParentId(int parentId, int childId) {
        this.parentId = parentId;
        this.childId = childId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }
}
