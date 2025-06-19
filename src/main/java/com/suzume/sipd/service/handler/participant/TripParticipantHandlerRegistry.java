package com.suzume.sipd.service.handler.participant;

import com.suzume.sipd.constant.enums.ParticipantType;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class TripParticipantHandlerRegistry {

    private final Map<ParticipantType, TripParticipantHandler> handlerMap = new EnumMap<>(ParticipantType.class);

    public TripParticipantHandlerRegistry(List<TripParticipantHandler> handlers) {
        for (TripParticipantHandler handler : handlers) {
            handlerMap.put(handler.getType(), handler);
        }
    }

    public TripParticipantHandler getHandler(ParticipantType type) {
        return handlerMap.get(type);
    }

}
