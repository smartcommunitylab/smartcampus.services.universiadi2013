package it.sayservice.services.universiadi2013.impl;

import it.sayservice.platform.core.common.exception.EntityNotFoundException;
import it.sayservice.platform.core.bus.common.exception.PersistenceException;
import it.sayservice.platform.core.bus.data.mongo.AbstractMongoPersistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Message;
import com.mongodb.DB;

public class GetVenuesServicePersistence extends AbstractMongoPersistence {

    public GetVenuesServicePersistence(DB db) {
        super(db);
    }

    @Override
    protected String getServiceId() {
        return "it.sayservice.ext.universiadi2013";
    }

    @Override
    protected String getMethodName() {
        return "GetVenues";
    }

    @Override
    protected Builder<?> getProtoBuilder() {
        return it.sayservice.services.universiadi2013.data.message.Data.Venue .newBuilder();
    }

    @Override
    protected Class<?> getParamType(String param) {
        return null;
    }
    
}
