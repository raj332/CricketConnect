package entities;

import entities.Teammaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-16T11:09:24")
@StaticMetamodel(Teamownermaster.class)
public class Teamownermaster_ { 

    public static volatile SingularAttribute<Teamownermaster, Long> mobileNumber;
    public static volatile SingularAttribute<Teamownermaster, String> name;
    public static volatile SingularAttribute<Teamownermaster, String> ownerId;
    public static volatile ListAttribute<Teamownermaster, Teammaster> teammasterList;

}