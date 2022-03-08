package emp.portal.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import emp.portal.entity.SkillSet;
import emp.portal.service.NotFoundException;


@Repository
public interface SkillSet_Repo  extends JpaRepository<SkillSet, String> {
@Transactional
//@Query(value="DELETE FROM skills WHERE title=?1",nativeQuery=true)
	void deleteByTitle(SkillSet title)throws NotFoundException;
	
	SkillSet findByTitle(String title);
	ArrayList<SkillSet> save(ArrayList<SkillSet> skillSet);
}