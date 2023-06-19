package entities;

import entities.Projectgroups;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-20T00:41:46")
@StaticMetamodel(Projectusers.class)
public class Projectusers_ { 

    public static volatile SingularAttribute<Projectusers, String> password;
    public static volatile ListAttribute<Projectusers, Projectgroups> projectgroupsList;
    public static volatile SingularAttribute<Projectusers, String> userId;

}