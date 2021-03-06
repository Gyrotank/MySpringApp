CREATE TABLE Addresses(
	addresses_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL primary key,
	postal_code varchar(20),
	city varchar(20),
	street varchar(30),
	bld varchar(10),
	apt varchar(10)
);

INSERT INTO Addresses (postal_code, city, street, bld, apt) VALUES ('0123', 'Kyiv', 'Khreschatik', '1B', '3')
INSERT INTO Addresses (postal_code, city, street, bld, apt) VALUES ('01234', 'Kyiv', 'Vokzalna', '221B', '378')


CREATE TABLE Clients(
	clients_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL primary key,
	clients_name varchar(50),
	address_id int FOREIGN KEY REFERENCES Addresses(addresses_id)	
);

INSERT INTO Clients (clients_name, address_id) VALUES ('Ivan Ivanov', 1)
INSERT INTO Clients (clients_name, address_id) VALUES ('Petro Petrov', 2)


CREATE TABLE Pizzas(
    pizzas_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL primary key,
    pizzas_name varchar(20),
    pizzas_price double,
    pizzas_type int	
);

INSERT INTO Pizzas (pizzas_name, pizzas_price, pizzas_type) VALUES ('Big Meat Pizza', 450.00, 2)
INSERT INTO Pizzas (pizzas_name, pizzas_price, pizzas_type) VALUES ('Big Sea Pizza', 500.00, 1)


CREATE TABLE Orderstatuses(
	order_statuses_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL primary key,
	order_statuses_name varchar(15)
);

INSERT INTO Orderstatuses (order_statuses_name) VALUES ('NEW')
INSERT INTO Orderstatuses (order_statuses_name) VALUES ('IN PROGRESS')
INSERT INTO Orderstatuses (order_statuses_name) VALUES ('CANCELLED')
INSERT INTO Orderstatuses (order_statuses_name) VALUES ('DONE')


CREATE TABLE Orders(
    orders_id int GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL primary key,
    orders_name varchar(20),
    orders_date date,
    orders_price double,
    orders_status_id int FOREIGN KEY REFERENCES Orderstatuses(order_statuses_id),
    client_id int FOREIGN KEY REFERENCES Clients(clients_id)
);

INSERT INTO Orders (orders_name, orders_date, orders_price, orders_status_id, client_id) VALUES ('Order1', '2008-08-22', 0.0, 2, 1)
INSERT INTO Orders (orders_name, orders_date, orders_price, orders_status_id, client_id) VALUES ('Order2', '2015-10-15', 0.0, 4, 2)


CREATE TABLE Pizzasinorders(
	pizzas_in_orders_id int GENERATED BY DEFAULT AS IDENTITY 
                                         (START WITH 1, INCREMENT BY 1) NOT NULL primary key,
	order_id int FOREIGN KEY REFERENCES Orders(orders_id),
	pizza_id int FOREIGN KEY REFERENCES Pizzas(pizzas_id),
	quantity int
);

INSERT INTO Pizzasinorders (order_id, pizza_id, quantity) VALUES (1, 1, 1)
INSERT INTO Pizzasinorders (order_id, pizza_id, quantity) VALUES (1, 2, 2)