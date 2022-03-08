package emp.portal.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(
        name = "Skills")
public class SkillSet {
//	@Id
//	@Column(
//    		name="id"
//    		)
//	@GeneratedValue //(strategy = GenerationType.IDENTITY)private long id;
	
	@Id
    @Column(
    		name="title"
    		)
    
    private String title;
	

	
	public SkillSet() {}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
//	
//	@ManyToOne(fetch=FetchType.EAGER)
//	private AboutMe aboutMe;
//
//
//
//	public AboutMe getAboutMe() {
//		return aboutMe;
//	}
//
//	public void setAboutMe(AboutMe aboutMe) {
//		this.aboutMe = aboutMe;
//	}
//	
}
