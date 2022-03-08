package emp.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import emp.portal.entity.SkillSet;
import emp.portal.repository.SkillSet_Repo;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	 private SkillSet_Repo repository;
	
	@Override
	public List<SkillSet> add(List<SkillSet> skillSet) {
		return repository.saveAll(skillSet);
		
	}
		
//		@Override
//		public List<SkillSet> getAll() {
//				return repository.findAll();
//			}
//
//
//		@Override
//		public SkillSet add(SkillSet skillSet) {
//				return repository.save(skillSet);
//			}
//	
	
	/*****************For HR DELETE*****************/
		//@Transactional
		@Override
		public void delete(SkillSet title)throws NotFoundException {
			if(repository.getById(title.getTitle())==null)
				throw new NotFoundException("title", "title", title.getTitle());
			repository.delete(title);
			
		}
		@Override
		public SkillSet findById(String title) {
		SkillSet skillSet=repository.findById(title).get();
			if(repository.findById(title)==null){
           throw new NotFoundException("title", "title", title);
			}
	return skillSet;
		}
		}
