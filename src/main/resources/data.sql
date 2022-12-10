insert into coffee(coffee_code, kor_name, eng_name, price, order_status, size_selectable, created_at, modified_at) values ('h_101', '아메리카노', 'Americano', 4500, '판매중', true, now(), now());
insert into coffee(coffee_code, kor_name, eng_name, price, order_status, size_selectable, created_at, modified_at) values ('c_101', '아메리카노', 'Americano', 4500, '판매중', true, now(), now());
insert into coffee(coffee_code, kor_name, eng_name, price, order_status, size_selectable, created_at, modified_at) values ('c_102', '콜드브루', 'Cold brew', 4800, '판매중', true, now(), now());

-- 옵션에도 새로운 유니크키를 만들어 주어야 겠다.
insert into option(kor_name, eng_name, price, created_at, modified_at) values ('시나몬 가루', 'cinnamon powder', 500, now(), now());
