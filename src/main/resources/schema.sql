CREATE TABLE PizzaTypes (
  pizza_type            varchar(20) primary key
);

INSERT INTO PizzaTypes (pizza_type) VALUES ('MEAT'), ('SEA'), ('VEGETARIAN');

CREATE TABLE Pizzas(
    pizzas_id int primary key,
    pizza_name varchar(10),
    pizza_price double,
    pizza_type varchar(20) NOT NULL DEFAULT 'MEAT',
	FOREIGN KEY (pizza_type) REFERENCES PizzaTypes(pizza_type)
);
