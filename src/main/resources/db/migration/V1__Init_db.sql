create table product_categories
(
	category_id bigserial not null
		constraint product_categories_pkey
			primary key,
	category_desc varchar(255),
	other_category_info varchar(255)
)
;

create table users
(
	user_id bigserial not null
		constraint users_pkey
			primary key,
	city varchar(255) not null,
	country varchar(255) not null,
	email varchar(255) not null
		constraint uk_6dotkott2kjsp8vw4d0m25fb7
			unique,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	password varchar(255) not null,
	postal_code varchar(255) not null,
	role varchar(255) not null,
	street varchar(255) not null,
	street_number varchar(255) not null
)
;

create table access_tokens
(
	access_token_id bigserial not null
		constraint access_tokens_pkey
			primary key,
	access_token uuid not null,
	created_on timestamp not null,
	expires_in timestamp not null,
	user_id bigint not null
		constraint uk_4d9bw948jtas40j53ui2nj2ff
			unique
		constraint fkjxi0wavfc9xw97x1mhuc8nphm
			references users
)
;

create table cart
(
	cart_id bigserial not null
		constraint cart_pkey
			primary key,
	date_added timestamp,
	price numeric(19,2),
	quantity numeric(19,2),
	tax numeric(19,2),
	user_id bigint not null
		constraint uk_9emlp6m95v5er2bcqkjsw48he
			unique
		constraint fkg5uhi8vpsuy0lgloxk2h4w5o6
			references users
)
;

create table orders
(
	order_id serial not null
		constraint orders_pkey
			primary key,
	amount numeric(19,2),
	order_date timestamp,
	order_status varchar(255),
	tax numeric(19,2),
	user_id bigint not null
		constraint fk32ql8ubntj5uh44ph9659tiih
			references users
)
;

create table products
(
	product_id bigserial not null
		constraint products_pkey
			primary key,
	keywords varchar(255),
	metric_unit varchar(255),
	other_product_info varchar(255),
	price numeric(19,2),
	product_desc varchar(255),
	stock numeric(19,2),
	stock_level numeric(19,2),
	tax numeric(19,2),
	cart_id bigint not null
		constraint fkaohdy515ap03vw8l89w6g9c6y
			references cart,
	category_id bigint not null
		constraint fk6t5dtw6tyo83ywljwohuc6g7k
			references product_categories
)
;

create table order_items
(
	order_item_id bigserial not null
		constraint order_items_pkey
			primary key,
	date_added timestamp,
	price numeric(19,2),
	quantity numeric(19,2),
	tax numeric(19,2),
	order_id integer not null
		constraint uk_9gap2fmw66v092ntb58rtohwh
			unique
		constraint fkbioxgbv59vetrxe0ejfubep1w
			references orders,
	product_id bigint not null
		constraint uk_3fea23hxar30bx7m7h8ed25n9
			unique
		constraint fkocimc7dtr037rh4ls4l95nlfi
			references products
)
;

