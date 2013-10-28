package it.sayservice.services.universiadi2013.script;

import java.util.ArrayList;
import java.util.List;

import it.sayservice.services.universiadi2013.data.message.Data.Contact;
import it.sayservice.services.universiadi2013.data.message.Data.Location;

public class RowEvent {
	public String lang;
	public String id;
	public String langGroup;
	public String category;
	public String title;
	public String shortDesc;
	public String startDate;
	public String startHour;
	public String endDate;
	public String endHour;
	public String url;
	public String imageUrl;
	public String longDescUrl;
	public String poiId;
	public Contact contact;
	public Location location;
	public List<String> sports = new ArrayList<String>();
	public List<String> tags = new ArrayList<String>();
}
