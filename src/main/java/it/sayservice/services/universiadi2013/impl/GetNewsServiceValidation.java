package it.sayservice.services.universiadi2013.impl;

import it.sayservice.platform.core.bus.service.management.impl.AbstractServiceValidationImpl;
import it.sayservice.platform.core.bus.service.persistence.PersistenceEngine;
import it.sayservice.platform.core.bus.service.validation.Criterion;
import it.sayservice.platform.core.bus.service.validation.ServicePolicy;
import it.sayservice.platform.core.bus.service.validation.VariableUpdatePolicy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetNewsServiceValidation extends AbstractServiceValidationImpl {

	private String onAbort = "ignore";
	private String onException = "error";
	private ServicePolicy integrityPolicy = null;
	private List<VariableUpdatePolicy> onVariableUpdates =  new ArrayList<VariableUpdatePolicy>();
	private String serviceURI = null;
	private String operation = null;

	public GetNewsServiceValidation(String serviceId, String methodName, PersistenceEngine persistenceEngine) throws Exception {
		super(serviceId, methodName, persistenceEngine, "service/universiadi2013/validation-GetNews.xml");
		init();
	}

	public void init() throws Exception {
		parseXml();	
	}

	private void parseXml() throws Exception {
		Document doc = getValidationRules();
		if(doc == null) 
			return;

		doc.getDocumentElement().normalize();

		Element docElement = doc.getDocumentElement();
		serviceURI = docElement.getAttribute("serviceURI");
		operation = docElement.getAttribute("operation");
		
		String onAbortDoc = docElement.getAttribute("onAbort");
		if((onAbortDoc != null) && (!onAbortDoc.equals("")))
			onAbort = onAbortDoc;
		String onExceptionDoc = docElement.getAttribute("onException");
		if((onExceptionDoc != null) && (!onExceptionDoc.equals("")))
			onException = onExceptionDoc;

		NodeList integrityPolicyNodeList = doc.getElementsByTagName("integrityPolicy");
		if (integrityPolicyNodeList.getLength() > 0) {
			integrityPolicy = new ServicePolicy();
			Node integrityPolicyNode = integrityPolicyNodeList.item(0);
			if (integrityPolicyNode.getNodeType() == Node.ELEMENT_NODE) {
				Element integrityPolicyElement = (Element) integrityPolicyNode;
				String result = integrityPolicyElement.getAttribute("result");
				if (result != null)
					integrityPolicy.setResult(result);
				NodeList criterionNodeList = integrityPolicyElement.getElementsByTagName("criterion");
				if (criterionNodeList.getLength() > 0) {
					List<Criterion> criterionList = new ArrayList<Criterion>();
					for (int i = 0; i < criterionNodeList.getLength(); i++) {
						Criterion criterion = new Criterion();
						Node criterionNode = criterionNodeList.item(i);
						if (criterionNode.getNodeType() == Node.ELEMENT_NODE) {
							Element criterionElement = (Element) criterionNode;
							criterion.setLang(criterionElement.getAttribute("lang"));
							criterion.setBody(criterionElement.getTextContent().trim());
							criterionList.add(criterion);
						}
					}
					integrityPolicy.setCriterions(criterionList);
				}
			}
		}

		NodeList onVariableUpdateNodeList = doc.getElementsByTagName("onVariableUpdate");
		if (onVariableUpdateNodeList.getLength() > 0) {
			onVariableUpdates = new ArrayList<VariableUpdatePolicy>();
			for (int j = 0; j < onVariableUpdateNodeList.getLength(); j++) {
				VariableUpdatePolicy variableUpdatePolicy = new VariableUpdatePolicy();
				Node onVariableUpdateNode = onVariableUpdateNodeList.item(j);
				if (onVariableUpdateNode.getNodeType() == Node.ELEMENT_NODE) {
					Element onVariableUpdateElement = (Element) onVariableUpdateNode;
					String variable = onVariableUpdateElement.getAttribute("variable");
					variableUpdatePolicy.setVariable(variable);
					String action = onVariableUpdateElement.getAttribute("action");
					if (action != null)
						variableUpdatePolicy.setAction(action);
					NodeList criterionNodeList = onVariableUpdateElement.getElementsByTagName("criterion");
					if (criterionNodeList.getLength() > 0) {
						List<Criterion> criterionList = new ArrayList<Criterion>();
						for (int i = 0; i < criterionNodeList.getLength(); i++) {
							Criterion criterion = new Criterion();
							Node criterionNode = criterionNodeList.item(i);
							if (criterionNode.getNodeType() == Node.ELEMENT_NODE) {
								Element criterionElement = (Element) criterionNode;
								criterion.setLang(criterionElement.getAttribute("lang"));
								criterion.setBody(criterionElement.getTextContent().trim());
								criterionList.add(criterion);
							}
						}
						variableUpdatePolicy.setCriterions(criterionList);
					}
				}
				onVariableUpdates.add(variableUpdatePolicy);
			}
		}
	}

	/*
	 * Getters and setters
	 */
	@Override
	public String getOnAbort() {
		return onAbort;
	}

	@Override
	public String getOnException() {
		return onException;
	}

	@Override
	public ServicePolicy getIntegrityPolicy() {
		return integrityPolicy;
	}

	@Override
	public VariableUpdatePolicy getOnVariableUpdate(String variable) {
		for (VariableUpdatePolicy vup : onVariableUpdates) {
			if (vup.getVariable().equals(variable)) {
				return vup;
			}
		}
		return null;
	}

	@Override
	public Collection<VariableUpdatePolicy> getOnVariableUpdates() {
		return onVariableUpdates;
	}

}
