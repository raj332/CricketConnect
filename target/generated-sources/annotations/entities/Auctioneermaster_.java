package entities;

import entities.Auctiondetailtb;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-19T00:18:38")
@StaticMetamodel(Auctioneermaster.class)
public class Auctioneermaster_ { 

    public static volatile SingularAttribute<Auctioneermaster, Long> mobileNumber;
    public static volatile ListAttribute<Auctioneermaster, Auctiondetailtb> auctiondetailtbList;
    public static volatile SingularAttribute<Auctioneermaster, String> name;
    public static volatile SingularAttribute<Auctioneermaster, String> auctioneerId;

}