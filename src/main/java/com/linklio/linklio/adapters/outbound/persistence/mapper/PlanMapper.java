package com.linklio.linklio.adapters.outbound.persistence.mapper;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaPlanEntity;
import com.linklio.linklio.domain.model.Plan;
import com.linklio.linklio.domain.model.Subscription;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PlanMapper {

    public Plan toDomain(JpaPlanEntity entity){
        if(entity==null) return null;

        Set<Subscription> shallowSubSet = entity.getSubscriptions().stream()
                .map(subSub -> new Subscription(subSub.getId(),null,null,null,null,false))
                .collect(Collectors.toSet());


        return new Plan(entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getLinkLimit(),
                entity.getDescription(),
                shallowSubSet
                );
    }
}
