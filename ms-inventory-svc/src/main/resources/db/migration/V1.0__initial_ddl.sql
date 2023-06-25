CREATE TABLE t_stock (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    quantity int4 NOT NULL,
    unit_measurement varchar(25) NOT NULL,
    cost numeric(12,4) NOT NULL,
    selling_price numeric(12,4) DEFAULT 0.00,
    created_time timestamptz NULL,
    updated_time timestamptz NULL,
    version int4 NULL,
    created_by varchar(255) NOT NULL,
    updated_by varchar(255) NULL,
    status varchar(50) NULL,
    CONSTRAINT stock_pkey PRIMARY KEY (id)
);