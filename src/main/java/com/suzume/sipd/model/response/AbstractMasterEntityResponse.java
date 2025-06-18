package com.suzume.sipd.model.response;

import com.suzume.sipd.entity.AbstractMasterEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class AbstractMasterEntityResponse extends AbstractBaseEntityResponse {
    protected boolean isDeleted;

    public static void setMasterEntity(AbstractMasterEntityResponse response, AbstractMasterEntity entity) {
        setBaseEntity(response, entity);
        response.setDeleted(entity.isDeleted());
    }

}
