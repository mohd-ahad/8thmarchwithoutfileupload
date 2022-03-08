package emp.portal.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emp.portal.entity.NewApp;
import emp.portal.repository.NewAppRepository;

import java.util.List;

@Service
public class NewAppServiceImpl implements NewAppService {

    @Autowired
    private NewAppRepository repository;

	@Override
	public List<NewApp> getAll() {
		return repository.findAll();
	}

	@Override
	public NewApp getById(long id) {
		return repository.getById(id);
	}

	@Override
	public NewApp add(NewApp newApp) {
		return repository.save(newApp);
	}

	@Override
	public void update(long id, NewApp newApp) {
		repository.save(newApp);
	}

	@Override
	public void delete(long id) {
		repository.deleteById(id);
	}
}
