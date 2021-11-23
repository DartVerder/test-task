CREATE TABLE bank
(
    id   UUID         NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_bank PRIMARY KEY (id)
);

CREATE TABLE client
(
    id              UUID         NOT NULL,
    first_name      VARCHAR(255) NOT NULL,
    last_name       VARCHAR(255) NOT NULL,
    patronymic      VARCHAR(255),
    phone_number    VARCHAR(255) NOT NULL,
    passport_number VARCHAR(255) NOT NULL,
    bank_id         UUID,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE credit
(
    id           UUID   NOT NULL,
    credit_limit DOUBLE NOT NULL,
    percent      DOUBLE NOT NULL,
    bank_id      UUID,
    CONSTRAINT pk_credit PRIMARY KEY (id)
);

CREATE TABLE credit_offer
(
    id                    UUID NOT NULL,
    client_id             UUID,
    credit_id             UUID,
    credit_sum            DOUBLE,
    months_count          INT  NOT NULL,
    start_date            date NOT NULL,
    payment_schedule_type INT  NOT NULL,
    CONSTRAINT pk_credit_offer PRIMARY KEY (id)
);

CREATE TABLE payment_schedule
(
    id                  UUID NOT NULL,
    payment_date        date,
    payment_sum         DOUBLE,
    payment_to_credit   DOUBLE,
    payment_to_percents DOUBLE,
    credit_offer_id     UUID,
    CONSTRAINT pk_payment_schedule PRIMARY KEY (id)
);

ALTER TABLE client
    ADD CONSTRAINT FK_CLIENT_ON_BANK FOREIGN KEY (bank_id) REFERENCES bank (id);

ALTER TABLE credit_offer
    ADD CONSTRAINT FK_CREDIT_OFFER_ON_CLIENT FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE credit_offer
    ADD CONSTRAINT FK_CREDIT_OFFER_ON_CREDIT FOREIGN KEY (credit_id) REFERENCES credit (id);

ALTER TABLE credit
    ADD CONSTRAINT FK_CREDIT_ON_BANK FOREIGN KEY (bank_id) REFERENCES bank (id);

ALTER TABLE payment_schedule
    ADD CONSTRAINT FK_PAYMENT_SCHEDULE_ON_CREDIT_OFFER FOREIGN KEY (credit_offer_id) REFERENCES credit_offer (id);