package com.danmidwood.danimals;

/**
 * Abstract class com.danmidwood.danimals.Selection - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
abstract public class Selection extends java.util.TreeMap
{
    Class[] allowed = {String.class, Integer.class, Boolean.class};
    abstract public boolean needsChild();
    abstract public boolean needsPreselectedString();
    abstract public Object select(Population pop) throws Exception;

    public boolean hasParams() {
        return !isEmpty();
    }

    public java.util.Set getParams() {
        return keySet();
    }
    
    public Object getParamValue(Object key) {
        return get(key);
    }
    
    public java.lang.Class getParamClass(Object key) {
        return getParamValue(key).getClass();
    }
    
    public boolean ready() {
        java.util.Iterator it = keySet().iterator();
        while (it.hasNext()) {
            if (it.next() == null) return false;
        }
        return true;
    }
    
    public Object addParam(Object key, Object value) {
        for (int classIndex = 0; classIndex < allowed.length; classIndex++) {
            Class curClass = allowed[classIndex];
            if (curClass.isInstance(value))
                return put(key, value);
        }
        return put(key, null);
    }
    
    public void setParam(Object key, Object value) throws Exception{
        System.out.println( key + " set to " + value);
        Class whatItShouldBe = getParamClass(key);
        if (!whatItShouldBe.isInstance(value)) throw new Exception ("This value (" + value.getClass().getName() + ") is of wrong type. It should be " + whatItShouldBe.getName() + ".");
        if (containsKey(key)) {
            put(key, value);
        } else throw new Exception("Parameter not set : Name (" + key.toString() + ") invalid");
    }
    
}
