package com.example.Schemone.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * userのドメインクラス.
 *
 * @author tuguk
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // ここから外部

    @OneToMany(mappedBy = "paidUser")
    private List<Event> paidEventList;

    @OneToMany(mappedBy = "eventInsteadUser")
    private List<EventInsteadUser> eventInsteadUserList;

    @OneToMany(mappedBy = "creditor")
    private List<Debt> debtsAsCreditor;

    @OneToMany(mappedBy = "debtor")
    private List<Debt> debtAsDebtor;
}