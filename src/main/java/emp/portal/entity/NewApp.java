package emp.portal.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NewApp {
    @Id
    private long id;
    private String col1;
    private String col2;
    private int col3;

    public NewApp(){}

	public NewApp(long id, String col1, String col2, int col3) {
		this.id = id;
		this.col1 = col1;
		this.col2 = col2;
		this.col3 = col3;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public int getCol3() {
		return col3;
	}

	public void setCol3(int col3) {
		this.col3 = col3;
	}
}

