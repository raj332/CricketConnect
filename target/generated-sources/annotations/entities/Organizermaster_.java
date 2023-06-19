package entities;

import entities.Tournamenttb;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-19T15:09:44")
@StaticMetamodel(Organizermaster.class)
public class Organizermaster_ { 

    public static volatile SingularAttribute<Organizermaster, String> organizerId;
    public static volatile SingularAttribute<Organizermaster, String> city;
    public static volatile SingularAttribute<Organizermaster, Long> mobileNumber;
    public static volatile ListAttribute<Organizermaster, Tournamenttb> tournamenttbList;
    public static volatile SingularAttribute<Organizermaster, String> name;

}