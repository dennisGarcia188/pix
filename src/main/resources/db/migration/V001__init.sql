
CREATE SEQUENCE IF NOT EXISTS user_pix_seq START 1;

CREATE TABLE IF NOT EXISTS USER_PIX (
    id varchar PRIMARY KEY DEFAULT nextval('user_pix_seq'),
    type_key varchar(9) NOT NULL,
    account_type varchar(10) NOT NULL,
    key_information varchar(77) NOT NULL,
    agency_number int NOT NULL,
    account_number int NOT NULL,
    holder_name varchar(30) NOT NULL,
    holder_last_name varchar(45)
);


