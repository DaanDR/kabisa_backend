CREATE TABLE IF NOT EXISTS quote
(
    id int4 NOT NULL,
    author varchar(255) NULL,
    quote varchar(255) NULL,
    permalink varchar(255) NULL,
    likes int4 NULL,
    CONSTRAINT quote_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE hibernate_sequence START 1;