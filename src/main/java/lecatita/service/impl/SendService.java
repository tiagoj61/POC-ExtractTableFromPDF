package lecatita.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import lecatita.dao.interfc.ILineDao;
import lecatita.dto.request.BurdenRequest;
import lecatita.service.ILineService;
import lecatita.service.ISendService;
import lecatita.step.writer.state.model.Burden;

@Service
public class SendService implements ISendService {
	@Autowired
	private ILineDao dao;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void sendLineResult(String empresaId, String ano) {
		sendBurdens(castBurdenRequest(findAllBurdens(), empresaId, ano));
		clearBurdens();
	}

	private List<BurdenRequest> castBurdenRequest(List<Burden> burdens, String empresaId, String ano) {
		return findAllBurdens().stream().map(burden -> new BurdenRequest(empresaId, ano, burden.getName(),
				burden.getQuantity_male(), burden.getQuantity_female())).collect(Collectors.toList());
	}

	private List<Burden> findAllBurdens() {
		List<Burden> burdens = dao.findAll();
		burdens.forEach(burden->{
			
			burden.setQuantity_male(burden.getQuantity_male().replaceAll("\\.", ""));
		burden.setQuantity_female(burden.getQuantity_female().replaceAll("\\.", ""));
		}
		);
		return burdens;
	}

	private void sendBurdens(List<BurdenRequest> burdens) {
		ResponseEntity<JsonNode> response = restTemplate.exchange("http://localhost:9000/companies/"+burdens.get(0).getCompany_id()+"/response", HttpMethod.POST,
				new HttpEntity<>(burdens, new HttpHeaders()), JsonNode.class);
	}

	private void clearBurdens() {
		dao.clearTable();

	}
}
