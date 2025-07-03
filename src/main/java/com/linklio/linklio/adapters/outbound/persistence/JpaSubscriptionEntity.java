package com.linklio.linklio.adapters.outbound.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subscriptions", schema = "linklio")
@SequenceGenerator(name = "subscriptions_seq_gen", sequenceName = "subscriptions_seq", allocationSize = 1)
@Getter
@Setter
public class JpaSubscriptionEntity {
   @Id
   @GeneratedValue(generator = "subscriptions_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

   @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

   @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
    private JpaUserEntity user;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="plan_id")
    private JpaPlanEntity plan;


}
