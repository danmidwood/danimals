
/**
 * Write a description of class InvalidSelectionException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InvalidSelectionException extends Exception
{
    Class invalidClass;
    
    InvalidSelectionException(String msg, Class invalidClass) {
        super(msg);
        this.invalidClass = invalidClass;
    }
    
    public Class getInvalidClass() {
        return invalidClass;
    }
}
