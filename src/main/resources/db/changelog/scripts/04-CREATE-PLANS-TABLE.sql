CREATE TABLE linklio.plans (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    price DECIMAL(10, 2),
    link_limit INT,
    description TEXT
);

INSERT INTO linklio.plans(id, name, price, link_limit, description)
VALUES
  (1, 'FREE', 0.00, 10, 'Basic plan with up to 10 links'),
  (2, 'PRO', 3.99, 1000, 'Pro plan with advanced features'),
  (3, 'ENTERPRISE', 49.99, NULL, 'Unlimited access and support');