package com.linklio.linklio.adapters.outbound.persistence.mapper;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaSubscriptionEntity;
import com.linklio.linklio.domain.model.Plan;
import com.linklio.linklio.domain.model.Subscription;
import com.linklio.linklio.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    public Subscription toDomain(JpaSubscriptionEntity entity){
        if(entity==null) return null;

        User shallowUser = new User();
        shallowUser.setId(entity.getUser().getId());

        Plan shallowPlan = new Plan();
        shallowPlan.setId(entity.getPlan().getId());

        return new Subscription(
                entity.getId(),
                entity.getStartDate(),
                entity.getEndDate(),
                shallowUser,
                shallowPlan,
                entity.isActive()
                );
    }
}
