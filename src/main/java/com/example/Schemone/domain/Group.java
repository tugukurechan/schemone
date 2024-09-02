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
 * groupのドメインクラス.
 *
 * @author tuguk
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "group_name", nullable = false)
    private String name;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    //ここから外部

    @OneToMany(mappedBy = "group")
    private List<User> userList;

    @OneToMany(mappedBy = "group")
    private List<EventInsteadUser> eventList;

    @OneToMany(mappedBy = "group")
    private List<EventInsteadUser> eventInsteadUserList;

    @OneToMany(mappedBy = "group")
    private List<Debt> debtList;
}
