use hibernate_lesson_2;

delete from CustomerProduct;

delete from Product;

delete from Customer;

INSERT INTO Customer (name)
VALUES ('Alex'), ('Petya');

INSERT INTO Product (name, price)
VALUES ('Bread', 30), ('Milk', 70), ('Cake', 150);
