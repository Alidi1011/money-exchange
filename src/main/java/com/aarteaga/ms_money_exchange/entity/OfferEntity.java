package com.aarteaga.ms_money_exchange.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offer", schema = "dbo")
public class OfferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "card_description", nullable = false)
    private String cardDescription;

    @Column(name = "button_name", nullable = false)
    private String buttonName;

    @Column(name = "button_link", nullable = false)
    private String buttonLink;

    @Column(name = "background_image", nullable = false)
    private String backgroundImage;

    @Column(name = "additional_image", nullable = false)
    private String additionalImage;

    @Column(name = "creation_date", nullable = true)
    private LocalDateTime creationDate;

    @Column(name = "created_by", nullable = true)
    private String createdBy;

    @Column(name = "modification_date", nullable = true)
    private LocalDateTime modificationDate;

    @Column(name = "modified_by", nullable = true)
    private String modifiedBy;
}
