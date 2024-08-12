package org.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`groups`")
public class Group  extends BaseModel{
    private String name;

    @ManyToOne
    private User admin;

    @ManyToMany
    private List<User> users;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
}
