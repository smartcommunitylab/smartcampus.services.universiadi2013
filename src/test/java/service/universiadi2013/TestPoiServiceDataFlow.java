package service.universiadi2013;

import it.sayservice.platform.core.bus.common.exception.PersistenceException;
import it.sayservice.platform.core.common.exception.EntityNotFoundException;
import it.sayservice.platform.core.common.exception.ServiceException;
import it.sayservice.platform.servicebus.test.DataFlowTestHelper;
import it.sayservice.services.universiadi2013.impl.GetCompetitionScheduleDataFlow;
import it.sayservice.services.universiadi2013.impl.GetEventsDataFlow;
import it.sayservice.services.universiadi2013.impl.GetNewsDataFlow;
import it.sayservice.services.universiadi2013.impl.GetPoiFromCsvDataFlow;
import it.sayservice.services.universiadi2013.impl.GetVenuesDataFlow;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class TestPoiServiceDataFlow extends TestCase {
	
	public void testCsvFlow() throws ServiceException, EntityNotFoundException, PersistenceException {
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("csv", "APT_Pine_Cembra_Censimento_POI.csv");
		//params.put("csv", "APT_Valsugana_Censimento_POI.csv");
		//params.put("csv", "APT_Fassa_Censimento_POI.csv");
		params.put("csv", "APT_Trento_Censimento_POI.csv");
		DataFlowTestHelper helper = new DataFlowTestHelper();
		Map<String, Object> out = helper.executeDataFlow("it.sayservice.ext.universiadi2013", "GetPoiFromCsv", new GetPoiFromCsvDataFlow(), params);
		//System.out.println(out);
	}
	
	public void testNewsFlow() throws ServiceException, EntityNotFoundException, PersistenceException {
		Map<String, Object> params = new HashMap<String, Object>();
		DataFlowTestHelper helper = new DataFlowTestHelper();
		Map<String, Object> out = helper.executeDataFlow("it.sayservice.ext.universiadi2013", "GetNews", new GetNewsDataFlow(), params);
		System.out.println(out);
	}
	
	public void testVenuesFlow() throws ServiceException, EntityNotFoundException, PersistenceException {
		Map<String, Object> params = new HashMap<String, Object>();
		DataFlowTestHelper helper = new DataFlowTestHelper();
		Map<String, Object> out = helper.executeDataFlow("it.sayservice.ext.universiadi2013", "GetVenues", new GetVenuesDataFlow(), params);
		System.out.println(out);
	}
	
	public void testEventsFlow() throws ServiceException, EntityNotFoundException, PersistenceException {
		Map<String, Object> params = new HashMap<String, Object>();
		DataFlowTestHelper helper = new DataFlowTestHelper();
		Map<String, Object> out = helper.executeDataFlow("it.sayservice.ext.universiadi2013", "GetEvents", new GetEventsDataFlow(), params);
		System.out.println(out);
	}
	
	public void testScheduleFlow() throws ServiceException, EntityNotFoundException, PersistenceException {
		Map<String, Object> params = new HashMap<String, Object>();
		DataFlowTestHelper helper = new DataFlowTestHelper();
		Map<String, Object> out = helper.executeDataFlow("it.sayservice.ext.universiadi2013", "GetCompetitionSchedule", new GetCompetitionScheduleDataFlow(), params);
		System.out.println(out);
	}
}
