package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.Tests.BB.LoyaltyCardList.LoyaltyCardListTestAddLoyaltyCard;
import it.polito.ezshop.Tests.BB.LoyaltyCardList.LoyaltyCardListTestAddLoyaltyCardIdAsArgoument;
import it.polito.ezshop.Tests.BB.LoyaltyCardList.LoyaltyCardListTestDeleteLoyaltyCard;
import it.polito.ezshop.Tests.BB.LoyaltyCardList.LoyaltyCardListTestSearchLoyaltyCardById;
import it.polito.ezshop.Tests.BB.LoyaltyCardList.LoyaltyCardListTestUpdatePointsOnCard;



@RunWith(Suite.class)
@Suite.SuiteClasses({LoyaltyCardListTestAddLoyaltyCard.class,
					 LoyaltyCardListTestUpdatePointsOnCard.class,
					 LoyaltyCardListTestAddLoyaltyCardIdAsArgoument.class,
					 LoyaltyCardListTestDeleteLoyaltyCard.class,
					 LoyaltyCardListTestSearchLoyaltyCardById.class
					})

public class TestSuiteLoyaltyCardList {

}
