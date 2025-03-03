package com.eazybytes.eazyschool.model;

import jakarta.persistence.*;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
@Data
@Entity
@Table(name="contact_msg")
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")//the database will handle the generated key value
    @Column(name = "contact_id")
    private  int contactId;
    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    @Column(updatable = false)
    private String name;
    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Column(updatable = false)
    private String mobileNum;
    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    @Column(updatable = false)
    private String email;
    @NotBlank(message="Subject must not be blank")
    @Size(min=5, message="Subject must be at least 5 characters long")
    @Column(updatable = false)
    private String subject;
    @NotBlank(message="Message must not be blank")
    @Size(min=10, message="Message must be at least 10 characters long")
    @Column(updatable = false)
    private String message;
    private String status;
}