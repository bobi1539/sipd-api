package com.suzume.sipd.service.handler.participant;

import com.suzume.sipd.constant.enums.ParticipantType;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class TripParticipantHandlerRegistry {

    private final Map<ParticipantType, AbstractTripParticipantHandler> handlerMap = new EnumMap<>(ParticipantType.class);

    public TripParticipantHandlerRegistry(List<AbstractTripParticipantHandler> handlers) {
        for (AbstractTripParticipantHandler handler : handlers) {
            handlerMap.put(handler.getType(), handler);
        }
    }

    public AbstractTripParticipantHandler getHandler(ParticipantType type) {
        return handlerMap.get(type);
    }

}
