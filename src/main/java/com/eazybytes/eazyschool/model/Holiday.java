package com.eazybytes.eazyschool.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@Entity
@Table(name="holidays")
public class Holiday extends BaseEntity{
    @Id
    @Column(name = "_day")
    private  String day;
    private  String reason;
    @Enumerated(EnumType.STRING)
    private  Type type;
    public enum Type {
        FESTIVAL,FEDERAL
    }
}
