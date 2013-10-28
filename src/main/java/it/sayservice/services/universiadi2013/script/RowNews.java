package it.sayservice.services.universiadi2013.script;

import java.util.ArrayList;
import java.util.List;

import it.sayservice.services.universiadi2013.data.message.Data.Contact;

public class RowNews {
	public String lang;
	public String id;
	public String langGroup;
	public String category;
	public String title;
	public String shortDesc;
	public String longDesc;
	public String publishDate;
	public String publishHour;
	public String url;
	public String imageUrl;
	public String longDescUrl;
	public String poiId;
	public String eventId;
	public Contact contact;
	public List<String> sports = new ArrayList<String>();
	public List<String> tags = new ArrayList<String>();
}
