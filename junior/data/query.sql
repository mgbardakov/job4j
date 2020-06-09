--все продукты с типом 'СЫР'
SELECT *
FROM product
JOIN type ON product.type_id = type.id
WHERE type.name = 'СЫР'

--все продукты со словом "мороженное" в названии
SELECT *
FROM product
WHERE name LIKE('%мороженное%')

--все продукты со сроком годности заканчивающимся в следующем месяце
SELECT *
FROM product
WHERE EXTRACT(MONTH FROM expired_date) - EXTRACT(MONTH FROM NOW()) = 1

--выводит самый дорогой продукт
SELECT *
FROM product
WHERE price = (SELECT MAX(price) FROM product)

--выводит количесвто продуктов определённого типа
SELECT type.name, COUNT(*)
FROM product
JOIN type ON product.type_id = type.id
GROUP BY type.name

--все продукты типов 'СЫР' и 'МОЛОКО'
SELECT *
FROM product
JOIN type ON product.type_id = type.id
WHERE type.name = 'СЫР' OR type.name = 'МОЛОКО'

--выводит типы продуктов, которых осталось меньше 10 штук
SELECT type.name, COUNT(*)
FROM product
JOIN type ON product.type_id = type.id
GROUP BY type.name
HAVING COUNT(*) < 10

--выводит все продукты и их тип
SELECT *
FROM product
JOIN type ON product.type_id = type.id