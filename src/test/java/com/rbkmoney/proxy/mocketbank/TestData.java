package com.rbkmoney.proxy.mocketbank;

import com.rbkmoney.cds.client.storage.model.CardDataProxyModel;
import com.rbkmoney.damsel.cds.CardData;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardPaymentSystem;
import com.rbkmoney.java.damsel.utils.creators.DomainPackageCreators;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.rbkmoney.java.damsel.utils.creators.CdsPackageCreators.createCardDataWithExpDate;
import static com.rbkmoney.java.damsel.utils.creators.DomainPackageCreators.createBankCardExpDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestData {

    public static final String PHONE_NUMBER = "9876543210";
    public static final String WITHDRAWAL_TOKEN = "token";
    public static final String BANK_CARD_TOKEN = "bank_card_token";
    public static final String RECURRENT_TOKEN = "recurrent_token";
    public static final String DEFAULT_CARDHOLDERNAME = "NONAME";

    public static final String FINGERPRINT = "fingerprint";
    public static final String IP_ADDRESS = "0.0.0.0";
    public static final String SESSION_ID = "session_id";
    public static final String CREATED_AT = "2020-06-02";

    public static final String DEFAULT_ACS_URL = "http://localhost/acs";
    public static final String DEFAULT_PAREQ = "PaReq";


    public static CardData makeCardData() {
        return createCardDataWithExpDate(
                DEFAULT_CARDHOLDERNAME,
                "123",
                "4012001011000771",
                "12", "2020"
        );
    }

    public static CardDataProxyModel createCardDataProxyModel(String pan) {
        return CardDataProxyModel.builder()
                .pan(pan)
                .expMonth(Byte.parseByte("12"))
                .expYear(Short.parseShort("2020"))
                .cardholderName(DEFAULT_CARDHOLDERNAME)
                .build();
    }

    public static CardData createCardData(String pan) {
        return createCardDataWithExpDate(
                DEFAULT_CARDHOLDERNAME,
                "123",
                pan,
                "12", "2020"
        );
    }

    public static CardData createCardData() {
        return createCardDataWithExpDate(
                DEFAULT_CARDHOLDERNAME,
                "123",
                "4012001011000771",
                "12", "2020"
        );
    }

    public static BankCard createBankCard(CardData cardData) {
        String month = String.valueOf(cardData.getExpDate().getMonth());
        String year = String.valueOf(cardData.getExpDate().getYear());

        return DomainPackageCreators.createBankCard(month, year, cardData.getCardholderName())
                .setPaymentSystem(BankCardPaymentSystem.mastercard)
                .setExpDate(
                        createBankCardExpDate(
                                cardData.getExpDate().getMonth(),
                                cardData.getExpDate().getYear()
                        )
                )
                .setBin(cardData.pan.substring(0, 6))
                .setLastDigits(cardData.pan.substring(cardData.pan.length() - 4));
    }

    public static BankCard makeBankCard(CardData cardData) {
        String month = String.valueOf(cardData.getExpDate().getMonth());
        String year = String.valueOf(cardData.getExpDate().getYear());

        return DomainPackageCreators.createBankCard(month, year, cardData.getCardholderName())
                .setPaymentSystem(BankCardPaymentSystem.mastercard)
                .setExpDate(
                        createBankCardExpDate(
                                cardData.getExpDate().getMonth(),
                                cardData.getExpDate().getYear()
                        )
                )
                .setBin(cardData.pan.substring(0, 6))
                .setLastDigits(cardData.pan.substring(cardData.pan.length() - 4));
    }

}
