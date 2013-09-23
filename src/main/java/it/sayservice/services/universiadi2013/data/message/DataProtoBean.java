package it.sayservice.services.universiadi2013.data.message;

import java.util.List;
import java.util.LinkedList;

import it.sayservice.xss.api.XSSDataException;
import it.sayservice.xss.api.data.XSSData;
import it.sayservice.xss.api.data.DOMData;

import com.google.protobuf.ByteString;
import java.io.UnsupportedEncodingException;

import it.sayservice.platform.core.message.ProtoBean;
/**
*
* Generated ProtoBean class: DO NOT EDIT!
*
*/
public class DataProtoBean {
   public static class KeyValueProtoBean implements ProtoBean {
          private String key;
    public String getKey() {
      return key;
    }
    public void setKey(String key) {
      this.key = key;
    }
    
          private String value;
    public String getValue() {
      return value;
    }
    public void setValue(String value) {
      this.value = value;
    }
    
    
    public KeyValueProtoBean() {
    	super();
    }
    
    public KeyValueProtoBean(it.sayservice.services.universiadi2013.data.message.Data.KeyValue reference) {
      super();
                        setKey(reference.getKey());
                                    setValue(reference.getValue());
                      }  

    public KeyValueProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("key") != null && !data.get("key").isEmpty()) {
            if (data.get("key").size()>1) throw new XSSDataException("Incorrect data cardinality for field key: expected single value.");
            
            Object item = data.get("key").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field key: expected DOMData");
                                  setKey(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("value") != null && !data.get("value").isEmpty()) {
            if (data.get("value").size()>1) throw new XSSDataException("Incorrect data cardinality for field value: expected single value.");
            
            Object item = data.get("value").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field value: expected DOMData");
                                  setValue(convertToString(((DOMData)item).getStringValue()));
                                      }
                  }  

    
    public it.sayservice.services.universiadi2013.data.message.Data.KeyValue buildMessage() {
      it.sayservice.services.universiadi2013.data.message.Data.KeyValue .Builder builder = it.sayservice.services.universiadi2013.data.message.Data.KeyValue .newBuilder();
                        if (getKey() != null) {
      	builder.setKey(getKey());
      }
                                    if (getValue() != null) {
      	builder.setValue(getValue());
      }
                        return builder.buildPartial();
    }
    
    
  }
  public static class ImageProtoBean implements ProtoBean {
          private String imageUrl;
    public String getImageUrl() {
      return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> captionList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getCaptionList() {
      return captionList;
    }
    public void setCaptionList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> captionList) {
      this.captionList = captionList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getCaption(int i) {
      return captionList .get(i);
    }
    public int getCaptionCount() {
      return captionList .size();
    }
    
    
    public ImageProtoBean() {
    	super();
    }
    
    public ImageProtoBean(it.sayservice.services.universiadi2013.data.message.Data.Image reference) {
      super();
                        setImageUrl(reference.getImageUrl());
                                    if (reference.getCaptionList()!=null) {
        captionList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getCaptionList()) {
          getCaptionList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                      }  

    public ImageProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("imageUrl") != null && !data.get("imageUrl").isEmpty()) {
            if (data.get("imageUrl").size()>1) throw new XSSDataException("Incorrect data cardinality for field imageUrl: expected single value.");
            
            Object item = data.get("imageUrl").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field imageUrl: expected DOMData");
                                  setImageUrl(convertToString(((DOMData)item).getStringValue()));
                                      }
                                captionList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("caption")!=null) {
            for (Object item : data.get("caption")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field caption: expected XSSData");
                getCaptionList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                  }  

    
    public it.sayservice.services.universiadi2013.data.message.Data.Image buildMessage() {
      it.sayservice.services.universiadi2013.data.message.Data.Image .Builder builder = it.sayservice.services.universiadi2013.data.message.Data.Image .newBuilder();
                        if (getImageUrl() != null) {
      	builder.setImageUrl(getImageUrl());
      }
                              if (getCaptionList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getCaptionList()) {
                    builder. addCaption(item.buildMessage());
                  }
      }
                  return builder.buildPartial();
    }
    
    
  }
  public static class CoordinateProtoBean implements ProtoBean {
          private Double latitude;
    public Double getLatitude() {
      return latitude;
    }
    public void setLatitude(Double latitude) {
      this.latitude = latitude;
    }
    
          private Double longitude;
    public Double getLongitude() {
      return longitude;
    }
    public void setLongitude(Double longitude) {
      this.longitude = longitude;
    }
    
    
    public CoordinateProtoBean() {
    	super();
    }
    
    public CoordinateProtoBean(it.sayservice.services.universiadi2013.data.message.Data.Coordinate reference) {
      super();
                        setLatitude(reference.getLatitude());
                                    setLongitude(reference.getLongitude());
                      }  

    public CoordinateProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("latitude") != null && !data.get("latitude").isEmpty()) {
            if (data.get("latitude").size()>1) throw new XSSDataException("Incorrect data cardinality for field latitude: expected single value.");
            
            Object item = data.get("latitude").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field latitude: expected DOMData");
                                  setLatitude(convertToDouble(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("longitude") != null && !data.get("longitude").isEmpty()) {
            if (data.get("longitude").size()>1) throw new XSSDataException("Incorrect data cardinality for field longitude: expected single value.");
            
            Object item = data.get("longitude").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field longitude: expected DOMData");
                                  setLongitude(convertToDouble(((DOMData)item).getStringValue()));
                                      }
                  }  

    
    public it.sayservice.services.universiadi2013.data.message.Data.Coordinate buildMessage() {
      it.sayservice.services.universiadi2013.data.message.Data.Coordinate .Builder builder = it.sayservice.services.universiadi2013.data.message.Data.Coordinate .newBuilder();
                        if (getLatitude() != null) {
      	builder.setLatitude(getLatitude());
      }
                                    if (getLongitude() != null) {
      	builder.setLongitude(getLongitude());
      }
                        return builder.buildPartial();
    }
    
    
  }
  public static class ContactProtoBean implements ProtoBean {
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> nameList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getNameList() {
      return nameList;
    }
    public void setNameList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> nameList) {
      this.nameList = nameList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getName(int i) {
      return nameList .get(i);
    }
    public int getNameCount() {
      return nameList .size();
    }
    
          private String phone;
    public String getPhone() {
      return phone;
    }
    public void setPhone(String phone) {
      this.phone = phone;
    }
    
          private String fax;
    public String getFax() {
      return fax;
    }
    public void setFax(String fax) {
      this.fax = fax;
    }
    
          private String email;
    public String getEmail() {
      return email;
    }
    public void setEmail(String email) {
      this.email = email;
    }
    
          private String url;
    public String getUrl() {
      return url;
    }
    public void setUrl(String url) {
      this.url = url;
    }
    
    
    public ContactProtoBean() {
    	super();
    }
    
    public ContactProtoBean(it.sayservice.services.universiadi2013.data.message.Data.Contact reference) {
      super();
                        if (reference.getNameList()!=null) {
        nameList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getNameList()) {
          getNameList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    setPhone(reference.getPhone());
                                    setFax(reference.getFax());
                                    setEmail(reference.getEmail());
                                    setUrl(reference.getUrl());
                      }  

    public ContactProtoBean(XSSData data) throws XSSDataException {
      super();
                        nameList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("name")!=null) {
            for (Object item : data.get("name")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field name: expected XSSData");
                getNameList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                if (data.get("phone") != null && !data.get("phone").isEmpty()) {
            if (data.get("phone").size()>1) throw new XSSDataException("Incorrect data cardinality for field phone: expected single value.");
            
            Object item = data.get("phone").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field phone: expected DOMData");
                                  setPhone(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("fax") != null && !data.get("fax").isEmpty()) {
            if (data.get("fax").size()>1) throw new XSSDataException("Incorrect data cardinality for field fax: expected single value.");
            
            Object item = data.get("fax").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field fax: expected DOMData");
                                  setFax(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("email") != null && !data.get("email").isEmpty()) {
            if (data.get("email").size()>1) throw new XSSDataException("Incorrect data cardinality for field email: expected single value.");
            
            Object item = data.get("email").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field email: expected DOMData");
                                  setEmail(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("url") != null && !data.get("url").isEmpty()) {
            if (data.get("url").size()>1) throw new XSSDataException("Incorrect data cardinality for field url: expected single value.");
            
            Object item = data.get("url").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field url: expected DOMData");
                                  setUrl(convertToString(((DOMData)item).getStringValue()));
                                      }
                  }  

    
    public it.sayservice.services.universiadi2013.data.message.Data.Contact buildMessage() {
      it.sayservice.services.universiadi2013.data.message.Data.Contact .Builder builder = it.sayservice.services.universiadi2013.data.message.Data.Contact .newBuilder();
                  if (getNameList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getNameList()) {
                    builder. addName(item.buildMessage());
                  }
      }
                              if (getPhone() != null) {
      	builder.setPhone(getPhone());
      }
                                    if (getFax() != null) {
      	builder.setFax(getFax());
      }
                                    if (getEmail() != null) {
      	builder.setEmail(getEmail());
      }
                                    if (getUrl() != null) {
      	builder.setUrl(getUrl());
      }
                        return builder.buildPartial();
    }
    
    
  }
  public static class AddressProtoBean implements ProtoBean {
          private String lang;
    public String getLang() {
      return lang;
    }
    public void setLang(String lang) {
      this.lang = lang;
    }
    
          private String street;
    public String getStreet() {
      return street;
    }
    public void setStreet(String street) {
      this.street = street;
    }
    
          private String postalCode;
    public String getPostalCode() {
      return postalCode;
    }
    public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
    }
    
          private String city;
    public String getCity() {
      return city;
    }
    public void setCity(String city) {
      this.city = city;
    }
    
    
    public AddressProtoBean() {
    	super();
    }
    
    public AddressProtoBean(it.sayservice.services.universiadi2013.data.message.Data.Address reference) {
      super();
                        setLang(reference.getLang());
                                    setStreet(reference.getStreet());
                                    setPostalCode(reference.getPostalCode());
                                    setCity(reference.getCity());
                      }  

    public AddressProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("lang") != null && !data.get("lang").isEmpty()) {
            if (data.get("lang").size()>1) throw new XSSDataException("Incorrect data cardinality for field lang: expected single value.");
            
            Object item = data.get("lang").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field lang: expected DOMData");
                                  setLang(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("street") != null && !data.get("street").isEmpty()) {
            if (data.get("street").size()>1) throw new XSSDataException("Incorrect data cardinality for field street: expected single value.");
            
            Object item = data.get("street").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field street: expected DOMData");
                                  setStreet(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("postalCode") != null && !data.get("postalCode").isEmpty()) {
            if (data.get("postalCode").size()>1) throw new XSSDataException("Incorrect data cardinality for field postalCode: expected single value.");
            
            Object item = data.get("postalCode").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field postalCode: expected DOMData");
                                  setPostalCode(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("city") != null && !data.get("city").isEmpty()) {
            if (data.get("city").size()>1) throw new XSSDataException("Incorrect data cardinality for field city: expected single value.");
            
            Object item = data.get("city").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field city: expected DOMData");
                                  setCity(convertToString(((DOMData)item).getStringValue()));
                                      }
                  }  

    
    public it.sayservice.services.universiadi2013.data.message.Data.Address buildMessage() {
      it.sayservice.services.universiadi2013.data.message.Data.Address .Builder builder = it.sayservice.services.universiadi2013.data.message.Data.Address .newBuilder();
                        if (getLang() != null) {
      	builder.setLang(getLang());
      }
                                    if (getStreet() != null) {
      	builder.setStreet(getStreet());
      }
                                    if (getPostalCode() != null) {
      	builder.setPostalCode(getPostalCode());
      }
                                    if (getCity() != null) {
      	builder.setCity(getCity());
      }
                        return builder.buildPartial();
    }
    
    
  }
  public static class LocationProtoBean implements ProtoBean {
          private it.sayservice.services.universiadi2013.data.message.DataProtoBean.CoordinateProtoBean coordinate;
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.CoordinateProtoBean getCoordinate() {
      return coordinate;
    }
    public void setCoordinate(it.sayservice.services.universiadi2013.data.message.DataProtoBean.CoordinateProtoBean coordinate) {
      this.coordinate = coordinate;
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.AddressProtoBean> addressList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.AddressProtoBean> getAddressList() {
      return addressList;
    }
    public void setAddressList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.AddressProtoBean> addressList) {
      this.addressList = addressList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.AddressProtoBean getAddress(int i) {
      return addressList .get(i);
    }
    public int getAddressCount() {
      return addressList .size();
    }
    
    
    public LocationProtoBean() {
    	super();
    }
    
    public LocationProtoBean(it.sayservice.services.universiadi2013.data.message.Data.Location reference) {
      super();
                        setCoordinate(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.CoordinateProtoBean(reference.getCoordinate()));
                                    if (reference.getAddressList()!=null) {
        addressList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.AddressProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.Address item : reference.getAddressList()) {
          getAddressList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.AddressProtoBean(item));
        }
      }
                      }  

    public LocationProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("coordinate") != null && !data.get("coordinate").isEmpty()) {
            if (data.get("coordinate").size()>1) throw new XSSDataException("Incorrect data cardinality for field coordinate: expected single value.");
            
            Object item = data.get("coordinate").get(0); 
                          if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field coordinate: expected XSSData");
              setCoordinate(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.CoordinateProtoBean((XSSData)item));
                      }
                                addressList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.AddressProtoBean>();
          if (data.get("address")!=null) {
            for (Object item : data.get("address")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field address: expected XSSData");
                getAddressList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.AddressProtoBean((XSSData)item));
                          }
          }
                  }  

    
    public it.sayservice.services.universiadi2013.data.message.Data.Location buildMessage() {
      it.sayservice.services.universiadi2013.data.message.Data.Location .Builder builder = it.sayservice.services.universiadi2013.data.message.Data.Location .newBuilder();
                        if (getCoordinate() != null) {
      	builder.setCoordinate(getCoordinate() .buildMessage());
      }
                              if (getAddressList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.AddressProtoBean item : getAddressList()) {
                    builder. addAddress(item.buildMessage());
                  }
      }
                  return builder.buildPartial();
    }
    
    
  }
  public static class PoiProtoBean implements ProtoBean {
          private String id;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    
          private String category;
    public String getCategory() {
      return category;
    }
    public void setCategory(String category) {
      this.category = category;
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> nameList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getNameList() {
      return nameList;
    }
    public void setNameList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> nameList) {
      this.nameList = nameList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getName(int i) {
      return nameList .get(i);
    }
    public int getNameCount() {
      return nameList .size();
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> descriptionList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getDescriptionList() {
      return descriptionList;
    }
    public void setDescriptionList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> descriptionList) {
      this.descriptionList = descriptionList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getDescription(int i) {
      return descriptionList .get(i);
    }
    public int getDescriptionCount() {
      return descriptionList .size();
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> longDescriptionList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getLongDescriptionList() {
      return longDescriptionList;
    }
    public void setLongDescriptionList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> longDescriptionList) {
      this.longDescriptionList = longDescriptionList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getLongDescription(int i) {
      return longDescriptionList .get(i);
    }
    public int getLongDescriptionCount() {
      return longDescriptionList .size();
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> serviceDescriptionList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getServiceDescriptionList() {
      return serviceDescriptionList;
    }
    public void setServiceDescriptionList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> serviceDescriptionList) {
      this.serviceDescriptionList = serviceDescriptionList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getServiceDescription(int i) {
      return serviceDescriptionList .get(i);
    }
    public int getServiceDescriptionCount() {
      return serviceDescriptionList .size();
    }
    
          private List<String> topicList;
    public List<String> getTopicList() {
      return topicList;
    }
    public void setTopicList(List<String> topicList) {
      this.topicList = topicList;
    }
    public String getTopic(int i) {
      return topicList .get(i);
    }
    public int getTopicCount() {
      return topicList .size();
    }
    
          private it.sayservice.services.universiadi2013.data.message.DataProtoBean.LocationProtoBean location;
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.LocationProtoBean getLocation() {
      return location;
    }
    public void setLocation(it.sayservice.services.universiadi2013.data.message.DataProtoBean.LocationProtoBean location) {
      this.location = location;
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.ImageProtoBean> imageList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.ImageProtoBean> getImageList() {
      return imageList;
    }
    public void setImageList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.ImageProtoBean> imageList) {
      this.imageList = imageList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.ImageProtoBean getImage(int i) {
      return imageList .get(i);
    }
    public int getImageCount() {
      return imageList .size();
    }
    
          private it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean contact;
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean getContact() {
      return contact;
    }
    public void setContact(it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean contact) {
      this.contact = contact;
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> timetableList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getTimetableList() {
      return timetableList;
    }
    public void setTimetableList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> timetableList) {
      this.timetableList = timetableList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getTimetable(int i) {
      return timetableList .get(i);
    }
    public int getTimetableCount() {
      return timetableList .size();
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> priceList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getPriceList() {
      return priceList;
    }
    public void setPriceList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> priceList) {
      this.priceList = priceList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getPrice(int i) {
      return priceList .get(i);
    }
    public int getPriceCount() {
      return priceList .size();
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> seatingCapacityList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getSeatingCapacityList() {
      return seatingCapacityList;
    }
    public void setSeatingCapacityList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> seatingCapacityList) {
      this.seatingCapacityList = seatingCapacityList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getSeatingCapacity(int i) {
      return seatingCapacityList .get(i);
    }
    public int getSeatingCapacityCount() {
      return seatingCapacityList .size();
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> accessibilityList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getAccessibilityList() {
      return accessibilityList;
    }
    public void setAccessibilityList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> accessibilityList) {
      this.accessibilityList = accessibilityList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getAccessibility(int i) {
      return accessibilityList .get(i);
    }
    public int getAccessibilityCount() {
      return accessibilityList .size();
    }
    
    
    public PoiProtoBean() {
    	super();
    }
    
    public PoiProtoBean(it.sayservice.services.universiadi2013.data.message.Data.Poi reference) {
      super();
                        setId(reference.getId());
                                    setCategory(reference.getCategory());
                                    if (reference.getNameList()!=null) {
        nameList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getNameList()) {
          getNameList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    if (reference.getDescriptionList()!=null) {
        descriptionList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getDescriptionList()) {
          getDescriptionList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    if (reference.getLongDescriptionList()!=null) {
        longDescriptionList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getLongDescriptionList()) {
          getLongDescriptionList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    if (reference.getServiceDescriptionList()!=null) {
        serviceDescriptionList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getServiceDescriptionList()) {
          getServiceDescriptionList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    setTopicList(reference.getTopicList());
                                    setLocation(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.LocationProtoBean(reference.getLocation()));
                                    if (reference.getImageList()!=null) {
        imageList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.ImageProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.Image item : reference.getImageList()) {
          getImageList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.ImageProtoBean(item));
        }
      }
                                    setContact(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean(reference.getContact()));
                                    if (reference.getTimetableList()!=null) {
        timetableList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getTimetableList()) {
          getTimetableList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    if (reference.getPriceList()!=null) {
        priceList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getPriceList()) {
          getPriceList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    if (reference.getSeatingCapacityList()!=null) {
        seatingCapacityList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getSeatingCapacityList()) {
          getSeatingCapacityList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    if (reference.getAccessibilityList()!=null) {
        accessibilityList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getAccessibilityList()) {
          getAccessibilityList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                      }  

    public PoiProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("id") != null && !data.get("id").isEmpty()) {
            if (data.get("id").size()>1) throw new XSSDataException("Incorrect data cardinality for field id: expected single value.");
            
            Object item = data.get("id").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field id: expected DOMData");
                                  setId(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("category") != null && !data.get("category").isEmpty()) {
            if (data.get("category").size()>1) throw new XSSDataException("Incorrect data cardinality for field category: expected single value.");
            
            Object item = data.get("category").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field category: expected DOMData");
                                  setCategory(convertToString(((DOMData)item).getStringValue()));
                                      }
                                nameList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("name")!=null) {
            for (Object item : data.get("name")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field name: expected XSSData");
                getNameList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                descriptionList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("description")!=null) {
            for (Object item : data.get("description")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field description: expected XSSData");
                getDescriptionList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                longDescriptionList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("longDescription")!=null) {
            for (Object item : data.get("longDescription")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field longDescription: expected XSSData");
                getLongDescriptionList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                serviceDescriptionList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("serviceDescription")!=null) {
            for (Object item : data.get("serviceDescription")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field serviceDescription: expected XSSData");
                getServiceDescriptionList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                topicList = new LinkedList<String>();
          if (data.get("topic")!=null) {
            for (Object item : data.get("topic")) {
                              if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field topic: expected DOMData");
                                  getTopicList().add(convertToString(((DOMData)item).getStringValue()));
                                          }
          }
                                if (data.get("location") != null && !data.get("location").isEmpty()) {
            if (data.get("location").size()>1) throw new XSSDataException("Incorrect data cardinality for field location: expected single value.");
            
            Object item = data.get("location").get(0); 
                          if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field location: expected XSSData");
              setLocation(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.LocationProtoBean((XSSData)item));
                      }
                                imageList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.ImageProtoBean>();
          if (data.get("image")!=null) {
            for (Object item : data.get("image")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field image: expected XSSData");
                getImageList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.ImageProtoBean((XSSData)item));
                          }
          }
                                if (data.get("contact") != null && !data.get("contact").isEmpty()) {
            if (data.get("contact").size()>1) throw new XSSDataException("Incorrect data cardinality for field contact: expected single value.");
            
            Object item = data.get("contact").get(0); 
                          if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field contact: expected XSSData");
              setContact(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean((XSSData)item));
                      }
                                timetableList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("timetable")!=null) {
            for (Object item : data.get("timetable")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field timetable: expected XSSData");
                getTimetableList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                priceList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("price")!=null) {
            for (Object item : data.get("price")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field price: expected XSSData");
                getPriceList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                seatingCapacityList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("seatingCapacity")!=null) {
            for (Object item : data.get("seatingCapacity")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field seatingCapacity: expected XSSData");
                getSeatingCapacityList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                accessibilityList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("accessibility")!=null) {
            for (Object item : data.get("accessibility")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field accessibility: expected XSSData");
                getAccessibilityList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                  }  

    
    public it.sayservice.services.universiadi2013.data.message.Data.Poi buildMessage() {
      it.sayservice.services.universiadi2013.data.message.Data.Poi .Builder builder = it.sayservice.services.universiadi2013.data.message.Data.Poi .newBuilder();
                        if (getId() != null) {
      	builder.setId(getId());
      }
                                    if (getCategory() != null) {
      	builder.setCategory(getCategory());
      }
                              if (getNameList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getNameList()) {
                    builder. addName(item.buildMessage());
                  }
      }
                        if (getDescriptionList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getDescriptionList()) {
                    builder. addDescription(item.buildMessage());
                  }
      }
                        if (getLongDescriptionList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getLongDescriptionList()) {
                    builder. addLongDescription(item.buildMessage());
                  }
      }
                        if (getServiceDescriptionList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getServiceDescriptionList()) {
                    builder. addServiceDescription(item.buildMessage());
                  }
      }
                        if (getTopicList()!=null) {
        for (String item : getTopicList()) {
                    builder. addTopic(item);
                  }
      }
                              if (getLocation() != null) {
      	builder.setLocation(getLocation() .buildMessage());
      }
                              if (getImageList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.ImageProtoBean item : getImageList()) {
                    builder. addImage(item.buildMessage());
                  }
      }
                              if (getContact() != null) {
      	builder.setContact(getContact() .buildMessage());
      }
                              if (getTimetableList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getTimetableList()) {
                    builder. addTimetable(item.buildMessage());
                  }
      }
                        if (getPriceList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getPriceList()) {
                    builder. addPrice(item.buildMessage());
                  }
      }
                        if (getSeatingCapacityList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getSeatingCapacityList()) {
                    builder. addSeatingCapacity(item.buildMessage());
                  }
      }
                        if (getAccessibilityList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getAccessibilityList()) {
                    builder. addAccessibility(item.buildMessage());
                  }
      }
                  return builder.buildPartial();
    }
    
    
  }
  public static class NewsProtoBean implements ProtoBean {
          private String id;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> titleList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getTitleList() {
      return titleList;
    }
    public void setTitleList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> titleList) {
      this.titleList = titleList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getTitle(int i) {
      return titleList .get(i);
    }
    public int getTitleCount() {
      return titleList .size();
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> shortDescList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getShortDescList() {
      return shortDescList;
    }
    public void setShortDescList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> shortDescList) {
      this.shortDescList = shortDescList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getShortDesc(int i) {
      return shortDescList .get(i);
    }
    public int getShortDescCount() {
      return shortDescList .size();
    }
    
          private String category;
    public String getCategory() {
      return category;
    }
    public void setCategory(String category) {
      this.category = category;
    }
    
          private String published;
    public String getPublished() {
      return published;
    }
    public void setPublished(String published) {
      this.published = published;
    }
    
          private it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean contact;
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean getContact() {
      return contact;
    }
    public void setContact(it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean contact) {
      this.contact = contact;
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> longDescList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getLongDescList() {
      return longDescList;
    }
    public void setLongDescList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> longDescList) {
      this.longDescList = longDescList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getLongDesc(int i) {
      return longDescList .get(i);
    }
    public int getLongDescCount() {
      return longDescList .size();
    }
    
          private String url;
    public String getUrl() {
      return url;
    }
    public void setUrl(String url) {
      this.url = url;
    }
    
          private String imageUrl;
    public String getImageUrl() {
      return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }
    
          private String poiId;
    public String getPoiId() {
      return poiId;
    }
    public void setPoiId(String poiId) {
      this.poiId = poiId;
    }
    
          private String eventId;
    public String getEventId() {
      return eventId;
    }
    public void setEventId(String eventId) {
      this.eventId = eventId;
    }
    
    
    public NewsProtoBean() {
    	super();
    }
    
    public NewsProtoBean(it.sayservice.services.universiadi2013.data.message.Data.News reference) {
      super();
                        setId(reference.getId());
                                    if (reference.getTitleList()!=null) {
        titleList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getTitleList()) {
          getTitleList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    if (reference.getShortDescList()!=null) {
        shortDescList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getShortDescList()) {
          getShortDescList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    setCategory(reference.getCategory());
                                    setPublished(reference.getPublished());
                                    setContact(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean(reference.getContact()));
                                    if (reference.getLongDescList()!=null) {
        longDescList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getLongDescList()) {
          getLongDescList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    setUrl(reference.getUrl());
                                    setImageUrl(reference.getImageUrl());
                                    setPoiId(reference.getPoiId());
                                    setEventId(reference.getEventId());
                      }  

    public NewsProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("id") != null && !data.get("id").isEmpty()) {
            if (data.get("id").size()>1) throw new XSSDataException("Incorrect data cardinality for field id: expected single value.");
            
            Object item = data.get("id").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field id: expected DOMData");
                                  setId(convertToString(((DOMData)item).getStringValue()));
                                      }
                                titleList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("title")!=null) {
            for (Object item : data.get("title")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field title: expected XSSData");
                getTitleList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                shortDescList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("shortDesc")!=null) {
            for (Object item : data.get("shortDesc")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field shortDesc: expected XSSData");
                getShortDescList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                if (data.get("category") != null && !data.get("category").isEmpty()) {
            if (data.get("category").size()>1) throw new XSSDataException("Incorrect data cardinality for field category: expected single value.");
            
            Object item = data.get("category").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field category: expected DOMData");
                                  setCategory(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("published") != null && !data.get("published").isEmpty()) {
            if (data.get("published").size()>1) throw new XSSDataException("Incorrect data cardinality for field published: expected single value.");
            
            Object item = data.get("published").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field published: expected DOMData");
                                  setPublished(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("contact") != null && !data.get("contact").isEmpty()) {
            if (data.get("contact").size()>1) throw new XSSDataException("Incorrect data cardinality for field contact: expected single value.");
            
            Object item = data.get("contact").get(0); 
                          if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field contact: expected XSSData");
              setContact(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean((XSSData)item));
                      }
                                longDescList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("longDesc")!=null) {
            for (Object item : data.get("longDesc")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field longDesc: expected XSSData");
                getLongDescList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                if (data.get("url") != null && !data.get("url").isEmpty()) {
            if (data.get("url").size()>1) throw new XSSDataException("Incorrect data cardinality for field url: expected single value.");
            
            Object item = data.get("url").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field url: expected DOMData");
                                  setUrl(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("imageUrl") != null && !data.get("imageUrl").isEmpty()) {
            if (data.get("imageUrl").size()>1) throw new XSSDataException("Incorrect data cardinality for field imageUrl: expected single value.");
            
            Object item = data.get("imageUrl").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field imageUrl: expected DOMData");
                                  setImageUrl(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("poiId") != null && !data.get("poiId").isEmpty()) {
            if (data.get("poiId").size()>1) throw new XSSDataException("Incorrect data cardinality for field poiId: expected single value.");
            
            Object item = data.get("poiId").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field poiId: expected DOMData");
                                  setPoiId(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("eventId") != null && !data.get("eventId").isEmpty()) {
            if (data.get("eventId").size()>1) throw new XSSDataException("Incorrect data cardinality for field eventId: expected single value.");
            
            Object item = data.get("eventId").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field eventId: expected DOMData");
                                  setEventId(convertToString(((DOMData)item).getStringValue()));
                                      }
                  }  

    
    public it.sayservice.services.universiadi2013.data.message.Data.News buildMessage() {
      it.sayservice.services.universiadi2013.data.message.Data.News .Builder builder = it.sayservice.services.universiadi2013.data.message.Data.News .newBuilder();
                        if (getId() != null) {
      	builder.setId(getId());
      }
                              if (getTitleList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getTitleList()) {
                    builder. addTitle(item.buildMessage());
                  }
      }
                        if (getShortDescList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getShortDescList()) {
                    builder. addShortDesc(item.buildMessage());
                  }
      }
                              if (getCategory() != null) {
      	builder.setCategory(getCategory());
      }
                                    if (getPublished() != null) {
      	builder.setPublished(getPublished());
      }
                                    if (getContact() != null) {
      	builder.setContact(getContact() .buildMessage());
      }
                              if (getLongDescList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getLongDescList()) {
                    builder. addLongDesc(item.buildMessage());
                  }
      }
                              if (getUrl() != null) {
      	builder.setUrl(getUrl());
      }
                                    if (getImageUrl() != null) {
      	builder.setImageUrl(getImageUrl());
      }
                                    if (getPoiId() != null) {
      	builder.setPoiId(getPoiId());
      }
                                    if (getEventId() != null) {
      	builder.setEventId(getEventId());
      }
                        return builder.buildPartial();
    }
    
    
  }
  public static class EventProtoBean implements ProtoBean {
          private String id;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> titleList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getTitleList() {
      return titleList;
    }
    public void setTitleList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> titleList) {
      this.titleList = titleList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getTitle(int i) {
      return titleList .get(i);
    }
    public int getTitleCount() {
      return titleList .size();
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> shortDescList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getShortDescList() {
      return shortDescList;
    }
    public void setShortDescList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> shortDescList) {
      this.shortDescList = shortDescList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getShortDesc(int i) {
      return shortDescList .get(i);
    }
    public int getShortDescCount() {
      return shortDescList .size();
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> longDescList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getLongDescList() {
      return longDescList;
    }
    public void setLongDescList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> longDescList) {
      this.longDescList = longDescList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getLongDesc(int i) {
      return longDescList .get(i);
    }
    public int getLongDescCount() {
      return longDescList .size();
    }
    
          private String category;
    public String getCategory() {
      return category;
    }
    public void setCategory(String category) {
      this.category = category;
    }
    
          private String startDate;
    public String getStartDate() {
      return startDate;
    }
    public void setStartDate(String startDate) {
      this.startDate = startDate;
    }
    
          private String endDate;
    public String getEndDate() {
      return endDate;
    }
    public void setEndDate(String endDate) {
      this.endDate = endDate;
    }
    
          private it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean contact;
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean getContact() {
      return contact;
    }
    public void setContact(it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean contact) {
      this.contact = contact;
    }
    
          private String url;
    public String getUrl() {
      return url;
    }
    public void setUrl(String url) {
      this.url = url;
    }
    
          private String imageUrl;
    public String getImageUrl() {
      return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
    }
    
          private String poiId;
    public String getPoiId() {
      return poiId;
    }
    public void setPoiId(String poiId) {
      this.poiId = poiId;
    }
    
    
    public EventProtoBean() {
    	super();
    }
    
    public EventProtoBean(it.sayservice.services.universiadi2013.data.message.Data.Event reference) {
      super();
                        setId(reference.getId());
                                    if (reference.getTitleList()!=null) {
        titleList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getTitleList()) {
          getTitleList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    if (reference.getShortDescList()!=null) {
        shortDescList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getShortDescList()) {
          getShortDescList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    if (reference.getLongDescList()!=null) {
        longDescList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getLongDescList()) {
          getLongDescList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    setCategory(reference.getCategory());
                                    setStartDate(reference.getStartDate());
                                    setEndDate(reference.getEndDate());
                                    setContact(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean(reference.getContact()));
                                    setUrl(reference.getUrl());
                                    setImageUrl(reference.getImageUrl());
                                    setPoiId(reference.getPoiId());
                      }  

    public EventProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("id") != null && !data.get("id").isEmpty()) {
            if (data.get("id").size()>1) throw new XSSDataException("Incorrect data cardinality for field id: expected single value.");
            
            Object item = data.get("id").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field id: expected DOMData");
                                  setId(convertToString(((DOMData)item).getStringValue()));
                                      }
                                titleList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("title")!=null) {
            for (Object item : data.get("title")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field title: expected XSSData");
                getTitleList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                shortDescList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("shortDesc")!=null) {
            for (Object item : data.get("shortDesc")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field shortDesc: expected XSSData");
                getShortDescList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                longDescList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("longDesc")!=null) {
            for (Object item : data.get("longDesc")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field longDesc: expected XSSData");
                getLongDescList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                if (data.get("category") != null && !data.get("category").isEmpty()) {
            if (data.get("category").size()>1) throw new XSSDataException("Incorrect data cardinality for field category: expected single value.");
            
            Object item = data.get("category").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field category: expected DOMData");
                                  setCategory(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("startDate") != null && !data.get("startDate").isEmpty()) {
            if (data.get("startDate").size()>1) throw new XSSDataException("Incorrect data cardinality for field startDate: expected single value.");
            
            Object item = data.get("startDate").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field startDate: expected DOMData");
                                  setStartDate(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("endDate") != null && !data.get("endDate").isEmpty()) {
            if (data.get("endDate").size()>1) throw new XSSDataException("Incorrect data cardinality for field endDate: expected single value.");
            
            Object item = data.get("endDate").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field endDate: expected DOMData");
                                  setEndDate(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("contact") != null && !data.get("contact").isEmpty()) {
            if (data.get("contact").size()>1) throw new XSSDataException("Incorrect data cardinality for field contact: expected single value.");
            
            Object item = data.get("contact").get(0); 
                          if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field contact: expected XSSData");
              setContact(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.ContactProtoBean((XSSData)item));
                      }
                                if (data.get("url") != null && !data.get("url").isEmpty()) {
            if (data.get("url").size()>1) throw new XSSDataException("Incorrect data cardinality for field url: expected single value.");
            
            Object item = data.get("url").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field url: expected DOMData");
                                  setUrl(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("imageUrl") != null && !data.get("imageUrl").isEmpty()) {
            if (data.get("imageUrl").size()>1) throw new XSSDataException("Incorrect data cardinality for field imageUrl: expected single value.");
            
            Object item = data.get("imageUrl").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field imageUrl: expected DOMData");
                                  setImageUrl(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("poiId") != null && !data.get("poiId").isEmpty()) {
            if (data.get("poiId").size()>1) throw new XSSDataException("Incorrect data cardinality for field poiId: expected single value.");
            
            Object item = data.get("poiId").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field poiId: expected DOMData");
                                  setPoiId(convertToString(((DOMData)item).getStringValue()));
                                      }
                  }  

    
    public it.sayservice.services.universiadi2013.data.message.Data.Event buildMessage() {
      it.sayservice.services.universiadi2013.data.message.Data.Event .Builder builder = it.sayservice.services.universiadi2013.data.message.Data.Event .newBuilder();
                        if (getId() != null) {
      	builder.setId(getId());
      }
                              if (getTitleList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getTitleList()) {
                    builder. addTitle(item.buildMessage());
                  }
      }
                        if (getShortDescList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getShortDescList()) {
                    builder. addShortDesc(item.buildMessage());
                  }
      }
                        if (getLongDescList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getLongDescList()) {
                    builder. addLongDesc(item.buildMessage());
                  }
      }
                              if (getCategory() != null) {
      	builder.setCategory(getCategory());
      }
                                    if (getStartDate() != null) {
      	builder.setStartDate(getStartDate());
      }
                                    if (getEndDate() != null) {
      	builder.setEndDate(getEndDate());
      }
                                    if (getContact() != null) {
      	builder.setContact(getContact() .buildMessage());
      }
                                    if (getUrl() != null) {
      	builder.setUrl(getUrl());
      }
                                    if (getImageUrl() != null) {
      	builder.setImageUrl(getImageUrl());
      }
                                    if (getPoiId() != null) {
      	builder.setPoiId(getPoiId());
      }
                        return builder.buildPartial();
    }
    
    
  }
  public static class VenueProtoBean implements ProtoBean {
          private String id;
    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }
    
          private String category;
    public String getCategory() {
      return category;
    }
    public void setCategory(String category) {
      this.category = category;
    }
    
          private List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> nameList;
    public List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> getNameList() {
      return nameList;
    }
    public void setNameList(List<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean> nameList) {
      this.nameList = nameList;
    }
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean getName(int i) {
      return nameList .get(i);
    }
    public int getNameCount() {
      return nameList .size();
    }
    
          private it.sayservice.services.universiadi2013.data.message.DataProtoBean.LocationProtoBean location;
    public it.sayservice.services.universiadi2013.data.message.DataProtoBean.LocationProtoBean getLocation() {
      return location;
    }
    public void setLocation(it.sayservice.services.universiadi2013.data.message.DataProtoBean.LocationProtoBean location) {
      this.location = location;
    }
    
          private List<String> tagList;
    public List<String> getTagList() {
      return tagList;
    }
    public void setTagList(List<String> tagList) {
      this.tagList = tagList;
    }
    public String getTag(int i) {
      return tagList .get(i);
    }
    public int getTagCount() {
      return tagList .size();
    }
    
    
    public VenueProtoBean() {
    	super();
    }
    
    public VenueProtoBean(it.sayservice.services.universiadi2013.data.message.Data.Venue reference) {
      super();
                        setId(reference.getId());
                                    setCategory(reference.getCategory());
                                    if (reference.getNameList()!=null) {
        nameList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
        for (it.sayservice.services.universiadi2013.data.message.Data.KeyValue item : reference.getNameList()) {
          getNameList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean(item));
        }
      }
                                    setLocation(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.LocationProtoBean(reference.getLocation()));
                                    setTagList(reference.getTagList());
                      }  

    public VenueProtoBean(XSSData data) throws XSSDataException {
      super();
                        if (data.get("id") != null && !data.get("id").isEmpty()) {
            if (data.get("id").size()>1) throw new XSSDataException("Incorrect data cardinality for field id: expected single value.");
            
            Object item = data.get("id").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field id: expected DOMData");
                                  setId(convertToString(((DOMData)item).getStringValue()));
                                      }
                                if (data.get("category") != null && !data.get("category").isEmpty()) {
            if (data.get("category").size()>1) throw new XSSDataException("Incorrect data cardinality for field category: expected single value.");
            
            Object item = data.get("category").get(0); 
                            if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field category: expected DOMData");
                                  setCategory(convertToString(((DOMData)item).getStringValue()));
                                      }
                                nameList = new LinkedList<it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean>();
          if (data.get("name")!=null) {
            for (Object item : data.get("name")) {
                              if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field name: expected XSSData");
                getNameList().add(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean((XSSData)item));
                          }
          }
                                if (data.get("location") != null && !data.get("location").isEmpty()) {
            if (data.get("location").size()>1) throw new XSSDataException("Incorrect data cardinality for field location: expected single value.");
            
            Object item = data.get("location").get(0); 
                          if (!(item instanceof XSSData)) throw new XSSDataException("Incorrect data type for field location: expected XSSData");
              setLocation(new it.sayservice.services.universiadi2013.data.message.DataProtoBean.LocationProtoBean((XSSData)item));
                      }
                                tagList = new LinkedList<String>();
          if (data.get("tag")!=null) {
            for (Object item : data.get("tag")) {
                              if (!(item instanceof DOMData)) throw new XSSDataException("Incorrect data type for field tag: expected DOMData");
                                  getTagList().add(convertToString(((DOMData)item).getStringValue()));
                                          }
          }
                  }  

    
    public it.sayservice.services.universiadi2013.data.message.Data.Venue buildMessage() {
      it.sayservice.services.universiadi2013.data.message.Data.Venue .Builder builder = it.sayservice.services.universiadi2013.data.message.Data.Venue .newBuilder();
                        if (getId() != null) {
      	builder.setId(getId());
      }
                                    if (getCategory() != null) {
      	builder.setCategory(getCategory());
      }
                              if (getNameList()!=null) {
        for (it.sayservice.services.universiadi2013.data.message.DataProtoBean.KeyValueProtoBean item : getNameList()) {
                    builder. addName(item.buildMessage());
                  }
      }
                              if (getLocation() != null) {
      	builder.setLocation(getLocation() .buildMessage());
      }
                              if (getTagList()!=null) {
        for (String item : getTagList()) {
                    builder. addTag(item);
                  }
      }
                  return builder.buildPartial();
    }
    
    
  }

 
  protected static double convertToDouble(String value) {
    return Double.parseDouble(value);
  }
  
  protected static float convertToFloat(String value) {
    return Float.parseFloat(value);
  }

  protected static boolean convertToBoolean(String value) {
    return Boolean.parseBoolean(value);
  }

  protected static String convertToString(String value) {
    return value;
  }

  protected static int convertToInteger(String value) {
    return Integer.parseInt(value);
  }

  protected static long convertToLong(String value) {
    return Long.parseLong(value);
  }

  protected static ByteString convertToByteString(String value) {
    try {
      return ByteString.copyFrom(value.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      return ByteString.copyFrom(value.getBytes());
    }
  }
 
}
