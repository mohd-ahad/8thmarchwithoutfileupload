package emp.portal.service;

import org.springframework.stereotype.Service;
import emp.portal.entity.AboutMe;


@Service
public interface AboutMeService {
	public AboutMe add(AboutMe abtMe);
	
}
