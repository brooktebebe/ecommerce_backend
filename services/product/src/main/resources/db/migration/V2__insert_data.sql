-- Insert data into product_category and capture the IDs
WITH category_ids AS (
    INSERT INTO product_category (id, description, name) VALUES (nextval('product_category_seq'),
                                                                 'Electronics and gadgets', 'Electronics')
        RETURNING id),
     category_2 AS (
         INSERT INTO product_category (id, description, name) VALUES (nextval('product_category_seq'),
                                                                      'Home and kitchen appliances', 'Home Appliances')
             RETURNING id),
     category_3 AS (
         INSERT INTO product_category (id, description, name) VALUES (nextval('product_category_seq'),
                                                                      'Books and Stationery', 'Books')
             RETURNING id),
     category_4 AS (
         INSERT INTO product_category (id, description, name) VALUES (nextval('product_category_seq'),
                                                                      'Fashion and Clothing', 'Fashion')
             RETURNING id),
     category_5 AS (
         INSERT INTO product_category (id, description, name) VALUES (nextval('product_category_seq'),
                                                                      'Health and Beauty', 'Beauty')
             RETURNING id),
     category_6 AS (
         INSERT INTO product_category (id, description, name) VALUES (nextval('product_category_seq'),
                                                                      'Sports and Outdoors', 'Sports')
             RETURNING id),
     category_7 AS (
         INSERT INTO product_category (id, description, name) VALUES (nextval('product_category_seq'), 'Toys and Games',
                                                                      'Toys')
             RETURNING id),
     category_8 AS (
         INSERT INTO product_category (id, description, name) VALUES (nextval('product_category_seq'),
                                                                      'Automotive and Industrial', 'Automotive')
             RETURNING id),
     category_9 AS (
         INSERT INTO product_category (id, description, name) VALUES (nextval('product_category_seq'),
                                                                      'Groceries and Essentials', 'Groceries')
             RETURNING id),
     category_10 AS (
         INSERT INTO product_category (id, description, name) VALUES (nextval('product_category_seq'), 'Pet Supplies',
                                                                      'Pets')
             RETURNING id)

-- Insert data into product using the captured category IDs
INSERT
INTO product (id, description, name, available_quantity, price, product_category_id)
VALUES (nextval('product_seq'), 'Smartphone with 128GB storage', 'Smartphone X', 150, 699.99,
        (SELECT id FROM category_ids)),
       (nextval('product_seq'), '4K Ultra HD TV with HDR', 'Ultra HD TV', 75, 1200.00, (SELECT id FROM category_ids)),
       (nextval('product_seq'), 'High-power blender', 'Blender Pro', 300, 89.99, (SELECT id FROM category_2)),
       (nextval('product_seq'), 'Air fryer with digital display', 'Air Fryer', 200, 129.50,
        (SELECT id FROM category_2)),
       (nextval('product_seq'), 'Fiction novel by a famous author', 'The Great Novel', 500, 15.99,
        (SELECT id FROM category_3)),
       (nextval('product_seq'), 'Academic textbook on programming', 'Programming 101', 250, 49.99,
        (SELECT id FROM category_3)),
       (nextval('product_seq'), 'Men''s leather jacket', 'Leather Jacket', 50, 199.99, (SELECT id FROM category_4)),
       (nextval('product_seq'), 'Women''s summer dress', 'Summer Dress', 120, 39.99, (SELECT id FROM category_4)),
       (nextval('product_seq'), 'Organic face moisturizer', 'Moisturizer', 400, 25.50, (SELECT id FROM category_5)),
       (nextval('product_seq'), 'Vitamin C serum', 'Vitamin C Serum', 350, 18.75, (SELECT id FROM category_5)),
       (nextval('product_seq'), 'Yoga mat with extra grip', 'Yoga Mat', 200, 35.99, (SELECT id FROM category_6)),
       (nextval('product_seq'), 'Set of 3 resistance bands', 'Resistance Bands', 150, 22.50,
        (SELECT id FROM category_6)),
       (nextval('product_seq'), 'Educational building blocks', 'Building Blocks', 500, 29.99,
        (SELECT id FROM category_7)),
       (nextval('product_seq'), 'Remote-controlled car', 'RC Car', 250, 45.99, (SELECT id FROM category_7)),
       (nextval('product_seq'), 'Car cleaning kit', 'Cleaning Kit', 180, 35.00, (SELECT id FROM category_8)),
       (nextval('product_seq'), 'Engine oil for cars', 'Engine Oil', 300, 25.99, (SELECT id FROM category_8)),
       (nextval('product_seq'), 'Organic honey', 'Organic Honey', 400, 12.99, (SELECT id FROM category_9)),
       (nextval('product_seq'), 'Pack of 12 eggs', 'Organic Eggs', 600, 3.99, (SELECT id FROM category_9)),
       (nextval('product_seq'), 'Premium dry dog food', 'Dry Dog Food', 250, 49.99, (SELECT id FROM category_10)),
       (nextval('product_seq'), 'Cat scratching post', 'Scratching Post', 150, 34.50, (SELECT id FROM category_10));
