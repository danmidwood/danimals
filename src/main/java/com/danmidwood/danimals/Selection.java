package com.danmidwood.danimals;

abstract public class Selection extends java.util.TreeMap {
    Class[] allowed = {String.class, Integer.class, Boolean.class};

    abstract public boolean needsChild();

    abstract public boolean needsPreselectedString();

    abstract public Object select(Population pop) throws Exception;

    public boolean hasParams() {
        return !isEmpty();
    }

    public Object getParamValue(Object key) {
        return get(key);
    }

    public java.lang.Class getParamClass(Object key) {
        return getParamValue(key).getClass();
    }

    public boolean ready() {
        for (Object o : keySet()) {
            if (o == null) return false;
        }
        return true;
    }

    public Object addParam(Object key, Object value) {
        for (Class curClass : allowed) {
            if (curClass.isInstance(value))
                return put(key, value);
        }
        return put(key, null);
    }

    public void setParam(Object key, Object value) throws Exception {
        System.out.println(key + " set to " + value);
        Class whatItShouldBe = getParamClass(key);
        if (!whatItShouldBe.isInstance(value))
            throw new Exception("This value (" + value.getClass().getName() + ") is of wrong type. It should be " + whatItShouldBe.getName() + ".");
        if (containsKey(key)) {
            put(key, value);
        } else throw new Exception("Parameter not set : Name (" + key.toString() + ") invalid");
    }

}
