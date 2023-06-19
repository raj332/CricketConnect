package entities;

import entities.Projectusers;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-19T15:09:44")
@StaticMetamodel(Projectgroups.class)
public class Projectgroups_ { 

    public static volatile SingularAttribute<Projectgroups, String> groupName;
    public static volatile SingularAttribute<Projectgroups, Integer> groupId;
    public static volatile SingularAttribute<Projectgroups, Projectusers> userId;

}