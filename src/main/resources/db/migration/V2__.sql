INSERT INTO bank (id, name) VALUES ('8fccf7d4-d03a-4447-a8c3-085b2b1c066f', 'AlfaTest');
INSERT INTO bank (id, name) VALUES ('b5265ab5-572a-4ad8-b565-c6c5d10df479', 'TinkTest');

INSERT INTO client (id, first_name, last_name, phone_number, passport_number, patronymic, bank_id)
VALUES ('09a78f29-ee3a-4b2a-baf1-3a36534cd50a', 'Ivanov', 'Ivan', '927654321', '1231234567', 'Ivanovich', '8fccf7d4-d03a-4447-a8c3-085b2b1c066f');
INSERT INTO client (id, first_name, last_name, phone_number, passport_number, patronymic, bank_id)
VALUES ('138b8e43-f68b-44d0-bda3-0a4707b2a4dd', 'Anna', 'Petrova', '88005553535', '6655678987', 'Fedorovna', 'b5265ab5-572a-4ad8-b565-c6c5d10df479');

INSERT INTO credit (id, credit_limit, percent, bank_id) VALUES ('0fd9f820-7fef-46d9-9e65-dcadae2f7afb', 100000, 10, '8fccf7d4-d03a-4447-a8c3-085b2b1c066f');
INSERT INTO credit (id, credit_limit, percent, bank_id) VALUES ('7069dc63-8d7a-47cb-9808-5bb2e3c76b8d', 50000, 23, '8fccf7d4-d03a-4447-a8c3-085b2b1c066f');
INSERT INTO credit (id, credit_limit, percent, bank_id) VALUES ('b6a21f1d-51b8-4cef-ba2a-2f2f494c5e43', 50000, 10, 'b5265ab5-572a-4ad8-b565-c6c5d10df479');
INSERT INTO credit (id, credit_limit, percent, bank_id) VALUES ('d172f3f9-07fa-4906-ad4b-bdd5a74ff40a', 10000, 15, 'b5265ab5-572a-4ad8-b565-c6c5d10df479');


INSERT INTO credit_offer (id, months_count, start_date, payment_schedule_type, client_id, credit_id, credit_sum)
VALUES ('9815ff05-d529-4b5e-a718-bb05e77159f7', 12, NOW(), 0, '09a78f29-ee3a-4b2a-baf1-3a36534cd50a', '0fd9f820-7fef-46d9-9e65-dcadae2f7afb', 1000);

INSERT INTO credit_offer (id, months_count, start_date, payment_schedule_type, client_id, credit_id, credit_sum)
VALUES ('9815ff05-d529-4b5e-a718-bb05e77159f9', 12, NOW(), 0, '138b8e43-f68b-44d0-bda3-0a4707b2a4dd', 'b6a21f1d-51b8-4cef-ba2a-2f2f494c5e43', 50000);