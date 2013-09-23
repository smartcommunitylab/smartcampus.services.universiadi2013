package it.sayservice.services.universiadi2013.impl;

import it.sayservice.platform.core.bus.service.compiler.InvokeConnector;
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
public class GetEventsDataFlow implements ServiceDataFlow {

	Logger log = Logger.getLogger(this.getClass());


	private java.lang.String datiJsonIt;
	private java.lang.String datiJsonEn;

	private ServiceMethod serviceMethod;

	public GetEventsDataFlow () {
		super();
	}

	public void setServiceMethod(ServiceMethod serviceMethod) {
		this.serviceMethod = serviceMethod;	
	}

	public List<Message> invoke(String serviceExecutionId, Map<String, Object> parameters) throws DataFlowException {
		Map<String, Object> contextVariables = new java.util.HashMap<String,Object>();
	contextVariables.put("datiJsonIt", datiJsonIt);
	contextVariables.put("datiJsonEn", datiJsonEn);


		List<Message> output = new java.util.ArrayList<Message>();
		try {

		
		//Connector (HTTP)
		it.sayservice.platform.core.bus.service.connector.HTTPConnector newsJSON = new it.sayservice.platform.core.bus.service.connector.HTTPConnector();
		newsJSON.setSessionSupport("REQUIRED", null);
		newsJSON.setPost(false);
		newsJSON.setEncoding("UTF-8");

		//Connect
		newsJSON.setUrl((String)InvokeScript.invoke("\"http://v4m-vps5.juniper-xs.it/v4web/uni2013?RefOwner=6A02F0E4B7EA457E90F11E341D60E444&lang=IT\"", contextVariables));
		try {
			InvokeConnector<java.io.Reader> newsJSONInvoker = new InvokeConnector<java.io.Reader>();
			java.io.Reader connectResult1 = newsJSONInvoker.invoke(newsJSON, "newsJSON", "datiJsonIt", serviceExecutionId, serviceMethod);
			it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer connectTransformer1 = new it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer();
			datiJsonIt = (java.lang.String)connectTransformer1.transform(connectResult1);
			contextVariables.put("datiJsonIt", datiJsonIt);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "datiJsonIt", datiJsonIt);
			} catch (ConnectorException e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.CONNECTION_ERROR, e0);
			} catch (TransformerException e1) {
				log.error("DataFlow Error: " + e1.getClass().getName());
				throw new DataFlowException(ExceptionMessage.CONNECTION_ERROR, e1);
			}

		//Connect
		newsJSON.setUrl((String)InvokeScript.invoke("\"http://v4m-vps5.juniper-xs.it/v4web/uni2013?RefOwner=6A02F0E4B7EA457E90F11E341D60E444&lang=EN\"", contextVariables));
		try {
			InvokeConnector<java.io.Reader> newsJSONInvoker = new InvokeConnector<java.io.Reader>();
			java.io.Reader connectResult2 = newsJSONInvoker.invoke(newsJSON, "newsJSON", "datiJsonEn", serviceExecutionId, serviceMethod);
			it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer connectTransformer2 = new it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer();
			datiJsonEn = (java.lang.String)connectTransformer2.transform(connectResult2);
			contextVariables.put("datiJsonEn", datiJsonEn);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "datiJsonEn", datiJsonEn);
			} catch (ConnectorException e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.CONNECTION_ERROR, e0);
			} catch (TransformerException e1) {
				log.error("DataFlow Error: " + e1.getClass().getName());
				throw new DataFlowException(ExceptionMessage.CONNECTION_ERROR, e1);
			}

		//Script
		{
		try {
			java.util.List<com.google.protobuf.Message> scriptResult1 = (java.util.List<com.google.protobuf.Message>)InvokeDataFlowScriptNode.invoke(it.sayservice.services.universiadi2013.script.ScriptBody.class, "getEvents", "datiJsonIt,datiJsonEn", contextVariables, serviceExecutionId, serviceMethod);
			output = (List<Message>)scriptResult1;
			contextVariables.put("output", output);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "output", output);
			} catch (Exception e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.TRANSFORMER_ERROR, e0);
			}
		}

	return output;
	} catch (ParseException pe) {
		log.error("Script Error: Parse Exception.", pe);
		throw new DataFlowException(pe.getMessage(), pe);
	} catch (EvalError ee) {
		log.error("Script Error: Eval Error.", ee);
		throw new DataFlowException(ee.getMessage(), ee);
	} catch (Exception e) {
		if (e instanceof DataFlowException) {
			throw (DataFlowException)e;
		} else {
			log.error("Unexpected Error", e);
			throw new DataFlowException(e.getMessage(), e);
		}
	}
	}

	public Message.Builder getOutputBuilder() {
		return it.sayservice.services.universiadi2013.data.message.Data.Event.newBuilder();
	}

	public void resetXSS() {
	}

}
