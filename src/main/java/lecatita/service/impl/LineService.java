package lecatita.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lecatita.dao.interfc.ILineDao;
import lecatita.service.ILineService;
import lecatita.step.writer.state.model.Burden;

@Repository
public class LineService implements ILineService {
	private final String TOTAL = "total";
	@Autowired
	private ILineDao dao;

	@Override
	public void insertBurdens(List<Burden> burdens) {
		try {
		burdens.forEach(burden -> {
			
			if (!burden.getName().toLowerCase().equals("total")) {
				dao.insert(burden);
			}
			
		});
		}catch(Exception e) {
			System.out.println("erro");
		}
	}

}
