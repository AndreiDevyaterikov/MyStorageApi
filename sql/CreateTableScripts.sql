create table if not exists storages
(
    storage_id   numeric primary key,
    storage_name varchar
);

create table if not exists products
(
    product_id      numeric primary key,
    article         varchar,
    product_name    varchar,
    last_buy_price  numeric,
    last_sell_price numeric,
    amount          numeric,
    storage_id      numeric,
    constraint FK_storage_id foreign key (storage_id) references storages (storage_id)
);
