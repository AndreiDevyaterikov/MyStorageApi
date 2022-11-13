create or replace function get_products_by_filter(_article varchar, _product_name varchar, _last_buy_price decimal,
                                                  _last_sell_price decimal) returns setof products
    language plpgsql
as
$$
declare
BEGIN
    return query
        select *
        from products
        where article like '%'||_article||'%'
           or product_name like '%'||_product_name||'%'
           or last_buy_price = _last_buy_price
           or last_sell_price = _last_sell_price;
end
$$;