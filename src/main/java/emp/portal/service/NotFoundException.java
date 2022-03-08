package emp.portal.service;
public class NotFoundException extends RuntimeException {
private static final long serialVersionUID=1L;
private String resourceName;
private String fieldName;
private String fieldValue;

public NotFoundException(String resourceName, String fieldName, String fieldValue){
    super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName  =fieldName;
    this.fieldValue = fieldValue;
	}
}
