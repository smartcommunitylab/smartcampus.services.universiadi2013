package it.sayservice.services.universiadi2013.impl;

import it.sayservice.platform.core.bus.service.compiler.InvokeDataFlowScriptNode;
import it.sayservice.platform.core.bus.service.dataflow.ServiceDataFlow;
import java.util.ArrayList;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.w3c.dom.Document;
import bsh.Interpreter;
import bsh.EvalError;
import bsh.ParseException;
import bsh.TargetError;
import com.google.protobuf.Message;
import org.apache.log4j.Logger;
import it.sayservice.platform.core.bus.common.exception.*;
import javax.script.ScriptException;
import it.sayservice.platform.core.bus.service.ServiceMethod;
import it.sayservice.platform.core.i18n.ExceptionMessage;
import it.sayservice.platform.core.bus.service.compiler.InvokeGeolocalize;
import it.sayservice.platform.core.bus.service.compiler.InvokeScript;
import it.sayservice.platform.core.bus.service.compiler.InvokeMerge;
import it.sayservice.platform.core.bus.service.compiler.InvokeConstructor;
import it.sayservice.platform.core.bus.service.compiler.InvokeFilter;
import it.sayservice.platform.core.bus.service.compiler.InvokeTransformer;
import it.sayservice.platform.core.bus.service.compiler.InvokeVariableValidation;
import it.sayservice.platform.core.message.ProtoBean;


/**
*
* Generated DataFlow class: DO NOT EDIT!
*
*/
public class GetPoiFromCsvDataFlow implements ServiceDataFlow {

	Logger log = Logger.getLogger(this.getClass());



	private ServiceMethod serviceMethod;

	public GetPoiFromCsvDataFlow () {
		super();
	}

	public void setServiceMethod(ServiceMethod serviceMethod) {
		this.serviceMethod = serviceMethod;	
	}

	public List<Message> invoke(String serviceExecutionId, Map<String, Object> parameters) throws DataFlowException {
		Map<String, Object> contextVariables = new java.util.HashMap<String,Object>();


		List<Message> output = new java.util.ArrayList<Message>();
		java.lang.String csv = null;
		if (!(parameters.get("csv") instanceof java.lang.String)) {
				throw new DataFlowException(ExceptionMessage.PARAMETER_INCORRECT);
		} else {
				csv = (java.lang.String)parameters.get("csv");
		}
		contextVariables.put("csv", csv);
		InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "csv", csv);

		
		//Script
		{
		try {
			java.util.List<com.google.protobuf.Message> scriptResult1 = (java.util.List<com.google.protobuf.Message>)InvokeDataFlowScriptNode.invoke(it.sayservice.services.universiadi2013.script.ScriptBody.class, "getPoiFromCsv", "csv", contextVariables, serviceExecutionId, serviceMethod);
			output = (List<Message>)scriptResult1;
			contextVariables.put("output", output);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "output", output);
			} catch (Exception e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.TRANSFORMER_ERROR, e0);
			}
		}

	return output;
	}

	public Message.Builder getOutputBuilder() {
		return it.sayservice.services.universiadi2013.data.message.Data.Poi.newBuilder();
	}

	public void resetXSS() {
	}

}
