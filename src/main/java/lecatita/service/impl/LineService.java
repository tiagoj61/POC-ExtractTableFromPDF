package lecatita.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lecatita.dao.interfc.ILineDao;
import lecatita.service.ILineService;
import lecatita.step.writer.state.model.Burden;

@Repository
public class LineService implements ILineService {
	@Autowired
	private ILineDao dao;

	@Override
	public void insertBurdens(List<Burden> burdens) {
		burdens.forEach(burden -> {
			dao.insert(burden);
		});
	}

}
