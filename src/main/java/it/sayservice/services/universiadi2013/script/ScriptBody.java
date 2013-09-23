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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public static final int CAPACITY_IT = 25;
	public static final int CAPACITY_EN = 26;
	public static final int STORY_IT = 27;
	public static final int STORY_EN = 28;
	public static final int ODDITY_IT = 29;
	public static final int ODDITY_EN = 30;
	public static final int ARRIVE_IT = 31;
	public static final int ARRIVE_EN = 32;
	public static final int ACCESSIBILITY_IT = 33;
	public static final int ACCESSIBILITY_EN = 34;
	
	public List<Message> getPoiFromCsv(String csv) throws ServiceException {
		List<Message> result = new ArrayList<Message>();
		try {
			StringReader reader = new StringReader(csv);
			CSVReader csvReader = new CSVReader(reader, ',', '"', '\\', 1);
			List<String[]> lines = csvReader.readAll();
			for (String[] fields : lines) {
				if((fields != null) && (fields.length >= 34)) {
					String id = getId(fields);
					
					String category = fields[CATEGORY];
					
					KeyValue nameIt = getValue("IT", fields[NAME_IT]);
					KeyValue nameEn = getValue("EN", fields[NAME_EN]);
					
					KeyValue descIt = getValue("IT", fields[DESC_IT]);
					KeyValue descEn = getValue("EN", fields[DESC_EN]);
					
					KeyValue longDescIt = getLongDesc("IT", fields[STORY_IT], fields[ODDITY_IT], fields[ARRIVE_IT]);
					KeyValue longDescEn = getLongDesc("EN", fields[STORY_EN], fields[ODDITY_EN], fields[ARRIVE_EN]);
					
					KeyValue serviceIt = getValue("IT", fields[SERVICES_IT]);
					KeyValue serviceEn = getValue("EN", fields[SERVICES_EN]);
					
					List<String> topics = getTopics(fields);
					
					Location location = null;
					try {
						location = getLocation(fields);
					} catch (NumberFormatException e) {
						log.warn("Location not valid:" + fields[NAME_IT]);
						continue;
					}
					
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

	private KeyValue getValue(String key, String value) {
		KeyValue keyValue = KeyValue.newBuilder()
		.setKey(key)
		.setValue(value)
		.build();
		return keyValue;
	}
	
	private String getId(String[] fields) {
		return fields[CATEGORY] + "." + fields[NAME_IT] + "." + fields[STREET] + "." + fields[POSTAL_CODE] + "." + fields[CITY];
	}
	
	private List<String> getTopics(String[] fields) {
		 List<String> result = new ArrayList<String>();
		 if(!fields[TAG_1].isEmpty())
			 result.add(fields[TAG_1]);
		 if(!fields[TAG_2].isEmpty())
			 result.add(fields[TAG_2]);
		 if(!fields[TAG_3].isEmpty())
			 result.add(fields[TAG_3]);
		 if(!fields[TAG_4].isEmpty())
			 result.add(fields[TAG_4]);
		return result;
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
		
		double latitude = Double.parseDouble(fields[LATITUDE]);
		double longitude = Double.parseDouble(fields[LONGITUDE]);
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
	
	private boolean isEmpty(String value) {
		if(value == null)
			return true;
		return value.isEmpty();
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
		}
		
		if(!isEmpty(news.url))
			builder.setUrl(news.url);
		
		if(!isEmpty(news.imageUrl))
			builder.setImageUrl(news.imageUrl);
		
		if(!isEmpty(news.poiId))
			builder.setPoiId(news.poiId);
		
		if(!isEmpty(news.eventId))
			builder.setEventId(news.eventId);
		
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
		}
		
		if(!isEmpty(newsEn.longDescUrl)) {
			KeyValue longDescEn = getLongDesc(newsEn.longDescUrl, "EN");
			if(longDescEn != null)
				builder.addLongDesc(longDescEn);
		}
		
		if(!isEmpty(newsIt.url))
			builder.setUrl(newsIt.url);
		
		if(!isEmpty(newsIt.imageUrl))
			builder.setImageUrl(newsIt.imageUrl);
		
		if(!isEmpty(newsIt.poiId))
			builder.setPoiId(newsIt.poiId);
		
		if(!isEmpty(newsIt.eventId))
			builder.setEventId(newsIt.eventId);
		
		return builder.build();
	}

	private KeyValue getLongDesc(String longDescUrl, String lang) {
		try {
			if(longDescUrl.toLowerCase().equals("null"))
				return null;
			HTTPConnector connector = new HTTPConnector();
			connector.setUrl(longDescUrl);
			connector.setEncoding("iso-8859-15");
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

	private RowNews getRowNews(JsonNode node, String lang) {
		RowNews news = new RowNews();
		String id = node.get("OID").asText();
		String category = node.get("CATEGORIA").asText();
		if(isEmpty(category)) {
			if(log.isWarnEnabled())
				log.warn("empty category:" + id);
			return null;
		}
		if(category.toLowerCase().equals("event"))
			return null;
		String langGroup = node.get("LANGGROUP").asText();
		String title = node.get("TITOLO").asText();
		String shortDesc = node.get("DESCRIZIONE_BREVE").asText();
		String publishDate = node.get("DATA_PUBBLICAZIONE").asText();
		String publishHour = node.get("ORA_PUBBLICAZIONE").asText();
		String url = node.get("URL_SITO_WEB").asText();
		String imageUrl = node.get("IMAGE").asText();
		String longDescUrl = node.get("DESCRIZIONE_LUNGA").asText();
		String poiId = node.get("RIFERIMENTO_POI").asText();
		String eventId = node.get("RIFERIMENTO_EVENTO").asText();
		Contact contact = getContact(node, lang);
		
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
		return news;
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
				String category = node.get("category").asText();
				String[] tags = node.get("tags").asText().split(";");
				List<String> tagList = Arrays.asList(tags);
				Location location = getVenueLocation(node);
				
				Venue.Builder builder = Venue.newBuilder();
				builder.setId(id);
				builder.addName(getValue("IT", nameIt));
				builder.addName(getValue("EN", nameEn));
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
		
		if(!isEmpty(eventIt.poiId))
			builder.setPoiId(eventIt.poiId);
		
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
		
		if(!isEmpty(event.poiId))
			builder.setPoiId(event.poiId);
		
		return builder.build();
	}

	private RowEvent getRowEvent(JsonNode node, String lang) {
		RowEvent event = new RowEvent();
		String id = node.get("OID").asText();
		String category = node.get("CATEGORIA").asText();
		if(isEmpty(category)) {
			if(log.isWarnEnabled())
				log.warn("empty category:" + id);
			return null;
		}
		if(!category.toLowerCase().equals("event"))
			return null;
		if(log.isInfoEnabled())
			log.info("event:" + id);
		String langGroup = node.get("LANGGROUP").asText();
		String title = node.get("TITOLO").asText();
		String shortDesc = node.get("DESCRIZIONE_BREVE").asText();
		String startDate = node.get("EVENTDATE").asText();
		String startHour = node.get("TIMESTART").asText();
		String endDate = node.get("EVENTEND").asText();
		String endHour = node.get("TIMEEND").asText();
		String url = node.get("URL_SITO_WEB").asText();
		String imageUrl = node.get("IMAGE").asText();
		String longDescUrl = node.get("DESCRIZIONE_LUNGA").asText();
		String poiId = node.get("LOCATION").get("id").asText();
		Contact contact = getContact(node, lang);
		
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
		return event;
	}

}
