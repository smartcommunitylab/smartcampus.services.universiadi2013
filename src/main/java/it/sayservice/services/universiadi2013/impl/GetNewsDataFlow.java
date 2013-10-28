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
public class GetNewsDataFlow implements ServiceDataFlow {

	Logger log = Logger.getLogger(this.getClass());


	private java.util.Date startingTime;
	private java.util.Date currentTime;
	private java.lang.String baseNewsUrl;
	private java.lang.String baseComunicatiUrl;
	private java.lang.String baseResultsUrl;
	private java.lang.String datiNewsJsonIt;
	private java.lang.String datiNewsJsonEn;
	private java.lang.String datiComunicatiJsonIt;
	private java.lang.String datiComunicatiJsonEn;
	private java.lang.String datiResultsJsonIt;
	private java.lang.String datiResultsJsonEn;

	private ServiceMethod serviceMethod;

	public GetNewsDataFlow () {
		super();
	}

	public void setServiceMethod(ServiceMethod serviceMethod) {
		this.serviceMethod = serviceMethod;	
	}

	public List<Message> invoke(String serviceExecutionId, Map<String, Object> parameters) throws DataFlowException {
		Map<String, Object> contextVariables = new java.util.HashMap<String,Object>();
	contextVariables.put("startingTime", startingTime);
	contextVariables.put("currentTime", currentTime);
	contextVariables.put("baseNewsUrl", baseNewsUrl);
	contextVariables.put("baseComunicatiUrl", baseComunicatiUrl);
	contextVariables.put("baseResultsUrl", baseResultsUrl);
	contextVariables.put("datiNewsJsonIt", datiNewsJsonIt);
	contextVariables.put("datiNewsJsonEn", datiNewsJsonEn);
	contextVariables.put("datiComunicatiJsonIt", datiComunicatiJsonIt);
	contextVariables.put("datiComunicatiJsonEn", datiComunicatiJsonEn);
	contextVariables.put("datiResultsJsonIt", datiResultsJsonIt);
	contextVariables.put("datiResultsJsonEn", datiResultsJsonEn);


		List<Message> output = new java.util.ArrayList<Message>();
		try {

		
		//Connector (HTTP)
		it.sayservice.platform.core.bus.service.connector.HTTPConnector newsJSON = new it.sayservice.platform.core.bus.service.connector.HTTPConnector();
		newsJSON.setSessionSupport("REQUIRED", null);
		newsJSON.setPost(false);
		newsJSON.setEncoding("iso-8859-1");

		//Set
		currentTime = (java.util.Date)InvokeScript.invoke("new Date()", contextVariables);
		contextVariables.put("currentTime", currentTime);

		//Set
		startingTime = (java.util.Date)InvokeScript.invoke("new Date(1381183200000L)", contextVariables);
		contextVariables.put("startingTime", startingTime);

		//Script
		{
		try {
			java.lang.String scriptResult1 = (java.lang.String)InvokeDataFlowScriptNode.invoke(it.sayservice.services.universiadi2013.script.ScriptBody.class, "getNewsUrl", "startingTime", contextVariables, serviceExecutionId, serviceMethod);
			baseNewsUrl = (java.lang.String)scriptResult1;
			contextVariables.put("baseNewsUrl", baseNewsUrl);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "baseNewsUrl", baseNewsUrl);
			} catch (Exception e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.TRANSFORMER_ERROR, e0);
			}
		}

		//Connect
		newsJSON.setUrl((String)InvokeScript.invoke("baseNewsUrl + \"lang=IT\"", contextVariables));
		try {
			InvokeConnector<java.io.Reader> newsJSONInvoker = new InvokeConnector<java.io.Reader>();
			java.io.Reader connectResult1 = newsJSONInvoker.invoke(newsJSON, "newsJSON", "datiNewsJsonIt", serviceExecutionId, serviceMethod);
			it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer connectTransformer1 = new it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer();
			datiNewsJsonIt = (java.lang.String)connectTransformer1.transform(connectResult1);
			contextVariables.put("datiNewsJsonIt", datiNewsJsonIt);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "datiNewsJsonIt", datiNewsJsonIt);
			} catch (ConnectorException e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.CONNECTION_ERROR, e0);
			} catch (TransformerException e1) {
				log.error("DataFlow Error: " + e1.getClass().getName());
				throw new DataFlowException(ExceptionMessage.CONNECTION_ERROR, e1);
			}

		//Connect
		newsJSON.setUrl((String)InvokeScript.invoke("baseNewsUrl + \"lang=EN\"", contextVariables));
		try {
			InvokeConnector<java.io.Reader> newsJSONInvoker = new InvokeConnector<java.io.Reader>();
			java.io.Reader connectResult2 = newsJSONInvoker.invoke(newsJSON, "newsJSON", "datiNewsJsonEn", serviceExecutionId, serviceMethod);
			it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer connectTransformer2 = new it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer();
			datiNewsJsonEn = (java.lang.String)connectTransformer2.transform(connectResult2);
			contextVariables.put("datiNewsJsonEn", datiNewsJsonEn);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "datiNewsJsonEn", datiNewsJsonEn);
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
			java.util.List<com.google.protobuf.Message> scriptResult2 = (java.util.List<com.google.protobuf.Message>)InvokeDataFlowScriptNode.invoke(it.sayservice.services.universiadi2013.script.ScriptBody.class, "getNews", "datiNewsJsonIt,datiNewsJsonEn", contextVariables, serviceExecutionId, serviceMethod);
			output.addAll(scriptResult2);
			contextVariables.put("output", output);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "output", output);
			} catch (Exception e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.TRANSFORMER_ERROR, e0);
			}
		}

		//Script
		{
		try {
			java.lang.String scriptResult3 = (java.lang.String)InvokeDataFlowScriptNode.invoke(it.sayservice.services.universiadi2013.script.ScriptBody.class, "getComunicatiUrl", "startingTime", contextVariables, serviceExecutionId, serviceMethod);
			baseComunicatiUrl = (java.lang.String)scriptResult3;
			contextVariables.put("baseComunicatiUrl", baseComunicatiUrl);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "baseComunicatiUrl", baseComunicatiUrl);
			} catch (Exception e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.TRANSFORMER_ERROR, e0);
			}
		}

		//Connect
		newsJSON.setUrl((String)InvokeScript.invoke("baseComunicatiUrl + \"lang=IT\"", contextVariables));
		try {
			InvokeConnector<java.io.Reader> newsJSONInvoker = new InvokeConnector<java.io.Reader>();
			java.io.Reader connectResult3 = newsJSONInvoker.invoke(newsJSON, "newsJSON", "datiComunicatiJsonIt", serviceExecutionId, serviceMethod);
			it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer connectTransformer3 = new it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer();
			datiComunicatiJsonIt = (java.lang.String)connectTransformer3.transform(connectResult3);
			contextVariables.put("datiComunicatiJsonIt", datiComunicatiJsonIt);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "datiComunicatiJsonIt", datiComunicatiJsonIt);
			} catch (ConnectorException e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.CONNECTION_ERROR, e0);
			} catch (TransformerException e1) {
				log.error("DataFlow Error: " + e1.getClass().getName());
				throw new DataFlowException(ExceptionMessage.CONNECTION_ERROR, e1);
			}

		//Connect
		newsJSON.setUrl((String)InvokeScript.invoke("baseComunicatiUrl + \"lang=EN\"", contextVariables));
		try {
			InvokeConnector<java.io.Reader> newsJSONInvoker = new InvokeConnector<java.io.Reader>();
			java.io.Reader connectResult4 = newsJSONInvoker.invoke(newsJSON, "newsJSON", "datiComunicatiJsonEn", serviceExecutionId, serviceMethod);
			it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer connectTransformer4 = new it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer();
			datiComunicatiJsonEn = (java.lang.String)connectTransformer4.transform(connectResult4);
			contextVariables.put("datiComunicatiJsonEn", datiComunicatiJsonEn);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "datiComunicatiJsonEn", datiComunicatiJsonEn);
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
			java.util.List<com.google.protobuf.Message> scriptResult4 = (java.util.List<com.google.protobuf.Message>)InvokeDataFlowScriptNode.invoke(it.sayservice.services.universiadi2013.script.ScriptBody.class, "getComunicati", "datiComunicatiJsonIt,datiComunicatiJsonEn", contextVariables, serviceExecutionId, serviceMethod);
			output.addAll(scriptResult4);
			contextVariables.put("output", output);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "output", output);
			} catch (Exception e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.TRANSFORMER_ERROR, e0);
			}
		}

		//Script
		{
		try {
			java.lang.String scriptResult5 = (java.lang.String)InvokeDataFlowScriptNode.invoke(it.sayservice.services.universiadi2013.script.ScriptBody.class, "getResultsUrl", "lastNewsUpdate", contextVariables, serviceExecutionId, serviceMethod);
			baseResultsUrl = (java.lang.String)scriptResult5;
			contextVariables.put("baseResultsUrl", baseResultsUrl);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "baseResultsUrl", baseResultsUrl);
			} catch (Exception e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.TRANSFORMER_ERROR, e0);
			}
		}

		//Connect
		newsJSON.setUrl((String)InvokeScript.invoke("baseResultsUrl + \"lang=IT\"", contextVariables));
		try {
			InvokeConnector<java.io.Reader> newsJSONInvoker = new InvokeConnector<java.io.Reader>();
			java.io.Reader connectResult5 = newsJSONInvoker.invoke(newsJSON, "newsJSON", "datiResultsJsonIt", serviceExecutionId, serviceMethod);
			it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer connectTransformer5 = new it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer();
			datiResultsJsonIt = (java.lang.String)connectTransformer5.transform(connectResult5);
			contextVariables.put("datiResultsJsonIt", datiResultsJsonIt);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "datiResultsJsonIt", datiResultsJsonIt);
			} catch (ConnectorException e0) {
				log.error("DataFlow Error: " + e0.getClass().getName());
				throw new DataFlowException(ExceptionMessage.CONNECTION_ERROR, e0);
			} catch (TransformerException e1) {
				log.error("DataFlow Error: " + e1.getClass().getName());
				throw new DataFlowException(ExceptionMessage.CONNECTION_ERROR, e1);
			}

		//Connect
		newsJSON.setUrl((String)InvokeScript.invoke("baseResultsUrl + \"lang=EN\"", contextVariables));
		try {
			InvokeConnector<java.io.Reader> newsJSONInvoker = new InvokeConnector<java.io.Reader>();
			java.io.Reader connectResult6 = newsJSONInvoker.invoke(newsJSON, "newsJSON", "datiResultsJsonEn", serviceExecutionId, serviceMethod);
			it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer connectTransformer6 = new it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer();
			datiResultsJsonEn = (java.lang.String)connectTransformer6.transform(connectResult6);
			contextVariables.put("datiResultsJsonEn", datiResultsJsonEn);
			InvokeVariableValidation.validate(serviceMethod, serviceExecutionId, "datiResultsJsonEn", datiResultsJsonEn);
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
			java.util.List<com.google.protobuf.Message> scriptResult6 = (java.util.List<com.google.protobuf.Message>)InvokeDataFlowScriptNode.invoke(it.sayservice.services.universiadi2013.script.ScriptBody.class, "getResults", "datiResultsJsonIt,datiResultsJsonEn", contextVariables, serviceExecutionId, serviceMethod);
			output.addAll(scriptResult6);
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
		return it.sayservice.services.universiadi2013.data.message.Data.News.newBuilder();
	}

	public void resetXSS() {
	}

}
