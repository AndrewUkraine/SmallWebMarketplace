


INSERT INTO category (category) VALUES
  ('MOTORS'),
  ('HOME AND GARDEN'),
  ('SPORTING GOODS'),
  ('FASHION');

INSERT INTO image (name, path, offer_id) VALUES
  ('mydocument', 'image1', 1),
  ('mydocument', 'image2', 2);

INSERT INTO offer (`condition`, creation_time, visible, description, price, title, buyer_id, category_id) VALUES
('rent car', now(), true, 'bla bla bla', 50.0, 'auto', 1, 1),
('buy car', now(), true, 'bla bla bla', 100.0, 'auto', 2, 2);


INSERT INTO user(city, email, first_name, password, phone, last_name) VALUES
('Kyiv', 'admin@mail.ru', 'Andrii', '1234567', '0938630809', 'Shatov'),
('Mariupol', 'user@mail.ru', 'Vova', '98765', '0953750864', 'Ivanov');



INSERT INTO user_role (role_type) VALUES
('ADMIN'),
('USER');

INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1),
(2, 2);

INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1),
(2, 2);

