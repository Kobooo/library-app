package com.library.app.category.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category implements Serializable {
    private static final long serialVersionUID = 2657551019023598969L;
}
