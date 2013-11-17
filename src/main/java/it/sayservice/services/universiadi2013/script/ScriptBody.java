package it.sayservice.services.universiadi2013.script;

import it.sayservice.platform.core.bus.service.connector.HTTPConnector;
import it.sayservice.platform.core.bus.service.transformer.ReaderToStringTransformer;
import it.sayservice.platform.core.common.exception.ServiceException;
import it.sayservice.services.universiadi2013.data.message.Data.Address;
import it.sayservice.services.universiadi2013.data.message.Data.Contact;
import it.sayservice.services.universiadi2013.data.message.Data.Coordinate;
import it.sayservice.services.universiadi2013.data.message.Data.Event;
import it.sayservice.services.universiadi2013.data.message.Data.KeyValue;
import it.sayservice.services.universiadi2013.data.message.Data.Location;
import it.sayservice.services.universiadi2013.data.message.Data.News;
import it.sayservice.services.universiadi2013.data.message.Data.Poi;
import it.sayservice.services.universiadi2013.data.message.Data.Venue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import au.com.bytecode.opencsv.CSVReader;

import com.google.protobuf.Message;

public class ScriptBody {
	private final transient Log log = LogFactory.getLog(this.getClass());
	
	public static final int NAME_IT = 0;
	public static final int NAME_EN = 1;
	public static final int DESC_IT = 2;
	public static final int DESC_EN = 3;
	public static final int CATEGORY = 4;
	public static final int LATITUDE = 5;
	public static final int LONGITUDE = 6;
	public static final int STREET = 7;
	public static final int POSTAL_CODE = 8;
	public static final int CITY = 9;
	public static final int CONTACT_NAME = 10;
	public static final int CONTACT_PHONE = 11;
	public static final int CONTACT_EMAIL = 12;
	public static final int CONTACT_FAX = 13;
	public static final int CONTACT_URL = 14;
	public static final int TIMETABLE_IT = 15;
	public static final int TIMETABLE_EN = 16;
	public static final int SERVICES_IT = 17;
	public static final int SERVICES_EN = 18;
	public static final int PRICE_IT = 19;
	public static final int PRICE_EN = 20;
	public static final int TAG_1 = 21;
	public static final int TAG_2 = 22;
	public static final int TAG_3 = 23;
	public static final int TAG_4 = 24;
	public static final int TAG_ADD = 25;
	public static final int CAPACITY_IT = 26;
	public static final int CAPACITY_EN = 27;
	public static final int STORY_IT = 28;
	public static final int STORY_EN = 29;
	public static final int ODDITY_IT = 30;
	public static final int ODDITY_EN = 31;
	public static final int ARRIVE_IT = 32;
	public static final int ARRIVE_EN = 33;
	public static final int ACCESSIBILITY_IT = 34;
	public static final int ACCESSIBILITY_EN = 35;
	
	public static final String categories = "noleggiosci;scuolasci;impiantosci;stadioghiaccio;stadiosalto;snowpark;"
	+ "camposportivo;campotennis;maneggio;piscina;palestra;disco;minigolf;wellness;parco;attrazione;museo;monumento;"
	+ "sitoarcheologico;teatro;luogoculto;pizzeria;ristorante;agriturismo;malga;birreria;bar;gelateria;"
	+ "hotel;residence;appartamentovacanze;affittocamere;bedbreakfast;garni;rifugio;camping;parcheggio;"
	+ "stazioneservizio;taxi;busnavetta;busfermata;autostazione;funivia;stazionetreni;serviziobici;noleggioprivato;"
	+ "servizisanitari;dentista;veterinario;farmacia;igienici;isoleecologiche;fontane;automoto;serviziabbigliamento;"
	+ "parrucchiera;istruzione;biblioteca;wifi;agenziaviaggi;info;forzeordine;servizimunicipali;posta;banca;"
	+ "prodottitipici;souvenir;mercato;antiquariato;cartoleria;tabacchino;centrocommerciale;negozioanimali;giocattoli;"
	+ "ottico;profumeria;gioielleria;abbigliamento;negoziosport;elettronica;ferramenta;casalinghi;alimentari;negozio";
	
	public List<Message> getPoiFromCsv(String csvFile) throws ServiceException {
		List<Message> result = new ArrayList<Message>();
		List<String> ids = new ArrayList<String>();
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream is = loader.getResourceAsStream("service/universiadi2013/" + csvFile);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
			StringBuffer sb = new StringBuffer();
			String line;
			while((line = bufferedReader.readLine()) != null) {
				sb.append(line+"\n");
			}
			String csv = sb.toString();
			
			String[] categoryList = categories.split(";");
			
			StringReader reader = new StringReader(csv);
			CSVReader csvReader = new CSVReader(reader, ',', '"', 1);
			List<String[]> lines = csvReader.readAll();
			int count = 0;
			for (String[] fields : lines) {
				count++;
				if(log.isInfoEnabled())
					log.info("line " + count);
				
				if((fields != null) && (fields.length >= 36)) {
					
					String name = fields[NAME_IT];
					if(isEmpty(name)) {
						log.warn("NameIT empty");
						continue;
					}
					
					String category = fields[CATEGORY];
					if(isEmpty(category)) {
						log.warn("empty category:" + name);
						continue;
					}
					category = category.toLowerCase().replace(" ", "");
					if(!containsCategory(category, categoryList)) {
						log.warn("unknown category:" + name);
						continue;
					}
					
					Location location = null;
					try {
						location = getLocation(fields);
					} catch (NumberFormatException e) {
						log.warn("Location not valid:" + fields[NAME_IT]);
						continue;
					}
					
					String id = getId(fields, location);
					if(ids.contains(id)) {
						log.warn("id duplicated:" + id);
						continue;
					}
					ids.add(id);
					
					KeyValue nameIt = getValue("IT", fields[NAME_IT]);
					KeyValue nameEn = getValue("EN", fields[NAME_EN]);
					
					KeyValue descIt = getHtmlDesc("IT", fields[DESC_IT]);
					KeyValue descEn = getHtmlDesc("EN", fields[DESC_EN]);
					
					KeyValue longDescIt = getLongDesc("IT", fields[STORY_IT], fields[ODDITY_IT], fields[ARRIVE_IT]);
					KeyValue longDescEn = getLongDesc("EN", fields[STORY_EN], fields[ODDITY_EN], fields[ARRIVE_EN]);
					
					KeyValue serviceIt = getHtmlDesc("IT", fields[SERVICES_IT]);
					KeyValue serviceEn = getHtmlDesc("EN", fields[SERVICES_EN]);
					
					List<String> topics = getTopics(fields);
					
					Contact contact = getContact(fields);
					
					KeyValue timeIt = getValue("IT", fields[TIMETABLE_IT]);
					KeyValue timeEn = getValue("EN", fields[TIMETABLE_EN]);
					
					KeyValue priceIt = getValue("IT", fields[PRICE_IT]);
					KeyValue priceEn = getValue("EN", fields[PRICE_EN]);

					KeyValue capacityIt = getValue("IT", fields[CAPACITY_IT]);
					KeyValue capacityEn = getValue("EN", fields[CAPACITY_EN]);

					KeyValue accessibilityIt = getValue("IT", fields[ACCESSIBILITY_IT]);
					KeyValue accessibilityEn = getValue("EN", fields[ACCESSIBILITY_EN]);

					Poi.Builder builder = Poi.newBuilder();
					builder.setId(id);
					builder.setCategory(category);
					builder.addName(nameIt);
					builder.addName(nameEn);
					builder.addDescription(descIt);
					builder.addDescription(descEn);
					
					if(!longDescIt.getValue().isEmpty())
						builder.addLongDescription(longDescIt);
					if(!longDescEn.getValue().isEmpty())
						builder.addLongDescription(longDescEn);
					
					if(!serviceIt.getValue().isEmpty())
						builder.addServiceDescription(serviceIt);
					if(!serviceEn.getValue().isEmpty())
						builder.addServiceDescription(serviceEn);
					
					builder.addAllTopic(topics);
					builder.setLocation(location);
					builder.setContact(contact);
					
					if(!timeIt.getValue().isEmpty())
						builder.addTimetable(timeIt);
					if(!timeEn.getValue().isEmpty())
						builder.addTimetable(timeEn);
					
					if(!priceIt.getValue().isEmpty())
						builder.addPrice(priceIt);
					if(!priceEn.getValue().isEmpty())
						builder.addPrice(priceEn);
					
					if(!capacityIt.getValue().isEmpty())
						builder.addSeatingCapacity(capacityIt);
					if(!capacityEn.getValue().isEmpty())
						builder.addSeatingCapacity(capacityEn);
					
					if(!accessibilityIt.getValue().isEmpty())
						builder.addAccessibility(accessibilityIt);
					if(!accessibilityEn.getValue().isEmpty())
						builder.addAccessibility(accessibilityEn);
					
					result.add(builder.build());
					
					if(log.isInfoEnabled()) {
						log.info("insert " + fields[NAME_IT]);
					}
				} else {
					if(log.isWarnEnabled()) {
						log.warn("numero di campi non conforme:" + fields);
					}
				}
			}
			csvReader.close();
			return result;
		} catch (Exception e) {
			log.error("error", e);
			throw new ServiceException(e.getMessage());
		} 
	}
	
	private boolean containsCategory(String value, String[] list) {
		for(String listvalue : list) {
			if(value.contains(listvalue))
				return true;
		}
		return false;
	}

	private KeyValue getValue(String key, String value) {
		KeyValue keyValue = KeyValue.newBuilder()
		.setKey(key)
		.setValue(value)
		.build();
		return keyValue;
	}
	
	private String getId(String[] fields, Location location) {
		return fields[NAME_IT] + "_" + location.getCoordinate().getLatitude() + "_" + location.getCoordinate().getLongitude();
	}
	
	private List<String> getTopics(String[] fields) {
		List<String> result = new ArrayList<String>();
		if(!isEmpty(fields[TAG_1]))
			result.add(fields[TAG_1]);
		if(!isEmpty(fields[TAG_2]))
			result.add(fields[TAG_2]);
		if(!isEmpty(fields[TAG_3]))
			result.add(fields[TAG_3]);
		if(!isEmpty(fields[TAG_4]))
			result.add(fields[TAG_4]);
		if(!isEmpty(fields[TAG_ADD])) {
			String[] tags = fields[TAG_ADD].split(",");
			result.addAll(Arrays.asList(tags));
		}
		return result;
	}
	
	private KeyValue getHtmlDesc(String key, String value) {
		String valueClened = StringEscapeUtils.unescapeHtml(value);
		valueClened = valueClened.replaceAll("\\<.*?>","");
		KeyValue keyValue = KeyValue.newBuilder()
		.setKey(key)
		.setValue(valueClened)
		.build();
		return keyValue;
	}
	
	private KeyValue getLongDesc(String key, String story, String oddity, String arrive) {
		String value = story;
		if(!oddity.isEmpty())
			value = value + "\n" + oddity;
		if(!arrive.isEmpty())
			value = value + "\n" + arrive;
		return getValue(key, value);
	}
	
	private Contact getContact(String[] fields) {
		Contact.Builder builder = Contact.newBuilder();
		
		KeyValue nameIt = getValue("IT", fields[CONTACT_NAME]);
		if(!nameIt.getValue().isEmpty())
			builder.addName(nameIt);
		
		if(!fields[CONTACT_PHONE].isEmpty())
			builder.setPhone(fields[CONTACT_PHONE]);
		
		if(!fields[CONTACT_FAX].isEmpty())
			builder.setFax(fields[CONTACT_FAX]);
		
		if(!fields[CONTACT_EMAIL].isEmpty())
			builder.setEmail(fields[CONTACT_EMAIL]);
		
		if(!fields[CONTACT_URL].isEmpty())
			builder.setUrl(fields[CONTACT_URL]);

		return builder.build();
	}
	
	private Location getLocation(String[] fields) throws NumberFormatException {
		Address addess = Address.newBuilder()
		.setStreet(fields[STREET])
		.setPostalCode(fields[POSTAL_CODE])
		.setCity(fields[CITY])
		.setLang("IT")
		.build();
		
		String lat = fields[LATITUDE];
		String lng = fields[LONGITUDE];
		if(isEmpty(lat) || isEmpty(lng))
			throw new NumberFormatException("empty value");
		double latitude = Double.parseDouble(lat.trim().replace("'", "").replace(",", "."));
		double longitude = Double.parseDouble(lng.trim().replace("'", "").replace(",", "."));
		Coordinate coordinate = Coordinate.newBuilder()
		.setLatitude(latitude)
		.setLongitude(longitude)
		.build();
		
		Location location = Location.newBuilder()
		.addAddress(addess)
		.setCoordinate(coordinate)
		.build();
		
		return location;
	}
	
	private String getValue(JsonNode node, String attrib) {
		String result = null;
		if(node.get(attrib) != null)
			result = node.get(attrib).asText();
		return result;
	}
	
	private boolean isEmpty(String value) {
		if(value == null)
			return true;
		return value.isEmpty();
	}
	
	public String getEventsUrl(Date time) {
		String url = "http://v4m-vps5.juniper-xs.it/v4web/uni2013?RefOwner=6A02F0E4B7EA457E90F11E341D60E444&&metatype=Event&";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		url = url + "lastupdate=" + sdf.format(time) + "&";
		if(log.isInfoEnabled())
			log.info(url);
		return url;
	}
	
	public String getNewsUrl(Date time) {
		String url = "http://v4m-vps5.juniper-xs.it/v4web/uni2013?RefOwner=6A02F0E4B7EA457E90F11E341D60E444&&metatype=News&";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		url = url + "lastupdate=" + sdf.format(time) + "&";
		if(log.isInfoEnabled())
			log.info(url);
		return url;
	}
	
	public String getComunicatiUrl(Date time) {
		String url = "http://v4m-vps5.juniper-xs.it/v4web/uni2013?RefOwner=6A02F0E4B7EA457E90F11E341D60E444&metatype=Comunicato&";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		url = url + "lastupdate=" + sdf.format(time) + "&";
		if(log.isInfoEnabled())
			log.info(url);
		return url;
	}
	
	public String getResultsUrl(Date time) {
		String url = "http://v4m-vps5.juniper-xs.it/v4web/uni2013?RefOwner=6A02F0E4B7EA457E90F11E341D60E444&metatype=Result&ATT=1&";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		url = url + "lastupdate=" + sdf.format(time) + "&";
		if(log.isInfoEnabled())
			log.info(url);
		return url;
	}
	
	public List<Message> getResults(String jsonIt, String jsonEn) throws Exception {
		List<Message> result = new ArrayList<Message>();
		Map<String, RowNews> mapNewsIt = new HashMap<String, RowNews>();
		Map<String, RowNews> mapNewsEn = new HashMap<String, RowNews>();
		ObjectMapper m = new ObjectMapper();
		//IT news
		JsonNode rootNodeIt;
		rootNodeIt = m.readValue(jsonIt, JsonNode.class);
		Iterator<JsonNode> elementsIt = rootNodeIt.getElements();
		while(elementsIt.hasNext()) {
			try {
				JsonNode node = elementsIt.next();
				RowNews news = getRowResult(node, "IT");
				if(news != null) {
					mapNewsIt.put(news.langGroup, news);
				}
			} catch (Exception e) {
				log.error("error", e);
			}
		}
		//EN news
		JsonNode rootNodeEn;
		rootNodeEn = m.readValue(jsonEn, JsonNode.class);
		Iterator<JsonNode> elementsEn = rootNodeEn.getElements();
		while(elementsEn.hasNext()) {
			try {
				JsonNode node = elementsEn.next();
				RowNews news = getRowResult(node, "EN");
				if(news != null) {
					mapNewsEn.put(news.langGroup, news);
				}
			} catch (Exception e) {
				log.error("error", e);
			}
		}
		Set<String> keysEn = mapNewsEn.keySet();
		Set<String> keysIt = mapNewsIt.keySet();
		for(String langGroup : keysIt) {
			RowNews newsIt = mapNewsIt.get(langGroup);
			RowNews newsEn = mapNewsEn.get(langGroup);
			if(newsEn != null) {
				result.add(getNews(newsIt, newsEn));
				keysEn.remove(langGroup);
			} else {
				result.add(getNews(newsIt, "IT"));
			}
		}
		for(String langGroup : keysEn) {
			RowNews newsEn = mapNewsEn.get(langGroup);
			result.add(getNews(newsEn, "EN"));
		}
		return result;
	}
	
	public List<Message> getComunicati(String jsonIt, String jsonEn) throws Exception {
		List<Message> result = new ArrayList<Message>();
		Map<String, RowNews> mapNewsIt = new HashMap<String, RowNews>();
		Map<String, RowNews> mapNewsEn = new HashMap<String, RowNews>();
		ObjectMapper m = new ObjectMapper();
		//IT news
		JsonNode rootNodeIt;
		rootNodeIt = m.readValue(jsonIt, JsonNode.class);
		Iterator<JsonNode> elementsIt = rootNodeIt.getElements();
		while(elementsIt.hasNext()) {
			try {
				JsonNode node = elementsIt.next();
				RowNews news = getRowNews(node, "IT");
				if(news != null) {
					mapNewsIt.put(news.langGroup, news);
				}
			} catch (Exception e) {
				log.error("error", e);
			}
		}
		//EN news
		JsonNode rootNodeEn;
		rootNodeEn = m.readValue(jsonEn, JsonNode.class);
		Iterator<JsonNode> elementsEn = rootNodeEn.getElements();
		while(elementsEn.hasNext()) {
			try {
				JsonNode node = elementsEn.next();
				RowNews news = getRowNews(node, "EN");
				if(news != null) {
					mapNewsEn.put(news.langGroup, news);
				}
			} catch (Exception e) {
				log.error("error", e);
			}
		}
		Set<String> keysEn = mapNewsEn.keySet();
		Set<String> keysIt = mapNewsIt.keySet();
		for(String langGroup : keysIt) {
			RowNews newsIt = mapNewsIt.get(langGroup);
			RowNews newsEn = mapNewsEn.get(langGroup);
			if(newsEn != null) {
				result.add(getNews(newsIt, newsEn));
				keysEn.remove(langGroup);
			} else {
				result.add(getNews(newsIt, "IT"));
			}
		}
		for(String langGroup : keysEn) {
			RowNews newsEn = mapNewsEn.get(langGroup);
			result.add(getNews(newsEn, "EN"));
		}
		return result;
	}
	
	public List<Message> getNews(String jsonIt, String jsonEn) throws Exception {
		List<Message> result = new ArrayList<Message>();
		Map<String, RowNews> mapNewsIt = new HashMap<String, RowNews>();
		Map<String, RowNews> mapNewsEn = new HashMap<String, RowNews>();
		ObjectMapper m = new ObjectMapper();
		//IT news
		JsonNode rootNodeIt;
		rootNodeIt = m.readValue(jsonIt, JsonNode.class);
		Iterator<JsonNode> elementsIt = rootNodeIt.getElements();
		while(elementsIt.hasNext()) {
			try {
				JsonNode node = elementsIt.next();
				RowNews news = getRowNews(node, "IT");
				if(news != null) {
					mapNewsIt.put(news.langGroup, news);
				}
			} catch (Exception e) {
				log.error("error", e);
			}
		}
		//EN news
		JsonNode rootNodeEn;
		rootNodeEn = m.readValue(jsonEn, JsonNode.class);
		Iterator<JsonNode> elementsEn = rootNodeEn.getElements();
		while(elementsEn.hasNext()) {
			try {
				JsonNode node = elementsEn.next();
				RowNews news = getRowNews(node, "EN");
				if(news != null) {
					mapNewsEn.put(news.langGroup, news);
				}
			} catch (Exception e) {
				log.error("error", e);
			}
		}
		Set<String> keysEn = mapNewsEn.keySet();
		Set<String> keysIt = mapNewsIt.keySet();
		for(String langGroup : keysIt) {
			RowNews newsIt = mapNewsIt.get(langGroup);
			RowNews newsEn = mapNewsEn.get(langGroup);
			if(newsEn != null) {
				result.add(getNews(newsIt, newsEn));
				keysEn.remove(langGroup);
			} else {
				result.add(getNews(newsIt, "IT"));
			}
		}
		for(String langGroup : keysEn) {
			RowNews newsEn = mapNewsEn.get(langGroup);
			result.add(getNews(newsEn, "EN"));
		}
		return result;
	}

	private Message getNews(RowNews news, String lang) {
		News.Builder builder = News.newBuilder();
		builder.setId(news.langGroup);
		
		KeyValue title = getValue(lang, news.title);
		builder.addTitle(title);
		
		KeyValue shordDesc = getValue(lang, news.shortDesc);
		builder.addShortDesc(shordDesc);
		
		builder.setCategory(news.category);
		builder.setPublished(news.publishDate + "T" + news.publishHour);
		
		if(news.contact != null) {
			builder.setContact(news.contact);
		}
		
		if(!isEmpty(news.longDescUrl)) {
			KeyValue longDesc = getLongDesc(news.longDescUrl, lang);
			if(longDesc != null)
				builder.addLongDesc(longDesc);
		} else {
			if(!isEmpty(news.longDesc)) {
				builder.addLongDesc(getValue(lang, news.longDesc));
			}
		}
		
		if(!isEmpty(news.url))
			builder.setUrl(news.url);
		
		if(!isEmpty(news.imageUrl))
			builder.setImageUrl(news.imageUrl);
		
		if(!isEmpty(news.poiId))
			builder.setPoiId(news.poiId);
		
		if(!isEmpty(news.eventId))
			builder.addEventId(news.eventId);
		
		if(!news.sports.isEmpty())
			builder.addAllSports(news.sports);
		
		return builder.build();
	}

	private News getNews(RowNews newsIt, RowNews newsEn) {
		News.Builder builder = News.newBuilder();
		builder.setId(newsIt.langGroup);
		
		KeyValue titleIt = getValue("IT", newsIt.title);
		KeyValue titleEn = getValue("EN", newsEn.title);
		builder.addTitle(titleIt);
		builder.addTitle(titleEn);

		KeyValue shordDescIt = getValue("IT", newsIt.shortDesc);
		KeyValue shordDescEn = getValue("EN", newsEn.shortDesc);
		builder.addShortDesc(shordDescIt);
		builder.addShortDesc(shordDescEn);
		
		builder.setCategory(newsIt.category);
		builder.setPublished(newsIt.publishDate + "T" + newsIt.publishHour);
		
		if(newsIt.contact != null) {
			Contact.Builder builderContact = Contact.newBuilder(newsIt.contact);
			builderContact.addAllName(newsEn.contact.getNameList());
			builder.setContact(builderContact.build());
		}
		
		if(!isEmpty(newsIt.longDescUrl)) {
			KeyValue longDescIt = getLongDesc(newsIt.longDescUrl, "IT");
			if(longDescIt != null)
				builder.addLongDesc(longDescIt);
		} else {
			if(!isEmpty(newsIt.longDesc)) {
				builder.addLongDesc(getValue("IT", newsIt.longDesc));
			}			
		}
		
		if(!isEmpty(newsEn.longDescUrl)) {
			KeyValue longDescEn = getLongDesc(newsEn.longDescUrl, "EN");
			if(longDescEn != null)
				builder.addLongDesc(longDescEn);
		} else {
			if(!isEmpty(newsEn.longDesc)) {
				builder.addLongDesc(getValue("EN", newsEn.longDesc));
			}			
		}
		
		if(!isEmpty(newsIt.url))
			builder.setUrl(newsIt.url);
		
		if(!isEmpty(newsIt.imageUrl))
			builder.setImageUrl(newsIt.imageUrl);
		
		if(!isEmpty(newsIt.poiId))
			builder.setPoiId(newsIt.poiId);
		
		if(!isEmpty(newsIt.eventId))
			builder.addEventId(newsIt.eventId);

		if(!newsIt.sports.isEmpty())
			builder.addAllSports(newsIt.sports);
		
		return builder.build();
	}

	private KeyValue getLongDesc(String longDescUrl, String lang) {
		try {
			if(longDescUrl.toLowerCase().equals("null"))
				return null;
			HTTPConnector connector = new HTTPConnector();
			connector.setUrl(longDescUrl);
			connector.setEncoding("iso-8859-1");
			ReaderToStringTransformer transformer = new ReaderToStringTransformer();
			String desc = transformer.transform(connector.run());
			KeyValue result = KeyValue.newBuilder()
			.setKey(lang)
			.setValue(desc)
			.build();
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	private RowNews getRowResult(JsonNode node, String lang) {
		RowNews news = new RowNews();
		String id = getValue(node, "OID");
		String category = getValue(node, "CATEGORIA");
		if(isEmpty(category)) {
			if(log.isWarnEnabled())
				log.warn("empty category:" + id);
			return null;
		}
		String langGroup = getValue(node, "LANGGROUP");
		String title = getValue(node, "TITOLO");
		String shortDesc = getValue(node, "DESCRIZIONE_BREVE");
		String publishDate = getValue(node, "DATA_PUBBLICAZIONE");
		String publishHour = getValue(node, "ORA_PUBBLICAZIONE");;
		String url = getValue(node, "WEBURL");
		String imageUrl = getValue(node, "IMAGE");
		String longDescUrl = getValue(node, "DESCRIZIONE_LUNGA");
		String poiId = getPoiId(node); //node.get("RIFERIMENTO_POI").asText();
		String eventId = getValue(node, "RIFERIMENTO_EVENTO");
		Contact contact = getContact(node, lang);
		List<String> sports = getListFromNode(node.get("SPORTS"));
		
		news.id = id;
		news.langGroup = langGroup;
		news.lang = lang;
		news.category = category;
		news.title = title;
		news.shortDesc = shortDesc;
		news.publishDate = publishDate;
		news.publishHour = publishHour;
		news.url = url;
		news.imageUrl = imageUrl;
		news.longDescUrl = longDescUrl;
		news.poiId = poiId;
		news.eventId = eventId;
		news.contact = contact;
		news.sports = sports;
		return news;
	}
	
	private RowNews getRowNews(JsonNode node, String lang) {
		RowNews news = new RowNews();
		String id = getValue(node, "OID");
		String category = getValue(node, "CATEGORIA");
		if(isEmpty(category)) {
			if(log.isWarnEnabled())
				log.warn("empty category:" + id);
			return null;
		}
		String langGroup = getValue(node, "LANGGROUP");
		String title = getValue(node, "TITOLO");
		String shortDesc = getValue(node, "DESCRIZIONE_BREVE");
		String publishDate = getValue(node, "DATA_PUBBLICAZIONE");
		String publishHour = getValue(node, "ORA_PUBBLICAZIONE");;
		String url = getValue(node, "WEBURL");
		if(!isEmpty(url)) {
			if(!url.toLowerCase().startsWith("http://"))
				url = "http://www.universiadetrentino.org" + url;
		}
		String imageUrl = getValue(node, "IMAGE");
		String longDescUrl = getValue(node, "DESCRIZIONE_LUNGA");
		String poiId = getPoiId(node); //node.get("RIFERIMENTO_POI").asText();
		String eventId = getValue(node, "RIFERIMENTO_EVENTO");
		Contact contact = getContact(node, lang);
		List<String> sports = getListFromNode(node.get("SPORTS"));
		
		news.id = id;
		news.langGroup = langGroup;
		news.lang = lang;
		news.category = category;
		news.title = title;
		news.shortDesc = shortDesc;
		news.publishDate = publishDate;
		news.publishHour = publishHour;
		news.url = url;
		news.imageUrl = imageUrl;
		news.longDescUrl = longDescUrl;
		news.poiId = poiId;
		news.eventId = eventId;
		news.contact = contact;
		news.sports = sports;
		return news;
	}
	
	private List<String> getListFromNode(JsonNode rootNode) {
		List<String> result = new ArrayList<String>();
		if(rootNode != null) {
			Iterator<JsonNode> elements = rootNode.getElements();
			while(elements.hasNext()) {
				JsonNode node = elements.next();
				result.add(node.asText());
			}
		}
		return result;
	}

	private String getPoiId(JsonNode node) {
		JsonNode location = node.get("LOCATION");
		if(location == null)
			return null;
		if(location.get("id") != null) {
			return location.get("id").asText();
		}
		return null;
	}
	
	private Location getLocation(JsonNode node, String lang) {
		JsonNode locationNode = node.get("LOCATION");
		if(locationNode == null)
			return null;
		
		String street = getValue(locationNode, "address");
		String postalCode = getValue(locationNode, "postalCode");
		String city = getValue(locationNode, "city");
		
		Address.Builder address = Address.newBuilder();
		if(!isEmpty(street)) {
			address.setStreet(street);
		}
		if(!isEmpty(postalCode)) {
			address.setPostalCode(postalCode);
		}
		if(!isEmpty(city)) {
			address.setCity(city);
		}
		address.setLang(lang);
		
		double latitude = locationNode.get("lat").asDouble();
		double longitude = locationNode.get("lng").asDouble();
		Coordinate coordinate = Coordinate.newBuilder()
		.setLatitude(latitude)
		.setLongitude(longitude)
		.build();
		
		Location location = Location.newBuilder()
		.addAddress(address)
		.setCoordinate(coordinate)
		.build();
		
		return location;
	}
	
	private Contact getContact(JsonNode node, String lang) {
		JsonNode contatto = node.get("CONTATTO");
		if(contatto == null)
			return null;
		
		Contact.Builder builder = Contact.newBuilder();
		
		String name = contatto.get("nome").asText();
		String phone = contatto.get("telefono").asText();
		String fax = contatto.get("fax").asText();
		String email = contatto.get("email").asText();
		String url = contatto.get("sitoweb").asText();
		
		if(!isEmpty(name)) {
			KeyValue nameIt = getValue(lang, name);
			builder.addName(nameIt);
		}
		if(!isEmpty(phone)) {
			builder.setPhone(phone);
		}
		if(!isEmpty(fax)) {
			builder.setFax(fax);
		}
		if(!isEmpty(email)) {
			builder.setEmail(email);
		}
		if(!isEmpty(url)) {
			builder.setUrl(url);
		}
				
		return builder.build();
	}

	public List<Message> getVenues(String json) throws Exception {
		List<Message> result = new ArrayList<Message>();
		ObjectMapper m = new ObjectMapper();
		JsonNode rootNode = m.readValue(json, JsonNode.class);
		Iterator<JsonNode> elements = rootNode.getElements();
		while(elements.hasNext()) {
			try {
				JsonNode node = elements.next();
				String id = node.get("id").asText();
				if(log.isInfoEnabled())
					log.info("venue:" + id);
				String nameIt = node.get("venueIt").asText();
				String nameEn = node.get("venueEn").asText();
				String descIt = node.get("descIt").asText();
				String descEn = node.get("descEn").asText();
				String imageUrl = node.get("imageUrl").asText();
				String category = node.get("category").asText();
				String[] tags = node.get("tags").asText().split(";");
				List<String> tagList = Arrays.asList(tags);
				Location location = getVenueLocation(node);
				
				Venue.Builder builder = Venue.newBuilder();
				builder.setId(id);
				builder.addName(getValue("IT", nameIt));
				builder.addName(getValue("EN", nameEn));
				if(!isEmpty(descIt))
					builder.addDescription(getValue("IT", descIt));
				if(!isEmpty(descEn))
					builder.addDescription(getValue("EN", descEn));
				if(!isEmpty(imageUrl))
					builder.setImageUrl(imageUrl);
				builder.setCategory(category);
				builder.setLocation(location);
				builder.addAllTag(tagList);
				result.add(builder.build());
			} catch (Exception e) {
				log.error("error", e);
			}
		}
		return result;
	}

	private Location getVenueLocation(JsonNode node) {
		double latitude = node.get("lat").asDouble();
		double longitude = node.get("lng").asDouble();
		
		String street = null;
		String postalCode = null;
		String city = null;
		
		if(node.has("address"))
				street = node.get("address").asText();
		if(node.has("postalCode"))
			postalCode = node.get("postalCode").asText();
		if(node.has("city"))
			city = node.get("city").asText();
		
		Coordinate coordinate = Coordinate.newBuilder()
		.setLatitude(latitude)
		.setLongitude(longitude)
		.build();
		
		Address.Builder address = Address.newBuilder();
		address.setLang("IT");
		if(!isEmpty(street))
			address.setStreet(street);
		if(!isEmpty(city))
			address.setCity(city);
		if(!isEmpty(postalCode))
			address.setPostalCode(postalCode);
		
		Location location = Location.newBuilder()
		.addAddress(address.build())
		.setCoordinate(coordinate)
		.build();
		
		return location;
	}

	public List<Message> getEvents(String jsonIt, String jsonEn) throws Exception {
		List<Message> result = new ArrayList<Message>();
		Map<String, RowEvent> mapEventIt = new HashMap<String, RowEvent>();
		Map<String, RowEvent> mapEventEn = new HashMap<String, RowEvent>();
		ObjectMapper m = new ObjectMapper();
		//IT news
		JsonNode rootNodeIt;
		rootNodeIt = m.readValue(jsonIt, JsonNode.class);
		Iterator<JsonNode> elementsIt = rootNodeIt.getElements();
		while(elementsIt.hasNext()) {
			try {
				JsonNode node = elementsIt.next();
				RowEvent event = getRowEvent(node, "IT");
				if(event != null) {
					mapEventIt.put(event.langGroup, event);
				}
			} catch (Exception e) {
				log.error("error", e);
			}
		}
		//EN news
		JsonNode rootNodeEn;
		rootNodeEn = m.readValue(jsonEn, JsonNode.class);
		Iterator<JsonNode> elementsEn = rootNodeEn.getElements();
		while(elementsEn.hasNext()) {
			try {
				JsonNode node = elementsEn.next();
				RowEvent event = getRowEvent(node, "EN");
				if(event != null) {
					mapEventEn.put(event.langGroup, event);
				}
			} catch (Exception e) {
				log.error("error", e);
			}
		}
		Set<String> keysEn = mapEventEn.keySet();
		Set<String> keysIt = mapEventIt.keySet();
		for(String langGroup : keysIt) {
			RowEvent eventIt = mapEventIt.get(langGroup);
			RowEvent eventEn = mapEventEn.get(langGroup);
			if(eventEn != null) {
				result.add(getEvent(eventIt, eventEn));
				keysEn.remove(langGroup);
			} else {
				result.add(getEvent(eventIt, "IT"));
			}
		}
		for(String langGroup : keysEn) {
			RowEvent eventEn = mapEventEn.get(langGroup);
			result.add(getEvent(eventEn, "EN"));
		}
		return result;
	}

	private Message getEvent(RowEvent eventIt, RowEvent eventEn) {
		Event.Builder builder = Event.newBuilder();
		builder.setId(eventIt.langGroup);
		
		KeyValue titleIt = getValue("IT", eventIt.title);
		KeyValue titleEn = getValue("EN", eventEn.title);
		builder.addTitle(titleIt);
		builder.addTitle(titleEn);

		KeyValue shordDescIt = getValue("IT", eventIt.shortDesc);
		KeyValue shordDescEn = getValue("EN", eventEn.shortDesc);
		builder.addShortDesc(shordDescIt);
		builder.addShortDesc(shordDescEn);
		
		builder.setCategory(eventIt.category);
		
		builder.setStartDate(eventIt.startDate + "T" + eventIt.startHour);
		builder.setEndDate(eventIt.endDate + "T" + eventIt.endHour);
		
		if(eventIt.contact != null) {
			Contact.Builder builderContact = Contact.newBuilder(eventIt.contact);
			builderContact.addAllName(eventEn.contact.getNameList());
			builder.setContact(builderContact.build());
		}
		
		if(!isEmpty(eventIt.longDescUrl)) {
			KeyValue longDescIt = getLongDesc(eventIt.longDescUrl, "IT");
			if(longDescIt != null)
				builder.addLongDesc(longDescIt);
		}
		
		if(!isEmpty(eventEn.longDescUrl)) {
			KeyValue longDescEn = getLongDesc(eventEn.longDescUrl, "EN");
			if(longDescEn != null)
				builder.addLongDesc(longDescEn);
		}
		
		if(!isEmpty(eventIt.url))
			builder.setUrl(eventIt.url);
		
		if(!isEmpty(eventIt.imageUrl))
			builder.setImageUrl(eventIt.imageUrl);
		
		if(!isEmpty(eventIt.poiId)) {
			builder.setPoiId(eventIt.poiId);
		} else if(eventIt.location != null) {
			builder.setLocation(eventIt.location);
		}
		
		if(!eventIt.sports.isEmpty())
			builder.addAllSports(eventIt.sports);
		
		return builder.build();
	}

	private Message getEvent(RowEvent event, String lang) {
		Event.Builder builder = Event.newBuilder();
		builder.setId(event.langGroup);
		
		KeyValue title = getValue(lang, event.title);
		builder.addTitle(title);
		
		KeyValue shordDesc = getValue(lang, event.shortDesc);
		builder.addShortDesc(shordDesc);
		
		builder.setCategory(event.category);
		
		builder.setStartDate(event.startDate + "T" + event.startHour);
		builder.setEndDate(event.endDate + "T" + event.endHour);
		
		if(event.contact != null) {
			builder.setContact(event.contact);
		}
		
		if(!isEmpty(event.longDescUrl)) {
			KeyValue longDesc = getLongDesc(event.longDescUrl, lang);
			if(longDesc != null)
				builder.addLongDesc(longDesc);
		}
		
		if(!isEmpty(event.url))
			builder.setUrl(event.url);
		
		if(!isEmpty(event.imageUrl))
			builder.setImageUrl(event.imageUrl);
		
		if(!isEmpty(event.poiId)) {
			builder.setPoiId(event.poiId);
		} else if(event.location != null) {
			builder.setLocation(event.location);
		}
		
		if(!event.sports.isEmpty())
			builder.addAllSports(event.sports);
		
		return builder.build();
	}

	private RowEvent getRowEvent(JsonNode node, String lang) {
		RowEvent event = new RowEvent();
		String id = getValue(node, "OID");
		String category = getValue(node, "CATEGORIA");
		if(isEmpty(category)) {
			if(log.isWarnEnabled())
				log.warn("empty category:" + id);
			return null;
		}
		if(log.isInfoEnabled())
			log.info("event:" + id);
		String langGroup = getValue(node, "LANGGROUP");
		String title = getValue(node, "TITOLO");
		String shortDesc = getValue(node, "DESCRIZIONE_BREVE");
		String startDate = getValue(node, "EVENTDATE");
		String startHour = getValue(node, "TIMESTART");
		String endDate = getValue(node, "EVENTEND");
		String endHour = getValue(node, "TIMEEND");
		String url = getValue(node, "WEBURL");
		String imageUrl = getValue(node, "IMAGE");
		String longDescUrl = getValue(node, "DESCRIZIONE_LUNGA");
		String poiId = getPoiId(node); //node.get("LOCATION").get("id").asText();
		Location location = getLocation(node, lang);
		Contact contact = getContact(node, lang);
		List<String> sports = getListFromNode(node.get("SPORTS"));
		
		event.id = id;
		event.langGroup = langGroup;
		event.lang = lang;
		event.category = category;
		event.title = title;
		event.shortDesc = shortDesc;
		event.startDate = startDate;
		event.startHour = startHour;
		event.endDate = endDate;
		event.endHour = endHour;
		event.url = url;
		event.imageUrl = imageUrl;
		event.longDescUrl = longDescUrl;
		event.poiId = poiId;
		event.contact = contact;
		event.sports = sports;
		event.location = location;
		return event;
	}
	


	public List<Message> getCompetitionSchedule(String json) throws Exception {
		List<Message> result = new ArrayList<Message>();
		ObjectMapper m = new ObjectMapper();
		JsonNode rootNode = m.readValue(json, JsonNode.class);
		Iterator<Entry<String, JsonNode>> sports = rootNode.getFields();
		while(sports.hasNext()) {
			Entry<String, JsonNode> sport = sports.next();
			String category = sport.getKey();
			if(log.isInfoEnabled())
				log.info(category);
			Iterator<JsonNode> disciplines = sport.getValue().getElements();
			while(disciplines.hasNext()) {
				JsonNode discipline = disciplines.next();
				Iterator<JsonNode> events = discipline.getElements();
				while(events.hasNext()) {
					try {
						Event event = getCompetitionEvent(events.next(), category);
						if(event != null)
							result.add(event);
					} catch (Exception e) {
						log.error("error", e);
					}
				}
			}
		}
		return result;
	}
	
	private Event getCompetitionEvent(JsonNode node, String category) {
		String id = node.get("id").asText();
		String title = node.get("id").asText();
		if(id == null) {
			if(log.isWarnEnabled())
				log.warn("empty id:" + title);
			return null;
		}
		if(log.isInfoEnabled())
			log.info("event id:" + id);
		String date = node.get("date").asText();
		String stime = node.get("stime").asText();
		String etime = node.get("etime").asText();
		String descIt = node.get("descIt").asText();
		String descEn = node.get("descEn").asText();
		String venue = node.get("venue").asText();
		
		Event.Builder builder = Event.newBuilder();
		builder.setId(id);
		
		KeyValue titleIt = getValue("IT", descIt);
		KeyValue titleEn = getValue("EN", descEn);
		builder.addTitle(titleIt);
		builder.addTitle(titleEn);

		KeyValue shordDescIt = getValue("IT", descIt);
		KeyValue shordDescEn = getValue("EN", descEn);
		builder.addShortDesc(shordDescIt);
		builder.addShortDesc(shordDescEn);
		
		builder.setCategory(category);
		
		builder.setStartDate(date + "T" + stime);
		builder.setEndDate(date + "T" + etime);
		
		if(!isEmpty(venue))
			builder.setPoiId(venue);
		
		return builder.build();
	}
	
	
}
