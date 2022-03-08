package emp.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emp.portal.entity.AboutMe;
import emp.portal.repository.AboutMe_Repo;

@Service
public class AboutMeServiceImpl implements AboutMeService {

	@Autowired
	public AboutMe_Repo repository;
	
	@Override
	public AboutMe add(AboutMe abtMe) {
		return repository.save(abtMe);	
	}
}
